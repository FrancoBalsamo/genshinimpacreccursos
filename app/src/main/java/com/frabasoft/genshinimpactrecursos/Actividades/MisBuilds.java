package com.frabasoft.genshinimpactrecursos.Actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.frabasoft.genshinimpactrecursos.R;
import com.frabasoft.genshinimpactrecursos.TouchImage.TouchImageView;
import com.master.permissionhelper.PermissionHelper;

import java.text.DecimalFormat;

public class MisBuilds extends AppCompatActivity {
    private Spinner spPJMisBuilds;
    private TouchImageView imgPJMisBuilds;
    private Button guardarFlor,guardarPluma, guardarReloj, guardarCopa, guardarCorona, guardarTodo;
    private String[] personajesString = {"Seleccione",
            "Albedo", "Amber",
            "Barbara", "Beidou", "Bennet",
            "Chongyun", "Diluc",
            "Diona", "Fischl", "Ganyu",
            "Hu Tao", "Jean", "Kaeya",
            "Keqing", "Klee", "Lisa",
            "Mona", "Ninguang",
            "Noelle", "Qiqi", "Razor",
            "Sucrose","Tartaglia", "Traveler Anemo",
            "Venti", "Xianling",
            "Xiao", "Xingqiu",
            "Xinyan","Zhongli"};

    private DecimalFormat df;
    PermissionHelper permissionHelper;
    private final static String TAG = "ERRORTAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_builds);

        spPJMisBuilds = (Spinner)findViewById(R.id.spPJMisBuilds);
        spPJMisBuilds.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, personajesString));
    }
}