package com.example.projectproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    /** Called when the user taps the InfoText */
    public void LargeZombie(View view) {
        setContentView(R.layout.activity_about_largezombie);
    }

    /** Called when the user taps the InfoText */
    public void SmallZombie(View view) {
        setContentView(R.layout.activity_about);
    }
}
