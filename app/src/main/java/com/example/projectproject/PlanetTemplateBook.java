package com.example.projectproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Scanner;

public class PlanetTemplateBook extends AppCompatActivity {

    //Private members
    private CBodies[] bodies;
    ArrayAdapter<CBodies> adapter;

    /** Called when the user taps the Menu Button */
    public void GoToMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /** Click to Open the book */
    public void OpenBook(View view) {
        Intent intent = new Intent(this, PlanetTemplate.class);
        startActivity(intent);
    }
//
//    /** Click to Close the book */
//    public void CloseBook(View view) {
//        setContentView(R.layout.activity_planet_template_book);
//    }
//
//    /** Click to Open the Notes */
//    public void OpenNotes(View view) {
//        setContentView(R.layout.activity_planet_template_notes);
//    }

    public static String convertStreamtoString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }

    @SuppressWarnings("SameParameterValue")
    private String readFile(String fileName) {
        try {
            //noinspection CharsetObjectCanBeUsed
            return new Scanner(getApplicationContext().getAssets().open(fileName), Charset.forName("UTF-8").name()).useDelimiter("\\A").next();
        } catch (IOException e) {
            Log.e("MainActivity ==>", "Could not read file: " + fileName);
            return null;
        }
    }


    // Global Variables
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet_template_book);

//        new JsonTask().execute("https://wwwlab.iit.his.se/brom/kurser/mobilprog/dbservice/admin/getdataasjson.php?type=a20chrho");

        try {
            //
            //
            String s = readFile("bodies.json");
            Gson gson = new Gson();
            bodies = gson.fromJson(s,CBodies[].class);
            for (int i = 0; i < bodies.length; i++) {
                Log.d("Main Activity ==>", "hittade himlakroppar: "+bodies[i]);
            }

            //arrays n stuff

            adapter = new ArrayAdapter<>(this,R.layout.itemtv,R.id.itemtv,bodies);
            ListView listView = findViewById(R.id.list_view);
            listView.setAdapter(adapter);
//            result.setText("\t" + bodies[2].getGravity());

//            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
////                public void onItemClick(AdapterView<?> parent, View view, int position, long id){
////                    showExternalWebPage(position);
//                }
//            });

        } catch (Exception e) {
            Log.e("MainActivity==>", "Something went wrong when reading textfile: \n\n"+ e.getMessage());
        }


    }

//    @SuppressLint("StaticFieldLeak")
//    private class JsonTask extends AsyncTask<String, String, String> {
//
//        private HttpURLConnection connection = null;
//        private BufferedReader reader = null;
//
//        protected String doInBackground(String... params) {
//            try {
//                URL url = new URL(params[0]);
//                connection = (HttpURLConnection) url.openConnection();
//                connection.connect();
//
//                InputStream stream = connection.getInputStream();
//                reader = new BufferedReader(new InputStreamReader(stream));
//
//                StringBuilder builder = new StringBuilder();
//                String line;
//                while ((line = reader.readLine()) != null && !isCancelled()) {
//                    builder.append(line).append("\n");
//                }
//                return builder.toString();
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                if (connection != null) {
//                    connection.disconnect();
//                }
//                try {
//                    if (reader != null) {
//                        reader.close();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            return null;
//        }

//        @Override
    //Här gör vi vår parsing
//        protected void onPostExecute(String json) {
//            Log.d("MainActivity==>", json);
//            Gson gson = new Gson();
//            bodies = gson.fromJson(json,CBodies[].class);
//            Log.d("AsyncTask ==>", "Hittade ett berg; "+bodies);

//            for (int i = 0; i < cbodies.length; i++) {
//                Log.d("AsyncTask ==>", "Hittade ett berg; "+bodies[i]);
//
//            }
//            adapter.clear();
//            adapter.notifyDataSetChanged();
//            for (int i = 0; i < bodies.length; i++) {
////                Log.d("Main Activity ==>", "hittade ett berg: "+bodies[i]);
//            }
}
//    }
//}