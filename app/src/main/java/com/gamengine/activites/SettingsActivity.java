package com.gamengine.activites;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.gamengine.R;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    // Views
    LinearLayout viewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        // View find by id
        viewGroup = findViewById(R.id.view);
        createText(getResources().getString(R.string.setting_theme_title), 18);
        createText(getResources().getString(R.string.setting_theme_description), 14);
    }
    public void createText(String text, int size){
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setTextSize(size);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        viewGroup.addView(textView, layoutParams);
        LinearLayout.LayoutParams rlp = (LinearLayout.LayoutParams) textView.getLayoutParams();
        textView.setLayoutParams(rlp);
    }
}
