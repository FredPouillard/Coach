package com.example.fred_.coach.vue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.fred_.coach.R;
import com.example.fred_.coach.controleur.Controle;
import com.example.fred_.coach.modele.Profil;

import java.util.ArrayList;

public class HistoActivity extends AppCompatActivity {
    // propriété
    Controle controle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histo);
        controle = Controle.getInstance(this);
        init();
        creerListe();
    }

    /**
     * méthode qui initialise la page et les éléments
     */
    private void init() {
        // clic sur le bouton accueil
        ecouteAccueil();
    }

    /**
     * méthode qui gère le clic sur le bouton accueil (retour vers la page d'accueil)
     */
    private void ecouteAccueil() {
        ImageButton imgBtn = (ImageButton)findViewById(R.id.btnHistoAccueil);
        imgBtn.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(HistoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * méthode pour la création de liste de profils
     */
    public void creerListe() {
        ArrayList<Profil> liste = controle.getLesProfils();
        if (liste != null) { // test suggéré par la prof
            ListView listView = (ListView)findViewById(R.id.lstHisto);
            HistoListAdapter adapter = new HistoListAdapter(HistoActivity.this, liste);
            listView.setAdapter(adapter);
        }
    }
}
