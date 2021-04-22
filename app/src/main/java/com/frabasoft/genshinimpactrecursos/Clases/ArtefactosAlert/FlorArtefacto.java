package com.frabasoft.genshinimpactrecursos.Clases.ArtefactosAlert;

import com.frabasoft.genshinimpactrecursos.Clases.Artefactos.Flor;

public class FlorArtefacto {
    private int idPK;
    //↓Flor
    private int seleccionFlor;
    private String nombreFlor;
    private int recursoFlor;

    public int getIdPK() {
        return idPK;
    }

    public void setIdPK(int idPK) {
        this.idPK = idPK;
    }

    public int getSeleccionFlor() {
        return seleccionFlor;
    }

    public void setSeleccionFlor(int seleccionFlor) {
        this.seleccionFlor = seleccionFlor;
    }

    public String getNombreFlor() {
        return nombreFlor;
    }

    public void setNombreFlor(String nombreFlor) {
        this.nombreFlor = nombreFlor;
    }

    public int getRecursoFlor() {
        return recursoFlor;
    }

    public void setRecursoFlor(int recursoFlor) {
        this.recursoFlor = recursoFlor;
    }

    public FlorArtefacto(){}

    public FlorArtefacto(int seleccionFlor, String nombreFlor, int recursoFlor) {
        this.seleccionFlor = seleccionFlor;
        this.nombreFlor = nombreFlor;
        this.recursoFlor = recursoFlor;
    }

    public FlorArtefacto(int seleccionFlor, String nombreFlor){
        this.seleccionFlor = seleccionFlor;
        this.nombreFlor = nombreFlor;
    }
}