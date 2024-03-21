package com.gamengine;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import com.bumptech.glide.load.engine.Resource;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;

// Local
import com.gamengine.activities.EditorActivity;
import com.gamengine.classes.FileUtil;

public class GamEngine {

    private static ArrayList<String> listString = new ArrayList<>();
    private static ArrayList<HashMap<String, Object>> listMap = new ArrayList<>();
    private static ArrayList<HashMap<String, Object>> listMap2 = new ArrayList<>();
    private static String data;

    public static void errorDialog(String title, String message, Context context) {
        MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(context);
        dialog.setTitle(title);
        dialog.setMessage("An error occurred in the application, send the error to us!");
        dialog.setPositiveButton(
                "Send",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface di, int it) {}
                });
        dialog.setNegativeButton(
                "Show Error",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface di, int it) {
                        dialog.setTitle(message);
                    }
                });
        dialog.setNeutralButton(
                "Exit",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface di, int it) {}
                });
    }

    public static void OpenProject(Context ctx, String path) {
        Intent intent = new Intent(ctx, EditorActivity.class);
        intent.putExtra("path", path);
        ctx.startActivity(intent);
    }

    public static void openURL(Context ctx, String url) {
        try {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            ctx.startActivity(intent);
        } catch (Exception e) {
            errorDialog("Un error ocurred", e.toString(), ctx);
        }
    }

    public static String loadProjects(Context ctx) {
        String BaseDir = "/storage/emulated/0/.Gampiot/GamEngine/Files/";
        listMap.clear();
        FileUtil.listDir(BaseDir + "/Projects/", listString);
        int position = 0;
        for (int _repeat14 = 0; _repeat14 < (int) (listString.size()); _repeat14++) {
            {
                HashMap<String, Object> i = new HashMap<>();
                i.put("path", listString.get((int) (position)));
                listMap.add(i);
                listMap2.add(i);
            }

            position++;
            data = new Gson().toJson(listMap);
        }
        return data;
    }

    public static void oChanged(Context ctx, EditText view) {
        view.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void onTextChanged(CharSequence p1, int p2, int p3, int p4) {
                        if (FileUtil.isExistFile(p1.toString())) {
                            view.setError(
                                    ctx.getResources()
                                            .getString(R.string.alert_project_already_exists));
                        }
                    }

                    @Override
                    public void beforeTextChanged(CharSequence p1, int p2, int p3, int p4) {}

                    @Override
                    public void afterTextChanged(Editable p1) {}
                });
    }

    public static String Search(Context ctx, String textToSearch, ArrayList<HashMap<String, Object>> list) {
            int p = list.size() - 1;
            for (int _repeat18 = 0; _repeat18 < (int) (list.size()); _repeat18++) {
                if (list.get((int) p).get("path").toString().toLowerCase().contains(textToSearch.toLowerCase())) {
                } else {
                    list.remove((int) (p));
                    if (list.size() == 0) {
                        loadProjects(ctx);
                    }
                }
                p--;
            }
        return new Gson().toJson(list);
    }
}
