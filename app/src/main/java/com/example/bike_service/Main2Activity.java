package com.example.bike_service;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main2Activity extends AppCompatActivity {
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        this.setTitle("Menu");

    }
    public void user(View v)
    {
        i = new Intent(Main2Activity.this, Main3Activity.class);
       // i.putExtra("user",user.getText().toString().toUpperCase());
        startActivity(i);
    }
    public void reminder(View v)
    {
        i = new Intent(Main2Activity.this, Main4Activity.class);
        // i.putExtra("user",user.getText().toString().toUpperCase());
        startActivity(i);
    }
    public void service(View v)
    {
        i = new Intent(Main2Activity.this, ServiceAddActivity.class);
        // i.putExtra("user",user.getText().toString().toUpperCase());
        startActivity(i);
    }
    public void aservice(View v)
    {
        i = new Intent(Main2Activity.this, ServiceViewActivity.class);
        // i.putExtra("user",user.getText().toString().toUpperCase());
        startActivity(i);
    }
}
