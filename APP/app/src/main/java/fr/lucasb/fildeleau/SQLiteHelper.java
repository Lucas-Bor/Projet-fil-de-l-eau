package fr.lucasb.fildeleau;

/**
 * Created by Juned on 1/23/2017.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    static String DATABASE_NAME="patrimoine";

    public static final String TABLE_NAME="SubjectTable";

    public static final String Table_Column_ID="id";

    public static final String Table_Column_1_Subject_Name="Commune";

    public static final String Table_Column_2_SubjectFullForm="Element_patri";

    public SQLiteHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        String CREATE_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+Table_Column_ID+" INTEGER PRIMARY KEY, "+Table_Column_1_Subject_Name+" VARCHAR, "+Table_Column_2_SubjectFullForm+" VARCHAR)";
        database.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

}