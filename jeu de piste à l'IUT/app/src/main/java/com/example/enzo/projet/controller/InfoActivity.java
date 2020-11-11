package com.example.enzo.projet.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.enzo.projet.R;

public class InfoActivity extends AppCompatActivity {

    private Button mRetourMain;
    private TextView mInfo;
    private TextView mInfoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        mRetourMain = findViewById(R.id.activity_info_retour_main_btn);
        mInfo = findViewById(R.id.activity_info_info);
        mInfoText = findViewById(R.id.activity_info_info_text);
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
                mInfoText.setText(getString(R.string.infoA));
                mRetourMain.setText("Return to menu");
                break;
            case "E" :
                mInfo.setText("Informaciones");
                mInfoText.setText(getString(R.string.infoE));
                mRetourMain.setText("Volver al menu\n");
                break;
        }
        mRetourMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MainActivityIntent = new Intent(InfoActivity.this, MainActivity.class);
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
