package com.example.coach.contract;

import java.util.List;

/**
 * Contrat pour que HistoPresenter puisse envoyer des informations à la vue
 */
public interface IHistoView extends IAllView {
    /**
     * Méthode permettant le transfert de la liste des profils pour affichage
     * @param profils
     */
    void afficherListe(List profils);
}
