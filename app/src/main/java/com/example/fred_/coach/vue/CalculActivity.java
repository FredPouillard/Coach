package com.example.fred_.coach.vue;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fred_.coach.R;
import com.example.fred_.coach.controleur.Controle;
import com.example.fred_.coach.outils.MesOutils;

/**
 * classe pour les calculs
 */
public class CalculActivity extends AppCompatActivity {
    // déclaration des propriétés
    private EditText txtPoids;
    private EditText txtTaille;
    private EditText txtAge;
    private RadioButton rdHomme;
    private RadioButton rdFemme;
    private TextView lblIMG;
    private ImageView imgSmiley;
    private Controle controle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcul);
        init();
    }

    /**
     * méthode init qui récupère les objets graphiques
     */
    private void init() {
        // récupération des objets graphiques
        txtPoids = (EditText)findViewById(R.id.txtPoids);
        txtTaille = (EditText)findViewById(R.id.txtTaille);
        txtAge = (EditText)findViewById(R.id.txtAge);
        rdHomme = (RadioButton)findViewById(R.id.rdHomme);
        rdFemme = (RadioButton)findViewById(R.id.rdFemme);
        lblIMG = (TextView)findViewById(R.id.lblIMG);
        imgSmiley = (ImageView)findViewById(R.id.imgSmiley);
        // création de l'objet controle
        controle = Controle.getInstance(this);
        // appel de la méthode ecouteCalcul
        ecouteCalcul();
        // récupération des données stockées
        // recupProfil(); // A METTRE SI BESOIN DE SERIALISER OU SI BDD LOCALE
        // clic sur le bouton accueil
        ecouteAccueil();
    }

    /**
     * méthode qui gère le clic sur le bouton accueil (retour vers la page d'accueil)
     */
    private void ecouteAccueil() {
        ((ImageButton) findViewById(R.id.btnCalculAccueil)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(CalculActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * méthode qui affiche les résultats du calcul
     * @param poids
     * @param taille
     * @param age
     * @param sexe
     */
    private void affichResult(Integer poids, Integer taille, Integer age, Integer sexe) {
        // création du profil
        controle.creerProfil(poids, taille, age, sexe);
        // récupération du message généré et du résultat du calcul
        String message = controle.getMessage();
        float resultat = controle.getImg();
        // String.format("%01f", resultat); // inutile car fonction de conversion format2Deciman
        // affichage de l'image, du résultat et du texte
        if (message == "IMG trop faible") {
            // trop maigre
            imgSmiley.setImageResource(R.drawable.maigre);
            lblIMG.setText(MesOutils.format2Deciman(resultat) + " " + message);
            lblIMG.setTextColor(Color.RED);
        } else if (message == "IMG trop élevé") {
            // trop gros
            imgSmiley.setImageResource(R.drawable.graisse);
            lblIMG.setText(MesOutils.format2Deciman(resultat) +  " " + message);
            lblIMG.setTextColor(Color.RED);
        } else {
            // normal
            imgSmiley.setImageResource(R.drawable.normal);
            lblIMG.setText(MesOutils.format2Deciman(resultat) + " " + message);
            lblIMG.setTextColor(Color.GREEN);
        }
    }

    /**
     * méthode qui gère l'événement sur le bouton calcul
     */
    private void ecouteCalcul() {
        ((Button)findViewById(R.id.btnCalc)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                // test de fonctionnement de l'affichage
                // Toast.makeText(CalculActivity.this, "test", Toast.LENGTH_SHORT).show();
                // récupération des valeurs poids, taille, âge
                String pds = txtPoids.getText().toString();
                String tl = txtTaille.getText().toString();
                String a = txtAge.getText().toString();
                Integer poids = 0;
                Integer taille = 0;
                Integer age = 0;
                if (!pds.matches("")) {
                    poids = Integer.parseInt(txtPoids.getText().toString());
                }
                if (!tl.matches("")) {
                    taille = Integer.parseInt(txtTaille.getText().toString());
                }
                if (!a.matches("")) {
                    age = Integer.parseInt(txtAge.getText().toString());
                }
                // récupération du sexe (1 = homme, 0 = femme)
                Integer sexe = 0;
                if (rdHomme.isChecked()) {
                    sexe = 1;
                }
                // vérification de la saisie des valeurs et affichage des résultats
                if (poids == 0 || taille == 0 || age == 0) {
                    Toast.makeText(CalculActivity.this, "Veuillez saisir tous les champs.", Toast.LENGTH_SHORT).show();
                } else {
                    affichResult(poids, taille, age, sexe);
                }
            }
        });
    }

    /**
     * méthode qui récupère le profil enregistré
     */
    /* public void recupProfil() { // A UTILISER SI PAS DE LISTE HISTORIQUE
        // vérification de présence de données enregistrées
        if (controle.getPoids() != null) {
            txtPoids.setText("" + controle.getPoids());
        }
        if (controle.getTaille() != null) {
            txtTaille.setText("" + controle.getTaille());
        }
        if (controle.getAge() != null) {
            txtAge.setText("" + controle.getAge());
        }
        if (controle.getSexe() != null) {
            if (controle.getSexe() == 1) {
                rdHomme.setChecked(true);
            } else if (controle.getSexe() == 0) {
                rdFemme.setChecked(true);
            }
        }
        // affichage du résultat stocké
        ((Button)findViewById(R.id.btnCalc)).performClick();
    } */

}
