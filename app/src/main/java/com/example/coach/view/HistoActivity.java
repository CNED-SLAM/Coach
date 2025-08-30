package com.example.coach.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coach.R;
import com.example.coach.contract.IHistoView;
import com.example.coach.model.Profil;
import com.example.coach.presenter.HistoPresenter;

import java.util.List;

/**
 * Activity pour afficher l'historique
 */
public class HistoActivity extends AppCompatActivity implements IHistoView {

    private HistoPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_histo);
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
        presenter = new HistoPresenter(this);
        presenter.chargerProfils();
    }

    /**
     * Méthode permettant le transfert de la liste des profils pour affichage
     *
     * @param profils
     */
    @Override
    public void afficherListe(List profils) {
        if (profils != null){
            RecyclerView lstHisto = (RecyclerView) findViewById(R.id.lstHisto);
            HistoListAdapter adapter = new HistoListAdapter(
                    profils,
                    HistoActivity.this,
                    new HistoListAdapter.IOnProfilClickListener() {
                @Override
                public void onProfilClick(Profil profil) {
                    Intent intent = new Intent(HistoActivity.this, CalculActivity.class);
                    // passer le profil dans l’intent (via Serializable)
                    intent.putExtra("profil", profil);
                    startActivity(intent);
                }
            });
            lstHisto.setAdapter(adapter);
            lstHisto.setLayoutManager(new LinearLayoutManager(HistoActivity.this));
        }
    }

    /**
     * Méthode permettant d'afficher un message de type Toast
     *
     * @param message
     */
    @Override
    public void afficherMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}