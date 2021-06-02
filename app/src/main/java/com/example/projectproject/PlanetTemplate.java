package com.example.projectproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class PlanetTemplate extends AppCompatActivity {

    //Private members
    private ArrayList<CBodies> cbodies = new ArrayList<>();
    private ArrayAdapter<CBodies> adapter;
    private ListView listView;
    private CBodies[] bodies;
    /**
     * Called when the user taps the Menu Button
     */
    public void GoToMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet_template);
        listView = findViewById(R.id.planet_list_view);
        new JsonTask().execute("https://wwwlab.iit.his.se/brom/kurser/mobilprog/dbservice/admin/getdataasjson.php?type=a20chrho");

        adapter = new ArrayAdapter<CBodies>(PlanetTemplate.this, R.layout.itemtv,cbodies);

        listView.setAdapter(adapter);

    }

    @SuppressLint("StaticFieldLeak")
    private class JsonTask extends AsyncTask<String, String, String> {

        private HttpURLConnection connection = null;
        private BufferedReader reader = null;

        protected String doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null && !isCancelled()) {
                    builder.append(line).append("\n");
                }
                return builder.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        //Här gör vi vår parsing
        protected void onPostExecute(String json) {
            Gson gson = new Gson();
            Log.d("Main Activity ==>", "hittade ett berg:");
            bodies = gson.fromJson(json,CBodies[].class);
            cbodies.clear();

            for (int i = 0; i < bodies.length; i++) {
                cbodies.add(bodies[i]);
                Log.d("Main Activity ==>", "hittade ett berg: " + bodies[i]);
            }
            adapter.notifyDataSetChanged();
        }
    }
}

