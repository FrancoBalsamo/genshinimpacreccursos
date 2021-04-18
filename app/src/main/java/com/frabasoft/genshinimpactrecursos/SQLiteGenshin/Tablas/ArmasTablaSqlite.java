package com.frabasoft.genshinimpactrecursos.SQLiteGenshin.Tablas;

public class ArmasTablaSqlite {
    //Tabla Armas
    public static final String TABLA_ARMA = "ArmaSqlite";

    //Columnas
    public static final String ID_ARMA = "idArma";
    public static final String NOMBRE_PERSONAJE = "nombrePresonaje";
    public static final String ARMA_NOMBRE = "armaNombre";

    //String con el create
    public static final String TABLA_ARMA_SQL =
            "CREATE TABLE  " + TABLA_ARMA + "(" +
                    ID_ARMA + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    NOMBRE_PERSONAJE + " TEXT NOT NULL," +
                    ARMA_NOMBRE + " TEXT NOT NULL);" ;
}
