package com.example.enzo.projet.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.enzo.projet.R;
import com.github.chrisbanes.photoview.PhotoView;

import tools.DataBaseManager;

public class MapActivity extends AppCompatActivity {

    private TextView mProchainBatTxt;
    private Button mJySuisBtn;
    private Button mRetourMenu;
    private PhotoView mImageMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        int idBat = 0;
        String testLangue ="";
        Intent intent = getIntent();
        if(intent != null) {
            if(intent.hasExtra("id")) {
                idBat = intent.getIntExtra("id",idBat);
            }
            if(intent.hasExtra("langue")) {
                testLangue = intent.getStringExtra("langue");
            }
        }
        final String langue = testLangue;
        /*
         *Ouverture de la base de données et récupération du nom du bâtiment dont l'id est en intent
         */
        DataBaseManager db = new DataBaseManager(this);
        String nomBat = db.readBatiment(langue).get(idBat).getNom();
        db.close();

        mProchainBatTxt = findViewById(R.id.activity_map_prochain_bat_txt);
        mJySuisBtn = findViewById(R.id.activity_map_jysuis_btn);
        mRetourMenu = findViewById(R.id.activity_map_retour_main_btn);
        mImageMap = findViewById(R.id.activity_map_map_img);

        int [] imgBat = new int[]{R.drawable.mapnoir, R.drawable.mapbata, R.drawable.mapbate, R.drawable.mapbatg, R.drawable.mapbatf, R.drawable.mapbath, R.drawable.mapbati, R.drawable.mapbatb, R.drawable.mapbatc, R.drawable.mapbatd, R.drawable.mapbatm, R.drawable.mapbatk, R.drawable.mapbatl};
        /*
        *Changement du TextView du prochain bâtiment par le nom du bâtiment récupéré ci-dessus et changement de l'ImageView
         */
        mProchainBatTxt.setText("Prochain batiment : " + nomBat);
        mImageMap.setImageResource(imgBat[idBat]);

        if(langue.equals("A")) {
            mProchainBatTxt.setText("Next building : " + nomBat);
            mJySuisBtn.setText("I'm there");
            mRetourMenu.setText("Back to the menu");
        }
        else if (langue.equals("E")) {
            mProchainBatTxt.setText("Próximo edificio : " + nomBat);
            mJySuisBtn.setText("Estoy");
            mRetourMenu.setText("Volver al menu");
        }



        mJySuisBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                Intent BatimentActivityIntent = new Intent(MapActivity.this, BatimentActivity.class);
                int idBat = 0;
                if (intent != null) {
                    if (intent.hasExtra("id")) {
                        idBat = intent.getIntExtra("id",idBat);
                        BatimentActivityIntent.putExtra("id",idBat);
                    }
                    if (intent.hasExtra("langue")) {
                        BatimentActivityIntent.putExtra("langue",langue);
                    }

                }
                startActivity(BatimentActivityIntent);
            }
        });

        mRetourMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MainActivityIntent = new Intent(MapActivity.this, MainActivity.class);
                int idBat = 0;
                if (intent != null) {
                    if (intent.hasExtra("id")) {
                        idBat = intent.getIntExtra("id", idBat);
                        MainActivityIntent.putExtra("id", idBat);
                    }
                    if(intent.hasExtra("langue")) {
                        MainActivityIntent.putExtra("langue",langue);
                    }
                }
                startActivity(MainActivityIntent);
            }
        });
    }
}
