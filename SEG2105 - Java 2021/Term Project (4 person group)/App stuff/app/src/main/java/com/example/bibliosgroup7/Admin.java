package com.example.bibliosgroup7;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Admin extends AppCompatActivity {
    EditText editTextName;
    EditText editTextRole;
    EditText editTextpassword;
    Button buttonAddAccount;
    ListView listViewAccounts;
    Button toServices;
    
    List<Account> accounts;
    DatabaseReference databaseAccounts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Toast.makeText(getApplicationContext(), "Welcome Administrator!", Toast.LENGTH_LONG).show();

        editTextName = (EditText) findViewById(R.id.editTextRating);
        editTextpassword = (EditText) findViewById(R.id.editTextpassword);
        editTextRole = (EditText) findViewById(R.id.editTextRole);

        listViewAccounts = (ListView) findViewById(R.id.listViewServiceRequests);
        buttonAddAccount = (Button) findViewById(R.id.newRequestButton);
        toServices = (Button) findViewById((R.id.ButtonReturn));

        databaseAccounts = FirebaseDatabase.getInstance().getReference("accounts");

        accounts = new ArrayList<>();

        toServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Admin1.class);
                startActivity(intent);
            }
        });
        buttonAddAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addAccount();
            }
        });


        listViewAccounts.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Account account = accounts.get(i);
                if (account.getAccountId()!=null) {
                    showAccountUpdateDeleteDialog(account.getAccountId(), account.getUsername(), account.getRole(), account.getPassword());
                } else {
                    String id = databaseAccounts.push().getKey();
                    account.setAccountId(id);
                    showAccountUpdateDeleteDialog(id,account.getUsername(),account.getRole(),account.getPassword());
                }
                return true;
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
                accounts.clear();
                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting account
                    Account account = postSnapshot.getValue(Account.class);
                    //adding a account to the list
                    accounts.add(account);
                }
                //creating adapter
                AccountList accountsAdapter = new AccountList(Admin.this, accounts);
                //attaching adapter to listview
                listViewAccounts.setAdapter(accountsAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void showAccountUpdateDeleteDialog(String accountId, String accountName, String accountType, String accountPassword) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_dialog, null);
        dialogBuilder.setView(dialogView);



        final EditText editTextpassword = (EditText) dialogView.findViewById(R.id.editTextPhoneNumber);
        final EditText editTextName = (EditText) dialogView.findViewById(R.id.editTextRating);
        final EditText editTextRole  = (EditText) dialogView.findViewById(R.id.editTextRole);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonSave);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDeleteAccount);


        dialogBuilder.setTitle(accountName);
        final AlertDialog b = dialogBuilder.create();
        b.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean error;

                String name = editTextName.getText().toString().trim();
                String type = editTextRole.getText().toString().trim();
                String password = editTextpassword.getText().toString().trim();

                if (!type.equals("customer") &&!type.equals("Customer") && !type.equals("employee") && !type.equals("Employee") && !type.isEmpty()) {
                    error = true;
                    editTextRole.requestFocus();
                    editTextRole.setError("INVALID INPUT");
                } else {
                    if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(editTextRole.getText().toString())) {
                        updateAccount(accountId, name, type, accountPassword);
                        b.dismiss();
                    } else if (!TextUtils.isEmpty(name)) {
                        updateAccount(accountId, name, accountType, accountPassword);
                        b.dismiss();
                    } else if (!TextUtils.isEmpty((type))) {
                        updateAccount(accountId, accountName, type, accountPassword);
                        b.dismiss();
                    } else if (!TextUtils.isEmpty((password))) {
                        updateAccount(accountId, accountName, accountType, password);
                        b.dismiss();
                    } else if (!TextUtils.isEmpty(type) && !TextUtils.isEmpty(password)) {
                        updateAccount(accountId, accountName, type, password);
                        b.dismiss();
                    } else if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)) {
                        updateAccount(accountId, name, accountType, password);
                        b.dismiss();
                    } else {
                        updateAccount(accountId, name, type, password);
                        b.dismiss();
                    }
                }

            }


        });


        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAccount(accountId);
                b.dismiss();
            }
        });
    }



    private void updateAccount(String id, String name, String type, String password) {

        //getting the specified account reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("accounts").child(id);
        //updating account
        Account account = new Account (id,name,type, password);
        dR.setValue(account);

        Toast.makeText(getApplicationContext(),"account Updated",Toast.LENGTH_LONG).show();
    }

    private boolean deleteAccount(String id) {
        //getting specific account reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("accounts").child(id);
        //removing account
        dR.removeValue();
        Toast.makeText(getApplicationContext(), "Account Deleted", Toast.LENGTH_LONG).show();
        return true;
    }

    private void addAccount() {
        //getting the values to save
        String name = editTextName.getText().toString().trim();
        String type = editTextRole.getText().toString().trim();
        String password = editTextpassword.getText().toString().trim();
        boolean error = false;

        if(!type.equals("customer") &&!type.equals("Customer") && !type.equals("employee") && !type.equals("Employee")){
            error = true;
            editTextRole.requestFocus();
            editTextRole.setError("INVALID INPUT");
        } if (type.isEmpty()){
            error = true;
            editTextRole.requestFocus();
            editTextRole.setError("INVALID INPUT");
        } if(name.isEmpty()) {
            error = true;
            editTextName.requestFocus();
            editTextName.setError("INVALID INPUT");
        } if(password.isEmpty()) {
            error = true;
            editTextpassword.requestFocus();
            editTextpassword.setError("INVALID INPUT");
        } if (error == false){
                //checking if the value is provided
                if (!TextUtils.isEmpty(name)){
                    //getting a unique id using push().getKey() method
                    //it will create a unique id and we will use it as the Primary Key for our account
                    String id = databaseAccounts.push().getKey();

                    //creating a account Object
                    Account account = new Account(id,name,type, password);

                    //Saving the account
                    databaseAccounts.child(id).setValue(account);

                    //setting edittext to blank again
                    editTextName.setText("");
                    editTextRole.setText("");
                    editTextpassword.setText("");

                    //displaying a success toast
                    Toast.makeText(this,"Account added",Toast.LENGTH_LONG).show();
                } else {
                    //if the value is not given displaying a toast
                    Toast.makeText(this,"Please enter a name",Toast.LENGTH_LONG).show();
                }



            }
        }

    }
