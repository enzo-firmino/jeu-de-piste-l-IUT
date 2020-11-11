package com.example.enzo.projet.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.enzo.projet.R;


import tools.CustomPopUp;
import tools.DataBaseManager;

public class MainActivity extends AppCompatActivity {

    private Button mCommencerBtn;
    private Button mTutorielBtn;
    private Button mInfoBtn;
    private Activity mActivity = this;
    private TextView mTitre, mDescription;
    private Button mRecommencer;
    private Button mLangue;

    @Override
    /*
     Méthode qui se lance quand l'activité se crée, prend un bundle en paramétre qui sauvegarde l'état de l'activité
     @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        if (intent != null) {
            if (!getIntent().hasExtra("langue")) {
                /*
                *Création d'une nouvelle CustomPopUp qui va nous permettre d'afficher une checkbox
                 */
                CustomPopUp customPopUp = new CustomPopUp(mActivity);
                customPopUp.setTitle("Langue :");
                customPopUp.getCheckBox().setText("Francais");
                customPopUp.getCheckBox().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (customPopUp.getCheckBox().isChecked()) {
                            /*
                            *Création d'un nouvel intent dans lequel on va mettre la langue avec pour valeur "F"
                             */
                            Intent MainActivityIntent = new Intent(MainActivity.this, MainActivity.class);
                            MainActivityIntent.putExtra("langue", "F");
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
                    }
                });
                customPopUp.getCheckBox2().setText("Anglais");
                customPopUp.getCheckBox2().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (customPopUp.getCheckBox2().isChecked()) {
                            Intent MainActivityIntent = new Intent(MainActivity.this, MainActivity.class);
                            MainActivityIntent.putExtra("langue", "A");
                            if (intent.hasExtra("id")) {
                                int idBat = 0;
                                idBat = intent.getIntExtra("id", idBat);
                                MainActivityIntent.putExtra("id", idBat);
                                startActivity(MainActivityIntent);
                            }
                            else{
                                startActivity(MainActivityIntent);
                            }                        }
                    }
                });
                customPopUp.getCheckBox3().setText("Espagnol");
                customPopUp.getCheckBox3().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (customPopUp.getCheckBox3().isChecked()) {
                            Intent MainActivityIntent = new Intent(MainActivity.this, MainActivity.class);
                            MainActivityIntent.putExtra("langue", "E");
                            if (intent.hasExtra("id")) {
                                int idBat = 0;
                                idBat = intent.getIntExtra("id", idBat);
                                MainActivityIntent.putExtra("id", idBat);
                                startActivity(MainActivityIntent);
                            }
                            else{
                                startActivity(MainActivityIntent);
                            }                        }
                    }
                });
                customPopUp.build();
            }
        }

        String testLangue = "";
        if (intent != null) {
            if (intent.hasExtra("langue")) {
                testLangue = intent.getStringExtra("langue");
            }
        }
        final String langue = testLangue;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        *Associations des éléments du fichier XML activity_main.xml aux variables JAVA
         */
        mCommencerBtn = findViewById(R.id.activity_main_commencer_btn);
        mTutorielBtn = findViewById(R.id.activity_main_tutoriel_btn);
        mInfoBtn = findViewById(R.id.activity_main_info_btn);
        mTitre = findViewById(R.id.activity_main_titre);
        mDescription = findViewById(R.id.activity_main_description);
        mRecommencer = findViewById(R.id.activity_main_recommencer_btn);
        mLangue = findViewById(R.id.activity_main_langue_btn);

        if (intent != null) {
            if (intent.hasExtra("id")) {
                assert langue != null;
                if (langue.equals("F")) {
                    mCommencerBtn.setText("Continuer");
                    mRecommencer.setVisibility(View.VISIBLE);
                    mRecommencer.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent MapActivityIntent = new Intent(MainActivity.this, MapActivity.class);
                            MapActivityIntent.putExtra("langue", langue);
                            startActivity(MapActivityIntent);
                        }
                    });
                }
                if (langue.equals("A")) {
                    mCommencerBtn.setText("Resume");
                    mRecommencer.setText("Restart");
                    mRecommencer.setVisibility(View.VISIBLE);
                    mRecommencer.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent MapActivityIntent = new Intent(MainActivity.this, MapActivity.class);
                            MapActivityIntent.putExtra("langue", langue);
                            startActivity(MapActivityIntent);
                        }
                    });
                } else if (langue.equals("E")) {
                    mCommencerBtn.setText("Continuar");
                    mRecommencer.setText("Retomar");
                    mRecommencer.setVisibility(View.VISIBLE);
                    /*
                    *Mise en "écoute" du bouton recommencer avec la méthode setOnClickListener et implémentatino de la méthode
                    * onClick qui va lancer l'activité MapActivity
                     */
                    mRecommencer.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent MapActivityIntent = new Intent(MainActivity.this, MapActivity.class);
                            MapActivityIntent.putExtra("langue", langue);
                            startActivity(MapActivityIntent);
                        }
                    });
                }

            } else {
                assert langue != null;
                if (langue.equals("A")) {
                    mCommencerBtn.setText("Start");
                    mTutorielBtn.setText("Tutorial");
                    mInfoBtn.setText("Information");
                    mLangue.setText("Language");
                    mTitre.setText("Treasure hunt at IUT");
                    mDescription.setText("Discover IUT of Montpellier");
                } else if (langue.equals("E")) {
                    mCommencerBtn.setText("Comenzar");
                    mTutorielBtn.setText("Tutorial");
                    mLangue.setText("Lengua");
                    mInfoBtn.setText("Información");
                    mTitre.setText("Juego de pista en el IUT");
                    mDescription.setText("Descubre el IUT de Montpellier");
                }
            }

            System.out.println("test");
            DataBaseManager db = new DataBaseManager(this);
            db.getReadableDatabase();
            System.out.println("testé");
            db.vider();
            try {
                db.vider();
                db.readBatiment(langue).get(0);
            } catch (Exception e) {
                db.mettreAJour();
            }
            db.close();

            mCommencerBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (intent.hasExtra("id")) {
                        int idBat = 0;
                        Intent BatimentActivityIntent = new Intent(MainActivity.this, BatimentActivity.class);
                        idBat = intent.getIntExtra("id", idBat);
                        BatimentActivityIntent.putExtra("langue", langue);
                        BatimentActivityIntent.putExtra("id", idBat);
                        startActivity(BatimentActivityIntent);
                    }
                    else {
                        Intent MapActivityIntent = new Intent(MainActivity.this, MapActivity.class);
                        MapActivityIntent.putExtra("langue", langue);
                        startActivity(MapActivityIntent);
                    }
                }
            });

            mTutorielBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent TutorielActivityIntent = new Intent(MainActivity.this, TutorielActivity.class);
                    TutorielActivityIntent.putExtra("langue", langue);
                    if (intent.hasExtra("id")) {
                        int idBat = 0;
                        idBat = intent.getIntExtra("id", idBat);
                        TutorielActivityIntent.putExtra("id", idBat);
                        startActivity(TutorielActivityIntent);
                    }
                    else{
                        startActivity(TutorielActivityIntent);
                    }
                }
            });

            mInfoBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent InfoActivityIntent = new Intent(MainActivity.this, InfoActivity.class);
                    InfoActivityIntent.putExtra("langue", langue);
                    if (intent.hasExtra("id")) {
                        int idBat = 0;
                        idBat = intent.getIntExtra("id", idBat);
                        InfoActivityIntent.putExtra("id", idBat);
                        startActivity(InfoActivityIntent);
                    }
                    else{
                        startActivity(InfoActivityIntent);
                    }
                }
            });

            mLangue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent MainActivityIntent = new Intent(MainActivity.this, MainActivity.class);
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
}
