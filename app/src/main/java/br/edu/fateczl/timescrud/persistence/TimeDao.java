package br.edu.fateczl.timescrud.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.timescrud.model.Time;

public class TimeDao implements ITimeDao, ICRUDDao<Time>{
    /*
     *@author:<JOÃO VITOR LIMA COSTA>
     */

    private final Context context;
    private GenericDao gDao;
    private SQLiteDatabase database;

    public TimeDao(Context context) {
        this.context = context;
    }

    @Override
    public TimeDao open() throws SQLException {
        gDao = new GenericDao(context);
        database = gDao.getWritableDatabase();
        return this;
    }

    @Override
    public void close() {
        gDao.close();
    }

    @Override
    public void insert(Time time) throws SQLException {
        ContentValues contentValues = getContentValues(time);
        database.insert("time", null, contentValues);
    }

    @Override
    public int update(Time time) throws SQLException {
        ContentValues contentValues = getContentValues(time);
        int retorno = database.update("time", contentValues,
                "codigo = " + time.getCodigo(), null);
        return retorno;
    }

    @Override
    public void delete(Time time) throws SQLException {
        database.delete("time", "codigo = " + time.getCodigo(), null);
    }

    @Override
    public Time findOne(Time time) throws SQLException {

        String sql = "SELECT * FROM time WHERE codigo = " + time.getCodigo();

        Cursor cursor = database.rawQuery(sql, null);

        List<String> dadosTimes;

        if(cursor != null){
            Buscador buscador = new Buscador(cursor);
            time.organizarDados(buscador.buscarUm());
        }
        cursor.close();

        return time;
    }

    @Override
    public List<Time> findAll() throws SQLException {
        String sql = "SELECT * FROM time";

        Cursor cursor = database.rawQuery(sql, null);

        List<Time> times = new ArrayList<>();
        List<String> dadosTimes;

        if(cursor != null){
            Buscador buscador = new Buscador(cursor);
            dadosTimes = buscador.listarTodos();
            for(String dado : dadosTimes){
                Time time = new Time();
                time.organizarDados(dado);
                times.add(time);
            }
        }
        cursor.close();

        return times;
    }

    private static ContentValues getContentValues(Time time) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("codigo", time.getCodigo());
        contentValues.put("nome", time.getNome());
        contentValues.put("cidade", time.getCidade());

        return contentValues;
    }
}
