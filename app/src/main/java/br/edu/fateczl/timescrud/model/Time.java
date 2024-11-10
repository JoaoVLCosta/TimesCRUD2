package br.edu.fateczl.timescrud.model;

public class Time {
    /*
     *@author:<JOÃO VITOR LIMA COSTA>
     */

    private int codigo;
    private String nome;
    private String cidade;

    public Time() {
        super();
    }

    @Override
    public String toString() {
        return codigo + " - " + nome + " - " + cidade;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
