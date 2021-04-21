package com.frabasoft.genshinimpactrecursos.Clases;

public class ArtefactosADT
{
    private String nombre;
    private int icono;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIcono() {
        return icono;
    }

    public void setIcono(int icono) {
        this.icono = icono;
    }

    public ArtefactosADT(String nombre, int icono) {
        this.nombre = nombre;
        this.icono = icono;
    }
}
