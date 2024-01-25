package com.example.bibliosgroup7;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;


public class CreateAccount extends AppCompatActivity {
    EditText txtUsername, txtRole, txtPassword;
    Button btnRegister;
    DatabaseReference dreference;
    Account account;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);



        Intent intent1 = new Intent(CreateAccount.this, customerAccount.class);
        Intent intent2 = new Intent(CreateAccount.this, Employee.class);


        txtUsername = (EditText) findViewById(R.id.textUsername);
        txtRole = (EditText) findViewById(R.id.textRole);
        txtPassword = (EditText) findViewById(R.id.textPassword);
        btnRegister = (Button) findViewById(R.id.btnRegister);


        //account = new Account();
        dreference = FirebaseDatabase.getInstance().getReference("accounts");



        btnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String username = txtUsername.getText().toString().trim();
                    String password = txtPassword.getText().toString().trim();
                    String role = txtRole.getText().toString().trim();
                   // String emailAddress = txtEmailAddress.getText().toString().trim();
                    //edge cases username
                    //if (username exists in storage)
                    // invalid
                    Boolean error = false;

                    Boolean empty = username.equals("");
                    if (empty) {
                        error = true;
                        txtUsername.requestFocus();
                        txtUsername.setError("FIELD CANNOT BE EMPTY");
                    }else if(username.equals("admin")){
                        error = true;
                        txtUsername.requestFocus();
                        txtUsername.setError("USERNAME IS TAKEN");
                    }

                    //edge cases email
                    /*empty = emailAddress.equals("");
                    if (empty) {
                        error = true;
                        txtEmailAddress.requestFocus();
                        txtEmailAddress.setError("FIELD CANNOT BE EMPTY");

                    }else if (!(emailAddress.contains("@") && emailAddress.contains("."))){
                        error = true;
                        txtEmailAddress.requestFocus();
                        txtEmailAddress.setError("INVALID FORMAT");

                    }*/

                    //role edge cases
                    empty = role.equals("");
                    if (empty) {
                        error = true;
                        txtRole.requestFocus();
                        txtRole.setError("FIELD CANNOT BE EMPTY");

                    }else if(!role.equals("customer") &&!role.equals("Customer") && !role.equals("employee") && !role.equals("Employee")){
                        error = true;
                        txtRole.requestFocus();
                        txtRole.setError("INVALID INPUT");

                    }

                    //password edge cases
                    empty = password.equals("");
                    if (empty) {
                        error = true;
                        txtPassword.requestFocus();
                        txtPassword.setError("FIELD CANNOT BE EMPTY");
                    }
                    if (!error){
                        String id = dreference.push().getKey();
                        account = new Account(id, username, role, password);

                        dreference.child(id).setValue(account);
                        Boolean x = false;
                        if (role.equals("Employee")||role.equals("employee")) {
                            x = true;
                            intent2.putExtra("NEW", x);

                            intent2.putExtra("ID", id);
                            intent2.putExtra("USERNAME", username);
                            intent2.putExtra("PASSWORD", password);
                            intent2.putExtra("ROLE", role);
                            startActivity(intent2);
                        }else {
                            intent1.putExtra("ID", id);
                            intent1.putExtra("USERNAME", username);
                            intent1.putExtra("PASSWORD", password);
                            intent1.putExtra("ROLE", role);
                            startActivity(intent1);
                        }
                        //Toast.makeText(datainsert.this, "account created!", Toast.LENGTH_LONG).show();
                    }
                }


            });

        }

}