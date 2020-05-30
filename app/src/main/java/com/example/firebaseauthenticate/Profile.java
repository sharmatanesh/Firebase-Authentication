package com.example.firebaseauthenticate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;
    private TextView textViewUserEmail;
    private Button buttonLogout;
    private EditText editName;
    private EditText editNumber;
    private EditText editAdd;
    private Button buttonSubmit;
    private FirebaseDatabase rootNode;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() == null)
        {
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }

        databaseReference = FirebaseDatabase.getInstance().getReference();


        FirebaseUser user = firebaseAuth.getCurrentUser();
        textViewUserEmail=(TextView) findViewById(R.id.textViewUserEmail);
        buttonLogout=(Button) findViewById(R.id.buttonLogout);
        textViewUserEmail.setText("Welcome "+user.getEmail());
        editName=(EditText) findViewById(R.id.editName);
        editNumber=(EditText) findViewById(R.id.editNumber);
        editAdd=(EditText) findViewById(R.id.editAdd);
        buttonSubmit=(Button) findViewById(R.id.buttonSubmit);
        buttonLogout.setOnClickListener(this);
        buttonSubmit.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if(v==buttonLogout)
        {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        }
        if (v==buttonSubmit)
        {
            rootNode=FirebaseDatabase.getInstance();
            databaseReference=rootNode.getReference("users");

            // Get all the values
            String Name=editName.getText().toString();
            String Number=editNumber.getText().toString();
            String Address=editAdd.getText().toString();


            UserHelperClass helperClass = new UserHelperClass(Name,Number,Address);

            databaseReference.child(Number).setValue(helperClass);




        }


    }
}
