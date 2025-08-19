package com.example.coach.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.coach.contract.ICalculView;
import com.example.coach.model.Profil;
import com.google.gson.Gson;

import java.util.Date;

/**
 * 'presenter' dédié à la vue qui affiche le calcul de l'img
 */
public class CalculPresenter {
    private ICalculView vue;
    private static final String NOM_FIC = "coach_fic";
    private static final String PROFIL_CLE = "profil_json";
    private Gson gson;
    private SharedPreferences prefs;

    /**
     * Constructeur : valorise la propriété qui permet d'accéder à la vue
     * @param vue
     */
    public CalculPresenter(ICalculView vue, Context context) {
        this.vue = vue;
        this.prefs = context.getSharedPreferences(NOM_FIC, Context.MODE_PRIVATE);
        this.gson = new Gson();
    }

    /**
     * Crée le profil avec les informations reçues de la vue
     * Renvoie à la vue les informations à afficher
     * @param poids
     * @param taille
     * @param age
     * @param sexe
     */
    public void creerProfil(Integer poids, Integer taille, Integer age, Integer sexe) {
        Profil profil = new Profil(poids, taille, age, sexe, new Date());
        sauvegarderProfil(profil);

        // On pousse les résultats vers la vue
        vue.afficherResultat(
                profil.getImage(),
                profil.getImg(),
                profil.getMessage(),
                profil.normal()
        );
    }

    /**
     * Enregistre le profil dans un fichier
     * @param profil
     */
    private void sauvegarderProfil(Profil profil){
        String json = gson.toJson(profil);
        prefs.edit().putString(PROFIL_CLE, json).apply();
    }

    /**
     * Récupère le profil enregistré et envoie ses informations vers la vue
     */
    public void chargerProfil(){
        String json = prefs.getString(PROFIL_CLE, null);
        if (json != null){
            Profil profil = gson.fromJson(json, Profil.class);

            // on pousse les informations vers la vue
            vue.remplirChamps(
                    profil.getPoids(),
                    profil.getTaille(),
                    profil.getAge(),
                    profil.getSexe()
            );
        }
    }
}
