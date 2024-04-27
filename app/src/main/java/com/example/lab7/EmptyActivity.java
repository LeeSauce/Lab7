package com.example.lab7;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EmptyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_empty);

        // Gets values from the intent
        Bundle passedData = getIntent().getExtras();

        // instantiates a new object
        DetailsFragment fragment = new DetailsFragment();

        // passes the bundled data
        fragment.setArguments( passedData );

        // begins a transaction
        getSupportFragmentManager().beginTransaction().replace((R.id.frameLayout),
                fragment).commit();
    }
}