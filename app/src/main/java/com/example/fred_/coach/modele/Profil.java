package com.example.fred_.coach.modele;

// import java.util.function.Function;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by fred_ on 06/02/2017.
 */

public class Profil implements Serializable {
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
    private Date dateMesure;

    /**
     * constructeur des 5 propriétés : poids, taille, age, sexe, dateMes
     * @param poids
     * @param taille
     * @param age
     * @param sexe
     * @param dateMes
     */
    public Profil(Integer poids, Integer taille, Integer age, Integer sexe, Date dateMes) {
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.sexe = sexe;
        this.dateMesure = dateMes;

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
     * getter de dateMesure
     * @return
     */
    public Date getDateMesure() {
        return dateMesure;
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
