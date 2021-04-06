package com.frabasoft.genshinimpactrecursos.SQLiteGenshin.Procesos;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.frabasoft.genshinimpactrecursos.Clases.Copa;
import com.frabasoft.genshinimpactrecursos.Clases.Flor;
import com.frabasoft.genshinimpactrecursos.SQLiteGenshin.NombreVersionSqlite;
import com.frabasoft.genshinimpactrecursos.SQLiteGenshin.Tablas.CopaTablaSqlite;
import com.frabasoft.genshinimpactrecursos.SQLiteGenshin.Tablas.CoronaTablaSqlite;
import com.frabasoft.genshinimpactrecursos.SQLiteGenshin.Tablas.FlorTablaSqlite;
import com.frabasoft.genshinimpactrecursos.SQLiteGenshin.Tablas.PlumaTablaSqlite;
import com.frabasoft.genshinimpactrecursos.SQLiteGenshin.Tablas.RelojTablaSqlite;

import java.io.Serializable;

public class DatosProcesosSqlite implements Serializable {
    private SQLiteDatabase sqLiteDatabase;
    private DBHelper dbHelper;

    public DatosProcesosSqlite(Context contexto){
        dbHelper = new DBHelper(contexto);
    }
    private void abrirDBLeer(){
        sqLiteDatabase = dbHelper.getReadableDatabase();
    }
    private void abrirDBEsccribir(){
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    private void cerrarBD(){
        if(dbHelper != null){
            dbHelper.close();
        }
    }

    /////////////////////////////////////////////////////////////////////////FLOR SAVE DATA SQLITE
    private ContentValues mapeoFlor(Flor flor){
        ContentValues contentValues = new ContentValues();
        contentValues.put(FlorTablaSqlite.STAT_PRINC, flor.getPrincipal());
        contentValues.put(FlorTablaSqlite.NOMBRE_PERSONAJE, flor.getNombrePersonaje());
        contentValues.put(FlorTablaSqlite.STAT_SEC_A, flor.getSecundarioA());
        contentValues.put(FlorTablaSqlite.STAT_SEC_B, flor.getSecundarioB());
        contentValues.put(FlorTablaSqlite.STAT_SEC_C, flor.getSecundarioC());
        contentValues.put(FlorTablaSqlite.STAT_SEC_D, flor.getSecundarioD());
        return contentValues;
    }

    private long guardarFlor(Flor flor){//insertar registro
        this.abrirDBEsccribir();
        long filaID = sqLiteDatabase.insert(FlorTablaSqlite.TABLA_FLOR, null, mapeoFlor(flor));
        return filaID;
    }

    private void actualizarFlor(Flor flor, String id){
        this.abrirDBEsccribir();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FlorTablaSqlite.STAT_PRINC, flor.getPrincipal());
        contentValues.put(FlorTablaSqlite.STAT_SEC_A, flor.getSecundarioA());
        contentValues.put(FlorTablaSqlite.STAT_SEC_B, flor.getSecundarioB());
        contentValues.put(FlorTablaSqlite.STAT_SEC_C, flor.getSecundarioC());
        contentValues.put(FlorTablaSqlite.STAT_SEC_D, flor.getSecundarioD());
        String[] idValor = {String.valueOf(id)};
        sqLiteDatabase.update(FlorTablaSqlite.TABLA_FLOR, contentValues, FlorTablaSqlite.ID_STAT_FLOR + " = ?", idValor);
        sqLiteDatabase.close();
    }

    /////////////////////////////////////////////////////////////////////////Creación de tablas al instalar
    private static class DBHelper extends SQLiteOpenHelper{
        public DBHelper(Context contexto){
            super(contexto, NombreVersionSqlite.DB_NAME, null, NombreVersionSqlite.DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL(CopaTablaSqlite.TABLA_COPA_SQL);
            db.execSQL(CoronaTablaSqlite.TABLA_CORONA_SQL);
            db.execSQL(FlorTablaSqlite.TABLA_FLOR_SQL);
            db.execSQL(PlumaTablaSqlite.TABLA_PLUMA_SQL);
            db.execSQL(RelojTablaSqlite.TABLA_RELOJ_SQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){}
    }
}
