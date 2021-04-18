package com.frabasoft.genshinimpactrecursos.Clases.Armas;

public class Armas {
    private int id;
    private String personaje;
    private int arma;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPersonaje() {
        return personaje;
    }

    public void setPersonaje(String personaje) {
        this.personaje = personaje;
    }

    public int getArma() {
        return arma;
    }

    public void setArma(int arma) {
        this.arma = arma;
    }

    public Armas(){}

    public Armas(String personaje, int arma) {
        this.personaje = personaje;
        this.arma = arma;
    }
}