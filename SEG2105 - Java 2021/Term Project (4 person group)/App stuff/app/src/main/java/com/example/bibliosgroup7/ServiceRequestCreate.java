package com.example.bibliosgroup7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import android.os.Bundle;

public class ServiceRequestCreate extends AppCompatActivity {
    ListView listViewRequests;
    ArrayList<Service> services;
    DatabaseReference databaseSrequests;
    DatabaseReference databaseAccounts;
    ArrayList<Account> accounts;
    ServiceRequest request;
    Bundle bundle;
    Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_choices_requests);
        bundle = getIntent().getExtras();
        request = new ServiceRequest();
        listViewRequests = findViewById(R.id.RequestServices);
        accounts = new ArrayList<>();
        services = new ArrayList<>();
        databaseAccounts = FirebaseDatabase.getInstance().getReference("accounts");
        databaseSrequests = FirebaseDatabase.getInstance().getReference("service_requests");
        cancel = findViewById(R.id.buttonCancel);

        listViewRequests.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Service service = services.get(i);
                request = new ServiceRequest();
                request.setServiceName(service.getServiceName());
                request.setPrice(service.getPrice());
                if (request.getServiceRequestId()!=null) {
                    showServiceRequestUpdateDeleteDialog(request, service);
                } else {
                    String id = databaseSrequests.push().getKey();
                    request.setServiceRequestId(id);
                    databaseSrequests.child(id).setValue(request);
                    showServiceRequestUpdateDeleteDialog(request, service);
                }
                return true;
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bundle.getString("CLASS").equals("employee")){
                    Intent intent = new Intent(ServiceRequestCreate.this, EmployeeSRView.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(ServiceRequestCreate.this, customerAccount.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }

        });

    }
    @Override
    protected void onStart() {
        super.onStart();

        databaseAccounts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //clearing the previous account list
                
                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting account
                    Account account = postSnapshot.getValue(Account.class);
                    //adding a account to the list
                    accounts.add(account);
                    if(bundle.getString("ID")!=null&& account.getAccountId().equals(bundle.getString("eID")))
                        request.setChosenBranch(account);
                        services = request.getChosenBranch().getActive();

                }
                //creating adapter

                ServiceList serviceAdapter = new ServiceList(ServiceRequestCreate.this, services);
                //attaching adapter to listview
                listViewRequests.setAdapter(serviceAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        FirebaseDatabase.getInstance().getReference("services").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //clearing the previous account list

                if (services.size() == 0)
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        //getting account
                        Service service = postSnapshot.getValue(Service.class);
                        //adding a account to the list
                        services.add(service);

                    }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void showServiceRequestUpdateDeleteDialog(ServiceRequest srequest, Service service) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.branch_request, null);
        dialogBuilder.setView(dialogView);

        final EditText firstName = (EditText) dialogView.findViewById(R.id.editTextFirstName);
        final EditText lastName  = (EditText) dialogView.findViewById(R.id.editTextLastName);
        final EditText birthDate = (EditText) dialogView.findViewById(R.id.editTextDOB);
        final EditText address = (EditText) dialogView.findViewById(R.id.editTextRating);
        final TextView price  = dialogView.findViewById(R.id.txtPrice);
        final EditText license = (EditText) dialogView.findViewById(R.id.editTextLicense);
        final EditText distance = (EditText) dialogView.findViewById(R.id.editTextDistance);
        final EditText start  = (EditText) dialogView.findViewById(R.id.editTextPickup);
        final EditText email = (EditText) dialogView.findViewById(R.id.editTextEmail);
        final EditText end = (EditText) dialogView.findViewById(R.id.editTextReturn);
        final EditText movers = (EditText) dialogView.findViewById(R.id.editTextMovers);
        final EditText boxes  = (EditText) dialogView.findViewById(R.id.editTextBoxes);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdateService);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDeleteService);
        final EditText cars = dialogView.findViewById(R.id.editTextCar);
        final EditText sLocate = dialogView.findViewById(R.id.editTextStart);
        final EditText eLocate = dialogView.findViewById(R.id.editTextEnd);
        final CalendarView birth = dialogView.findViewById(R.id.birth);
        final CalendarView pD = dialogView.findViewById(R.id.pickupDate);
        final CalendarView rD = dialogView.findViewById(R.id.returnDate);
        final EditText pTime = dialogView.findViewById(R.id.editTextPickupTime);
        final EditText rTime = dialogView.findViewById(R.id.editTextReturnTime);


        birth.setVisibility(View.GONE);
        pD.setVisibility(View.GONE);
        rD.setVisibility(View.GONE);

        price.setText("$"+Double.toString(srequest.getPrice()));
        dialogBuilder.setTitle(srequest.getServiceName());
        final AlertDialog b = dialogBuilder.create();
        b.show();

        birthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                birth.setVisibility(View.VISIBLE);
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pD.setVisibility(View.VISIBLE);
            }
        });

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rD.setVisibility(View.VISIBLE);
            }
        });

        rD.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                end.setText(String.valueOf(dayOfMonth)+"/"+String.valueOf(month+1)+"/"+String.valueOf(year));
                rD.setVisibility(View.GONE);
            }
        });

        pD.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                start.setText(String.valueOf(dayOfMonth)+"/"+String.valueOf(month+1)+"/"+String.valueOf(year));
                pD.setVisibility(View.GONE);
            }
        });

        birth.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                birthDate.setText(String.valueOf(dayOfMonth)+"/"+String.valueOf(month+1)+"/"+String.valueOf(year));
                birth.setVisibility(View.GONE);
            }
        });




       buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pweekday;
                int rweekday;
                Calendar calendar = Calendar.getInstance();

                String firstName2 = firstName.getText().toString().trim();
                String lastName2 = lastName.getText().toString().trim();
                String birthDate2 = birthDate.getText().toString().trim();
                String address2 = address.getText().toString().trim();
                String license2 = license.getText().toString().trim();
                String distance2 = distance.getText().toString().trim();
                String start2 = start.getText().toString().trim();
                String email2 = email.getText().toString().trim();
                String end2 = end.getText().toString().trim();
                String movers2 = movers.getText().toString().trim();
                String boxes2 = boxes.getText().toString().trim();
                String car = cars.getText().toString().trim();
                String slo = sLocate.getText().toString().trim();
                String elo = eLocate.getText().toString().trim();
                String pT = pTime.getText().toString().trim();
                String rT = rTime.getText().toString().trim();
                int day = Integer.parseInt(pT.substring(0,pT.indexOf("/")));
                String next = pT.substring(pT.indexOf("/")+1);
                int month = Integer.parseInt(next.substring(0,next.indexOf("/")));
                next = next.substring(pT.indexOf("/")+1);
                int year = Integer.parseInt(next);
                calendar.set(year, month, day);
                pweekday = calendar.get(Calendar.DAY_OF_WEEK);

                day = Integer.parseInt(rT.substring(0,rT.indexOf("/")));
                next = rT.substring(rT.indexOf("/")+1);
                month = Integer.parseInt(next.substring(0,next.indexOf("/")));
                next = next.substring(rT.indexOf("/")+1);
                year = Integer.parseInt(next);
                calendar.set(year, month, day);
                rweekday = calendar.get(Calendar.DAY_OF_WEEK);
                String d[];


                Boolean required;
                Boolean empty;
                Boolean error = false;

                required = service.getFirstName();
                if(required) {
                    empty = firstName2.equals("");
                    if (empty) {
                        error = true;
                        firstName.requestFocus();
                        firstName.setError("FIELD CANNOT BE EMPTY");
                    }
                }

                required = service.getStart();
                if(required) {
                    empty = slo.equals("");
                    if (empty) {
                        error = true;
                        sLocate.requestFocus();
                        sLocate.setError("FIELD CANNOT BE EMPTY");
                    }
                }

                required = service.getpTime();
                if(required) {
                    empty = pT.equals("");

                    if (empty) {
                        error = true;
                        pTime.requestFocus();
                        pTime.setError("FIELD CANNOT BE EMPTY");
                    }else if(!pT.contains(":")){
                        error = true;
                        pTime.requestFocus();
                        pTime.setError("ENTER A VALID TIME");
                    }/*else if(pweekday==0){
                        d=srequest.getChosenBranch().getSunOpen().split(":");
                        if(Integer.parseInt(pT.split(":")[0])<Integer.parseInt(d[0])) {
                            error = true;
                            pTime.requestFocus();
                            pTime.setError("CHOSEN BRANCH IS NOT OPEN");
                        }else{
                            d=srequest.getChosenBranch().getSunClose().split(":");
                            if(Integer.parseInt(pT.split(":")[0])>Integer.parseInt(d[0])){
                                error = true;
                                pTime.requestFocus();
                                pTime.setError("CHOSEN BRANCH IS NOT OPEN");
                            }

                        }

                    }else if(pweekday==1){
                        d=srequest.getChosenBranch().getMonOpen().split(":");
                        if(Integer.parseInt(pT.split(":")[0])<Integer.parseInt(d[0])) {
                            error = true;
                            pTime.requestFocus();
                            pTime.setError("CHOSEN BRANCH IS NOT OPEN");
                        }else{
                            d=srequest.getChosenBranch().getMonClose().split(":");
                            if(Integer.parseInt(pT.split(":")[0])>Integer.parseInt(d[0])){
                                error = true;
                                pTime.requestFocus();
                                pTime.setError("CHOSEN BRANCH IS NOT OPEN");
                            }

                        }
                    }else if(pweekday==2){
                        d=srequest.getChosenBranch().getTuesOpen().split(":");
                        if(Integer.parseInt(pT.split(":")[0])<Integer.parseInt(d[0])) {
                            error = true;
                            pTime.requestFocus();
                            pTime.setError("CHOSEN BRANCH IS NOT OPEN");
                        }else{
                            d=srequest.getChosenBranch().getTuesClose().split(":");
                            if(Integer.parseInt(pT.split(":")[0])>Integer.parseInt(d[0])){
                                error = true;
                                pTime.requestFocus();
                                pTime.setError("CHOSEN BRANCH IS NOT OPEN");
                            }

                        }
                    }else if(pweekday==3){
                        d=srequest.getChosenBranch().getWedOpen().split(":");
                        if(Integer.parseInt(pT.split(":")[0])<Integer.parseInt(d[0])) {
                            error = true;
                            pTime.requestFocus();
                            pTime.setError("CHOSEN BRANCH IS NOT OPEN");
                        }else{
                            d=srequest.getChosenBranch().getWedClose().split(":");
                            if(Integer.parseInt(pT.split(":")[0])>Integer.parseInt(d[0])){
                                error = true;
                                pTime.requestFocus();
                                pTime.setError("CHOSEN BRANCH IS NOT OPEN");
                            }

                        }
                    }else if(pweekday==4){
                        d=srequest.getChosenBranch().getThursOpen().split(":");
                        if(Integer.parseInt(pT.split(":")[0])<Integer.parseInt(d[0])) {
                            error = true;
                            pTime.requestFocus();
                            pTime.setError("CHOSEN BRANCH IS NOT OPEN");
                        }else{
                            d=srequest.getChosenBranch().getThursClose().split(":");
                            if(Integer.parseInt(pT.split(":")[0])>Integer.parseInt(d[0])){
                                error = true;
                                pTime.requestFocus();
                                pTime.setError("CHOSEN BRANCH IS NOT OPEN");
                            }

                        }
                    }else if(pweekday==5){
                        d=srequest.getChosenBranch().getFriOpen().split(":");
                        if(Integer.parseInt(pT.split(":")[0])<Integer.parseInt(d[0])) {
                            error = true;
                            pTime.requestFocus();
                            pTime.setError("CHOSEN BRANCH IS NOT OPEN");
                        }else{
                            d=srequest.getChosenBranch().getFriClose().split(":");
                            if(Integer.parseInt(pT.split(":")[0])>Integer.parseInt(d[0])){
                                error = true;
                                pTime.requestFocus();
                                pTime.setError("CHOSEN BRANCH IS NOT OPEN");
                            }

                        }
                    }else if (pweekday==6){
                        d=srequest.getChosenBranch().getSatOpen().split(":");
                        if(Integer.parseInt(pT.split(":")[0])<Integer.parseInt(d[0])) {
                            error = true;
                            pTime.requestFocus();
                            pTime.setError("CHOSEN BRANCH IS NOT OPEN");
                        }else{
                            d=srequest.getChosenBranch().getSatClose().split(":");
                            if(Integer.parseInt(pT.split(":")[0])>Integer.parseInt(d[0])){
                                error = true;
                                pTime.requestFocus();
                                pTime.setError("CHOSEN BRANCH IS NOT OPEN");
                            }

                        }
                    }*/
                }

                required = service.getrTime();
                if(required) {
                    empty = rT.equals("");
                    if (empty) {
                        error = true;
                        rTime.requestFocus();
                        rTime.setError("FIELD CANNOT BE EMPTY");
                    }else if(!rT.contains(":")){
                        error = true;
                        rTime.requestFocus();
                        rTime.setError("ENTER A VALID TIME");
                    }/*else if(rweekday==0) {
                        d = srequest.getChosenBranch().getSunOpen().split(":");
                        if (Integer.parseInt(rT.split(":")[0]) < Integer.parseInt(d[0])) {
                            error = true;
                            rTime.requestFocus();
                            rTime.setError("CHOSEN BRANCH IS NOT OPEN");
                        } else {
                            d = srequest.getChosenBranch().getSunClose().split(":");
                            if (Integer.parseInt(rT.split(":")[0]) > Integer.parseInt(d[0])) {
                                error = true;
                                rTime.requestFocus();
                                rTime.setError("CHOSEN BRANCH IS NOT OPEN");
                            }

                        }
                    }else if(rweekday==1){
                        d=srequest.getChosenBranch().getMonOpen().split(":");
                        if(Integer.parseInt(rT.split(":")[0])<Integer.parseInt(d[0])) {
                            error = true;
                            rTime.requestFocus();
                            rTime.setError("CHOSEN BRANCH IS NOT OPEN");
                        }else{
                            d=srequest.getChosenBranch().getMonClose().split(":");
                            if(Integer.parseInt(rT.split(":")[0])>Integer.parseInt(d[0])){
                                error = true;
                                rTime.requestFocus();
                                rTime.setError("CHOSEN BRANCH IS NOT OPEN");
                            }

                        }
                    }else if(rweekday==2){
                        d=srequest.getChosenBranch().getTuesOpen().split(":");
                        if(Integer.parseInt(rT.split(":")[0])<Integer.parseInt(d[0])) {
                            error = true;
                            rTime.requestFocus();
                            rTime.setError("CHOSEN BRANCH IS NOT OPEN");
                        }else{
                            d=srequest.getChosenBranch().getTuesClose().split(":");
                            if(Integer.parseInt(rT.split(":")[0])>Integer.parseInt(d[0])){
                                error = true;
                                rTime.requestFocus();
                                rTime.setError("CHOSEN BRANCH IS NOT OPEN");
                            }

                        }
                    }else if(rweekday==3){
                        d=srequest.getChosenBranch().getWedOpen().split(":");
                        if(Integer.parseInt(rT.split(":")[0])<Integer.parseInt(d[0])) {
                            error = true;
                            rTime.requestFocus();
                            rTime.setError("CHOSEN BRANCH IS NOT OPEN");
                        }else{
                            d=srequest.getChosenBranch().getWedClose().split(":");
                            if(Integer.parseInt(rT.split(":")[0])>Integer.parseInt(d[0])){
                                error = true;
                                rTime.requestFocus();
                                rTime.setError("CHOSEN BRANCH IS NOT OPEN");
                            }

                        }
                    }else if(rweekday==4){
                        d=srequest.getChosenBranch().getThursOpen().split(":");
                        if(Integer.parseInt(rT.split(":")[0])<Integer.parseInt(d[0])) {
                            error = true;
                            rTime.requestFocus();
                            rTime.setError("CHOSEN BRANCH IS NOT OPEN");
                        }else{
                            d=srequest.getChosenBranch().getThursClose().split(":");
                            if(Integer.parseInt(rT.split(":")[0])>Integer.parseInt(d[0])){
                                error = true;
                                rTime.requestFocus();
                                rTime.setError("CHOSEN BRANCH IS NOT OPEN");
                            }

                        }
                    }else if(rweekday==5){
                        d=srequest.getChosenBranch().getFriOpen().split(":");
                        if(Integer.parseInt(rT.split(":")[0])<Integer.parseInt(d[0])) {
                            error = true;
                            rTime.requestFocus();
                            rTime.setError("CHOSEN BRANCH IS NOT OPEN");
                        }else{
                            d=srequest.getChosenBranch().getFriClose().split(":");
                            if(Integer.parseInt(rT.split(":")[0])>Integer.parseInt(d[0])){
                                error = true;
                                rTime.requestFocus();
                                rTime.setError("CHOSEN BRANCH IS NOT OPEN");
                            }

                        }
                    }else if (rweekday==6){
                        d=srequest.getChosenBranch().getSatOpen().split(":");
                        if(Integer.parseInt(rT.split(":")[0])<Integer.parseInt(d[0])) {
                            error = true;
                            rTime.requestFocus();
                            rTime.setError("CHOSEN BRANCH IS NOT OPEN");
                        }else{
                            d=srequest.getChosenBranch().getSatClose().split(":");
                            if(Integer.parseInt(rT.split(":")[0])>Integer.parseInt(d[0])){
                                error = true;
                                rTime.requestFocus();
                                rTime.setError("CHOSEN BRANCH IS NOT OPEN");
                            }

                        }
                    }*/
                }

                required = service.getEnd();
                if(required) {
                    empty = elo.equals("");
                    if (empty) {
                        error = true;
                        eLocate.requestFocus();
                        eLocate.setError("FIELD CANNOT BE EMPTY");
                    }
                }

                required = service.getCar();
                if(required) {
                    empty = car.equals("");
                    if (empty) {
                        error = true;
                        cars.requestFocus();
                        cars.setError("FIELD CANNOT BE EMPTY");
                    }
                }

                required = service.getLastName();
                if(required) {
                    empty = lastName2.equals("");
                    if (empty) {
                        error = true;
                        lastName.requestFocus();
                        lastName.setError("FIELD CANNOT BE EMPTY");

                    }
                }
                required = service.getDOB();
                if(required) {
                    empty = birthDate2.equals("");
                    if (empty) {
                        error = true;
                        birthDate.requestFocus();
                        birthDate.setError("FIELD CANNOT BE EMPTY");
                    }
                }
                required = service.getRAddress();
                if(required) {
                    empty = address2.equals("");
                    if (empty) {
                        error = true;
                        address.requestFocus();
                        address.setError("FIELD CANNOT BE EMPTY");
                    }
                }

                required = service.getLicense();
                if(required) {
                    empty = license2.equals("");
                    if (empty) {
                        error = true;
                        license.requestFocus();
                        license.setError("FIELD CANNOT BE EMPTY");
                    }
                }
                required = service.getMaxDistance();
                if(required) {
                    empty = distance2.equals("");
                    if (empty) {
                        error = true;
                        distance.requestFocus();
                        distance.setError("FIELD CANNOT BE EMPTY");
                    }
                }
                required = service.getPickup();
                if(required) {
                    empty = start2.equals("");
                    if (empty) {
                        error = true;
                        start.requestFocus();
                        start.setError("FIELD CANNOT BE EMPTY");
                    }
                }
                required = service.getEmailAddress();
                if(required) {
                    empty = email2.equals("");
                    if (empty) {
                        error = true;
                        email.requestFocus();
                        email.setError("FIELD CANNOT BE EMPTY");
                    } else if (!(email2.contains("@") && email2.contains("."))) {
                        error = true;
                        email.requestFocus();
                        email.setError("INVALID FORMAT");

                    }
                }
                required = service.getReturn();
                if(required) {
                    empty = end2.equals("");
                    if (empty) {
                        error = true;
                        end.requestFocus();
                        end.setError("FIELD CANNOT BE EMPTY");
                    }
                }
                required = service.getMovers();
                if(required) {
                    empty = movers2.equals("");
                    if (empty) {
                        error = true;
                        movers.requestFocus();
                        movers.setError("FIELD CANNOT BE EMPTY");
                    }
                }
                required = service.getBoxes();
                if(required) {
                    empty = boxes2.equals("");
                    if (empty) {
                        error = true;
                        boxes.requestFocus();
                        boxes.setError("FIELD CANNOT BE EMPTY");
                    }
                }
                if (!error) {

                    if(bundle.getString("CLASS").equals("customer"))
                        srequest.setaccountId(bundle.getString("ID"));

                    srequest.setAddress(address2);
                    srequest.setFirstName(firstName2);
                    srequest.setLastName(lastName2);
                    srequest.setDOB(birthDate2);
                    srequest.setLicense(license2);
                    srequest.setMaxDistance(distance2);
                    srequest.setStart(slo);
                    srequest.setEnd(elo);
                    srequest.setMovers(movers2);
                    srequest.setBoxes(boxes2);
                    srequest.setEmailAddress(email2);
                    srequest.setCar(car);
                    srequest.setPickup(start2);
                    srequest.setReturnDate(end2);
                    srequest.setPickupTime(pT);
                    srequest.setReturnTime(rT);


                    updateServiceRequest(srequest);
                    b.dismiss();
                }
            }



        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteServiceRequest(srequest.getServiceRequestId());
                b.dismiss();
            }
        });
    }

    private void updateServiceRequest(ServiceRequest srequest) {

        //getting the specified srequest reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("service_requests").child(srequest.getServiceRequestId());
        //updating srequest
        dR.setValue(srequest);

        Toast.makeText(getApplicationContext(),"Request Updated",Toast.LENGTH_LONG).show();

        if(bundle.getString("CLASS").equals("employee")){
            Intent intent = new Intent(ServiceRequestCreate.this, EmployeeSRView.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }else{
            Intent intent = new Intent(ServiceRequestCreate.this, customerAccount.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    private boolean deleteServiceRequest(String id) {
        //getting specific srequest reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("service_requests").child(id);
        //removing srequest
        dR.removeValue();
        Toast.makeText(getApplicationContext(), "Service Request Deleted", Toast.LENGTH_LONG).show();
        return true;
    }






}