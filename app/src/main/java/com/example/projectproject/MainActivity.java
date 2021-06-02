package com.example.projectproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user taps the Let's Learn-Button */
    public void GoToBodies(View view) {
        Intent intent = new Intent(this, PlanetTemplate.class);
        startActivity(intent);
        //setContentView(R.layout.PlanetTemplate);
    }

    /** Called when the user taps the About Button */
    public void GoToAbout(View view) {
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }
}