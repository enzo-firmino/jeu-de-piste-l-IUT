package com.example.enzo.projet.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.enzo.projet.R;

public class TutorielActivity extends AppCompatActivity {

    private Button mRetourMain;
    private TextView mTutoriel;
    private TextView mTutorielText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutoriel);

        mRetourMain = findViewById(R.id.activity_tutoriel_retour_main_btn);
        mTutoriel = findViewById(R.id.activity_tutoriel_tutoriel);
        mTutorielText = findViewById(R.id.activity_tutoriel_text);

        String testLangue = "";
        int test = 0;
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("langue")) {
                testLangue = intent.getStringExtra("langue");
            }
        }
        final String langue = testLangue;

        switch (testLangue){
            case "A" :
                mTutoriel.setText(getString(R.string.tutoA));
                mTutorielText.setText(getString(R.string.tutorialTextA));
                mRetourMain.setText("Return to menu");
                break;
            case "E" :
                mTutoriel.setText(getString(R.string.tutoE));
                mTutorielText.setText(getString(R.string.tutorialTextE));
                mRetourMain.setText("Volver al menu\n");
                break;
        }
        mRetourMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MainActivityIntent = new Intent(TutorielActivity.this, MainActivity.class);
                MainActivityIntent.putExtra("langue", langue);
                if (intent.hasExtra("id")) {
                    int idBat = 0;
                    idBat = intent.getIntExtra("id", idBat);
                    MainActivityIntent.putExtra("id", idBat);
                    startActivity(MainActivityIntent);
                }
                else{
                    startActivity(MainActivityIntent);
                }
            }
        });

    }
}
