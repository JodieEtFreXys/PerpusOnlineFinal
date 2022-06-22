package com.example.perpusonlinefinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.perpusonlinefinal.UserDatabase.UserDatabaseHelper;

public class LoginForm extends AppCompatActivity {

    private EditText etxLoginEmail, etxLoginPass;
    private TextView txvRegister;
    private Button btnLogin;

    UserDatabaseHelper userDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);

        initUi();
        initControl();
    }

    private void initUi(){

        etxLoginEmail = findViewById(R.id.etxLoginEmail);
        etxLoginPass = findViewById(R.id.etxLoginPass);
        btnLogin = findViewById(R.id.btnLogin);
        txvRegister = findViewById(R.id.txvRegister);

        userDatabaseHelper = new UserDatabaseHelper(LoginForm.this);
    }

    private void initControl(){

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean isValid;

                isValid = validateEmail();
                if(isValid){

                    toMainMenu();
                }else{
                    //Failed to login
                    toMainMenu();
                }
            }
        });

        txvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                toRegister();
            }
        });
    }

    private boolean validateEmail(){

        String email;
        String password;
        Cursor cursor;

        email = etxLoginEmail.getText().toString();
        password = etxLoginPass.getText().toString();

        cursor = userDatabaseHelper.validateEmail(email, password);
        if(cursor != null){
            // Missing shared preference
            return true;
        }else{

            return false;
        }
    }//

    private void toMainMenu(){

        Intent intent = new Intent(LoginForm.this, MainForm.class);
        startActivity(intent);
    }

    private void toRegister(){
//dsfdf
        Intent intent = new Intent(LoginForm.this, RegisterForm.class);
        startActivity(intent);
    }
}