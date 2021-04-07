package com.frabasoft.genshinimpactrecursos.Actividades;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.frabasoft.genshinimpactrecursos.Clases.Copa;
import com.frabasoft.genshinimpactrecursos.Clases.Corona;
import com.frabasoft.genshinimpactrecursos.Clases.Flor;
import com.frabasoft.genshinimpactrecursos.Clases.Pluma;
import com.frabasoft.genshinimpactrecursos.Clases.Reloj;
import com.frabasoft.genshinimpactrecursos.R;
import com.frabasoft.genshinimpactrecursos.SQLiteGenshin.Procesos.DatosProcesosSqlite;
import com.frabasoft.genshinimpactrecursos.TouchImage.TouchImageView;
import com.master.permissionhelper.PermissionHelper;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MisBuilds extends AppCompatActivity {
    private Spinner spPJMisBuilds;
    private TouchImageView imgPJMisBuilds;
    private String[] personajesString = {"Seleccione",
            "Albedo", "Amber",
            "Barbara", "Beidou", "Bennet",
            "Chongyun", "Diluc",
            "Diona", "Fischl", "Ganyu",
            "Hu Tao", "Jean", "Kaeya",
            "Keqing", "Klee", "Lisa",
            "Mona", "Ninguang",
            "Noelle", "Qiqi", "Razor",
            "Rosaria",
            "Sucrose","Tartaglia",
            "Venti", "Xianling",
            "Xiao", "Xingqiu",
            "Xinyan","Zhongli"};

    private DecimalFormat df;
    PermissionHelper permissionHelper;
    private final static String TAG = "ERRORTAG";
    private Button guardarFlor,guardarPluma, guardarReloj, guardarCopa, guardarCorona, guardarTodo;
    private EditText etFlorPrin, etFlorSecA, etFlorSecB, etFlorSecC, etFlorSecD;
    private EditText etPlumPrin, etPlumSecA, etPlumSecB, etPlumSecC, etPlumSecD;
    private EditText etRelPrin, etRelSecA, etRelSecB, etRelSecC, etRelSecD;
    private EditText etCopPrin, etCopSecA, etCopSecB, etCopSecC, etCopSecD;
    private EditText etCorPrin, etCorSecA, etCorSecB, etCorSecC, etCorSecD;
    private ArrayList<Flor> florArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_builds);

        ejecutar();

        etFlorPrin = (EditText)findViewById(R.id.etFlorPrin);
        etFlorSecA = (EditText)findViewById(R.id.etFlorSecA);
        etFlorSecB = (EditText)findViewById(R.id.etFlorSecB);
        etFlorSecC = (EditText)findViewById(R.id.etFlorSecC);
        etFlorSecD = (EditText)findViewById(R.id.etFlorSecD);

        etPlumPrin = (EditText)findViewById(R.id.etPlumPrin);
        etPlumSecA = (EditText)findViewById(R.id.etPlumSecA);
        etPlumSecB = (EditText)findViewById(R.id.etPlumSecB);
        etPlumSecC = (EditText)findViewById(R.id.etPlumSecC);
        etPlumSecD = (EditText)findViewById(R.id.etPlumSecD);

        etRelPrin = (EditText)findViewById(R.id.etRelPrin);
        etRelSecA = (EditText)findViewById(R.id.etRelSecA);
        etRelSecB = (EditText)findViewById(R.id.etRelSecB);
        etRelSecC = (EditText)findViewById(R.id.etRelSecC);
        etRelSecD = (EditText)findViewById(R.id.etRelSecD);

        spPJMisBuilds = (Spinner)findViewById(R.id.spPJMisBuilds);
        imgPJMisBuilds = (TouchImageView)findViewById(R.id.imgPJMisBuilds);
        guardarFlor = (Button)findViewById(R.id.guardarFlor);
        guardarPluma = (Button)findViewById(R.id.guardarPluma);
        guardarReloj = (Button)findViewById(R.id.guardarReloj);
        guardarCopa = (Button)findViewById(R.id.guardarCopa);
        guardarCorona = (Button)findViewById(R.id.guardarCorona);
        guardarTodo = (Button)findViewById(R.id.guardarTodo);

        df = new DecimalFormat("#.##");
        
        spPJMisBuilds.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, personajesString));
        spPJMisBuilds.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.wallpaper);
                    guardarFlor.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(MisBuilds.this, "Debe seleccionar un personaje primero.", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else if (position == 1) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.albedobuilds);
                    DatosProcesosSqlite datosProcesosSqlite = new DatosProcesosSqlite(getApplicationContext());
                    florArrayList = datosProcesosSqlite.mostrarDatosDelPjFlor(spPJMisBuilds.getSelectedItem().toString());
                    for(int i = 0; i < florArrayList.size(); i++){
                        if(etFlorPrin.getText().toString()=="" || etFlorPrin.getText().toString()==null || etFlorPrin.equals("")
                                || etFlorSecA.getText().toString()=="" || etFlorSecA.getText().toString()==null || etFlorSecA.equals("")
                                || etFlorSecB.getText().toString()=="" || etFlorSecB.getText().toString()==null || etFlorSecB.equals("")
                                || etFlorSecC.getText().toString()=="" || etFlorSecC.getText().toString()==null || etFlorSecC.equals("")
                                || etFlorSecD.getText().toString()=="" || etFlorSecD.getText().toString()==null || etFlorSecD.equals("")){
                             limpiarET();
                        }else{
                            etFlorPrin.setText(florArrayList.get(i).getPrincipal());
                            etFlorSecA.setText(florArrayList.get(i).getSecundarioA());
                            etFlorSecB.setText(florArrayList.get(i).getSecundarioB());
                            etFlorSecC.setText(florArrayList.get(i).getSecundarioC());
                            etFlorSecD.setText(florArrayList.get(i).getSecundarioD());
                        }
                    }
                    guardarFlor.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            guardarActualizarFlor(MisBuilds.this, spPJMisBuilds.getSelectedItem().toString());
                        }
                    });
                } else if (position == 2) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.amberbuilds);
                    DatosProcesosSqlite datosProcesosSqlite = new DatosProcesosSqlite(getApplicationContext());
                    florArrayList = datosProcesosSqlite.mostrarDatosDelPjFlor(spPJMisBuilds.getSelectedItem().toString());
                    for(int i = 0; i < florArrayList.size(); i++) {
                        if (etFlorPrin.getText().toString() == "" || etFlorPrin.getText().toString() == null || etFlorPrin.equals("")
                                || etFlorSecA.getText().toString() == "" || etFlorSecA.getText().toString() == null || etFlorSecA.equals("")
                                || etFlorSecB.getText().toString() == "" || etFlorSecB.getText().toString() == null || etFlorSecB.equals("")
                                || etFlorSecC.getText().toString() == "" || etFlorSecC.getText().toString() == null || etFlorSecC.equals("")
                                || etFlorSecD.getText().toString() == "" || etFlorSecD.getText().toString() == null || etFlorSecD.equals("")) {
                             limpiarET();
                        } else {
                            etFlorPrin.setText(florArrayList.get(i).getPrincipal());
                            etFlorSecA.setText(florArrayList.get(i).getSecundarioA());
                            etFlorSecB.setText(florArrayList.get(i).getSecundarioB());
                            etFlorSecC.setText(florArrayList.get(i).getSecundarioC());
                            etFlorSecD.setText(florArrayList.get(i).getSecundarioD());
                        }
                    }
                } else if (position == 3) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.barbarabuilds);
                    DatosProcesosSqlite datosProcesosSqlite = new DatosProcesosSqlite(getApplicationContext());
                    florArrayList = datosProcesosSqlite.mostrarDatosDelPjFlor(spPJMisBuilds.getSelectedItem().toString());
                    for(int i = 0; i < florArrayList.size(); i++) {
                        if (etFlorPrin.getText().toString() == "" || etFlorPrin.getText().toString() == null || etFlorPrin.equals("")
                                || etFlorSecA.getText().toString() == "" || etFlorSecA.getText().toString() == null || etFlorSecA.equals("")
                                || etFlorSecB.getText().toString() == "" || etFlorSecB.getText().toString() == null || etFlorSecB.equals("")
                                || etFlorSecC.getText().toString() == "" || etFlorSecC.getText().toString() == null || etFlorSecC.equals("")
                                || etFlorSecD.getText().toString() == "" || etFlorSecD.getText().toString() == null || etFlorSecD.equals("")) {
                             limpiarET();
                        } else {
                            etFlorPrin.setText(florArrayList.get(i).getPrincipal());
                            etFlorSecA.setText(florArrayList.get(i).getSecundarioA());
                            etFlorSecB.setText(florArrayList.get(i).getSecundarioB());
                            etFlorSecC.setText(florArrayList.get(i).getSecundarioC());
                            etFlorSecD.setText(florArrayList.get(i).getSecundarioD());
                        }
                    }
                } else if (position == 4) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.beidoubuilds);
                    DatosProcesosSqlite datosProcesosSqlite = new DatosProcesosSqlite(getApplicationContext());
                    florArrayList = datosProcesosSqlite.mostrarDatosDelPjFlor(spPJMisBuilds.getSelectedItem().toString());
                    for(int i = 0; i < florArrayList.size(); i++) {
                        if (etFlorPrin.getText().toString() == "" || etFlorPrin.getText().toString() == null || etFlorPrin.equals("")
                                || etFlorSecA.getText().toString() == "" || etFlorSecA.getText().toString() == null || etFlorSecA.equals("")
                                || etFlorSecB.getText().toString() == "" || etFlorSecB.getText().toString() == null || etFlorSecB.equals("")
                                || etFlorSecC.getText().toString() == "" || etFlorSecC.getText().toString() == null || etFlorSecC.equals("")
                                || etFlorSecD.getText().toString() == "" || etFlorSecD.getText().toString() == null || etFlorSecD.equals("")) {
                            limpiarET();
                        } else {
                            etFlorPrin.setText(florArrayList.get(i).getPrincipal());
                            etFlorSecA.setText(florArrayList.get(i).getSecundarioA());
                            etFlorSecB.setText(florArrayList.get(i).getSecundarioB());
                            etFlorSecC.setText(florArrayList.get(i).getSecundarioC());
                            etFlorSecD.setText(florArrayList.get(i).getSecundarioD());
                        }
                    }
                } else if (position == 5) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.bennetbuilds);
                    DatosProcesosSqlite datosProcesosSqlite = new DatosProcesosSqlite(getApplicationContext());
                    florArrayList = datosProcesosSqlite.mostrarDatosDelPjFlor(spPJMisBuilds.getSelectedItem().toString());
                    for(int i = 0; i < florArrayList.size(); i++) {
                        if (etFlorPrin.getText().toString() == "" || etFlorPrin.getText().toString() == null || etFlorPrin.equals("")
                                || etFlorSecA.getText().toString() == "" || etFlorSecA.getText().toString() == null || etFlorSecA.equals("")
                                || etFlorSecB.getText().toString() == "" || etFlorSecB.getText().toString() == null || etFlorSecB.equals("")
                                || etFlorSecC.getText().toString() == "" || etFlorSecC.getText().toString() == null || etFlorSecC.equals("")
                                || etFlorSecD.getText().toString() == "" || etFlorSecD.getText().toString() == null || etFlorSecD.equals("")) {
                            limpiarET();
                        } else {
                            etFlorPrin.setText(florArrayList.get(i).getPrincipal());
                            etFlorSecA.setText(florArrayList.get(i).getSecundarioA());
                            etFlorSecB.setText(florArrayList.get(i).getSecundarioB());
                            etFlorSecC.setText(florArrayList.get(i).getSecundarioC());
                            etFlorSecD.setText(florArrayList.get(i).getSecundarioD());
                        }
                    }
                } else if (position == 6) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.chongyunbuilds);
                    DatosProcesosSqlite datosProcesosSqlite = new DatosProcesosSqlite(getApplicationContext());
                    florArrayList = datosProcesosSqlite.mostrarDatosDelPjFlor(spPJMisBuilds.getSelectedItem().toString());
                    for(int i = 0; i < florArrayList.size(); i++) {
                        if (etFlorPrin.getText().toString() == "" || etFlorPrin.getText().toString() == null || etFlorPrin.equals("")
                                || etFlorSecA.getText().toString() == "" || etFlorSecA.getText().toString() == null || etFlorSecA.equals("")
                                || etFlorSecB.getText().toString() == "" || etFlorSecB.getText().toString() == null || etFlorSecB.equals("")
                                || etFlorSecC.getText().toString() == "" || etFlorSecC.getText().toString() == null || etFlorSecC.equals("")
                                || etFlorSecD.getText().toString() == "" || etFlorSecD.getText().toString() == null || etFlorSecD.equals("")) {
                            limpiarET();
                        } else {
                            etFlorPrin.setText(florArrayList.get(i).getPrincipal());
                            etFlorSecA.setText(florArrayList.get(i).getSecundarioA());
                            etFlorSecB.setText(florArrayList.get(i).getSecundarioB());
                            etFlorSecC.setText(florArrayList.get(i).getSecundarioC());
                            etFlorSecD.setText(florArrayList.get(i).getSecundarioD());
                        }
                    }
                } else if (position == 7) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.dilucbuilds);
                    DatosProcesosSqlite datosProcesosSqlite = new DatosProcesosSqlite(getApplicationContext());
                    florArrayList = datosProcesosSqlite.mostrarDatosDelPjFlor(spPJMisBuilds.getSelectedItem().toString());
                    for(int i = 0; i < florArrayList.size(); i++) {
                        if (etFlorPrin.getText().toString() == "" || etFlorPrin.getText().toString() == null || etFlorPrin.equals("")
                                || etFlorSecA.getText().toString() == "" || etFlorSecA.getText().toString() == null || etFlorSecA.equals("")
                                || etFlorSecB.getText().toString() == "" || etFlorSecB.getText().toString() == null || etFlorSecB.equals("")
                                || etFlorSecC.getText().toString() == "" || etFlorSecC.getText().toString() == null || etFlorSecC.equals("")
                                || etFlorSecD.getText().toString() == "" || etFlorSecD.getText().toString() == null || etFlorSecD.equals("")) {
                            limpiarET();
                        } else {
                            etFlorPrin.setText(florArrayList.get(i).getPrincipal());
                            etFlorSecA.setText(florArrayList.get(i).getSecundarioA());
                            etFlorSecB.setText(florArrayList.get(i).getSecundarioB());
                            etFlorSecC.setText(florArrayList.get(i).getSecundarioC());
                            etFlorSecD.setText(florArrayList.get(i).getSecundarioD());
                        }
                    }
                } else if (position == 8) {
                    imgPJMisBuilds.setImageResource(R.drawable.dionabuilds);
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.dilucbuilds);
                    DatosProcesosSqlite datosProcesosSqlite = new DatosProcesosSqlite(getApplicationContext());
                    florArrayList = datosProcesosSqlite.mostrarDatosDelPjFlor(spPJMisBuilds.getSelectedItem().toString());
                    for(int i = 0; i < florArrayList.size(); i++) {
                        if (etFlorPrin.getText().toString() == "" || etFlorPrin.getText().toString() == null || etFlorPrin.equals("")
                                || etFlorSecA.getText().toString() == "" || etFlorSecA.getText().toString() == null || etFlorSecA.equals("")
                                || etFlorSecB.getText().toString() == "" || etFlorSecB.getText().toString() == null || etFlorSecB.equals("")
                                || etFlorSecC.getText().toString() == "" || etFlorSecC.getText().toString() == null || etFlorSecC.equals("")
                                || etFlorSecD.getText().toString() == "" || etFlorSecD.getText().toString() == null || etFlorSecD.equals("")) {
                            limpiarET();
                        } else {
                            etFlorPrin.setText(florArrayList.get(i).getPrincipal());
                            etFlorSecA.setText(florArrayList.get(i).getSecundarioA());
                            etFlorSecB.setText(florArrayList.get(i).getSecundarioB());
                            etFlorSecC.setText(florArrayList.get(i).getSecundarioC());
                            etFlorSecD.setText(florArrayList.get(i).getSecundarioD());
                        }
                    }
                } else if (position == 9) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.fischlbuilds);
                    DatosProcesosSqlite datosProcesosSqlite = new DatosProcesosSqlite(getApplicationContext());
                    florArrayList = datosProcesosSqlite.mostrarDatosDelPjFlor(spPJMisBuilds.getSelectedItem().toString());
                    for(int i = 0; i < florArrayList.size(); i++) {
                        if (etFlorPrin.getText().toString() == "" || etFlorPrin.getText().toString() == null || etFlorPrin.equals("")
                                || etFlorSecA.getText().toString() == "" || etFlorSecA.getText().toString() == null || etFlorSecA.equals("")
                                || etFlorSecB.getText().toString() == "" || etFlorSecB.getText().toString() == null || etFlorSecB.equals("")
                                || etFlorSecC.getText().toString() == "" || etFlorSecC.getText().toString() == null || etFlorSecC.equals("")
                                || etFlorSecD.getText().toString() == "" || etFlorSecD.getText().toString() == null || etFlorSecD.equals("")) {
                            limpiarET();
                        } else {
                            etFlorPrin.setText(florArrayList.get(i).getPrincipal());
                            etFlorSecA.setText(florArrayList.get(i).getSecundarioA());
                            etFlorSecB.setText(florArrayList.get(i).getSecundarioB());
                            etFlorSecC.setText(florArrayList.get(i).getSecundarioC());
                            etFlorSecD.setText(florArrayList.get(i).getSecundarioD());
                        }
                    }
                } else if (position == 10) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.ganyubuilds);
                    DatosProcesosSqlite datosProcesosSqlite = new DatosProcesosSqlite(getApplicationContext());
                    florArrayList = datosProcesosSqlite.mostrarDatosDelPjFlor(spPJMisBuilds.getSelectedItem().toString());
                    for(int i = 0; i < florArrayList.size(); i++) {
                        if (etFlorPrin.getText().toString() == "" || etFlorPrin.getText().toString() == null || etFlorPrin.equals("")
                                || etFlorSecA.getText().toString() == "" || etFlorSecA.getText().toString() == null || etFlorSecA.equals("")
                                || etFlorSecB.getText().toString() == "" || etFlorSecB.getText().toString() == null || etFlorSecB.equals("")
                                || etFlorSecC.getText().toString() == "" || etFlorSecC.getText().toString() == null || etFlorSecC.equals("")
                                || etFlorSecD.getText().toString() == "" || etFlorSecD.getText().toString() == null || etFlorSecD.equals("")) {
                            limpiarET();
                        } else {
                            etFlorPrin.setText(florArrayList.get(i).getPrincipal());
                            etFlorSecA.setText(florArrayList.get(i).getSecundarioA());
                            etFlorSecB.setText(florArrayList.get(i).getSecundarioB());
                            etFlorSecC.setText(florArrayList.get(i).getSecundarioC());
                            etFlorSecD.setText(florArrayList.get(i).getSecundarioD());
                        }
                    }
                } else if (position == 11) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.hutaobuilds);
                    DatosProcesosSqlite datosProcesosSqlite = new DatosProcesosSqlite(getApplicationContext());
                    florArrayList = datosProcesosSqlite.mostrarDatosDelPjFlor(spPJMisBuilds.getSelectedItem().toString());
                    for(int i = 0; i < florArrayList.size(); i++) {
                        if (etFlorPrin.getText().toString() == "" || etFlorPrin.getText().toString() == null || etFlorPrin.equals("")
                                || etFlorSecA.getText().toString() == "" || etFlorSecA.getText().toString() == null || etFlorSecA.equals("")
                                || etFlorSecB.getText().toString() == "" || etFlorSecB.getText().toString() == null || etFlorSecB.equals("")
                                || etFlorSecC.getText().toString() == "" || etFlorSecC.getText().toString() == null || etFlorSecC.equals("")
                                || etFlorSecD.getText().toString() == "" || etFlorSecD.getText().toString() == null || etFlorSecD.equals("")) {
                            limpiarET();
                        } else {
                            etFlorPrin.setText(florArrayList.get(i).getPrincipal());
                            etFlorSecA.setText(florArrayList.get(i).getSecundarioA());
                            etFlorSecB.setText(florArrayList.get(i).getSecundarioB());
                            etFlorSecC.setText(florArrayList.get(i).getSecundarioC());
                            etFlorSecD.setText(florArrayList.get(i).getSecundarioD());
                        }
                    }
                } else if (position == 12) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.jeanbuilds);
                    DatosProcesosSqlite datosProcesosSqlite = new DatosProcesosSqlite(getApplicationContext());
                    florArrayList = datosProcesosSqlite.mostrarDatosDelPjFlor(spPJMisBuilds.getSelectedItem().toString());
                    for(int i = 0; i < florArrayList.size(); i++) {
                        if (etFlorPrin.getText().toString() == "" || etFlorPrin.getText().toString() == null || etFlorPrin.equals("")
                                || etFlorSecA.getText().toString() == "" || etFlorSecA.getText().toString() == null || etFlorSecA.equals("")
                                || etFlorSecB.getText().toString() == "" || etFlorSecB.getText().toString() == null || etFlorSecB.equals("")
                                || etFlorSecC.getText().toString() == "" || etFlorSecC.getText().toString() == null || etFlorSecC.equals("")
                                || etFlorSecD.getText().toString() == "" || etFlorSecD.getText().toString() == null || etFlorSecD.equals("")) {
                            limpiarET();
                        } else {
                            etFlorPrin.setText(florArrayList.get(i).getPrincipal());
                            etFlorSecA.setText(florArrayList.get(i).getSecundarioA());
                            etFlorSecB.setText(florArrayList.get(i).getSecundarioB());
                            etFlorSecC.setText(florArrayList.get(i).getSecundarioC());
                            etFlorSecD.setText(florArrayList.get(i).getSecundarioD());
                        }
                    }
                } else if (position == 13) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.kaeyabuilds);
                    DatosProcesosSqlite datosProcesosSqlite = new DatosProcesosSqlite(getApplicationContext());
                    florArrayList = datosProcesosSqlite.mostrarDatosDelPjFlor(spPJMisBuilds.getSelectedItem().toString());
                    for(int i = 0; i < florArrayList.size(); i++) {
                        if (etFlorPrin.getText().toString() == "" || etFlorPrin.getText().toString() == null || etFlorPrin.equals("")
                                || etFlorSecA.getText().toString() == "" || etFlorSecA.getText().toString() == null || etFlorSecA.equals("")
                                || etFlorSecB.getText().toString() == "" || etFlorSecB.getText().toString() == null || etFlorSecB.equals("")
                                || etFlorSecC.getText().toString() == "" || etFlorSecC.getText().toString() == null || etFlorSecC.equals("")
                                || etFlorSecD.getText().toString() == "" || etFlorSecD.getText().toString() == null || etFlorSecD.equals("")) {
                            limpiarET();
                        } else {
                            etFlorPrin.setText(florArrayList.get(i).getPrincipal());
                            etFlorSecA.setText(florArrayList.get(i).getSecundarioA());
                            etFlorSecB.setText(florArrayList.get(i).getSecundarioB());
                            etFlorSecC.setText(florArrayList.get(i).getSecundarioC());
                            etFlorSecD.setText(florArrayList.get(i).getSecundarioD());
                        }
                    }
                } else if (position == 14) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.keqingbuilds);
                    DatosProcesosSqlite datosProcesosSqlite = new DatosProcesosSqlite(getApplicationContext());
                    florArrayList = datosProcesosSqlite.mostrarDatosDelPjFlor(spPJMisBuilds.getSelectedItem().toString());
                    for(int i = 0; i < florArrayList.size(); i++) {
                        if (etFlorPrin.getText().toString() == "" || etFlorPrin.getText().toString() == null || etFlorPrin.equals("")
                                || etFlorSecA.getText().toString() == "" || etFlorSecA.getText().toString() == null || etFlorSecA.equals("")
                                || etFlorSecB.getText().toString() == "" || etFlorSecB.getText().toString() == null || etFlorSecB.equals("")
                                || etFlorSecC.getText().toString() == "" || etFlorSecC.getText().toString() == null || etFlorSecC.equals("")
                                || etFlorSecD.getText().toString() == "" || etFlorSecD.getText().toString() == null || etFlorSecD.equals("")) {
                            limpiarET();
                        } else {
                            etFlorPrin.setText(florArrayList.get(i).getPrincipal());
                            etFlorSecA.setText(florArrayList.get(i).getSecundarioA());
                            etFlorSecB.setText(florArrayList.get(i).getSecundarioB());
                            etFlorSecC.setText(florArrayList.get(i).getSecundarioC());
                            etFlorSecD.setText(florArrayList.get(i).getSecundarioD());
                        }
                    }
                } else if (position == 15) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.kleebuilds);
                    DatosProcesosSqlite datosProcesosSqlite = new DatosProcesosSqlite(getApplicationContext());
                    florArrayList = datosProcesosSqlite.mostrarDatosDelPjFlor(spPJMisBuilds.getSelectedItem().toString());
                    for(int i = 0; i < florArrayList.size(); i++) {
                        if (etFlorPrin.getText().toString() == "" || etFlorPrin.getText().toString() == null || etFlorPrin.equals("")
                                || etFlorSecA.getText().toString() == "" || etFlorSecA.getText().toString() == null || etFlorSecA.equals("")
                                || etFlorSecB.getText().toString() == "" || etFlorSecB.getText().toString() == null || etFlorSecB.equals("")
                                || etFlorSecC.getText().toString() == "" || etFlorSecC.getText().toString() == null || etFlorSecC.equals("")
                                || etFlorSecD.getText().toString() == "" || etFlorSecD.getText().toString() == null || etFlorSecD.equals("")) {
                            limpiarET();
                        } else {
                            etFlorPrin.setText(florArrayList.get(i).getPrincipal());
                            etFlorSecA.setText(florArrayList.get(i).getSecundarioA());
                            etFlorSecB.setText(florArrayList.get(i).getSecundarioB());
                            etFlorSecC.setText(florArrayList.get(i).getSecundarioC());
                            etFlorSecD.setText(florArrayList.get(i).getSecundarioD());
                        }
                    }
                } else if (position == 16) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.lisabuilds);
                    DatosProcesosSqlite datosProcesosSqlite = new DatosProcesosSqlite(getApplicationContext());
                    florArrayList = datosProcesosSqlite.mostrarDatosDelPjFlor(spPJMisBuilds.getSelectedItem().toString());
                    for(int i = 0; i < florArrayList.size(); i++) {
                        if (etFlorPrin.getText().toString() == "" || etFlorPrin.getText().toString() == null || etFlorPrin.equals("")
                                || etFlorSecA.getText().toString() == "" || etFlorSecA.getText().toString() == null || etFlorSecA.equals("")
                                || etFlorSecB.getText().toString() == "" || etFlorSecB.getText().toString() == null || etFlorSecB.equals("")
                                || etFlorSecC.getText().toString() == "" || etFlorSecC.getText().toString() == null || etFlorSecC.equals("")
                                || etFlorSecD.getText().toString() == "" || etFlorSecD.getText().toString() == null || etFlorSecD.equals("")) {
                            limpiarET();
                        } else {
                            etFlorPrin.setText(florArrayList.get(i).getPrincipal());
                            etFlorSecA.setText(florArrayList.get(i).getSecundarioA());
                            etFlorSecB.setText(florArrayList.get(i).getSecundarioB());
                            etFlorSecC.setText(florArrayList.get(i).getSecundarioC());
                            etFlorSecD.setText(florArrayList.get(i).getSecundarioD());
                        }
                    }
                } else if (position == 17) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.monabuilds);
                    DatosProcesosSqlite datosProcesosSqlite = new DatosProcesosSqlite(getApplicationContext());
                    florArrayList = datosProcesosSqlite.mostrarDatosDelPjFlor(spPJMisBuilds.getSelectedItem().toString());
                    for(int i = 0; i < florArrayList.size(); i++) {
                        if (etFlorPrin.getText().toString() == "" || etFlorPrin.getText().toString() == null || etFlorPrin.equals("")
                                || etFlorSecA.getText().toString() == "" || etFlorSecA.getText().toString() == null || etFlorSecA.equals("")
                                || etFlorSecB.getText().toString() == "" || etFlorSecB.getText().toString() == null || etFlorSecB.equals("")
                                || etFlorSecC.getText().toString() == "" || etFlorSecC.getText().toString() == null || etFlorSecC.equals("")
                                || etFlorSecD.getText().toString() == "" || etFlorSecD.getText().toString() == null || etFlorSecD.equals("")) {
                            limpiarET();
                        } else {
                            etFlorPrin.setText(florArrayList.get(i).getPrincipal());
                            etFlorSecA.setText(florArrayList.get(i).getSecundarioA());
                            etFlorSecB.setText(florArrayList.get(i).getSecundarioB());
                            etFlorSecC.setText(florArrayList.get(i).getSecundarioC());
                            etFlorSecD.setText(florArrayList.get(i).getSecundarioD());
                        }
                    }
                } else if (position == 18) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.ningguangbuilds);
                    DatosProcesosSqlite datosProcesosSqlite = new DatosProcesosSqlite(getApplicationContext());
                    florArrayList = datosProcesosSqlite.mostrarDatosDelPjFlor(spPJMisBuilds.getSelectedItem().toString());
                    for(int i = 0; i < florArrayList.size(); i++) {
                        if (etFlorPrin.getText().toString() == "" || etFlorPrin.getText().toString() == null || etFlorPrin.equals("")
                                || etFlorSecA.getText().toString() == "" || etFlorSecA.getText().toString() == null || etFlorSecA.equals("")
                                || etFlorSecB.getText().toString() == "" || etFlorSecB.getText().toString() == null || etFlorSecB.equals("")
                                || etFlorSecC.getText().toString() == "" || etFlorSecC.getText().toString() == null || etFlorSecC.equals("")
                                || etFlorSecD.getText().toString() == "" || etFlorSecD.getText().toString() == null || etFlorSecD.equals("")) {
                            limpiarET();
                        } else {
                            etFlorPrin.setText(florArrayList.get(i).getPrincipal());
                            etFlorSecA.setText(florArrayList.get(i).getSecundarioA());
                            etFlorSecB.setText(florArrayList.get(i).getSecundarioB());
                            etFlorSecC.setText(florArrayList.get(i).getSecundarioC());
                            etFlorSecD.setText(florArrayList.get(i).getSecundarioD());
                        }
                    }
                } else if (position == 19) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.noellebuilds);
                    DatosProcesosSqlite datosProcesosSqlite = new DatosProcesosSqlite(getApplicationContext());
                    florArrayList = datosProcesosSqlite.mostrarDatosDelPjFlor(spPJMisBuilds.getSelectedItem().toString());
                    for(int i = 0; i < florArrayList.size(); i++) {
                        if (etFlorPrin.getText().toString() == "" || etFlorPrin.getText().toString() == null || etFlorPrin.equals("")
                                || etFlorSecA.getText().toString() == "" || etFlorSecA.getText().toString() == null || etFlorSecA.equals("")
                                || etFlorSecB.getText().toString() == "" || etFlorSecB.getText().toString() == null || etFlorSecB.equals("")
                                || etFlorSecC.getText().toString() == "" || etFlorSecC.getText().toString() == null || etFlorSecC.equals("")
                                || etFlorSecD.getText().toString() == "" || etFlorSecD.getText().toString() == null || etFlorSecD.equals("")) {
                            limpiarET();
                        } else {
                            etFlorPrin.setText(florArrayList.get(i).getPrincipal());
                            etFlorSecA.setText(florArrayList.get(i).getSecundarioA());
                            etFlorSecB.setText(florArrayList.get(i).getSecundarioB());
                            etFlorSecC.setText(florArrayList.get(i).getSecundarioC());
                            etFlorSecD.setText(florArrayList.get(i).getSecundarioD());
                        }
                    }
                } else if (position == 20) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.qiqibuilds);
                    DatosProcesosSqlite datosProcesosSqlite = new DatosProcesosSqlite(getApplicationContext());
                    florArrayList = datosProcesosSqlite.mostrarDatosDelPjFlor(spPJMisBuilds.getSelectedItem().toString());
                    for(int i = 0; i < florArrayList.size(); i++) {
                        if (etFlorPrin.getText().toString() == "" || etFlorPrin.getText().toString() == null || etFlorPrin.equals("")
                                || etFlorSecA.getText().toString() == "" || etFlorSecA.getText().toString() == null || etFlorSecA.equals("")
                                || etFlorSecB.getText().toString() == "" || etFlorSecB.getText().toString() == null || etFlorSecB.equals("")
                                || etFlorSecC.getText().toString() == "" || etFlorSecC.getText().toString() == null || etFlorSecC.equals("")
                                || etFlorSecD.getText().toString() == "" || etFlorSecD.getText().toString() == null || etFlorSecD.equals("")) {
                            limpiarET();
                        } else {
                            etFlorPrin.setText(florArrayList.get(i).getPrincipal());
                            etFlorSecA.setText(florArrayList.get(i).getSecundarioA());
                            etFlorSecB.setText(florArrayList.get(i).getSecundarioB());
                            etFlorSecC.setText(florArrayList.get(i).getSecundarioC());
                            etFlorSecD.setText(florArrayList.get(i).getSecundarioD());
                        }
                    }
                } else if (position == 21) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.razorbuilds);
                    DatosProcesosSqlite datosProcesosSqlite = new DatosProcesosSqlite(getApplicationContext());
                    florArrayList = datosProcesosSqlite.mostrarDatosDelPjFlor(spPJMisBuilds.getSelectedItem().toString());
                    for(int i = 0; i < florArrayList.size(); i++) {
                        if (etFlorPrin.getText().toString() == "" || etFlorPrin.getText().toString() == null || etFlorPrin.equals("")
                                || etFlorSecA.getText().toString() == "" || etFlorSecA.getText().toString() == null || etFlorSecA.equals("")
                                || etFlorSecB.getText().toString() == "" || etFlorSecB.getText().toString() == null || etFlorSecB.equals("")
                                || etFlorSecC.getText().toString() == "" || etFlorSecC.getText().toString() == null || etFlorSecC.equals("")
                                || etFlorSecD.getText().toString() == "" || etFlorSecD.getText().toString() == null || etFlorSecD.equals("")) {
                            limpiarET();
                        } else {
                            etFlorPrin.setText(florArrayList.get(i).getPrincipal());
                            etFlorSecA.setText(florArrayList.get(i).getSecundarioA());
                            etFlorSecB.setText(florArrayList.get(i).getSecundarioB());
                            etFlorSecC.setText(florArrayList.get(i).getSecundarioC());
                            etFlorSecD.setText(florArrayList.get(i).getSecundarioD());
                        }
                    }
                } else if (position == 22) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.rosariabuilds);
                    DatosProcesosSqlite datosProcesosSqlite = new DatosProcesosSqlite(getApplicationContext());
                    florArrayList = datosProcesosSqlite.mostrarDatosDelPjFlor(spPJMisBuilds.getSelectedItem().toString());
                    for(int i = 0; i < florArrayList.size(); i++) {
                        if (etFlorPrin.getText().toString() == "" || etFlorPrin.getText().toString() == null || etFlorPrin.equals("")
                                || etFlorSecA.getText().toString() == "" || etFlorSecA.getText().toString() == null || etFlorSecA.equals("")
                                || etFlorSecB.getText().toString() == "" || etFlorSecB.getText().toString() == null || etFlorSecB.equals("")
                                || etFlorSecC.getText().toString() == "" || etFlorSecC.getText().toString() == null || etFlorSecC.equals("")
                                || etFlorSecD.getText().toString() == "" || etFlorSecD.getText().toString() == null || etFlorSecD.equals("")) {
                            limpiarET();
                        } else {
                            etFlorPrin.setText(florArrayList.get(i).getPrincipal());
                            etFlorSecA.setText(florArrayList.get(i).getSecundarioA());
                            etFlorSecB.setText(florArrayList.get(i).getSecundarioB());
                            etFlorSecC.setText(florArrayList.get(i).getSecundarioC());
                            etFlorSecD.setText(florArrayList.get(i).getSecundarioD());
                        }
                    }
                } else if (position == 23) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.sacarosabuilds);
                    DatosProcesosSqlite datosProcesosSqlite = new DatosProcesosSqlite(getApplicationContext());
                    florArrayList = datosProcesosSqlite.mostrarDatosDelPjFlor(spPJMisBuilds.getSelectedItem().toString());
                    for(int i = 0; i < florArrayList.size(); i++) {
                        if (etFlorPrin.getText().toString() == "" || etFlorPrin.getText().toString() == null || etFlorPrin.equals("")
                                || etFlorSecA.getText().toString() == "" || etFlorSecA.getText().toString() == null || etFlorSecA.equals("")
                                || etFlorSecB.getText().toString() == "" || etFlorSecB.getText().toString() == null || etFlorSecB.equals("")
                                || etFlorSecC.getText().toString() == "" || etFlorSecC.getText().toString() == null || etFlorSecC.equals("")
                                || etFlorSecD.getText().toString() == "" || etFlorSecD.getText().toString() == null || etFlorSecD.equals("")) {
                            limpiarET();
                        } else {
                            etFlorPrin.setText(florArrayList.get(i).getPrincipal());
                            etFlorSecA.setText(florArrayList.get(i).getSecundarioA());
                            etFlorSecB.setText(florArrayList.get(i).getSecundarioB());
                            etFlorSecC.setText(florArrayList.get(i).getSecundarioC());
                            etFlorSecD.setText(florArrayList.get(i).getSecundarioD());
                        }
                    }
                } else if (position == 24) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.tartagliabuilds);
                    DatosProcesosSqlite datosProcesosSqlite = new DatosProcesosSqlite(getApplicationContext());
                    florArrayList = datosProcesosSqlite.mostrarDatosDelPjFlor(spPJMisBuilds.getSelectedItem().toString());
                    for(int i = 0; i < florArrayList.size(); i++) {
                        if (etFlorPrin.getText().toString() == "" || etFlorPrin.getText().toString() == null || etFlorPrin.equals("")
                                || etFlorSecA.getText().toString() == "" || etFlorSecA.getText().toString() == null || etFlorSecA.equals("")
                                || etFlorSecB.getText().toString() == "" || etFlorSecB.getText().toString() == null || etFlorSecB.equals("")
                                || etFlorSecC.getText().toString() == "" || etFlorSecC.getText().toString() == null || etFlorSecC.equals("")
                                || etFlorSecD.getText().toString() == "" || etFlorSecD.getText().toString() == null || etFlorSecD.equals("")) {
                            limpiarET();
                        } else {
                            etFlorPrin.setText(florArrayList.get(i).getPrincipal());
                            etFlorSecA.setText(florArrayList.get(i).getSecundarioA());
                            etFlorSecB.setText(florArrayList.get(i).getSecundarioB());
                            etFlorSecC.setText(florArrayList.get(i).getSecundarioC());
                            etFlorSecD.setText(florArrayList.get(i).getSecundarioD());
                        }
                    }
                } else if (position == 25) {
                    imgPJMisBuilds.setImageResource(R.drawable.ventibuilds);
                    DatosProcesosSqlite datosProcesosSqlite = new DatosProcesosSqlite(getApplicationContext());
                    florArrayList = datosProcesosSqlite.mostrarDatosDelPjFlor(spPJMisBuilds.getSelectedItem().toString());
                    for(int i = 0; i < florArrayList.size(); i++) {
                        if (etFlorPrin.getText().toString() == "" || etFlorPrin.getText().toString() == null || etFlorPrin.equals("")
                                || etFlorSecA.getText().toString() == "" || etFlorSecA.getText().toString() == null || etFlorSecA.equals("")
                                || etFlorSecB.getText().toString() == "" || etFlorSecB.getText().toString() == null || etFlorSecB.equals("")
                                || etFlorSecC.getText().toString() == "" || etFlorSecC.getText().toString() == null || etFlorSecC.equals("")
                                || etFlorSecD.getText().toString() == "" || etFlorSecD.getText().toString() == null || etFlorSecD.equals("")) {
                            limpiarET();
                        } else {
                            limpiarET();
                        }
                    }
                } else if (position == 26) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.xianlingbuilds);
                    DatosProcesosSqlite datosProcesosSqlite = new DatosProcesosSqlite(getApplicationContext());
                    florArrayList = datosProcesosSqlite.mostrarDatosDelPjFlor(spPJMisBuilds.getSelectedItem().toString());
                    for(int i = 0; i < florArrayList.size(); i++) {
                        if (etFlorPrin.getText().toString() == "" || etFlorPrin.getText().toString() == null || etFlorPrin.equals("")
                                || etFlorSecA.getText().toString() == "" || etFlorSecA.getText().toString() == null || etFlorSecA.equals("")
                                || etFlorSecB.getText().toString() == "" || etFlorSecB.getText().toString() == null || etFlorSecB.equals("")
                                || etFlorSecC.getText().toString() == "" || etFlorSecC.getText().toString() == null || etFlorSecC.equals("")
                                || etFlorSecD.getText().toString() == "" || etFlorSecD.getText().toString() == null || etFlorSecD.equals("")) {
                            limpiarET();
                        } else {
                            etFlorPrin.setText(florArrayList.get(i).getPrincipal());
                            etFlorSecA.setText(florArrayList.get(i).getSecundarioA());
                            etFlorSecB.setText(florArrayList.get(i).getSecundarioB());
                            etFlorSecC.setText(florArrayList.get(i).getSecundarioC());
                            etFlorSecD.setText(florArrayList.get(i).getSecundarioD());
                        }
                    }
                } else if (position == 27) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.xiaobuilds);
                    DatosProcesosSqlite datosProcesosSqlite = new DatosProcesosSqlite(getApplicationContext());
                    florArrayList = datosProcesosSqlite.mostrarDatosDelPjFlor(spPJMisBuilds.getSelectedItem().toString());
                    for(int i = 0; i < florArrayList.size(); i++) {
                        if (etFlorPrin.getText().toString() == "" || etFlorPrin.getText().toString() == null || etFlorPrin.equals("")
                                || etFlorSecA.getText().toString() == "" || etFlorSecA.getText().toString() == null || etFlorSecA.equals("")
                                || etFlorSecB.getText().toString() == "" || etFlorSecB.getText().toString() == null || etFlorSecB.equals("")
                                || etFlorSecC.getText().toString() == "" || etFlorSecC.getText().toString() == null || etFlorSecC.equals("")
                                || etFlorSecD.getText().toString() == "" || etFlorSecD.getText().toString() == null || etFlorSecD.equals("")) {
                            limpiarET();
                        } else {
                            etFlorPrin.setText(florArrayList.get(i).getPrincipal());
                            etFlorSecA.setText(florArrayList.get(i).getSecundarioA());
                            etFlorSecB.setText(florArrayList.get(i).getSecundarioB());
                            etFlorSecC.setText(florArrayList.get(i).getSecundarioC());
                            etFlorSecD.setText(florArrayList.get(i).getSecundarioD());
                        }
                    }
                } else if (position == 28) {
                    imgPJMisBuilds.setImageResource(R.drawable.xingqiubuilds);
                    DatosProcesosSqlite datosProcesosSqlite = new DatosProcesosSqlite(getApplicationContext());
                    florArrayList = datosProcesosSqlite.mostrarDatosDelPjFlor(spPJMisBuilds.getSelectedItem().toString());
                    for(int i = 0; i < florArrayList.size(); i++) {
                        if (etFlorPrin.getText().toString() == "" || etFlorPrin.getText().toString() == null || etFlorPrin.equals("")
                                || etFlorSecA.getText().toString() == "" || etFlorSecA.getText().toString() == null || etFlorSecA.equals("")
                                || etFlorSecB.getText().toString() == "" || etFlorSecB.getText().toString() == null || etFlorSecB.equals("")
                                || etFlorSecC.getText().toString() == "" || etFlorSecC.getText().toString() == null || etFlorSecC.equals("")
                                || etFlorSecD.getText().toString() == "" || etFlorSecD.getText().toString() == null || etFlorSecD.equals("")) {
                            limpiarET();
                        } else {
                            etFlorPrin.setText(florArrayList.get(i).getPrincipal());
                            etFlorSecA.setText(florArrayList.get(i).getSecundarioA());
                            etFlorSecB.setText(florArrayList.get(i).getSecundarioB());
                            etFlorSecC.setText(florArrayList.get(i).getSecundarioC());
                            etFlorSecD.setText(florArrayList.get(i).getSecundarioD());
                        }
                    }
                } else if (position == 29) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.xinyanbuilds);
                    DatosProcesosSqlite datosProcesosSqlite = new DatosProcesosSqlite(getApplicationContext());
                    florArrayList = datosProcesosSqlite.mostrarDatosDelPjFlor(spPJMisBuilds.getSelectedItem().toString());
                    for(int i = 0; i < florArrayList.size(); i++) {
                        if (etFlorPrin.getText().toString() == "" || etFlorPrin.getText().toString() == null || etFlorPrin.equals("")
                                || etFlorSecA.getText().toString() == "" || etFlorSecA.getText().toString() == null || etFlorSecA.equals("")
                                || etFlorSecB.getText().toString() == "" || etFlorSecB.getText().toString() == null || etFlorSecB.equals("")
                                || etFlorSecC.getText().toString() == "" || etFlorSecC.getText().toString() == null || etFlorSecC.equals("")
                                || etFlorSecD.getText().toString() == "" || etFlorSecD.getText().toString() == null || etFlorSecD.equals("")) {
                            limpiarET();
                        } else {
                            etFlorPrin.setText(florArrayList.get(i).getPrincipal());
                            etFlorSecA.setText(florArrayList.get(i).getSecundarioA());
                            etFlorSecB.setText(florArrayList.get(i).getSecundarioB());
                            etFlorSecC.setText(florArrayList.get(i).getSecundarioC());
                            etFlorSecD.setText(florArrayList.get(i).getSecundarioD());
                        }
                    }
                } else if (position == 30) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.zhonglibuilds);
                    DatosProcesosSqlite datosProcesosSqlite = new DatosProcesosSqlite(getApplicationContext());
                    florArrayList = datosProcesosSqlite.mostrarDatosDelPjFlor(spPJMisBuilds.getSelectedItem().toString());
                    for(int i = 0; i < florArrayList.size(); i++) {
                        if (etFlorPrin.getText().toString() == "" || etFlorPrin.getText().toString() == null || etFlorPrin.equals("")
                                || etFlorSecA.getText().toString() == "" || etFlorSecA.getText().toString() == null || etFlorSecA.equals("")
                                || etFlorSecB.getText().toString() == "" || etFlorSecB.getText().toString() == null || etFlorSecB.equals("")
                                || etFlorSecC.getText().toString() == "" || etFlorSecC.getText().toString() == null || etFlorSecC.equals("")
                                || etFlorSecD.getText().toString() == "" || etFlorSecD.getText().toString() == null || etFlorSecD.equals("")) {
                            limpiarET();
                        } else {
                            etFlorPrin.setText(florArrayList.get(i).getPrincipal());
                            etFlorSecA.setText(florArrayList.get(i).getSecundarioA());
                            etFlorSecB.setText(florArrayList.get(i).getSecundarioB());
                            etFlorSecC.setText(florArrayList.get(i).getSecundarioC());
                            etFlorSecD.setText(florArrayList.get(i).getSecundarioD());
                        }
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

    private void ejecutar(){
        permissionHelper = new PermissionHelper(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        permissionHelper.request(new PermissionHelper.PermissionCallback() {
            @Override
            public void onPermissionGranted() {
                Log.d(TAG, "onPermissionGranted() called");
            }

            @Override
            public void onIndividualPermissionGranted(String[] grantedPermission) {
                Log.d(TAG, "onIndividualPermissionGranted() called with: grantedPermission = [" + TextUtils.join(",",grantedPermission) + "]");
            }

            @Override
            public void onPermissionDenied() {
                Log.d(TAG, "onPermissionDenied() called");
            }

            @Override
            public void onPermissionDeniedBySystem() {
                Log.d(TAG, "onPermissionDeniedBySystem() called");
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissionHelper != null) {
            permissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void guardarActualizarFlor(Context actividad, String personaje){
        if (TextUtils.isEmpty(etFlorPrin.getText().toString())) {
            etFlorPrin.setError("El campo no puede estar vacío.");
            etFlorPrin.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(etFlorSecA.getText().toString())) {
            etFlorSecA.setError("El campo no puede estar vacío.");
            etFlorSecA.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(etFlorSecB.getText().toString())) {
            etFlorSecB.setError("El campo no puede estar vacío.");
            etFlorSecB.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(etFlorSecC.getText().toString())) {
            etFlorSecC.setError("El campo no puede estar vacío.");
            etFlorSecC.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(etFlorSecD.getText().toString())) {
            etFlorSecD.setError("El campo no puede estar vacío.");
            etFlorSecD.requestFocus();
            return;
        }

        DatosProcesosSqlite datosProcesosSqlite = new DatosProcesosSqlite(actividad);
        Flor flor = new Flor();
        flor.setNombrePersonaje(personaje);
        flor.setPrincipal(etFlorPrin.getText().toString());
        flor.setSecundarioA(etFlorSecA.getText().toString());
        flor.setSecundarioB(etFlorSecB.getText().toString());
        flor.setSecundarioC(etFlorSecC.getText().toString());
        flor.setSecundarioD(etFlorSecD.getText().toString());
        datosProcesosSqlite.validarUInsertUpdateFlor(actividad, personaje, flor);
    }

    private void guardarActualizarPluma(Context actividad, String personaje){
        if (TextUtils.isEmpty(etPlumPrin.getText().toString())) {
            etPlumPrin.setError("El campo no puede estar vacío.");
            etPlumPrin.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(etPlumSecA.getText().toString())) {
            etPlumSecA.setError("El campo no puede estar vacío.");
            etPlumSecA.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(etPlumSecB.getText().toString())) {
            etPlumSecB.setError("El campo no puede estar vacío.");
            etPlumSecB.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(etPlumSecC.getText().toString())) {
            etPlumSecC.setError("El campo no puede estar vacío.");
            etPlumSecC.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(etPlumSecD.getText().toString())) {
            etPlumSecD.setError("El campo no puede estar vacío.");
            etPlumSecD.requestFocus();
            return;
        }

        DatosProcesosSqlite datosProcesosSqlite = new DatosProcesosSqlite(actividad);
        Pluma plumar = new Pluma();
        plumar.setNombrePersonaje(personaje);
        plumar.setPrincipal(etPlumPrin.getText().toString());
        plumar.setSecundarioA(etPlumSecA.getText().toString());
        plumar.setSecundarioB(etPlumSecB.getText().toString());
        plumar.setSecundarioC(etPlumSecC.getText().toString());
        plumar.setSecundarioD(etPlumSecD.getText().toString());
        datosProcesosSqlite.validarUInsertUpdatePluma(actividad, personaje, plumar);
    }

    private void guardarActualizarReloj(Context actividad, String personaje){
        if (TextUtils.isEmpty(etRelPrin.getText().toString())) {
            etRelPrin.setError("El campo no puede estar vacío.");
            etRelPrin.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(etRelSecA.getText().toString())) {
            etRelSecA.setError("El campo no puede estar vacío.");
            etRelSecA.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(etRelSecB.getText().toString())) {
            etRelSecB.setError("El campo no puede estar vacío.");
            etRelSecB.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(etRelSecC.getText().toString())) {
            etRelSecC.setError("El campo no puede estar vacío.");
            etRelSecC.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(etRelSecD.getText().toString())) {
            etRelSecD.setError("El campo no puede estar vacío.");
            etRelSecD.requestFocus();
            return;
        }

        DatosProcesosSqlite datosProcesosSqlite = new DatosProcesosSqlite(actividad);
        Reloj reloj = new Reloj();
        reloj.setNombrePersonaje(personaje);
        reloj.setPrincipal(etRelPrin.getText().toString());
        reloj.setSecundarioA(etRelSecA.getText().toString());
        reloj.setSecundarioB(etRelSecB.getText().toString());
        reloj.setSecundarioC(etRelSecC.getText().toString());
        reloj.setSecundarioD(etRelSecD.getText().toString());
        datosProcesosSqlite.validarUInsertUpdateReloj(actividad, personaje, reloj);
    }

    private void guardarActualizarCopa(Context actividad, String personaje){
        if (TextUtils.isEmpty(etCopPrin.getText().toString())) {
            etCopPrin.setError("El campo no puede estar vacío.");
            etCopPrin.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(etCopSecA.getText().toString())) {
            etCopSecA.setError("El campo no puede estar vacío.");
            etCopSecA.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(etCopSecB.getText().toString())) {
            etCopSecB.setError("El campo no puede estar vacío.");
            etCopSecB.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(etCopSecC.getText().toString())) {
            etCopSecC.setError("El campo no puede estar vacío.");
            etCopSecC.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(etCopSecD.getText().toString())) {
            etCopSecD.setError("El campo no puede estar vacío.");
            etCopSecD.requestFocus();
            return;
        }

        DatosProcesosSqlite datosProcesosSqlite = new DatosProcesosSqlite(actividad);
        Copa copa = new Copa();
        copa.setNombrePersonaje(personaje);
        copa.setPrincipal(etCopPrin.getText().toString());
        copa.setSecundarioA(etCopSecA.getText().toString());
        copa.setSecundarioB(etCopSecB.getText().toString());
        copa.setSecundarioC(etCopSecC.getText().toString());
        copa.setSecundarioD(etCopSecD.getText().toString());
        datosProcesosSqlite.validarUInsertUpdateCopa(actividad, personaje, copa);
    }

    private void guardarActualizarCorona(Context actividad, String personaje){
        if (TextUtils.isEmpty(etCorPrin.getText().toString())) {
            etCorPrin.setError("El campo no puede estar vacío.");
            etCorPrin.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(etCorSecA.getText().toString())) {
            etCorSecA.setError("El campo no puede estar vacío.");
            etCorSecA.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(etCorSecB.getText().toString())) {
            etCorSecB.setError("El campo no puede estar vacío.");
            etCorSecB.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(etCorSecC.getText().toString())) {
            etCorSecC.setError("El campo no puede estar vacío.");
            etCorSecC.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(etCorSecD.getText().toString())) {
            etCorSecD.setError("El campo no puede estar vacío.");
            etCorSecD.requestFocus();
            return;
        }

        DatosProcesosSqlite datosProcesosSqlite = new DatosProcesosSqlite(actividad);
        Corona corona = new Corona();
        corona.setNombrePersonaje(personaje);
        corona.setPrincipal(etCorPrin.getText().toString());
        corona.setSecundarioA(etCorSecA.getText().toString());
        corona.setSecundarioB(etCorSecB.getText().toString());
        corona.setSecundarioC(etCorSecC.getText().toString());
        corona.setSecundarioD(etCorSecD.getText().toString());
        datosProcesosSqlite.validarUInsertUpdateCorona(actividad, personaje, corona);
    }



    private void limpiarET(){
        etFlorPrin.setText("");
        etFlorSecA.setText("");
        etFlorSecB.setText("");
        etFlorSecC.setText("");
        etFlorSecD.setText("");
    }
}