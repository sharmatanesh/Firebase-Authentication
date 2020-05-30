package com.example.firebaseauthenticate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonSignin;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignup;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() != null)
        {
            finish();
            startActivity(new Intent(getApplicationContext(),Profile.class));

        }

        editTextEmail=(EditText) findViewById(R.id.editTextEmail);
        editTextPassword=(EditText) findViewById(R.id.editTextPassword);
        buttonSignin=(Button) findViewById(R.id.buttonSignin);
        textViewSignup=(TextView) findViewById(R.id.textViewSignup);
        buttonSignin.setOnClickListener(this);
        textViewSignup.setOnClickListener(this);
        progressDialog=new ProgressDialog(this);


    }
    private void userLogin()
    {
        String email=editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();
        if(TextUtils.isEmpty(email))
        {
            //email is empty

            Toast.makeText(this, "Please Enter email", Toast.LENGTH_SHORT).show();
            //stop the function for executing further
            return;
        }
        if(TextUtils.isEmpty(password))
        {
            //password is empty
            Toast.makeText(this,"Please Enter Password",Toast.LENGTH_SHORT).show();
            //stop the function for executing further

            return;
        }

        progressDialog.setMessage("Registration in Process");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                progressDialog.dismiss();

                                if(task.isSuccessful())
                                {
                                    //start the profile activity
                                    finish();
                                    startActivity(new Intent(getApplicationContext(),Profile.class));


                                }

                            }
                        }

                );





    }
    @Override
    public void onClick(View v) {
        if(v==buttonSignin)
        {
            userLogin();
        }
        if(v==textViewSignup)
        {
            finish();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));

        }





    }
}
