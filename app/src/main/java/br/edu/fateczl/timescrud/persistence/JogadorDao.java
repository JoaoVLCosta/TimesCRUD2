package br.edu.fateczl.timescrud.persistence;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.edu.fateczl.timescrud.model.Jogador;
import br.edu.fateczl.timescrud.model.Time;

public class JogadorDao implements IJogadorDao, ICRUDDao<Jogador>{
    /*
     *@author:<JOÃO VITOR LIMA COSTA>
     */

    private final Context context;
    private GenericDao gDao;
    private SQLiteDatabase database;

    public JogadorDao(Context context){
        this.context = context;
    }

    @Override
    public JogadorDao open() throws SQLException {
        gDao = new GenericDao(context);
        database = gDao.getWritableDatabase();
        return this;
    }

    @Override
    public void close() {
        gDao.close();
    }

    @Override
    public void insert(Jogador jogador) throws SQLException {
        ContentValues contentValues = getContentValues(jogador);
        database.insert("jogador", null, contentValues);
    }

    @Override
    public int update(Jogador jogador) throws SQLException {
        ContentValues contentValues = getContentValues(jogador);
        int retorno = database.update("jogador", contentValues,
                "id = " + jogador.getId(), null);
        return retorno;
    }

    @Override
    public void delete(Jogador jogador) throws SQLException {
        database.delete("jogador", "id = " + jogador.getId(), null);
    }

    @SuppressLint("Range")
    @Override
    public Jogador findOne(Jogador jogador) throws SQLException {
        String timeSQL = "SELECT time.* " +
                "FROM time INNER JOIN jogador " +
                "ON jogador.time_codigo = time.codigo " +
                "WHERE jogador.id = " + jogador.getId();

        String jogadorSQL = "SELECT * " +
                "FROM jogador " +
                "WHERE jogador.id = " + jogador.getId();

        Cursor cursor = database.rawQuery(timeSQL, null);

        Time time = new Time();

        if(cursor != null){
            Buscador buscador = new Buscador(cursor);
            time.organizarDados(buscador.buscarUm());
        }
        cursor.close();

        cursor = database.rawQuery(jogadorSQL, null);
        if(cursor != null){
            Buscador buscador = new Buscador(cursor);
            jogador.organizarDados(buscador.buscarUm());
            jogador.setTime(time);
        }
        cursor.close();
        return jogador;
    }

    @Override
    public List<Jogador> findAll() throws SQLException {
        String timeSQL = "SELECT time.* " +
                "FROM time INNER JOIN jogador " +
                "ON jogador.time_codigo = time.codigo ";

        String jogadorSQL = "SELECT * FROM jogador";

        Cursor cursor = database.rawQuery(timeSQL, null);

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

        cursor = database.rawQuery(jogadorSQL, null);

        List<Jogador> jogadores = new ArrayList<>();
        List<String> dadosJogadores;

        if(cursor != null){
            Buscador buscador = new Buscador(cursor);
            dadosJogadores = buscador.listarTodos();
            for(String dado : dadosJogadores){
                Jogador jogador = new Jogador();
                jogador.organizarDados(dado);
                jogadores.add(jogador);
            }
        }
        cursor.close();

        int i = 0;
        for(Jogador jog : jogadores){
           jog.setTime(times.get(i));
           i++;
        }
        return jogadores;
    }

    private static ContentValues getContentValues(Jogador jogador) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", jogador.getId());
        contentValues.put("nome", jogador.getNome());
        contentValues.put("data_nasc", String.valueOf(jogador.getDataNasc()));
        contentValues.put("altura", jogador.getAltura());
        contentValues.put("peso", jogador.getPeso());
        contentValues.put("time_codigo", jogador.getTime().getCodigo());

        return contentValues;
    }
}
