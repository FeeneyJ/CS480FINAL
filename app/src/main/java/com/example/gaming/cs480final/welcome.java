package com.example.gaming.cs480final;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;

import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.tomer.fadingtextview.FadingTextView;
public class  welcome extends AppCompatActivity {
    private FadingTextView fadingTextView;
    private TextView DeluxTitle;
    private TextView officialsupplier;
    private TextView nep;
    private TextView opendaily;
    private TextView dailytime;
    private ImageButton instagram;
    private ImageButton facebook;
    private ImageButton twitter;
    private ImageButton chrome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // initializes fonts to corresponding variables
        Typeface freebootscript = getResources().getFont(R.font.freebooterscript);
        Typeface cavierdreams = getResources().getFont(R.font.caviardreams);
        Typeface rechtmanplain = getResources().getFont(R.font.rechtmanplain);

        //sets variables to xml resource
        DeluxTitle = findViewById(R.id.deluxtitle);
        fadingTextView = findViewById(R.id.fading_text_view);
        officialsupplier = findViewById(R.id.officialsupplier);
        nep = findViewById(R.id.nep);
        opendaily = findViewById(R.id.opendaily);
        dailytime = findViewById(R.id.dailytime);
        instagram = findViewById(R.id.instagram);
        facebook = findViewById(R.id.facebook);
        twitter = findViewById(R.id.twitter);
        chrome = findViewById(R.id.chrome);


        //sets fonts and colors
        fadingTextView.setTypeface(freebootscript);
        DeluxTitle.setTypeface(freebootscript);
        DeluxTitle.setTextColor(getColor(R.color.textPrimary));
        fadingTextView.setTextColor(getColor(R.color.textPrimary));
        officialsupplier.setTextColor(getColor(R.color.textPrimary));
        nep.setTextColor(getColor(R.color.textPrimary));
        officialsupplier.setTypeface(cavierdreams);
        nep.setTypeface(freebootscript);
        opendaily.setTypeface(cavierdreams);
        opendaily.setTextColor(getColor(R.color.textPrimary));
        dailytime.setTypeface(cavierdreams);
        dailytime.setTextColor(getColor(R.color.textPrimary));

        // sets background color
        View root = findViewById(R.id.activitiy_welcome).getRootView();
        root.setBackgroundColor(getColor(R.color.bgrd));

        // sets on click listeners for all icon-buttons, was unable to do with switch statement :(
        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.instagram.com/deluxtuxwalpole/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.facebook.com/DeluxTuxWalpole/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://twitter.com/search?vertical=default&q=delux%20tux";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        chrome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://www.deluxtux.com/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
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
