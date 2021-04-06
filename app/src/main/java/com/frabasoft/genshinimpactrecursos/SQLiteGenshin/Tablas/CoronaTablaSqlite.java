package com.frabasoft.genshinimpactrecursos.SQLiteGenshin.Tablas;

public class CoronaTablaSqlite {
    //Tabla Corono
    public static final String TABLA_CORONA = "CoronaSqlite";

    //Columnas
    public static final String ID_STAT_CORONA = "idCorona";
    public static final String NOMBRE_PERSONAJE = "nombrepresonaje";
    public static final String STAT_PRINC = "statprincipal";
    public static final String STAT_SEC_A = "statsecundarioa";
    public static final String STAT_SEC_B = "statsecundariob";
    public static final String STAT_SEC_C = "statsecundarioc";
    public static final String STAT_SEC_D = "statsecundariod";

    //String con el create
    public static final String TABLA_CORONA_SQL =
            "CREATE TABLE  " + TABLA_CORONA + "(" +
                    ID_STAT_CORONA + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    NOMBRE_PERSONAJE + " TEXT NOT NULL," +
                    STAT_PRINC + " TEXT NOT NULL," +
                    STAT_SEC_A + " TEXT NOT NULL," +
                    STAT_SEC_B + " TEXT NOT NULL," +
                    STAT_SEC_C + " TEXT NOT NULL," +
                    STAT_SEC_D + " TEXT NOT NULL);" ;
}
