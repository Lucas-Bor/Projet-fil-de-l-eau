package fr.lucasb.fildeleau;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class BaseSqlite extends SQLiteOpenHelper {

    private static final String TABLE_Patrimoine = "table_Patrimoine ";
    private static final String COL_Identifiant = "identifiant";
    private static final String COL_Commune = "commune";
    private static final String COL_Elem_patri = "Elem_patri";
    private static final String COL_Elem_princ = "Elem_princ";

    private static final String CREATE_BDD = "CREATE TABLE " + TABLE_Patrimoine + " ("
            + COL_Identifiant + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_Commune + " TEXT NOT NULL, "
            + COL_Elem_patri + " TEXT NOT NULL,"  + COL_Elem_princ + " TEXT NOT NULL);";

    public BaseSqlite(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //on crée la table à partir de la requête écrite dans la variable CREATE_BDD
        db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //On peut faire ce qu'on veut ici moi j'ai décidé de supprimer la table et de la recréer
        //comme ça lorsque je change la version les id repartent de 0
        db.execSQL("DROP TABLE " + TABLE_Patrimoine + ";");
        onCreate(db);
    }

}