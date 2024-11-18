package br.edu.fateczl.timescrud.persistence;

import java.sql.SQLException;

public interface IJogadorDao {
    /*
     *@author:<JOÃO VITOR LIMA COSTA>
     */

    public JogadorDao open() throws SQLException;
    public void close();

}
