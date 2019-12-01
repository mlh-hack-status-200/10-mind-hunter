package com.project.construction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class startapp extends AppCompatActivity {


    private FirebaseAuth mAuth;
    String value;

    EditText pass;
    EditText email;
    private ProgressDialog mdialog;
    DatabaseReference rootRef,demoRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startapp);
        mAuth = FirebaseAuth.getInstance();
        pass=findViewById(R.id.pass);
        email=findViewById(R.id.email);
        ImageView button=findViewById(R.id.register);


        mdialog=new ProgressDialog(this);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(startapp.this,register.class);
                startActivity(intent);

            }
        });

        Button button22=findViewById(R.id.login);

        button22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cou=0;int co2=0;
                String memail=email.getText().toString().trim();
                String mpass=pass.getText().toString().trim();
                if (TextUtils.isEmpty(mpass)){
                    email.setError("Required Field..");cou=1;
                }
                if (TextUtils.isEmpty(memail)){
                    pass.setError("Required Field..");co2=1;
                }
                if(co2==0&&cou==0) {
                    mdialog.setMessage("PROcessing..");
                    mdialog.show();
                    mAuth.signInWithEmailAndPassword(memail, mpass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        mdialog.dismiss();

                                        // Sign in success, update UI with the signed-in user's information

                                        /*FirebaseUser user = mAuth.getCurrentUser();
                                        FirebaseDatabase database = FirebaseDatabase.getInstance();

                                        DatabaseReference myRef = database.getReference("message");

                                        myRef.child("value").addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                 value = dataSnapshot.getValue(String.class);
                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {
                                            }
                                        });


*/
                                              //  if (value.equals("1")) {
                                                    Intent intent = new Intent(startapp.this, sell.class);
                                                    startActivity(intent);
                                               // }

                                               // if (value.equals("2")) {
                                               //     Intent intent = new Intent(startapp.this, sell.class);
                                               //     startActivity(intent);
                                              //  }






                                    } else {
                                        // If sign in fails, display a message to the user.
                                        FirebaseAuthException e = (FirebaseAuthException) task.getException();
                                        Toast.makeText(startapp.this, "Failed Registration: " + e.getMessage(), Toast.LENGTH_SHORT).show();

                                    }

                                }
                            });
                }
            }
        });



    }
}
