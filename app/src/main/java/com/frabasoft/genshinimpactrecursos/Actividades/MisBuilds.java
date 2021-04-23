package com.frabasoft.genshinimpactrecursos.Actividades;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.frabasoft.genshinimpactrecursos.Adaptadores.AdaptadorListViewAlertFlor;
import com.frabasoft.genshinimpactrecursos.Adaptadores.AdaptadorListViewPluma;
import com.frabasoft.genshinimpactrecursos.Adaptadores.AdaptadorListViewReloj;
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
import com.frabasoft.genshinimpactrecursos.R;
import com.frabasoft.genshinimpactrecursos.SQLiteGenshin.Procesos.DatosProcesosSqlite;
import com.frabasoft.genshinimpactrecursos.TouchImage.TouchImageView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.master.permissionhelper.PermissionHelper;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MisBuilds extends AppCompatActivity {
    private Spinner spPJMisBuilds, spinnerArmas;
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

    private String[] arcosStringArray = {
            "Selecciona un arco",
            "Alas Celestiales", "Arco Compuesto", "Arco Real", "Arco Recurvo", "Arco de Amos",
            "Arco de Cuervo", "Arco de Favonius", "Arco de la Cazadora Esmeralda", "Arco del Cazador", "Arco del Cazador Estacional",
            "Arco del Peñasco Oscuro", "Arco del Sacrificio", "Cazador del Callejón", "Ebony Bow", "Elegia del Fin",
            "Herrumbre", "Juramento del Arquero", "Mensajero", "Oda a las Flores de Viento", "Prototipo Luz de Luna",
            "Tirachinas", "Ultimo Acorde"
    };

    private String[] catalizadorStringArray = {
            "Selecciona un catalizador",
            "Amber Catalyst", "Apuntes del Aprendiz", "Candado Terrenal", "Carta Náutica", "Cuentos de Cazadores de Dragones",
            "Códice de Favonius", "Frío Eterno", "Grimorio Real", "Grimorio de Bolsillo", "Guía Mágica",
            "Historias de Otros Mundos", "Memorias de Sacrificios", "Nefrita Gemela", "Ojo de la Perspicacia", "Oración Perdida a los Vientos",
            "Orbe Esmeralda", "Pergamino Celestial", "Perla Solar", "Prototipo Ámbar", "Sinfonía de los Merodeadores",
            "Vino y Poesía", "Ágata del Peñasco Oscuro"
    };

    private String[] espadaStringArray = {
            "Selecciona una espada",
            "Aquila Favonia", "Cortador de Jade Primordial", "Deseo Ponzoñoso", "Destello en la Oscuridad", "Espada Larga del Peñasco Oscuro", "Espada Negra",
            "Espada Plateada", "Espada Real Larga", "Espada Surcacielos", "Espada de Favonius", "Espada de Hierro Oscuro", "Espada de Sacrificio",
            "Espada del Alba", "Espada del Descenso", "Espada del Viajero", "Espina de Hierro", "Flauta", "Hoja Afilada Celestial",
            "Hoja Desafilada", "Hoja Fría", "Hoja de Filetear", "Prototipo Rencor", "Rompemontañas", "Rugido del León"
    };

    private String[] lanzaStringArray = {
            "Selecciona una lanza",
            "Alabarda", "Borla Blanca", "Borla Negra", "Báculo de Homa", "Halcón de Jade", "Lanza Lítica",
            "Lanza Perforanubes", "Lanza de Caza Real", "Lanza de Espinadragón", "Lanza de Favonius", "Lanza del Duelo", "Lanza del Peñasco Oscuro",
            "Lanza del Principiante", "Perdición del Dragón", "Pica Luna Creciente", "Prototipo Estelar", "Punta de Hierro", "Púa Celestial"
    };

    private String[] mandobleStringArray = {
            "Selecciona un mandoble",
            "Argento Estelar de las Nieves", "Espada Lítica", "Espada de Hierro Blanco", "Espada de la Desidia", "Espada del Mercenario", "Espada del Tiempo",
            "Garrote del Debate", "Gran Espada Real", "Gran Espada Sangrienta", "Gran Espada Surcacielos", "Gran Espada de Favonius", "Gran Espada de Sacrificio",
            "Gran Espada del Guerrero", "Gran Hoja del Peñasco Oscuro", "Lápida del Lobo", "Médula de la Serpiente Marina", "Orgullo Celestial", "Prototipo Arcaico",
            "Quartz", "Segadora de la Lluvia", "Sombra Blanca", "Sombra férrea"
    };

    private ArrayList<Armas> armasArrayList;
    private ArrayList<FlorArtefacto> florArtefactoArrayList = new ArrayList<FlorArtefacto>();
    private ArrayList<PlumaArtefacto> plumaArtefactoArrayList;
    private ArrayList<RelojArtefacto> relojArtefactoArrayList;
    private ArrayList<CopaArtefacto> copaArtefactoArrayList;
    private ArrayList<CoronaArtefacto> coronaArtefactoArrayList;

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
    private ImageView ivArmas, ivFlorArtefacto, ivPluma, ivRelojArtefacto;
    private ScrollView scrollView;
    String nombrePJ = "";

    AdView publicidad;

    //para los soniditos
    private MediaPlayer entrar, salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_builds);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ejecutar();

        entrar = MediaPlayer.create(getApplicationContext(), R.raw.entrar);
        salir = MediaPlayer.create(getApplicationContext(), R.raw.salir);

        MobileAds.initialize(MisBuilds.this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        publicidad = findViewById(R.id.banerMisBuilds);
        AdRequest adRequest = new AdRequest.Builder().build();
        publicidad.loadAd(adRequest);

        etFlorPrin = findViewById(R.id.etFlorPrin);
        etFlorSecA = findViewById(R.id.etFlorSecA);
        etFlorSecB = findViewById(R.id.etFlorSecB);
        etFlorSecC = findViewById(R.id.etFlorSecC);
        etFlorSecD = findViewById(R.id.etFlorSecD);

        etPlumPrin = findViewById(R.id.etPlumPrin);
        etPlumSecA = findViewById(R.id.etPlumSecA);
        etPlumSecB = findViewById(R.id.etPlumSecB);
        etPlumSecC = findViewById(R.id.etPlumSecC);
        etPlumSecD = findViewById(R.id.etPlumSecD);

        etRelPrin = findViewById(R.id.etRelPrin);
        etRelSecA = findViewById(R.id.etRelSecA);
        etRelSecB = findViewById(R.id.etRelSecB);
        etRelSecC = findViewById(R.id.etRelSecC);
        etRelSecD = findViewById(R.id.etRelSecD);

        etCopPrin = findViewById(R.id.etCopPrin);
        etCopSecA = findViewById(R.id.etCopSecA);
        etCopSecB = findViewById(R.id.etCopSecB);
        etCopSecC = findViewById(R.id.etCopSecC);
        etCopSecD = findViewById(R.id.etCopSecD);

        etCorPrin = findViewById(R.id.etCorPrin);
        etCorSecA = findViewById(R.id.etCorSecA);
        etCorSecB = findViewById(R.id.etCorSecB);
        etCorSecC = findViewById(R.id.etCorSecC);
        etCorSecD = findViewById(R.id.etCorSecD);

        spPJMisBuilds = findViewById(R.id.spPJMisBuilds);
        spinnerArmas = findViewById(R.id.spinnerArmas);
        imgPJMisBuilds = findViewById(R.id.imgPJMisBuilds);
        guardarFlor = findViewById(R.id.guardarFlor);
        guardarPluma = findViewById(R.id.guardarPluma);
        guardarReloj = findViewById(R.id.guardarReloj);
        guardarCopa = findViewById(R.id.guardarCopa);
        guardarCorona = findViewById(R.id.guardarCorona);
        guardarTodo = findViewById(R.id.guardarTodo);
        vistaPrevia = findViewById(R.id.vistaprevia);
        ivArmas = findViewById(R.id.ivArmas);
        scrollView = findViewById(R.id.scroll);
        ivFlorArtefacto = findViewById(R.id.ivFlorArtefacto);
        ivPluma = findViewById(R.id.ivPluma);
        ivRelojArtefacto = findViewById(R.id.ivRelojArtefacto);

        ivFlorArtefacto.setImageResource(R.drawable.flor_afortunado);
        ivPluma.setImageResource(R.drawable.pluma_afortunado);
        ivRelojArtefacto.setImageResource(R.drawable.reloj_afortunado);

        df = new DecimalFormat("#.##");

        spPJMisBuilds.setAdapter(new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_color_text, personajesString));
        spPJMisBuilds.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.wallpaper);
                    GuardadoEnCeroPosicion();
                    scrollView.setEnabled(false);
                    scrollView.setVisibility(View.GONE);
                    vistaPrevia.setOnClickListener(v -> Toast.makeText(MisBuilds.this, "Debe seleccionar un personaje para ir a su vista previa.", Toast.LENGTH_SHORT).show());
                } else if (position == 1) {
                    limpiarET();
                    nombrePJ = spPJMisBuilds.getSelectedItem().toString();
                    imgPJMisBuilds.setImageResource(R.drawable.albedobuilds);
                    scrollView.setEnabled(true);
                    scrollView.setVisibility(View.VISIBLE);
                    cargarEspadas();
                    traerArtefactosGuardadosFlor();
                    traerArtefactosGuardadosPluma();
                    traerArtefactosGuardadosReloj();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 2) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.amberbuilds);
                    cargarArcos();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 3) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.barbarabuilds);
                    cargarCatalizadores();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 4) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.beidoubuilds);
                    cargarMandobles();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 5) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.bennetbuilds);
                    cargarEspadas();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 6) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.chongyunbuilds);
                    cargarMandobles();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 7) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.dilucbuilds);
                    cargarMandobles();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 8) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.dionabuilds);
                    cargarArcos();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 9) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.fischlbuilds);
                    cargarArcos();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 10) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.ganyubuilds);
                    cargarArcos();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 11) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.hutaobuilds);
                    cargarLanzas();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 12) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.jeanbuilds);
                    cargarEspadas();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 13) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.kaeyabuilds);
                    cargarEspadas();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 14) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.keqingbuilds);
                    cargarEspadas();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 15) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.kleebuilds);
                    cargarCatalizadores();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 16) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.lisabuilds);
                    cargarCatalizadores();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 17) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.monabuilds);
                    cargarCatalizadores();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 18) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.ningguangbuilds);
                    cargarCatalizadores();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 19) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.noellebuilds);
                    cargarMandobles();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 20) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.qiqibuilds);
                    cargarEspadas();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 21) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.razorbuilds);
                    cargarMandobles();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 22) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.rosariabuilds);
                    cargarLanzas();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 23) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.sacarosabuilds);
                    cargarCatalizadores();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 24) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.tartagliabuilds);
                    cargarArcos();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 25) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.ventibuilds);
                    cargarArcos();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 26) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.xianlingbuilds);
                    cargarLanzas();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 27) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.xiaobuilds);
                    cargarLanzas();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 28) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.xingqiubuilds);
                    cargarEspadas();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 29) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.xinyanbuilds);
                    cargarMandobles();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 30) {
                    limpiarET();
                    imgPJMisBuilds.setImageResource(R.drawable.zhonglibuilds);
                    cargarLanzas();
                    traerArmaGuardada();
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

        ivFlorArtefacto.setOnClickListener(v ->alertArtefactosFlor());
        ivPluma.setOnClickListener(v -> alertArtefactosPluma());
        ivRelojArtefacto.setOnClickListener(v -> alertArtefactosReloj());
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

    private void traerArmaGuardada(){
        DatosProcesosSqlite datosProcesosSqlite = new DatosProcesosSqlite(this);
        armasArrayList = datosProcesosSqlite.mostrarDatosDelPjArma(spPJMisBuilds.getSelectedItem().toString());
        if(armasArrayList.size() <= 0){
            //nada
        }else{
            for(int i = 0; i < armasArrayList.size(); i++){
                spinnerArmas.setSelection(armasArrayList.get(i).getId());
                ivArmas.setImageResource(armasArrayList.get(i).getRecursoImagen());
                Log.d("ARRAYIV", "onItemSelected: "+ armasArrayList.get(i).getNombreArma());
            }
        }
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
                entrar.start();
                Intent vista = new Intent(MisBuilds.this, VistaPrevia.class);
                vista.putExtra("pj", spPJMisBuilds.getSelectedItem().toString());
                startActivity(vista);
//                Toast.makeText(MisBuilds.this, "¡Próximamente vas a poder ver la vista previa de tus builds!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void cargarArcos(){
        spinnerArmas.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_color_text, arcosStringArray));
        spinnerArmas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    ivArmas.setImageResource(R.drawable.wallpaper);
                }else if(position == 1){
                    ivArmas.setImageResource(R.drawable.alas_celestiales);
                    guardarArma(R.drawable.alas_celestiales);
                }else if(position == 2){
                    ivArmas.setImageResource(R.drawable.arco_compuesto);
                    guardarArma(R.drawable.arco_compuesto);
                }else if(position == 3){
                    ivArmas.setImageResource(R.drawable.arco_real);
                    guardarArma(R.drawable.arco_real);
                }else if(position == 4){
                    ivArmas.setImageResource(R.drawable.arco_recurvo);
                    guardarArma(R.drawable.arco_recurvo);
                }else if(position == 5){
                    ivArmas.setImageResource(R.drawable.arco_de_amos);
                    guardarArma(R.drawable.arco_de_amos);
                }else if(position == 6){
                    ivArmas.setImageResource(R.drawable.arco_de_cuervo);
                    guardarArma(R.drawable.arco_de_cuervo);
                }else if(position == 7){
                    ivArmas.setImageResource(R.drawable.arco_de_favonius);
                    guardarArma(R.drawable.arco_de_favonius);
                }else if(position == 8){
                    ivArmas.setImageResource(R.drawable.arco_de_la_cazadora_esmeralda);
                    guardarArma(R.drawable.arco_de_la_cazadora_esmeralda);
                }else if(position == 9){
                    ivArmas.setImageResource(R.drawable.arco_del_cazador);
                    guardarArma(R.drawable.arco_del_cazador);
                }else if(position == 10){
                    ivArmas.setImageResource(R.drawable.arco_del_cazador_estacional);
                    guardarArma(R.drawable.arco_del_cazador_estacional);
                }else if(position == 11){
                    ivArmas.setImageResource(R.drawable.arco_del_penasco_oscuro);
                    guardarArma(R.drawable.arco_del_penasco_oscuro);
                }else if(position == 12){
                    ivArmas.setImageResource(R.drawable.arco_del_sacrificio);
                    guardarArma(R.drawable.arco_del_sacrificio);
                }else if(position == 13){
                    ivArmas.setImageResource(R.drawable.cazador_del_callejon);
                    guardarArma(R.drawable.cazador_del_callejon);
                }else if(position == 14){
                    ivArmas.setImageResource(R.drawable.ebony_bow);
                    guardarArma(R.drawable.ebony_bow);
                }else if(position == 15){
                    ivArmas.setImageResource(R.drawable.elegia_del_fin);
                    guardarArma(R.drawable.elegia_del_fin);
                }else if(position == 16){
                    ivArmas.setImageResource(R.drawable.herrumbre);
                    guardarArma(R.drawable.herrumbre);
                }else if(position == 17){
                    ivArmas.setImageResource(R.drawable.juramento_del_arquero);
                    guardarArma(R.drawable.juramento_del_arquero);
                }else if(position == 18){
                    ivArmas.setImageResource(R.drawable.mensajero);
                    guardarArma(R.drawable.mensajero);
                }else if(position == 19){
                    ivArmas.setImageResource(R.drawable.oda_a_las_flores_de_viento);
                    guardarArma(R.drawable.oda_a_las_flores_de_viento);
                }else if(position == 20){
                    ivArmas.setImageResource(R.drawable.prototipo_luz_de_luna);
                    guardarArma(R.drawable.prototipo_luz_de_luna);
                }else if(position == 21){
                    ivArmas.setImageResource(R.drawable.tirachinas);
                    guardarArma(R.drawable.tirachinas);
                }else if(position == 22){
                    ivArmas.setImageResource(R.drawable.ultimo_acorde);
                    guardarArma(R.drawable.ultimo_acorde);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

    private void cargarCatalizadores(){
        spinnerArmas.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_color_text, catalizadorStringArray));
        spinnerArmas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    ivArmas.setImageResource(R.drawable.wallpaper);
                }else if(position == 1){
                    ivArmas.setImageResource(R.drawable.amber_catalyst);
                    guardarArma(R.drawable.amber_catalyst);
                }else if(position == 2){
                    ivArmas.setImageResource(R.drawable.apuntes_del_aprendiz);
                    guardarArma(R.drawable.apuntes_del_aprendiz);
                }else if(position == 3){
                    ivArmas.setImageResource(R.drawable.candado_terrenal);
                    guardarArma(R.drawable.candado_terrenal);
                }else if(position == 4){
                    ivArmas.setImageResource(R.drawable.carta_nautica);
                    guardarArma(R.drawable.carta_nautica);
                }else if(position == 5){
                    ivArmas.setImageResource(R.drawable.cuentos_de_cazadores_de_dragones);
                    guardarArma(R.drawable.cuentos_de_cazadores_de_dragones);
                }else if(position == 6){
                    ivArmas.setImageResource(R.drawable.codice_de_favonius);
                    guardarArma(R.drawable.codice_de_favonius);
                }else if(position == 7){
                    ivArmas.setImageResource(R.drawable.frio_eterno);
                    guardarArma(R.drawable.frio_eterno);
                }else if(position == 8){
                    ivArmas.setImageResource(R.drawable.grimorio_real);
                    guardarArma(R.drawable.grimorio_real);
                }else if(position == 9){
                    ivArmas.setImageResource(R.drawable.grimorio_de_bolsillo);
                    guardarArma(R.drawable.grimorio_de_bolsillo);
                }else if(position == 10){
                    ivArmas.setImageResource(R.drawable.guia_magica);
                    guardarArma(R.drawable.guia_magica);
                }else if(position == 11){
                    ivArmas.setImageResource(R.drawable.historias_de_otros_mundos);
                    guardarArma(R.drawable.historias_de_otros_mundos);
                }else if(position == 12){
                    ivArmas.setImageResource(R.drawable.memorias_de_sacrificios);
                    guardarArma(R.drawable.memorias_de_sacrificios);
                }else if(position == 13){
                    ivArmas.setImageResource(R.drawable.nefrita_gemela);
                    guardarArma(R.drawable.nefrita_gemela);
                }else if(position == 14){
                    ivArmas.setImageResource(R.drawable.ojo_de_la_perspicacia);
                    guardarArma(R.drawable.ojo_de_la_perspicacia);
                }else if(position == 15){
                    ivArmas.setImageResource(R.drawable.oracion_perdida_a_los_vientos_sagrados);
                    guardarArma(R.drawable.oracion_perdida_a_los_vientos_sagrados);
                }else if(position == 16){
                    ivArmas.setImageResource(R.drawable.orbe_esmeralda);
                    guardarArma(R.drawable.orbe_esmeralda);
                }else if(position == 17){
                    ivArmas.setImageResource(R.drawable.pergamino_celestial);
                    guardarArma(R.drawable.pergamino_celestial);
                }else if(position == 18){
                    ivArmas.setImageResource(R.drawable.perla_solar);
                    guardarArma(R.drawable.perla_solar);
                }else if(position == 19){
                    ivArmas.setImageResource(R.drawable.prototipo_ambar);
                    guardarArma(R.drawable.prototipo_ambar);
                }else if(position == 20){
                    ivArmas.setImageResource(R.drawable.sinfonia_de_los_merodeadores);
                    guardarArma(R.drawable.sinfonia_de_los_merodeadores);
                }else if(position == 21){
                    ivArmas.setImageResource(R.drawable.vino_y_poesia);
                    guardarArma(R.drawable.vino_y_poesia);
                }else if(position == 22){
                    ivArmas.setImageResource(R.drawable.agata_del_penasco_oscuro);
                    guardarArma(R.drawable.agata_del_penasco_oscuro);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

    private void cargarEspadas(){
        spinnerArmas.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_color_text, espadaStringArray));
        spinnerArmas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    ivArmas.setImageResource(R.drawable.wallpaper);
                }else if(position == 1){
                    ivArmas.setImageResource(R.drawable.aquila_favonia);
                    guardarArma(R.drawable.aquila_favonia);
                }else if(position == 2){
                    ivArmas.setImageResource(R.drawable.cortador_de_jade_primordial);
                    guardarArma(R.drawable.cortador_de_jade_primordial);
                }else if(position == 3){
                    ivArmas.setImageResource(R.drawable.deseo_ponzonoso);
                    guardarArma(R.drawable.deseo_ponzonoso);
                }else if(position == 4){
                    ivArmas.setImageResource(R.drawable.destello_en_la_oscuridad);
                    guardarArma(R.drawable.destello_en_la_oscuridad);
                }else if(position == 5){
                    ivArmas.setImageResource(R.drawable.espada_larga_del_penasco_oscuro);
                    guardarArma(R.drawable.espada_larga_del_penasco_oscuro);
                }else if(position == 6){
                    ivArmas.setImageResource(R.drawable.espada_negra);
                    guardarArma(R.drawable.espada_negra);
                }else if(position == 7){
                    ivArmas.setImageResource(R.drawable.espada_plateada);
                    guardarArma(R.drawable.espada_plateada);
                }else if(position == 8){
                    ivArmas.setImageResource(R.drawable.espada_real_larga);
                    guardarArma(R.drawable.espada_real_larga);
                }else if(position == 9){
                    ivArmas.setImageResource(R.drawable.espada_surcacielos);
                    guardarArma(R.drawable.espada_surcacielos);
                }else if(position == 10){
                    ivArmas.setImageResource(R.drawable.espada_de_favonius);
                    guardarArma(R.drawable.espada_de_favonius);
                }else if(position == 11){
                    ivArmas.setImageResource(R.drawable.espada_de_hierro_oscuro);
                    guardarArma(R.drawable.espada_de_hierro_oscuro);
                }else if(position == 12){
                    ivArmas.setImageResource(R.drawable.espada_de_sacrificio);
                    guardarArma(R.drawable.espada_de_sacrificio);
                }else if(position == 13){
                    ivArmas.setImageResource(R.drawable.espada_del_alba);
                    guardarArma(R.drawable.espada_del_alba);
                }else if(position == 14){
                    ivArmas.setImageResource(R.drawable.espada_del_descenso);
                    guardarArma(R.drawable.espada_del_descenso);
                }else if(position == 15){
                    ivArmas.setImageResource(R.drawable.espada_del_viajero);
                    guardarArma(R.drawable.espada_del_viajero);
                }else if(position == 16){
                    ivArmas.setImageResource(R.drawable.espina_de_hierro);
                    guardarArma(R.drawable.espina_de_hierro);
                }else if(position == 17){
                    ivArmas.setImageResource(R.drawable.flauta);
                    guardarArma(R.drawable.flauta);
                }else if(position == 18){
                    ivArmas.setImageResource(R.drawable.hoja_afilada_celestial);
                    guardarArma(R.drawable.hoja_afilada_celestial);
                }else if(position == 19){
                    ivArmas.setImageResource(R.drawable.hoja_desafilada);
                    guardarArma(R.drawable.hoja_desafilada);
                }else if(position == 20){
                    ivArmas.setImageResource(R.drawable.hoja_fria);
                    guardarArma(R.drawable.hoja_fria);
                }else if(position == 21){
                    ivArmas.setImageResource(R.drawable.hoja_de_filetear);
                    guardarArma(R.drawable.hoja_de_filetear);
                }else if(position == 22){
                    ivArmas.setImageResource(R.drawable.prototipo_rencor);
                    guardarArma(R.drawable.prototipo_rencor);
                }else if(position == 23){
                    ivArmas.setImageResource(R.drawable.rompemontanas);
                    guardarArma(R.drawable.rompemontanas);
                }else if(position == 24){
                    ivArmas.setImageResource(R.drawable.rugido_del_leon);
                    guardarArma(R.drawable.rugido_del_leon);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

    private void cargarLanzas(){
        spinnerArmas.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_color_text, lanzaStringArray));
        spinnerArmas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    ivArmas.setImageResource(R.drawable.wallpaper);
                }else if(position == 1){
                    ivArmas.setImageResource(R.drawable.alabarda);
                    guardarArma(R.drawable.alabarda);
                }else if(position == 2){
                    ivArmas.setImageResource(R.drawable.borla_blanca);
                    guardarArma(R.drawable.borla_blanca);
                }else if(position == 3){
                    ivArmas.setImageResource(R.drawable.borla_begra);
                    guardarArma(R.drawable.borla_begra);
                }else if(position == 4){
                    ivArmas.setImageResource(R.drawable.baculo_de_homa);
                    guardarArma(R.drawable.baculo_de_homa);
                }else if(position == 5){
                    ivArmas.setImageResource(R.drawable.halcon_de_jade);
                    guardarArma(R.drawable.halcon_de_jade);
                }else if(position == 6){
                    ivArmas.setImageResource(R.drawable.lanza_litica);
                    guardarArma(R.drawable.lanza_litica);
                }else if(position == 7){
                    ivArmas.setImageResource(R.drawable.lanza_perforanubes);
                    guardarArma(R.drawable.lanza_perforanubes);
                }else if(position == 8){
                    ivArmas.setImageResource(R.drawable.lanza_de_caza_real);
                    guardarArma(R.drawable.lanza_de_caza_real);
                }else if(position == 9){
                    ivArmas.setImageResource(R.drawable.lanza_de_espinadragon);
                    guardarArma(R.drawable.lanza_de_espinadragon);
                }else if(position == 10){
                    ivArmas.setImageResource(R.drawable.lanza_de_favonius);
                    guardarArma(R.drawable.lanza_de_favonius);
                }else if(position == 11){
                    ivArmas.setImageResource(R.drawable.lanza_del_fuelo);
                    guardarArma(R.drawable.lanza_del_fuelo);
                }else if(position == 12){
                    ivArmas.setImageResource(R.drawable.lanza_del_penasco_oscuro);
                    guardarArma(R.drawable.lanza_del_penasco_oscuro);
                }else if(position == 13){
                    ivArmas.setImageResource(R.drawable.lanza_del_principiante);
                    guardarArma(R.drawable.lanza_del_principiante);
                }else if(position == 14){
                    ivArmas.setImageResource(R.drawable.perdicion_del_dragon);
                    guardarArma(R.drawable.perdicion_del_dragon);
                }else if(position == 15){
                    ivArmas.setImageResource(R.drawable.pica_luna_creciente);
                    guardarArma(R.drawable.pica_luna_creciente);
                }else if(position == 16){
                    ivArmas.setImageResource(R.drawable.prototipo_estelar);
                    guardarArma(R.drawable.prototipo_estelar);
                }else if(position == 17){
                    ivArmas.setImageResource(R.drawable.punta_de_hierro);
                    guardarArma(R.drawable.punta_de_hierro);
                }else if(position == 18){
                    ivArmas.setImageResource(R.drawable.pua_celestial);
                    guardarArma(R.drawable.pua_celestial);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

    private void cargarMandobles(){
        spinnerArmas.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_color_text, mandobleStringArray));
        spinnerArmas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    ivArmas.setImageResource(R.drawable.wallpaper);
                }else if(position == 1){
                    ivArmas.setImageResource(R.drawable.argento_estelar_de_las_nieves);
                    guardarArma(R.drawable.argento_estelar_de_las_nieves);
                }else if(position == 2){
                    ivArmas.setImageResource(R.drawable.espada_litica);
                    guardarArma(R.drawable.espada_litica);
                }else if(position == 3){
                    ivArmas.setImageResource(R.drawable.espada_de_hierro_blanco);
                    guardarArma(R.drawable.espada_de_hierro_blanco);
                }else if(position == 4){
                    ivArmas.setImageResource(R.drawable.espada_de_la_desidia);
                    guardarArma(R.drawable.espada_de_la_desidia);
                }else if(position == 5){
                    ivArmas.setImageResource(R.drawable.espada_del_mercenario);
                    guardarArma(R.drawable.espada_del_mercenario);
                }else if(position == 6){
                    ivArmas.setImageResource(R.drawable.espada_del_tiempo);
                    guardarArma(R.drawable.espada_del_tiempo);
                }else if(position == 7){
                    ivArmas.setImageResource(R.drawable.garrote_del_debate);
                    guardarArma(R.drawable.garrote_del_debate);
                }else if(position == 8){
                    ivArmas.setImageResource(R.drawable.fran_espada_real);
                    guardarArma(R.drawable.fran_espada_real);
                }else if(position == 9){
                    ivArmas.setImageResource(R.drawable.gran_espada_sangrienta);
                    guardarArma(R.drawable.gran_espada_sangrienta);
                }else if(position == 10){
                    ivArmas.setImageResource(R.drawable.gran_espada_surcacielos);
                    guardarArma(R.drawable.gran_espada_surcacielos);
                }else if(position == 11){
                    ivArmas.setImageResource(R.drawable.gran_espada_defavonius);
                    guardarArma(R.drawable.gran_espada_defavonius);
                }else if(position == 12){
                    ivArmas.setImageResource(R.drawable.gran_espada_de_sacrificio);
                    guardarArma(R.drawable.gran_espada_de_sacrificio);
                }else if(position == 13){
                    ivArmas.setImageResource(R.drawable.gran_espada_del_guerrero);
                    guardarArma(R.drawable.gran_espada_del_guerrero);
                }else if(position == 14){
                    ivArmas.setImageResource(R.drawable.gran_hoja_del_penasco_oscuro);
                    guardarArma(R.drawable.gran_hoja_del_penasco_oscuro);
                }else if(position == 15){
                    ivArmas.setImageResource(R.drawable.lapida_del_lobo);
                    guardarArma(R.drawable.lapida_del_lobo);
                }else if(position == 16){
                    ivArmas.setImageResource(R.drawable.medula_de_laserpiente_marina);
                    guardarArma(R.drawable.medula_de_laserpiente_marina);
                }else if(position == 17){
                    ivArmas.setImageResource(R.drawable.orgullo_celestial);
                    guardarArma(R.drawable.orgullo_celestial);
                }else if(position == 18){
                    ivArmas.setImageResource(R.drawable.prototipo_arcaico);
                    guardarArma(R.drawable.prototipo_arcaico);
                }else if(position == 19){
                    ivArmas.setImageResource(R.drawable.quartz);
                    guardarArma(R.drawable.quartz);
                }else if(position == 20){
                    ivArmas.setImageResource(R.drawable.segadora_de_la_lluvia);
                    guardarArma(R.drawable.segadora_de_la_lluvia);
                }else if(position == 21){
                    ivArmas.setImageResource(R.drawable.sombra_blanca);
                    guardarArma(R.drawable.sombra_blanca);
                }else if(position == 22){
                    ivArmas.setImageResource(R.drawable.sombra_ferrea);
                    guardarArma(R.drawable.sombra_ferrea);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MisBuilds.this.finish();
        salir.start();
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

    private void guardarArma(int res){
        DatosProcesosSqlite datosProcesosSqlite = new DatosProcesosSqlite(this);
        Armas armas = new Armas();
        armas.setId(spinnerArmas.getSelectedItemPosition());
        armas.setPersonaje(spPJMisBuilds.getSelectedItem().toString());
        armas.setNombreArma(spinnerArmas.getSelectedItem().toString());
        armas.setRecursoImagen(res);
        datosProcesosSqlite.validarUInsertUpdateArma(this, spPJMisBuilds.getSelectedItem().toString(), armas);
    }

    private void alertArtefactosFlor(){
        florArtefactoArrayList = new ArrayList<>();
        florArtefactoArrayList.add(new FlorArtefacto(0, "Flor del Afortunado"));
        florArtefactoArrayList.add(new FlorArtefacto(1, "Flor del Aventurero"));
        florArtefactoArrayList.add(new FlorArtefacto(2, "Flor Curativa"));

        DatosProcesosSqlite datosProcesosSqlite = new DatosProcesosSqlite(this);
        FlorArtefacto florArtefacto = new FlorArtefacto();
        try{
            LayoutInflater layoutInflater = LayoutInflater.from(MisBuilds.this);
            AlertDialog anuncio = new AlertDialog.Builder(this).create();
            final View view = layoutInflater.inflate(R.layout.alert_artefactos_listview, null);
            final ListView lvAlertArtefactos = view.findViewById(R.id.lvAlertArtefactos);
            anuncio.setView(view);
            anuncio.show();
            lvAlertArtefactos.setAdapter(new AdaptadorListViewAlertFlor(MisBuilds.this, florArtefactoArrayList));
            lvAlertArtefactos.setOnItemClickListener((parent, view1, position, id) -> {
                if(position == 0){
                    florArtefacto.setNombrePJ(nombrePJ);
                    florArtefacto.setSeleccionDatoSpiner(position);
                    florArtefacto.setNombreArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto());
                    florArtefacto.setRecursoArtefacto(R.drawable.flor_afortunado);
                    datosProcesosSqlite.validarUInsertUpdateFlorArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto(), florArtefacto);
                    traerArtefactosGuardadosFlor();
                    anuncio.dismiss();
                }else if(position == 1){
                    florArtefacto.setNombrePJ(nombrePJ);
                    florArtefacto.setSeleccionDatoSpiner(position);
                    florArtefacto.setNombreArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto());
                    florArtefacto.setRecursoArtefacto(R.drawable.flor_aventurero);
                    datosProcesosSqlite.validarUInsertUpdateFlorArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto(), florArtefacto);
                    traerArtefactosGuardadosFlor();
                    anuncio.dismiss();
                }else if(position == 2){
                    florArtefacto.setNombrePJ(nombrePJ);
                    florArtefacto.setSeleccionDatoSpiner(position);
                    florArtefacto.setNombreArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto());
                    florArtefacto.setRecursoArtefacto(R.drawable.flor_curativa);
                    datosProcesosSqlite.validarUInsertUpdateFlorArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto(), florArtefacto);
                    traerArtefactosGuardadosFlor();
                    anuncio.dismiss();
                }
            });
        }catch (Exception exception){
            Log.d("AlertArtefactosFlor", "ERROR ALERT GUARDAR: " + exception.getMessage());
        }
    }

    private void traerArtefactosGuardadosFlor(){
        DatosProcesosSqlite datosProcesosSqlite = new DatosProcesosSqlite(this);
        florArtefactoArrayList = datosProcesosSqlite.mostrarSeleccionFlorArtefacto(nombrePJ);
        if(florArtefactoArrayList.size() <= 0){
            //nada
        }else{
            for(int i = 0; i < florArtefactoArrayList.size(); i++){
                ivFlorArtefacto.setImageResource(florArtefactoArrayList.get(i).getRecursoArtefacto());
                Log.d("traerArtefactosFlor", "traerArtefactosGuardados: "+ florArtefactoArrayList.get(i).getNombreArtefacto());
            }
        }
    }

    private void alertArtefactosPluma(){
        plumaArtefactoArrayList = new ArrayList<>();
        plumaArtefactoArrayList.add(new PlumaArtefacto(0, "Pluma del Afortunado"));
        plumaArtefactoArrayList.add(new PlumaArtefacto(1, "Pluma del Aventurero"));
        plumaArtefactoArrayList.add(new PlumaArtefacto(2, "Pluma Curativa"));

        DatosProcesosSqlite datosProcesosSqlite = new DatosProcesosSqlite(this);
        PlumaArtefacto plumaArtefacto = new PlumaArtefacto();
        try{
            LayoutInflater layoutInflater = LayoutInflater.from(MisBuilds.this);
            AlertDialog anuncio = new AlertDialog.Builder(this).create();
            final View view = layoutInflater.inflate(R.layout.alert_artefactos_listview, null);
            final ListView lvAlertArtefactos = view.findViewById(R.id.lvAlertArtefactos);
            anuncio.setView(view);
            anuncio.show();
            lvAlertArtefactos.setAdapter(new AdaptadorListViewPluma(MisBuilds.this, plumaArtefactoArrayList));
            lvAlertArtefactos.setOnItemClickListener((parent, view1, position, id) -> {
                if(position == 0){
                    plumaArtefacto.setNombrePJ(nombrePJ);
                    plumaArtefacto.setSeleccionDatoSpiner(position);
                    plumaArtefacto.setNombreArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto());
                    plumaArtefacto.setRecursoArtefacto(R.drawable.pluma_afortunado);
                    datosProcesosSqlite.validarUInsertUpdatePlumaArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto(), plumaArtefacto);
                    traerArtefactosGuardadosPluma();
                    anuncio.dismiss();
                }else if(position == 1){
                    plumaArtefacto.setNombrePJ(nombrePJ);
                    plumaArtefacto.setSeleccionDatoSpiner(position);
                    plumaArtefacto.setNombreArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto());
                    plumaArtefacto.setRecursoArtefacto(R.drawable.pluma_aventurero);
                    datosProcesosSqlite.validarUInsertUpdatePlumaArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto(), plumaArtefacto);
                    traerArtefactosGuardadosPluma();
                    anuncio.dismiss();
                }else if(position == 2){
                    plumaArtefacto.setNombrePJ(nombrePJ);
                    plumaArtefacto.setSeleccionDatoSpiner(position);
                    plumaArtefacto.setNombreArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto());
                    plumaArtefacto.setRecursoArtefacto(R.drawable.pluma_curativa);
                    datosProcesosSqlite.validarUInsertUpdatePlumaArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto(), plumaArtefacto);
                    traerArtefactosGuardadosPluma();
                    anuncio.dismiss();
                }
            });
        }catch (Exception exception){
            Log.d("AlertArtefactosPluma", "ERROR ALERT GUARDAR: " + exception.getMessage());
        }
    }

    private void traerArtefactosGuardadosPluma(){
        DatosProcesosSqlite datosProcesosSqlite = new DatosProcesosSqlite(this);
        plumaArtefactoArrayList = datosProcesosSqlite.mostrarSeleccionPlumaArtefacto(nombrePJ);
        if(plumaArtefactoArrayList.size() <= 0){
            //nada
        }else{
            for(int i = 0; i < plumaArtefactoArrayList.size(); i++){
                ivPluma.setImageResource(plumaArtefactoArrayList.get(i).getRecursoArtefacto());
                Log.d("traerArtefactosPluma", "traerArtefactosGuardados: "+ plumaArtefactoArrayList.get(i).getNombreArtefacto());
            }
        }
    }

    private void alertArtefactosReloj(){
        relojArtefactoArrayList = new ArrayList<>();
        relojArtefactoArrayList.add(new RelojArtefacto(0, "Reloj del Afortunado"));
        relojArtefactoArrayList.add(new RelojArtefacto(1, "Reloj del Aventurero"));
        relojArtefactoArrayList.add(new RelojArtefacto(2, "Reloj Curativo"));

        DatosProcesosSqlite datosProcesosSqlite = new DatosProcesosSqlite(this);
        RelojArtefacto relojArtefacto = new RelojArtefacto();
        try{
            LayoutInflater layoutInflater = LayoutInflater.from(MisBuilds.this);
            AlertDialog anuncio = new AlertDialog.Builder(this).create();
            final View view = layoutInflater.inflate(R.layout.alert_artefactos_listview, null);
            final ListView lvAlertArtefactos = view.findViewById(R.id.lvAlertArtefactos);
            anuncio.setView(view);
            anuncio.show();
            lvAlertArtefactos.setAdapter(new AdaptadorListViewReloj(MisBuilds.this, relojArtefactoArrayList));
            lvAlertArtefactos.setOnItemClickListener((parent, view1, position, id) -> {
                if(position == 0){
                    relojArtefacto.setNombrePJ(nombrePJ);
                    relojArtefacto.setSeleccionDatoSpiner(position);
                    relojArtefacto.setNombreArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto());
                    relojArtefacto.setRecursoArtefacto(R.drawable.reloj_afortunado);
                    datosProcesosSqlite.validarUInsertUpdateRelojArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto(), relojArtefacto);
                    traerArtefactosGuardadosReloj();
                    anuncio.dismiss();
                }else if(position == 1){
                    relojArtefacto.setNombrePJ(nombrePJ);
                    relojArtefacto.setSeleccionDatoSpiner(position);
                    relojArtefacto.setNombreArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto());
                    relojArtefacto.setRecursoArtefacto(R.drawable.reloj_aventurero);
                    datosProcesosSqlite.validarUInsertUpdateRelojArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto(), relojArtefacto);
                    traerArtefactosGuardadosReloj();
                    anuncio.dismiss();
                }else if(position == 2){
                    relojArtefacto.setNombrePJ(nombrePJ);
                    relojArtefacto.setSeleccionDatoSpiner(position);
                    relojArtefacto.setNombreArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto());
                    relojArtefacto.setRecursoArtefacto(R.drawable.reloj_curativa);
                    datosProcesosSqlite.validarUInsertUpdateRelojArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto(), relojArtefacto);
                    traerArtefactosGuardadosReloj();
                    anuncio.dismiss();
                }
            });
        }catch (Exception exception){
            Log.d("AlertArtefactosPluma", "ERROR ALERT GUARDAR: " + exception.getMessage());
        }
    }

    private void traerArtefactosGuardadosReloj(){
        DatosProcesosSqlite datosProcesosSqlite = new DatosProcesosSqlite(this);
        relojArtefactoArrayList = datosProcesosSqlite.mostrarSeleccionRelojArtefacto(nombrePJ);
        if(relojArtefactoArrayList.size() <= 0){
            //nada
        }else{
            for(int i = 0; i < relojArtefactoArrayList.size(); i++){
                ivRelojArtefacto.setImageResource(relojArtefactoArrayList.get(i).getRecursoArtefacto());
                Log.d("traerArtefactosPluma", "traerArtefactosGuardados: "+ relojArtefactoArrayList.get(i).getNombreArtefacto());
            }
        }
    }
}