package com.example.foodzy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class personalInfo extends AppCompatActivity {

    ListView listView;
    final String topics[] = {"Name","Mobile","Email","Address","D.O.B"};
    final String data[] = {"Developer","9999999999","gmail.com","India","01-01-1001"};
    final int pics[] = {R.drawable.person1,R.drawable.number1,R.drawable.email1,R.drawable.address1,R.drawable.date1};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        FloatingActionButton b1 = (FloatingActionButton) findViewById(R.id.homebutton);
        listView = findViewById(R.id.listview1);
        persinfoadapter customadapter = new persinfoadapter(getApplicationContext(),topics,data,pics);
        listView.setAdapter(customadapter);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(personalInfo.this,homePage.class);
                startActivity(intent);
            }
        });

    }
}