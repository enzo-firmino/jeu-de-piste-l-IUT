package model;

import androidx.appcompat.app.AppCompatActivity;

public class Batiment extends AppCompatActivity {

    private int id;
    private String mNom;
    private String mDescription;


    public Batiment(int id, String nom, String description) {
        this.id = id;
        mNom = nom;
        mDescription = description;
    }

    public String getNom() {
        return mNom;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return mDescription;
    }


}
