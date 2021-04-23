package com.frabasoft.genshinimpactrecursos.SQLiteGenshin.Tablas;

public class RelojImagenSqlite {
    public static final String TABLA_RELOJ_IMAGEN= "RelojArtefactoImagen";
    //Columnas
    public static final String ID_PK_ARTEFACTO_RELOJ = "idPK";
    public static final String NOMBRE_PERSONAJE = "nombrePJ";
    public static final String ID_SELECCION_RELOJ = "idSeleccionReloj";
    public static final String RELOJ_ARTEFACTO = "arteFactoFlorReloj";
    public static final String RECURSO_ARTEFACTO_RELOJ= "recursoArtefactoReloj";

    //String con el create
    public static final String TABLA_RELOJ_RECURSO_ARTEFACTO_SQL =
            "CREATE TABLE  IF NOT EXISTS  " + TABLA_RELOJ_IMAGEN + "(" +
                    ID_PK_ARTEFACTO_RELOJ + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NOMBRE_PERSONAJE + " TEXT NOT NULL, " +
                    ID_SELECCION_RELOJ + " INTEGER NOT NULL, "+
                    RELOJ_ARTEFACTO + " TEXT NOT NULL, " +
                    RECURSO_ARTEFACTO_RELOJ + " TEXT NOT NULL);";
}
