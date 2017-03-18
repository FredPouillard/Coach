package com.example.fred_.coach.modele;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.fred_.coach.outils.MesOutils;
import com.example.fred_.coach.outils.MySQLiteOpenHelper;

import java.util.Date;

/**
 * Created by fred_ on 17/03/2017.
 */

public class AccesLocal {
    // déclaration des propriétés
    private String nomBase = "bdCoach.sqlite";
    private Integer versionBase = 1;
    private MySQLiteOpenHelper accesBD;
    private SQLiteDatabase bd;

    /**
     * constructeur
     * @param contexte
     */
    public AccesLocal(Context contexte) {
        this.accesBD = new MySQLiteOpenHelper(contexte, nomBase, versionBase);
    }

    /**
     * méthode qui ajoute un profil dans la BDD et qui exécute une requête
     * @param profil
     */
    public void ajout(Profil profil) {
        bd = accesBD.getWritableDatabase();
        // requête
        String req = "insert into profil (datemesure, poids, taille, age, sexe) values";
        req += "(\"" + MesOutils.convertDateToString(profil.getDateMesure()) + "\"," + profil.getPoids() + "," + profil.getTaille() + "," + profil.getAge() + "," + profil.getSexe() + ")";
        bd.execSQL(req);
    }

    /**
     * méthode qui récupère dans la bdd le dernier profil
     * @return
     */
    public Profil recupDernier() {
        Profil profil = null;
        bd = accesBD.getReadableDatabase();
        // requête
        String req = "select * from profil order by datemesure desc";
        // déclaration du curseur
        Cursor curseur = bd.rawQuery(req, null);
        curseur.moveToFirst();
        if (!curseur.isAfterLast()) { // si le curseur n'est pas vide on réucpère les infos et on crée le profil
            Date dateMesure = MesOutils.convertStringToDate(curseur.getString(0));
            Log.d("dateMesure", "********************* avant="+curseur.getString(0)) ;
            Log.d("dateMesure", "********************* après="+dateMesure) ;
            Integer poids = curseur.getInt(1);
            Integer taille = curseur.getInt(2);
            Integer age = curseur.getInt(3);
            Integer sexe = curseur.getInt(4);
            profil = new Profil(poids, taille, age, sexe, dateMesure);
        }
        curseur.close();
        return profil;
    }
}
