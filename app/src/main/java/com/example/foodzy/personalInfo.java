package com.example.foodzy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class personalInfo extends AppCompatActivity implements  addressdialogbox.DialogListener {

    ListView listView1;
    ImageView imageView1;
    TextView t1;
    Button b3;
    DatabaseReference ref1;
    final String topics1[] = {"Name","Mobile","Email","D.O.B"};
    final String data1[] = {"Developer","9999999999","gmail.com","01-01-1001"};
    final int pics1[] = {R.drawable.person1,R.drawable.number1,R.drawable.email1,R.drawable.date1};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        FloatingActionButton b1 = (FloatingActionButton) findViewById(R.id.homebutton);
        listView1 = findViewById(R.id.listview1);
        b3 = findViewById(R.id.button3);
        imageView1 = findViewById(R.id.editimage);
        t1  = findViewById(R.id.addressfield);
        ref1 = FirebaseDatabase.getInstance().getReference().child("ADDRESS");
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
                    t1.setText("No Address Added");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(personalInfo.this,homePage.class);
                startActivity(intent);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
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
    t1.setText(address);
    ref1.child("value").setValue(address);
    }
}