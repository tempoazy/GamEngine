package com.gamengine.activities;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;

//Local
import com.gamengine.GamEngine;
import com.gamengine.R;

public class EditorActivity extends AppCompatActivity {
    
    ListView list1;
    
    String path, data, mode;
    ArrayList<String> listString = new ArrayList<>();
    ArrayList<HashMap<String, Object>> listMap = new ArrayList<>();
    HashMap<String, Object> map = new HashMap<>();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        oCreate();
        
    }
    private void oCreate() {
    	try {
            setContentView(R.layout.activity_editor);
        	path = getIntent().getStringExtra("path");
            setTitle(Uri.parse(path).getLastPathSegment());
        } catch(Exception e) {
        	GamEngine.errorDialog("Un error ocurred", e.toString(), this);
        }
    }
}
