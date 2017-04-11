package com.example.fred_.coach.vue;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.fred_.coach.R;

/**
 * classe principale (menu)
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // création du menu
        creerMenu();
    }

    /**
     * méthode qui gère le menu
     */
    private void creerMenu() {
        // récupération des boutons
        ImageButton btnMonIMG = (ImageButton)findViewById(R.id.btnMonIMG);
        ImageButton btnMonHistorique = (ImageButton)findViewById(R.id.btnMonHistorique);
        ecouteMenu(btnMonIMG, CalculActivity.class);
        ecouteMenu(btnMonHistorique, HistoActivity.class);
    }

    /**
     * méthode qui gère le clic sur le bouton
     * @param bouton
     * @param classe
     */
    private void ecouteMenu(ImageButton bouton, final Class classe) {
        bouton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, classe);
                startActivity(intent);
            }
        });
    }
}
