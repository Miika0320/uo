package com.example.bibliosgroup7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class customerAccount extends AppCompatActivity {

    Account account;
    Button btnSearchAddress;
    Button btnRatings;
    Button btnViewRequests;
    DatabaseReference databaseAccounts;
    List<Account> accounts;
    DatabaseReference databaseServiceRequests;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_account);
        bundle = getIntent().getExtras();
        account = new Account(bundle.getString("ID"), bundle.getString("USERNAME"), bundle.getString("ROLE"), bundle.getString("PASSWORD"));
        String userID = bundle.getString("USERNAME");
        userID = "Welcome "+userID;
        TextView vText = (TextView)findViewById(R.id.textViewWelcome);
        vText.setText(userID);

        databaseAccounts = FirebaseDatabase.getInstance().getReference("accounts");
        databaseServiceRequests = FirebaseDatabase.getInstance().getReference("service_requests");
        accounts = new ArrayList<>();

        //initializing buttons
        btnSearchAddress = (Button)findViewById(R.id.btnSearchAddress);
        btnRatings = (Button)findViewById(R.id.btnViewRatings);
        btnViewRequests = (Button)findViewById(R.id.btnViewRequests);

        btnRatings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRatingsDialog(account);
            }
        });

        btnSearchAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { showSearchDialog(account); }
        });

        btnViewRequests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRequestsDialog();
            }
        });



        String SRid = databaseAccounts.push().getKey();

        DatabaseReference SR = FirebaseDatabase.getInstance().getReference("accounts").child(SRid);
        //removing account
        SR.removeValue();

        databaseAccounts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot userSnapshot: snapshot.getChildren()) {

                    if (userSnapshot.getValue(Account.class).getAccountId().equals(bundle.getString("ID")) ){
                        account = userSnapshot.getValue(Account.class);

                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void showSearchDialog(Account account)
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.search, null);
        dialogBuilder.setView(dialogView);



        final EditText searchbar = (EditText)dialogView.findViewById(R.id.editTextSearchBar);
        final TextView results = (TextView)dialogView.findViewById(R.id.textResults);
        final ListView listResults = (ListView)dialogView.findViewById(R.id.listViewResults);
        final RadioGroup radioGroup = (RadioGroup)dialogView.findViewById(R.id.theRadioGroup);
        final Button btnStartSearch = (Button)dialogView.findViewById(R.id.btnStartSearch);

        dialogBuilder.setTitle("Search");
        final AlertDialog cd = dialogBuilder.create();
        cd.show();

        radioGroup.clearCheck();
        //radio button set up
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override

            // The flow will come here when
            // any of the radio buttons in the radioGroup
            // has been clicked

            // Check which radio button has been clicked
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {

                // Get the selected Radio Button
                RadioButton rButton = (RadioButton)group.findViewById(checkedId);
            }
        });
        listResults.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Account a = accounts.get(i);
                if (a.getAccountId()!=null) {
                    showAccountDialog(a);
                }else{
                    String id = databaseAccounts.push().getKey();
                    a.setAccountId(id);
                    showAccountDialog(a);


                }
                return true;
            }
        });


        btnStartSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selected = radioGroup.getCheckedRadioButtonId();
                if (selected == -1) //no radio button has been selected
                {
                    Toast.makeText(getApplicationContext(), "Please select what to search by", Toast.LENGTH_LONG).show();
                } else {
                    RadioButton rButton = (RadioButton) radioGroup.findViewById(selected);
                    String select = (String) rButton.getText();
                    String query = searchbar.getText().toString();
                    if (query.isEmpty()) {
                        searchbar.requestFocus();
                        searchbar.setError("ENTER A SEARCH");
                    } else {
                        databaseAccounts.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                //clearing the previous account list
                                accounts.clear();
                                //iterating through all the nodes
                                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                                    //getting account
                                    Account a = postSnapshot.getValue(Account.class);
                                    if (a.getRole().equals("Employee") || a.getRole().equals("employee")) {
                                        if (select.equals("Address")) {
                                            if (a.getAccountAddress() != null && (a.getAccountAddress().equals(query)|| a.getAccountAddress().contains(query))){
                                                //adding a account to the list
                                                accounts.add(a);
                                            }
                                        }else if (select.equals("Service")){
                                            ArrayList<Service> s = a.getActive();
                                            boolean b = false;
                                            for (int i = 0; i < s.size(); i++)
                                            {
                                                if (s.get(i).getServiceName().equals(query))
                                                {
                                                    b = true;
                                                }
                                            }
                                            if (b){
                                                accounts.add(a);
                                            }
                                        }else if (select.equals("Hours")){
                                            String[] q = query.trim().split(" ");
                                            String[] q2 = q[1].split(":");
                                            if (q[0].equals("Monday") || q[0].equals("monday")){
                                                if (!a.getMonOpen().isEmpty() && !a.getMonClose().isEmpty()) {
                                                    String[] open = a.getMonOpen().split(":");
                                                    int open1 = Integer.parseInt(open[0]);
                                                    String[] close = a.getMonClose().split(":");
                                                    int close1 = Integer.parseInt(close[0]);
                                                    int close2 = Integer.parseInt(close[1]);
                                                    if (Integer.parseInt(q2[0]) > open1 && Integer.parseInt(q2[0]) < close1) {
                                                        accounts.add(a);
                                                    }else if (Integer.parseInt(q2[0]) == open1 && Integer.parseInt(q2[0]) != close1){
                                                        accounts.add(a);
                                                    }else if (Integer.parseInt(q2[0]) == close1 && Integer.parseInt(q2[1]) == close2){
                                                        accounts.add(a);
                                                    }
                                                }
                                            }else if (q[0].equals("Tuesday") || q[0].equals("tuesday")){
                                                if (!a.getTuesOpen().isEmpty() && !a.getTuesClose().isEmpty()) {
                                                    String[] open = a.getTuesOpen().split(":");
                                                    int open1 = Integer.parseInt(open[0]);
                                                    String[] close = a.getTuesClose().split(":");
                                                    int close1 = Integer.parseInt(close[0]);
                                                    int close2 = Integer.parseInt(close[1]);
                                                    if (Integer.parseInt(q2[0]) > open1 && Integer.parseInt(q2[0]) < close1) {
                                                        accounts.add(a);
                                                    }else if (Integer.parseInt(q2[0]) == open1 && Integer.parseInt(q2[0]) != close1){
                                                        accounts.add(a);
                                                    }else if (Integer.parseInt(q2[0]) == close1 && Integer.parseInt(q2[1]) == close2){
                                                        accounts.add(a);
                                                    }
                                                }
                                            }else if (q[0].equals("Wednesday") || q[0].equals("wednesday")){
                                                if (!a.getWedOpen().isEmpty() && !a.getWedClose().isEmpty()) {
                                                    String[] open = a.getWedOpen().split(":");
                                                    int open1 = Integer.parseInt(open[0]);
                                                    String[] close = a.getWedClose().split(":");
                                                    int close1 = Integer.parseInt(close[0]);
                                                    int close2 = Integer.parseInt(close[1]);
                                                    if (Integer.parseInt(q2[0]) > open1 && Integer.parseInt(q2[0]) < close1) {
                                                        accounts.add(a);
                                                    }else if (Integer.parseInt(q2[0]) == open1 && Integer.parseInt(q2[0]) != close1){
                                                        accounts.add(a);
                                                    }else if (Integer.parseInt(q2[0]) == close1 && Integer.parseInt(q2[1]) == close2){
                                                        accounts.add(a);
                                                    }
                                                }
                                            }else if (q[0].equals("Thursday") || q[0].equals("thursday")){
                                                if (!a.getThursOpen().isEmpty() && !a.getThursClose().isEmpty()) {
                                                    String[] open = a.getThursOpen().split(":");
                                                    int open1 = Integer.parseInt(open[0]);
                                                    String[] close = a.getThursClose().split(":");
                                                    int close1 = Integer.parseInt(close[0]);
                                                    int close2 = Integer.parseInt(close[1]);
                                                    if (Integer.parseInt(q2[0]) > open1 && Integer.parseInt(q2[0]) < close1) {
                                                        accounts.add(a);
                                                    }else if (Integer.parseInt(q2[0]) == open1 && Integer.parseInt(q2[0]) != close1){
                                                        accounts.add(a);
                                                    }else if (Integer.parseInt(q2[0]) == close1 && Integer.parseInt(q2[1]) == close2){
                                                        accounts.add(a);
                                                    }
                                                }
                                            }else if (q[0].equals("Friday") || q[0].equals("friday")){
                                                if (!a.getFriOpen().isEmpty() && !a.getFriClose().isEmpty()) {
                                                    String[] open = a.getFriOpen().split(":");
                                                    int open1 = Integer.parseInt(open[0]);
                                                    String[] close = a.getFriClose().split(":");
                                                    int close1 = Integer.parseInt(close[0]);
                                                    int close2 = Integer.parseInt(close[1]);
                                                    if (Integer.parseInt(q2[0]) > open1 && Integer.parseInt(q2[0]) < close1) {
                                                        accounts.add(a);
                                                    }else if (Integer.parseInt(q2[0]) == open1 && Integer.parseInt(q2[0]) != close1){
                                                        accounts.add(a);
                                                    }else if (Integer.parseInt(q2[0]) == close1 && Integer.parseInt(q2[1]) == close2){
                                                        accounts.add(a);
                                                    }
                                                }
                                            }else if (q[0].equals("Saturday") || q[0].equals("saturday")){
                                                if (!a.getSatOpen().isEmpty() && !a.getSatClose().isEmpty()) {
                                                    String[] open = a.getSatOpen().split(":");
                                                    int open1 = Integer.parseInt(open[0]);
                                                    String[] close = a.getSatClose().split(":");
                                                    int close1 = Integer.parseInt(close[0]);
                                                    int close2 = Integer.parseInt(close[1]);
                                                    if (Integer.parseInt(q2[0]) > open1 && Integer.parseInt(q2[0]) < close1) {
                                                        accounts.add(a);
                                                    }else if (Integer.parseInt(q2[0]) == open1 && Integer.parseInt(q2[0]) != close1){
                                                        accounts.add(a);
                                                    }else if (Integer.parseInt(q2[0]) == close1 && Integer.parseInt(q2[1]) == close2){
                                                        accounts.add(a);
                                                    }
                                                }
                                            }else if (q[0].equals("Sunday") || q[0].equals("sunday")){
                                                if (!a.getSunOpen().isEmpty() && !a.getSunClose().isEmpty()) {
                                                    String[] open = a.getSunOpen().split(":");
                                                    int open1 = Integer.parseInt(open[0]);
                                                    String[] close = a.getSunClose().split(":");
                                                    int close1 = Integer.parseInt(close[0]);
                                                    int close2 = Integer.parseInt(close[1]);
                                                    if (Integer.parseInt(q2[0]) > open1 && Integer.parseInt(q2[0]) < close1) {
                                                        accounts.add(a);
                                                    }else if (Integer.parseInt(q2[0]) == open1 && Integer.parseInt(q2[0]) != close1){
                                                        accounts.add(a);
                                                    }else if (Integer.parseInt(q2[0]) == close1 && Integer.parseInt(q2[1]) == close2){
                                                        accounts.add(a);
                                                    }
                                                }
                                            }else{
                                                searchbar.requestFocus();
                                                searchbar.setError("ENTER A VALID DAY AND TIME");
                                            }
                                        }
                                    }

                                }
                                //creating adapter
                                SearchList accountsAdapter = new SearchList(customerAccount.this, accounts);
                                //attaching adapter to listview
                                listResults.setAdapter(accountsAdapter);
                                if (accounts.isEmpty()) {
                                    results.setText("No results found.");
                                } else {
                                    results.setText("Results:");
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                }
            }
        });
    }
    private void showAccountDialog(Account a) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.branch_information, null);
        dialogBuilder.setView(dialogView);

        dialogBuilder.setTitle("Branch Info");
        final AlertDialog cd = dialogBuilder.create();
        final Button ok = dialogView.findViewById(R.id.btnok);
        cd.show();
        final TextView name = dialogView.findViewById(R.id.txtEName);
        final TextView address = dialogView.findViewById(R.id.txtAddress);
        final TextView phone = dialogView.findViewById(R.id.txtPhone);
        final TextView sun = dialogView.findViewById(R.id.txtSunday);
        final TextView mon = dialogView.findViewById(R.id.txtlastName);
        final TextView tues = dialogView.findViewById(R.id.txtTuesday);
        final TextView wed = dialogView.findViewById(R.id.txtWednesday);
        final TextView thurs = dialogView.findViewById(R.id.txtThursday);
        final TextView fri = dialogView.findViewById(R.id.txtFriday);
        final TextView sat = dialogView.findViewById(R.id.txtSaturday);
        final TextView services = dialogView.findViewById(R.id.txtServices);
        final Button rate = dialogView.findViewById(R.id.buttonRate);
        final Button newR = dialogView.findViewById(R.id.buttonNewRequest);



        name.setText("Username: "+a.getUsername());
        address.setText("Address: "+a.getAccountAddress());
        phone.setText("Phone Number: "+a.getNumber());
        sun.setText("Sunday: "+a.getSunOpen()+"-"+a.getSunClose());
        mon.setText("Monday: " +a.getMonOpen()+ "-" + a.getMonClose());
        tues.setText("Tuesday: " +a.getTuesOpen()+ "-" + a.getTuesClose());
        wed.setText("Wednesday: "+ a.getWedOpen()+ "-" + a.getWedClose());
        thurs.setText("Thursday: "+ a.getThursOpen()+ "-" + a.getThursClose());
        fri.setText("Friday: " +a.getFriOpen()+ "-" + a.getFriClose());
        sat.setText("Saturday: " +a.getSatOpen()+ "-" + a.getSatClose());
        String servics = "";

        for (int i=0;i<a.getActive().size(); i++){
            if (i == a.getActive().size()-1){
                servics += a.getActive().get(i).getServiceName();
            }else{
                servics+=a.getActive().get(i).getServiceName()+", ";
            }
        }
        services.setText("Services Offered: "+servics);

        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRatingDialog(a, account);
            }
        });

        newR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(customerAccount.this, ServiceRequestCreate.class);
                intent.putExtra("eID", a.getAccountId());
                intent.putExtras(bundle);
                intent.putExtra("CLASS", "customer");
                startActivity(intent);
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cd.dismiss();
            }
        });
    }

    private void showRatingDialog(Account a, Account b) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.create_rating, null);
        dialogBuilder.setView(dialogView);

        dialogBuilder.setTitle("Rating");
        final AlertDialog cd = dialogBuilder.create();

        final EditText rate = dialogView.findViewById(R.id.editTextRating);
        final EditText comment = dialogView.findViewById(R.id.editTextComment);
        final Button save = dialogView.findViewById(R.id.buttonSave);
        final Button delete = dialogView.findViewById(R.id.buttonDelete);

        cd.show();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = rate.getText().toString().trim();
                String com = comment.getText().toString().trim();
                Boolean error = false;
                if (num.isEmpty()){
                    error = true;
                    rate.requestFocus();
                    rate.setError("FIELD CANNOT BE EMPTY");
                }else if (Integer.parseInt(num)<1 || Integer.parseInt(num)>5){
                    error = true;
                    rate.requestFocus();
                    rate.setError("ENTER VALID RATING BETWEEN 1 AND 5");
                }
                if (!error) {
                    String rating = "Branch: " + a.getUsername() + ", " + num + " stars: " + com;
                    a.addRating(rating);
                    b.addRating(rating);

                    DatabaseReference dR = FirebaseDatabase.getInstance().getReference("accounts").child(b.getAccountId());
                    //updating account
                    dR.setValue(b);
                    DatabaseReference dW = FirebaseDatabase.getInstance().getReference("accounts").child(a.getAccountId());
                    //updating account
                    dW.setValue(a);
                    cd.dismiss();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = rate.getText().toString().trim();
                String com = comment.getText().toString().trim();
                Boolean error = false;
                if (num.isEmpty()){
                    error = true;
                    rate.requestFocus();
                    rate.setError("FIELD CANNOT BE EMPTY");
                }else if (Integer.parseInt(num)<1 || Integer.parseInt(num)>5){
                    error = true;
                    rate.requestFocus();
                    rate.setError("ENTER VALID RATING BETWEEN 1 AND 5");
                }
                if (!error) {
                    String rating = "Branch: " + a.getUsername() + ", " + num + " stars: " + com;
                    a.delRating(rating);
                    b.delRating(rating);
                    DatabaseReference dR = FirebaseDatabase.getInstance().getReference("accounts").child(b.getAccountId());
                    //updating account
                    dR.setValue(b);
                    DatabaseReference dW = FirebaseDatabase.getInstance().getReference("accounts").child(a.getAccountId());
                    //updating account
                    dW.setValue(a);
                    cd.dismiss();
                }
            }
        });
    }

    private void showRatingsDialog(Account a) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_ratings, null);
        dialogBuilder.setView(dialogView);

        final ListView listRatings = (ListView)dialogView.findViewById(R.id.listViewRatings);
        final Button ret = dialogView.findViewById(R.id.ButtonReturn);


        dialogBuilder.setTitle("Rating");
        final AlertDialog cd = dialogBuilder.create();
        cd.show();
        ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cd.dismiss();
            }
        });


        RateList accountsAdapter = new RateList(customerAccount.this, a.getRatings());
        //attaching adapter to listview
        listRatings.setAdapter(accountsAdapter);
    }

    private void showRequestsDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.s_choices_requests, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog cd = dialogBuilder.create();
        cd.show();

        final ListView listRequests = (ListView) dialogView.findViewById(R.id.RequestServices);
        final Button ret = dialogView.findViewById(R.id.buttonCancel);

        ArrayList<ServiceRequest> made = new ArrayList<>();
        databaseServiceRequests.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //clearing the previous account list
                made.clear();
                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    if (postSnapshot.getValue(ServiceRequest.class).getaccountId().equals(bundle.getString("ID")) ){
                        ServiceRequest request = postSnapshot.getValue(ServiceRequest.class);

                        made.add(request);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        listRequests.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ServiceRequest req = made.get(position);
                showRequestInfoDialog(req);
                return true;
            }
        });

        ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cd.dismiss();
            }
        });

        ServiceRequestList accountsAdapter = new ServiceRequestList(customerAccount.this, made);
        //attaching adapter to listview
        listRequests.setAdapter(accountsAdapter);

        dialogBuilder.setTitle("Requests");

    }
    private void showRequestInfoDialog(ServiceRequest s) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.request_info, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog cd = dialogBuilder.create();
        cd.show();
        dialogBuilder.setTitle(s.getServiceName());

        final TextView firstName = (TextView) dialogView.findViewById(R.id.txtFirstName);
        final TextView lastName  = (TextView) dialogView.findViewById(R.id.txtlastName);
        final TextView birthDate = (TextView) dialogView.findViewById(R.id.txtDOB);
        final TextView address = (TextView) dialogView.findViewById(R.id.txtAddress);
        final TextView price  = dialogView.findViewById(R.id.txtPrice);
        final TextView license = (TextView) dialogView.findViewById(R.id.txtLicense);
        final TextView distance = (TextView) dialogView.findViewById(R.id.txtDistance);
        final TextView start  = (TextView) dialogView.findViewById(R.id.txtStartLocation);
        final TextView email = (TextView) dialogView.findViewById(R.id.txtEmail);
        final TextView end = (TextView) dialogView.findViewById(R.id.txtEndLocation);
        final TextView movers = (TextView) dialogView.findViewById(R.id.txtMovers);
        final TextView boxes  = (TextView) dialogView.findViewById(R.id.txtBoxes);
        final TextView cars = dialogView.findViewById(R.id.txtCar);
        final TextView pickup = dialogView.findViewById(R.id.txtPickupDate);
        final TextView returnD = dialogView.findViewById(R.id.txtReturnDate);
        final TextView pTime = dialogView.findViewById(R.id.txtPickupTime);
        final TextView rTime = dialogView.findViewById(R.id.txtReturnTime);
        final Button ok = dialogView.findViewById(R.id.btnok);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cd.dismiss();
            }
        });

        firstName.setText(s.getFirstName());
        lastName.setText(s.getLastName());
        birthDate.setText(s.getDOB());
        address.setText(s.getAddress());
        price.setText(String.valueOf(s.getPrice()));
        license.setText(s.getLicense());
        distance.setText(s.getMaxDistance());
        start.setText(s.getStart());
        email.setText(s.getEmailAddress());
        end.setText(s.getEnd());
        movers.setText(s.getMovers());
        boxes.setText(s.getBoxes());
        cars.setText(s.getCar());
        pickup.setText(s.getPickup());
        returnD.setText(s.getReturnDate());
        pTime.setText(s.getPickupTime());
        rTime.setText(s.getReturnTime());
    }

}

