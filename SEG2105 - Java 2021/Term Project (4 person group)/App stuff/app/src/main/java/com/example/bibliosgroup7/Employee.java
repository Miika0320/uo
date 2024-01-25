package com.example.bibliosgroup7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Employee extends AppCompatActivity{
    Account account;
    Time open;
    Time close;
    Button btnWorkingHours;
    ArrayList<Service> services;
    Button btnService;
    Button btnServiceRequests;
    Button reviews;
    EditText name;
    Button editInfo;
    Button view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        Bundle bundle = getIntent().getExtras();
        account = new Account(bundle.getString("eID"), bundle.getString("USERNAME"), bundle.getString("ROLE"), bundle.getString("PASSWORD"));

        editInfo = findViewById(R.id.btnEditInfo);
        name = (EditText) findViewById(R.id.editTextWelcome);
        btnService = (Button) findViewById(R.id.buttonSetServices);
        btnServiceRequests = (Button) findViewById(R.id.btnServiceRequests);
        btnWorkingHours = (Button) findViewById(R.id.buttonWorkingHours);
        view = findViewById(R.id.btnViewAccount);
        reviews = findViewById(R.id.buttonRatings);

        services = new ArrayList<>();
        String str = "Welcome "+bundle.getString("USERNAME");
        name.setText(str);

        reviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRatingsDialog(account);
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAccountDialog(account);
            }
        });

        editInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUpdateDialog(account);
            }
        });

        DatabaseReference dR = FirebaseDatabase.getInstance().getReference().child("accounts");

        String SRid = dR.push().getKey();

        DatabaseReference SR = FirebaseDatabase.getInstance().getReference("accounts").child(SRid);
        //removing account
        SR.removeValue();

        dR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot userSnapshot: snapshot.getChildren()) {

                    if (userSnapshot.getValue(Account.class).getAccountId().equals(bundle.getString("eID")) ){
                        account = userSnapshot.getValue(Account.class);

                        break;

                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("absolute mayhem", "this thing is cancelled omg");
            }
        });


        if (bundle.getBoolean("NEW")) {
                showUpdateDialog(account);
                bundle.putBoolean("NEW",false);
            }



        FirebaseDatabase.getInstance().getReference("services").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //clearing the previous account list


                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting account
                    Service service = postSnapshot.getValue(Service.class);
                    //adding a account to the list
                    services.add(service);
                    if (account.getActive().size() == 0)
                        account.addActive(service);
                }
                    DatabaseReference dR = FirebaseDatabase.getInstance().getReference("accounts").child(bundle.getString("eID"));
                    //updating account
                    dR.setValue(account);

                }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        btnServiceRequests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(Employee.this, EmployeeSRView.class);
              intent.putExtras(bundle);
              startActivity(intent);
            }
        });

        btnService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Employee.this, SetServices.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


        btnWorkingHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWorkingHoursDialog(account);
            }
        });
    }

    private void showAccountDialog(Account account) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.information, null);
        dialogBuilder.setView(dialogView);

        dialogBuilder.setTitle("Account Info");
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



        name.setText("Username: "+account.getUsername());
        address.setText("Address: "+account.getAccountAddress());
        phone.setText("Phone Number: "+account.getNumber());
        sun.setText("Sunday: "+account.getSunOpen()+"-"+account.getSunClose());
        mon.setText("Monday: " +account.getMonOpen()+ "-" + account.getMonClose());
        tues.setText("Tuesday: " +account.getTuesOpen()+ "-" + account.getTuesClose());
        wed.setText("Wednesday: "+ account.getWedOpen()+ "-" + account.getWedClose());
        thurs.setText("Thursday: "+ account.getThursOpen()+ "-" + account.getThursClose());
        fri.setText("Friday: " +account.getFriOpen()+ "-" + account.getFriClose());
        sat.setText("Saturday: " +account.getSatOpen()+ "-" + account.getSatClose());
        String servics = "";

        for (int i=0;i<account.getActive().size(); i++){
            if (i == account.getActive().size()-1){
                servics += account.getActive().get(i).getServiceName();
            }else{
                servics+=account.getActive().get(i).getServiceName()+", ";
            }
        }
        services.setText("Services Offered: "+servics);


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cd.dismiss();
            }
        });
    }

    private void showWorkingHoursDialog(Account account){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_workinghours, null);
        dialogBuilder.setView(dialogView);

        final EditText mondayOpen = (EditText) dialogView.findViewById(R.id.mondayOpen);
        final EditText mondayClose = (EditText) dialogView.findViewById(R.id.mondayClose);
        final EditText tuesdayOpen  = (EditText) dialogView.findViewById(R.id.tuesdayOpen);
        final EditText tuesdayClose = (EditText) dialogView.findViewById(R.id.tuesdayClose);
        final EditText wednesdayOpen = (EditText) dialogView.findViewById(R.id.wednesdayOpen);
        final EditText wednesdayClose  = (EditText) dialogView.findViewById(R.id.wednesdayClose);
        final EditText thursdayOpen = (EditText) dialogView.findViewById(R.id.thursdayOpen);
        final EditText thursdayClose = (EditText) dialogView.findViewById(R.id.thursdayClose);
        final EditText fridayOpen  = (EditText) dialogView.findViewById(R.id.fridayOpen);
        final EditText fridayClose = (EditText) dialogView.findViewById(R.id.fridayClose);
        final EditText saturdayOpen = (EditText) dialogView.findViewById(R.id.saturdayOpen);
        final EditText saturdayClose  = (EditText) dialogView.findViewById(R.id.saturdayClose);
        final EditText sundayOpen = (EditText) dialogView.findViewById(R.id.sundayOpen);
        final EditText sundayClose  = (EditText) dialogView.findViewById(R.id.sundayClose);
        final Button workingSave = (Button) dialogView.findViewById(R.id.btnWorkingSave);

        dialogBuilder.setTitle("Please set your working hours, "+account.getUsername());
        final AlertDialog cd = dialogBuilder.create();
        cd.show();

        workingSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String monOpen = mondayOpen.getText().toString();
                String monClose = mondayClose.getText().toString();
                String tuesOpen = tuesdayOpen.getText().toString();
                String tuesClose = tuesdayClose.getText().toString();
                String wedOpen = wednesdayOpen.getText().toString();
                String wedClose = wednesdayClose.getText().toString();
                String thursOpen = thursdayOpen.getText().toString();
                String thursClose = thursdayClose.getText().toString();
                String friOpen = fridayOpen.getText().toString();
                String friClose = fridayClose.getText().toString();
                String satOpen = saturdayOpen.getText().toString();
                String satClose = saturdayClose.getText().toString();
                String sunOpen = sundayOpen.getText().toString();
                String sunClose = sundayClose.getText().toString();



                Boolean good = true;

                if (monOpen.equals("") || !monOpen.contains(":")) {
                    good = false;
                    mondayOpen.requestFocus();
                    mondayOpen.setError("PLEASE ENTER A TIME");
                }
                if (monClose.equals("") || !monClose.contains(":")) {
                    good = false;
                    mondayClose.requestFocus();
                    mondayClose.setError("PLEASE ENTER A TIME");
                }
                if (tuesOpen.equals("") || !tuesOpen.contains(":")) {
                    good = false;
                    tuesdayOpen.requestFocus();
                    tuesdayOpen.setError("PLEASE ENTER A TIME");
                }
                if (tuesClose.equals("") || !tuesClose.contains(":")) {
                    good = false;
                    tuesdayClose.requestFocus();
                    tuesdayClose.setError("PLEASE ENTER A TIME");
                }
                if (wedOpen.equals("") || !wedOpen.contains(":")) {
                    good = false;
                    wednesdayOpen.requestFocus();
                    wednesdayOpen.setError("PLEASE ENTER A TIME");
                }
                if (thursOpen.equals("") || !thursOpen.contains(":")) {
                    good = false;
                    thursdayOpen.requestFocus();
                    thursdayOpen.setError("PLEASE ENTER A TIME");
                }
                if (thursClose.equals("") || !thursClose.contains(":")) {
                    good = false;
                    thursdayClose.requestFocus();
                    thursdayClose.setError("PLEASE ENTER A TIME");
                }
                if (friOpen.equals("") || !friOpen.contains(":")) {
                    good = false;
                    fridayOpen.requestFocus();
                    fridayOpen.setError("PLEASE ENTER A TIME");
                }
                if (friClose.equals("") || !friClose.contains(":")) {
                    good = false;
                    fridayClose.requestFocus();
                    fridayClose.setError("PLEASE ENTER A TIME");
                }
                if (satOpen.equals("") || !satOpen.contains(":")) {
                    good = false;
                    saturdayOpen.requestFocus();
                    saturdayOpen.setError("PLEASE ENTER A TIME");
                }
                if (satClose.equals("") || !satClose.contains(":")) {
                    good = false;
                    saturdayClose.requestFocus();
                    saturdayClose.setError("PLEASE ENTER A TIME");
                }
                if (sunOpen.equals("") || !sunOpen.contains(":")) {
                    good = false;
                    sundayOpen.requestFocus();
                    sundayOpen.setError("PLEASE ENTER A TIME");
                }
                if (sunClose.equals("") || !sunClose.contains(":")) {
                    good = false;
                    sundayClose.requestFocus();
                    sundayClose.setError("PLEASE ENTER A TIME");
                }
                if (wedClose.equals("") || !wedClose.contains(":")) {
                    good = false;
                    wednesdayClose.requestFocus();
                    wednesdayClose.setError("PLEASE ENTER A TIME");
                }





                if (good) {
                    account.setWorkingHours(monOpen, monClose, tuesOpen, tuesClose, wedOpen, wedClose, thursOpen, thursClose, friOpen, friClose, satOpen, satClose, sunOpen, sunClose);
                    //account.setNumber(number.getText().toString());
                    update(account);
                    cd.dismiss();

                }
            }
        });

    }

    private void showUpdateDialog(Account account){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.employee_info, null);
        dialogBuilder.setView(dialogView);


        final EditText number = (EditText) dialogView.findViewById(R.id.editTextPhoneNumber);
        final EditText homeNumber = (EditText) dialogView.findViewById(R.id.editTextRating);
        final EditText street  = (EditText) dialogView.findViewById(R.id.editTextStreetName);
        final EditText city = (EditText) dialogView.findViewById(R.id.editTextCity);
        final EditText province = (EditText) dialogView.findViewById(R.id.editTextState);
        final EditText zip  = (EditText) dialogView.findViewById(R.id.editTextZip);
        final EditText country = (EditText) dialogView.findViewById(R.id.editTextCountry);
        final Button buttonSave = (Button) dialogView.findViewById(R.id.buttonSave);


        dialogBuilder.setTitle("Welcome "+account.getUsername());
        final AlertDialog b = dialogBuilder.create();
        b.show();

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone =number.getText().toString();
                String hn = homeNumber.getText().toString();
                String st = street.getText().toString();
                String c = city.getText().toString();
                String p = province.getText().toString();
                String z = zip.getText().toString();
                String pays = country.getText().toString();
                Boolean good = true;

                if (hn.equals("")) {
                    good = false;
                    homeNumber.requestFocus();
                    homeNumber.setError("FIELD CANNOT BE EMPTY");
                }

                //role edge cases
                if (st.equals("")) {
                    good = false;
                    street.requestFocus();
                    street.setError("FIELD CANNOT BE EMPTY");
                }

                if (phone.equals("")) {
                    good = false;
                    number.requestFocus();
                    number.setError("FIELD CANNOT BE EMPTY");
                }else if(phone.length()>12 || phone.length()<9 ) {
                    good = false;
                    number.requestFocus();
                    number.setError("PHONE NUMBER MUST BE BETWEEN 9 AND 12 DIGITS");
                }


                if(c.equals("")){
                    good=false;
                    city.requestFocus();
                    city.setError("FIELD CANNOT BE EMPTY");
                }


                if(p.equals("")){
                    good = false;
                    province.requestFocus();
                    province.setError("FIELD CANNOT BE EMPTY");
                }


                if(z.equals("")) {
                    good = false;
                    zip.requestFocus();
                    zip.setError("FIELD CANNOT BE EMPTY");
                }


                if(pays.equals("")){
                    good = false;
                    country.requestFocus();
                    country.setError("FIELD CANNOT BE EMPTY");
                }

                if (good) {
                    String address = hn + " " + st + " " +c + " " + p + " "+ z + " " +pays;
                    account.setAccountAddress(address);
                    account.setNumber(number.getText().toString());
                    update(account);
                    b.dismiss();

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


        RateList accountsAdapter = new RateList(Employee.this, a.getRatings());
        //attaching adapter to listview
        listRatings.setAdapter(accountsAdapter);
    }

    /*private void showServiceDialog(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        //final View dialogView = inflater.inflate(R.layout.service_choice, null);
        //dialogBuilder.setView(dialogView);

        dialogBuilder.setTitle("Services");
        final List<String> all = new ArrayList<>();
        for(int i =0; i<services.size(); i++){
            all.add(services.get(i).getServiceName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, findViewById(R.id.textView2), all);
        dialogBuilder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Service serv = services.get(which);
                innerDialog(serv);


            }
        });
        final AlertDialog b = dialogBuilder.create();
        b.show();


    }
    private void innerDialog(Service s){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.set_service, null);
        dialogBuilder.setView(dialogView);

        Button btna = findViewById(R.id.btnAdd);
        Button btnr = findViewById(R.id.btnRemove);
        TextView name= findViewById(R.id.txtService);

        name.setText(s.getServiceName());

        btna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!account.getActive().contains(s)){
                    account.addActive(s);
                }
            }
        });

        btnr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(account.getActive().contains(s)){
                    account.delActive(s);
                }
            }
        });

    }*/
    private void update(Account account){
        //update database account info
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("accounts").child(account.getAccountId());
        //updating account
        dR.setValue(account);
    }
}