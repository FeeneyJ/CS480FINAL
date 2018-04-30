package com.example.gaming.cs480final;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class employeelogin extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private TextView text;
    private TextToSpeech speaker;
    private EditText username;
    private EditText password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employeelogin);
        //pops the login dialog box up
        callLoginDialog();
        //references textview to variable
        text = findViewById(R.id.texte);
        speaker = new TextToSpeech(this, this);
        try {
            List<String> info = Arrays.asList(FiletoStr(this).split(" "));
            String str = "      New Customer Information!\n..." + "\nName:  " + info.get(0) + "\nPhone: " + info.get(1) + "\nNeck:   " + info.get(2) + "\nSleeve: " + info.get(3) + "\nWaist:   " + info.get(4) + "\nOutseam:   " + info.get(5) + "\nChest:  " + info.get(6) + "\n      please give them a call!";
            text.setText(str);
            speak(str);
        }
        catch( Exception e){
            Toast.makeText(this,("No new customer information"), Toast.LENGTH_LONG);
        }
        // sets tasks, creates listadapter converter, creates reference to listview, and sets adapter to the listview
        String[] tasks = {"Sweeping", "Hemming","Sewing","Steaming","Cashiering","Doorman","Shoe Shinning"};
        ListAdapter la = new ArrayAdapter<String>(this,R.layout.item,tasks);
        ListView listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(la);
        listview.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String str = String.valueOf(parent.getItemAtPosition(position));
                        //create dialog
                        AlertDialog dialog = new AlertDialog.Builder(employeelogin.this).create();
                        //set message, title, and icon
                        dialog.setTitle("Thanks for doing " + str);
                        dialog.setMessage("Management has taken note.");
                        dialog.show();
                    }
                }
        );
    }

    // takes contents of info.txt and returns string with the values
    private String FiletoStr(Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("info.txt");
            // if the stream is not null
            if ( inputStream != null ) {
                //initialize readers
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                //creates string
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();
                // while next Line != null set nextline to receive string
                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
        speak(ret);
        return ret;
    }

    // creates a login dialog with the dialog_employee layout
    private void callLoginDialog()
    {
        final Dialog myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.dialog_employee);
        //unable to leave dialog without correct username/password
        myDialog.setCancelable(false);
        Button login = myDialog.findViewById(R.id.lgnbtn);

        username = myDialog.findViewById(R.id.username);
        password = myDialog.findViewById(R.id.password);
        myDialog.show();

        //sets on click lister to login button to check the password and username against the correct: "James Feeney" and "cs480"
        login.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                //if password is correct dismiss the dialog
                if(username.getText().toString().equals("James Feeney") && password.getText().toString().equals("cs480"))
                    myDialog.dismiss();
                
                else{
                    //create dialog
                    AlertDialog dialog = new AlertDialog.Builder(employeelogin.this).create();
                    //set message, title, and icon
                    dialog.setTitle("Incorrect");
                    dialog.setMessage("Wrong password");
                    dialog.show();
                }

            }
        });


    }
    //TTS
    public void speak(String output){
        //	speaker.speak(output, TextToSpeech.QUEUE_FLUSH, null);  //for APIs before 21
        speaker.speak(output, TextToSpeech.QUEUE_FLUSH, null, "Id 0");
    }

    // Implements TextToSpeech.OnInitListener.
    public void onInit(int status) {
        // status can be either TextToSpeech.SUCCESS or TextToSpeech.ERROR.
        if (status == TextToSpeech.SUCCESS) {
            // Set preferred language to US english.
            // If a language is not be available, the result will indicate it.
            int result = speaker.setLanguage(Locale.US);

            //int result = speaker.setLanguage(Locale.FRANCE);
            if (result == TextToSpeech.LANG_MISSING_DATA ||
                    result == TextToSpeech.LANG_NOT_SUPPORTED) {
                // Language data is missing or the language is not supported.
                Log.e("OnInit", "Language is not available.");
            } else {
                // The TTS engine has been successfully initializedq
                Log.i("OnInit", "TTS Initialization successful.");
            }
        } else {
            // Initialization failed.
            Log.e("OnInit", "Could not initialize TextToSpeech.");
        }
    }
    // on destroy
    public void onDestroy(){

        // shut down TTS engine
        if(speaker != null){
            speaker.stop();
            speaker.shutdown();
        }
        super.onDestroy();
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
