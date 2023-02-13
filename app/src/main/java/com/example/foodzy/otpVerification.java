package com.example.foodzy;
//import android.support.v7.app.AppCompatActivity;
import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class otpVerification extends AppCompatActivity {
    private TextView textView;
    private EditText one, two, three, four, five, six;
    private AppCompatButton verify;
    private AppCompatButton resendOtp1;
    int otp_code;
    String mobile_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);

        DBHandler dbHandler = new DBHandler(this);

        Intent intent = getIntent();
        String mail3 = intent.getStringExtra("mail");
        String name3 = intent.getStringExtra("name");
        String pass3 = intent.getStringExtra("password");
        String phone3 = intent.getStringExtra("phone_no");
        Boolean forgotpass = intent.getBooleanExtra("forgot password", false);

        verify = findViewById(R.id.verifyOtp);
        textView = findViewById(R.id.subtextPhone);
        mobile_no = "+91" + phone3;
        one = findViewById(R.id.numberOne);
        two = findViewById(R.id.numberTwo);
        three = findViewById(R.id.numberThree);
        four = findViewById(R.id.numberFour);
        five = findViewById(R.id.numberFive);
        six = findViewById(R.id.numberSix);
        resendOtp1 = findViewById(R.id.resendOtp);
        ProgressBar progressbar2 = findViewById(R.id.progressBarverifyOtp);

        textView.setText("+91 " + phone3);

        message_otp();

        numberotpmove();
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (one.getText().toString().isEmpty() || two.getText().toString().isEmpty() || three.getText().toString().isEmpty() || four.getText().toString().isEmpty() || five.getText().toString().isEmpty() || six.getText().toString().isEmpty()) {
                    Toast.makeText(otpVerification.this, "please Enter the otp", Toast.LENGTH_SHORT).show();
                } else {
                    String enterCodeOtp = one.getText().toString() + two.getText().toString() + three.getText().toString() +
                            four.getText().toString() + five.getText().toString() + six.getText().toString();
                    int enteredCodeOtp = Integer.parseInt(enterCodeOtp);
//                    progressbar2.setVisibility(View.VISIBLE);
//                    verify.setVisibility(View.INVISIBLE);
                    if (enteredCodeOtp == otp_code) {
                        if (forgotpass) {
                            String mail3 = intent.getStringExtra("mail");
                            String new_pass = intent.getStringExtra("new password");
                            boolean update_pass = dbHandler.update_pass(mail3, new_pass);
                            if (update_pass) {
                                Toast.makeText(otpVerification.this, "NEW PASSWORD UPDATED \nSIGN IN WITH NEW PASSWORD", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(otpVerification.this, logInPage.class);
                                startActivity(intent);
                            } else
                                Toast.makeText(otpVerification.this, "NEW PASSWORD NOT UPDATED", Toast.LENGTH_SHORT).show();
                        } else {
                            boolean insert = dbHandler.insert_data(mail3, name3, pass3, phone3);
                            if (insert) {
                                Toast.makeText(otpVerification.this, "USER REGISTERED SUCCESSFULLY \nSIGN IN WITH REGISTERED ACCOUNT", Toast.LENGTH_LONG).show();
                                Intent intent1 = new Intent(otpVerification.this, logInPage.class);
                                startActivity(intent1);
                            } else
                                Toast.makeText(otpVerification.this, "REGISTRATION FAILED", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(otpVerification.this, "Enter correct OTP", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        resendOtp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message_otp();
            }
        });
    }

    private void numberotpmove() {
        one.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    two.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        two.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    three.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        three.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    four.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        four.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    five.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        five.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    six.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void sendSMS(int otp) {
        String SMS = "Your FOODZY user verification OTP is "+otp+".\nVerify with OTP to continue. \n";
        if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
            try {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(mobile_no, null, SMS, null, null);
                Toast.makeText(otpVerification.this, "OTP Sent", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(otpVerification.this, "OTP Not Sent", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void message_otp(){
        Random r = new Random();
        otp_code = r.nextInt((999999 - 100000)+1) + 100000;
        sendSMS(otp_code);
    }
}