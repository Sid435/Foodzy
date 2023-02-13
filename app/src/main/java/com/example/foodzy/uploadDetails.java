package com.example.foodzy;
import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

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

    private AppCompatButton submit;
    private RadioButton genderradioButton;
    private EditText phone, mail, pass, name_ed;
    private RadioGroup gender;
    String phone_no, name;
    Boolean forgotpass;
    TextView sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_details);
        if (checkSelfPermission(Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);
        }

        DBHandler dbHandler = new DBHandler(this);

        phone = findViewById(R.id.phone);
        mail = findViewById(R.id.mail);
        pass = findViewById(R.id.pass);
        name_ed = findViewById(R.id.Name);
        gender = findViewById(R.id.gender);
        submit = findViewById(R.id.submit);
        sp = findViewById(R.id.starPhone);

        int selectedId = gender.getCheckedRadioButtonId();
        genderradioButton = (RadioButton) findViewById(selectedId);

//        final ProgressBar progressbar = findViewById(R.id.progressBarSubmitOtp);
        Intent intent = getIntent();
        forgotpass = intent.getBooleanExtra("forgot password", false);
        String mail_id = intent.getStringExtra("mail");
        sp.setVisibility(View.INVISIBLE);


        if (forgotpass) {
            sp.setVisibility(View.VISIBLE);
            phone.setVisibility(View.INVISIBLE);
            gender.setVisibility(View.INVISIBLE);
            Cursor t1 = dbHandler.get_name(mail_id);
            while (t1.moveToNext()) {
                name = t1.getString(0);
            }
            Cursor t2 = dbHandler.get_phone(mail_id);
            while (t2.moveToNext()) {
                phone_no = t2.getString(0);
            }
            mail.setText(mail_id);
            String vc = "******";
            for (int i = 6; i < 10; i++) {
                vc = vc + phone_no.charAt(i);
            }
//            System.out.println(vc);
//            phone.setText(vc);
            name_ed.setText(name);
            sp.setText(vc);
            pass.setHint("Enter new password");
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (pass.getText().toString().trim().length() == 0) {
                        pass.setError("Enter new password");
                    } else {
                        String new_pass = pass.getText().toString();
                        Intent intent = new Intent(uploadDetails.this, otpVerification.class);
                        intent.putExtra("forgot password", true);
                        intent.putExtra("mail", mail_id);
                        intent.putExtra("name", name);
                        intent.putExtra("phone_no", phone_no);
                        intent.putExtra("new password", new_pass);
                        startActivity(intent);
                    }
                }
            });
        }


        else{
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (mail.getText().toString().trim().length() == 0 || !mail.getText().toString().contains("@gmail.com")) {
                        mail.setError("Enter '@gmail.com' mail id");
                    } else if (name_ed.getText().toString().trim().length() == 0) {
                        name_ed.setError("Enter user detail");
                    } else if (phone.getText().toString().trim().length() == 0) {
                        phone.setError("Enter user detail");
                    } else if (pass.getText().toString().trim().length() == 0) {
                        pass.setError("Enter user detail");
                    } else {
                        String mail2 = mail.getText().toString();
                        String name2 = name_ed.getText().toString();
                        String pass2 = pass.getText().toString();
                        String phone2 = phone.getText().toString();

                        Boolean checkuser = dbHandler.check_user(mail2);
                        if (!checkuser) {
                            Intent intent = new Intent(uploadDetails.this, otpVerification.class);
                            intent.putExtra("mail", mail2);
                            intent.putExtra("name", name2);
                            intent.putExtra("password", pass2);
                            intent.putExtra("phone_no", phone2);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(uploadDetails.this, "USER ALREADY EXISTS", Toast.LENGTH_SHORT).show();
                            Toast.makeText(uploadDetails.this, "TRY SIGN IN", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }
    }
}