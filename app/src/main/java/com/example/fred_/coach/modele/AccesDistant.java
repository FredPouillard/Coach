package com.example.fred_.coach.modele;

import com.example.fred_.coach.controleur.Controle;
import com.example.fred_.coach.outils.AccesHTTP;
import com.example.fred_.coach.outils.AsyncResponse;
import com.example.fred_.coach.outils.MesOutils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
            } else if (message[0].equals("dernier")) {
                // Log.d("serveur", "************Dernier :" + message[1]); // utile pour vérifier
                try {
                    // récupération du dernier profil dans la bdd distante
                    JSONObject info = new JSONObject(message[1]);
                    Date dateMesure = MesOutils.convertStringToDate(info.getString("datemesure"));
                    Integer poids = info.getInt("poids");
                    Integer taille = info.getInt("taille");
                    Integer age = info.getInt("age");
                    Integer sexe = info.getInt("sexe");
                    Profil profil = new Profil(poids, taille, age, sexe, dateMesure);
                    controle.setProfil(profil);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
