package com.frabasoft.genshinimpactrecursos.Clases.ArtefactosAlert;

public class PlumaArtefacto {
    private int idPK;
    private String nombrePJ;
    private int seleccionDatoSpiner;
    private String nombreArtefacto;
    private int recursoArtefacto;

    public int getIdPK() {
        return idPK;
    }

    public void setIdPK(int idPK) {
        this.idPK = idPK;
    }

    public int getSeleccionDatoSpiner() {
        return seleccionDatoSpiner;
    }

    public String getNombrePJ() { return nombrePJ; }

    public void setNombrePJ(String nombrePJ) { this.nombrePJ = nombrePJ; }

    public void setSeleccionDatoSpiner(int seleccionDatoSpiner) {
        this.seleccionDatoSpiner = seleccionDatoSpiner;
    }

    public String getNombreArtefacto() {
        return nombreArtefacto;
    }

    public void setNombreArtefacto(String nombreArtefacto) {
        this.nombreArtefacto = nombreArtefacto;
    }

    public int getRecursoArtefacto() {
        return recursoArtefacto;
    }

    public void setRecursoArtefacto(int recursoArtefacto) {
        this.recursoArtefacto = recursoArtefacto;
    }

    public PlumaArtefacto(){}

    public PlumaArtefacto(int seleccionDatoSpiner, String nombreArtefacto){
        this.seleccionDatoSpiner = seleccionDatoSpiner;
        this.nombreArtefacto = nombreArtefacto;
    }

    public PlumaArtefacto(String nombrePJ, int seleccionDatoSpiner, String nombreArtefacto, int recursoArtefacto) {
        this.nombrePJ = nombrePJ;
        this.seleccionDatoSpiner = seleccionDatoSpiner;
        this.nombreArtefacto = nombreArtefacto;
        this.recursoArtefacto = recursoArtefacto;
    }
}
