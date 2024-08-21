package com.example.foodzy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;

public class personalInfo extends AppCompatActivity implements  addressdialogbox.DialogListener, DatePickerDialog.OnDateSetListener {

    ListView listView1;
    ImageView imageView1;
    TextView t1,t2;
    Button b3,b4;
    DatabaseReference ref1,ref2;
    final String topics1[] = {"Name","Mobile","Email"};
    final String data1[] = {"Siddharth","+91-8510071981","siddharthkumar435gmail.com"};
    final int pics1[] = {R.drawable.person1,R.drawable.number1,R.drawable.email1};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        FloatingActionButton b1 = (FloatingActionButton) findViewById(R.id.homebutton);
        listView1 = findViewById(R.id.listview1);
        b3 = findViewById(R.id.button3);
        imageView1 = findViewById(R.id.editimage);
        b4 = findViewById(R.id.button4);
        t1  = findViewById(R.id.dobfield);
        t2 = findViewById(R.id.addressfield);
        ref1 = FirebaseDatabase.getInstance().getReference().child("DOB");
        ref2 = FirebaseDatabase.getInstance().getReference().child("ADDRESS");
        persinfoadapter customadapter1 = new persinfoadapter(getApplicationContext(),topics1,data1,pics1);
        listView1.setAdapter(customadapter1);

        ref1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild("value")){
                    String a = snapshot.child("value").getValue().toString();
                    t1.setText(a);
                }
                else{
                    b3.setVisibility(View.VISIBLE);
                    t1.setText("No Date Added");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ref2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild("value")){
                    String a = snapshot.child("value").getValue().toString();
                    t2.setText(a);
                }
                else{
                    b4.setVisibility(View.VISIBLE);
                    t2.setText("No Address Added");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datepickerfragment datePicker = new datepickerfragment();
                datePicker.show(getSupportFragmentManager(),"date picker");
            }
        });





        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(personalInfo.this,homePage.class);
                startActivity(intent);
            }
        });


        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });


    }

    public void openDialog(){
        addressdialogbox addressdialogbox1 = new addressdialogbox();
        addressdialogbox1.show(getSupportFragmentManager(),"Address");
    }

    @Override
    public void applyaddress(String address) {
    t2.setText(address);
    ref2.child("value").setValue(address);
    b4.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String currentDateString = DateFormat.getDateInstance().format(c.getTime());
        t1.setText(currentDateString);
        ref1.child("value").setValue(currentDateString);
        b3.setVisibility(View.INVISIBLE);
    }
}