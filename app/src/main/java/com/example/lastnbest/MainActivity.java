package com.example.lastnbest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private TextView forpass,register;
    private Button btnsignin;
    private EditText etmail,etpass;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        forpass=findViewById(R.id.textView);
        register=findViewById(R.id.textView2);
        btnsignin=findViewById(R.id.button);
        etmail=findViewById(R.id.editTextTextEmailAddress);
        etpass=findViewById(R.id.editTextTextPassword);
        mAuth=FirebaseAuth.getInstance();
        forpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ForgetPass.class));
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Registration.class));
            }
        });

        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userlogin();
            }
        });


    }

    private void userlogin() {
        final String email=etmail.getText().toString().trim();
        String pass=etpass.getText().toString().trim();
        if(email.isEmpty())
        {
            etmail.setError("Please enter email");
            etmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            etmail.setError("Please enter valid email");
            etmail.requestFocus();
            return;
        }
        if(pass.isEmpty()) {
            etpass.setError("Please enter password");
            etpass.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    if(email.equals("ourp272207@gmail.com"))
                        startActivity(new Intent(MainActivity.this,Owner.class));
                    else
                        startActivity(new Intent(MainActivity.this,Customer.class));
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Failed to login! Please, check your login credentials",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}