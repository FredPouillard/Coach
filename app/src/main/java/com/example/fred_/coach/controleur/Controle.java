package com.example.fred_.coach.controleur;

import android.content.Context;

import com.example.fred_.coach.modele.AccesDistant;
import com.example.fred_.coach.modele.Profil;
import com.example.fred_.coach.outils.Serializer;
import com.example.fred_.coach.vue.CalculActivity;
import com.example.fred_.coach.vue.HistoActivity;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by fred_ on 12/03/2017.
 */

public final class Controle {
    private static Controle instance = null;
    // private static Profil profil; // A UTILISER SI PAS DE LISTES HISTORIQUES
    private static ArrayList<Profil> lesProfils = new ArrayList<Profil>();
    private static String nomFic = "saveprofil";
    // private static AccesLocal accesLocal; // A UTILISER SI ACCES A BDD LOCALE
    private static AccesDistant accesDistant; // A UTILISER SI ACCES A BDD DISTANTE
    private static Context contexte;

    /**
     * Constructeur de la classe
     */
    private Controle() {
        super();
    }

    /**
     * getter de la liste contenant les profils
     * @return
     */
    public static ArrayList<Profil> getLesProfils() {
        return lesProfils;
    }

    /**
     * méthode qui retourne l'instance de la classe existante ou qui en crée une si inexistante
     * @param contexte
     * @return instance
     */
    public final static Controle getInstance(Context contexte) {
        Controle.contexte = contexte;
        if (Controle.instance == null) {
            //Controle.contexte = contexte;
            Controle.instance = new Controle();
            // récupération des données stockées
            // recupSerialize(contexte); // A METTRE SI BESOIN DE SERIALISER
            // accesLocal = new AccesLocal(contexte); // accès vers la bdd locale SI UTILISATION D'UNE BDD LOCALE
            // profil = accesLocal.recupDernier(); // création d'un profil à partir d'une bdd local SI UTILISATION D'UNE BDD LOCALE
            accesDistant = new AccesDistant(); // SI UTILISATION D'UNE BDD DISTANTE
            // accesDistant.envoi("dernier", new JSONArray()); // SI UTILISATION D'UNE BDD DISTANTE
            accesDistant.envoi("tous", new JSONArray()); // SI BESOIN DE TOUT RECUPERER
        }
        return Controle.instance;
    }

    /**
     * méthode qui crée un profil à partir du poids, de la taille, de l'âge et du sexe saisis
     * @param poids
     * @param taille en cm
     * @param age
     * @param sexe 1 pour homme, 0 pour femme
     */
    public void creerProfil(Integer poids, Integer taille, Integer age, Integer sexe) { // ATTENTION LE CONTEXTE A ETE ENELEVE POUR LA BASE DISTANTE, A REMETTRE DANS CALCULACTIVITY
        Profil profil = new Profil(poids, taille, age, sexe, new Date());
        lesProfils.add(profil);
        // this.profil = new Profil(poids, taille, age, sexe, new Date()); // // A UTILISER SI PAS DE LISTE HISTORIQUE
        // création du fichier de sauvegarde
        // Serializer.serialize(nomFic, this.profil, contexte); // A METTRE SI BESOIN DE SERIALISER
        // ajout du profil dans la bdd locale
        // accesLocal.ajout(profil); // ajout du profil dans la bdd locale A UTILISER SI BDD LOCALE
        // ajout du profil dans la bdd distante
        accesDistant.envoi("enreg", profil.convertToJSONArray()); // A METTRE SI UTILISATION D'UNE BDD DISTANTE
    }

    /**
     * méthode qui retourne l'IMG calculé à partir du profil saisi
     * @return
     */
    public float getImg() {
        if (lesProfils.size() == 0) {
            return Float.parseFloat(null);
        } else {
            return lesProfils.get(lesProfils.size() - 1).getImg();
        }
        /* if (this.profil == null) { // A UTILISER SI PAS DE LISTE HISTORIQUE
            return Float.parseFloat(null);
        } else {
            return this.profil.getImg();
        } */
    }

    /**
     * méthode qui retourne le message généré à partir du profil saisi
     * @return
     */
    public String getMessage() {
        if (lesProfils.size() == 0) {
            return null;
        } else {
            return lesProfils.get(lesProfils.size() - 1).getMessage();
        }
        /* if (this.profil == null) { // A UTILISER SI PAS DE LISTE HISTORIQUE
            return null;
        } else {
            return this.profil.getMessage();
        } */
    }

    /**
     * méthode qui retourne la taille générée à partir du profil saisi
     * @return
     */
    /* public Integer getTaille() { // A UTILISER SI PAS DE LISTE HISTORIQUE
        if (this.profil == null) {
            return null;
        } else {
            return this.profil.getTaille();
        }
    } */

    /**
     * méthode qui retourne le poids généré à partir du profil saisi
     * @return
     */
    /* public Integer getPoids() { // A UTILISER SI PAS DE LISTE HISTORIQUE
        if (this.profil == null) {
            return null;
        } else {
            return this.profil.getPoids();
        }
    } */

    /**
     * méthode qui retourne l'âge généré à partir du profil saisi
     * @return
     */
    /* public Integer getAge() { // A UTILISER SI PAS DE LISTE HISTORIQUE
        if (this.profil == null) {
            return null;
        } else {
            return this.profil.getAge();
        }
    } */

    /**
     * méthode qui retourne le sexe généré à partir du profil saisi
     * @return
     */
    /* public Integer getSexe() { // A UTILISER SI PAS DE LISTE HISTORIQUE
        if (this.profil == null) {
            return null;
        } else {
            return this.profil.getSexe();
        }
    } */

    /**
     * méthode qui récupère les données sérialisées stockées
     * @param contexte
     */
    /* private static void recupSerialize(Context contexte) { // A UTILISER SI SERIALISER
        profil = (Profil)Serializer.deSerialize(nomFic, contexte);
    } */

    /**
     * méthode qui récupère le profil pour un affichage des données après un appel à une BDD distante
     * @param profil
     */
    /* public void setProfil(Profil profil) { // A UTILISER SI PAS DE LISTE HISTORIQUE
        Controle.profil = profil;
        ((CalculActivity)contexte).recupProfil();
    } */

    /**
     * setter qui valorise la collection des profils
     * @param lesProfils
     */
    public void setLesProfils(ArrayList<Profil> lesProfils) {
        Controle.lesProfils = lesProfils;
        // ((HistoActivity)Controle.contexte).creerListe(); // PLANTAGE !
    }

    /**
     * méthode qui supprime une ligne
     * @param leProfil
     */
    public void delProfil(Profil leProfil) {
        accesDistant.envoi("suppr", leProfil.convertToJSONArray());
        lesProfils.remove(leProfil);
    }
}