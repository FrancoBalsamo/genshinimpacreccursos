package com.frabasoft.genshinimpactrecursos.SQLiteGenshin.Tablas;

public class PlumaImagenSqlite {
    public static final String TABLA_PLUMA_IMAGEN= "PlumaArtefactoImagen";
    //Columnas
    public static final String ID_PK_ARTEFACTO_PLUMA = "idPK";
    public static final String ID_SELECCION_PLUMA = "idSeleccionPluma";
    public static final String PLUMA_ARTEFACTO = "arteFactoPluma";
    public static final String RECURSO_ARTEFACTO_PLUMA= "recursoArtefactoPluma";

    //String con el create
    public static final String TABLA_PLUMA_RECURSO_ARTEFACTO_SQL =
            "CREATE TABLE  IF NOT EXISTS  " + TABLA_PLUMA_IMAGEN + "(" +
                    ID_PK_ARTEFACTO_PLUMA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ID_SELECCION_PLUMA + " INTEGER NOT NULL, "+
                    PLUMA_ARTEFACTO + " TEXT NOT NULL, " +
                    RECURSO_ARTEFACTO_PLUMA + " TEXT NOT NULL);";
}