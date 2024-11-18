package br.edu.fateczl.timescrud.model;

import java.time.LocalDate;

public class Jogador{
    /*
     *@author:<JOÃO VITOR LIMA COSTA>
     */

    private int id;
    private String nome;
    private LocalDate dataNasc;
    private float altura;
    private float peso;
    private Time time;

    public Jogador() {
        super();
    }

    @Override
    public String toString() {
        return id + " - " + nome + " - " + dataNasc + " - " + altura + " - " + peso + " - " + time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void organizarDados(String dados) {
        setId(Integer.parseInt(dados.split(";")[0]));
        setNome(dados.split(";")[1]);
        setDataNasc(LocalDate.parse(dados.split(";")[2]));
        setAltura(Float.parseFloat(dados.split(";")[3]));
        setPeso(Float.parseFloat(dados.split(";")[4]));
    }
}
