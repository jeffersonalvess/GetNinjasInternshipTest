package com.getninjas.test.jeffersonalvess.getninjastest;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutionException;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class DetailsActivity extends AppCompatActivity implements OnMapReadyCallback  {

    Detail details;
    String from = "";
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //  Basic Configuration
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //  Getting info from last MainActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            from = extras.getString("from");
            details = getDetailsInfo(extras.getString("link"));

            getSupportActionBar().setTitle(from);
        }

        //  Handling info list
        inflateItemsList();


        // Inflating top and bottom information
        inflateLayoutOutsideList();

        //  Configuring the buttons on screen's bottom
        inflateBottomButtons();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap map) {

        LatLng lg = new LatLng(details.getGeoLatitude(), details.getGeoLongitude());
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(lg, 16.4f));
        map.addCircle(new CircleOptions()
                .center(lg)
                .radius(80)
                .strokeWidth(0f)
                .fillColor(0x5500B0FF));
    }

    public Detail getDetailsInfo(String uri) {
        Detail detail = new Detail();
        JSONParser jsonParser = new JSONParser();

        try {
            JSONObject json = new RetrieveJSONTask().execute(uri).get();

            //  Populating straightforward data
            detail.setDistance(json.optInt("distance"));
            detail.setLeadPrice(json.optInt("lead_price"));
            detail.setTitle(json.optString("title"));
            detail.setName(json.getJSONObject("_embedded").getJSONObject("user").optString("name"));
            detail.setEmail(json.getJSONObject("_embedded").getJSONObject("user").optString("email"));
            detail.setCity(json.getJSONObject("_embedded").getJSONObject("address").optString("city"));
            detail.setNeighborhood(json.getJSONObject("_embedded").getJSONObject("address").optString("neighborhood"));
            detail.setUf(json.getJSONObject("_embedded").getJSONObject("address").optString("uf"));
            detail.setGeoLatitude(json.getJSONObject("_embedded").getJSONObject("address").getJSONObject("geolocation").optDouble("latitude"));
            detail.setGeoLongitude(json.getJSONObject("_embedded").getJSONObject("address").getJSONObject("geolocation").optDouble("longitude"));

            if (from.equals("Offer")) {
                detail.setLinkAccept(json.getJSONObject("_links").getJSONObject("accept").getString("href"));
                detail.setLinkReject(json.getJSONObject("_links").getJSONObject("reject").getString("href"));
            }
            else
            {
                detail.setLinkAccept("");
                detail.setLinkReject("");
            }


            //  Phones
            JSONArray jsonArrayPhones = new JSONArray(json.getJSONObject("_embedded").getJSONObject("user").getJSONObject("_embedded").optString("phones"));
            JSONObject jsonObjectPhones = jsonArrayPhones.getJSONObject(0);
            detail.setPhone(jsonObjectPhones.optString("number"));


            //  Populating ArrayList
            JSONArray jsonArray = new JSONArray(json.getJSONObject("_embedded").optString("info"));
            ArrayList<Info> infos = new ArrayList<Info>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jO = jsonArray.getJSONObject(i);
                Info info = new Info(jO.optString("label"), jO.optString("value"));
                infos.add(info);
            }

            detail.setInfo(infos);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        catch (ExecutionException e) {
            e.printStackTrace();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        return detail;

    }

    public void inflateItemsList(){
        RecyclerView rvInfos = (RecyclerView) findViewById(R.id.recycler_view);
        Context c = getApplicationContext();

        float density = getApplicationContext().getResources().getDisplayMetrics().density;
        float px = 60 * density;

        InfosAdapter adapter = new InfosAdapter(getApplicationContext(), details.getInfo(), from);
        rvInfos.setAdapter(adapter);
        rvInfos.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvInfos.setNestedScrollingEnabled(false);
        rvInfos.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int)(details.getInfo().size() * px)));
        rvInfos.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    private void inflateLayoutOutsideList() {
        TextView txtDetailsTitle = (TextView) findViewById(R.id.txtDetailsTitle);
        TextView txtDetailsUser = (TextView) findViewById(R.id.txtDetailsUser);
        TextView txtDetailsLocation = (TextView) findViewById(R.id.txtDetailsLocation);
        TextView txtDetailsDistance = (TextView) findViewById(R.id.txtDetailsDistance);
        TextView txtDetailsPhone = (TextView) findViewById(R.id.txtDetailsPhone);
        TextView txtDetailsEmail = (TextView) findViewById(R.id.txtDetailsEmail);
        TextView txtDetailsContactTitle = (TextView) findViewById(R.id.txtDetailsContactTitle);

        ImageView imgDetailsPerson = (ImageView) findViewById(R.id.imgDetailsPerson);
        ImageView imgDetailsLocation= (ImageView) findViewById(R.id.imgDetailsLocation);
        ImageView imgDetailsPhone = (ImageView) findViewById(R.id.imgDetailsPhone);
        ImageView imgDetailsEmail = (ImageView) findViewById(R.id.imgDetailsEmail);

        LinearLayout llDetailsClientContact = (LinearLayout) findViewById(R.id.llDetailsClientContact);


        double distance = details.getDistance()/10000;

        txtDetailsTitle.setText(details.getTitle());
        txtDetailsUser.setText(details.getName());
        txtDetailsLocation.setText(details.getNeighborhood() + " - " + details.getCity());
        txtDetailsDistance.setText("about " + String.valueOf(distance) + " km from you.");
        txtDetailsPhone.setText(details.getPhone());
        txtDetailsEmail.setText(details.getEmail());

        if (from.equals("Offer")) {
            imgDetailsPerson.setColorFilter(Color.parseColor("#0091ea"));
            imgDetailsLocation.setColorFilter(Color.parseColor("#0091ea"));
            imgDetailsPhone.setColorFilter(Color.parseColor("#FFFFFF"));
            imgDetailsEmail.setColorFilter(Color.parseColor("#FFFFFF"));

            txtDetailsPhone.setTextColor(Color.parseColor("#FFFFFF"));
            txtDetailsEmail.setTextColor(Color.parseColor("#FFFFFF"));
            txtDetailsContactTitle.setTextColor(Color.parseColor("#FFFFFF"));

            llDetailsClientContact.setBackgroundColor(Color.parseColor("#4ec6fe"));
        }
        else {
            imgDetailsPerson.setColorFilter(Color.parseColor("#90e135"));
            imgDetailsLocation.setColorFilter(Color.parseColor("#90e135"));
            imgDetailsPhone.setColorFilter(Color.parseColor("#000000"));
            imgDetailsEmail.setColorFilter(Color.parseColor("#000000"));

            txtDetailsPhone.setTextColor(Color.parseColor("#000000"));
            txtDetailsEmail.setTextColor(Color.parseColor("#000000"));
            txtDetailsContactTitle.setTextColor(Color.parseColor("#000000"));

            llDetailsClientContact.setBackgroundColor(Color.parseColor("#c8f57e"));
        }
    }

    private void inflateBottomButtons() {

        LinearLayout buttonLeft = (LinearLayout) findViewById(R.id.llbtnLeft);
        LinearLayout buttonRight = (LinearLayout) findViewById(R.id.llbtnRight);

        ImageView imgLeft = (ImageView) findViewById(R.id.imgbtnLeft);
        ImageView imgRight = (ImageView) findViewById(R.id.imgbtnRight);

        TextView txtLeft = (TextView) findViewById(R.id.txtbtnLeft);
        TextView txtRight = (TextView) findViewById(R.id.txtbtnRight);


        if (from.equals("Offer"))
        {
            imgLeft.setImageResource(R.drawable.ic_close);
            txtLeft.setText("REFUSE");

            imgRight.setImageResource(R.drawable.ic_accept);
            txtRight.setText("ACCEPT");

            buttonLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            buttonRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);

                    intent.putExtra("from", "Lead");
                    intent.putExtra("link", details.getLinkAccept());
                    intent.addFlags(FLAG_ACTIVITY_NEW_TASK);

                    finish();
                    startActivity(intent);

                }
            });
        }
        else
        {
            imgLeft.setImageResource(R.drawable.ic_phone);
            txtLeft.setText("CALL");

            imgRight.setImageResource(R.drawable.ic_sms);
            txtRight.setText("WHATSAPP");

            buttonLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", details.getPhone(), null));
                    startActivity(intent);
                }
            });

            buttonRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + details.getPhone()));
                    intent.setPackage("com.whatsapp");
                    startActivity(intent);
                }
            });
        }
    }
}
