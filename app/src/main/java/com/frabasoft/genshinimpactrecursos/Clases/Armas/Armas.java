package com.frabasoft.genshinimpactrecursos.Clases.Armas;

public class Armas {
    private int id;
    private String personaje;
    private String nombreArma;
    private int recursoImagen;

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

    public String getNombreArma() {
        return nombreArma;
    }

    public void setNombreArma(String nombreArma) {
        this.nombreArma = nombreArma;
    }

    public int getRecursoImagen() {
        return recursoImagen;
    }

    public void setRecursoImagen(int recursoImagen) {
        this.recursoImagen = recursoImagen;
    }

    public Armas(){}

    public Armas(String personaje, String nombreArma, int recursoImagen) {
        this.personaje = personaje;
        this.nombreArma = nombreArma;
        this.recursoImagen = recursoImagen;
    }
}