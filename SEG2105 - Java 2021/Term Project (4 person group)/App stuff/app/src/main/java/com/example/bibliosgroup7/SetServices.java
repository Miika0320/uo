package com.example.bibliosgroup7;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SetServices extends AppCompatActivity {
    ListView listViewService;
    Button btnCancel;
    DatabaseReference databaseServices;
    ArrayList<Service> services;
    DatabaseReference databaseAccounts;
    Bundle bundle;
    Account account;
    ArrayList<Account> accounts;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_choice);
        bundle = getIntent().getExtras();
        account = new Account(bundle.getString("ID"), bundle.getString("USERNAME"), bundle.getString("ROLE"), bundle.getString("PASSWORD"));

        listViewService = (ListView) findViewById(R.id.serviceList);
        btnCancel = (Button) findViewById((R.id.buttonCancel));

        databaseServices = FirebaseDatabase.getInstance().getReference("services");
        databaseAccounts = FirebaseDatabase.getInstance().getReference("accounts");

        services = new ArrayList<>();
        accounts = new ArrayList<>();

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Employee.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        

        listViewService.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Service service = services.get(i);
                if (service.getServiceId()!=null) {
                    showServiceUpdateDeleteDialog(service);
                } else {
                    String id = databaseServices.push().getKey();
                    service.setServiceId(id);
                    showServiceUpdateDeleteDialog(service);
                }
                return true;
            }
        });


    }
    @Override
    protected void onStart() {
        super.onStart();

        databaseServices.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //clearing the previous service list
                services.clear();
                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting service
                    Service service = postSnapshot.getValue(Service.class);
                    //adding a service to the list
                    services.add(service);
                }
                //creating adapter
                ServiceList servicesAdapter = new ServiceList(SetServices.this, services);
                //attaching adapter to listview
                listViewService.setAdapter(servicesAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseAccounts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    if (postSnapshot.getValue(Account.class).getAccountId().equals(bundle.getString("ID")) ){
                        account = postSnapshot.getValue(Account.class);
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void showServiceUpdateDeleteDialog(Service s) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.set_service, null);
        dialogBuilder.setView(dialogView);

        dialogBuilder.setTitle("Set Service");
        final AlertDialog b = dialogBuilder.create();
        b.show();



        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.btnAdd);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.btnRemove);




        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!account.getActive().contains(s)){
                    account.addActive(s);
                    updateAccount();
                }
                b.dismiss();
            }


        });


        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if(account.getActive().contains(s)){
                    account.delActive(s);
                    updateAccount();
                //}
                b.dismiss();
            }
        });
    }



    private void updateAccount() {

        //getting the specified service reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("accounts").child(account.getAccountId());
        //updating service

        dR.setValue(account);

        Toast.makeText(getApplicationContext(),"Account Updated",Toast.LENGTH_LONG).show();
    }






}