package com.gamengine.activities;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

// Local imports
import com.gamengine.GamEngine;
import com.gamengine.R;

public class MainActivity extends AppCompatActivity {

    private LinearLayout nextButton;
    private Context context; 
    private ProgressBar progressBar;
    private TextView infoText;
    private SharedPreferences prefs;  

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find views by ID
        nextButton = findViewById(R.id.next);
        progressBar = findViewById(R.id.bar);
        infoText = findViewById(R.id.text);

        // Check if first app launch using shared preferences
        prefs = getSharedPreferences("sp", Activity.MODE_PRIVATE);
        if (!prefs.getBoolean("firstLaunch", true)) {
            launchHomeActivity();
            return; 
        }

        // Set click listener for next Button
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleNextClick();
            }
        });
    }

    private void handleNextClick() {
        try {
            updateProgressAndText();
        } catch (Exception e) {
            GamEngine.errorDialog("An error occurred", e.toString(), context);
        }
    }

    private void updateProgressAndText() {
        int progress = progressBar.getProgress();
        progressBar.setProgress(progress + 33);

        ObjectAnimator animation = ObjectAnimator.ofFloat(infoText, "alpha", 0.0f, 1.0f);
        animation.setDuration(400);

        switch (progress) {
            case 33:
                animation.start();
                infoText.setText(R.string.main_title_create_amazing_games);
                break;
            case 66:
                animation.start();
                infoText.setText(R.string.intro_text_html_javascript_css);
                break;
            case 99:
                animation.start();
                infoText.setText(R.string.all_ready_text);
                break;
        }

        if (progress == 100) {
            // Update shared preferences on app launch completion
            prefs.edit().putBoolean("firstLaunch", false).apply();

            launchHomeActivity();
            finish();
        }
    }

    private void launchHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
