package com.getninjas.test.jeffersonalvess.getninjastest;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class JSONParser {

    static InputStream is = null;
    static JSONObject json = null;
    static String output = "";


    public JSONParser() {

    }

    public JSONObject getJSONFromUrl(String url, List params) throws IOException {

        URL _url = new URL(url);
        HttpURLConnection urlConnection = (HttpURLConnection) _url.openConnection();;

        try {
            is = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder total = new StringBuilder(is.available());
            String line;

            while ((line = reader.readLine()) != null) {
                total.append(line).append('\n');
            }
            output = total.toString();
            json = new JSONObject(output);
        }
        catch (JSONException e) {
            e.printStackTrace();
            return null;
        }


        return json;
    }
}

class RetrieveJSONTask extends AsyncTask<String, Void, JSONObject> {

    private Exception exception;

    protected JSONObject doInBackground(String... urls) {
        try {
            JSONParser jp = new JSONParser();
            JSONObject json = jp.getJSONFromUrl(urls[0], null);

            return json;
        }
        catch (Exception e) {
            this.exception = e;
            return null;
        }
    }

    protected void onPostExecute(JSONObject json) {
        super.onPostExecute(json);
    }
}
