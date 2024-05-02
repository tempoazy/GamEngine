package com.gamengine.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gamengine.GamEngine; 
import com.gamengine.R;
import com.gamengine.classes.FileUtil;

import com.google.android.material.card.MaterialCardView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;

public class ListSearchAdapter extends BaseAdapter {
    
    private Context ctx;

    private ArrayList<HashMap<String, Object>> data;

    public ListSearchAdapter(ArrayList<HashMap<String, Object>> arr, Context a) {
        data = arr;
        ctx=a;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public HashMap<String, Object> getItem(int index) {
        return data.get(index);
    }

    @Override
    public long getItemId(int index) {
        return index;
    }

    @Override
    public View getView(final int position, View v, ViewGroup container) {
        LayoutInflater inflater = (LayoutInflater) ((Context) ctx).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.content_layout_project, null);

        MaterialCardView linear_background = view.findViewById(R.id.linear_background);
        TextView projname = view.findViewById(R.id.projname);
        TextView projpackage = view.findViewById(R.id.projpackage);

        String mPath = data.get(position).get("path").toString();
        projname.setText(Uri.parse(mPath).getLastPathSegment());
        HashMap<String, Object> map = new Gson()
                .fromJson(FileUtil.readFile(mPath + "/Basics/info.json"),
                        new TypeToken<HashMap<String, Object>>() {}.getType());
        projpackage.setText(map.get("project_package_name").toString());

        linear_background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GamEngine.OpenProject((Context) ctx, mPath);
            }
        });

        return view;
    }
}
