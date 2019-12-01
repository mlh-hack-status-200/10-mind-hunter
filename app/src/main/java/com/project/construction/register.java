package com.project.construction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class register extends AppCompatActivity {
    EditText pass;
    EditText pass2;
    EditText name;
    private FirebaseAuth mAuth;
    EditText email;
    Button button;
    private ProgressDialog mdialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name=findViewById(R.id.name);
        pass=findViewById(R.id.pass);
        pass2=findViewById(R.id.pass2);

        email=findViewById(R.id.email);
        button=findViewById(R.id.register);
        mAuth = FirebaseAuth.getInstance();



        mdialog=new ProgressDialog(this);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mpass=pass.getText().toString().trim();
                String mpass2=pass2.getText().toString().trim();
                String memail=email.getText().toString().trim();
                String mname=name.getText().toString().trim();
                    int c1=0;int c2=0;int c3=0;int c4=0;
                if(pass.getText().toString().trim().equals(pass2.getText().toString().trim()))
                {
                }
                else
                    Toast.makeText(register.this, "Passwords do not match", Toast.LENGTH_SHORT).show();


                mdialog=new ProgressDialog(register.this);

                if (TextUtils.isEmpty(mpass)){
                    pass.setError("Required Field..");c1=1;
                }
                if (TextUtils.isEmpty(mpass2)){
                    pass2.setError("Required Field..");c2=1;
                }

                if (TextUtils.isEmpty(memail)){
                    email.setError("Required Field..");c3=1;
                }
                if (TextUtils.isEmpty(mname)){
                    name.setError("Required Field..");c4=1;
                }




                if(c1==0&&c2==0&&c3==0&&c4==0) {
                    mdialog.setMessage("PROcessing..");
                    mdialog.show();
                    mAuth.createUserWithEmailAndPassword(memail, mpass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        mdialog.dismiss();
                                        Intent intent = new Intent(register.this, choice.class);
                                        startActivity(intent);


                                    } else {
                                        // If sign in fails, display a message to the user.
                                        FirebaseAuthException e = (FirebaseAuthException) task.getException();
                                        Toast.makeText(register.this, "Failed Registration: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                        return;

                                    }

                                    // ...
                                }
                            });

                }


            }
        });



    }

}
