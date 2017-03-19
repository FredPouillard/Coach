package com.example.fred_.coach.vue;

import android.graphics.Color;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fred_.coach.R;
import com.example.fred_.coach.controleur.Controle;
import com.example.fred_.coach.modele.Profil;

public class MainActivity extends AppCompatActivity {
    // déclaration des propriétés
    private EditText txtPoids;
    private EditText txtTaille;
    private EditText txtAge;
    private RadioButton rdHomme;
    private RadioButton rdFemme;
    private TextView lblIMG;
    private ImageView imgSmiley;
    private Controle controle;

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
        this.controle = Controle.getInstance(this);
        // appel de la méthode ecouteCalcul
        this.ecouteCalcul();
        // récupération des données stockées
        // recupProfil(); // A METTRE SI BESOIN DE SERIALISER OU SI BDD LOCALE
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
        this.controle.creerProfil(poids, taille, age, sexe, this);
        // récupération du message généré et du résultat du calcul
        String message = controle.getMessage();
        float resultat = controle.getImg();
        String.format("%01f", resultat);
        // affichage de l'image, du résultat et du texte
        if ((resultat < 15 && sexe == 0) || (resultat < 10 && sexe == 1)) {
            // trop maigre
            imgSmiley.setImageResource(R.drawable.maigre);
            lblIMG.setText(Float.toString(resultat) + " : IMG trop faible");
            lblIMG.setTextColor(Color.RED);
        } else if ((resultat > 30 && sexe == 0) || (resultat > 25 && sexe == 1)) {
            // trop gros
            imgSmiley.setImageResource(R.drawable.graisse);
            lblIMG.setText(Float.toString(resultat) + " : IMG trop élevé");
            lblIMG.setTextColor(Color.RED);
        } else {
            // normal
            imgSmiley.setImageResource(R.drawable.normal);
            lblIMG.setText(Float.toString(resultat) + " : IMG normal");
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
                // Toast.makeText(MainActivity.this, "test", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(MainActivity.this, "Veuillez saisir tous les champs.", Toast.LENGTH_SHORT).show();
                } else {
                    affichResult(poids, taille, age, sexe);
                }
            }
        });
    }

    /**
     * méthode qui récupère le profil enregistré
     */
    public void recupProfil() {
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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.init();
    }
}
