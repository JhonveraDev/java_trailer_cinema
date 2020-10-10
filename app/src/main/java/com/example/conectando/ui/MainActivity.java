package com.example.conectando.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.conectando.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private EditText mEditTextName;
    private EditText mEditTextEmail;
    private  EditText mEditTextPassword;
    private Button mButtonRegister;

    private String name="";
    private String email="";
    private String password="";

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth= FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mEditTextName= (EditText) findViewById(R.id.mEditTextName);
        mEditTextEmail=(EditText) findViewById(R.id.mEditTextEmail);
        mEditTextPassword=(EditText) findViewById(R.id.mEditTextPassword);
        mButtonRegister=(Button)findViewById(R.id.btnRegister);

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = mEditTextName.getText().toString();
                email= mEditTextEmail.getText().toString();
                password= mEditTextPassword.getText().toString();

                if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()){

                    if (password.length() >=6 ){

                        registerUser();

                    }else{
                        Toast.makeText(MainActivity.this,"La contraseña debe tener como minimo 6 Caracteres",Toast.LENGTH_SHORT).show();
                    }


                }
                else{
                    Toast.makeText(MainActivity.this,"Debe Completar Los Campos",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void registerUser(){
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    Map<String,Object> map = new HashMap<>();
                    map.put("name",name);
                    map.put("email",email);
                    map.put("password",password);

                    String id= mAuth.getCurrentUser().getUid();
                    mDatabase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if(task2.isSuccessful()){
                                startActivity(new Intent(MainActivity.this,HomeActivity.class));
                                finish();


                            }else {
                                Toast.makeText(MainActivity.this,"No se pudo crear datos",Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
                else{
                    Toast.makeText(MainActivity.this,"No se pudo registrar este Usuario",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
