package com.example.enzo.projet.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.enzo.projet.R;

public class EndActivity extends AppCompatActivity {

    private Button mRetourMenu;
    private TextView mBravoText;
    private TextView mTexte;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        mRetourMenu = findViewById(R.id.activity_end_retour_main_btn);
        mBravoText = findViewById(R.id.activity_end_bravo_txt);
        mTexte = findViewById(R.id.activity_end_txt);

        int test = 0;
        String testLangue ="";
        Intent intent = getIntent();
        if(intent != null) {
            if(intent.hasExtra("id")) {
                test = intent.getIntExtra("id",test);
            }
            if(intent.hasExtra("langue")) {
                testLangue = intent.getStringExtra("langue");
            }
        }
        final String langue = testLangue;

        if (langue.equals("A")){
            mTexte.setText(getString(R.string.endtxtA));
            mBravoText.setText(getString(R.string.bravotxtA));
            mRetourMenu.setText("Back to home");
        }
        else if (langue.equals("E")){
                mTexte.setText(getString(R.string.endtxtE));
                mBravoText.setText(getString(R.string.bravotxtA));
                mRetourMenu.setText("Retorno al inicio");
        }


        mRetourMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ActivityMainIntent = new Intent(EndActivity.this, MainActivity.class);
                ActivityMainIntent.putExtra("langue", langue);
                startActivity(ActivityMainIntent);
            }
        });

    }




}
