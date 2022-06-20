package com.example.perpusonlinefinal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.perpusonlinefinal.UserDatabase.UserDatabaseHelper;

import java.util.Calendar;
import java.util.Date;

public class RegisterForm extends AppCompatActivity {

    private EditText etxRegEmail, etxRegPass, etxRegPhone;
    private TextView txvDatePick;
    private CheckBox cbxTerm;
    private Button btnRegister;

    UserDatabaseHelper userDatabaseHelper;

    private int age, selectedYear, selectedMonth, selectedDay;
    private DatePickerDialog datePickerDialog;
    private Date dateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);

        initDate();
        initUi();
        initControl();
    }

    private void initDate(){

        Calendar calendar = Calendar.getInstance();
        selectedYear = calendar.get(Calendar.YEAR);
        selectedMonth = calendar.get(Calendar.MONTH);
        selectedDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                selectedYear = i;
                selectedMonth = i1;
                selectedDay = i2;

                txvDatePick.setText(selectedDay + "/" + (selectedMonth+1) + "/" + selectedYear);
            }
        };

        datePickerDialog = new DatePickerDialog(this, dateSetListener, selectedYear, selectedMonth, selectedDay);
    }

    private void initUi(){

        etxRegEmail = findViewById(R.id.etxRegEmail);
        etxRegPass = findViewById(R.id.etxRegPass);
        etxRegPhone = findViewById(R.id.etxRegPhone);
        txvDatePick = findViewById(R.id.txvDatePick);
        cbxTerm = findViewById(R.id.cbxTerm);
        btnRegister = findViewById(R.id.btnRegister);

        userDatabaseHelper = new UserDatabaseHelper(RegisterForm.this);
    }

    private void initControl(){

        txvDatePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                datePickerDialog.show();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                insertData();
            }
        });
    }

    private void insertData(){

        String email = etxRegEmail.getText().toString();
        String password = etxRegPass.getText().toString();
        String phoneNumber = etxRegPhone.getText().toString();
        age = getAge();

        String errorMessage = validateRegistration( email, password, phoneNumber);

        if(errorMessage.isEmpty()){

            String dob = txvDatePick.getText().toString();
            userDatabaseHelper.addData(email, password, phoneNumber, dob);
            toLogin();
        }else{

            Toast.makeText(RegisterForm.this, errorMessage, Toast.LENGTH_SHORT).show();
        }
    }

    private int getAge(){

        Calendar calendar = Calendar.getInstance();

        return calendar.get(Calendar.YEAR) - selectedYear;
    }

    private String validateRegistration(String email,  String password, String phoneNumber){

        String passPattern = "(?=^.*[a-zA-Z])(?=.*[0-9])(.+)$";
        String phonePattern = "(^[+][6][2])([0-9]+)$";

        if(email.isEmpty()){

            return "Email must be filled";
        }else if(password.isEmpty()){

            return "Password must be filled";
        }else if(password.length() < 8){

            return "Password must be 8 characters or more";
        }else if(!password.matches(passPattern)){

            return "Password must have at least 1 letter and 1 digit";
        }else if(phoneNumber.isEmpty()){

            return "Phone Number must be filled";
        }else if(!phoneNumber.matches(phonePattern)){

            return "Phone Number must start with +62";
        }else if(phoneNumber.length() > 16 || phoneNumber.length() < 11){

            return "Phone Number must be between 10 to 15 digits";
        }else if(age < 13){

            return "You must be 13 years old or above";
        }else if(!cbxTerm.isChecked()){

            return "You must agree to term and condition";
        }else{

            return "";
        }
    }

    private void toLogin(){

        Intent intent = new Intent(RegisterForm.this, LoginForm.class);
        startActivity(intent);
    }
}