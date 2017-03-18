package com.example.fred_.coach.controleur;

import android.content.Context;

import com.example.fred_.coach.modele.AccesLocal;
import com.example.fred_.coach.modele.Profil;
import com.example.fred_.coach.outils.Serializer;

import java.util.Date;

/**
 * Created by fred_ on 12/03/2017.
 */

public final class Controle {
    private static Controle instance = null;
    private static Profil profil;
    private static String nomFic = "saveprofil";
    private static AccesLocal accesLocal;

    /**
     * Constructeur de la classe
     */
    private Controle() {
        super();
    }

    /**
     * méthode qui retourne l'instance de la classe existante ou qui en crée une si inexistante
     * @param contexte
     * @return instance
     */
    public final static Controle getInstance(Context contexte) {
        if (Controle.instance == null) {
            Controle.instance = new Controle();
            // récupération des données stockées
            // recupSerialize(contexte); // A METTRE SI BESOIN DE SERIALISER
            // accesLocal = new AccesLocal(contexte); // accès vers la bdd locale SI UTILISATION D'UNE BDD LOCALE
            // profil = accesLocal.recupDernier(); // création d'un profil à partir d'une bdd local SI UTILISATION D'UNE BDD LOCALE
        }
        return Controle.instance;
    }

    /**
     * méthode qui crée un profil à partir du poids, de la taille, de l'âge et du sexe saisis
     * @param poids
     * @param taille en cm
     * @param age
     * @param sexe 1 pour homme, 0 pour femme
     * @param contexte contexte (activity) du MainActivity
     */
    public void creerProfil(Integer poids, Integer taille, Integer age, Integer sexe, Context contexte) {
        this.profil = new Profil(poids, taille, age, sexe, new Date());
        // création du fichier de sauvegarde
        // Serializer.serialize(nomFic, this.profil, contexte); // A METTRE SI BESOIN DE SERIALISER
        // ajout du profil dans la bdd locale
        // accesLocal.ajout(profil); // ajout du profil dans la bdd locale A UTILISER SI BDD LOCALE
    }

    /**
     * méthode qui retourne l'IMG calculé à partir du profil saisi
     * @return
     */
    public float getImg() {
        if (this.profil == null) {
            return Float.parseFloat(null);
        } else {
            return this.profil.getImg();
        }
    }

    /**
     * méthode qui retourne le message généré à partir du profil saisi
     * @return
     */
    public String getMessage() {
        if (this.profil == null) {
            return null;
        } else {
            return this.profil.getMessage();
        }
    }

    /**
     * méthode qui retourne la taille générée à partir du profil saisi
     * @return
     */
    public Integer getTaille() {
        if (this.profil == null) {
            return null;
        } else {
            return this.profil.getTaille();
        }
    }

    /**
     * méthode qui retourne le poids généré à partir du profil saisi
     * @return
     */
    public Integer getPoids() {
        if (this.profil == null) {
            return null;
        } else {
            return this.profil.getPoids();
        }
    }

    /**
     * méthode qui retourne l'âge généré à partir du profil saisi
     * @return
     */
    public Integer getAge() {
        if (this.profil == null) {
            return null;
        } else {
            return this.profil.getAge();
        }
    }

    /**
     * méthode qui retourne le sexe généré à partir du profil saisi
     * @return
     */
    public Integer getSexe() {
        if (this.profil == null) {
            return null;
        } else {
            return this.profil.getSexe();
        }
    }

    /**
     * méthode qui récupère les données sérialisées stockées
     * @param contexte
     */
    private static void recupSerialize(Context contexte) {
        profil = (Profil)Serializer.deSerialize(nomFic, contexte);
    }
}