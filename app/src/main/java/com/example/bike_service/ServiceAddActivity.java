package com.example.bike_service;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ServiceAddActivity extends AppCompatActivity {
    Button search,add,send,bill,delete;
    TextView name,mob,servicenote,date;
    EditText veh,brecord,bamount;
    Cursor resultSet;
    SQLiteDatabase mydatabase;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_add);
        this.setTitle("Service");
        mydatabase = openOrCreateDatabase("service", MODE_PRIVATE, null);

        search=(Button) findViewById(R.id.sad_search_button) ;
        add=(Button) findViewById(R.id.sad_addService_button) ;
        send=(Button) findViewById(R.id.sad_send_button) ;
        bill=(Button) findViewById(R.id.sad_bill_button) ;
        delete=(Button) findViewById(R.id.sad_delete_button) ;

        name=(TextView) findViewById(R.id.sad_name_textView) ;
        mob=(TextView) findViewById(R.id.sad_mobile_textView) ;
        servicenote=(TextView) findViewById(R.id.sad_sn_textView) ;
        date=(TextView) findViewById(R.id.sad_date_textView) ;

        veh=(EditText)findViewById(R.id.sad_vehicleNo_editText) ;
        brecord=(EditText)findViewById(R.id.sad_bRecord_editText) ;
        bamount=(EditText)findViewById(R.id.sad_amount_editText) ;

        search.setVisibility(View.VISIBLE);
        add.setVisibility(View.GONE);
        send.setVisibility(View.GONE);
        bill.setVisibility(View.GONE);
        delete.setVisibility(View.GONE);

        name.setVisibility(View.GONE);
        mob.setVisibility(View.GONE);
        servicenote.setVisibility(View.GONE);
        date.setVisibility(View.GONE);

        veh.setVisibility(View.VISIBLE);
        brecord.setVisibility(View.GONE);
        bamount.setVisibility(View.GONE);
        try {
            //  mydatabase = openOrCreateDatabase("service", MODE_PRIVATE, null);
            mydatabase.execSQL("CREATE TABLE IF NOT EXISTS user(vehicle VARCHAR,model VARCHAR,name VARCHAR,mobile VARCHAR,email VARCHAR);");
        }
        catch(Exception e)
        {
            Toast.makeText(ServiceAddActivity.this, ""+e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    public void search(View v)
    {
        String vehicle;
        vehicle = veh.getText().toString();
        if (vehicle.isEmpty()) {
            Toast.makeText(ServiceAddActivity.this, "Enter Vehicle No", Toast.LENGTH_LONG).show();
        } else if (!vehicle.toString().isEmpty()) {

            resultSet = mydatabase.rawQuery("Select * from user where vehicle='" + veh.getText().toString().toUpperCase() + "'", null);
            if (resultSet.getCount() == 0) {
                Toast.makeText(ServiceAddActivity.this, "Create New User", Toast.LENGTH_LONG).show();
                i = new Intent(ServiceAddActivity.this, Main3Activity.class);
                i.putExtra("vehicle",veh.getText().toString());
                startActivity(i);



            } else if (resultSet.getCount() > 0)
            {
                Toast.makeText(ServiceAddActivity.this, "from update", Toast.LENGTH_LONG).show();

                veh.setEnabled(false);
                search.setVisibility(View.GONE);
                name.setVisibility(View.VISIBLE);
                mob.setVisibility(View.VISIBLE);
                servicenote.setVisibility(View.VISIBLE);
                add.setVisibility(View.VISIBLE);
                resultSet.moveToFirst();
                veh.setText(resultSet.getString(0));
                // mod.setText(resultSet.getString(1));
                name.setText(resultSet.getString(2));
                mob.setText(resultSet.getString(3));

            }
        }
    }
}
