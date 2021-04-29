package com.frabasoft.genshinimpactrecursos.Preferencias;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.BaseAdapter;

import static android.content.Context.MODE_PRIVATE;

public class PreferenciaSonidosEntrar {
    private Context context;//contexto para saber dónde se va a usar
    private SharedPreferences pref;//nombre
    private SharedPreferences.Editor editor;//para setear el dato

    public PreferenciaSonidosEntrar(Context context) {
        this.context = context;
    }

    public void guardarValor(int valor){
        pref = context.getSharedPreferences("sonido", MODE_PRIVATE); //nombre de la preferencia, en modo privado
        editor = pref.edit();
        editor.putInt("valor", valor); //nombre del campo donde se almacena el valor
        editor.apply(); //lo aplicamos
    }

    public int traerValorGuardado(){//traer ese resultado
        pref = context.getSharedPreferences("sonido", MODE_PRIVATE);//arriba se explica
        int valorSeleccion = pref.getInt("valor", 0);//de donde lo llamamos
        return valorSeleccion;//devolvemos
    }
}
