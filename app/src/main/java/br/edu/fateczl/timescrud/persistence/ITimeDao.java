package br.edu.fateczl.timescrud.persistence;

import java.sql.SQLException;

public interface ITimeDao {
    /*
     *@author:<JOÃO VITOR LIMA COSTA>
     */

    public TimeDao open() throws SQLException;
    public void close();
}
