package com.example.intents;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Activity2 extends AppCompatActivity {

    ArrayList<String> pathfinder;
    int redirects;
    String str;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);

        pathfinder = getIntent().getStringArrayListExtra("Pathfinder");
        redirects = getIntent().getIntExtra("Redirects", 0);
        str = getIntent().getStringExtra("Str");

        TextView textView = findViewById(R.id.TV1);
        textView.setText(str);

        Button button21 = findViewById(R.id.But21);
        button21.setOnClickListener(view -> navigateToActivity("Activity1"));

        Button button22 = findViewById(R.id.But22);
        button22.setOnClickListener(view -> navigateToActivity("Activity3"));

        Button button23 = findViewById(R.id.But23);
        button23.setOnClickListener(view -> showPathAndFinish());
    }

    private void navigateToActivity(String targetActivity) {
        pathfinder.add(targetActivity);
        redirects++;

        Class<?> activityClass;
        switch (targetActivity) {
            case "Activity2":
                activityClass = Activity2.class;
                break;
            case "Activity3":
                activityClass = Activity3.class;
                break;
            default:
                activityClass = MainActivity.class;
        }

        Intent intent = new Intent(Activity2.this, activityClass);
        intent.putExtra("Str", str);
        intent.putStringArrayListExtra("Pathfinder", pathfinder);
        intent.putExtra("Redirects", redirects);
        startActivity(intent);
        finish();
    }


    private void showPathAndFinish() {
        redirects = 0;
        StringBuilder path = new StringBuilder();
        for (String activity : pathfinder) {
            path.append(activity).append(" -> ");
        }
        Toast.makeText(Activity2.this, path.toString(), Toast.LENGTH_SHORT).show();
        pathfinder.clear();
        finish();
    }
}
