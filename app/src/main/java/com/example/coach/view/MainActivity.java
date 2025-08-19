package com.example.coach.view;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.coach.R;
import com.example.coach.contract.ICalculView;
import com.example.coach.presenter.CalculPresenter;

/**
 * Activity qui permet le calcul de l'img
 */
public class MainActivity extends AppCompatActivity implements ICalculView {

    private EditText txtPoids, txtTaille, txtAge;
    private RadioButton rdHomme;
    private RadioButton rdFemme;
    private TextView lblIMG;
    private ImageView imgSmiley;
    private Button btnCalc;
    private CalculPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
    }

    /**
     * Traitements nécessaires dès la création de l'activity
     */
    private void init(){
        chargeObjetsGraphiques();
        presenter = new CalculPresenter(this);
        btnCalc.setOnClickListener(v -> btnCalc_clic());
    }

    /**
     * Récupération des objets graphiques
     */
    private void chargeObjetsGraphiques(){
        txtPoids = findViewById(R.id.txtPoids);
        txtTaille = findViewById(R.id.txtTaille);
        txtAge = findViewById(R.id.txtAge);
        rdHomme = findViewById(R.id.rdHomme);
        rdFemme = findViewById(R.id.rdFemme);
        lblIMG = findViewById(R.id.lblIMG);
        imgSmiley = findViewById(R.id.imgSmiley);
        btnCalc = findViewById(R.id.btnCalc);
    }

    /**
     * Traitements réalisés lors du clic sur le bouton Calculer
     */
    private void btnCalc_clic(){
        Integer poids = 0, taille = 0, age = 0, sexe = 0;
        try {
            poids = Integer.parseInt(txtPoids.getText().toString());
            taille = Integer.parseInt(txtTaille.getText().toString());
            age = Integer.parseInt(txtAge.getText().toString());
        } catch (Exception ignored) {}

        if (rdHomme.isChecked()) {
            sexe = 1;
        }

        if (poids == 0 || taille == 0 || age == 0) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
        } else {
            presenter.creerProfil(poids, taille, age, sexe);
        }
    }

    /**
     * Méthode permettant l'affichage du résultat du calcul de l'img
     * @param image nom du fichier drawable pour le smiley
     * @param img valeur de l'img calculé
     * @param message information textuelle correspondant à l'img
     * @param normal vrai si l'img est normal
     */
    @Override
    public void afficherResultat(String image, double img, String message, boolean normal) {
        int imageId = getResources().getIdentifier(image, "drawable", getPackageName());
        if (imageId != 0) {
            imgSmiley.setImageResource(imageId);
        } else {
            imgSmiley.setImageResource(R.drawable.normal);
        }

        String texte = String.format("%.01f", img) + " : IMG " + message;
        lblIMG.setText(texte);
        lblIMG.setTextColor(normal ? Color.GREEN : Color.RED);
    }
}