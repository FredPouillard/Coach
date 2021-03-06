package com.example.fred_.coach.modele;

// import java.util.function.Function;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        float tailleEnM = ((float)taille)/100 ;
        this.img = (float) ((1.2 * poids / (tailleEnM * tailleEnM)) + (0.23 * age) - (10.83 * sexe) - 5.4);
    }

    /**
     * méthode qui valorise la propriété message
     */
    private void resultIMG() {
        // test pour les femmes
        if (sexe == 0) {
            if (img < minFemme) {
                message = "IMG trop faible";
            } else if (img > maxFemme) {
                message = "IMG trop élevé";
            } else {
                message = "IMG normal";
            }
        }
        // test pour les hommes
        if (sexe == 1) {
            if (img < minHomme) {
                message = "IMG trop faible";
            } else if (img > maxHomme) {
                message = "IMG trop élevé";
            } else {
                message = "IMG normal";
            }
        }
    }

    /**
     * méthode qui convertit un objet en JSON
     * @return
     */
    public JSONArray convertToJSONArray() {
        List laListe = new ArrayList();
        laListe.add(dateMesure);
        laListe.add(poids);
        laListe.add(taille);
        laListe.add(age);
        laListe.add(sexe);
        return new JSONArray(laListe);
    }
}
