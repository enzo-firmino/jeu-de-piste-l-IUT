package tools;

import android.app.Activity;
import android.app.Dialog;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.enzo.projet.R;

public class CustomPopUp extends Dialog {

    // Attribut
    private String title;
    private CheckBox mCheckBox,mCheckBox2,mCheckBox3;
    private TextView titleView;

    // Constructeur
    public CustomPopUp(Activity activity) {
        super(activity, R.style.Theme_AppCompat);
        setContentView(R.layout.pop_up);
        title = "Titre";

        mCheckBox = findViewById(R.id.pop_up_checbox);
        mCheckBox2 = findViewById(R.id.pop_up_checbox2);
        mCheckBox3 = findViewById(R.id.pop_up_checkbox3);
        titleView = findViewById(R.id.pop_up_txt);
    }

    // Getter et Setter
    public void setTitle(String title) {
        this.title = title;
    }

    public CheckBox getCheckBox() {
        return mCheckBox;
    }

    public CheckBox getCheckBox2() {
        return mCheckBox2;
    }

    public CheckBox getCheckBox3() {
        return mCheckBox3;
    }

    // Méthode construisant la popUp
    public void build() {
        // affiche la pop up
        show();
        // affecte un titre à la pop up
        titleView.setText(title);
    }
}
