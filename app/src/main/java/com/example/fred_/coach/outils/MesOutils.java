package com.example.fred_.coach.outils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        // String expectedPattern = "EEE MMM dd hh:mm:ss 'GMT' yyyy";
        String expectedPattern = "dd/MM/yy HH:mm:ss";
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
        return date.format(uneDate);
    }
}
