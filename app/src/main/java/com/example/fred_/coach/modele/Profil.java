package com.example.fred_.coach.modele;

import java.util.function.Function;

/**
 * Created by fred_ on 06/02/2017.
 */

public class Profil {
    // déclaration des constantes
    private static final Integer minFemme = 15; // maigre si en - dessous
    private static final Integer maxFemme = 30; // gros si au - dessus
    private static final Integer minHomme = 10; // maigre si en - dessous
    private static final Integer maxHomme = 25; // gros si au - dessous

    // déclaration des propriétés
    private Integer poids;
    private Integer taille;
    private Integer age;
    private Integer sexe;
    private float img; // contient le calcul de l'img
    private String message; // contient le message en lien avec l'image

    /**
     * constructeur des 4 propriétés : poids, taille, age, sexe
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

        calculIMG();
        resultIMG();
    }

    /**
     * getter de poids
     * @return
     */
    public Integer getPoids() {
        return poids;
    }

    /**
     * getter de taille
     * @return
     */
    public Integer getTaille() {
        return taille;
    }

    /**
     * getter de age
     * @return
     */
    public Integer getAge() {
        return age;
    }

    /**
     * getter de sexe
     * @return
     */
    public Integer getSexe() {
        return sexe;
    }

    /**
     * getter de img
     * @return
     */
    public float getImg() {
        return img;
    }

    /**
     * getter de message
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     * méthode qui calcule l'img et valorise la propriété de img
     */
    private void calculIMG() {
        img = (float)((1.2 * poids / (((float)(taille * taille)) / 10000)) + (0.23 * age) - (10.83 * sexe) - 5.4);
    }

    /**
     * méthode qui valorise la propriété message
     */
    private void resultIMG() {
        // test pour les femmes
        if (sexe == 0) {
            if (img < 0.15) {
                message = "IMG trop faible";
            } else if (img > 0.15 && img < 0.3) {
                message = "IMG normal";
            } else {
                message = "IMG trop élevé";
            }
        }
        // test pour les hommes
        if (sexe == 1) {
            if (img < 0.1) {
                message = "IMG trop faible";
            } else if (img > 0.1 && img < 0.25) {
                message = "IMG normal";
            } else {
                message = "IMG trop élevé";
            }
        }
    }
}
