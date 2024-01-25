package com.example.bibliosgroup7;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import android.os.Bundle;

public class EmployeeSRView extends AppCompatActivity {
    Button buttonAddServiceRequest;
    ListView listViewServiceRequests;
    Button returns;
    ArrayList<Service> s;
    ListView listViewServices;
    Account account;
    DatabaseReference dR;
    Bundle bundle;

    ArrayList<ServiceRequest> srequests;
    ArrayList<Account> accounts;

    DatabaseReference databaseServiceRequests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_srview);
         bundle = getIntent().getExtras();

        listViewServiceRequests = (ListView) findViewById(R.id.listViewServiceRequests);
        listViewServices = (ListView) findViewById(R.id.listViewServices);
        buttonAddServiceRequest = (Button) findViewById(R.id.newRequestButton);
        returns = (Button) findViewById((R.id.ButtonReturn));
        
        databaseServiceRequests = FirebaseDatabase.getInstance().getReference("service_requests");


        accounts = new ArrayList<>();
        srequests = new ArrayList<>();


        dR = FirebaseDatabase.getInstance().getReference("accounts");

        dR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot userSnapshot: snapshot.getChildren()) {

                    if (userSnapshot.getValue(Account.class).getAccountId().equals(bundle.getString("eID")) ){
                        account = userSnapshot.getValue(Account.class);
                        s= account.getActive();
                        break;

                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("absolute mayhem", "this thing is cancelled omg");
            }
        });


        String SRid = dR.push().getKey();

        DatabaseReference SR = FirebaseDatabase.getInstance().getReference("accounts").child(SRid);
        //removing account
        SR.removeValue();

        if(account == null){
            s = new ArrayList<>();
            Log.d("msg1", "acc is null");
        }




        returns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmployeeSRView.this, Employee.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        buttonAddServiceRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addServiceRequest();
            }
        });


        listViewServiceRequests.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                ServiceRequest srequest = srequests.get(i);
                for (int j=0;j<accounts.size();j++) {
                    if (accounts.get(j).getAccountId().equals(account.getAccountId())) {
                        account = accounts.get(j);
                    }
                }
                if (srequest.getServiceRequestId()!=null) {
                    showServiceRequestUpdateDeleteDialog(srequest);
                } else {
                    String id = databaseServiceRequests.push().getKey();
                    srequest.setServiceRequestId(id);
                    showServiceRequestUpdateDeleteDialog(srequest);
                }
                return true;
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        databaseServiceRequests.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //clearing the previous account list
                srequests.clear();
                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting account
                    ServiceRequest sr = postSnapshot.getValue(ServiceRequest.class);
                    //adding a account to the list
                    if (sr.getChosenBranch().equals(account))
                        srequests.add(sr);


                }
                //creating adapter

                ServiceRequestList serviceAdapter = new ServiceRequestList(EmployeeSRView.this, srequests);
                //attaching adapter to listview
                listViewServiceRequests.setAdapter(serviceAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        dR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Account account = postSnapshot.getValue(Account.class);
                    if(account!=null)
                        accounts.add(account);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }








    private void showServiceRequestUpdateDeleteDialog(ServiceRequest s) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.erequest_info, null);
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

        final Button a = dialogView.findViewById(R.id.btnApprove2);
        final Button d = dialogView.findViewById(R.id.btnDeny2);

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s.setApproved(true);
                updateRequest(s);
                cd.dismiss();
            }
        });

        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteRequest(s);
                cd.dismiss();
            }
        });


    }

    private void deleteRequest(ServiceRequest s){
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("service_requests").child(s.getServiceRequestId());
        //removing account
        dR.removeValue();
        Toast.makeText(getApplicationContext(), "Request Denied", Toast.LENGTH_LONG).show();
    }

    private void updateRequest(ServiceRequest s){
        s.setApproved(true);
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("service_requests").child(s.getServiceRequestId());
        //updating account
        dR.setValue(s);

        Toast.makeText(getApplicationContext(),"Request Approved",Toast.LENGTH_LONG).show();
    }

    private void addServiceRequest() {
        Intent intent = new Intent(EmployeeSRView.this, ServiceRequestCreate.class);
        intent.putExtras(bundle);
        intent.putExtra("CLASS", "employee");
        startActivity(intent);

    }

}
