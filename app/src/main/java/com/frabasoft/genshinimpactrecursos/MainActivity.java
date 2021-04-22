package com.frabasoft.genshinimpactrecursos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.frabasoft.genshinimpactrecursos.Actividades.Builds;
import com.frabasoft.genshinimpactrecursos.Actividades.MisBuilds;
import com.frabasoft.genshinimpactrecursos.Actividades.RutaArtefactos;
import com.frabasoft.genshinimpactrecursos.SQLiteGenshin.Procesos.DatosProcesosSqlite;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.master.permissionhelper.PermissionHelper;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.frabasoft.genshinimpactrecursos.SQLiteGenshin.NombreVersionSqlite.DB_NAME;

public class MainActivity extends AppCompatActivity {
    ImageView instagram;
    AdView publicidad;
    Button builds, artefactos, misBuilds, hacerBKP;
    private DatosProcesosSqlite datosProcesosSqlite;
    private String primerStringTxt = "Archivo de instalación: " +
            "\n-Se ha creado por primera vez el archivo. " +
            "\n-Instalación de Genshin Impact Recursos exitosa.";
    private String segundoStringTxt = "Esta aplicación fue creada para el uso y consumo de jugadores de Genshin Impact. " +
            "\n-Creada por un jugador para otros jugadores. " +
            "\n-La aplicación creará de manera automática un archivo .db en tu memoria, por favor no lo elimines o perderás tu BackUp personal.";
    PermissionHelper permissionHelper;
    private String nombreTXT = "Leer Importante Sobre Genshin Impact Recursos.txt";

    //para el ad de la descarga de la imagen
    private final static String TAG = "ERRORTAG";
    private static final String AD_UNIT_ID = "ca-app-pub-4467142756516555/7442029029";
    private InterstitialAd interstitialAd;

    //para los soniditos
    private MediaPlayer entrar;

    //para los tiempos del banner
    private String fechaActualBanner, horaActualBanner;
    private String fechaFinalBanner = "27/04/2021 17:00:00";
    private long tiempoInicial;
    private CountDownTimer conteoRegresivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        entrar = MediaPlayer.create(getApplicationContext(), R.raw.entrar);

        anuncio();
        crearDirectorio();
        loadAd();

        ejecutar();

        instagram = (ImageView) findViewById(R.id.instagram);
        builds = (Button) findViewById(R.id.builds);
        artefactos = (Button) findViewById(R.id.artefactos);
        misBuilds = (Button) findViewById(R.id.misBuilds);
        hacerBKP = (Button)findViewById(R.id.hacerBKP);

        MobileAds.initialize(MainActivity.this, initializationStatus -> {
        });
        publicidad = (AdView) findViewById(R.id.banerMainActivity);
        AdRequest adRequest = new AdRequest.Builder().build();
        publicidad.loadAd(adRequest);

        instagram.setOnClickListener(v -> {
            entrar.start();
            instagramActividad(MainActivity.this);
        });
        builds.setOnClickListener(v -> {
            entrar.start();
            buildActivity();
        });
        misBuilds.setOnClickListener(v -> {
            entrar.start();
            misBuildsActivity();
        });
        artefactos.setOnClickListener(v -> {
            entrar.start();
            artefactosActivity();
        });
        hacerBKP.setOnClickListener(v -> importarBKP());
    }

    private void importarBKP() {
        showInterstitial();
        try{
            DatosProcesosSqlite datosProcesosSqlite = new DatosProcesosSqlite(MainActivity.this);
            datosProcesosSqlite.importarBackUp();
            Toast.makeText(this, "¡Se ha importado con éxito tu BD, ahora puedes ver tus datos almacenados!", Toast.LENGTH_SHORT).show();
            Log.d("LOGImportBKP", "Try: ");
        }catch(Exception e){
            Toast.makeText(this, "¡Ha ocurrido un error al intentar importar tu BD!", Toast.LENGTH_SHORT).show();
            Log.d("LOGImportBKP", "Catch: " + e.getMessage());
        }
    }

    private void instagramActividad(Context contexto) {
        Uri uri = Uri.parse("https://www.instagram.com/_u/genshinbaoer/");
        Intent instagram = new Intent(Intent.ACTION_VIEW, uri);
        try {
            startActivity(instagram);
        } catch (ActivityNotFoundException activityNotFoundException) {
            Toast.makeText(contexto, "No dispones de la aplicación, se abrirá en el navegador.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.instagram.com/genshinbaoer/")));
        }
    }

    private void buildActivity() {
        Intent build = new Intent(MainActivity.this, Builds.class);
        startActivity(build);
    }

    private void misBuildsActivity() {
        Intent miBuild = new Intent(MainActivity.this, MisBuilds.class);
        startActivity(miBuild);
    }

    private void artefactosActivity() {
        Intent artefactos = new Intent(MainActivity.this, RutaArtefactos.class);
        startActivity(artefactos);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MainActivity.this.finish();
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

    private void crearFicheroTxt(String instalacion){
        File directorioArchivo = new File("/sdcard/Genshin Impact Recursos/Genshin Impact Datos/", nombreTXT);
        if(!directorioArchivo.exists()){
            datosProcesosSqlite = new DatosProcesosSqlite(MainActivity.this);
            datosProcesosSqlite.copiarArchivo(instalacion);
        }
    }

    private void ejecutar(){
        permissionHelper = new PermissionHelper(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        permissionHelper.request(new PermissionHelper.PermissionCallback() {
            @Override
            public void onPermissionGranted() {

                crearFicheroTxt(primerStringTxt + segundoStringTxt);
                Log.d("TAG", "onPermissionGranted() called");
            }

            @Override
            public void onIndividualPermissionGranted(String[] grantedPermission) {
                Log.d("TAG", "onIndividualPermissionGranted() called with: grantedPermission = [" + TextUtils.join(",",grantedPermission) + "]");
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

    //CREAR DIRECTORIO DE ARCHIVOS DE GUARDADO
    public void crearDirectorio(){
        File directorioApp = new File(Environment.getExternalStorageDirectory() + "/Genshin Impact Recursos/Genshin Impact Datos/", DB_NAME);
        if (!directorioApp.exists()) {
            File rutaGenshin = new File(Environment.getExternalStorageDirectory() + "/Genshin Impact Recursos/Genshin Impact Datos/");
            rutaGenshin.mkdirs();
        }
        File rutaBDDatos = new File("/sdcard/Genshin Impact Recursos/Genshin Impact Datos/");
        if (!rutaBDDatos.exists()) {
            File rutaBDDatosCrear = new File("/sdcard/Genshin Impact Recursos/Genshin Impact Datos/");
            rutaBDDatosCrear.mkdirs();
        }
    }

    public void loadAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(
                this,
                AD_UNIT_ID,
                adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        MainActivity.this.interstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");
                        interstitialAd.setFullScreenContentCallback(
                                new FullScreenContentCallback() {
                                    @Override
                                    public void onAdDismissedFullScreenContent() {
                                        // Called when fullscreen content is dismissed.
                                        // Make sure to set your reference to null so you don't
                                        // show it a second time.
                                        MainActivity.this.interstitialAd = null;
                                        Log.d("TAG", "The ad was dismissed.");
                                    }

                                    @Override
                                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                                        // Called when fullscreen content failed to show.
                                        // Make sure to set your reference to null so you don't
                                        // show it a second time.
                                        MainActivity.this.interstitialAd = null;
                                        Log.d("TAG", "The ad failed to show.");
                                    }

                                    @Override
                                    public void onAdShowedFullScreenContent() {
                                        // Called when fullscreen content is shown.
                                        Log.d("TAG", "The ad was shown.");
                                    }
                                });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.i(TAG, loadAdError.getMessage());
                        interstitialAd = null;

                        String error =
                                String.format(
                                        "domain: %s, code: %d, message: %s",
                                        loadAdError.getDomain(), loadAdError.getCode(), loadAdError.getMessage());
                        Log.d(TAG, "onAdFailedToLoad: " + error);
                    }
                });
    }

    private void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and restart the game.
        if (interstitialAd != null) {
            interstitialAd.show(this);
        } else {
            Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
        }
    }

    private void anuncio(){
        Calendar fechaActual = Calendar.getInstance();
        int mesActual = fechaActual.get(Calendar.MONTH) + 1;
        SimpleDateFormat sdfFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try{
            if(fechaActual.get(Calendar.DAY_OF_MONTH) < 10){
                fechaActualBanner = "0" + fechaActual.get(Calendar.DAY_OF_MONTH)
                        + "/" + mesActual
                        + "/" + fechaActual.get(Calendar.YEAR);
            }else if(mesActual < 10){
                fechaActualBanner = fechaActual.get(Calendar.DAY_OF_MONTH)
                        + "/0" + mesActual
                        + "/" + fechaActual.get(Calendar.YEAR);
            }else{
                fechaActualBanner = fechaActual.get(Calendar.DAY_OF_MONTH)
                        + "/" + mesActual
                        + "/" + fechaActual.get(Calendar.YEAR);
            }
            horaActualBanner = fechaActual.get(Calendar.HOUR_OF_DAY)
                    + ":" + fechaActual.get(Calendar.MINUTE)
                    + ":" + fechaActual.get(Calendar.SECOND);

            String resultadoFechaHoraSO = fechaActualBanner + " " + horaActualBanner;
            Date fechaResultadoString = sdfFecha.parse(resultadoFechaHoraSO);
            Date fechaFinalString = sdfFecha.parse(fechaFinalBanner);

            long resultado = fechaFinalString.getTime() - fechaResultadoString.getTime();
            long segundos =(resultado/1000)%60;
            long minutos=(resultado/(1000*60))%60;
            long horas=(resultado/(1000*60*60))%24;
            long dias=(resultado/(1000*60*60*24))%365;

            tiempoInicial = resultado;

            if(fechaResultadoString.before(fechaFinalString)) {
                Log.d("COMPARATIVA", "anuncio: " + fechaFinalString.after(fechaResultadoString));
                LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
                AlertDialog anuncio = new AlertDialog.Builder(this).create();
                final View view = layoutInflater.inflate(R.layout.alerta_inicio_app, null);
                final TextView tiempoRestante = view.findViewById(R.id.tiempoRestante);
                conteoRegresivo = new CountDownTimer(tiempoInicial, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        int dias = (int) ((millisUntilFinished / 1000) / 86400);
                        int horas = (int) (((millisUntilFinished / 1000)
                                - (dias * 86400)) / 3600);
                        int minutos = (int) (((millisUntilFinished / 1000)
                                - (dias * 86400) - (horas * 3600)) / 60);
                        int segundos = (int) ((millisUntilFinished / 1000) % 60);
                        String countDownText = String.format("Faltan %2d Dia(s) %2d Hr(s) %2d Min %2d Seg para que finalice el banner.", dias, horas, minutos, segundos);
                        tiempoRestante.setText(countDownText);
                    }

                    @Override
                    public void onFinish() {
                        tiempoRestante.setText(DateUtils.formatElapsedTime(0));
                    }
                }.start();
                final Button cerrar = view.findViewById(R.id.cerrarAnuncio);
                cerrar.setOnClickListener(v -> anuncio.dismiss());
                anuncio.setView(view);
                anuncio.show();
            }else{
                Toast.makeText(this, "El banner de Tartaglia, ¡Ha terminado! Pronto iniciará una nueva actualización. ¡No olvides descargarla!", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception exception){
            Log.d(TAG, "ERROR TIEMPO: " + exception.getMessage());
        }
    }
}