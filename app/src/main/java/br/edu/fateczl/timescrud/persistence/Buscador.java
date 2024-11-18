package br.edu.fateczl.timescrud.persistence;

import android.database.Cursor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Buscador implements IBuscador{
    /*
     *@author:<JOÃO VITOR LIMA COSTA>
     */

    private final Cursor CURSOR;

    public Buscador(Cursor cursor){
        this.CURSOR = cursor;
    }

    @Override
    public String buscarUm() throws SQLException {

        CURSOR.moveToFirst();

        StringBuffer buffer = new StringBuffer();

        if(!CURSOR.isAfterLast()){
            int colunasCont = CURSOR.getColumnCount();

            for(int i = 0; i < colunasCont; i++){

                int colunaTipo = CURSOR.getType(i);

                buffer.append(ler(i, colunaTipo)).append(';');
            }
        }
        return buffer.toString();
    }

    @Override
    public List<String> listarTodos() throws SQLException{
        CURSOR.moveToFirst();

        List<String> dadosRetorno = new ArrayList<>();

        int colunasCont = CURSOR.getColumnCount();

        while(!CURSOR.isAfterLast()){
            StringBuffer buffer = new StringBuffer();

            for(int i = 0; i < colunasCont; i++){

                int colunaTipo = CURSOR.getType(i);

                buffer.append(ler(i, colunaTipo)).append(';');
            }
            dadosRetorno.add(buffer.toString());
            CURSOR.moveToNext();
        }
        return dadosRetorno;
    }

    private String ler(int posicao, int colunaTipo){
        switch (colunaTipo) {
            case Cursor.FIELD_TYPE_INTEGER:
                int intValue = CURSOR.getInt(posicao);
                return String.valueOf(intValue);
            case Cursor.FIELD_TYPE_STRING:
                String stringValue = CURSOR.getString(posicao);
                return stringValue;
            case Cursor.FIELD_TYPE_FLOAT:
                float floatValue = CURSOR.getFloat(posicao);
                return String.valueOf(floatValue);
            case Cursor.FIELD_TYPE_NULL:
                break;
        }
        return "NULL";
    }
}
