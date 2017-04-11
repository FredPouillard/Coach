package com.example.fred_.coach.modele;

import com.example.fred_.coach.controleur.Controle;
import com.example.fred_.coach.outils.AccesHTTP;
import com.example.fred_.coach.outils.AsyncResponse;
import com.example.fred_.coach.outils.MesOutils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by fred_ on 19/03/2017.
 */

public class AccesDistant implements AsyncResponse {
    // déclaration des propriétés
    private static final String SERVERADDR = "http://192.168.1.31/coach/serveurcoach.php";
    private Controle controle;

    /**
     * constructeur
     */
    public AccesDistant() {
        controle = Controle.getInstance(null);
    }

    /**
     * méthode qui gère le retour asynchrone du serveur
     * @param output
     */
    @Override
    public void processFinish(String output) {
        // affichage du message
        Log.d("serveur", "*****************" + output);
        // récupération du message
        String[] message = output.split("%");
        if (message.length > 1) {
            if (message[0].equals("Erreur : ")) {
                Log.d("serveur", "************Erreur :" + message[1]); // utile pour vérifier
            } else if (message[0].equals("enreg")) {
                Log.d("serveur", "************Enreg :" + message[1]); // utile pour vérifier
            } else if (message[0].equals("tous")) { // FAIRE LE TEST SUR "dernier" SI PAS DE LISTES HISTORIQUES
                // Log.d("serveur", "************Dernier :" + message[1]); // utile pour vérifier
                try {
                    // récupération du dernier profil dans la bdd distante
                    // JSONObject info = new JSONObject(message[1]); // A UTILISER SI PAS DE LISTE HISTORIQUE
                    JSONArray jSonInfo = new JSONArray(message[1]);
                    ArrayList<Profil> lesProfils = new ArrayList<Profil>();
                    for(int i = 0; i < jSonInfo.length(); i++) {
                        JSONObject info = new JSONObject("" + jSonInfo.get(i));
                        Integer poids = info.getInt("poids");
                        Integer taille = info.getInt("taille");
                        Integer age = info.getInt("age");
                        Integer sexe = info.getInt("sexe");
                        Date dateMesure = MesOutils.convertStringToDate(info.getString("datemesure"));
                        Profil profil = new Profil(poids, taille, age, sexe, dateMesure);
                        lesProfils.add(profil);
                    }
                    // Integer poids = info.getInt("poids"); // A UTILISER SI PAS DE LISTE HISTORIQUE
                    // Integer taille = info.getInt("taille"); // A UTILISER SI PAS DE LISTE HISTORIQUE
                    // Integer age = info.getInt("age"); // A UTILISER SI PAS DE LISTE HISTORIQUE
                    // Integer sexe = info.getInt("sexe"); // A UTILISER SI PAS DE LISTE HISTORIQUE
                    // Date dateMesure = MesOutils.convertStringToDate(info.getString("datemesure")); // A UTILISER SI PAS DE LISTE HISTORIQUE
                    // Profil profil = new Profil(poids, taille, age, sexe, dateMesure); // A UTILISER SI PAS DE LISTE HISTORIQUE
                    // controle.setProfil(profil); // A UTILISER SI PAS DE LISTE HISTORIQUE
                    controle.setLesProfils(lesProfils);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // le serveur a supprimé un profil et normalement ne retourne rien (juste la requête)
            } else if (message[0].equals("suppr")) {
                Log.d("serveur", "************suppr :" + message[1]);
            }
        }
    }

    /**
     * méthode qui envoie les données vers le serveur distant
     * @param operation
     * @param lesDonneesJSON
     */
    public void envoi(String operation, JSONArray lesDonneesJSON) {
        AccesHTTP accesDonnees = new AccesHTTP();
        accesDonnees.delegate = this;
        accesDonnees.addParam("operation", operation);
        accesDonnees.addParam("lesdonnees", lesDonneesJSON.toString());
        accesDonnees.execute(SERVERADDR);
    }
}
