package com.example.foodzy;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class uploadDetails extends AppCompatActivity {

    private Button submit;
    private RadioButton genderradioButton;
    private EditText name, lname, phone, mail, pass;
    private RadioGroup gender;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_details);


        name = findViewById(R.id.username); phone = findViewById(R.id.phone);
        mail = findViewById(R.id.mail); pass= findViewById(R.id.pass); gender = findViewById(R.id.gender);
        submit = findViewById(R.id.submit);

        //fire base


        // getting radio option

        int selectedId = gender.getCheckedRadioButtonId();
        genderradioButton = (RadioButton) findViewById(selectedId);

        final ProgressBar progressbar = findViewById(R.id.progressBarSubmitOtp);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail2 = mail.getText().toString();
                String pass2 = pass.getText().toString();
                String phone2 = phone.getText().toString();

                if(mail2.isEmpty()||pass2.isEmpty()||phone2.isEmpty()){
                    Toast.makeText(uploadDetails.this, "Please Enter All Details", Toast.LENGTH_SHORT).show();
                }else{
                    if(phone2.length() == 10){
                        progressbar.setVisibility(View.VISIBLE);
                        submit.setVisibility(View.INVISIBLE);
                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                "+91" + phone.getText().toString(),
                                60,
                                TimeUnit.SECONDS,
                                uploadDetails.this,
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                        progressbar.setVisibility(View.GONE);
                                        submit.setVisibility(View.VISIBLE);
                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {
                                        progressbar.setVisibility(View.GONE);
                                        submit.setVisibility(View.VISIBLE);
                                        Toast.makeText(uploadDetails.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String backendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                        progressbar.setVisibility(View.GONE);
                                        submit.setVisibility(View.VISIBLE);
                                        Intent intent = new Intent(uploadDetails.this, otpVerification.class);
                                        intent.putExtra("mobile", phone2);
                                        intent.putExtra("backendotp", backendotp);
                                        intent.putExtra("mail", mail.getText().toString());
                                        intent.putExtra("password", pass.getText().toString());
                                        startActivity(intent);
                                    }
                                }
                        );
                    }
                }
            }
        });
    }
//    private void regis(String mail2, String pass2){
//        auth.createUserWithEmailAndPassword(mail2, pass2).addOnCompleteListener(uploadDetails.this, new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if(task.isSuccessful()){
//                    Toast.makeText(uploadDetails.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(uploadDetails.this, "failed", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
}