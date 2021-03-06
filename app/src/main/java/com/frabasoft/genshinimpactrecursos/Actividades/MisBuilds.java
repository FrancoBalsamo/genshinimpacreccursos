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
import com.frabasoft.genshinimpactrecursos.Adaptadores.AdaptadorListViewCopa;
import com.frabasoft.genshinimpactrecursos.Adaptadores.AdaptadorListViewCorona;
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
import com.frabasoft.genshinimpactrecursos.MainActivity;
import com.frabasoft.genshinimpactrecursos.Preferencias.PreferenciaSonidosEntrar;
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
            "Xinyan", "Yan Fei", "Zhongli"};

    private String[] arcosStringArray = {
            "Selecciona un arco",
            "Alas Celestiales", "Arco Compuesto", "Arco Real", "Arco Recurvo", "Arco de Amos",
            "Arco de Cuervo", "Arco de Favonius", "Arco de la Cazadora Esmeralda", "Arco del Cazador", "Arco del Cazador Estacional",
            "Arco del Pe??asco Oscuro", "Arco del Sacrificio", "Cazador del Callej??n", "Ebony Bow", "Elegia del Fin",
            "Herrumbre", "Juramento del Arquero", "Mensajero", "Oda a las Flores de Viento", "Prototipo Luz de Luna",
            "Tirachinas", "Ultimo Acorde"
    };

    private String[] catalizadorStringArray = {
            "Selecciona un catalizador",
            "Amber Catalyst", "Apuntes del Aprendiz", "Candado Terrenal", "Carta N??utica", "Cuentos de Cazadores de Dragones",
            "C??dice de Favonius", "Fr??o Eterno", "Grimorio Real", "Grimorio de Bolsillo", "Gu??a M??gica",
            "Historias de Otros Mundos", "Memorias de Sacrificios", "Nefrita Gemela", "Ojo de la Perspicacia", "Oraci??n Perdida a los Vientos",
            "Orbe Esmeralda", "Pergamino Celestial", "Perla Solar", "Prototipo ??mbar", "Sinfon??a de los Merodeadores",
            "Vino y Poes??a", "??gata del Pe??asco Oscuro"
    };

    private String[] espadaStringArray = {
            "Selecciona una espada",
            "Aquila Favonia", "Cortador de Jade Primordial", "Deseo Ponzo??oso", "Destello en la Oscuridad", "Espada Larga del Pe??asco Oscuro", "Espada Negra",
            "Espada Plateada", "Espada Real Larga", "Espada Surcacielos", "Espada de Favonius", "Espada de Hierro Oscuro", "Espada de Sacrificio",
            "Espada del Alba", "Espada del Descenso", "Espada del Viajero", "Espina de Hierro", "Flauta", "Hoja Afilada Celestial",
            "Hoja Desafilada", "Hoja Fr??a", "Hoja de Filetear", "Prototipo Rencor", "Rompemonta??as", "Rugido del Le??n"
    };

    private String[] lanzaStringArray = {
            "Selecciona una lanza",
            "Alabarda", "Borla Blanca", "Borla Negra", "B??culo de Homa", "Halc??n de Jade", "Lanza L??tica",
            "Lanza Perforanubes", "Lanza de Caza Real", "Lanza de Espinadrag??n", "Lanza de Favonius", "Lanza del Duelo", "Lanza del Pe??asco Oscuro",
            "Lanza del Principiante", "Perdici??n del Drag??n", "Pica Luna Creciente", "Prototipo Estelar", "Punta de Hierro", "P??a Celestial"
    };

    private String[] mandobleStringArray = {
            "Selecciona un mandoble",
            "Argento Estelar de las Nieves", "Espada L??tica", "Espada de Hierro Blanco", "Espada de la Desidia", "Espada del Mercenario", "Espada del Tiempo",
            "Garrote del Debate", "Gran Espada Real", "Gran Espada Sangrienta", "Gran Espada Surcacielos", "Gran Espada de Favonius", "Gran Espada de Sacrificio",
            "Gran Espada del Guerrero", "Gran Hoja del Pe??asco Oscuro", "L??pida del Lobo", "M??dula de la Serpiente Marina", "Orgullo Celestial", "Prototipo Arcaico",
            "Quartz", "Segadora de la Lluvia", "Sombra Blanca", "Sombra f??rrea"
    };

    private ArrayList<Armas> armasArrayList;
    private ArrayList<FlorArtefacto> florArtefactoArrayList = new ArrayList<FlorArtefacto>();
    private ArrayList<PlumaArtefacto> plumaArtefactoArrayList;
    private ArrayList<RelojArtefacto> relojArtefactoArrayList;
    private ArrayList<CopaArtefacto> copaArtefactoArrayList;
    private ArrayList<CoronaArtefacto> coronaArtefactoArrayList;

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
    private ImageView ivArmas, ivFlorArtefacto, ivPluma, ivRelojArtefacto, ivCopaArtefacto, ivCoronaArtefacto;
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
        ivCopaArtefacto = findViewById(R.id.ivCopaArtefacto);
        ivCoronaArtefacto = findViewById(R.id.ivCoronaArtefacto);

        ivFlorArtefacto.setImageResource(R.drawable.flor_afortunado);
        ivPluma.setImageResource(R.drawable.pluma_afortunado);
        ivRelojArtefacto.setImageResource(R.drawable.reloj_afortunado);
        ivCopaArtefacto.setImageResource(R.drawable.copa_afortunado);
        ivCoronaArtefacto.setImageResource(R.drawable.corona_afortunado);

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
                    scrollView.setEnabled(true);
                    scrollView.setVisibility(View.VISIBLE);
                    imgPJMisBuilds.setImageResource(R.drawable.albedobuilds);
                    cargarEspadas();
                    traerArtefactosGuardadosFlor();
                    traerArtefactosGuardadosPluma();
                    traerArtefactosGuardadosReloj();
                    traerArtefactosGuardadosCopa();
                    traerArtefactosGuardadosCorona();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 2) {
                    limpiarET();
                    nombrePJ = spPJMisBuilds.getSelectedItem().toString();
                    scrollView.setEnabled(true);
                    scrollView.setVisibility(View.VISIBLE);
                    imgPJMisBuilds.setImageResource(R.drawable.amberbuilds);
                    cargarArcos();
                    traerArtefactosGuardadosFlor();
                    traerArtefactosGuardadosPluma();
                    traerArtefactosGuardadosReloj();
                    traerArtefactosGuardadosCopa();
                    traerArtefactosGuardadosCorona();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 3) {
                    limpiarET();
                    nombrePJ = spPJMisBuilds.getSelectedItem().toString();
                    scrollView.setEnabled(true);
                    scrollView.setVisibility(View.VISIBLE);
                    imgPJMisBuilds.setImageResource(R.drawable.barbarabuilds);
                    cargarCatalizadores();
                    traerArtefactosGuardadosFlor();
                    traerArtefactosGuardadosPluma();
                    traerArtefactosGuardadosReloj();
                    traerArtefactosGuardadosCopa();
                    traerArtefactosGuardadosCorona();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 4) {
                    limpiarET();
                    nombrePJ = spPJMisBuilds.getSelectedItem().toString();
                    scrollView.setEnabled(true);
                    scrollView.setVisibility(View.VISIBLE);
                    imgPJMisBuilds.setImageResource(R.drawable.beidoubuilds);
                    cargarMandobles();
                    traerArtefactosGuardadosFlor();
                    traerArtefactosGuardadosPluma();
                    traerArtefactosGuardadosReloj();
                    traerArtefactosGuardadosCopa();
                    traerArtefactosGuardadosCorona();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 5) {
                    limpiarET();
                    nombrePJ = spPJMisBuilds.getSelectedItem().toString();
                    scrollView.setEnabled(true);
                    scrollView.setVisibility(View.VISIBLE);
                    imgPJMisBuilds.setImageResource(R.drawable.bennetbuilds);
                    cargarEspadas();
                    traerArtefactosGuardadosFlor();
                    traerArtefactosGuardadosPluma();
                    traerArtefactosGuardadosReloj();
                    traerArtefactosGuardadosCopa();
                    traerArtefactosGuardadosCorona();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 6) {
                    limpiarET();
                    nombrePJ = spPJMisBuilds.getSelectedItem().toString();
                    scrollView.setEnabled(true);
                    scrollView.setVisibility(View.VISIBLE);
                    imgPJMisBuilds.setImageResource(R.drawable.chongyunbuilds);
                    cargarMandobles();
                    traerArtefactosGuardadosFlor();
                    traerArtefactosGuardadosPluma();
                    traerArtefactosGuardadosReloj();
                    traerArtefactosGuardadosCopa();
                    traerArtefactosGuardadosCorona();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 7) {
                    limpiarET();
                    nombrePJ = spPJMisBuilds.getSelectedItem().toString();
                    scrollView.setEnabled(true);
                    scrollView.setVisibility(View.VISIBLE);
                    imgPJMisBuilds.setImageResource(R.drawable.dilucbuilds);
                    cargarMandobles();
                    traerArtefactosGuardadosFlor();
                    traerArtefactosGuardadosPluma();
                    traerArtefactosGuardadosReloj();
                    traerArtefactosGuardadosCopa();
                    traerArtefactosGuardadosCorona();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 8) {
                    limpiarET();
                    nombrePJ = spPJMisBuilds.getSelectedItem().toString();
                    scrollView.setEnabled(true);
                    scrollView.setVisibility(View.VISIBLE);
                    imgPJMisBuilds.setImageResource(R.drawable.dionabuilds);
                    cargarArcos();
                    traerArtefactosGuardadosFlor();
                    traerArtefactosGuardadosPluma();
                    traerArtefactosGuardadosReloj();
                    traerArtefactosGuardadosCopa();
                    traerArtefactosGuardadosCorona();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 9) {
                    limpiarET();
                    nombrePJ = spPJMisBuilds.getSelectedItem().toString();
                    scrollView.setEnabled(true);
                    scrollView.setVisibility(View.VISIBLE);
                    imgPJMisBuilds.setImageResource(R.drawable.fischlbuilds);
                    cargarArcos();
                    traerArtefactosGuardadosFlor();
                    traerArtefactosGuardadosPluma();
                    traerArtefactosGuardadosReloj();
                    traerArtefactosGuardadosCopa();
                    traerArtefactosGuardadosCorona();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 10) {
                    limpiarET();
                    nombrePJ = spPJMisBuilds.getSelectedItem().toString();
                    scrollView.setEnabled(true);
                    scrollView.setVisibility(View.VISIBLE);
                    imgPJMisBuilds.setImageResource(R.drawable.ganyubuilds);
                    cargarArcos();
                    traerArtefactosGuardadosFlor();
                    traerArtefactosGuardadosPluma();
                    traerArtefactosGuardadosReloj();
                    traerArtefactosGuardadosCopa();
                    traerArtefactosGuardadosCorona();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 11) {
                    limpiarET();
                    nombrePJ = spPJMisBuilds.getSelectedItem().toString();
                    scrollView.setEnabled(true);
                    scrollView.setVisibility(View.VISIBLE);
                    imgPJMisBuilds.setImageResource(R.drawable.hutaobuilds);
                    cargarLanzas();
                    traerArtefactosGuardadosFlor();
                    traerArtefactosGuardadosPluma();
                    traerArtefactosGuardadosReloj();
                    traerArtefactosGuardadosCopa();
                    traerArtefactosGuardadosCorona();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 12) {
                    limpiarET();
                    nombrePJ = spPJMisBuilds.getSelectedItem().toString();
                    scrollView.setEnabled(true);
                    scrollView.setVisibility(View.VISIBLE);
                    imgPJMisBuilds.setImageResource(R.drawable.jeanbuilds);
                    cargarEspadas();
                    traerArtefactosGuardadosFlor();
                    traerArtefactosGuardadosPluma();
                    traerArtefactosGuardadosReloj();
                    traerArtefactosGuardadosCopa();
                    traerArtefactosGuardadosCorona();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 13) {
                    limpiarET();
                    nombrePJ = spPJMisBuilds.getSelectedItem().toString();
                    scrollView.setEnabled(true);
                    scrollView.setVisibility(View.VISIBLE);
                    imgPJMisBuilds.setImageResource(R.drawable.kaeyabuilds);
                    cargarEspadas();
                    traerArtefactosGuardadosFlor();
                    traerArtefactosGuardadosPluma();
                    traerArtefactosGuardadosReloj();
                    traerArtefactosGuardadosCopa();
                    traerArtefactosGuardadosCorona();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 14) {
                    limpiarET();
                    nombrePJ = spPJMisBuilds.getSelectedItem().toString();
                    scrollView.setEnabled(true);
                    scrollView.setVisibility(View.VISIBLE);
                    imgPJMisBuilds.setImageResource(R.drawable.keqingbuilds);
                    cargarEspadas();
                    traerArtefactosGuardadosFlor();
                    traerArtefactosGuardadosPluma();
                    traerArtefactosGuardadosReloj();
                    traerArtefactosGuardadosCopa();
                    traerArtefactosGuardadosCorona();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 15) {
                    limpiarET();
                    nombrePJ = spPJMisBuilds.getSelectedItem().toString();
                    scrollView.setEnabled(true);
                    scrollView.setVisibility(View.VISIBLE);
                    imgPJMisBuilds.setImageResource(R.drawable.kleebuilds);
                    cargarCatalizadores();
                    traerArtefactosGuardadosFlor();
                    traerArtefactosGuardadosPluma();
                    traerArtefactosGuardadosReloj();
                    traerArtefactosGuardadosCopa();
                    traerArtefactosGuardadosCorona();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 16) {
                    limpiarET();
                    nombrePJ = spPJMisBuilds.getSelectedItem().toString();
                    scrollView.setEnabled(true);
                    scrollView.setVisibility(View.VISIBLE);
                    imgPJMisBuilds.setImageResource(R.drawable.lisabuilds);
                    cargarCatalizadores();
                    traerArtefactosGuardadosFlor();
                    traerArtefactosGuardadosPluma();
                    traerArtefactosGuardadosReloj();
                    traerArtefactosGuardadosCopa();
                    traerArtefactosGuardadosCorona();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 17) {
                    limpiarET();
                    nombrePJ = spPJMisBuilds.getSelectedItem().toString();
                    scrollView.setEnabled(true);
                    scrollView.setVisibility(View.VISIBLE);
                    imgPJMisBuilds.setImageResource(R.drawable.monabuilds);
                    cargarCatalizadores();
                    traerArtefactosGuardadosFlor();
                    traerArtefactosGuardadosPluma();
                    traerArtefactosGuardadosReloj();
                    traerArtefactosGuardadosCopa();
                    traerArtefactosGuardadosCorona();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 18) {
                    limpiarET();
                    nombrePJ = spPJMisBuilds.getSelectedItem().toString();
                    scrollView.setEnabled(true);
                    scrollView.setVisibility(View.VISIBLE);
                    imgPJMisBuilds.setImageResource(R.drawable.ningguangbuilds);
                    cargarCatalizadores();
                    traerArtefactosGuardadosFlor();
                    traerArtefactosGuardadosPluma();
                    traerArtefactosGuardadosReloj();
                    traerArtefactosGuardadosCopa();
                    traerArtefactosGuardadosCorona();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 19) {
                    limpiarET();
                    nombrePJ = spPJMisBuilds.getSelectedItem().toString();
                    scrollView.setEnabled(true);
                    scrollView.setVisibility(View.VISIBLE);
                    imgPJMisBuilds.setImageResource(R.drawable.noellebuilds);
                    cargarMandobles();
                    traerArtefactosGuardadosFlor();
                    traerArtefactosGuardadosPluma();
                    traerArtefactosGuardadosReloj();
                    traerArtefactosGuardadosCopa();
                    traerArtefactosGuardadosCorona();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 20) {
                    limpiarET();
                    nombrePJ = spPJMisBuilds.getSelectedItem().toString();
                    scrollView.setEnabled(true);
                    scrollView.setVisibility(View.VISIBLE);
                    imgPJMisBuilds.setImageResource(R.drawable.qiqibuilds);
                    cargarEspadas();
                    traerArtefactosGuardadosFlor();
                    traerArtefactosGuardadosPluma();
                    traerArtefactosGuardadosReloj();
                    traerArtefactosGuardadosCopa();
                    traerArtefactosGuardadosCorona();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 21) {
                    limpiarET();
                    nombrePJ = spPJMisBuilds.getSelectedItem().toString();
                    scrollView.setEnabled(true);
                    scrollView.setVisibility(View.VISIBLE);
                    imgPJMisBuilds.setImageResource(R.drawable.razorbuilds);
                    cargarMandobles();
                    traerArtefactosGuardadosFlor();
                    traerArtefactosGuardadosPluma();
                    traerArtefactosGuardadosReloj();
                    traerArtefactosGuardadosCopa();
                    traerArtefactosGuardadosCorona();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 22) {
                    limpiarET();
                    nombrePJ = spPJMisBuilds.getSelectedItem().toString();
                    scrollView.setEnabled(true);
                    scrollView.setVisibility(View.VISIBLE);
                    imgPJMisBuilds.setImageResource(R.drawable.rosariabuilds);
                    cargarLanzas();
                    traerArtefactosGuardadosFlor();
                    traerArtefactosGuardadosPluma();
                    traerArtefactosGuardadosReloj();
                    traerArtefactosGuardadosCopa();
                    traerArtefactosGuardadosCorona();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 23) {
                    limpiarET();
                    nombrePJ = spPJMisBuilds.getSelectedItem().toString();
                    scrollView.setEnabled(true);
                    scrollView.setVisibility(View.VISIBLE);
                    imgPJMisBuilds.setImageResource(R.drawable.sacarosabuilds);
                    cargarCatalizadores();
                    traerArtefactosGuardadosFlor();
                    traerArtefactosGuardadosPluma();
                    traerArtefactosGuardadosReloj();
                    traerArtefactosGuardadosCopa();
                    traerArtefactosGuardadosCorona();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 24) {
                    limpiarET();
                    nombrePJ = spPJMisBuilds.getSelectedItem().toString();
                    scrollView.setEnabled(true);
                    scrollView.setVisibility(View.VISIBLE);
                    imgPJMisBuilds.setImageResource(R.drawable.tartagliabuilds);
                    cargarArcos();
                    traerArtefactosGuardadosFlor();
                    traerArtefactosGuardadosPluma();
                    traerArtefactosGuardadosReloj();
                    traerArtefactosGuardadosCopa();
                    traerArtefactosGuardadosCorona();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 25) {
                    limpiarET();
                    nombrePJ = spPJMisBuilds.getSelectedItem().toString();
                    scrollView.setEnabled(true);
                    scrollView.setVisibility(View.VISIBLE);
                    imgPJMisBuilds.setImageResource(R.drawable.ventibuilds);
                    cargarArcos();
                    traerArtefactosGuardadosFlor();
                    traerArtefactosGuardadosPluma();
                    traerArtefactosGuardadosReloj();
                    traerArtefactosGuardadosCopa();
                    traerArtefactosGuardadosCorona();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 26) {
                    limpiarET();
                    nombrePJ = spPJMisBuilds.getSelectedItem().toString();
                    scrollView.setEnabled(true);
                    scrollView.setVisibility(View.VISIBLE);
                    imgPJMisBuilds.setImageResource(R.drawable.xianlingbuilds);
                    cargarLanzas();
                    traerArtefactosGuardadosFlor();
                    traerArtefactosGuardadosPluma();
                    traerArtefactosGuardadosReloj();
                    traerArtefactosGuardadosCopa();
                    traerArtefactosGuardadosCorona();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 27) {
                    limpiarET();
                    nombrePJ = spPJMisBuilds.getSelectedItem().toString();
                    scrollView.setEnabled(true);
                    scrollView.setVisibility(View.VISIBLE);
                    imgPJMisBuilds.setImageResource(R.drawable.xiaobuilds);
                    cargarLanzas();
                    traerArtefactosGuardadosFlor();
                    traerArtefactosGuardadosPluma();
                    traerArtefactosGuardadosReloj();
                    traerArtefactosGuardadosCopa();
                    traerArtefactosGuardadosCorona();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 28) {
                    limpiarET();
                    nombrePJ = spPJMisBuilds.getSelectedItem().toString();
                    scrollView.setEnabled(true);
                    scrollView.setVisibility(View.VISIBLE);
                    imgPJMisBuilds.setImageResource(R.drawable.xingqiubuilds);
                    cargarEspadas();
                    traerArtefactosGuardadosFlor();
                    traerArtefactosGuardadosPluma();
                    traerArtefactosGuardadosReloj();
                    traerArtefactosGuardadosCopa();
                    traerArtefactosGuardadosCorona();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 29) {
                    limpiarET();
                    nombrePJ = spPJMisBuilds.getSelectedItem().toString();
                    scrollView.setEnabled(true);
                    scrollView.setVisibility(View.VISIBLE);
                    imgPJMisBuilds.setImageResource(R.drawable.xinyanbuilds);
                    cargarMandobles();
                    traerArtefactosGuardadosFlor();
                    traerArtefactosGuardadosPluma();
                    traerArtefactosGuardadosReloj();
                    traerArtefactosGuardadosCopa();
                    traerArtefactosGuardadosCorona();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                } else if (position == 30) {
                    limpiarET();
                    nombrePJ = spPJMisBuilds.getSelectedItem().toString();
                    scrollView.setEnabled(true);
                    scrollView.setVisibility(View.VISIBLE);
                    imgPJMisBuilds.setImageResource(R.drawable.yanfeibuilds);
                    cargarCatalizadores();
                    traerArtefactosGuardadosFlor();
                    traerArtefactosGuardadosPluma();
                    traerArtefactosGuardadosReloj();
                    traerArtefactosGuardadosCopa();
                    traerArtefactosGuardadosCorona();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                }else if (position == 31) {
                    limpiarET();
                    nombrePJ = spPJMisBuilds.getSelectedItem().toString();
                    scrollView.setEnabled(true);
                    scrollView.setVisibility(View.VISIBLE);
                    imgPJMisBuilds.setImageResource(R.drawable.zhonglibuilds);
                    cargarLanzas();
                    traerArtefactosGuardadosFlor();
                    traerArtefactosGuardadosPluma();
                    traerArtefactosGuardadosReloj();
                    traerArtefactosGuardadosCopa();
                    traerArtefactosGuardadosCorona();
                    traerArmaGuardada();
                    CargarDatosSQLite();
                    GuardarIndividuales();
                    GuardarTodos();
                    VistaPreviaBuild();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        ivFlorArtefacto.setOnClickListener(v -> alertArtefactosFlor());
        ivPluma.setOnClickListener(v -> alertArtefactosPluma());
        ivRelojArtefacto.setOnClickListener(v -> alertArtefactosReloj());
        ivCopaArtefacto.setOnClickListener(v -> alertArtefactosCopa());
        ivCoronaArtefacto.setOnClickListener(v ->alertArtefactosCorona());
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
            etFlorPrin.setError("El campo no puede estar vac??o.");
            etFlorPrin.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(etFlorSecA.getText().toString())) {
            etFlorSecA.setError("El campo no puede estar vac??o.");
            etFlorSecA.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(etFlorSecB.getText().toString())) {
            etFlorSecB.setError("El campo no puede estar vac??o.");
            etFlorSecB.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(etFlorSecC.getText().toString())) {
            etFlorSecC.setError("El campo no puede estar vac??o.");
            etFlorSecC.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(etFlorSecD.getText().toString())) {
            etFlorSecD.setError("El campo no puede estar vac??o.");
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
            etPlumPrin.setError("El campo no puede estar vac??o.");
            etPlumPrin.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(etPlumSecA.getText().toString())) {
            etPlumSecA.setError("El campo no puede estar vac??o.");
            etPlumSecA.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(etPlumSecB.getText().toString())) {
            etPlumSecB.setError("El campo no puede estar vac??o.");
            etPlumSecB.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(etPlumSecC.getText().toString())) {
            etPlumSecC.setError("El campo no puede estar vac??o.");
            etPlumSecC.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(etPlumSecD.getText().toString())) {
            etPlumSecD.setError("El campo no puede estar vac??o.");
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
            etRelPrin.setError("El campo no puede estar vac??o.");
            etRelPrin.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(etRelSecA.getText().toString())) {
            etRelSecA.setError("El campo no puede estar vac??o.");
            etRelSecA.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(etRelSecB.getText().toString())) {
            etRelSecB.setError("El campo no puede estar vac??o.");
            etRelSecB.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(etRelSecC.getText().toString())) {
            etRelSecC.setError("El campo no puede estar vac??o.");
            etRelSecC.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(etRelSecD.getText().toString())) {
            etRelSecD.setError("El campo no puede estar vac??o.");
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
            etCopPrin.setError("El campo no puede estar vac??o.");
            etCopPrin.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(etCopSecA.getText().toString())) {
            etCopSecA.setError("El campo no puede estar vac??o.");
            etCopSecA.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(etCopSecB.getText().toString())) {
            etCopSecB.setError("El campo no puede estar vac??o.");
            etCopSecB.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(etCopSecC.getText().toString())) {
            etCopSecC.setError("El campo no puede estar vac??o.");
            etCopSecC.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(etCopSecD.getText().toString())) {
            etCopSecD.setError("El campo no puede estar vac??o.");
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
            etCorPrin.setError("El campo no puede estar vac??o.");
            etCorPrin.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(etCorSecA.getText().toString())) {
            etCorSecA.setError("El campo no puede estar vac??o.");
            etCorSecA.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(etCorSecB.getText().toString())) {
            etCorSecB.setError("El campo no puede estar vac??o.");
            etCorSecB.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(etCorSecC.getText().toString())) {
            etCorSecC.setError("El campo no puede estar vac??o.");
            etCorSecC.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(etCorSecD.getText().toString())) {
            etCorSecD.setError("El campo no puede estar vac??o.");
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
        vistaPrevia.setOnClickListener(v -> {
            if(traerValorChk(this) == 1){
                entrar.start();
            }
            Intent vista = new Intent(MisBuilds.this, VistaPrevia.class);
            vista.putExtra("pj", spPJMisBuilds.getSelectedItem().toString());
            Toast.makeText(MisBuilds.this, "??Recuerda que puedes guardar la vista previa como imagen en tu tel??fono presionando o tocando en cualquier parte de la pantalla!", Toast.LENGTH_SHORT).show();
            startActivity(vista);
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
        if(traerValorChk(this) == 1){
            salir.start();
        }
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
        florArtefactoArrayList.add(new FlorArtefacto(3, "Flor del Instructor"));
        florArtefactoArrayList.add(new FlorArtefacto(4, "Flor del Berseker"));
        florArtefactoArrayList.add(new FlorArtefacto(5, "Flor Exiliado"));
        florArtefactoArrayList.add(new FlorArtefacto(6, "Flor del Viajero"));
        florArtefactoArrayList.add(new FlorArtefacto(7, "Flor del Marcial"));
        florArtefactoArrayList.add(new FlorArtefacto(8, "Flor Guardian"));
        florArtefactoArrayList.add(new FlorArtefacto(9, "Flor del Milagro"));
        florArtefactoArrayList.add(new FlorArtefacto(10, "Flor del Guerrero"));
        florArtefactoArrayList.add(new FlorArtefacto(11, "Flor Jugadora"));
        florArtefactoArrayList.add(new FlorArtefacto(12, "Flor del Erudita"));
        florArtefactoArrayList.add(new FlorArtefacto(13, "Flor del Gladiador"));
        florArtefactoArrayList.add(new FlorArtefacto(14, "Flor Doncella"));
        florArtefactoArrayList.add(new FlorArtefacto(15, "Flor del Nobleza"));
        florArtefactoArrayList.add(new FlorArtefacto(16, "Flor del Sanguinaria"));
        florArtefactoArrayList.add(new FlorArtefacto(17, "Flor Errante"));
        florArtefactoArrayList.add(new FlorArtefacto(18, "Flor del Esmeralda"));
        florArtefactoArrayList.add(new FlorArtefacto(19, "Flor del Furia"));
        florArtefactoArrayList.add(new FlorArtefacto(20, "Flor Domadora"));
        florArtefactoArrayList.add(new FlorArtefacto(21, "Flor del Bruja"));
        florArtefactoArrayList.add(new FlorArtefacto(22, "Flor del Corredor"));
        florArtefactoArrayList.add(new FlorArtefacto(23, "Flor Petra"));
        florArtefactoArrayList.add(new FlorArtefacto(24, "Flor del Meteoro"));
        florArtefactoArrayList.add(new FlorArtefacto(25, "Flor del Nomada"));
        florArtefactoArrayList.add(new FlorArtefacto(26, "Flor Profundidades"));

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
                }else if(position == 3){
                    florArtefacto.setNombrePJ(nombrePJ);
                    florArtefacto.setSeleccionDatoSpiner(position);
                    florArtefacto.setNombreArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto());
                    florArtefacto.setRecursoArtefacto(R.drawable.flor_instructor);
                    datosProcesosSqlite.validarUInsertUpdateFlorArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto(), florArtefacto);
                    traerArtefactosGuardadosFlor();
                    anuncio.dismiss();
                }else if(position == 4){
                    florArtefacto.setNombrePJ(nombrePJ);
                    florArtefacto.setSeleccionDatoSpiner(position);
                    florArtefacto.setNombreArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto());
                    florArtefacto.setRecursoArtefacto(R.drawable.flor_berseker);
                    datosProcesosSqlite.validarUInsertUpdateFlorArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto(), florArtefacto);
                    traerArtefactosGuardadosFlor();
                    anuncio.dismiss();
                }else if(position == 5){
                    florArtefacto.setNombrePJ(nombrePJ);
                    florArtefacto.setSeleccionDatoSpiner(position);
                    florArtefacto.setNombreArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto());
                    florArtefacto.setRecursoArtefacto(R.drawable.flor_exiliado);
                    datosProcesosSqlite.validarUInsertUpdateFlorArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto(), florArtefacto);
                    traerArtefactosGuardadosFlor();
                    anuncio.dismiss();
                }else if(position == 6){
                    florArtefacto.setNombrePJ(nombrePJ);
                    florArtefacto.setSeleccionDatoSpiner(position);
                    florArtefacto.setNombreArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto());
                    florArtefacto.setRecursoArtefacto(R.drawable.flor_viajero);
                    datosProcesosSqlite.validarUInsertUpdateFlorArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto(), florArtefacto);
                    traerArtefactosGuardadosFlor();
                    anuncio.dismiss();
                }else if(position == 7){
                    florArtefacto.setNombrePJ(nombrePJ);
                    florArtefacto.setSeleccionDatoSpiner(position);
                    florArtefacto.setNombreArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto());
                    florArtefacto.setRecursoArtefacto(R.drawable.flor_marcial);
                    datosProcesosSqlite.validarUInsertUpdateFlorArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto(), florArtefacto);
                    traerArtefactosGuardadosFlor();
                    anuncio.dismiss();
                }else if(position == 8){
                    florArtefacto.setNombrePJ(nombrePJ);
                    florArtefacto.setSeleccionDatoSpiner(position);
                    florArtefacto.setNombreArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto());
                    florArtefacto.setRecursoArtefacto(R.drawable.flor_guardian);
                    datosProcesosSqlite.validarUInsertUpdateFlorArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto(), florArtefacto);
                    traerArtefactosGuardadosFlor();
                    anuncio.dismiss();
                }else if(position == 9){
                    florArtefacto.setNombrePJ(nombrePJ);
                    florArtefacto.setSeleccionDatoSpiner(position);
                    florArtefacto.setNombreArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto());
                    florArtefacto.setRecursoArtefacto(R.drawable.flor_milagro);
                    datosProcesosSqlite.validarUInsertUpdateFlorArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto(), florArtefacto);
                    traerArtefactosGuardadosFlor();
                    anuncio.dismiss();
                }else if(position == 10){
                    florArtefacto.setNombrePJ(nombrePJ);
                    florArtefacto.setSeleccionDatoSpiner(position);
                    florArtefacto.setNombreArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto());
                    florArtefacto.setRecursoArtefacto(R.drawable.flor_guerrero);
                    datosProcesosSqlite.validarUInsertUpdateFlorArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto(), florArtefacto);
                    traerArtefactosGuardadosFlor();
                    anuncio.dismiss();
                }else if(position == 1){
                    florArtefacto.setNombrePJ(nombrePJ);
                    florArtefacto.setSeleccionDatoSpiner(position);
                    florArtefacto.setNombreArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto());
                    florArtefacto.setRecursoArtefacto(R.drawable.flor_jugadora);
                    datosProcesosSqlite.validarUInsertUpdateFlorArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto(), florArtefacto);
                    traerArtefactosGuardadosFlor();
                    anuncio.dismiss();
                }else if(position == 12){
                    florArtefacto.setNombrePJ(nombrePJ);
                    florArtefacto.setSeleccionDatoSpiner(position);
                    florArtefacto.setNombreArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto());
                    florArtefacto.setRecursoArtefacto(R.drawable.flor_erudita);
                    datosProcesosSqlite.validarUInsertUpdateFlorArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto(), florArtefacto);
                    traerArtefactosGuardadosFlor();
                    anuncio.dismiss();
                }else if(position == 13){
                    florArtefacto.setNombrePJ(nombrePJ);
                    florArtefacto.setSeleccionDatoSpiner(position);
                    florArtefacto.setNombreArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto());
                    florArtefacto.setRecursoArtefacto(R.drawable.flor_gladiador);
                    datosProcesosSqlite.validarUInsertUpdateFlorArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto(), florArtefacto);
                    traerArtefactosGuardadosFlor();
                    anuncio.dismiss();
                }else if(position == 14){
                    florArtefacto.setNombrePJ(nombrePJ);
                    florArtefacto.setSeleccionDatoSpiner(position);
                    florArtefacto.setNombreArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto());
                    florArtefacto.setRecursoArtefacto(R.drawable.flor_doncella);
                    datosProcesosSqlite.validarUInsertUpdateFlorArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto(), florArtefacto);
                    traerArtefactosGuardadosFlor();
                    anuncio.dismiss();
                }else if(position == 15){
                    florArtefacto.setNombrePJ(nombrePJ);
                    florArtefacto.setSeleccionDatoSpiner(position);
                    florArtefacto.setNombreArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto());
                    florArtefacto.setRecursoArtefacto(R.drawable.flor_nobleza);
                    datosProcesosSqlite.validarUInsertUpdateFlorArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto(), florArtefacto);
                    traerArtefactosGuardadosFlor();
                    anuncio.dismiss();
                }else if(position == 16){
                    florArtefacto.setNombrePJ(nombrePJ);
                    florArtefacto.setSeleccionDatoSpiner(position);
                    florArtefacto.setNombreArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto());
                    florArtefacto.setRecursoArtefacto(R.drawable.flor_sangui);
                    datosProcesosSqlite.validarUInsertUpdateFlorArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto(), florArtefacto);
                    traerArtefactosGuardadosFlor();
                    anuncio.dismiss();
                }else if(position == 17){
                    florArtefacto.setNombrePJ(nombrePJ);
                    florArtefacto.setSeleccionDatoSpiner(position);
                    florArtefacto.setNombreArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto());
                    florArtefacto.setRecursoArtefacto(R.drawable.flor_errante);
                    datosProcesosSqlite.validarUInsertUpdateFlorArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto(), florArtefacto);
                    traerArtefactosGuardadosFlor();
                    anuncio.dismiss();
                }else if(position == 18){
                    florArtefacto.setNombrePJ(nombrePJ);
                    florArtefacto.setSeleccionDatoSpiner(position);
                    florArtefacto.setNombreArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto());
                    florArtefacto.setRecursoArtefacto(R.drawable.flor_esmeralda);
                    datosProcesosSqlite.validarUInsertUpdateFlorArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto(), florArtefacto);
                    traerArtefactosGuardadosFlor();
                    anuncio.dismiss();
                }else if(position == 19){
                    florArtefacto.setNombrePJ(nombrePJ);
                    florArtefacto.setSeleccionDatoSpiner(position);
                    florArtefacto.setNombreArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto());
                    florArtefacto.setRecursoArtefacto(R.drawable.flor_furiatrueno);
                    datosProcesosSqlite.validarUInsertUpdateFlorArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto(), florArtefacto);
                    traerArtefactosGuardadosFlor();
                    anuncio.dismiss();
                }else if(position == 20){
                    florArtefacto.setNombrePJ(nombrePJ);
                    florArtefacto.setSeleccionDatoSpiner(position);
                    florArtefacto.setNombreArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto());
                    florArtefacto.setRecursoArtefacto(R.drawable.flor_domtrueno);
                    datosProcesosSqlite.validarUInsertUpdateFlorArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto(), florArtefacto);
                    traerArtefactosGuardadosFlor();
                    anuncio.dismiss();
                }else if(position == 21){
                    florArtefacto.setNombrePJ(nombrePJ);
                    florArtefacto.setSeleccionDatoSpiner(position);
                    florArtefacto.setNombreArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto());
                    florArtefacto.setRecursoArtefacto(R.drawable.flor_bruja);
                    datosProcesosSqlite.validarUInsertUpdateFlorArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto(), florArtefacto);
                    traerArtefactosGuardadosFlor();
                    anuncio.dismiss();
                }else if(position == 22){
                    florArtefacto.setNombrePJ(nombrePJ);
                    florArtefacto.setSeleccionDatoSpiner(position);
                    florArtefacto.setNombreArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto());
                    florArtefacto.setRecursoArtefacto(R.drawable.flor_corredor);
                    datosProcesosSqlite.validarUInsertUpdateFlorArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto(), florArtefacto);
                    traerArtefactosGuardadosFlor();
                    anuncio.dismiss();
                }else if(position == 23){
                    florArtefacto.setNombrePJ(nombrePJ);
                    florArtefacto.setSeleccionDatoSpiner(position);
                    florArtefacto.setNombreArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto());
                    florArtefacto.setRecursoArtefacto(R.drawable.flor_petra);
                    datosProcesosSqlite.validarUInsertUpdateFlorArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto(), florArtefacto);
                    traerArtefactosGuardadosFlor();
                    anuncio.dismiss();
                }else if(position == 24){
                    florArtefacto.setNombrePJ(nombrePJ);
                    florArtefacto.setSeleccionDatoSpiner(position);
                    florArtefacto.setNombreArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto());
                    florArtefacto.setRecursoArtefacto(R.drawable.flor_meteoro);
                    datosProcesosSqlite.validarUInsertUpdateFlorArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto(), florArtefacto);
                    traerArtefactosGuardadosFlor();
                    anuncio.dismiss();
                }else if(position == 25){
                    florArtefacto.setNombrePJ(nombrePJ);
                    florArtefacto.setSeleccionDatoSpiner(position);
                    florArtefacto.setNombreArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto());
                    florArtefacto.setRecursoArtefacto(R.drawable.flor_nomada);
                    datosProcesosSqlite.validarUInsertUpdateFlorArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto(), florArtefacto);
                    traerArtefactosGuardadosFlor();
                    anuncio.dismiss();
                }else if(position == 26){
                    florArtefacto.setNombrePJ(nombrePJ);
                    florArtefacto.setSeleccionDatoSpiner(position);
                    florArtefacto.setNombreArtefacto(florArtefactoArrayList.get(position).getNombreArtefacto());
                    florArtefacto.setRecursoArtefacto(R.drawable.flor_profundidades);
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

        //3*
        plumaArtefactoArrayList.add(new PlumaArtefacto(0, "Pluma del Afortunado"));
        plumaArtefactoArrayList.add(new PlumaArtefacto(1, "Pluma del Aventurero"));
        plumaArtefactoArrayList.add(new PlumaArtefacto(2, "Pluma Curativa"));

        //4*
        plumaArtefactoArrayList.add(new PlumaArtefacto(3, "Pluma del Instructor"));
        plumaArtefactoArrayList.add(new PlumaArtefacto(4, "Pluma del Berseker"));
        plumaArtefactoArrayList.add(new PlumaArtefacto(5, "Pluma Exiliado"));
        plumaArtefactoArrayList.add(new PlumaArtefacto(6, "Pluma del Viajero"));
        plumaArtefactoArrayList.add(new PlumaArtefacto(7, "Pluma del Marcial"));
        plumaArtefactoArrayList.add(new PlumaArtefacto(8, "Pluma Guardian"));
        plumaArtefactoArrayList.add(new PlumaArtefacto(9, "Pluma del Milagro"));
        plumaArtefactoArrayList.add(new PlumaArtefacto(10, "Pluma del Guerrero"));
        plumaArtefactoArrayList.add(new PlumaArtefacto(11, "Pluma Jugadora"));
        plumaArtefactoArrayList.add(new PlumaArtefacto(12, "Pluma del Erudita"));

        //5*
        plumaArtefactoArrayList.add(new PlumaArtefacto(13, "Pluma del Gladiador"));
        plumaArtefactoArrayList.add(new PlumaArtefacto(14, "Pluma Doncella"));
        plumaArtefactoArrayList.add(new PlumaArtefacto(15, "Pluma del Nobleza"));
        plumaArtefactoArrayList.add(new PlumaArtefacto(16, "Pluma del Sanguinaria"));
        plumaArtefactoArrayList.add(new PlumaArtefacto(17, "Pluma Errante"));
        plumaArtefactoArrayList.add(new PlumaArtefacto(18, "Pluma del Esmeralda"));
        plumaArtefactoArrayList.add(new PlumaArtefacto(19, "Pluma del Furia"));
        plumaArtefactoArrayList.add(new PlumaArtefacto(20, "Pluma Domadora"));
        plumaArtefactoArrayList.add(new PlumaArtefacto(21, "Pluma del Bruja"));
        plumaArtefactoArrayList.add(new PlumaArtefacto(22, "Pluma del Corredor"));
        plumaArtefactoArrayList.add(new PlumaArtefacto(23, "Pluma Petra"));
        plumaArtefactoArrayList.add(new PlumaArtefacto(24, "Pluma del Meteoro"));
        plumaArtefactoArrayList.add(new PlumaArtefacto(25, "Pluma del Nomada"));
        plumaArtefactoArrayList.add(new PlumaArtefacto(26, "Pluma Profundidades"));

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
                }else if(position == 3){/////////////////////4*
                    plumaArtefacto.setNombrePJ(nombrePJ);
                    plumaArtefacto.setSeleccionDatoSpiner(position);
                    plumaArtefacto.setNombreArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto());
                    plumaArtefacto.setRecursoArtefacto(R.drawable.pluma_instructor);
                    datosProcesosSqlite.validarUInsertUpdatePlumaArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto(), plumaArtefacto);
                    traerArtefactosGuardadosPluma();
                    anuncio.dismiss();
                }else if(position == 4){
                    plumaArtefacto.setNombrePJ(nombrePJ);
                    plumaArtefacto.setSeleccionDatoSpiner(position);
                    plumaArtefacto.setNombreArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto());
                    plumaArtefacto.setRecursoArtefacto(R.drawable.pluma_berseker);
                    datosProcesosSqlite.validarUInsertUpdatePlumaArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto(), plumaArtefacto);
                    traerArtefactosGuardadosPluma();
                    anuncio.dismiss();
                }else if(position == 5){
                    plumaArtefacto.setNombrePJ(nombrePJ);
                    plumaArtefacto.setSeleccionDatoSpiner(position);
                    plumaArtefacto.setNombreArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto());
                    plumaArtefacto.setRecursoArtefacto(R.drawable.pluma_exiliado);
                    datosProcesosSqlite.validarUInsertUpdatePlumaArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto(), plumaArtefacto);
                    traerArtefactosGuardadosPluma();
                    anuncio.dismiss();
                }else if(position == 6){
                    plumaArtefacto.setNombrePJ(nombrePJ);
                    plumaArtefacto.setSeleccionDatoSpiner(position);
                    plumaArtefacto.setNombreArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto());
                    plumaArtefacto.setRecursoArtefacto(R.drawable.pluma_viajero);
                    datosProcesosSqlite.validarUInsertUpdatePlumaArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto(), plumaArtefacto);
                    traerArtefactosGuardadosPluma();
                    anuncio.dismiss();
                }else if(position == 7){
                    plumaArtefacto.setNombrePJ(nombrePJ);
                    plumaArtefacto.setSeleccionDatoSpiner(position);
                    plumaArtefacto.setNombreArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto());
                    plumaArtefacto.setRecursoArtefacto(R.drawable.pluma_marcial);
                    datosProcesosSqlite.validarUInsertUpdatePlumaArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto(), plumaArtefacto);
                    traerArtefactosGuardadosPluma();
                    anuncio.dismiss();
                }else if(position == 8){
                    plumaArtefacto.setNombrePJ(nombrePJ);
                    plumaArtefacto.setSeleccionDatoSpiner(position);
                    plumaArtefacto.setNombreArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto());
                    plumaArtefacto.setRecursoArtefacto(R.drawable.pluma_guardian);
                    datosProcesosSqlite.validarUInsertUpdatePlumaArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto(), plumaArtefacto);
                    traerArtefactosGuardadosPluma();
                    anuncio.dismiss();
                }else if(position == 9){
                    plumaArtefacto.setNombrePJ(nombrePJ);
                    plumaArtefacto.setSeleccionDatoSpiner(position);
                    plumaArtefacto.setNombreArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto());
                    plumaArtefacto.setRecursoArtefacto(R.drawable.pluma_milagro);
                    datosProcesosSqlite.validarUInsertUpdatePlumaArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto(), plumaArtefacto);
                    traerArtefactosGuardadosPluma();
                    anuncio.dismiss();
                }else if(position == 10){
                    plumaArtefacto.setNombrePJ(nombrePJ);
                    plumaArtefacto.setSeleccionDatoSpiner(position);
                    plumaArtefacto.setNombreArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto());
                    plumaArtefacto.setRecursoArtefacto(R.drawable.pluma_guerrero);
                    datosProcesosSqlite.validarUInsertUpdatePlumaArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto(), plumaArtefacto);
                    traerArtefactosGuardadosPluma();
                    anuncio.dismiss();
                }else if(position == 11){
                    plumaArtefacto.setNombrePJ(nombrePJ);
                    plumaArtefacto.setSeleccionDatoSpiner(position);
                    plumaArtefacto.setNombreArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto());
                    plumaArtefacto.setRecursoArtefacto(R.drawable.pluma_jugadora);
                    datosProcesosSqlite.validarUInsertUpdatePlumaArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto(), plumaArtefacto);
                    traerArtefactosGuardadosPluma();
                    anuncio.dismiss();
                }else if(position == 12){
                    plumaArtefacto.setNombrePJ(nombrePJ);
                    plumaArtefacto.setSeleccionDatoSpiner(position);
                    plumaArtefacto.setNombreArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto());
                    plumaArtefacto.setRecursoArtefacto(R.drawable.pluma_erudita);
                    datosProcesosSqlite.validarUInsertUpdatePlumaArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto(), plumaArtefacto);
                    traerArtefactosGuardadosPluma();
                    anuncio.dismiss();
                }else if(position == 13){//////////////////////////////5*
                    plumaArtefacto.setNombrePJ(nombrePJ);
                    plumaArtefacto.setSeleccionDatoSpiner(position);
                    plumaArtefacto.setNombreArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto());
                    plumaArtefacto.setRecursoArtefacto(R.drawable.pluma_gladiador);
                    datosProcesosSqlite.validarUInsertUpdatePlumaArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto(), plumaArtefacto);
                    traerArtefactosGuardadosPluma();
                    anuncio.dismiss();
                }else if(position == 14){
                    plumaArtefacto.setNombrePJ(nombrePJ);
                    plumaArtefacto.setSeleccionDatoSpiner(position);
                    plumaArtefacto.setNombreArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto());
                    plumaArtefacto.setRecursoArtefacto(R.drawable.pluma_doncella);
                    datosProcesosSqlite.validarUInsertUpdatePlumaArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto(), plumaArtefacto);
                    traerArtefactosGuardadosPluma();
                    anuncio.dismiss();
                }else if(position == 15){
                    plumaArtefacto.setNombrePJ(nombrePJ);
                    plumaArtefacto.setSeleccionDatoSpiner(position);
                    plumaArtefacto.setNombreArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto());
                    plumaArtefacto.setRecursoArtefacto(R.drawable.pluma_nobleza);
                    datosProcesosSqlite.validarUInsertUpdatePlumaArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto(), plumaArtefacto);
                    traerArtefactosGuardadosPluma();
                    anuncio.dismiss();
                }else if(position == 16){
                    plumaArtefacto.setNombrePJ(nombrePJ);
                    plumaArtefacto.setSeleccionDatoSpiner(position);
                    plumaArtefacto.setNombreArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto());
                    plumaArtefacto.setRecursoArtefacto(R.drawable.pluma_sangui);
                    datosProcesosSqlite.validarUInsertUpdatePlumaArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto(), plumaArtefacto);
                    traerArtefactosGuardadosPluma();
                    anuncio.dismiss();
                }else if(position == 17){
                    plumaArtefacto.setNombrePJ(nombrePJ);
                    plumaArtefacto.setSeleccionDatoSpiner(position);
                    plumaArtefacto.setNombreArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto());
                    plumaArtefacto.setRecursoArtefacto(R.drawable.pluma_errante);
                    datosProcesosSqlite.validarUInsertUpdatePlumaArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto(), plumaArtefacto);
                    traerArtefactosGuardadosPluma();
                    anuncio.dismiss();
                }else if(position == 18){
                    plumaArtefacto.setNombrePJ(nombrePJ);
                    plumaArtefacto.setSeleccionDatoSpiner(position);
                    plumaArtefacto.setNombreArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto());
                    plumaArtefacto.setRecursoArtefacto(R.drawable.pluma_esmeralda);
                    datosProcesosSqlite.validarUInsertUpdatePlumaArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto(), plumaArtefacto);
                    traerArtefactosGuardadosPluma();
                    anuncio.dismiss();
                }else if(position == 19){
                    plumaArtefacto.setNombrePJ(nombrePJ);
                    plumaArtefacto.setSeleccionDatoSpiner(position);
                    plumaArtefacto.setNombreArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto());
                    plumaArtefacto.setRecursoArtefacto(R.drawable.pluma_furiatrueno);
                    datosProcesosSqlite.validarUInsertUpdatePlumaArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto(), plumaArtefacto);
                    traerArtefactosGuardadosPluma();
                    anuncio.dismiss();
                }else if(position == 20){
                    plumaArtefacto.setNombrePJ(nombrePJ);
                    plumaArtefacto.setSeleccionDatoSpiner(position);
                    plumaArtefacto.setNombreArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto());
                    plumaArtefacto.setRecursoArtefacto(R.drawable.pluma_domtrueno);
                    datosProcesosSqlite.validarUInsertUpdatePlumaArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto(), plumaArtefacto);
                    traerArtefactosGuardadosPluma();
                    anuncio.dismiss();
                }else if(position == 21){
                    plumaArtefacto.setNombrePJ(nombrePJ);
                    plumaArtefacto.setSeleccionDatoSpiner(position);
                    plumaArtefacto.setNombreArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto());
                    plumaArtefacto.setRecursoArtefacto(R.drawable.pluma_bruja);
                    datosProcesosSqlite.validarUInsertUpdatePlumaArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto(), plumaArtefacto);
                    traerArtefactosGuardadosPluma();
                    anuncio.dismiss();
                }else if(position == 22){
                    plumaArtefacto.setNombrePJ(nombrePJ);
                    plumaArtefacto.setSeleccionDatoSpiner(position);
                    plumaArtefacto.setNombreArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto());
                    plumaArtefacto.setRecursoArtefacto(R.drawable.pluma_corredor);
                    datosProcesosSqlite.validarUInsertUpdatePlumaArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto(), plumaArtefacto);
                    traerArtefactosGuardadosPluma();
                    anuncio.dismiss();
                }else if(position == 23){
                    plumaArtefacto.setNombrePJ(nombrePJ);
                    plumaArtefacto.setSeleccionDatoSpiner(position);
                    plumaArtefacto.setNombreArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto());
                    plumaArtefacto.setRecursoArtefacto(R.drawable.pluma_petra);
                    datosProcesosSqlite.validarUInsertUpdatePlumaArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto(), plumaArtefacto);
                    traerArtefactosGuardadosPluma();
                    anuncio.dismiss();
                }else if(position == 24){
                    plumaArtefacto.setNombrePJ(nombrePJ);
                    plumaArtefacto.setSeleccionDatoSpiner(position);
                    plumaArtefacto.setNombreArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto());
                    plumaArtefacto.setRecursoArtefacto(R.drawable.pluma_meteoro);
                    datosProcesosSqlite.validarUInsertUpdatePlumaArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto(), plumaArtefacto);
                    traerArtefactosGuardadosPluma();
                    anuncio.dismiss();
                }else if(position == 25){
                    plumaArtefacto.setNombrePJ(nombrePJ);
                    plumaArtefacto.setSeleccionDatoSpiner(position);
                    plumaArtefacto.setNombreArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto());
                    plumaArtefacto.setRecursoArtefacto(R.drawable.pluma_nomada);
                    datosProcesosSqlite.validarUInsertUpdatePlumaArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto(), plumaArtefacto);
                    traerArtefactosGuardadosPluma();
                    anuncio.dismiss();
                }else if(position == 26){
                    plumaArtefacto.setNombrePJ(nombrePJ);
                    plumaArtefacto.setSeleccionDatoSpiner(position);
                    plumaArtefacto.setNombreArtefacto(plumaArtefactoArrayList.get(position).getNombreArtefacto());
                    plumaArtefacto.setRecursoArtefacto(R.drawable.pluma_profundidades);
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

        //3*
        relojArtefactoArrayList.add(new RelojArtefacto(0, "Reloj del Afortunado"));
        relojArtefactoArrayList.add(new RelojArtefacto(1, "Reloj del Aventurero"));
        relojArtefactoArrayList.add(new RelojArtefacto(2, "Reloj Curativo"));

        //4*
        relojArtefactoArrayList.add(new RelojArtefacto(3, "Reloj del Instructor"));
        relojArtefactoArrayList.add(new RelojArtefacto(4, "Reloj del Berseker"));
        relojArtefactoArrayList.add(new RelojArtefacto(5, "Reloj Exiliado"));
        relojArtefactoArrayList.add(new RelojArtefacto(6, "Reloj del Viajero"));
        relojArtefactoArrayList.add(new RelojArtefacto(7, "Reloj del Marcial"));
        relojArtefactoArrayList.add(new RelojArtefacto(8, "Reloj Guardian"));
        relojArtefactoArrayList.add(new RelojArtefacto(9, "Reloj del Milagro"));
        relojArtefactoArrayList.add(new RelojArtefacto(10, "Reloj del Guerrero"));
        relojArtefactoArrayList.add(new RelojArtefacto(11, "Reloj Jugadora"));
        relojArtefactoArrayList.add(new RelojArtefacto(12, "Reloj del Erudita"));

        //5*
        relojArtefactoArrayList.add(new RelojArtefacto(13, "Reloj del Gladiador"));
        relojArtefactoArrayList.add(new RelojArtefacto(14, "Reloj Doncella"));
        relojArtefactoArrayList.add(new RelojArtefacto(15, "Reloj del Nobleza"));
        relojArtefactoArrayList.add(new RelojArtefacto(16, "Reloj del Sanguinaria"));
        relojArtefactoArrayList.add(new RelojArtefacto(17, "Reloj Errante"));
        relojArtefactoArrayList.add(new RelojArtefacto(18, "Reloj del Esmeralda"));
        relojArtefactoArrayList.add(new RelojArtefacto(19, "Reloj del Furia"));
        relojArtefactoArrayList.add(new RelojArtefacto(20, "Reloj Domadora"));
        relojArtefactoArrayList.add(new RelojArtefacto(21, "Reloj del Bruja"));
        relojArtefactoArrayList.add(new RelojArtefacto(22, "Reloj del Corredor"));
        relojArtefactoArrayList.add(new RelojArtefacto(23, "Reloj Petra"));
        relojArtefactoArrayList.add(new RelojArtefacto(24, "Reloj del Meteoro"));
        relojArtefactoArrayList.add(new RelojArtefacto(25, "Reloj del Nomada"));
        relojArtefactoArrayList.add(new RelojArtefacto(26, "Reloj Profundidades"));

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
                }else if(position == 3){/////////////////////////////////4*
                    relojArtefacto.setNombrePJ(nombrePJ);
                    relojArtefacto.setSeleccionDatoSpiner(position);
                    relojArtefacto.setNombreArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto());
                    relojArtefacto.setRecursoArtefacto(R.drawable.reloj_instructor);
                    datosProcesosSqlite.validarUInsertUpdateRelojArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto(), relojArtefacto);
                    traerArtefactosGuardadosReloj();
                    anuncio.dismiss();
                }else if(position == 4){
                    relojArtefacto.setNombrePJ(nombrePJ);
                    relojArtefacto.setSeleccionDatoSpiner(position);
                    relojArtefacto.setNombreArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto());
                    relojArtefacto.setRecursoArtefacto(R.drawable.reloj_berseker);
                    datosProcesosSqlite.validarUInsertUpdateRelojArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto(), relojArtefacto);
                    traerArtefactosGuardadosReloj();
                    anuncio.dismiss();
                }else if(position == 5){
                    relojArtefacto.setNombrePJ(nombrePJ);
                    relojArtefacto.setSeleccionDatoSpiner(position);
                    relojArtefacto.setNombreArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto());
                    relojArtefacto.setRecursoArtefacto(R.drawable.reloj_exiliado);
                    datosProcesosSqlite.validarUInsertUpdateRelojArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto(), relojArtefacto);
                    traerArtefactosGuardadosReloj();
                    anuncio.dismiss();
                }else if(position == 6){
                    relojArtefacto.setNombrePJ(nombrePJ);
                    relojArtefacto.setSeleccionDatoSpiner(position);
                    relojArtefacto.setNombreArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto());
                    relojArtefacto.setRecursoArtefacto(R.drawable.reloj_viajero);
                    datosProcesosSqlite.validarUInsertUpdateRelojArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto(), relojArtefacto);
                    traerArtefactosGuardadosReloj();
                    anuncio.dismiss();
                }else if(position == 7){
                    relojArtefacto.setNombrePJ(nombrePJ);
                    relojArtefacto.setSeleccionDatoSpiner(position);
                    relojArtefacto.setNombreArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto());
                    relojArtefacto.setRecursoArtefacto(R.drawable.reloj_marcial);
                    datosProcesosSqlite.validarUInsertUpdateRelojArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto(), relojArtefacto);
                    traerArtefactosGuardadosReloj();
                    anuncio.dismiss();
                }else if(position == 8){
                    relojArtefacto.setNombrePJ(nombrePJ);
                    relojArtefacto.setSeleccionDatoSpiner(position);
                    relojArtefacto.setNombreArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto());
                    relojArtefacto.setRecursoArtefacto(R.drawable.reloj_guardian);
                    datosProcesosSqlite.validarUInsertUpdateRelojArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto(), relojArtefacto);
                    traerArtefactosGuardadosReloj();
                    anuncio.dismiss();
                }else if(position == 9){
                    relojArtefacto.setNombrePJ(nombrePJ);
                    relojArtefacto.setSeleccionDatoSpiner(position);
                    relojArtefacto.setNombreArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto());
                    relojArtefacto.setRecursoArtefacto(R.drawable.reloj_milagro);
                    datosProcesosSqlite.validarUInsertUpdateRelojArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto(), relojArtefacto);
                    traerArtefactosGuardadosReloj();
                    anuncio.dismiss();
                }else if(position == 10){
                    relojArtefacto.setNombrePJ(nombrePJ);
                    relojArtefacto.setSeleccionDatoSpiner(position);
                    relojArtefacto.setNombreArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto());
                    relojArtefacto.setRecursoArtefacto(R.drawable.reloj_guerrero);
                    datosProcesosSqlite.validarUInsertUpdateRelojArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto(), relojArtefacto);
                    traerArtefactosGuardadosReloj();
                    anuncio.dismiss();
                }else if(position == 11){
                    relojArtefacto.setNombrePJ(nombrePJ);
                    relojArtefacto.setSeleccionDatoSpiner(position);
                    relojArtefacto.setNombreArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto());
                    relojArtefacto.setRecursoArtefacto(R.drawable.reloja_jugadora);
                    datosProcesosSqlite.validarUInsertUpdateRelojArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto(), relojArtefacto);
                    traerArtefactosGuardadosReloj();
                    anuncio.dismiss();
                }else if(position == 12){
                    relojArtefacto.setNombrePJ(nombrePJ);
                    relojArtefacto.setSeleccionDatoSpiner(position);
                    relojArtefacto.setNombreArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto());
                    relojArtefacto.setRecursoArtefacto(R.drawable.reloj_erudita);
                    datosProcesosSqlite.validarUInsertUpdateRelojArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto(), relojArtefacto);
                    traerArtefactosGuardadosReloj();
                    anuncio.dismiss();
                }else if(position == 13){/////////////////////////////////5*
                    relojArtefacto.setNombrePJ(nombrePJ);
                    relojArtefacto.setSeleccionDatoSpiner(position);
                    relojArtefacto.setNombreArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto());
                    relojArtefacto.setRecursoArtefacto(R.drawable.reloj_gladiador);
                    datosProcesosSqlite.validarUInsertUpdateRelojArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto(), relojArtefacto);
                    traerArtefactosGuardadosReloj();
                    anuncio.dismiss();
                }else if(position == 14){
                    relojArtefacto.setNombrePJ(nombrePJ);
                    relojArtefacto.setSeleccionDatoSpiner(position);
                    relojArtefacto.setNombreArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto());
                    relojArtefacto.setRecursoArtefacto(R.drawable.reloj_doncella);
                    datosProcesosSqlite.validarUInsertUpdateRelojArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto(), relojArtefacto);
                    traerArtefactosGuardadosReloj();
                    anuncio.dismiss();
                }else if(position == 15){
                    relojArtefacto.setNombrePJ(nombrePJ);
                    relojArtefacto.setSeleccionDatoSpiner(position);
                    relojArtefacto.setNombreArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto());
                    relojArtefacto.setRecursoArtefacto(R.drawable.reloj_nobleza);
                    datosProcesosSqlite.validarUInsertUpdateRelojArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto(), relojArtefacto);
                    traerArtefactosGuardadosReloj();
                    anuncio.dismiss();
                }else if(position == 16){
                    relojArtefacto.setNombrePJ(nombrePJ);
                    relojArtefacto.setSeleccionDatoSpiner(position);
                    relojArtefacto.setNombreArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto());
                    relojArtefacto.setRecursoArtefacto(R.drawable.reloj_sangui);
                    datosProcesosSqlite.validarUInsertUpdateRelojArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto(), relojArtefacto);
                    traerArtefactosGuardadosReloj();
                    anuncio.dismiss();
                }else if(position == 17){
                    relojArtefacto.setNombrePJ(nombrePJ);
                    relojArtefacto.setSeleccionDatoSpiner(position);
                    relojArtefacto.setNombreArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto());
                    relojArtefacto.setRecursoArtefacto(R.drawable.reloj_errante);
                    datosProcesosSqlite.validarUInsertUpdateRelojArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto(), relojArtefacto);
                    traerArtefactosGuardadosReloj();
                    anuncio.dismiss();
                }else if(position == 18){
                    relojArtefacto.setNombrePJ(nombrePJ);
                    relojArtefacto.setSeleccionDatoSpiner(position);
                    relojArtefacto.setNombreArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto());
                    relojArtefacto.setRecursoArtefacto(R.drawable.reloj_esmeralda);
                    datosProcesosSqlite.validarUInsertUpdateRelojArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto(), relojArtefacto);
                    traerArtefactosGuardadosReloj();
                    anuncio.dismiss();
                }else if(position == 19){
                    relojArtefacto.setNombrePJ(nombrePJ);
                    relojArtefacto.setSeleccionDatoSpiner(position);
                    relojArtefacto.setNombreArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto());
                    relojArtefacto.setRecursoArtefacto(R.drawable.reloj_furiatrueno);
                    datosProcesosSqlite.validarUInsertUpdateRelojArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto(), relojArtefacto);
                    traerArtefactosGuardadosReloj();
                    anuncio.dismiss();
                }else if(position == 20){
                    relojArtefacto.setNombrePJ(nombrePJ);
                    relojArtefacto.setSeleccionDatoSpiner(position);
                    relojArtefacto.setNombreArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto());
                    relojArtefacto.setRecursoArtefacto(R.drawable.reloj_domtrueno);
                    datosProcesosSqlite.validarUInsertUpdateRelojArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto(), relojArtefacto);
                    traerArtefactosGuardadosReloj();
                    anuncio.dismiss();
                }else if(position == 21){
                    relojArtefacto.setNombrePJ(nombrePJ);
                    relojArtefacto.setSeleccionDatoSpiner(position);
                    relojArtefacto.setNombreArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto());
                    relojArtefacto.setRecursoArtefacto(R.drawable.reloj_bruja);
                    datosProcesosSqlite.validarUInsertUpdateRelojArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto(), relojArtefacto);
                    traerArtefactosGuardadosReloj();
                    anuncio.dismiss();
                }else if(position == 22){
                    relojArtefacto.setNombrePJ(nombrePJ);
                    relojArtefacto.setSeleccionDatoSpiner(position);
                    relojArtefacto.setNombreArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto());
                    relojArtefacto.setRecursoArtefacto(R.drawable.reloj_corredor);
                    datosProcesosSqlite.validarUInsertUpdateRelojArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto(), relojArtefacto);
                    traerArtefactosGuardadosReloj();
                    anuncio.dismiss();
                }else if(position == 23){
                    relojArtefacto.setNombrePJ(nombrePJ);
                    relojArtefacto.setSeleccionDatoSpiner(position);
                    relojArtefacto.setNombreArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto());
                    relojArtefacto.setRecursoArtefacto(R.drawable.reloj_petra);
                    datosProcesosSqlite.validarUInsertUpdateRelojArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto(), relojArtefacto);
                    traerArtefactosGuardadosReloj();
                    anuncio.dismiss();
                }else if(position == 24){
                    relojArtefacto.setNombrePJ(nombrePJ);
                    relojArtefacto.setSeleccionDatoSpiner(position);
                    relojArtefacto.setNombreArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto());
                    relojArtefacto.setRecursoArtefacto(R.drawable.reloj_meteoro);
                    datosProcesosSqlite.validarUInsertUpdateRelojArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto(), relojArtefacto);
                    traerArtefactosGuardadosReloj();
                    anuncio.dismiss();
                }else if(position == 25){
                    relojArtefacto.setNombrePJ(nombrePJ);
                    relojArtefacto.setSeleccionDatoSpiner(position);
                    relojArtefacto.setNombreArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto());
                    relojArtefacto.setRecursoArtefacto(R.drawable.reloj_nomada);
                    datosProcesosSqlite.validarUInsertUpdateRelojArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto(), relojArtefacto);
                    traerArtefactosGuardadosReloj();
                    anuncio.dismiss();
                }else if(position == 26){
                    relojArtefacto.setNombrePJ(nombrePJ);
                    relojArtefacto.setSeleccionDatoSpiner(position);
                    relojArtefacto.setNombreArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto());
                    relojArtefacto.setRecursoArtefacto(R.drawable.reloj_profundidades);
                    datosProcesosSqlite.validarUInsertUpdateRelojArtefacto(relojArtefactoArrayList.get(position).getNombreArtefacto(), relojArtefacto);
                    traerArtefactosGuardadosReloj();
                    anuncio.dismiss();
                }
            });
        }catch (Exception exception){
            Log.d("AlertArtefactosReloj", "ERROR ALERT GUARDAR: " + exception.getMessage());
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
                Log.d("traerArtefactosReloj", "traerArtefactosGuardados: "+ relojArtefactoArrayList.get(i).getNombreArtefacto());
            }
        }
    }

    private void alertArtefactosCopa(){
        copaArtefactoArrayList = new ArrayList<>();
        copaArtefactoArrayList.add(new CopaArtefacto(0, "Copa del Afortunado"));
        copaArtefactoArrayList.add(new CopaArtefacto(1, "Copa del Aventurero"));
        copaArtefactoArrayList.add(new CopaArtefacto(2, "Copa Curativo"));
        copaArtefactoArrayList.add(new CopaArtefacto(3, "Copa del Instructor"));
        copaArtefactoArrayList.add(new CopaArtefacto(4, "Copa del Berseker"));
        copaArtefactoArrayList.add(new CopaArtefacto(5, "Copa Exiliado"));
        copaArtefactoArrayList.add(new CopaArtefacto(6, "Copa del Viajero"));
        copaArtefactoArrayList.add(new CopaArtefacto(7, "Copa del Marcial"));
        copaArtefactoArrayList.add(new CopaArtefacto(8, "Copa Guardian"));
        copaArtefactoArrayList.add(new CopaArtefacto(9, "Copa del Milagro"));
        copaArtefactoArrayList.add(new CopaArtefacto(10, "Copa del Guerrero"));
        copaArtefactoArrayList.add(new CopaArtefacto(11, "Copa Jugadora"));
        copaArtefactoArrayList.add(new CopaArtefacto(12, "Copa del Erudita"));
        copaArtefactoArrayList.add(new CopaArtefacto(13, "Copa del Gladiador"));
        copaArtefactoArrayList.add(new CopaArtefacto(14, "Copa Doncella"));
        copaArtefactoArrayList.add(new CopaArtefacto(15, "Copa del Nobleza"));
        copaArtefactoArrayList.add(new CopaArtefacto(16, "Copa del Sanguinaria"));
        copaArtefactoArrayList.add(new CopaArtefacto(17, "Copa Errante"));
        copaArtefactoArrayList.add(new CopaArtefacto(18, "Copa del Esmeralda"));
        copaArtefactoArrayList.add(new CopaArtefacto(19, "Copa del Furia"));
        copaArtefactoArrayList.add(new CopaArtefacto(20, "Copa Domadora"));
        copaArtefactoArrayList.add(new CopaArtefacto(21, "Copa del Bruja"));
        copaArtefactoArrayList.add(new CopaArtefacto(22, "Copa del Corredor"));
        copaArtefactoArrayList.add(new CopaArtefacto(23, "Copa Petra"));
        copaArtefactoArrayList.add(new CopaArtefacto(24, "Copa del Meteoro"));
        copaArtefactoArrayList.add(new CopaArtefacto(25, "Copa del Nomada"));
        copaArtefactoArrayList.add(new CopaArtefacto(26, "Copa Profundidades"));

        DatosProcesosSqlite datosProcesosSqlite = new DatosProcesosSqlite(this);
        CopaArtefacto copaArtefacto = new CopaArtefacto();
        try{
            LayoutInflater layoutInflater = LayoutInflater.from(MisBuilds.this);
            AlertDialog anuncio = new AlertDialog.Builder(this).create();
            final View view = layoutInflater.inflate(R.layout.alert_artefactos_listview, null);
            final ListView lvAlertArtefactos = view.findViewById(R.id.lvAlertArtefactos);
            anuncio.setView(view);
            anuncio.show();
            lvAlertArtefactos.setAdapter(new AdaptadorListViewCopa(MisBuilds.this, copaArtefactoArrayList));
            lvAlertArtefactos.setOnItemClickListener((parent, view1, position, id) -> {
                if(position == 0){
                    copaArtefacto.setNombrePJ(nombrePJ);
                    copaArtefacto.setSeleccionDatoSpiner(position);
                    copaArtefacto.setNombreArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto());
                    copaArtefacto.setRecursoArtefacto(R.drawable.copa_afortunado);
                    datosProcesosSqlite.validarUInsertUpdateCopaArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto(), copaArtefacto);
                    traerArtefactosGuardadosCopa();
                    anuncio.dismiss();
                }else if(position == 1){
                    copaArtefacto.setNombrePJ(nombrePJ);
                    copaArtefacto.setSeleccionDatoSpiner(position);
                    copaArtefacto.setNombreArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto());
                    copaArtefacto.setRecursoArtefacto(R.drawable.copa_aventurero);
                    datosProcesosSqlite.validarUInsertUpdateCopaArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto(), copaArtefacto);
                    traerArtefactosGuardadosCopa();
                    anuncio.dismiss();
                }else if(position == 2){
                    copaArtefacto.setNombrePJ(nombrePJ);
                    copaArtefacto.setSeleccionDatoSpiner(position);
                    copaArtefacto.setNombreArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto());
                    copaArtefacto.setRecursoArtefacto(R.drawable.copa_curativa);
                    datosProcesosSqlite.validarUInsertUpdateCopaArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto(), copaArtefacto);
                    traerArtefactosGuardadosCopa();
                    anuncio.dismiss();
                }else if(position == 3){
                    copaArtefacto.setNombrePJ(nombrePJ);
                    copaArtefacto.setSeleccionDatoSpiner(position);
                    copaArtefacto.setNombreArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto());
                    copaArtefacto.setRecursoArtefacto(R.drawable.copa_instructor);
                    datosProcesosSqlite.validarUInsertUpdateCopaArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto(), copaArtefacto);
                    traerArtefactosGuardadosCopa();
                    anuncio.dismiss();
                }else if(position == 4){
                    copaArtefacto.setNombrePJ(nombrePJ);
                    copaArtefacto.setSeleccionDatoSpiner(position);
                    copaArtefacto.setNombreArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto());
                    copaArtefacto.setRecursoArtefacto(R.drawable.copa_berseker);
                    datosProcesosSqlite.validarUInsertUpdateCopaArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto(), copaArtefacto);
                    traerArtefactosGuardadosCopa();
                    anuncio.dismiss();
                }else if(position == 5){
                    copaArtefacto.setNombrePJ(nombrePJ);
                    copaArtefacto.setSeleccionDatoSpiner(position);
                    copaArtefacto.setNombreArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto());
                    copaArtefacto.setRecursoArtefacto(R.drawable.copa_exiliado);
                    datosProcesosSqlite.validarUInsertUpdateCopaArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto(), copaArtefacto);
                    traerArtefactosGuardadosCopa();
                    anuncio.dismiss();
                }else if(position == 6){
                    copaArtefacto.setNombrePJ(nombrePJ);
                    copaArtefacto.setSeleccionDatoSpiner(position);
                    copaArtefacto.setNombreArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto());
                    copaArtefacto.setRecursoArtefacto(R.drawable.copa_viajero);
                    datosProcesosSqlite.validarUInsertUpdateCopaArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto(), copaArtefacto);
                    traerArtefactosGuardadosCopa();
                    anuncio.dismiss();
                }else if(position == 7){
                    copaArtefacto.setNombrePJ(nombrePJ);
                    copaArtefacto.setSeleccionDatoSpiner(position);
                    copaArtefacto.setNombreArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto());
                    copaArtefacto.setRecursoArtefacto(R.drawable.copa_marcial);
                    datosProcesosSqlite.validarUInsertUpdateCopaArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto(), copaArtefacto);
                    traerArtefactosGuardadosCopa();
                    anuncio.dismiss();
                }else if(position == 8){
                    copaArtefacto.setNombrePJ(nombrePJ);
                    copaArtefacto.setSeleccionDatoSpiner(position);
                    copaArtefacto.setNombreArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto());
                    copaArtefacto.setRecursoArtefacto(R.drawable.copa_guardian);
                    datosProcesosSqlite.validarUInsertUpdateCopaArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto(), copaArtefacto);
                    traerArtefactosGuardadosCopa();
                    anuncio.dismiss();
                }else if(position == 9){
                    copaArtefacto.setNombrePJ(nombrePJ);
                    copaArtefacto.setSeleccionDatoSpiner(position);
                    copaArtefacto.setNombreArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto());
                    copaArtefacto.setRecursoArtefacto(R.drawable.copa_milagro);
                    datosProcesosSqlite.validarUInsertUpdateCopaArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto(), copaArtefacto);
                    traerArtefactosGuardadosCopa();
                    anuncio.dismiss();
                }else if(position == 10){
                    copaArtefacto.setNombrePJ(nombrePJ);
                    copaArtefacto.setSeleccionDatoSpiner(position);
                    copaArtefacto.setNombreArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto());
                    copaArtefacto.setRecursoArtefacto(R.drawable.copa_guerrero);
                    datosProcesosSqlite.validarUInsertUpdateCopaArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto(), copaArtefacto);
                    traerArtefactosGuardadosCopa();
                    anuncio.dismiss();
                }else if(position == 11){
                    copaArtefacto.setNombrePJ(nombrePJ);
                    copaArtefacto.setSeleccionDatoSpiner(position);
                    copaArtefacto.setNombreArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto());
                    copaArtefacto.setRecursoArtefacto(R.drawable.copa_jugadora);
                    datosProcesosSqlite.validarUInsertUpdateCopaArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto(), copaArtefacto);
                    traerArtefactosGuardadosCopa();
                    anuncio.dismiss();
                }else if(position == 12){
                    copaArtefacto.setNombrePJ(nombrePJ);
                    copaArtefacto.setSeleccionDatoSpiner(position);
                    copaArtefacto.setNombreArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto());
                    copaArtefacto.setRecursoArtefacto(R.drawable.copa_erudita);
                    datosProcesosSqlite.validarUInsertUpdateCopaArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto(), copaArtefacto);
                    traerArtefactosGuardadosCopa();
                    anuncio.dismiss();
                }else if(position == 13){
                    copaArtefacto.setNombrePJ(nombrePJ);
                    copaArtefacto.setSeleccionDatoSpiner(position);
                    copaArtefacto.setNombreArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto());
                    copaArtefacto.setRecursoArtefacto(R.drawable.copa_gladiador);
                    datosProcesosSqlite.validarUInsertUpdateCopaArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto(), copaArtefacto);
                    traerArtefactosGuardadosCopa();
                    anuncio.dismiss();
                }else if(position == 14){
                    copaArtefacto.setNombrePJ(nombrePJ);
                    copaArtefacto.setSeleccionDatoSpiner(position);
                    copaArtefacto.setNombreArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto());
                    copaArtefacto.setRecursoArtefacto(R.drawable.copa_doncella);
                    datosProcesosSqlite.validarUInsertUpdateCopaArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto(), copaArtefacto);
                    traerArtefactosGuardadosCopa();
                    anuncio.dismiss();
                }else if(position == 15){
                    copaArtefacto.setNombrePJ(nombrePJ);
                    copaArtefacto.setSeleccionDatoSpiner(position);
                    copaArtefacto.setNombreArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto());
                    copaArtefacto.setRecursoArtefacto(R.drawable.copa_nobleza);
                    datosProcesosSqlite.validarUInsertUpdateCopaArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto(), copaArtefacto);
                    traerArtefactosGuardadosCopa();
                    anuncio.dismiss();
                }else if(position == 16){
                    copaArtefacto.setNombrePJ(nombrePJ);
                    copaArtefacto.setSeleccionDatoSpiner(position);
                    copaArtefacto.setNombreArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto());
                    copaArtefacto.setRecursoArtefacto(R.drawable.copa_sangui);
                    datosProcesosSqlite.validarUInsertUpdateCopaArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto(), copaArtefacto);
                    traerArtefactosGuardadosCopa();
                    anuncio.dismiss();
                }else if(position == 17){
                    copaArtefacto.setNombrePJ(nombrePJ);
                    copaArtefacto.setSeleccionDatoSpiner(position);
                    copaArtefacto.setNombreArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto());
                    copaArtefacto.setRecursoArtefacto(R.drawable.copa_errante);
                    datosProcesosSqlite.validarUInsertUpdateCopaArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto(), copaArtefacto);
                    traerArtefactosGuardadosCopa();
                    anuncio.dismiss();
                }else if(position == 18){
                    copaArtefacto.setNombrePJ(nombrePJ);
                    copaArtefacto.setSeleccionDatoSpiner(position);
                    copaArtefacto.setNombreArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto());
                    copaArtefacto.setRecursoArtefacto(R.drawable.copa_esmeralda);
                    datosProcesosSqlite.validarUInsertUpdateCopaArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto(), copaArtefacto);
                    traerArtefactosGuardadosCopa();
                    anuncio.dismiss();
                }else if(position == 19){
                    copaArtefacto.setNombrePJ(nombrePJ);
                    copaArtefacto.setSeleccionDatoSpiner(position);
                    copaArtefacto.setNombreArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto());
                    copaArtefacto.setRecursoArtefacto(R.drawable.copa_furiatrueno);
                    datosProcesosSqlite.validarUInsertUpdateCopaArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto(), copaArtefacto);
                    traerArtefactosGuardadosCopa();
                    anuncio.dismiss();
                }else if(position == 20){
                    copaArtefacto.setNombrePJ(nombrePJ);
                    copaArtefacto.setSeleccionDatoSpiner(position);
                    copaArtefacto.setNombreArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto());
                    copaArtefacto.setRecursoArtefacto(R.drawable.copa_domtrueno);
                    datosProcesosSqlite.validarUInsertUpdateCopaArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto(), copaArtefacto);
                    traerArtefactosGuardadosCopa();
                    anuncio.dismiss();
                }else if(position == 21){
                    copaArtefacto.setNombrePJ(nombrePJ);
                    copaArtefacto.setSeleccionDatoSpiner(position);
                    copaArtefacto.setNombreArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto());
                    copaArtefacto.setRecursoArtefacto(R.drawable.copa_bruja);
                    datosProcesosSqlite.validarUInsertUpdateCopaArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto(), copaArtefacto);
                    traerArtefactosGuardadosCopa();
                    anuncio.dismiss();
                }else if(position == 22){
                    copaArtefacto.setNombrePJ(nombrePJ);
                    copaArtefacto.setSeleccionDatoSpiner(position);
                    copaArtefacto.setNombreArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto());
                    copaArtefacto.setRecursoArtefacto(R.drawable.copa_corredor);
                    datosProcesosSqlite.validarUInsertUpdateCopaArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto(), copaArtefacto);
                    traerArtefactosGuardadosCopa();
                    anuncio.dismiss();
                }else if(position == 23){
                    copaArtefacto.setNombrePJ(nombrePJ);
                    copaArtefacto.setSeleccionDatoSpiner(position);
                    copaArtefacto.setNombreArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto());
                    copaArtefacto.setRecursoArtefacto(R.drawable.copa_petra);
                    datosProcesosSqlite.validarUInsertUpdateCopaArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto(), copaArtefacto);
                    traerArtefactosGuardadosCopa();
                    anuncio.dismiss();
                }else if(position == 24){
                    copaArtefacto.setNombrePJ(nombrePJ);
                    copaArtefacto.setSeleccionDatoSpiner(position);
                    copaArtefacto.setNombreArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto());
                    copaArtefacto.setRecursoArtefacto(R.drawable.copa_meteoro);
                    datosProcesosSqlite.validarUInsertUpdateCopaArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto(), copaArtefacto);
                    traerArtefactosGuardadosCopa();
                    anuncio.dismiss();
                }else if(position == 25){
                    copaArtefacto.setNombrePJ(nombrePJ);
                    copaArtefacto.setSeleccionDatoSpiner(position);
                    copaArtefacto.setNombreArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto());
                    copaArtefacto.setRecursoArtefacto(R.drawable.copa_nomada);
                    datosProcesosSqlite.validarUInsertUpdateCopaArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto(), copaArtefacto);
                    traerArtefactosGuardadosCopa();
                    anuncio.dismiss();
                }else if(position == 26){
                    copaArtefacto.setNombrePJ(nombrePJ);
                    copaArtefacto.setSeleccionDatoSpiner(position);
                    copaArtefacto.setNombreArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto());
                    copaArtefacto.setRecursoArtefacto(R.drawable.copa_profundidades);
                    datosProcesosSqlite.validarUInsertUpdateCopaArtefacto(copaArtefactoArrayList.get(position).getNombreArtefacto(), copaArtefacto);
                    traerArtefactosGuardadosCopa();
                    anuncio.dismiss();
                }
            });
        }catch (Exception exception){
            Log.d("AlertArtefactosCopa", "ERROR ALERT GUARDAR: " + exception.getMessage());
        }
    }

    private void traerArtefactosGuardadosCopa(){
        DatosProcesosSqlite datosProcesosSqlite = new DatosProcesosSqlite(this);
        copaArtefactoArrayList = datosProcesosSqlite.mostrarSeleccionCopaArtefacto(nombrePJ);
        if(copaArtefactoArrayList.size() <= 0){
            //nada
        }else{
            for(int i = 0; i < copaArtefactoArrayList.size(); i++){
                ivCopaArtefacto.setImageResource(copaArtefactoArrayList.get(i).getRecursoArtefacto());
                Log.d("traerArtefactosPluma", "traerArtefactosGuardados: "+ copaArtefactoArrayList.get(i).getNombreArtefacto());
            }
        }
    }

    private void alertArtefactosCorona(){
        coronaArtefactoArrayList = new ArrayList<>();
        coronaArtefactoArrayList.add(new CoronaArtefacto(0, "Corona del Afortunado"));
        coronaArtefactoArrayList.add(new CoronaArtefacto(1, "Corona del Aventurero"));
        coronaArtefactoArrayList.add(new CoronaArtefacto(2, "Corona Curativo"));
        coronaArtefactoArrayList.add(new CoronaArtefacto(3, "Corona del Instructor"));
        coronaArtefactoArrayList.add(new CoronaArtefacto(4, "Corona del Berseker"));
        coronaArtefactoArrayList.add(new CoronaArtefacto(5, "Corona Exiliado"));
        coronaArtefactoArrayList.add(new CoronaArtefacto(6, "Corona del Viajero"));
        coronaArtefactoArrayList.add(new CoronaArtefacto(7, "Corona del Marcial"));
        coronaArtefactoArrayList.add(new CoronaArtefacto(8, "Corona Guardian"));
        coronaArtefactoArrayList.add(new CoronaArtefacto(9, "Corona del Milagro"));
        coronaArtefactoArrayList.add(new CoronaArtefacto(10, "Corona del Guerrero"));
        coronaArtefactoArrayList.add(new CoronaArtefacto(11, "Corona Jugadora"));
        coronaArtefactoArrayList.add(new CoronaArtefacto(12, "Corona del Erudita"));
        coronaArtefactoArrayList.add(new CoronaArtefacto(13, "Corona del Gladiador"));
        coronaArtefactoArrayList.add(new CoronaArtefacto(14, "Corona Doncella"));
        coronaArtefactoArrayList.add(new CoronaArtefacto(15, "Corona del Nobleza"));
        coronaArtefactoArrayList.add(new CoronaArtefacto(16, "Corona del Sanguinaria"));
        coronaArtefactoArrayList.add(new CoronaArtefacto(17, "Corona Errante"));
        coronaArtefactoArrayList.add(new CoronaArtefacto(18, "Corona del Esmeralda"));
        coronaArtefactoArrayList.add(new CoronaArtefacto(19, "Corona del Furia"));
        coronaArtefactoArrayList.add(new CoronaArtefacto(20, "Corona Domadora"));
        coronaArtefactoArrayList.add(new CoronaArtefacto(21, "Corona del Bruja"));
        coronaArtefactoArrayList.add(new CoronaArtefacto(22, "Corona del Corredor"));
        coronaArtefactoArrayList.add(new CoronaArtefacto(23, "Corona Petra"));
        coronaArtefactoArrayList.add(new CoronaArtefacto(24, "Corona del Meteoro"));
        coronaArtefactoArrayList.add(new CoronaArtefacto(25, "Corona del Nomada"));
        coronaArtefactoArrayList.add(new CoronaArtefacto(26, "Corona Profundidades"));

        DatosProcesosSqlite datosProcesosSqlite = new DatosProcesosSqlite(this);
        CoronaArtefacto coronaArtefacto = new CoronaArtefacto();
        try{
            LayoutInflater layoutInflater = LayoutInflater.from(MisBuilds.this);
            AlertDialog anuncio = new AlertDialog.Builder(this).create();
            final View view = layoutInflater.inflate(R.layout.alert_artefactos_listview, null);
            final ListView lvAlertArtefactos = view.findViewById(R.id.lvAlertArtefactos);
            anuncio.setView(view);
            anuncio.show();
            lvAlertArtefactos.setAdapter(new AdaptadorListViewCorona(MisBuilds.this, coronaArtefactoArrayList));
            lvAlertArtefactos.setOnItemClickListener((parent, view1, position, id) -> {
                if(position == 0){
                    coronaArtefacto.setNombrePJ(nombrePJ);
                    coronaArtefacto.setSeleccionDatoSpiner(position);
                    coronaArtefacto.setNombreArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto());
                    coronaArtefacto.setRecursoArtefacto(R.drawable.corona_afortunado);
                    datosProcesosSqlite.validarUInsertUpdateCoronaArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto(), coronaArtefacto);
                    traerArtefactosGuardadosCorona();
                    anuncio.dismiss();
                }else if(position == 1){
                    coronaArtefacto.setNombrePJ(nombrePJ);
                    coronaArtefacto.setSeleccionDatoSpiner(position);
                    coronaArtefacto.setNombreArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto());
                    coronaArtefacto.setRecursoArtefacto(R.drawable.corona_aventurero);
                    datosProcesosSqlite.validarUInsertUpdateCoronaArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto(), coronaArtefacto);
                    traerArtefactosGuardadosCorona();
                    anuncio.dismiss();
                }else if(position == 2){
                    coronaArtefacto.setNombrePJ(nombrePJ);
                    coronaArtefacto.setSeleccionDatoSpiner(position);
                    coronaArtefacto.setNombreArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto());
                    coronaArtefacto.setRecursoArtefacto(R.drawable.corona_curativa);
                    datosProcesosSqlite.validarUInsertUpdateCoronaArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto(), coronaArtefacto);
                    traerArtefactosGuardadosCorona();
                    anuncio.dismiss();
                }else if(position == 3){
                    coronaArtefacto.setNombrePJ(nombrePJ);
                    coronaArtefacto.setSeleccionDatoSpiner(position);
                    coronaArtefacto.setNombreArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto());
                    coronaArtefacto.setRecursoArtefacto(R.drawable.corona_instructor);
                    datosProcesosSqlite.validarUInsertUpdateCoronaArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto(), coronaArtefacto);
                    traerArtefactosGuardadosCorona();
                    anuncio.dismiss();
                }else if(position == 4){
                    coronaArtefacto.setNombrePJ(nombrePJ);
                    coronaArtefacto.setSeleccionDatoSpiner(position);
                    coronaArtefacto.setNombreArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto());
                    coronaArtefacto.setRecursoArtefacto(R.drawable.corona_berseker);
                    datosProcesosSqlite.validarUInsertUpdateCoronaArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto(), coronaArtefacto);
                    traerArtefactosGuardadosCorona();
                    anuncio.dismiss();
                }else if(position == 5){
                    coronaArtefacto.setNombrePJ(nombrePJ);
                    coronaArtefacto.setSeleccionDatoSpiner(position);
                    coronaArtefacto.setNombreArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto());
                    coronaArtefacto.setRecursoArtefacto(R.drawable.corona_exiliado);
                    datosProcesosSqlite.validarUInsertUpdateCoronaArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto(), coronaArtefacto);
                    traerArtefactosGuardadosCorona();
                    anuncio.dismiss();
                }else if(position == 6){
                    coronaArtefacto.setNombrePJ(nombrePJ);
                    coronaArtefacto.setSeleccionDatoSpiner(position);
                    coronaArtefacto.setNombreArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto());
                    coronaArtefacto.setRecursoArtefacto(R.drawable.corona_viajero);
                    datosProcesosSqlite.validarUInsertUpdateCoronaArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto(), coronaArtefacto);
                    traerArtefactosGuardadosCorona();
                    anuncio.dismiss();
                }else if(position == 7){
                    coronaArtefacto.setNombrePJ(nombrePJ);
                    coronaArtefacto.setSeleccionDatoSpiner(position);
                    coronaArtefacto.setNombreArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto());
                    coronaArtefacto.setRecursoArtefacto(R.drawable.corona_marcial);
                    datosProcesosSqlite.validarUInsertUpdateCoronaArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto(), coronaArtefacto);
                    traerArtefactosGuardadosCorona();
                    anuncio.dismiss();
                }else if(position == 8){
                    coronaArtefacto.setNombrePJ(nombrePJ);
                    coronaArtefacto.setSeleccionDatoSpiner(position);
                    coronaArtefacto.setNombreArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto());
                    coronaArtefacto.setRecursoArtefacto(R.drawable.corona_guardian);
                    datosProcesosSqlite.validarUInsertUpdateCoronaArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto(), coronaArtefacto);
                    traerArtefactosGuardadosCorona();
                    anuncio.dismiss();
                }else if(position == 9){
                    coronaArtefacto.setNombrePJ(nombrePJ);
                    coronaArtefacto.setSeleccionDatoSpiner(position);
                    coronaArtefacto.setNombreArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto());
                    coronaArtefacto.setRecursoArtefacto(R.drawable.corona_milagro);
                    datosProcesosSqlite.validarUInsertUpdateCoronaArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto(), coronaArtefacto);
                    traerArtefactosGuardadosCorona();
                    anuncio.dismiss();
                }else if(position == 10){
                    coronaArtefacto.setNombrePJ(nombrePJ);
                    coronaArtefacto.setSeleccionDatoSpiner(position);
                    coronaArtefacto.setNombreArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto());
                    coronaArtefacto.setRecursoArtefacto(R.drawable.corona_guerrero);
                    datosProcesosSqlite.validarUInsertUpdateCoronaArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto(), coronaArtefacto);
                    traerArtefactosGuardadosCorona();
                    anuncio.dismiss();
                }else if(position == 11){
                    coronaArtefacto.setNombrePJ(nombrePJ);
                    coronaArtefacto.setSeleccionDatoSpiner(position);
                    coronaArtefacto.setNombreArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto());
                    coronaArtefacto.setRecursoArtefacto(R.drawable.corona_jugadora);
                    datosProcesosSqlite.validarUInsertUpdateCoronaArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto(), coronaArtefacto);
                    traerArtefactosGuardadosCorona();
                    anuncio.dismiss();
                }else if(position == 12){
                    coronaArtefacto.setNombrePJ(nombrePJ);
                    coronaArtefacto.setSeleccionDatoSpiner(position);
                    coronaArtefacto.setNombreArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto());
                    coronaArtefacto.setRecursoArtefacto(R.drawable.corona_erudita);
                    datosProcesosSqlite.validarUInsertUpdateCoronaArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto(), coronaArtefacto);
                    traerArtefactosGuardadosCorona();
                    anuncio.dismiss();
                }else if(position == 13){
                    coronaArtefacto.setNombrePJ(nombrePJ);
                    coronaArtefacto.setSeleccionDatoSpiner(position);
                    coronaArtefacto.setNombreArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto());
                    coronaArtefacto.setRecursoArtefacto(R.drawable.corona_gladiador);
                    datosProcesosSqlite.validarUInsertUpdateCoronaArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto(), coronaArtefacto);
                    traerArtefactosGuardadosCorona();
                    anuncio.dismiss();
                }else if(position == 14){
                    coronaArtefacto.setNombrePJ(nombrePJ);
                    coronaArtefacto.setSeleccionDatoSpiner(position);
                    coronaArtefacto.setNombreArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto());
                    coronaArtefacto.setRecursoArtefacto(R.drawable.corona_doncella);
                    datosProcesosSqlite.validarUInsertUpdateCoronaArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto(), coronaArtefacto);
                    traerArtefactosGuardadosCorona();
                    anuncio.dismiss();
                }else if(position == 15){
                    coronaArtefacto.setNombrePJ(nombrePJ);
                    coronaArtefacto.setSeleccionDatoSpiner(position);
                    coronaArtefacto.setNombreArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto());
                    coronaArtefacto.setRecursoArtefacto(R.drawable.corona_nobleza);
                    datosProcesosSqlite.validarUInsertUpdateCoronaArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto(), coronaArtefacto);
                    traerArtefactosGuardadosCorona();
                    anuncio.dismiss();
                }else if(position == 16){
                    coronaArtefacto.setNombrePJ(nombrePJ);
                    coronaArtefacto.setSeleccionDatoSpiner(position);
                    coronaArtefacto.setNombreArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto());
                    coronaArtefacto.setRecursoArtefacto(R.drawable.corona_sangui);
                    datosProcesosSqlite.validarUInsertUpdateCoronaArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto(), coronaArtefacto);
                    traerArtefactosGuardadosCorona();
                    anuncio.dismiss();
                }else if(position == 17){
                    coronaArtefacto.setNombrePJ(nombrePJ);
                    coronaArtefacto.setSeleccionDatoSpiner(position);
                    coronaArtefacto.setNombreArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto());
                    coronaArtefacto.setRecursoArtefacto(R.drawable.corona_errante);
                    datosProcesosSqlite.validarUInsertUpdateCoronaArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto(), coronaArtefacto);
                    traerArtefactosGuardadosCorona();
                    anuncio.dismiss();
                }else if(position == 18){
                    coronaArtefacto.setNombrePJ(nombrePJ);
                    coronaArtefacto.setSeleccionDatoSpiner(position);
                    coronaArtefacto.setNombreArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto());
                    coronaArtefacto.setRecursoArtefacto(R.drawable.corona_esmeralda);
                    datosProcesosSqlite.validarUInsertUpdateCoronaArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto(), coronaArtefacto);
                    traerArtefactosGuardadosCorona();
                    anuncio.dismiss();
                }else if(position == 19){
                    coronaArtefacto.setNombrePJ(nombrePJ);
                    coronaArtefacto.setSeleccionDatoSpiner(position);
                    coronaArtefacto.setNombreArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto());
                    coronaArtefacto.setRecursoArtefacto(R.drawable.corona_furiatrueno);
                    datosProcesosSqlite.validarUInsertUpdateCoronaArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto(), coronaArtefacto);
                    traerArtefactosGuardadosCorona();
                    anuncio.dismiss();
                }else if(position == 20){
                    coronaArtefacto.setNombrePJ(nombrePJ);
                    coronaArtefacto.setSeleccionDatoSpiner(position);
                    coronaArtefacto.setNombreArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto());
                    coronaArtefacto.setRecursoArtefacto(R.drawable.corona_domtrueno);
                    datosProcesosSqlite.validarUInsertUpdateCoronaArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto(), coronaArtefacto);
                    traerArtefactosGuardadosCorona();
                    anuncio.dismiss();
                }else if(position == 21){
                    coronaArtefacto.setNombrePJ(nombrePJ);
                    coronaArtefacto.setSeleccionDatoSpiner(position);
                    coronaArtefacto.setNombreArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto());
                    coronaArtefacto.setRecursoArtefacto(R.drawable.corona_bruja);
                    datosProcesosSqlite.validarUInsertUpdateCoronaArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto(), coronaArtefacto);
                    traerArtefactosGuardadosCorona();
                    anuncio.dismiss();
                }else if(position == 22){
                    coronaArtefacto.setNombrePJ(nombrePJ);
                    coronaArtefacto.setSeleccionDatoSpiner(position);
                    coronaArtefacto.setNombreArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto());
                    coronaArtefacto.setRecursoArtefacto(R.drawable.corona_corredor);
                    datosProcesosSqlite.validarUInsertUpdateCoronaArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto(), coronaArtefacto);
                    traerArtefactosGuardadosCorona();
                    anuncio.dismiss();
                }else if(position == 23){
                    coronaArtefacto.setNombrePJ(nombrePJ);
                    coronaArtefacto.setSeleccionDatoSpiner(position);
                    coronaArtefacto.setNombreArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto());
                    coronaArtefacto.setRecursoArtefacto(R.drawable.corona_petra);
                    datosProcesosSqlite.validarUInsertUpdateCoronaArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto(), coronaArtefacto);
                    traerArtefactosGuardadosCorona();
                    anuncio.dismiss();
                }else if(position == 24){
                    coronaArtefacto.setNombrePJ(nombrePJ);
                    coronaArtefacto.setSeleccionDatoSpiner(position);
                    coronaArtefacto.setNombreArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto());
                    coronaArtefacto.setRecursoArtefacto(R.drawable.corona_meteoro);
                    datosProcesosSqlite.validarUInsertUpdateCoronaArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto(), coronaArtefacto);
                    traerArtefactosGuardadosCorona();
                    anuncio.dismiss();
                }else if(position == 25){
                    coronaArtefacto.setNombrePJ(nombrePJ);
                    coronaArtefacto.setSeleccionDatoSpiner(position);
                    coronaArtefacto.setNombreArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto());
                    coronaArtefacto.setRecursoArtefacto(R.drawable.corona_nomada);
                    datosProcesosSqlite.validarUInsertUpdateCoronaArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto(), coronaArtefacto);
                    traerArtefactosGuardadosCorona();
                    anuncio.dismiss();
                }else if(position == 26){
                    coronaArtefacto.setNombrePJ(nombrePJ);
                    coronaArtefacto.setSeleccionDatoSpiner(position);
                    coronaArtefacto.setNombreArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto());
                    coronaArtefacto.setRecursoArtefacto(R.drawable.corona_profundidades);
                    datosProcesosSqlite.validarUInsertUpdateCoronaArtefacto(coronaArtefactoArrayList.get(position).getNombreArtefacto(), coronaArtefacto);
                    traerArtefactosGuardadosCorona();
                    anuncio.dismiss();
                }
            });
        }catch (Exception exception){
            Log.d("AlertArtefactosCorona", "ERROR ALERT GUARDAR: " + exception.getMessage());
        }
    }

    private void traerArtefactosGuardadosCorona(){
        DatosProcesosSqlite datosProcesosSqlite = new DatosProcesosSqlite(this);
        coronaArtefactoArrayList = datosProcesosSqlite.mostrarSeleccionCoronaArtefacto(nombrePJ);
        if(coronaArtefactoArrayList.size() <= 0){
            //nada
        }else{
            for(int i = 0; i < coronaArtefactoArrayList.size(); i++){
                ivCoronaArtefacto.setImageResource(coronaArtefactoArrayList.get(i).getRecursoArtefacto());
                Log.d("traerArtefactosCorona", "traerArtefactosGuardados: "+ coronaArtefactoArrayList.get(i).getNombreArtefacto());
            }
        }
    }

    private int traerValorChk(Context context){
        int valor;
        if(new PreferenciaSonidosEntrar(context).traerValorGuardado() == 0){
            valor = 0;
        }else{
            valor = 1;
        }
        return valor;
    }
}