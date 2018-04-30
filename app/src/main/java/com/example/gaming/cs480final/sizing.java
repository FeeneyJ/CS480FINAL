package com.example.gaming.cs480final;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;


public class sizing extends AppCompatActivity {
    private EditText name;
    private EditText phonenumber;
    private EditText NeckValue;
    private EditText SleeveValue;
    private EditText WaistValue;
    private EditText OutValue;
    private EditText Chest;
    private Button send;
    private SQLiteDatabase db;
    private final String create = Constants.CREATE_TABLE;
    private ContentValues values;
    private Cursor cursor;
    private TextView text;
    private final String file = "info.txt";
    private NotificationManager mNotificationManager;
    private Notification notifyDetails;
    private int SIMPLE_NOTFICATION_ID;
    private String contentTitle = "New Customer sent info!";
    private String contentText = "Call them asap!";
    private String tickerText = "Click Me !!!";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sizing);

        //associates variables to their xml resource
        name =findViewById(R.id.name);
        phonenumber = findViewById(R.id.phonenumber);
        NeckValue =findViewById(R.id.NeckValue);
        SleeveValue =findViewById(R.id.SleeveValue);
        WaistValue =findViewById(R.id.WaistValue);
        OutValue =findViewById(R.id.OutValue);
        Chest =findViewById(R.id.Chest);
        send = findViewById(R.id.send);
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        //create intent for action when notification selected
        //from expanded status bar
        Intent notifyIntent = new Intent(this, employeelogin.class);
        //create pending intent to wrap intent so that it
        //will fire when notification selected.
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        //build notification object and set parameters
        notifyDetails =
                new Notification.Builder(this)
                        .setContentIntent(pendingIntent)

                        .setContentTitle(contentTitle)   //set Notification text and icon
                        .setContentText(contentText)
                        .setSmallIcon(R.drawable.droid)

                        .setTicker(tickerText)            //set status bar text

                        .setWhen(System.currentTimeMillis())    //timestamp when event occurs

                        .setAutoCancel(true)     //cancel Notification after clicking on it

                        //set Android to vibrate when notified
                        .setVibrate(new long[]{1000, 1000, 1000, 1000})

                        // flash LED (color, on in millisec, off)
                        //doesn't work for all handsets
                        .setLights(Integer.MAX_VALUE, 500, 500)

                        .build();


        // sets on click listener for send button, gathers all edittext values and concats them into one string variable "info" :(
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String info = "";
                info += name.getText().toString()+" ";
                info += phonenumber.getText().toString()+" ";
                info += NeckValue.getText().toString()+" ";
                info += SleeveValue.getText().toString()+" ";
                info += WaistValue.getText().toString()+" ";
                info += OutValue.getText().toString()+" ";
                info += Chest.getText().toString()+" ";

                //displays noti for successful button press
                AlertDialog dialog = new AlertDialog.Builder(sizing.this).create();
                //set message, title, and icon
                dialog.setTitle("Thanks for sending us your info " + name.getText().toString() +"!");
                dialog.show();
                //writes info to log
                Log.d("info stuff", info);
                //writes info to txt file in directory
                FileOutputStream ops;
                try {
                    ops = openFileOutput(file, Context.MODE_PRIVATE);
                    ops.write(info.getBytes());
                    ops.close();
                }
                catch (IOException e) {
                    Log.e("Exception", "File write failed: " + e.toString());
                }


                //writes info to SQLite Database
               // creates database and table
                try {
                    db = openOrCreateDatabase(Constants.DATABASE_NAME, Context.MODE_PRIVATE, null);
                    db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
                    db.execSQL(create);

                    //insert info as a record generates random number between 1 and 1,000,000 for key id
                    Random rand = new Random();
                    int n = rand.nextInt(1000000) + 1;
                    values = new ContentValues();
                    values.put(Constants.KEY_ID, n);
                    values.put(Constants.KEY_NAME, name.getText().toString());
                    values.put(Constants.KEY_PHONE, phonenumber.getText().toString());
                    values.put(Constants.KEY_NECK, NeckValue.getText().toString());
                    values.put(Constants.KEY_SLEEVE, NeckValue.getText().toString());
                    values.put(Constants.KEY_WAIST, WaistValue.getText().toString());
                    values.put(Constants.KEY_OUT, OutValue.getText().toString());
                    values.put(Constants.KEY_CHEST, Chest.getText().toString());
                    db.insert(Constants.TABLE_NAME, null, values);
                }
                catch(Exception e){
                    Log.d("SQLite implementation threw", e.toString());
                }


            }
        });


    }



    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        final Context context = this;
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.home:
                Intent intent1 = new Intent(context, welcome.class);
                startActivity(intent1);
                return true;

            case R.id.sizing:
                Intent intent3 = new Intent(context, sizing.class);
                startActivity(intent3);

                return true;

            case R.id.contact:
                Intent intent4 = new Intent(context, contactus.class);
                startActivity(intent4);
                return true;

            case R.id.employeelogin:
                Intent i = new Intent(context, employeelogin.class);
                startActivity(i);
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
