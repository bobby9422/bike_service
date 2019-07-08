package com.example.bike_service;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class Main5Activity extends AppCompatActivity {
    private ArrayList<vehicleview> vehlist = new ArrayList<>();
    Cursor resultSet;
    SQLiteDatabase mydatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        this.setTitle("Active Reminders");
        ArrayAdapter<vehicleview> adapter = new ListViewAdapter(this, 0, vehlist);

        mydatabase = openOrCreateDatabase("service", MODE_PRIVATE, null);
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS rem(vehicle VARCHAR,date VARCHAR,sn VARCHAR(20));");
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS user(vehicle VARCHAR,model VARCHAR,name VARCHAR,mobile VARCHAR,email VARCHAR);");

        resultSet = mydatabase.rawQuery("SELECT user.name, user.mobile, user.vehicle, rem.date \n" +
                "FROM user\n" +
                "INNER JOIN rem ON user.vehicle = rem.vehicle",null);
        if (resultSet.moveToFirst()){
            do {
                // Passing values
                String name = resultSet.getString(0);
                String mobile = resultSet.getString(1);
                String vehicle = resultSet.getString(2);
                String date = resultSet.getString(3);
                vehlist.add(
                        new vehicleview(name,mobile,vehicle,date));
            } while(resultSet.moveToNext());
        }

//        vehlist.add(
//                new vehicleview("bobby","7020724885","MH14FE6320","28-06-2019"));



        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        AdapterView.OnItemClickListener adapterViewListener = new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                vehicleview vv = vehlist.get(position);
                Toast.makeText(Main5Activity.this, ""+vv.getName(), Toast.LENGTH_LONG).show();

//                Intent intent = new Intent(Main5ActivityActivity.this, DetailActivity.class);
//                intent.putExtra("streetNumber", property.getStreetNumber());
//                intent.putExtra("streetName", property.getStreetName());
//                intent.putExtra("suburb", property.getSuburb());
//                intent.putExtra("state", property.getState());
//                intent.putExtra("image", property.getImage());
//                intent.putExtra("bedrooms", property.getBedrooms());
//                intent.putExtra("bathrooms", property.getBathrooms());
//                intent.putExtra("carspots", property.getCarspots());
//                intent.putExtra("description", property.getDescription());
//
//                startActivity(intent);
            }
        };
//set the listener to the list view
        listView.setOnItemClickListener(adapterViewListener);
    }
}
