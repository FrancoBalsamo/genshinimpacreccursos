package com.frabasoft.genshinimpactrecursos.SQLiteGenshin.Tablas;

public class CopaImagenSqlite {
    public static final String TABLA_COPA_IMAGEN= "CopajArtefactoImagen";
    //copa↓
    public static final String ID_PK_ARTEFACTO_COPA = "idPK";
    public static final String NOMBRE_PERSONAJE = "nombrePJ";
    public static final String ID_SELECCION_COPA = "idSeleccionCopa";
    public static final String COPA_ARTEFACTO = "arteFactoCopa";
    public static final String RECURSO_ARTEFACTO_COPA = "recursoArtefactoCopa";

    //String con el create
    public static final String TABLA_COPA_RECURSO_ARTEFACTO_SQL =
            "CREATE TABLE  IF NOT EXISTS  " + TABLA_COPA_IMAGEN + "(" +
                    ID_PK_ARTEFACTO_COPA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NOMBRE_PERSONAJE + " TEXT NOT NULL, " +
                    ID_SELECCION_COPA + " INTEGER NOT NULL, "+
                    COPA_ARTEFACTO + " TEXT NOT NULL, " +
                    RECURSO_ARTEFACTO_COPA + " TEXT NOT NULL);";
}
