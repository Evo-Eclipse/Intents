package com.example.intents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<String> PATHFINDER = new ArrayList<>();
    public static int REDIRECTS = 0;
    String STR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> pathfinderExtra = getIntent().getStringArrayListExtra("Pathfinder");
        if (pathfinderExtra != null) {
            PATHFINDER = pathfinderExtra;
        } else {
            PATHFINDER = new ArrayList<>();
        }

        //PATHFINDER = getIntent().getStringArrayListExtra("Pathfinder");
        REDIRECTS = getIntent().getIntExtra("Redirects", 0);
        STR = getIntent().getStringExtra("Str");

        EditText editText = findViewById(R.id.ET);

        if (STR != null) {
            editText.setText(STR);
        }

        Button button11 = findViewById(R.id.But11);
        button11.setOnClickListener(view -> {
            PATHFINDER.add("Activity2");
            REDIRECTS++;
            Intent intent = new Intent(MainActivity.this, Activity2.class);
            intent.putExtra("Str", editText.getText().toString());
            intent.putStringArrayListExtra("Pathfinder", PATHFINDER);
            intent.putExtra("Redirects", REDIRECTS);
            startActivity(intent);
        });

        Button button12 = findViewById(R.id.But12);
        button12.setOnClickListener(view -> {
            PATHFINDER.add("Activity3");
            REDIRECTS++;
            Intent intent = new Intent(MainActivity.this, Activity3.class);
            intent.putExtra("Str", editText.getText().toString());
            intent.putStringArrayListExtra("Pathfinder", PATHFINDER);
            intent.putExtra("Redirects", REDIRECTS);
            startActivity(intent);
        });

        if (REDIRECTS > 0) {
            Button button13 = findViewById(R.id.But13);
            button13.setOnClickListener(view -> {
                REDIRECTS = 0;
                StringBuilder path = new StringBuilder();
                for (String activity : PATHFINDER) {
                    path.append(activity).append(" -> ");
                }
                Toast.makeText(MainActivity.this, path.toString(), Toast.LENGTH_SHORT).show();
                PATHFINDER.clear();
            });
        }
    }
}
