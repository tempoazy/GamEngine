package com.gamengine.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.gamengine.GamEngine;
import com.gamengine.R;
import com.gamengine.activites.SettingsActivity;
import com.gamengine.classes.FileUtil;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.search.SearchBar;
import com.google.android.material.search.SearchView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {

    LinearLayout newP;
    TextView text1;
    SearchBar sBar;
    SearchView sView;
    DrawerLayout drawer;
    NavigationView nav_view;
    ListView list1, list2;
    Intent intent = new Intent();
    String path, projProjectName, projPackageName, projIcon, data;
    int mode = 0;
    String BaseDir = "/storage/emulated/0/.Gampiot/GamEngine/Files/";
    HashMap<String, Object> map = new HashMap<>();
    Context ctx;
    ArrayList<String> listString = new ArrayList<>();
    ArrayList<HashMap<String, Object>> listMap = new ArrayList<>();
    ArrayList<HashMap<String, Object>> listMap2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        newP = findViewById(R.id.newP);
        text1 = findViewById(R.id.txt1);
        sBar = findViewById(R.id.search_bar);
        sView = findViewById(R.id.search_view);
        list1 = findViewById(R.id.list_projects_1);
        list2 = findViewById(R.id.list_projects_2);
        drawer = findViewById(R.id.drawer);
        nav_view = findViewById(R.id.nav_view);
        try {
            oCreate();
            GamEngine.loadProjects(ctx);
        } catch (Exception e) {
            GamEngine.errorDialog("Un error ocurred", e.toString(), this);
        }
        newP.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        oClickCreateProject();
                    }
                });
    }

    private void oCreate() {
        GamEngine.loadProjects(HomeActivity.this);
        sBar.inflateMenu(R.menu.searchbar_menu);
        sBar.setOnMenuItemClickListener(
                menuItem -> {
                    drawer.openDrawer(GravityCompat.START);
                    return true;
                });
        sView.getEditText()
                .setOnEditorActionListener(
                        (v, actionId, event) -> {
                            GamEngine.Search(ctx, sView.getText().toString(), listMap2);
                            return false;
                        });
        nav_view.bringToFront();
        nav_view.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        if (item.getItemId() == R.id.telegram) {
                            GamEngine.openURL(ctx, "https://t.me/GamEngineProject");
                            return true;
                        } else if (item.getItemId() == R.id.whatsapp) {
                            GamEngine.openURL(
                                    ctx, "https://chat.whatsapp.com/DuO2FByCMm74PDdTOB5SnA");
                            return true;
                        } else if (item.getItemId() == R.id.settings) {
                             Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
                             startActivity(intent);
                            return true;
                        } else {
                            return false;
                        }
                    }
                });
    }

    private void oClickCreateProject() {

        final BottomSheetDialog create_project_dialog = new BottomSheetDialog(this);
        final BottomSheetBehavior create_project_dialogB;
        View v;
        v = getLayoutInflater().inflate(R.layout.content_bottom_sheet, null);
        create_project_dialog.setContentView(v);
        create_project_dialog.setCancelable(true);
        // Find views
        TextInputEditText projName = v.findViewById(R.id.projName);
        TextInputEditText pkgName = v.findViewById(R.id.pkgName);
        Button createProj = v.findViewById(R.id.createProj);
        TextView txt1 = v.findViewById(R.id.t1);

        try {
            GamEngine.oChanged(HomeActivity.this, projName);
            createProj.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            projProjectName = projName.getText().toString();
                            projPackageName = pkgName.getText().toString();
                            projIcon = path;
                            map.put("project_name", projProjectName);
                            map.put("project_package_name", projPackageName);
                            map.put("project_icon", "null");
                            String json_code = new Gson().toJson(map);
                            FileUtil.writeFile(
                                    BaseDir + "/Projects/" + projProjectName + "/Basics/info.json",
                                    json_code);
                            toast(json_code);
                            GamEngine.loadProjects(ctx);
                            create_project_dialog.dismiss();
                        }
                    });

            create_project_dialog.show();
        } catch (Exception e) {
            GamEngine.errorDialog("Un error ocurred", e.toString(), this);
        }
    }

    private void toast(String s) {
        Toast.makeText(getApplicationContext(), s, 4000).show();
    }
}
