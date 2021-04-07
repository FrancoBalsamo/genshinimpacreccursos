package com.frabasoft.genshinimpactrecursos.SQLiteGenshin.Procesos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.frabasoft.genshinimpactrecursos.Clases.Copa;
import com.frabasoft.genshinimpactrecursos.Clases.Corona;
import com.frabasoft.genshinimpactrecursos.Clases.Flor;
import com.frabasoft.genshinimpactrecursos.Clases.Pluma;
import com.frabasoft.genshinimpactrecursos.Clases.Reloj;
import com.frabasoft.genshinimpactrecursos.SQLiteGenshin.NombreVersionSqlite;
import com.frabasoft.genshinimpactrecursos.SQLiteGenshin.Tablas.CopaTablaSqlite;
import com.frabasoft.genshinimpactrecursos.SQLiteGenshin.Tablas.CoronaTablaSqlite;
import com.frabasoft.genshinimpactrecursos.SQLiteGenshin.Tablas.FlorTablaSqlite;
import com.frabasoft.genshinimpactrecursos.SQLiteGenshin.Tablas.PlumaTablaSqlite;
import com.frabasoft.genshinimpactrecursos.SQLiteGenshin.Tablas.RelojTablaSqlite;

import java.io.Serializable;
import java.util.ArrayList;

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

    public long guardarFlor(Flor flor){//insertar registro
        this.abrirDBEsccribir();
        long filaID = sqLiteDatabase.insert(FlorTablaSqlite.TABLA_FLOR, null, mapeoFlor(flor));
        return filaID;
    }

    public void actualizarFlor(Flor flor, String nombrePJ){
        this.abrirDBEsccribir();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FlorTablaSqlite.STAT_PRINC, flor.getPrincipal());
        contentValues.put(FlorTablaSqlite.STAT_SEC_A, flor.getSecundarioA());
        contentValues.put(FlorTablaSqlite.STAT_SEC_B, flor.getSecundarioB());
        contentValues.put(FlorTablaSqlite.STAT_SEC_C, flor.getSecundarioC());
        contentValues.put(FlorTablaSqlite.STAT_SEC_D, flor.getSecundarioD());
        String[] idValor = {String.valueOf(nombrePJ)};
        sqLiteDatabase.update(FlorTablaSqlite.TABLA_FLOR, contentValues, FlorTablaSqlite.NOMBRE_PERSONAJE + " = ? ", idValor);
        sqLiteDatabase.close();
    }

    public boolean validarUInsertUpdateFlor(Context actividad, String personaje, Flor flor){//meétodo para validar el estado no visible del manga
        this.abrirDBEsccribir();
        String[] nom = {String.valueOf(personaje)};
        String consulta = "SELECT * FROM " + FlorTablaSqlite.TABLA_FLOR + " WHERE " + FlorTablaSqlite.NOMBRE_PERSONAJE + " = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(consulta, nom);
        if(cursor.getCount() <= 0){
            cursor.close();
            guardarFlor(flor);
            Toast.makeText(actividad, "¡Se ha creado un nuevo registro de artefacto!", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            if(cursor.moveToFirst()){
                String pj = cursor.getString(cursor.getColumnIndex(FlorTablaSqlite.NOMBRE_PERSONAJE));
                actualizarFlor(flor, pj);
                Toast.makeText(actividad, "¡Se ha actualizado la build de: " + personaje + "!", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        cursor.close();
        return true;
    }

    public ArrayList mostrarDatosDelPjFlor(String nombrePJ) {
        ArrayList list = new ArrayList<>();
        this.abrirDBLeer();
        String[] campos = new String[]{FlorTablaSqlite.ID_STAT_FLOR, FlorTablaSqlite.NOMBRE_PERSONAJE, FlorTablaSqlite.STAT_PRINC,
                FlorTablaSqlite.STAT_SEC_A, FlorTablaSqlite.STAT_SEC_B, FlorTablaSqlite.STAT_SEC_C, FlorTablaSqlite.STAT_SEC_D};
        String where = FlorTablaSqlite.NOMBRE_PERSONAJE + " IN ('" + nombrePJ + "');";
        Cursor c = sqLiteDatabase.query(FlorTablaSqlite.TABLA_FLOR, campos, where, null, null, null, null);
        try {
            while (c.moveToNext()) {
                Flor flor = new Flor();
                flor.setId(c.getInt(0));
                flor.setNombrePersonaje(c.getString(1));
                flor.setPrincipal(c.getString(2));
                flor.setSecundarioA(c.getString(3));
                flor.setSecundarioB(c.getString(4));
                flor.setSecundarioC(c.getString(5));
                flor.setSecundarioD(c.getString(6));
                list.add(flor);
            }
        } finally { c.close(); }
        this.cerrarBD();
        return list;
    }

    /////////////////////////////////////////////////////////////////////////Pluma SAVE DATA SQLITE
    private ContentValues mapeoPluma(Pluma pluma){
        ContentValues contentValues = new ContentValues();
        contentValues.put(PlumaTablaSqlite.STAT_PRINC, pluma.getPrincipal());
        contentValues.put(PlumaTablaSqlite.NOMBRE_PERSONAJE, pluma.getNombrePersonaje());
        contentValues.put(PlumaTablaSqlite.STAT_SEC_A, pluma.getSecundarioA());
        contentValues.put(PlumaTablaSqlite.STAT_SEC_B, pluma.getSecundarioB());
        contentValues.put(PlumaTablaSqlite.STAT_SEC_C, pluma.getSecundarioC());
        contentValues.put(PlumaTablaSqlite.STAT_SEC_D, pluma.getSecundarioD());
        return contentValues;
    }

    public long guardarPluma(Pluma pluma){//insertar registro
        this.abrirDBEsccribir();
        long filaID = sqLiteDatabase.insert(PlumaTablaSqlite.TABLA_PLUMA, null, mapeoPluma(pluma));
        return filaID;
    }

    public void actualizarPluma(Pluma pluma, String nombrePJ){
        this.abrirDBEsccribir();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PlumaTablaSqlite.STAT_PRINC, pluma.getPrincipal());
        contentValues.put(PlumaTablaSqlite.STAT_SEC_A, pluma.getSecundarioA());
        contentValues.put(PlumaTablaSqlite.STAT_SEC_B, pluma.getSecundarioB());
        contentValues.put(PlumaTablaSqlite.STAT_SEC_C, pluma.getSecundarioC());
        contentValues.put(PlumaTablaSqlite.STAT_SEC_D, pluma.getSecundarioD());
        String[] idValor = {String.valueOf(nombrePJ)};
        sqLiteDatabase.update(PlumaTablaSqlite.TABLA_PLUMA, contentValues, PlumaTablaSqlite.NOMBRE_PERSONAJE + " = ? ", idValor);
        sqLiteDatabase.close();
    }

    public boolean validarUInsertUpdatePluma(Context actividad, String personaje, Pluma pluma){//meétodo para validar el estado no visible del manga
        this.abrirDBEsccribir();
        String[] nom = {String.valueOf(personaje)};
        String consulta = "SELECT * FROM " + PlumaTablaSqlite.TABLA_PLUMA + " WHERE " + PlumaTablaSqlite.NOMBRE_PERSONAJE + " = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(consulta, nom);
        if(cursor.getCount() <= 0){
            cursor.close();
            guardarPluma(pluma);
            Toast.makeText(actividad, "¡Se ha creado un nuevo registro de artefacto!", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            if(cursor.moveToFirst()){
                String pj = cursor.getString(cursor.getColumnIndex(PlumaTablaSqlite.NOMBRE_PERSONAJE));
                actualizarPluma(pluma, pj);
                Toast.makeText(actividad, "¡Se ha actualizado la build de: " + personaje + "!", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        cursor.close();
        return true;
    }

    public ArrayList mostrarDatosDelPjPluma(String nombrePJ) {
        ArrayList list = new ArrayList<>();
        this.abrirDBLeer();
        String[] campos = new String[]{PlumaTablaSqlite.ID_STAT_PLUMA, PlumaTablaSqlite.NOMBRE_PERSONAJE, PlumaTablaSqlite.STAT_PRINC,
                PlumaTablaSqlite.STAT_SEC_A, PlumaTablaSqlite.STAT_SEC_B, PlumaTablaSqlite.STAT_SEC_C, PlumaTablaSqlite.STAT_SEC_D};
        String where = PlumaTablaSqlite.NOMBRE_PERSONAJE + " IN ('" + nombrePJ + "');";
        Cursor c = sqLiteDatabase.query(PlumaTablaSqlite.TABLA_PLUMA, campos, where, null, null, null, null);
        try {
            while (c.moveToNext()) {
                Pluma pl = new Pluma();
                pl.setId(c.getInt(0));
                pl.setNombrePersonaje(c.getString(1));
                pl.setPrincipal(c.getString(2));
                pl.setSecundarioA(c.getString(3));
                pl.setSecundarioB(c.getString(4));
                pl.setSecundarioC(c.getString(5));
                pl.setSecundarioD(c.getString(6));
                list.add(pl);
            }
        } finally { c.close(); }
        this.cerrarBD();
        return list;
    }

    /////////////////////////////////////////////////////////////////////////Reloj SAVE DATA SQLITE
    private ContentValues mapeoReloj(Reloj reloj){
        ContentValues contentValues = new ContentValues();
        contentValues.put(RelojTablaSqlite.STAT_PRINC, reloj.getPrincipal());
        contentValues.put(RelojTablaSqlite.NOMBRE_PERSONAJE, reloj.getNombrePersonaje());
        contentValues.put(RelojTablaSqlite.STAT_SEC_A, reloj.getSecundarioA());
        contentValues.put(RelojTablaSqlite.STAT_SEC_B, reloj.getSecundarioB());
        contentValues.put(RelojTablaSqlite.STAT_SEC_C, reloj.getSecundarioC());
        contentValues.put(RelojTablaSqlite.STAT_SEC_D, reloj.getSecundarioD());
        return contentValues;
    }

    public long guardarReloj(Reloj reloj){//insertar registro
        this.abrirDBEsccribir();
        long filaID = sqLiteDatabase.insert(RelojTablaSqlite.TABLA_RELOJ, null, mapeoReloj(reloj));
        return filaID;
    }

    public void actualizarReloj(Reloj reloj, String nombrePJ){
        this.abrirDBEsccribir();
        ContentValues contentValues = new ContentValues();
        contentValues.put(RelojTablaSqlite.STAT_PRINC, reloj.getPrincipal());
        contentValues.put(RelojTablaSqlite.STAT_SEC_A, reloj.getSecundarioA());
        contentValues.put(RelojTablaSqlite.STAT_SEC_B, reloj.getSecundarioB());
        contentValues.put(RelojTablaSqlite.STAT_SEC_C, reloj.getSecundarioC());
        contentValues.put(RelojTablaSqlite.STAT_SEC_D, reloj.getSecundarioD());
        String[] idValor = {String.valueOf(nombrePJ)};
        sqLiteDatabase.update(RelojTablaSqlite.TABLA_RELOJ, contentValues, RelojTablaSqlite.NOMBRE_PERSONAJE + " = ? ", idValor);
        sqLiteDatabase.close();
    }

    public boolean validarUInsertUpdateReloj(Context actividad, String personaje, Reloj reloj){//meétodo para validar el estado no visible del manga
        this.abrirDBEsccribir();
        String[] nom = {String.valueOf(personaje)};
        String consulta = "SELECT * FROM " + RelojTablaSqlite.TABLA_RELOJ + " WHERE " + RelojTablaSqlite.NOMBRE_PERSONAJE + " = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(consulta, nom);
        if(cursor.getCount() <= 0){
            cursor.close();
            guardarReloj(reloj);
            Toast.makeText(actividad, "¡Se ha creado un nuevo registro de artefacto!", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            if(cursor.moveToFirst()){
                String pj = cursor.getString(cursor.getColumnIndex(RelojTablaSqlite.NOMBRE_PERSONAJE));
                actualizarReloj(reloj, pj);
                Toast.makeText(actividad, "¡Se ha actualizado la build de: " + personaje + "!", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        cursor.close();
        return true;
    }

    public ArrayList mostrarDatosDelPjReloj(String nombrePJ) {
        ArrayList list = new ArrayList<>();
        this.abrirDBLeer();
        String[] campos = new String[]{RelojTablaSqlite.ID_STAT_RELOJ, RelojTablaSqlite.NOMBRE_PERSONAJE, RelojTablaSqlite.STAT_PRINC,
                RelojTablaSqlite.STAT_SEC_A, RelojTablaSqlite.STAT_SEC_B, RelojTablaSqlite.STAT_SEC_C, RelojTablaSqlite.STAT_SEC_D};
        String where = RelojTablaSqlite.NOMBRE_PERSONAJE + " IN ('" + nombrePJ + "');";
        Cursor c = sqLiteDatabase.query(RelojTablaSqlite.TABLA_RELOJ, campos, where, null, null, null, null);
        try {
            while (c.moveToNext()) {
                Reloj reloj = new Reloj();
                reloj.setId(c.getInt(0));
                reloj.setNombrePersonaje(c.getString(1));
                reloj.setPrincipal(c.getString(2));
                reloj.setSecundarioA(c.getString(3));
                reloj.setSecundarioB(c.getString(4));
                reloj.setSecundarioC(c.getString(5));
                reloj.setSecundarioD(c.getString(6));
                list.add(reloj);
            }
        } finally { c.close(); }
        this.cerrarBD();
        return list;
    }

    /////////////////////////////////////////////////////////////////////////Copa SAVE DATA SQLITE
    private ContentValues mapeoCopa(Copa copa){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CopaTablaSqlite.STAT_PRINC, copa.getPrincipal());
        contentValues.put(CopaTablaSqlite.NOMBRE_PERSONAJE, copa.getNombrePersonaje());
        contentValues.put(CopaTablaSqlite.STAT_SEC_A, copa.getSecundarioA());
        contentValues.put(CopaTablaSqlite.STAT_SEC_B, copa.getSecundarioB());
        contentValues.put(CopaTablaSqlite.STAT_SEC_C, copa.getSecundarioC());
        contentValues.put(CopaTablaSqlite.STAT_SEC_D, copa.getSecundarioD());
        return contentValues;
    }

    public long guardarCopa(Copa copa){//insertar registro
        this.abrirDBEsccribir();
        long filaID = sqLiteDatabase.insert(CopaTablaSqlite.TABLA_COPA, null, mapeoCopa(copa));
        return filaID;
    }

    public void actualizarCopa(Copa copa, String nombrePJ){
        this.abrirDBEsccribir();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CopaTablaSqlite.STAT_PRINC, copa.getPrincipal());
        contentValues.put(CopaTablaSqlite.STAT_SEC_A, copa.getSecundarioA());
        contentValues.put(CopaTablaSqlite.STAT_SEC_B, copa.getSecundarioB());
        contentValues.put(CopaTablaSqlite.STAT_SEC_C, copa.getSecundarioC());
        contentValues.put(CopaTablaSqlite.STAT_SEC_D, copa.getSecundarioD());
        String[] idValor = {String.valueOf(nombrePJ)};
        sqLiteDatabase.update(CopaTablaSqlite.TABLA_COPA, contentValues, CopaTablaSqlite.NOMBRE_PERSONAJE + " = ? ", idValor);
        sqLiteDatabase.close();
    }

    public boolean validarUInsertUpdateCopa(Context actividad, String personaje, Copa copa){//meétodo para validar el estado no visible del manga
        this.abrirDBEsccribir();
        String[] nom = {String.valueOf(personaje)};
        String consulta = "SELECT * FROM " + CopaTablaSqlite.TABLA_COPA + " WHERE " + CopaTablaSqlite.NOMBRE_PERSONAJE + " = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(consulta, nom);
        if(cursor.getCount() <= 0){
            cursor.close();
            guardarCopa(copa);
            Toast.makeText(actividad, "¡Se ha creado un nuevo registro de artefacto!", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            if(cursor.moveToFirst()){
                String pj = cursor.getString(cursor.getColumnIndex(CopaTablaSqlite.NOMBRE_PERSONAJE));
                actualizarCopa(copa, pj);
                Toast.makeText(actividad, "¡Se ha actualizado la build de: " + personaje + "!", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        cursor.close();
        return true;
    }

    public ArrayList mostrarDatosDelPjCopa(String nombrePJ) {
        ArrayList list = new ArrayList<>();
        this.abrirDBLeer();
        String[] campos = new String[]{CopaTablaSqlite.ID_STAT_COPA, CopaTablaSqlite.NOMBRE_PERSONAJE, CopaTablaSqlite.STAT_PRINC,
                CopaTablaSqlite.STAT_SEC_A, CopaTablaSqlite.STAT_SEC_B, CopaTablaSqlite.STAT_SEC_C, CopaTablaSqlite.STAT_SEC_D};
        String where = CopaTablaSqlite.NOMBRE_PERSONAJE + " IN ('" + nombrePJ + "');";
        Cursor c = sqLiteDatabase.query(CopaTablaSqlite.TABLA_COPA, campos, where, null, null, null, null);
        try {
            while (c.moveToNext()) {
                Copa copa = new Copa();
                copa.setId(c.getInt(0));
                copa.setNombrePersonaje(c.getString(1));
                copa.setPrincipal(c.getString(2));
                copa.setSecundarioA(c.getString(3));
                copa.setSecundarioB(c.getString(4));
                copa.setSecundarioC(c.getString(5));
                copa.setSecundarioD(c.getString(6));
                list.add(copa);
            }
        } finally { c.close(); }
        this.cerrarBD();
        return list;
    }

    /////////////////////////////////////////////////////////////////////////Corona SAVE DATA SQLITE
    private ContentValues mapeoCorona(Corona corona){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CoronaTablaSqlite.STAT_PRINC, corona.getPrincipal());
        contentValues.put(CoronaTablaSqlite.NOMBRE_PERSONAJE, corona.getNombrePersonaje());
        contentValues.put(CoronaTablaSqlite.STAT_SEC_A, corona.getSecundarioA());
        contentValues.put(CoronaTablaSqlite.STAT_SEC_B, corona.getSecundarioB());
        contentValues.put(CoronaTablaSqlite.STAT_SEC_C, corona.getSecundarioC());
        contentValues.put(CoronaTablaSqlite.STAT_SEC_D, corona.getSecundarioD());
        return contentValues;
    }

    public long guardarCorona(Corona corona){//insertar registro
        this.abrirDBEsccribir();
        long filaID = sqLiteDatabase.insert(CoronaTablaSqlite.TABLA_CORONA, null, mapeoCorona(corona));
        return filaID;
    }

    public void actualizarCorona(Corona coro, String nombrePJ){
        this.abrirDBEsccribir();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CoronaTablaSqlite.STAT_PRINC, coro.getPrincipal());
        contentValues.put(CoronaTablaSqlite.STAT_SEC_A, coro.getSecundarioA());
        contentValues.put(CoronaTablaSqlite.STAT_SEC_B, coro.getSecundarioB());
        contentValues.put(CoronaTablaSqlite.STAT_SEC_C, coro.getSecundarioC());
        contentValues.put(CoronaTablaSqlite.STAT_SEC_D, coro.getSecundarioD());
        String[] idValor = {String.valueOf(nombrePJ)};
        sqLiteDatabase.update(CoronaTablaSqlite.TABLA_CORONA, contentValues, CoronaTablaSqlite.NOMBRE_PERSONAJE + " = ? ", idValor);
        sqLiteDatabase.close();
    }

    public boolean validarUInsertUpdateCorona(Context actividad, String personaje, Corona corona){//meétodo para validar el estado no visible del manga
        this.abrirDBEsccribir();
        String[] nom = {String.valueOf(personaje)};
        String consulta = "SELECT * FROM " + CoronaTablaSqlite.TABLA_CORONA + " WHERE " + CoronaTablaSqlite.NOMBRE_PERSONAJE + " = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(consulta, nom);
        if(cursor.getCount() <= 0){
            cursor.close();
            guardarCorona(corona);
            Toast.makeText(actividad, "¡Se ha creado un nuevo registro de artefacto!", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            if(cursor.moveToFirst()){
                String pj = cursor.getString(cursor.getColumnIndex(CoronaTablaSqlite.NOMBRE_PERSONAJE));
                actualizarCorona(corona, pj);
                Toast.makeText(actividad, "¡Se ha actualizado la build de: " + personaje + "!", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        cursor.close();
        return true;
    }

    public ArrayList mostrarDatosDelPjCorona(String nombrePJ) {
        ArrayList list = new ArrayList<>();
        this.abrirDBLeer();
        String[] campos = new String[]{CoronaTablaSqlite.ID_STAT_CORONA, CoronaTablaSqlite.NOMBRE_PERSONAJE, CoronaTablaSqlite.STAT_PRINC,
                CoronaTablaSqlite.STAT_SEC_A, CoronaTablaSqlite.STAT_SEC_B, CoronaTablaSqlite.STAT_SEC_C, CoronaTablaSqlite.STAT_SEC_D};
        String where = CoronaTablaSqlite.NOMBRE_PERSONAJE + " IN ('" + nombrePJ + "');";
        Cursor c = sqLiteDatabase.query(CoronaTablaSqlite.TABLA_CORONA, campos, where, null, null, null, null);
        try {
            while (c.moveToNext()) {
                Corona corona = new Corona();
                corona.setId(c.getInt(0));
                corona.setNombrePersonaje(c.getString(1));
                corona.setPrincipal(c.getString(2));
                corona.setSecundarioA(c.getString(3));
                corona.setSecundarioB(c.getString(4));
                corona.setSecundarioC(c.getString(5));
                corona.setSecundarioD(c.getString(6));
                list.add(corona);
            }
        } finally { c.close(); }
        this.cerrarBD();
        return list;
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
