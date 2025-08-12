package com.example.coach.model;

public class Profil {
    // Constantes pour les bornes IMG
    private static final int minFemme = 25;
    private static final int maxFemme = 30;
    private static final int minHomme = 15;
    private static final int maxHomme = 20;

    /**
     * messages à afficher suivant la valeur de l'img
     */
    private static final String[] message = {"trop faible", "normal", "trop élevé"};
    /**
     * nom du fichier drawable correspondant à la valeur de l'img
     */
    private static final String[] image = {"maigre", "normal", "graisse"};

    // Propriétés d'entrée
    private Integer poids;
    private Integer taille;
    private Integer age;
    private Integer sexe;
    private double img;
    private int indice;


    /**
     * Constructeur : valorise les propriétés
     * @param poids
     * @param taille
     * @param age
     * @param sexe
     */
    public Profil(Integer poids, Integer taille, Integer age, Integer sexe) {
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.sexe = sexe;
        this.img = calculImg();
        this.indice = calcultIndice();
    }

    /**
     *
     * @return nom du fichier drawable qui correspond à l'img
     */
    public String getImage(){
        return image[indice];
    }

    /**
     *
     * @return message à afficher qui correspond à l'img
     */
    public String getMessage(){
        return message[indice];
    }

    /**
     *
     * @return vrai si l'img est normal
     */
    public boolean normal(){
        return indice == 1;
    }

    /**
     *
     * @return valeur de l'img
     */
    public double getImg() {
        return img;
    }

    /**
     * Calcule l'img
     * @return résultat du calcul de l'img
     */
    private double calculImg(){
        double tailleMetres = taille / 100.0;
        return (1.2 * poids / (tailleMetres * tailleMetres))
                + (0.23 * age)
                - (10.83 * sexe)
                - 5.4;
    }

    /**
     * Calcule l'indice correspondant à l'img
     * pour se positionner dans la bonne case des tableaux message et image
     * @return valeur de l'indice
     */
    private int calcultIndice() {
        int min = (sexe == 0) ? minFemme : minHomme;
        int max = (sexe == 0) ? maxFemme : maxHomme;
        if (img > max) {
            return 2; // au-dessus de la norme
        }
        if (img >= min) {
            return 1; // dans la norme
        }
        return 0; // en dessous
    }

}
