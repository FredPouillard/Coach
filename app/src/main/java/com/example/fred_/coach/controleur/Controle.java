package com.example.fred_.coach.controleur;

import com.example.fred_.coach.modele.Profil;

/**
 * Created by fred_ on 12/03/2017.
 */

public final class Controle {
    private static Controle instance = null;
    private static Profil profil;

    /**
     * Constructeur de la classe
     */
    private Controle() {
        super();
    }

    /**
     * méthode qui retourne l'instance de la classe existante ou qui en crée une si inexistante
     * @return instance
     */
    public final static Controle getInstance() {
        if (Controle.instance == null) {
            Controle.instance = new Controle();
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
    public void creerProfil(Integer poids, Integer taille, Integer age, Integer sexe) {
        this.profil = new Profil(poids, taille, age, sexe);
    }

    /**
     * méthode qui retourne l'IMG calculé à partir du profil saisi
     * @return
     */
    public float getImg() {
        return this.profil.getImg();
    }

    /**
     * méthode qui retourne le message généré à partir du profil saisi
     * @return
     */
    public String getMessage() {
        return this.profil.getMessage();
    }
}