package com.frabasoft.genshinimpactrecursos.SQLiteGenshin.Tablas;

public class FlorImagenSqlite {
    public static final String TABLA_FLOR_IMAGEN= "FlorArtefactoImagen";
    //Columnas
    public static final String ID_PK_ARTEFACTO_FLOR = "idPK";
    public static final String NOMBRE_PERSONAJE = "nombrePJ";
    public static final String ID_SELECCION_FLOR = "idSeleccionFlor";
    public static final String FLOR_ARTEFACTO = "arteFactoFlor";
    public static final String RECURSO_ARTEFACTO_FLOR = "recursoArtefactoFlor";

    //String con el create
    public static final String TABLA_FLOR_RECURSO_ARTEFACTO_SQL =
            "CREATE TABLE  IF NOT EXISTS  " + TABLA_FLOR_IMAGEN + "(" +
                    ID_PK_ARTEFACTO_FLOR + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NOMBRE_PERSONAJE + " TEXT NOT NULL, " +
                    ID_SELECCION_FLOR + " INTEGER NOT NULL, "+
                    FLOR_ARTEFACTO + " TEXT NOT NULL, " +
                    RECURSO_ARTEFACTO_FLOR + " TEXT NOT NULL);";
}