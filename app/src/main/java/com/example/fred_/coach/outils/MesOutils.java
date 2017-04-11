package com.example.fred_.coach.outils;

import android.support.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * Created by fred_ on 18/03/2017.
 * classe qui contient les méthodes pour convertir un string en date et inversement
 */

public abstract class MesOutils {
    /**
     * méthode qui convertit une date en format string vers le format date
     * @param laDate
     * @return
     */
    public static Date convertStringToDate(String laDate) {
        String expectedPattern = "EEE MMM dd hh:mm:ss z yyyy";
        // String expectedPattern = "dd/MM/yy HH:mm:ss";
        SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
        try {
            Date date = formatter.parse(laDate);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * méthode qui convertit une date en format date vers le format string
     * @param uneDate
     * @return
     */
    public static String convertDateToString(Date uneDate) {
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        // SimpleDateFormat date = new SimpleDateFormat("EEE MMM dd hh:mm:ss 'ZZZ' yyyy");
        return date.format(uneDate);
    }

    /**
     * méthode qui convertit un nombre en float vers le type string avec 2 décimales
     * @param nombre
     * @return
     */
    public static String format2Deciman(Float nombre) {
        return String.format("%.01f", nombre);
    }
}
