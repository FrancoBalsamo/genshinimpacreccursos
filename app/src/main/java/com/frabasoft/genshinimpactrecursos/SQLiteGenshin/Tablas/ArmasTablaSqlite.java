package com.frabasoft.genshinimpactrecursos.SQLiteGenshin.Tablas;

public class ArmasTablaSqlite {
    //Tabla Armas
    public static final String TABLA_ARMA = "ArmaSqlite";

    //Columnas
    public static final String ID_ARMA = "idArma";
    public static final String NOMBRE_PERSONAJE = "nombrePresonaje";
    public static final String ARMA_NOMBRE = "armaNombre";
    public static final String RECURSO_IMAGEN = "recursoImagen";

    //String con el create
    public static final String TABLA_ARMA_SQL =
            "CREATE TABLE  IF NOT EXISTS " + TABLA_ARMA + "(" +
                    ID_ARMA + " INTEGER, " +
                    NOMBRE_PERSONAJE + " TEXT NOT NULL, " +
                    ARMA_NOMBRE + " TEXT NOT NULL, " +
                    RECURSO_IMAGEN + " TEXT NOT NULL);";
}
