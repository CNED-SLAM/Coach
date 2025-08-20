package com.example.coach.presenter;

import android.content.Context;

import com.example.coach.contract.ICalculView;
import com.example.coach.data.ProfilDAO;
import com.example.coach.model.Profil;

import java.util.Date;

/**
 * 'presenter' dédié à la vue qui affiche le calcul de l'img
 */
public class CalculPresenter {
    private ICalculView vue;
    private ProfilDAO profilDAO;

    /**
     * Constructeur : valorise les propriétés
     * @param vue
     */
    public CalculPresenter(ICalculView vue, Context context) {
        this.vue = vue;
        this.profilDAO = new ProfilDAO(context);
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
        profilDAO.insertProfil(profil);

        // On pousse les résultats vers la vue
        vue.afficherResultat(
                profil.getImage(),
                profil.getImg(),
                profil.getMessage(),
                profil.normal()
        );
    }

    /**
     * Récupère le dernier profil dans la bdd et envoie les informations à la vue
     */
    public void chargerDernierProfil() {
        Profil profil = profilDAO.getLastProfil();
        if (profil != null) {
            vue.remplirChamps(
                    profil.getPoids(),
                    profil.getTaille(),
                    profil.getAge(),
                    profil.getSexe()
            );
        }
    }
}
