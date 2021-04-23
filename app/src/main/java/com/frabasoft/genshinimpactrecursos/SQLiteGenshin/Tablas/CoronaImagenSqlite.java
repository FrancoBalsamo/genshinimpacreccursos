package com.frabasoft.genshinimpactrecursos.SQLiteGenshin.Tablas;

public class CoronaImagenSqlite {
    public static final String TABLA_CORONA_IMAGEN= "CoronajArtefactoImagen";
    //corona↓
    public static final String ID_PK_ARTEFACTO_CORONA = "idPK";
    public static final String NOMBRE_PERSONAJE = "nombrePJ";
    public static final String ID_SELECCION_CORONA = "idSeleccionCorona";
    public static final String CORONA_ARTEFACTO = "arteFactoCorona";
    public static final String RECURSO_ARTEFACTO_CORONA = "recursoArtefactoCorona";

    //String con el create
    public static final String TABLA_COPA_RECURSO_ARTEFACTO_SQL =
            "CREATE TABLE  IF NOT EXISTS  " + TABLA_CORONA_IMAGEN + "(" +
                    ID_PK_ARTEFACTO_CORONA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NOMBRE_PERSONAJE + " TEXT NOT NULL, " +
                    ID_SELECCION_CORONA + " INTEGER NOT NULL, "+
                    CORONA_ARTEFACTO + " TEXT NOT NULL, " +
                    RECURSO_ARTEFACTO_CORONA + " TEXT NOT NULL);";
}