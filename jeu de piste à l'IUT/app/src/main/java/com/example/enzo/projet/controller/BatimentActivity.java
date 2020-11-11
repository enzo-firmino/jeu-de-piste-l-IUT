package com.example.enzo.projet.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.enzo.projet.R;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.List;
import java.util.Random;

import model.Batiment;
import model.Enigme;
import tools.CustomPopUp;
import tools.DataBaseManager;

public class BatimentActivity extends AppCompatActivity {

    private TextView mNomBatiment;
    private PhotoView mImgBatiment;
    private TextView mDescriptionBatiment;
    private Button mProchainBatBtn;
    private Button mRetourMenu;
    private Activity mActivity = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batiment);


        mNomBatiment = findViewById(R.id.activity_batiment_nom_txt);
        mImgBatiment = findViewById(R.id.activity_batiment_image_img);
        mDescriptionBatiment = findViewById(R.id.activity_batiment_description_txt);
        mProchainBatBtn = findViewById(R.id.activity_batiment_prochain_bat_btn);
        mRetourMenu = findViewById(R.id.activity_batiment_retour_main_btn);

        Intent intent = getIntent();
        String testLangue = "";
        int test = 0;
        if (intent != null) {
            if (intent.hasExtra("id")) {
                test = intent.getIntExtra("id",test);
            }
            if (intent.hasExtra("langue")) {
                testLangue = intent.getStringExtra("langue");
            }
        }
        final String langue = testLangue;
        final int renvoie = test;


        DataBaseManager db = new DataBaseManager(this);
        List<Batiment> bat = db.readBatiment(langue);
        mNomBatiment.setText("Bâtiment " + bat.get(test).getNom());
        mDescriptionBatiment.setText(bat.get(test).getDescription());
        mDescriptionBatiment.setTextSize(1,30);

        final int sizeListBat = db.count(langue);
        Random random = new Random();
        List<Enigme> listeEnigme = db.readEnigme(langue, test);//La fonction renvoie une liste d'enigme comme peut y avoir plusieurs enigmes par bat, faut faire le choix ici
        Enigme enigme = listeEnigme.get(0); //
        String enigmeNom = enigme.getNom();
        String reponse1 = enigme.getReponse1();
        String reponse2 = enigme.getReponse2();
        String reponse3 = enigme.getReponse3();
        int reponse = enigme.getReponse();
        db.close();


        int [] imgBat = new int[]{R.drawable.bata, R.drawable.bate, R.drawable.bata, R.drawable.batf, R.drawable.bath, R.drawable.batij, R.drawable.batb, R.drawable.batc, R.drawable.batd, R.drawable.batm, R.drawable.batk, R.drawable.batl};

        mImgBatiment.setImageResource(imgBat[test]);

        if(test == sizeListBat -1) {
            if (langue.equals("F")) mProchainBatBtn.setText("Fin de la visite");
        }

        if (langue.equals("A")) {
            mNomBatiment.setText("Building " + bat.get(test).getNom());
            mDescriptionBatiment.setText(bat.get(test).getDescription());
            mProchainBatBtn.setText("Next building");
            mRetourMenu.setText("Back to the menu");
            if(test == sizeListBat -1){
                mProchainBatBtn.setText("End of the visit");
            }
        }
        else if (langue.equals("E")) {
            mNomBatiment.setText("Edificio " + bat.get(test).getNom());
            mDescriptionBatiment.setText(bat.get(test).getDescription());
            mProchainBatBtn.setText("Próximo edificio");
            mRetourMenu.setText("Volver al menu");
            if(test == sizeListBat -1){
                mProchainBatBtn.setText("Fin de la visita");
            }
        }



        mProchainBatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (renvoie != sizeListBat -1) {
                    Intent ActivityMapIntent = new Intent(BatimentActivity.this, MapActivity.class);
                    ActivityMapIntent.putExtra("id", renvoie+1);
                    ActivityMapIntent.putExtra("langue",langue);
                    /*
                    * Création d'une checkbox pour afficher les énigmes
                     */
                    CustomPopUp enigme = new CustomPopUp(mActivity);
                    enigme.setTitle(enigmeNom);
                    enigme.getCheckBox().setText(reponse1);
                    enigme.getCheckBox().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (enigme.getCheckBox().isChecked()) {
                                if (reponse == 1) {
                                    startActivity(ActivityMapIntent);
                                } else {
                                    Context context = getApplicationContext();
                                    CharSequence text;
                                    if (langue.equals("E")) {
                                        text = "Mala respuesta !";
                                    } else if (langue.equals("A")) {
                                        text = "Bad answer !";
                                    } else{
                                        text = "Mauvaise réponse !";
                                    }
                                    int duration = Toast.LENGTH_SHORT;
                                    Toast toast = Toast.makeText(context, text, duration);
                                    toast.show();
                                    enigme.getCheckBox().setChecked(false);
                                }
                            }
                        }
                    });
                    enigme.getCheckBox2().setText(reponse2);
                    enigme.getCheckBox2().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (enigme.getCheckBox2().isChecked()) {
                                if (reponse == 2) {
                                    startActivity(ActivityMapIntent);
                                } else {
                                    Context context = getApplicationContext();
                                    CharSequence text;
                                    if (langue.equals("E")) {
                                        text = "Mala respuesta !";
                                    } else if (langue.equals("A")) {
                                        text = "Bad answer !";
                                    } else{
                                        text = "Mauvaise réponse !";
                                    }                                    int duration = Toast.LENGTH_SHORT;
                                    Toast toast = Toast.makeText(context, text, duration);
                                    toast.show();
                                    enigme.getCheckBox2().setChecked(false);
                                }
                            }
                        }
                    });
                    enigme.getCheckBox3().setText(reponse3);
                    enigme.getCheckBox3().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (enigme.getCheckBox3().isChecked()) {
                                if (reponse == 3) {
                                    startActivity(ActivityMapIntent);
                                } else {
                                    Context context = getApplicationContext();
                                    CharSequence text;
                                    if (langue.equals("E")) {
                                        text = "Mala respuesta !";
                                    } else if (langue.equals("A")) {
                                        text = "Bad answer !";
                                    } else{
                                        text = "Mauvaise réponse !";
                                    }                                    int duration = Toast.LENGTH_SHORT;
                                    Toast toast = Toast.makeText(context, text, duration);
                                    toast.show();
                                    enigme.getCheckBox3().setChecked(false);
                                }
                            }
                        }
                    });
                    enigme.build();
                }
                else {
                    Intent ActivityEndIntent = new Intent(BatimentActivity.this, EndActivity.class);
                    ActivityEndIntent.putExtra("langue",langue);
                    startActivity(ActivityEndIntent);
                }
            }
        });


        mRetourMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MainActivityIntent = new Intent(BatimentActivity.this, MainActivity.class);
                MainActivityIntent.putExtra("id", renvoie);
                MainActivityIntent.putExtra("langue",langue);
                startActivity(MainActivityIntent);
            }
        });

    }
}

