package br.edu.fateczl.timescrud.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GenericDao extends SQLiteOpenHelper {
    /*
     *@author:<JOÃO VITOR LIMA COSTA>
     */

    private static final String DATABASE = "ESPORTIVO.DB";

    private static final int DATA_VER = 1;

    private static final String CREATE_TABLE_TIME =
            "CREATE TABLE time ( " +
                    "codigo INT NOT NULL PRIMARY KEY, " +
                    "nome VARCHAR(50) NOT NULL, " +
                    "cidade VARCHAR(80) NOT NULL);";

    private static final String CREATE_TABLE_JOGADOR =
            "CREATE TABLE jogador ( " +
                    "id INT(10) NOT NULL PRIMARY KEY, " +
                    "nome VARCHAR(100) NOT NULL, " +
                    "data_nasc VARCHAR(10) NOT NULL, " +
                    "altura DECIMAL(4,2) NOT NULL, " +
                    "peso DECIMAL(4,1) NOT NULL, " +
                    "time_codigo INT(10) NOT NULL, " +
                    "FOREIGN KEY (time_codigo) REFERENCES time(codigo));";

    public GenericDao(Context context){
        super(context, DATABASE, null, DATA_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_TIME);
        sqLiteDatabase.execSQL(CREATE_TABLE_JOGADOR);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int antigaVersao, int novaVersao) {
        if(novaVersao > antigaVersao){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS jogador");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS time");
            onCreate(sqLiteDatabase);
        }
    }
}
