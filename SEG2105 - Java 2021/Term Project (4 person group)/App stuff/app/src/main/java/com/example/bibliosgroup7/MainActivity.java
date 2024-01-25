package com.example.bibliosgroup7;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    Button enter, create;
    DatabaseReference accounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        accounts = FirebaseDatabase.getInstance().getReference("accounts");
        setContentView(R.layout.activity_main);
        enter = findViewById(R.id.btnEnter);
        create = findViewById(R.id.btnCreate);

        EditText user = findViewById(R.id.textUsername);
        EditText pass = findViewById(R.id.editTextPassword);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = user.getText().toString();
                String password = pass.getText().toString();
                //if username and password exists together then:
                Intent intent = new Intent(MainActivity.this, Admin.class);
                intent.putExtra("USERNAME", username);
                intent.putExtra("PASSWORD", password);
                if (username.equals("admin") && password.equals("admin")) {
                    intent.putExtra("ROLE", "administrator");
                    startActivity(intent); //starts activity admin
                //}else if ()
                // else if (account type == customer)
                }else {
                    Boolean[] use = {false};
                    accounts.orderByChild("username").equalTo(username).addChildEventListener( new ChildEventListener(){

                        @Override
                        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                            if (snapshot.exists()) {
                                use[0] = true;
                                Account temp =snapshot.getValue(Account.class);
                                if ( temp.getPassword() != null)
                                    if (temp.getPassword().equals(password)){
                                        String role = temp.getRole();
                                        use[0] = true;
                                        if (role.equals("employee") || role.equals("Employee")) {
                                            Intent intent1 = new Intent(MainActivity.this, Employee.class);
                                            Boolean x = false;
                                            intent1.putExtra("NEW",x);
                                            intent1.putExtra("USERNAME", username);
                                            intent1.putExtra("PASSWORD", password);
                                            intent1.putExtra("eID", temp.getAccountId());
                                            intent1.putExtra("ROLE", role);
                                            startActivity(intent1);
                                        }else if(role.equals("customer") || role.equals("Customer")){
                                            Intent intent2 = new Intent(MainActivity.this, customerAccount.class);
                                            intent2.putExtra("USERNAME", username);
                                            intent2.putExtra("PASSWORD", password);
                                            intent2.putExtra("ID", temp.getAccountId());
                                            intent2.putExtra("ROLE", role);
                                            startActivity(intent2);
                                        }
                                    }
                            }
                        }


                        @Override
                        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        }

                        @Override
                        public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                        }

                        @Override
                        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }




            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateAccount.class);
                startActivity(intent);
            }
        });


    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }



    //private void updateUI(FirebaseUser currentUser){
    //}
}