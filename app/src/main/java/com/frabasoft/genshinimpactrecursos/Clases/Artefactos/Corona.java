package com.frabasoft.genshinimpactrecursos.Clases.Artefactos;

public class Corona {
    private int id;
    private String nombrePersonaje;
    private String principal;
    private String secundarioA;
    private String secundarioB;
    private String secundarioC;
    private String secundarioD;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombrePersonaje() {
        return nombrePersonaje;
    }

    public void setNombrePersonaje(String nombrePersonaje) {
        this.nombrePersonaje = nombrePersonaje;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getSecundarioA() {
        return secundarioA;
    }

    public void setSecundarioA(String secundarioA) {
        this.secundarioA = secundarioA;
    }

    public String getSecundarioB() {
        return secundarioB;
    }

    public void setSecundarioB(String secundarioB) {
        this.secundarioB = secundarioB;
    }

    public String getSecundarioC() {
        return secundarioC;
    }

    public void setSecundarioC(String secundarioC) {
        this.secundarioC = secundarioC;
    }

    public String getSecundarioD() {
        return secundarioD;
    }

    public void setSecundarioD(String secundarioD) {
        this.secundarioD = secundarioD;
    }

    public Corona(String nombrePersonaje, String principal, String secundarioA, String secundarioB, String secundarioC,
                String secundarioD) {
        this.nombrePersonaje = nombrePersonaje;
        this.principal = principal;
        this.secundarioA = secundarioA;
        this.secundarioB = secundarioB;
        this.secundarioC = secundarioC;
        this.secundarioD = secundarioD;
    }

    public Corona(){}
}
