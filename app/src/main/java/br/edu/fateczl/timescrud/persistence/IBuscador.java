package br.edu.fateczl.timescrud.persistence;

import java.sql.SQLException;
import java.util.List;

public interface IBuscador {
    /*
     *@author:<JOÃO VITOR LIMA COSTA>
     */

    public String buscarUm() throws SQLException;
    public List<String> listarTodos() throws SQLException;
}
