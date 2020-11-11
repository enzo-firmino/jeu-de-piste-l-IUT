package model;

import androidx.appcompat.app.AppCompatActivity;

public class Enigme extends AppCompatActivity {

    private int id;
    private String mNom;
    private String mReponse1;
    private String mReponse2;
    private String mReponse3;
    private int mReponse;

    public Enigme(int id, String nom, String reponse1, String reponse2, String reponse3, int reponse) {
        this.id = id;
        mNom = nom;
        mReponse1 = reponse1;
        mReponse2 = reponse2;
        mReponse3 = reponse3;
        mReponse = reponse;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        mNom = nom;
    }

    public void setReponse1(String reponse1) {
        mReponse1 = reponse1;
    }

    public void setReponse2(String reponse2) {
        mReponse2 = reponse2;
    }

    public void setReponse3(String reponse3) {
        mReponse3 = reponse3;
    }

    public void setReponse(int reponse) {
        mReponse = reponse;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return mNom;
    }

    public String getReponse1() {
        return mReponse1;
    }

    public String getReponse2() {
        return mReponse2;
    }

    public String getReponse3() {
        return mReponse3;
    }

    public int getReponse() {
        return mReponse;
    }
}
