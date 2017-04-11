package com.example.fred_.coach.vue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.fred_.coach.R;
import com.example.fred_.coach.controleur.Controle;
import com.example.fred_.coach.modele.Profil;
import com.example.fred_.coach.outils.MesOutils;

import java.util.ArrayList;

/**
 * Created by fred_ on 20/03/2017.
 */

public class HistoListAdapter extends BaseAdapter {
    // déclaration des propriétés
    private ArrayList<Profil> lesProfils;
    private LayoutInflater inflater;
    private Context contexte;

    /**
     * constructeur
     * @param contexte
     * @param lesProfils
     */
    public HistoListAdapter(Context contexte, ArrayList<Profil> lesProfils) {
        this.lesProfils = lesProfils;
        this.inflater = LayoutInflater.from(contexte);
        this.contexte = contexte;
    }

    /**
     * méthode qui retourne le nombre de lignes de la liste
     * @return
     */
    @Override
    public int getCount() {
        return lesProfils.size();
    }

    /**
     * méthode qui retourne l'item sélectionné
     * @param position
     * @return
     */
    @Override
    public Object getItem(int position) {
        return lesProfils.get(position);
    }

    /**
     * méthode qui retourne le numéro de l'item sélectionné
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * méthode qui construit la vue d'une ligne

     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // holder est un objet de la petite classe
        ViewHolder holder ;
        // si la ligne n'existe pas encore
        if (convertView == null) {
            holder = new ViewHolder() ;
            // la ligne est construite à partir de la structure de la ligne (récupéré dans layout_list_histo)
            convertView = inflater.inflate(R.layout.layout_liste_histo, null) ;
            // chaque propriété de holder (correspondant aux objets graphiques) est liée à un des objets graphiques
            holder.txtListDate = (TextView)convertView.findViewById(R.id.txtListDate) ;
            holder.txtListIMG = (TextView)convertView.findViewById(R.id.txtListIMG) ;
            holder.imgListSuppr = (ImageButton)convertView.findViewById(R.id.imgListSuppr) ;
            holder.imgListSuppr.setImageResource(R.drawable.suppr);
            // affecte un tag (un indice) à la ligne (qui permettra de la repérer facilement)
            convertView.setTag(holder) ;
        }else{
            // si la ligne existe déjà, holder récupère le contenu (à partir de son tag, donc son indice)
            holder = (ViewHolder)convertView.getTag();
        }
        // holder est maintenant lié à la ligne graphique
        // valorisation des propriétés de holder avec le profil de lesProfils (à un indice précis : position)
        holder.txtListDate.setText(MesOutils.convertDateToString(lesProfils.get(position).getDateMesure())) ;
        holder.txtListIMG.setText(MesOutils.format2Deciman(lesProfils.get(position).getImg())) ;
        holder.imgListSuppr.setTag(position) ;
        // gestion de l'événement clic sur le bouton de suppression
        holder.imgListSuppr.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // récupère l'indice de la ligne concernée
                int position = (Integer)v.getTag() ;
                // averti le controleur qu'il faut enlever un profil
                Controle controle = Controle.getInstance(contexte);
                controle.delProfil(lesProfils.get(position));
                // rafraichi la liste visuelle
                notifyDataSetChanged() ;
            }
        }) ;
        return convertView ;
    }

    /**
     * création d'une classe interne pour donner la liste des éléments présents dans la ligne
     */
    private class ViewHolder {
        TextView txtListDate;
        TextView txtListIMG;
        ImageButton imgListSuppr;
    }
}
