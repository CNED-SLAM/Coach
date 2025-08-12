package com.example.coach.contract;

public interface ICalculView {
    /**
     * Méthode permettant l'affichage du résultat du calcul de l'img
     * @param image nom du fichier drawable pour le smiley
     * @param img valeur de l'img calculé
     * @param message information textuelle correspondant à l'img
     * @param normal vrai si l'img est normal
     */
    void afficherResultat(String image, double img, String message, boolean normal);
}
