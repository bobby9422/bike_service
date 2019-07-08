package com.example.bike_service;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText user,pass;
Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Login");
        user=(EditText)findViewById(R.id.editText);
        pass=(EditText)findViewById(R.id.editText2);
        b1=(Button)findViewById(R.id.button);
        b1.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((user.getText().toString().equalsIgnoreCase("bobby")) && (pass.getText().toString().equals("login")))
                {
                    Toast.makeText(MainActivity.this,"Successful Login!",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(MainActivity.this, Main2Activity.class);
                   // i.putExtra("user",user.getText().toString().toUpperCase());
                    startActivity(i);

                }
                else
                {
                    Toast.makeText(MainActivity.this,"Unsuccessful Login!",Toast.LENGTH_LONG).show();
                }

            }
        });


    }
    public void clear(View v)
    {
        user.setText("");
        pass.setText("");
        Toast.makeText(MainActivity.this,"Login again!",Toast.LENGTH_LONG).show();
    }
}
