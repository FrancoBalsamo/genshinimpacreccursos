package com.frabasoft.genshinimpactrecursos.SQLiteGenshin.Procesos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Environment;
import android.os.FileUtils;
import android.util.Log;
import android.widget.Toast;

import com.frabasoft.genshinimpactrecursos.Clases.Armas.Armas;
import com.frabasoft.genshinimpactrecursos.Clases.Artefactos.Copa;
import com.frabasoft.genshinimpactrecursos.Clases.Artefactos.Corona;
import com.frabasoft.genshinimpactrecursos.Clases.Artefactos.Flor;
import com.frabasoft.genshinimpactrecursos.Clases.Artefactos.Pluma;
import com.frabasoft.genshinimpactrecursos.Clases.Artefactos.Reloj;
import com.frabasoft.genshinimpactrecursos.Clases.ArtefactosAlert.CopaArtefacto;
import com.frabasoft.genshinimpactrecursos.Clases.ArtefactosAlert.CoronaArtefacto;
import com.frabasoft.genshinimpactrecursos.Clases.ArtefactosAlert.FlorArtefacto;
import com.frabasoft.genshinimpactrecursos.Clases.ArtefactosAlert.PlumaArtefacto;
import com.frabasoft.genshinimpactrecursos.Clases.ArtefactosAlert.RelojArtefacto;
import com.frabasoft.genshinimpactrecursos.SQLiteGenshin.NombreVersionSqlite;
import com.frabasoft.genshinimpactrecursos.SQLiteGenshin.Tablas.ArmasTablaSqlite;
import com.frabasoft.genshinimpactrecursos.SQLiteGenshin.Tablas.CopaImagenSqlite;
import com.frabasoft.genshinimpactrecursos.SQLiteGenshin.Tablas.CopaTablaSqlite;
import com.frabasoft.genshinimpactrecursos.SQLiteGenshin.Tablas.CoronaImagenSqlite;
import com.frabasoft.genshinimpactrecursos.SQLiteGenshin.Tablas.CoronaTablaSqlite;
import com.frabasoft.genshinimpactrecursos.SQLiteGenshin.Tablas.FlorImagenSqlite;
import com.frabasoft.genshinimpactrecursos.SQLiteGenshin.Tablas.FlorTablaSqlite;
import com.frabasoft.genshinimpactrecursos.SQLiteGenshin.Tablas.PlumaImagenSqlite;
import com.frabasoft.genshinimpactrecursos.SQLiteGenshin.Tablas.PlumaTablaSqlite;
import com.frabasoft.genshinimpactrecursos.SQLiteGenshin.Tablas.RelojImagenSqlite;
import com.frabasoft.genshinimpactrecursos.SQLiteGenshin.Tablas.RelojTablaSqlite;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

import static com.frabasoft.genshinimpactrecursos.SQLiteGenshin.NombreVersionSqlite.DB_NAME;

public class DatosProcesosSqlite implements Serializable {
    private SQLiteDatabase sqLiteDatabase;
    private DBHelper dbHelper;
    private String formatoHoy;
    private String nombreTXT = "Leer Importante Sobre Genshin Impact Recursos.txt";
    private String guardar = "Se ha generado la build ";
    private String guardarA = "del personaje ";
    private String actualizar = "Se ha actualizado la build ";
    private String actualizarA = "del personaje ";

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


    public boolean validarUInsertUpdateFlor(Context actividad, String personaje, Flor flor) throws IOException {
        this.abrirDBEsccribir();
        String[] nom = {String.valueOf(personaje)};
        String consulta = "SELECT * FROM " + FlorTablaSqlite.TABLA_FLOR + " WHERE " + FlorTablaSqlite.NOMBRE_PERSONAJE + " = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(consulta, nom);
        if(cursor.getCount() <= 0){
            cursor.close();
            guardarFlor(flor);
            copiarArchivo(guardar + "Flor " + guardarA + personaje);
            Toast.makeText(actividad, "¡Se ha creado un nuevo registro de artefacto en Flor!", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            if(cursor.moveToFirst()){
                String pj = cursor.getString(cursor.getColumnIndex(FlorTablaSqlite.NOMBRE_PERSONAJE));
                actualizarFlor(flor, pj);
                copiarArchivo(actualizar + "Flor " + actualizarA + personaje);
                Toast.makeText(actividad, "¡Se ha actualizado la build de: " + personaje + " en Flor!", Toast.LENGTH_SHORT).show();
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

    public boolean validarUInsertUpdatePluma(Context actividad, String personaje, Pluma pluma) throws IOException {//meétodo para validar el estado no visible del manga
        this.abrirDBEsccribir();
        String[] nom = {String.valueOf(personaje)};
        String consulta = "SELECT * FROM " + PlumaTablaSqlite.TABLA_PLUMA + " WHERE " + PlumaTablaSqlite.NOMBRE_PERSONAJE + " = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(consulta, nom);
        if(cursor.getCount() <= 0){
            cursor.close();
            guardarPluma(pluma);
            copiarArchivo(guardar + "Pluma " + guardarA + personaje);
            Toast.makeText(actividad, "¡Se ha creado un nuevo registro de artefacto en Pluma!", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            if(cursor.moveToFirst()){
                String pj = cursor.getString(cursor.getColumnIndex(PlumaTablaSqlite.NOMBRE_PERSONAJE));
                actualizarPluma(pluma, pj);
                copiarArchivo(actualizar + "Pluma " + actualizarA + personaje);
                Toast.makeText(actividad, "¡Se ha actualizado la build de: " + personaje + " en Pluma!", Toast.LENGTH_SHORT).show();
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

    public boolean validarUInsertUpdateReloj(Context actividad, String personaje, Reloj reloj) throws IOException {//meétodo para validar el estado no visible del manga
        this.abrirDBEsccribir();
        String[] nom = {String.valueOf(personaje)};
        String consulta = "SELECT * FROM " + RelojTablaSqlite.TABLA_RELOJ + " WHERE " + RelojTablaSqlite.NOMBRE_PERSONAJE + " = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(consulta, nom);
        if(cursor.getCount() <= 0){
            cursor.close();
            guardarReloj(reloj);
            copiarArchivo(guardar + "Reloj " + guardarA + personaje);
            Toast.makeText(actividad, "¡Se ha creado un nuevo registro de artefacto en Reloj!", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            if(cursor.moveToFirst()){
                String pj = cursor.getString(cursor.getColumnIndex(RelojTablaSqlite.NOMBRE_PERSONAJE));
                actualizarReloj(reloj, pj);
                copiarArchivo(actualizar + "Reloj " + actualizarA + personaje);
                Toast.makeText(actividad, "¡Se ha actualizado la build de: " + personaje + " en Reloj!", Toast.LENGTH_SHORT).show();
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

    public boolean validarUInsertUpdateCopa(Context actividad, String personaje, Copa copa) throws IOException {//meétodo para validar el estado no visible del manga
        this.abrirDBEsccribir();
        String[] nom = {String.valueOf(personaje)};
        String consulta = "SELECT * FROM " + CopaTablaSqlite.TABLA_COPA + " WHERE " + CopaTablaSqlite.NOMBRE_PERSONAJE + " = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(consulta, nom);
        if(cursor.getCount() <= 0){
            cursor.close();
            guardarCopa(copa);
            copiarArchivo(guardar + "Copa " + guardarA + personaje);
            Toast.makeText(actividad, "¡Se ha creado un nuevo registro de artefacto en Copa!", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            if(cursor.moveToFirst()){
                String pj = cursor.getString(cursor.getColumnIndex(CopaTablaSqlite.NOMBRE_PERSONAJE));
                actualizarCopa(copa, pj);
                copiarArchivo(actualizar + "Copa " + actualizarA + personaje);
                Toast.makeText(actividad, "¡Se ha actualizado la build de: " + personaje + " en Copa!", Toast.LENGTH_SHORT).show();
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

    public boolean validarUInsertUpdateCorona(Context actividad, String personaje, Corona corona) throws IOException {//meétodo para validar el estado no visible del manga
        this.abrirDBEsccribir();
        String[] nom = {String.valueOf(personaje)};
        String consulta = "SELECT * FROM " + CoronaTablaSqlite.TABLA_CORONA + " WHERE " + CoronaTablaSqlite.NOMBRE_PERSONAJE + " = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(consulta, nom);
        if(cursor.getCount() <= 0){
            cursor.close();
            guardarCorona(corona);
            copiarArchivo(guardar + "Corona " + guardarA + personaje);
            Toast.makeText(actividad, "¡Se ha creado un nuevo registro de artefacto en Corona!", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            if(cursor.moveToFirst()){
                String pj = cursor.getString(cursor.getColumnIndex(CoronaTablaSqlite.NOMBRE_PERSONAJE));
                actualizarCorona(corona, pj);
                copiarArchivo(actualizar + "Corona " + actualizarA + personaje);
                Toast.makeText(actividad, "¡Se ha actualizado la build de: " + personaje + " en Corona!", Toast.LENGTH_SHORT).show();
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

    /////////////////////////////////////////////////////////////////////////Arma SAVE DATA SQLITE
    private ContentValues mapeoArma(Armas armas){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ArmasTablaSqlite.ID_ARMA, armas.getId());
        contentValues.put(ArmasTablaSqlite.NOMBRE_PERSONAJE, armas.getPersonaje());
        contentValues.put(ArmasTablaSqlite.ARMA_NOMBRE, armas.getNombreArma());
        contentValues.put(ArmasTablaSqlite.RECURSO_IMAGEN, armas.getRecursoImagen());
        return contentValues;
    }

    public long guardarArma(Armas armas){//insertar registro
        this.abrirDBEsccribir();
        long filaID = sqLiteDatabase.insert(ArmasTablaSqlite.TABLA_ARMA, null, mapeoArma(armas));
        return filaID;
    }

    public void actualizarArma(Armas armas, String nombrePJ){
        this.abrirDBEsccribir();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ArmasTablaSqlite.ID_ARMA, armas.getId());
        contentValues.put(ArmasTablaSqlite.NOMBRE_PERSONAJE, armas.getPersonaje());
        contentValues.put(ArmasTablaSqlite.ARMA_NOMBRE, armas.getNombreArma());
        contentValues.put(ArmasTablaSqlite.RECURSO_IMAGEN, armas.getRecursoImagen());
        String[] idValor = {String.valueOf(nombrePJ)};
        sqLiteDatabase.update(ArmasTablaSqlite.TABLA_ARMA, contentValues, ArmasTablaSqlite.NOMBRE_PERSONAJE + " = ? ", idValor);
        sqLiteDatabase.close();
    }

    public boolean validarUInsertUpdateArma(Context actividad, String personaje, Armas armas){//meétodo para validar el estado no visible del manga
        this.abrirDBEsccribir();
        String[] nom = {String.valueOf(personaje)};
        String consulta = "SELECT * FROM " + ArmasTablaSqlite.TABLA_ARMA + " WHERE " + ArmasTablaSqlite.NOMBRE_PERSONAJE + " = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(consulta, nom);
        if(cursor.getCount() <= 0){
            cursor.close();
            guardarArma(armas);
            copiarArchivo(guardar + "Corona " + guardarA + personaje);
            return false;
        }else{
            if(cursor.moveToFirst()){
                String pj = cursor.getString(cursor.getColumnIndex(ArmasTablaSqlite.NOMBRE_PERSONAJE));
                actualizarArma(armas, pj);
                copiarArchivo(actualizar + "Corona " + actualizarA + personaje);
                return false;
            }
        }
        cursor.close();
        return true;
    }

    public ArrayList mostrarDatosDelPjArma(String nombrePJ) {
        ArrayList list = new ArrayList<>();
        this.abrirDBLeer();
        String[] campos = new String[]{ArmasTablaSqlite.ID_ARMA,
                ArmasTablaSqlite.NOMBRE_PERSONAJE,
                ArmasTablaSqlite.ARMA_NOMBRE,
                ArmasTablaSqlite.RECURSO_IMAGEN
                };
        String where = ArmasTablaSqlite.NOMBRE_PERSONAJE + " IN ('" + nombrePJ + "');";
        Cursor c = sqLiteDatabase.query(ArmasTablaSqlite.TABLA_ARMA, campos, where, null, null, null, null);
        try {
            while (c.moveToNext()) {
                Armas armas = new Armas();
                armas.setId(c.getInt(0));
                armas.setPersonaje(c.getString(1));
                armas.setNombreArma(c.getString(2));
                armas.setRecursoImagen(c.getInt(3));
                list.add(armas);
            }
        } finally { c.close(); }
        this.cerrarBD();
        return list;
    }

    /////////////////////////////////////////////////////////////////////////FLOR IMAGEN SAVE DATA SQLITE
    private ContentValues mapeoFlorArtefacto(FlorArtefacto florArtefacto){
        ContentValues contentValues = new ContentValues();
        contentValues.put(FlorImagenSqlite.NOMBRE_PERSONAJE, florArtefacto.getNombrePJ());
        contentValues.put(FlorImagenSqlite.ID_SELECCION_FLOR, florArtefacto.getSeleccionDatoSpiner());
        contentValues.put(FlorImagenSqlite.FLOR_ARTEFACTO, florArtefacto.getNombreArtefacto());
        contentValues.put(FlorImagenSqlite.RECURSO_ARTEFACTO_FLOR, florArtefacto.getRecursoArtefacto());
        return contentValues;
    }

    public long guardarFlorArtefacto(FlorArtefacto florArtefacto){//insertar registro
        this.abrirDBEsccribir();
        long filaID = sqLiteDatabase.insert(FlorImagenSqlite.TABLA_FLOR_IMAGEN, null, mapeoFlorArtefacto(florArtefacto));
        return filaID;
    }

    public void actualizarFlorArtefacto(FlorArtefacto florArtefacto, String valor){
        this.abrirDBEsccribir();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FlorImagenSqlite.ID_SELECCION_FLOR, florArtefacto.getSeleccionDatoSpiner());
        contentValues.put(FlorImagenSqlite.FLOR_ARTEFACTO, florArtefacto.getNombreArtefacto());
        contentValues.put(FlorImagenSqlite.RECURSO_ARTEFACTO_FLOR, florArtefacto.getRecursoArtefacto());
        String[] idValor = {String.valueOf(valor)};
        sqLiteDatabase.update(FlorImagenSqlite.TABLA_FLOR_IMAGEN, contentValues, FlorImagenSqlite.ID_PK_ARTEFACTO_FLOR + " = ? ", idValor);
        sqLiteDatabase.close();
    }

    public boolean validarUInsertUpdateFlorArtefacto(String nombreArtef, FlorArtefacto florArtefacto){//meétodo para validar el estado no visible del manga
        this.abrirDBEsccribir();
        String[] nom = {String.valueOf(1)};
        String consulta = "SELECT * FROM " + FlorImagenSqlite.TABLA_FLOR_IMAGEN + " WHERE "
                + FlorImagenSqlite.ID_PK_ARTEFACTO_FLOR + " = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(consulta, nom);
        if(cursor.getCount() <= 0){
            cursor.close();
            guardarFlorArtefacto(florArtefacto);
            copiarArchivo(guardar + "Flor " + guardarA + nombreArtef);
            return false;
        }else{
            if(cursor.moveToFirst()){
                String pj = cursor.getString(cursor.getColumnIndex(FlorImagenSqlite.ID_PK_ARTEFACTO_FLOR));
                actualizarFlorArtefacto(florArtefacto, pj);
                copiarArchivo(actualizar + "Flor " + actualizarA + nombreArtef);
                return false;
            }
        }
        cursor.close();
        return true;
    }

    public ArrayList mostrarSeleccionFlorArtefacto(String personaje) {
        ArrayList list = new ArrayList<>();
        this.abrirDBLeer();
        String[] campos = new String[]{FlorImagenSqlite.ID_PK_ARTEFACTO_FLOR,
                FlorImagenSqlite.NOMBRE_PERSONAJE,
                FlorImagenSqlite.ID_SELECCION_FLOR,
                FlorImagenSqlite.FLOR_ARTEFACTO,
                FlorImagenSqlite.RECURSO_ARTEFACTO_FLOR
        };
        String where = FlorImagenSqlite.NOMBRE_PERSONAJE + " IN ('" + personaje + "');";
        Cursor c = sqLiteDatabase.query(FlorImagenSqlite.TABLA_FLOR_IMAGEN, campos, where,
                null, null, null, null);
        try {
            while (c.moveToNext()) {
                FlorArtefacto florArtefacto = new FlorArtefacto();
                florArtefacto.setIdPK(c.getInt(0));
                florArtefacto.setNombrePJ(c.getString(1));
                florArtefacto.setSeleccionDatoSpiner(c.getInt(2));
                florArtefacto.setNombreArtefacto(c.getString(3));
                florArtefacto.setRecursoArtefacto(c.getInt(4));
                list.add(florArtefacto);
            }
        } finally { c.close(); }
        this.cerrarBD();
        return list;
    }

    /////////////////////////////////////////////////////////////////////////Pluma IMAGEN SAVE DATA SQLITE
    private ContentValues mapeoPlumaArtefacto(PlumaArtefacto PlumaArtefacto){
        ContentValues contentValues = new ContentValues();
        contentValues.put(PlumaImagenSqlite.NOMBRE_PERSONAJE, PlumaArtefacto.getNombrePJ());
        contentValues.put(PlumaImagenSqlite.ID_SELECCION_PLUMA, PlumaArtefacto.getSeleccionDatoSpiner());
        contentValues.put(PlumaImagenSqlite.PLUMA_ARTEFACTO, PlumaArtefacto.getNombreArtefacto());
        contentValues.put(PlumaImagenSqlite.RECURSO_ARTEFACTO_PLUMA, PlumaArtefacto.getRecursoArtefacto());
        return contentValues;
    }

    public long guardarPlumaArtefacto(PlumaArtefacto PlumaArtefacto){//insertar registro
        this.abrirDBEsccribir();
        long filaID = sqLiteDatabase.insert(PlumaImagenSqlite.TABLA_PLUMA_IMAGEN, null, mapeoPlumaArtefacto(PlumaArtefacto));
        return filaID;
    }

    public void actualizarPlumaArtefacto(PlumaArtefacto PlumaArtefacto, String valor){
        this.abrirDBEsccribir();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PlumaImagenSqlite.ID_SELECCION_PLUMA, PlumaArtefacto.getSeleccionDatoSpiner());
        contentValues.put(PlumaImagenSqlite.PLUMA_ARTEFACTO, PlumaArtefacto.getNombreArtefacto());
        contentValues.put(PlumaImagenSqlite.RECURSO_ARTEFACTO_PLUMA, PlumaArtefacto.getRecursoArtefacto());
        String[] idValor = {String.valueOf(valor)};
        sqLiteDatabase.update(PlumaImagenSqlite.TABLA_PLUMA_IMAGEN, contentValues,
                PlumaImagenSqlite.ID_PK_ARTEFACTO_PLUMA + " = ? ", idValor);
        sqLiteDatabase.close();
    }

    public boolean validarUInsertUpdatePlumaArtefacto(String nombreArtef, PlumaArtefacto PlumaArtefacto){//meétodo para validar el estado no visible del manga
        this.abrirDBEsccribir();
        String[] nom = {String.valueOf(1)};
        String consulta = "SELECT * FROM " + PlumaImagenSqlite.TABLA_PLUMA_IMAGEN + " WHERE "
                + PlumaImagenSqlite.ID_PK_ARTEFACTO_PLUMA + " = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(consulta, nom);
        if(cursor.getCount() <= 0){
            cursor.close();
            guardarPlumaArtefacto(PlumaArtefacto);
            copiarArchivo(guardar + "Pluma " + guardarA + nombreArtef);
            return false;
        }else{
            if(cursor.moveToFirst()){
                String pj = cursor.getString(cursor.getColumnIndex(PlumaImagenSqlite.ID_PK_ARTEFACTO_PLUMA));
                actualizarPlumaArtefacto(PlumaArtefacto, pj);
                copiarArchivo(actualizar + "Pluma " + actualizarA + nombreArtef);
                return false;
            }
        }
        cursor.close();
        return true;
    }

    public ArrayList mostrarSeleccionPlumaArtefacto(String personaje) {
        ArrayList list = new ArrayList<>();
        this.abrirDBLeer();
        String[] campos = new String[]{PlumaImagenSqlite.ID_PK_ARTEFACTO_PLUMA,
                PlumaImagenSqlite.NOMBRE_PERSONAJE,
                PlumaImagenSqlite.ID_SELECCION_PLUMA,
                PlumaImagenSqlite.PLUMA_ARTEFACTO,
                PlumaImagenSqlite.RECURSO_ARTEFACTO_PLUMA
        };
        String where = PlumaImagenSqlite.NOMBRE_PERSONAJE + " IN ('" + personaje + "');";
        Cursor c = sqLiteDatabase.query(PlumaImagenSqlite.TABLA_PLUMA_IMAGEN, campos, where,
                null, null, null, null);
        try {
            while (c.moveToNext()) {
                PlumaArtefacto PlumaArtefacto = new PlumaArtefacto();
                PlumaArtefacto.setIdPK(c.getInt(0));
                PlumaArtefacto.setNombrePJ(c.getString(1));
                PlumaArtefacto.setSeleccionDatoSpiner(c.getInt(2));
                PlumaArtefacto.setNombreArtefacto(c.getString(3));
                PlumaArtefacto.setRecursoArtefacto(c.getInt(4));
                list.add(PlumaArtefacto);
            }
        } finally { c.close(); }
        this.cerrarBD();
        return list;
    }

    /////////////////////////////////////////////////////////////////////////Reloj IMAGEN SAVE DATA SQLITE
    private ContentValues mapeoRelojArtefacto(RelojArtefacto RelojArtefacto){
        ContentValues contentValues = new ContentValues();
        contentValues.put(RelojImagenSqlite.NOMBRE_PERSONAJE, RelojArtefacto.getNombrePJ());
        contentValues.put(RelojImagenSqlite.ID_SELECCION_RELOJ, RelojArtefacto.getSeleccionDatoSpiner());
        contentValues.put(RelojImagenSqlite.RELOJ_ARTEFACTO, RelojArtefacto.getNombreArtefacto());
        contentValues.put(RelojImagenSqlite.RECURSO_ARTEFACTO_RELOJ, RelojArtefacto.getRecursoArtefacto());
        return contentValues;
    }

    public long guardarRelojArtefacto(RelojArtefacto RelojArtefacto){//insertar registro
        this.abrirDBEsccribir();
        long filaID = sqLiteDatabase.insert(RelojImagenSqlite.TABLA_RELOJ_IMAGEN, null, mapeoRelojArtefacto(RelojArtefacto));
        return filaID;
    }

    public void actualizarRelojArtefacto(RelojArtefacto RelojArtefacto, String valor){
        this.abrirDBEsccribir();
        ContentValues contentValues = new ContentValues();
        contentValues.put(RelojImagenSqlite.ID_SELECCION_RELOJ, RelojArtefacto.getSeleccionDatoSpiner());
        contentValues.put(RelojImagenSqlite.RELOJ_ARTEFACTO, RelojArtefacto.getNombreArtefacto());
        contentValues.put(RelojImagenSqlite.RECURSO_ARTEFACTO_RELOJ, RelojArtefacto.getRecursoArtefacto());
        String[] idValor = {String.valueOf(valor)};
        sqLiteDatabase.update(RelojImagenSqlite.TABLA_RELOJ_IMAGEN, contentValues,
                RelojImagenSqlite.ID_PK_ARTEFACTO_RELOJ + " = ? ", idValor);
        sqLiteDatabase.close();
    }

    public boolean validarUInsertUpdateRelojArtefacto(String nombreArtef, RelojArtefacto RelojArtefacto){//meétodo para validar el estado no visible del manga
        this.abrirDBEsccribir();
        String[] nom = {String.valueOf(1)};
        String consulta = "SELECT * FROM " + RelojImagenSqlite.TABLA_RELOJ_IMAGEN + " WHERE "
                + RelojImagenSqlite.ID_PK_ARTEFACTO_RELOJ + " = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(consulta, nom);
        if(cursor.getCount() <= 0){
            cursor.close();
            guardarRelojArtefacto(RelojArtefacto);
            copiarArchivo(guardar + "Reloj " + guardarA + nombreArtef);
            return false;
        }else{
            if(cursor.moveToFirst()){
                String pj = cursor.getString(cursor.getColumnIndex(RelojImagenSqlite.ID_PK_ARTEFACTO_RELOJ));
                actualizarRelojArtefacto(RelojArtefacto, pj);
                copiarArchivo(actualizar + "Reloj " + actualizarA + nombreArtef);
                return false;
            }
        }
        cursor.close();
        return true;
    }

    public ArrayList mostrarSeleccionRelojArtefacto(String personaje) {
        ArrayList list = new ArrayList<>();
        this.abrirDBLeer();
        String[] campos = new String[]{RelojImagenSqlite.ID_PK_ARTEFACTO_RELOJ,
                RelojImagenSqlite.NOMBRE_PERSONAJE,
                RelojImagenSqlite.ID_SELECCION_RELOJ,
                RelojImagenSqlite.RELOJ_ARTEFACTO,
                RelojImagenSqlite.RECURSO_ARTEFACTO_RELOJ
        };
        String where = RelojImagenSqlite.NOMBRE_PERSONAJE + " IN ('" + personaje + "');";
        Cursor c = sqLiteDatabase.query(RelojImagenSqlite.TABLA_RELOJ_IMAGEN, campos, where,
                null, null, null, null);
        try {
            while (c.moveToNext()) {
                RelojArtefacto RelojArtefacto = new RelojArtefacto();
                RelojArtefacto.setIdPK(c.getInt(0));
                RelojArtefacto.setNombrePJ(c.getString(1));
                RelojArtefacto.setSeleccionDatoSpiner(c.getInt(2));
                RelojArtefacto.setNombreArtefacto(c.getString(3));
                RelojArtefacto.setRecursoArtefacto(c.getInt(4));
                list.add(RelojArtefacto);
            }
        } finally { c.close(); }
        this.cerrarBD();
        return list;
    }

    /////////////////////////////////////////////////////////////////////////Copa IMAGEN SAVE DATA SQLITE
    private ContentValues mapeoCopaArtefacto(CopaArtefacto copaArtefacto){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CopaImagenSqlite.NOMBRE_PERSONAJE, copaArtefacto.getNombrePJ());
        contentValues.put(CopaImagenSqlite.ID_SELECCION_COPA, copaArtefacto.getSeleccionDatoSpiner());
        contentValues.put(CopaImagenSqlite.COPA_ARTEFACTO, copaArtefacto.getNombreArtefacto());
        contentValues.put(CopaImagenSqlite.RECURSO_ARTEFACTO_COPA, copaArtefacto.getRecursoArtefacto());
        return contentValues;
    }

    public long guardarCopaArtefacto(CopaArtefacto copaArtefacto){//insertar registro
        this.abrirDBEsccribir();
        long filaID = sqLiteDatabase.insert(CopaImagenSqlite.TABLA_COPA_IMAGEN, null, mapeoCopaArtefacto(copaArtefacto));
        return filaID;
    }

    public void actualizarCopaArtefacto(CopaArtefacto copaArtefacto, String valor){
        this.abrirDBEsccribir();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CopaImagenSqlite.ID_SELECCION_COPA, copaArtefacto.getSeleccionDatoSpiner());
        contentValues.put(CopaImagenSqlite.COPA_ARTEFACTO, copaArtefacto.getNombreArtefacto());
        contentValues.put(CopaImagenSqlite.RECURSO_ARTEFACTO_COPA, copaArtefacto.getRecursoArtefacto());
        String[] idValor = {String.valueOf(valor)};
        sqLiteDatabase.update(CopaImagenSqlite.TABLA_COPA_IMAGEN, contentValues, 
                CopaImagenSqlite.ID_PK_ARTEFACTO_COPA + " = ? ", idValor);
        sqLiteDatabase.close();
    }

    public boolean validarUInsertUpdateCopaArtefacto(String nombreArtef, CopaArtefacto copaArtefacto){//meétodo para validar el estado no visible del manga
        this.abrirDBEsccribir();
        String[] nom = {String.valueOf(1)};
        String consulta = "SELECT * FROM " + CopaImagenSqlite.TABLA_COPA_IMAGEN + " WHERE "
                + CopaImagenSqlite.ID_PK_ARTEFACTO_COPA + " = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(consulta, nom);
        if(cursor.getCount() <= 0){
            cursor.close();
            guardarCopaArtefacto(copaArtefacto);
            copiarArchivo(guardar + "Copa " + guardarA + nombreArtef);
            return false;
        }else{
            if(cursor.moveToFirst()){
                String pj = cursor.getString(cursor.getColumnIndex(CopaImagenSqlite.ID_PK_ARTEFACTO_COPA));
                actualizarCopaArtefacto(copaArtefacto, pj);
                copiarArchivo(actualizar + "Copa " + actualizarA + nombreArtef);
                return false;
            }
        }
        cursor.close();
        return true;
    }

    public ArrayList mostrarSeleccionCopaArtefacto(String personaje) {
        ArrayList list = new ArrayList<>();
        this.abrirDBLeer();
        String[] campos = new String[]{CopaImagenSqlite.ID_PK_ARTEFACTO_COPA,
                CopaImagenSqlite.NOMBRE_PERSONAJE,
                CopaImagenSqlite.ID_SELECCION_COPA,
                CopaImagenSqlite.COPA_ARTEFACTO,
                CopaImagenSqlite.RECURSO_ARTEFACTO_COPA
        };
        String where = CopaImagenSqlite.NOMBRE_PERSONAJE + " IN ('" + personaje + "');";
        Cursor c = sqLiteDatabase.query(CopaImagenSqlite.TABLA_COPA_IMAGEN, campos, where,
                null, null, null, null);
        try {
            while (c.moveToNext()) {
                CopaArtefacto copaArtefacto = new CopaArtefacto();
                copaArtefacto.setIdPK(c.getInt(0));
                copaArtefacto.setNombrePJ(c.getString(1));
                copaArtefacto.setSeleccionDatoSpiner(c.getInt(2));
                copaArtefacto.setNombreArtefacto(c.getString(3));
                copaArtefacto.setRecursoArtefacto(c.getInt(4));
                list.add(copaArtefacto);
            }
        } finally { c.close(); }
        this.cerrarBD();
        return list;
    }

    /////////////////////////////////////////////////////////////////////////CORONA IMAGEN SAVE DATA SQLITE
    private ContentValues mapeoCoronaArtefacto(CoronaArtefacto CoronaArtefacto){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CoronaImagenSqlite.NOMBRE_PERSONAJE, CoronaArtefacto.getNombrePJ());
        contentValues.put(CoronaImagenSqlite.ID_SELECCION_CORONA, CoronaArtefacto.getSeleccionDatoSpiner());
        contentValues.put(CoronaImagenSqlite.CORONA_ARTEFACTO, CoronaArtefacto.getNombreArtefacto());
        contentValues.put(CoronaImagenSqlite.RECURSO_ARTEFACTO_CORONA, CoronaArtefacto.getRecursoArtefacto());
        return contentValues;
    }

    public long guardarCoronaArtefacto(CoronaArtefacto CoronaArtefacto){//insertar registro
        this.abrirDBEsccribir();
        long filaID = sqLiteDatabase.insert(CoronaImagenSqlite.TABLA_CORONA_IMAGEN, null, mapeoCoronaArtefacto(CoronaArtefacto));
        return filaID;
    }

    public void actualizarCoronaArtefacto(CoronaArtefacto CoronaArtefacto, String valor){
        this.abrirDBEsccribir();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CoronaImagenSqlite.ID_SELECCION_CORONA, CoronaArtefacto.getSeleccionDatoSpiner());
        contentValues.put(CoronaImagenSqlite.CORONA_ARTEFACTO, CoronaArtefacto.getNombreArtefacto());
        contentValues.put(CoronaImagenSqlite.RECURSO_ARTEFACTO_CORONA, CoronaArtefacto.getRecursoArtefacto());
        String[] idValor = {String.valueOf(valor)};
        sqLiteDatabase.update(CoronaImagenSqlite.TABLA_CORONA_IMAGEN, contentValues,
                CoronaImagenSqlite.ID_PK_ARTEFACTO_CORONA + " = ? ", idValor);
        sqLiteDatabase.close();
    }

    public boolean validarUInsertUpdateCoronaArtefacto(String nombreArtef, CoronaArtefacto CoronaArtefacto){//meétodo para validar el estado no visible del manga
        this.abrirDBEsccribir();
        String[] nom = {String.valueOf(1)};
        String consulta = "SELECT * FROM " + CoronaImagenSqlite.TABLA_CORONA_IMAGEN + " WHERE "
                + CoronaImagenSqlite.ID_PK_ARTEFACTO_CORONA + " = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(consulta, nom);
        if(cursor.getCount() <= 0){
            cursor.close();
            guardarCoronaArtefacto(CoronaArtefacto);
            copiarArchivo(guardar + "Corona " + guardarA + nombreArtef);
            return false;
        }else{
            if(cursor.moveToFirst()){
                String pj = cursor.getString(cursor.getColumnIndex(CoronaImagenSqlite.ID_PK_ARTEFACTO_CORONA));
                actualizarCoronaArtefacto(CoronaArtefacto, pj);
                copiarArchivo(actualizar + "Corona " + actualizarA + nombreArtef);
                return false;
            }
        }
        cursor.close();
        return true;
    }

    public ArrayList mostrarSeleccionCoronaArtefacto(String personaje) {
        ArrayList list = new ArrayList<>();
        this.abrirDBLeer();
        String[] campos = new String[]{CoronaImagenSqlite.ID_PK_ARTEFACTO_CORONA,
                CoronaImagenSqlite.NOMBRE_PERSONAJE,
                CoronaImagenSqlite.ID_SELECCION_CORONA,
                CoronaImagenSqlite.CORONA_ARTEFACTO,
                CoronaImagenSqlite.RECURSO_ARTEFACTO_CORONA
        };
        String where = CoronaImagenSqlite.NOMBRE_PERSONAJE + " IN ('" + personaje + "');";
        Cursor c = sqLiteDatabase.query(CoronaImagenSqlite.TABLA_CORONA_IMAGEN, campos, where,
                null, null, null, null);
        try {
            while (c.moveToNext()) {
                CoronaArtefacto CoronaArtefacto = new CoronaArtefacto();
                CoronaArtefacto.setIdPK(c.getInt(0));
                CoronaArtefacto.setNombrePJ(c.getString(1));
                CoronaArtefacto.setSeleccionDatoSpiner(c.getInt(2));
                CoronaArtefacto.setNombreArtefacto(c.getString(3));
                CoronaArtefacto.setRecursoArtefacto(c.getInt(4));
                list.add(CoronaArtefacto);
            }
        } finally { c.close(); }
        this.cerrarBD();
        return list;
    }

    /////////////////////////////////////////////////////////////////////////Creación de TXT y copia de bd
    public void copiarArchivo(String detalles){
        //para el archivo txt
        Calendar fechaHoy = Calendar.getInstance();
        int mesActual = fechaHoy.get(Calendar.MONTH) + 1;
        if(fechaHoy.get(Calendar.DAY_OF_MONTH) < 10){
            formatoHoy = "FECHA: " + "0" + fechaHoy.get(Calendar.DAY_OF_MONTH) + "/" + mesActual + fechaHoy.get(Calendar.YEAR)
                    + "\nHORA: " + fechaHoy.get(Calendar.HOUR_OF_DAY) + ":" + fechaHoy.get(Calendar.MINUTE) + ":" + fechaHoy.get(Calendar.SECOND); //hora del día
            Log.d("FechaLOGD", "copiarArchivo: " + formatoHoy);
        }else if(mesActual < 10){
            formatoHoy = "FECHA: " + fechaHoy.get(Calendar.DAY_OF_MONTH) + "/" + "0" + mesActual + "/" + fechaHoy.get(Calendar.YEAR)
                    + "\nHORA: " + fechaHoy.get(Calendar.HOUR_OF_DAY) + ":" + fechaHoy.get(Calendar.MINUTE) + ":" + fechaHoy.get(Calendar.SECOND);
            Log.d("FechaLOGD", "copiarArchivo: " + formatoHoy);
        }else{
            formatoHoy ="FECHA: " + fechaHoy.get(Calendar.DAY_OF_MONTH) + "/" + mesActual + "/" + fechaHoy.get(Calendar.YEAR)
                    + "\nHORA: " + fechaHoy.get(Calendar.HOUR_OF_DAY) + ":" + fechaHoy.get(Calendar.MINUTE) + ":" + fechaHoy.get(Calendar.SECOND);
            Log.d("FechaLOGD", "copiarArchivo: " + formatoHoy);
        }

        File rutaDestino = new File(Environment.getExternalStorageDirectory() + "/Genshin Impact Recursos/Genshin Impact Datos/");
        if(!rutaDestino.exists()){
            rutaDestino.mkdirs();
        }
        File archivoTxt = new File(rutaDestino, nombreTXT);
        try{
            //instalaciòn
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivoTxt, true));
            try{
                String escribir = formatoHoy + "\n"
                        + detalles
                        + "\n"
                        + "\n";
                bw.write(escribir);

                bw.flush();
                bw.close();
                Log.d("Buffer", "imprimir textoA: " + bw);
            }catch(Exception e){
                Log.d("Buffered", "imprimir textoA: " + e.getMessage());
            }
        }catch (IOException e){
            Log.d("TAG", "copiarArchivo: " + e.getMessage());
        }catch(Exception exception){
            Log.d("TAG", "copiarArchivo: " + exception.getMessage());
        }

        //para la copia del archivo .db
        String rutaBD = "/data/data/com.frabasoft.genshinimpactrecursos/databases/genshin_db_prueba.db";
        File archivoBd = new File(rutaBD);
        String destinoBD = Environment.getExternalStorageDirectory() + "/Genshin Impact Recursos/Genshin Impact Datos/" + DB_NAME;
        File archivoDestinoBD = new File(destinoBD);
        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.Q){
            try{
                FileUtils.copy(new FileInputStream(archivoBd), new FileOutputStream(archivoDestinoBD));
                Log.d("CopiarArchivoO", "BDTry: " + archivoDestinoBD);
            }catch(IOException ioException){
                Log.d("CopiarArchivoO", "BDCatch: " + ioException.getMessage());
            }
        }else{
            try{
                InputStream in = new FileInputStream(archivoBd);
                OutputStream out = new FileOutputStream(archivoDestinoBD);
                byte[] buff = new byte[1024];
                int len;
                while ((len = in.read(buff)) > 0){
                    out.write(buff, 0, len);
                }
                in.close();
                out.close();
            }catch (IOException ioException){
                Log.d("HOLA", "copiarArchivo: " +ioException.getMessage());
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////Para el backup interno
    public void importarBackUp(){
        String rutaBackUp = Environment.getExternalStorageDirectory() + "/Genshin Impact Recursos/Genshin Impact Datos/" + DB_NAME;
        File archivoBackUp = new File(rutaBackUp);
        String rutaBD = "/data/data/com.frabasoft.genshinimpactrecursos/databases/genshin_db_prueba.db";
        File archivoBd = new File(rutaBD);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            try{
                FileUtils.copy(new FileInputStream(archivoBackUp), new FileOutputStream(archivoBd));
                Log.d("ImportBKP", "try: " + archivoBd);
            }catch(IOException ioException){
                Log.d("ImportBKP", "catch: " + ioException.getMessage());
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////Creación de tablas al instalar
    private static class DBHelper extends SQLiteOpenHelper{
        public DBHelper(Context contexto){
            super(contexto, DB_NAME, null, NombreVersionSqlite.DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL(FlorTablaSqlite.TABLA_FLOR_SQL);
            db.execSQL(CoronaTablaSqlite.TABLA_CORONA_SQL);
            db.execSQL(CopaTablaSqlite.TABLA_COPA_SQL);
            db.execSQL(PlumaTablaSqlite.TABLA_PLUMA_SQL);
            db.execSQL(RelojTablaSqlite.TABLA_RELOJ_SQL);
            db.execSQL(ArmasTablaSqlite.TABLA_ARMA_SQL);
            db.execSQL(FlorImagenSqlite.TABLA_FLOR_RECURSO_ARTEFACTO_SQL);
            db.execSQL(PlumaImagenSqlite.TABLA_PLUMA_RECURSO_ARTEFACTO_SQL);
            db.execSQL(RelojImagenSqlite.TABLA_RELOJ_RECURSO_ARTEFACTO_SQL);
            db.execSQL(CopaImagenSqlite.TABLA_COPA_RECURSO_ARTEFACTO_SQL);
            db.execSQL(CoronaImagenSqlite.TABLA_COPA_RECURSO_ARTEFACTO_SQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            switch(oldVersion){
                case  1:
                    db.execSQL(FlorTablaSqlite.TABLA_FLOR_SQL);
                    db.execSQL(CoronaTablaSqlite.TABLA_CORONA_SQL);
                    db.execSQL(CopaTablaSqlite.TABLA_COPA_SQL);
                    db.execSQL(PlumaTablaSqlite.TABLA_PLUMA_SQL);
                    db.execSQL(RelojTablaSqlite.TABLA_RELOJ_SQL);
                case 2:
                    db.execSQL("DROP TABLE IF EXISTS " + ArmasTablaSqlite.TABLA_ARMA);
                    db.execSQL(ArmasTablaSqlite.TABLA_ARMA_SQL);
                case 3:
                    db.execSQL("DROP TABLE IF EXISTS " + FlorImagenSqlite.TABLA_FLOR_IMAGEN);
                    db.execSQL("DROP TABLE IF EXISTS " + PlumaImagenSqlite.TABLA_PLUMA_IMAGEN);
                    db.execSQL("DROP TABLE IF EXISTS " + RelojImagenSqlite.TABLA_RELOJ_IMAGEN);
                    db.execSQL("DROP TABLE IF EXISTS " + CopaImagenSqlite.TABLA_COPA_IMAGEN);
                    db.execSQL("DROP TABLE IF EXISTS " + CoronaImagenSqlite.TABLA_CORONA_IMAGEN);
                    db.execSQL(FlorImagenSqlite.TABLA_FLOR_RECURSO_ARTEFACTO_SQL);
                    db.execSQL(PlumaImagenSqlite.TABLA_PLUMA_RECURSO_ARTEFACTO_SQL);
                    db.execSQL(RelojImagenSqlite.TABLA_RELOJ_RECURSO_ARTEFACTO_SQL);
                    db.execSQL(CopaImagenSqlite.TABLA_COPA_RECURSO_ARTEFACTO_SQL);
                    db.execSQL(CoronaImagenSqlite.TABLA_COPA_RECURSO_ARTEFACTO_SQL);
                case 4:
                    db.execSQL("DROP TABLE IF EXISTS " + FlorImagenSqlite.TABLA_FLOR_IMAGEN);
                    db.execSQL("DROP TABLE IF EXISTS " + PlumaImagenSqlite.TABLA_PLUMA_IMAGEN);
                    db.execSQL("DROP TABLE IF EXISTS " + RelojImagenSqlite.TABLA_RELOJ_IMAGEN);
                    db.execSQL("DROP TABLE IF EXISTS " + CopaImagenSqlite.TABLA_COPA_IMAGEN);
                    db.execSQL("DROP TABLE IF EXISTS " + CoronaImagenSqlite.TABLA_CORONA_IMAGEN);
                    db.execSQL(FlorImagenSqlite.TABLA_FLOR_RECURSO_ARTEFACTO_SQL);
                    db.execSQL(PlumaImagenSqlite.TABLA_PLUMA_RECURSO_ARTEFACTO_SQL);
                    db.execSQL(RelojImagenSqlite.TABLA_RELOJ_RECURSO_ARTEFACTO_SQL);
                    db.execSQL(CopaImagenSqlite.TABLA_COPA_RECURSO_ARTEFACTO_SQL);
                    db.execSQL(CoronaImagenSqlite.TABLA_COPA_RECURSO_ARTEFACTO_SQL);
                case 5:
                    db.execSQL("DROP TABLE IF EXISTS " + FlorImagenSqlite.TABLA_FLOR_IMAGEN);
                    db.execSQL("DROP TABLE IF EXISTS " + PlumaImagenSqlite.TABLA_PLUMA_IMAGEN);
                    db.execSQL("DROP TABLE IF EXISTS " + RelojImagenSqlite.TABLA_RELOJ_IMAGEN);
                    db.execSQL("DROP TABLE IF EXISTS " + CopaImagenSqlite.TABLA_COPA_IMAGEN);
                    db.execSQL("DROP TABLE IF EXISTS " + CoronaImagenSqlite.TABLA_CORONA_IMAGEN);
                    db.execSQL(FlorImagenSqlite.TABLA_FLOR_RECURSO_ARTEFACTO_SQL);
                    db.execSQL(PlumaImagenSqlite.TABLA_PLUMA_RECURSO_ARTEFACTO_SQL);
                    db.execSQL(RelojImagenSqlite.TABLA_RELOJ_RECURSO_ARTEFACTO_SQL);
                    db.execSQL(CopaImagenSqlite.TABLA_COPA_RECURSO_ARTEFACTO_SQL);
                    db.execSQL(CoronaImagenSqlite.TABLA_COPA_RECURSO_ARTEFACTO_SQL);
            }
        }
    }
}