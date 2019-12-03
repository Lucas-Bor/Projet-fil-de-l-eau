package fr.lucasb.fildeleau;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PatrimoineBDD {

    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "eleves.db";

    private static final String TABLE_Patrimoine = "Patrimoine";
    private static final String COL_Identifiant = "Identifiant";
    private static final int NUM_Identifiant = 0;
    private static final String COL_Commune = "ISBN";
    private static final int NUM_Commune = 1;
    private static final String COL_Elem_patri = "Elem_patri";
    private static final int NUM_Elem_patri = 2;
    private static final String COL_Elem_princ = "Elem_patri";
    private static final int NUM_Elem_princ = 3;

    private SQLiteDatabase bdd;

    private BaseSqlite BaseSqlite;

    public PatrimoineBDD(Context context){
        //On crée la BDD et sa table
        BaseSqlite = new BaseSqlite(context, NOM_BDD, null, VERSION_BDD);
    }

    public void open(){
        //on ouvre la BDD en écriture
        bdd = BaseSqlite.getWritableDatabase();
    }

    public void close(){
        //on ferme l'accès à la BDD
        bdd.close();
    }

    public SQLiteDatabase getBDD(){
        return bdd;
    }

    public long insertPatrimoine(Patrimoine patrimoine){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_Commune, patrimoine.getCommune());
        values.put(COL_Elem_patri, patrimoine.getElem_patri());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_Patrimoine, null, values);
    }

    public int updateLivre(int id, Patrimoine patrimoine){
        //La mise à jour d'un livre dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simplement préciser quel livre on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(COL_Commune, patrimoine.getCommune());
        values.put(COL_Elem_patri, patrimoine.getElem_patri());
        return bdd.update(TABLE_Patrimoine, values, COL_Identifiant + " = " +id, null);
    }

    public int removeLivreWithID(int id){
        //Suppression d'un livre de la BDD grâce à l'ID
        return bdd.delete(TABLE_Patrimoine, COL_Identifiant + " = " +id, null);
    }

    public Patrimoine getLivreWithTitre(String titre){
        //Récupère dans un Cursor les valeurs correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
        Cursor c = bdd.query(TABLE_Patrimoine, new String[] {COL_Identifiant, COL_Commune, COL_Elem_patri}, COL_Commune + " LIKE \"" + titre +"\"", null, null, null, null);
        return cursorToPatrimoine(c);
    }

    //Cette méthode permet de convertir un cursor en un livre
    private Patrimoine cursorToPatrimoine(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();
        //On créé un livre
        Patrimoine livre = new Patrimoine();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        livre.setIdentifiant(c.getInt(NUM_Identifiant));
        livre.setCommune(c.getString(NUM_Commune));
        livre.setElem_patri(c.getString(NUM_Elem_patri));
        //On ferme le cursor
        c.close();

        //On retourne le livre
        return livre;
    }
}
