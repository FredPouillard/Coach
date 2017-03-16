package com.example.fred_.coach.modele;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by fred_ on 06/02/2017.
 */
public class ProfilTest {
    // création d'un profil : femme de 67 kg, 1m65, 35 ans
    private Profil profil = new Profil(67, 165, 35, 0);
    // résultat de l'img correspondant
    private float img = (float)32.2;
    // message correspondant
    private String message = "IMG trop élevé";

    @Test
    public void testGetImg() throws Exception {
        assertEquals(img, profil.getImg(), (float)0.1);
    }

    @Test
    public void testGetMessage() throws Exception {
        assertEquals(message, profil.getMessage());
    }

}