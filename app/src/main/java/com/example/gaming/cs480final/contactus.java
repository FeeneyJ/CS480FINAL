package com.example.gaming.cs480final;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class contactus extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback {

    private GoogleMap mMap;
    private static final float zoom = 9.0f;

    private TextView wphone;
    private TextView fphone;
    private TextView wemail;
    private TextView femail;
    private static final String tag = "in contactus activity";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);

        //asociates variables to their IDs
        wphone = findViewById(R.id.wphone);
        fphone = findViewById(R.id.fphone);
        wemail = findViewById(R.id.wemail);
        femail = findViewById(R.id.femail);

        // sets listeners to variables
        wphone.setOnClickListener(this);
        fphone.setOnClickListener(this);
        wemail.setOnClickListener(this);
        femail.setOnClickListener(this);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }
    public void onClick(View v) throws SecurityException {

        switch (v.getId()) {
            case R.id.wphone:
                try {
                    Uri uri = Uri.parse("tel:5086689100");
                    Intent i = new Intent(Intent.ACTION_CALL, uri);
                    startActivity(i);
                } catch (Exception e) {
                    Log.e(tag, "failed to call wphone" + e.getMessage());
                }
            case R.id.fphone:
                try {
                    Uri uri = Uri.parse("tel:5085209100");
                    Intent i = new Intent(Intent.ACTION_CALL, uri);
                    startActivity(i);
                } catch (Exception e) {
                    Log.e(tag, "failed to call fphone" + e.getMessage());
                }
            case R.id.wemail:
                try{
                    Uri uri = Uri.parse(wemail.getText().toString());
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("plain/text");
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "jricci367@gmail.com" });
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Question for Delux Tux");
                    intent.putExtra(Intent.EXTRA_TEXT, "From new app!");
                    startActivity(Intent.createChooser(intent, ""));
                }
                catch (Exception e) {
                    Log.e(tag, "failed to wemail" + e.getMessage());
                }
            case R.id.femail:
                try{
                    Uri uri = Uri.parse(femail.getText().toString());
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("plain/text");
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "deluxtux@hotmail.com" });
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Question for Delux Tux");
                    intent.putExtra(Intent.EXTRA_TEXT, "From new app!");
                    startActivity(Intent.createChooser(intent, ""));
                }
                catch (Exception e) {
                    Log.e(tag, "failed to femail" + e.getMessage());
                }

            }
        }

    public void MarkerToastMaker(Marker m){
        String title = m.getTitle();
        String snip = m.getSnippet();
        Toast.makeText(getApplicationContext(), title + "\n" + snip, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onMapReady(GoogleMap googleMap)  {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(42.115529, -71.310868), zoom));
        // Add a marker by the four universities
        final Marker m1 = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(42.0802341, -71.382775))
                .title("Delux Tux Franklin")
                .snippet("Est. 1996")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));

        final Marker m2 = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(42.145456, -71.252900))
                .title("Delux Tux Walpole")
                .snippet("Est. 1999")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));

        mMap.setOnMarkerClickListener(
                new GoogleMap.OnMarkerClickListener() {
                    public boolean onMarkerClick(Marker m) throws SecurityException {
                        Intent i = new Intent(Intent.ACTION_CALL);
                        MarkerToastMaker(m);
                        // if marker equals m1,m2 open web with intent with each marker's cooresponding website

                        if (m.equals(m1)) {
                            try {
                                i.putExtra("url", "https://www.deluxtux.com");
                                startActivity(i);
                            } catch (Exception e) {
                                Log.e(tag, "failed to open website" + e.getMessage());
                            }
                        }

                        if (m.equals(m2)) {
                            try {
                                i.putExtra("url", "https://www.deluxtux.com");
                                startActivity(i);
                            }
                            catch (Exception e){
                                Log.e(tag, "failed to open website" + e.getMessage());
                            }
                        }
                        return true;
                    }
                }


        );
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
