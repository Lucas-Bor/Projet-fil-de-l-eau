package fr.lucasb.fildeleau;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Création d'une instance de ma classe patrimoineBDD
        PatrimoineBDD patrimoineBdd = new PatrimoineBDD(this);

        //Création d'un patrimoine
        Patrimoine patrimoine = new Patrimoine(1, "Boissise", "une fontaine", "rond point");

        //On ouvre la base de données pour écrire dedans
        patrimoineBdd.open();
        //On insère le patrimoine que l'on vient de créer
        patrimoineBdd.insertPatrimoine(patrimoine);

        //Pour vérifier que l'on a bien créé notre patrimoine dans la BDD
        //on extrait le livre de la BDD grâce au titre du patrimoine que l'on a créé précédemment
        Patrimoine patrimoineFromBdd = patrimoineBdd.getLivreWithTitre(patrimoine.getCommune());
        //Si un livre est retourné (donc si le patrimoine à bien été ajouté à la BDD)
        if(patrimoineFromBdd != null){
            //On affiche les infos du patrimoine dans un Toast
            Toast.makeText(this, patrimoineFromBdd.toString(), Toast.LENGTH_LONG).show();
            //On modifie le titre du patrimoine
            patrimoineFromBdd.setCommune("J'ai modifié le titre du patrimoine");
            //Puis on met à jour la BDD
            patrimoineBdd.updateLivre(patrimoineFromBdd.getIdentifiant(), patrimoineFromBdd);
        }

        //On extrait le patrimoine de la BDD grâce au nouveau titre
        patrimoineFromBdd = patrimoineBdd.getLivreWithTitre("J'ai modifié le patrimoine");
        //S'il existe un patrimoine possédant ce titre dans la BDD
        if(patrimoineFromBdd != null){
            //On affiche les nouvelles informations du patrimoine pour vérifier que le titre du patrimoine a bien été mis à jour
            Toast.makeText(this, patrimoineFromBdd.toString(), Toast.LENGTH_LONG).show();
            //on supprime le patrimoine de la BDD grâce à son ID
            patrimoineBdd.removeLivreWithID(patrimoineFromBdd.getIdentifiant());
        }

        //On essaye d'extraire de nouveau le patrimoine de la BDD toujours grâce à son nouveau titre
        patrimoineFromBdd = patrimoineBdd.getLivreWithTitre("J'ai modifié le titre du patrimoine");
        //Si aucun patrimoine n'est retourné
        if(patrimoineFromBdd == null){
            //On affiche un message indiquant que le patrimoine n'existe pas dans la BDD
            Toast.makeText(this, "Ce patrimoine n'existe pas dans la BDD", Toast.LENGTH_LONG).show();
        }
        //Si le patrimoine existe (mais normalement il ne devrait pas)
        else{
            //on affiche un message indiquant que le patrimoine existe dans la BDD
            Toast.makeText(this, "Ce patrimoine existe dans la BDD", Toast.LENGTH_LONG).show();
        }

        patrimoineBdd.close();
    }
}