package fr.lucasb.fildeleau;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ShowDataActivity extends AppCompatActivity {

    SQLiteHelper sqLiteHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    ListAdapter listAdapter ;
    ListView LISTVIEW;
    ArrayList<String> ID_Array;
    ArrayList<String> Subject_NAME_Array;
    ArrayList<String> Subject_FullForm_Array;
    ArrayList<String> ListViewClickItemArray = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        LISTVIEW = (ListView) findViewById(R.id.listView1);

        ID_Array = new ArrayList<String>();

        Subject_NAME_Array = new ArrayList<String>();

        Subject_FullForm_Array = new ArrayList<String>();

        sqLiteHelper = new SQLiteHelper(this);

        LISTVIEW.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(ShowDataActivity.this, ListViewClickItemArray.get(position).toString(), Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    protected void onResume() {

        ShowSQLiteDBdata() ;

        super.onResume();
    }

    private void ShowSQLiteDBdata() {

        sqLiteDatabase = sqLiteHelper.getWritableDatabase();

        cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+ SQLiteHelper.TABLE_NAME+"", null);

        ID_Array.clear();
        Subject_NAME_Array.clear();
        Subject_FullForm_Array.clear();

        if (cursor.moveToFirst()) {
            do {

                ID_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_ID)));

                ListViewClickItemArray.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_1_Subject_Name)));

                Subject_NAME_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_1_Subject_Name)));

                Subject_FullForm_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_2_SubjectFullForm)));


            } while (cursor.moveToNext());
        }

        listAdapter = new ListAdapter(ShowDataActivity.this,

                ID_Array,
                Subject_NAME_Array,
                Subject_FullForm_Array
        );

        LISTVIEW.setAdapter(listAdapter);

        cursor.close();
    }
}