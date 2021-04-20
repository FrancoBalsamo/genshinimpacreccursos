package com.frabasoft.genshinimpactrecursos.SQLiteGenshin.Tablas;

public class ArtefactosSqlite {
    public static final String TABLA_ARTEFACTOS= "ArtefactosSqlite";

    //Columnas
    public static final String ID_PK_ARTEFACTO = "idPK";
    //flor↓
    public static final String ID_SELECCION_FLOR = "idSeleccionFlor";
    public static final String FLOR_ARTEFACTO = "arteFactoFlor";
    public static final String RECURSO_ARTEFACTO_FLOR = "recursoArtefactoFlor";
    //pluma↓
    public static final String ID_SELECCION_PLUMA = "idSeleccionPluma";
    public static final String PLUMA_ARTEFACTO = "arteFactoPluma";
    public static final String RECURSO_ARTEFACTO_PLUMA= "recursoArtefactoPluma";
    //reloj↓
    public static final String ID_SELECCION_RELOJ = "idSeleccionReloj";
    public static final String RELOJ_ARTEFACTO = "arteFactoFlorReloj";
    public static final String RECURSO_ARTEFACTO_RELOJ= "recursoArtefactoReloj";
    //copa↓
    public static final String ID_SELECCION_COPA = "idSeleccionCopa";
    public static final String COPA_ARTEFACTO = "arteFactoCopa";
    public static final String RECURSO_ARTEFACTO_COPA = "recursoArtefactoCopa";
    //corona↓
    public static final String ID_SELECCION_CORONA = "idSeleccionCorona";
    public static final String CORONA_ARTEFACTO = "arteFactoCorona";
    public static final String RECURSO_ARTEFACTO_CORONA = "recursoArtefactoCorona";

    //String con el create
    public static final String TABLA_ARTEFACTOS_SQL =
            "CREATE TABLE  IF NOT EXISTS  " + TABLA_ARTEFACTOS + "(" +
                    ID_PK_ARTEFACTO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

                    ID_SELECCION_FLOR + "INTEGER NOT NULL, "+
                    FLOR_ARTEFACTO + " TEXT NOT NULL, " +
                    RECURSO_ARTEFACTO_FLOR + " TEXT NOT NULL, " +

                    ID_SELECCION_PLUMA + "INTEGER NOT NULL, "+
                    PLUMA_ARTEFACTO + " TEXT NOT NULL, " +
                    RECURSO_ARTEFACTO_PLUMA + " TEXT NOT NULL, " +

                    ID_SELECCION_RELOJ + "INTEGER NOT NULL, "+
                    RELOJ_ARTEFACTO + " TEXT NOT NULL, " +
                    RECURSO_ARTEFACTO_RELOJ + " TEXT NOT NULL, " +

                    ID_SELECCION_COPA + "INTEGER NOT NULL, "+
                    COPA_ARTEFACTO+ " TEXT NOT NULL, " +
                    RECURSO_ARTEFACTO_COPA + " TEXT NOT NULL, " +

                    ID_SELECCION_CORONA + "INTEGER NOT NULL, "+
                    CORONA_ARTEFACTO + " TEXT NOT NULL, " +
                    RECURSO_ARTEFACTO_CORONA + " TEXT NOT NULL);";
}
