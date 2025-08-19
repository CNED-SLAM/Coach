package com.example.coach.contract;

/**
 * Contrat pour que le CalculPresenter puisse envoyer des informations à la vue
 */
public interface ICalculView {
    /**
     * Méthode permettant le transfert des résultats vers la vue
     * @param image nom du fichier drawable pour le smiley
     * @param img valeur de l'img calculé
     * @param message information textuelle correspondant à l'img
     * @param normal vrai si l'img est normal
     */
    void afficherResultat(String image, double img, String message, boolean normal);

    /**
     * Méthode permettant le transfert des informations d'origine, vers la vue
     * @param poids
     * @param taille
     * @param age
     * @param sexe
     */
    void remplirChamps(Integer poids, Integer taille, Integer age, Integer sexe);
}
