package com.frabasoft.genshinimpactrecursos.Actividades;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.frabasoft.genshinimpactrecursos.Clases.Artefactos.Copa;
import com.frabasoft.genshinimpactrecursos.Clases.Artefactos.Corona;
import com.frabasoft.genshinimpactrecursos.Clases.Artefactos.Flor;
import com.frabasoft.genshinimpactrecursos.Clases.Artefactos.Pluma;
import com.frabasoft.genshinimpactrecursos.Clases.Artefactos.Reloj;
import com.frabasoft.genshinimpactrecursos.R;
import com.frabasoft.genshinimpactrecursos.SQLiteGenshin.Procesos.DatosProcesosSqlite;
import com.frabasoft.genshinimpactrecursos.TouchImage.TouchImageView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.master.permissionhelper.PermissionHelper;

import java.io.File;
import java.io.IOException;
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
            "Sucrose", "Tartaglia",
            "Venti", "Xianling",
            "Xiao", "Xingqiu",
            "Xinyan", "Zhongli"};

    private DecimalFormat df;
    PermissionHelper permissionHelper;
    private Button guardarFlor, guardarPluma, guardarReloj, guardarCopa, guardarCorona, guardarTodo, vistaPrevia;
    private EditText etFlorPrin, etFlorSecA, etFlorSecB, etFlorSecC, etFlorSecD;
    private EditText etPlumPrin, etPlumSecA, etPlumSecB, etPlumSecC, etPlumSecD;
    private EditText etRelPrin, etRelSecA, etRelSecB, etRelSecC, etRelSecD;
    private EditText etCopPrin, etCopSecA, etCopSecB, etCopSecC, etCopSecD;
    private EditText etCorPrin, etCorSecA, etCorSecB, etCorSecC, etCorSecD;
    private ArrayList<Flor> florArrayList;
    private ArrayList<Pluma> plumaArrayList;
    private ArrayList<Reloj> relojArrayList;
    private ArrayList<Copa> copaArrayList;
    private ArrayList<Corona> coronaArrayList;

    private AdView publicidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_builds);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ejecutar();

        MobileAds.initialize(MisBuilds.this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        publicidad = (AdView) findViewById(R.id.banerMisBuilds);
        AdRequest adRequest = new AdRequest.Builder().build();
        publicidad.loadAd(adRequest);

        etFlorPrin = (EditText) findViewById(R.id.etFlorPrin);
        etFlorSecA = (EditText) findViewById(R.id.etFlorSecA);
        etFlorSecB = (EditText) findViewById(R.id.etFlorSecB);
        etFlorSecC = (EditText) findViewById(R.id.etFlorSecC);
        etFlorSecD = (EditText) findViewById(R.id.etFlorSecD);

        etPlumPrin = (EditText) findViewById(R.id.etPlumPrin);
        etPlumSecA = (EditText) findViewById(R.id.etPlumSecA);
        etPlumSecB = (EditText) findViewById(R.id.etPlumSecB);
        etPlumSecC = (EditText) findViewById(R.id.etPlumSecC);
        etPlumSecD = (EditText) findViewById(R.id.etPlumSecD);

        etRelPrin = (EditText) findViewById(R.id.etRelPrin);
        etRelSecA = (EditText) findViewById(R.id.etRelSecA);
        etRelSecB = (EditText) findViewById(R.id.etRelSecB);
        etRelSecC = (EditText) findViewById(R.id.etRelSecC);
        etRelSecD = (EditText) findViewById(R.id.etRelSecD);

        etCopPrin = (EditText) findViewById(R.id.etCopPrin);
        etCopSecA = (EditText) findViewById(R.id.etCopSecA);
        etCopSecB = (EditText) findViewById(R.id.etCopSecB);
        etCopSecC = (EditText) findViewById(R.id.etCopSecC);
        etCopSecD = (EditText) findViewById(R.id.etCopSecD);

        etCorPrin = (EditText) findViewById(R.id.etCorPrin);
        etCorSecA = (EditText) findViewById(R.id.etCorSecA);
        etCorSecB = (EditText) findViewById(R.id.etCorSecB);
        etCorSecC = (EditText) findViewById(R.id.etCorSecC);
        etCorSecD = (EditText) findViewById(R.id.etCorSecD);

        spPJMisBuilds = (Spinner) findViewById(R.id.spPJMisBuilds);
        imgPJMisBuilds = (TouchImageView) findViewById(R.id.imgPJMisBuilds);
        guardarFlor = (Button) findViewById(R.id.guardarFlor);
        guardarPluma = (Button) findViewById(R.id.guardarPluma);
        guardarReloj = (Button) findViewById(R.id.guardarReloj);
        guardarCopa = (Button) findViewById(R.id.guardarCopa);
        guardarCorona = (Button) findViewById(R.id.guardarCorona);
        guardarTodo = (Button) findViewById(R.id.guardarTodo);
        vistaPrevia = (Button) findViewById(R.id.vistaprevia);

        df = new DecimalFormat("#.##");

        spPJMisBuilds.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, personajesString));
        spPJMisBuilds.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.wallpaper);
                    GuardadoEnCeroPosicion();
                    vistaPrevia.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(MisBuilds.this, "Debe seleccionar un personaje para ir a su vista previa.", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else if (position == 1) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.albedobuilds);
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 2) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.amberbuilds);
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 3) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.barbarabuilds);
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 4) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.beidoubuilds);
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 5) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.bennetbuilds);
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 6) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.chongyunbuilds);
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 7) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.dilucbuilds);
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 8) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.dionabuilds);
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 9) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.fischlbuilds);
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 10) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.ganyubuilds);
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 11) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.hutaobuilds);
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 12) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.jeanbuilds);
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 13) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.kaeyabuilds);
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 14) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.keqingbuilds);
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 15) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.kleebuilds);
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 16) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.lisabuilds);
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 17) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.monabuilds);
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 18) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.ningguangbuilds);
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 19) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.noellebuilds);
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 20) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.qiqibuilds);
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 21) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.razorbuilds);
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 22) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.rosariabuilds);
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 23) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.sacarosabuilds);
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 24) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.tartagliabuilds);
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 25) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.ventibuilds);
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 26) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.xianlingbuilds);
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 27) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.xiaobuilds);
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 28) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.xingqiubuilds);
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 29) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.xinyanbuilds);
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 30) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.zhonglibuilds);
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void ejecutar() {
        permissionHelper = new PermissionHelper(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        permissionHelper.request(new PermissionHelper.PermissionCallback() {
            @Override
            public void onPermissionGranted() {
                Log.d("TAG", "onPermissionGranted() called");
            }

            @Override
            public void onIndividualPermissionGranted(String[] grantedPermission) {
                Log.d("TAG", "onIndividualPermissionGranted() called with: grantedPermission = [" + TextUtils.join(",", grantedPermission) + "]");
            }

            @Override
            public void onPermissionDenied() {
                Log.d("TAG", "onPermissionDenied() called");
            }

            @Override
            public void onPermissionDeniedBySystem() {
                Log.d("TAG", "onPermissionDeniedBySystem() called");
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

    
    private void guardarActualizarFlor(Context actividad, String personaje) throws IOException {
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

        try {
            DatosProcesosSqlite datosProcesosSqlite = new DatosProcesosSqlite(actividad);
            Flor flor = new Flor();
            flor.setNombrePersonaje(personaje);
            flor.setPrincipal(etFlorPrin.getText().toString());
            flor.setSecundarioA(etFlorSecA.getText().toString());
            flor.setSecundarioB(etFlorSecB.getText().toString());
            flor.setSecundarioC(etFlorSecC.getText().toString());
            flor.setSecundarioD(etFlorSecD.getText().toString());
            datosProcesosSqlite.validarUInsertUpdateFlor(actividad, personaje, flor);
            Log.d("GuardarActualizarFlor", "try: " + flor.toString());
        }catch(Exception e){
            Log.d("GuardarActualizarFlor", "guardarActualizarFlor: " + e.getMessage());
        }
    }

    
    private void guardarActualizarPluma(Context actividad, String personaje) throws IOException {
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

    
    private void guardarActualizarReloj(Context actividad, String personaje) throws IOException {
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

    
    private void guardarActualizarCopa(Context actividad, String personaje) throws IOException {
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
    
    private void guardarActualizarCorona(Context actividad, String personaje) throws IOException {
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

    private void CargarDatosSQLite() {
        DatosProcesosSqlite datosProcesosSqlite = new DatosProcesosSqlite(getApplicationContext());
        //flor
        florArrayList = datosProcesosSqlite.mostrarDatosDelPjFlor(spPJMisBuilds.getSelectedItem().toString());
        for (int i = 0; i < florArrayList.size(); i++) {
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
        //pluma
        plumaArrayList = datosProcesosSqlite.mostrarDatosDelPjPluma(spPJMisBuilds.getSelectedItem().toString());
        for (int i = 0; i < plumaArrayList.size(); i++) {
            if (etPlumPrin.getText().toString() == "" || etPlumPrin.getText().toString() == null || etPlumPrin.equals("")
                    || etPlumSecA.getText().toString() == "" || etPlumSecA.getText().toString() == null || etPlumSecA.equals("")
                    || etPlumSecB.getText().toString() == "" || etPlumSecB.getText().toString() == null || etPlumSecB.equals("")
                    || etPlumSecC.getText().toString() == "" || etPlumSecC.getText().toString() == null || etPlumSecC.equals("")
                    || etPlumSecD.getText().toString() == "" || etPlumSecD.getText().toString() == null || etPlumSecD.equals("")) {
                limpiarET();
            } else {
                etPlumPrin.setText(plumaArrayList.get(i).getPrincipal());
                etPlumSecA.setText(plumaArrayList.get(i).getSecundarioA());
                etPlumSecB.setText(plumaArrayList.get(i).getSecundarioB());
                etPlumSecC.setText(plumaArrayList.get(i).getSecundarioC());
                etPlumSecD.setText(plumaArrayList.get(i).getSecundarioD());
            }
        }
        //reloj
        relojArrayList = datosProcesosSqlite.mostrarDatosDelPjReloj(spPJMisBuilds.getSelectedItem().toString());
        for (int i = 0; i < relojArrayList.size(); i++) {
            if (etRelPrin.getText().toString() == "" || etRelPrin.getText().toString() == null || etRelPrin.equals("")
                    || etRelSecA.getText().toString() == "" || etRelSecA.getText().toString() == null || etRelSecA.equals("")
                    || etRelSecB.getText().toString() == "" || etRelSecB.getText().toString() == null || etRelSecB.equals("")
                    || etRelSecC.getText().toString() == "" || etRelSecC.getText().toString() == null || etRelSecC.equals("")
                    || etRelSecD.getText().toString() == "" || etRelSecD.getText().toString() == null || etRelSecD.equals("")) {
                limpiarET();
            } else {
                etRelPrin.setText(relojArrayList.get(i).getPrincipal());
                etRelSecA.setText(relojArrayList.get(i).getSecundarioA());
                etRelSecB.setText(relojArrayList.get(i).getSecundarioB());
                etRelSecC.setText(relojArrayList.get(i).getSecundarioC());
                etRelSecD.setText(relojArrayList.get(i).getSecundarioD());
            }
        }
        //copa
        copaArrayList = datosProcesosSqlite.mostrarDatosDelPjCopa(spPJMisBuilds.getSelectedItem().toString());
        for (int i = 0; i < copaArrayList.size(); i++) {
            if (etCopPrin.getText().toString() == "" || etCopPrin.getText().toString() == null || etCopPrin.equals("")
                    || etCopSecA.getText().toString() == "" || etCopSecA.getText().toString() == null || etCopSecA.equals("")
                    || etCopSecB.getText().toString() == "" || etCopSecB.getText().toString() == null || etCopSecB.equals("")
                    || etCopSecC.getText().toString() == "" || etCopSecC.getText().toString() == null || etCopSecC.equals("")
                    || etCopSecD.getText().toString() == "" || etCopSecD.getText().toString() == null || etCopSecD.equals("")) {
                limpiarET();
            } else {
                etCopPrin.setText(copaArrayList.get(i).getPrincipal());
                etCopSecA.setText(copaArrayList.get(i).getSecundarioA());
                etCopSecB.setText(copaArrayList.get(i).getSecundarioB());
                etCopSecC.setText(copaArrayList.get(i).getSecundarioC());
                etCopSecD.setText(copaArrayList.get(i).getSecundarioD());
            }
        }
        //corona
        coronaArrayList = datosProcesosSqlite.mostrarDatosDelPjCorona(spPJMisBuilds.getSelectedItem().toString());
        for (int i = 0; i < coronaArrayList.size(); i++) {
            if (etCorPrin.getText().toString() == "" || etCorPrin.getText().toString() == null || etCorPrin.equals("")
                    || etCorSecA.getText().toString() == "" || etCorSecA.getText().toString() == null || etCorSecA.equals("")
                    || etCorSecB.getText().toString() == "" || etCorSecB.getText().toString() == null || etCorSecB.equals("")
                    || etCorSecC.getText().toString() == "" || etCorSecC.getText().toString() == null || etCorSecC.equals("")
                    || etCorSecD.getText().toString() == "" || etCorSecD.getText().toString() == null || etCorSecD.equals("")) {
                limpiarET();
            } else {
                etCorPrin.setText(coronaArrayList.get(i).getPrincipal());
                etCorSecA.setText(coronaArrayList.get(i).getSecundarioA());
                etCorSecB.setText(coronaArrayList.get(i).getSecundarioB());
                etCorSecC.setText(coronaArrayList.get(i).getSecundarioC());
                etCorSecD.setText(coronaArrayList.get(i).getSecundarioD());
            }
        }
    }
    
    private void GuardarIndividuales() {
        guardarFlor.setOnClickListener(new View.OnClickListener() {//flor
            @Override
            public void onClick(View v) {
                try {
                    guardarActualizarFlor(MisBuilds.this, spPJMisBuilds.getSelectedItem().toString());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        guardarPluma.setOnClickListener(new View.OnClickListener() {//pluma
            @Override
            public void onClick(View v) {
                try {
                    guardarActualizarPluma(MisBuilds.this, spPJMisBuilds.getSelectedItem().toString());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        guardarReloj.setOnClickListener(new View.OnClickListener() {//reloj
            @Override
            public void onClick(View v) {
                try {
                    guardarActualizarReloj(MisBuilds.this, spPJMisBuilds.getSelectedItem().toString());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        guardarCopa.setOnClickListener(new View.OnClickListener() {//copa
            @Override
            public void onClick(View v) {
                try {
                    guardarActualizarCopa(MisBuilds.this, spPJMisBuilds.getSelectedItem().toString());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        guardarCorona.setOnClickListener(new View.OnClickListener() {//corona
            @Override
            public void onClick(View v) {
                try {
                    guardarActualizarCorona(MisBuilds.this, spPJMisBuilds.getSelectedItem().toString());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }

    private void GuardadoEnCeroPosicion() {
        guardarFlor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MisBuilds.this, "Debe seleccionar un personaje primero.", Toast.LENGTH_SHORT).show();
            }
        });
        guardarPluma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MisBuilds.this, "Debe seleccionar un personaje primero.", Toast.LENGTH_SHORT).show();
            }
        });
        guardarReloj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MisBuilds.this, "Debe seleccionar un personaje primero.", Toast.LENGTH_SHORT).show();
            }
        });
        guardarCopa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MisBuilds.this, "Debe seleccionar un personaje primero.", Toast.LENGTH_SHORT).show();
            }
        });
        guardarCorona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MisBuilds.this, "Debe seleccionar un personaje primero.", Toast.LENGTH_SHORT).show();
            }
        });
        guardarTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MisBuilds.this, "Debe seleccionar un personaje primero.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void GuardarTodos() {
        guardarTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    guardarActualizarFlor(MisBuilds.this, spPJMisBuilds.getSelectedItem().toString());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                try {
                    guardarActualizarPluma(MisBuilds.this, spPJMisBuilds.getSelectedItem().toString());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                try {
                    guardarActualizarReloj(MisBuilds.this, spPJMisBuilds.getSelectedItem().toString());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                try {
                    guardarActualizarCopa(MisBuilds.this, spPJMisBuilds.getSelectedItem().toString());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                try {
                    guardarActualizarCorona(MisBuilds.this, spPJMisBuilds.getSelectedItem().toString());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }

    private void limpiarET() {
        //limpiar flor
        etFlorPrin.setText("");
        etFlorSecA.setText("");
        etFlorSecB.setText("");
        etFlorSecC.setText("");
        etFlorSecD.setText("");
        //limpiar pluma
        etPlumPrin.setText("");
        etPlumSecA.setText("");
        etPlumSecB.setText("");
        etPlumSecC.setText("");
        etPlumSecD.setText("");
        //limpiar reloj
        etRelPrin.setText("");
        etRelSecA.setText("");
        etRelSecB.setText("");
        etRelSecC.setText("");
        etRelSecD.setText("");
        //limpiar copa
        etCopPrin.setText("");
        etCopSecA.setText("");
        etCopSecB.setText("");
        etCopSecC.setText("");
        etCopSecD.setText("");
        //limpiar corona
        etCorPrin.setText("");
        etCorSecA.setText("");
        etCorSecB.setText("");
        etCorSecC.setText("");
        etCorSecD.setText("");
    }

    private void VistaPreviaBuild() {
        vistaPrevia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vista = new Intent(MisBuilds.this, VistaPrevia.class);
                vista.putExtra("pj", spPJMisBuilds.getSelectedItem().toString());
                startActivity(vista);
//                Toast.makeText(MisBuilds.this, "¡Próximamente vas a poder ver la vista previa de tus builds!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MisBuilds.this.finish();
    }

    /**
     * Called when leaving the activity
     */
    @Override
    public void onPause() {
        if (publicidad != null) {
            publicidad.pause();
        }
        super.onPause();
    }

    /**
     * Called when returning to the activity
     */
    @Override
    public void onResume() {
        super.onResume();
        if (publicidad != null) {
            publicidad.resume();
        }
    }

    /**
     * Called before the activity is destroyed
     */
    @Override
    public void onDestroy() {
        if (publicidad != null) {
            publicidad.destroy();
        }
        super.onDestroy();
    }
}