package com.example.bibliosgroup7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import android.os.Bundle;

public class Admin1 extends AppCompatActivity {
    EditText editTextName;
    EditText editTextPrice;
    Button buttonAddService;
    ListView listViewServices;
    Button toAccounts;

    List<Service> services;
    DatabaseReference databaseServices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin1);

        editTextName = (EditText) findViewById(R.id.editTextMovers);
        editTextPrice = (EditText) findViewById(R.id.editTextBoxes);
        listViewServices = (ListView) findViewById(R.id.listViewServices);
        buttonAddService = (Button) findViewById(R.id.newRequestButton);
        toAccounts = findViewById(R.id.accountsButton);
        databaseServices = FirebaseDatabase.getInstance().getReference("services");

        services = new ArrayList<>();

        toAccounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin1.this, Admin.class);
                startActivity(intent);
            }
        });
        buttonAddService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addService();
            }
        });


        listViewServices.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Service service = services.get(i);
                if (service.getServiceId()!=null) {
                    showServiceUpdateDeleteDialog(service, service.getServiceId(), service.getServiceName(), service.getPrice());
                }else{
                    String id = databaseServices.push().getKey();
                    service.setServiceId(id);
                    showServiceUpdateDeleteDialog(service, id,service.getServiceName(),service.getPrice());
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
                    //adding a Service to the list
                    services.add(service);
                }
                //creating adapter
                ServiceList servicesAdapter = new ServiceList(Admin1.this, services);
                //attaching adapter to listview
                listViewServices.setAdapter(servicesAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void showServiceUpdateDeleteDialog(Service service, String serviceId, String serviceName, double servicePrice) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_dialog1, null);
        dialogBuilder.setView(dialogView);


        final EditText editTextName = (EditText) dialogView.findViewById(R.id.editTextMovers);
        final EditText editTextPrice  = (EditText) dialogView.findViewById(R.id.editTextBoxes);

        final Switch firstName = dialogView.findViewById(R.id.switchFirstName);
        final Switch lastName = dialogView.findViewById(R.id.switchLastName);
        final Switch email  = dialogView.findViewById(R.id.switchEmail);
        final Switch address = dialogView.findViewById(R.id.switchAddress);
        final Switch dob = dialogView.findViewById(R.id.switchDOB);
        final Switch car = dialogView.findViewById(R.id.switchCar);
        final Switch pickup = dialogView.findViewById(R.id.switchPickup);
        final Switch returnDate = dialogView.findViewById(R.id.switchReturn);
        final Switch license = dialogView.findViewById(R.id.switchLicense);
        final Switch distance = dialogView.findViewById(R.id.switchMaxDistance);
        final Switch start  = dialogView.findViewById(R.id.switchStartLocation);
        final Switch end = dialogView.findViewById(R.id.switchEndLocation);
        final Switch movers = dialogView.findViewById(R.id.switchMovers);
        final Switch boxes = dialogView.findViewById(R.id.switchBoxes);
        final Switch pTime = dialogView.findViewById(R.id.switchPickupTime);
        final Switch rTime = dialogView.findViewById(R.id.switchReturnTime);

        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdateService);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDeleteService);

        firstName.setChecked(service.getFirstName());
        lastName.setChecked(service.getLastName());
        email.setChecked(service.getEmailAddress());
        address.setChecked(service.getRAddress());
        dob.setChecked(service.getDOB());
        car.setChecked(service.getCar());
        pickup.setChecked(service.getPickup());
        returnDate.setChecked(service.getReturn());
        license.setChecked(service.getLicense());
        distance.setChecked(service.getMaxDistance());
        start.setChecked(service.getStart());
        end.setChecked(service.getEnd());
        movers.setChecked(service.getMovers());
        boxes.setChecked(service.getBoxes());
        pTime.setChecked(service.getpTime());
        rTime.setChecked(service.getrTime());

        dialogBuilder.setTitle(serviceName);
        final AlertDialog b = dialogBuilder.create();
        b.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString().trim();
                String price1 = editTextPrice.getText().toString();
                double price = 0;
                if (!price1.isEmpty()) {
                    price = Double.parseDouble(editTextPrice.getText().toString().trim());
                }
                Service service;

                DatabaseReference dR = FirebaseDatabase.getInstance().getReference("services").child(serviceId);
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(price1))
                    service = new Service (serviceId,name,price);
                else if(!TextUtils.isEmpty(name))
                    service = new Service(serviceId,name, servicePrice);
                else if(!TextUtils.isEmpty(price1))
                    service = new Service(serviceId,serviceName, price);
                else
                    service = new Service(serviceId, serviceName, servicePrice);
                service.setFirstName(firstName.isChecked());
                service.setLastName(lastName.isChecked());
                service.setEmailAddress(email.isChecked());
                service.setDOB(dob.isChecked());
                service.setRAddress(address.isChecked());
                service.setLicense(license.isChecked());
                service.setCar(car.isChecked());
                service.setPickup(pickup.isChecked());
                service.setReturn(returnDate.isChecked());
                service.setMaxDistance(distance.isChecked());
                service.setStart(start.isChecked());
                service.setEnd(end.isChecked());
                service.setMovers(movers.isChecked());
                service.setBoxes(boxes.isChecked());
                service.setpTime(pTime.isChecked());
                service.setrTime(rTime.isChecked());

                dR.setValue(service);


                b.dismiss();

                Toast.makeText(getApplicationContext(),"service Updated",Toast.LENGTH_LONG).show();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteService(serviceId);
                b.dismiss();
            }
        });
    }



    private boolean deleteService(String id) {
        //getting specific Service reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("services").child(id);
        //removing service
        dR.removeValue();
        Toast.makeText(getApplicationContext(), "Service Deleted", Toast.LENGTH_LONG).show();
        return true;
    }

    private void addService() {
        //getting the values to save
        String name = editTextName.getText().toString().trim();
        double price = Double.parseDouble(editTextPrice.getText().toString());

        //checking if the value is provided
        if (!TextUtils.isEmpty(name)){
            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our service
            String id = databaseServices.push().getKey();

            //creating a service Object
            Service service = new Service(id,name,price);

            //Saving the Service
            databaseServices.child(id).setValue(service);

            //setting edittext to blank again
            editTextName.setText("");
            editTextPrice.setText("");

            //displaying a success toast
            Toast.makeText(this,"Service added",Toast.LENGTH_LONG).show();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(this,"Please enter a name",Toast.LENGTH_LONG).show();
        }
    }
}