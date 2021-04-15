package com.frabasoft.genshinimpactrecursos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileUtils;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.frabasoft.genshinimpactrecursos.Actividades.Builds;
import com.frabasoft.genshinimpactrecursos.Actividades.MisBuilds;
import com.frabasoft.genshinimpactrecursos.Actividades.RutaArtefactos;
import com.frabasoft.genshinimpactrecursos.SQLiteGenshin.Procesos.DatosProcesosSqlite;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.master.permissionhelper.PermissionHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.frabasoft.genshinimpactrecursos.SQLiteGenshin.NombreVersionSqlite.DB_NAME;

public class MainActivity extends AppCompatActivity {
    ImageView instagram, whatsapp;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        crearDirectorio();

        ejecutar();

        instagram = (ImageView) findViewById(R.id.instagram);
        whatsapp = (ImageView) findViewById(R.id.whatsapp);
        builds = (Button) findViewById(R.id.builds);
        artefactos = (Button) findViewById(R.id.artefactos);
        misBuilds = (Button) findViewById(R.id.misBuilds);
        hacerBKP = (Button)findViewById(R.id.hacerBKP);

        MobileAds.initialize(MainActivity.this, initializationStatus -> {
        });
        publicidad = (AdView) findViewById(R.id.banerMainActivity);
        AdRequest adRequest = new AdRequest.Builder().build();
        publicidad.loadAd(adRequest);

        instagram.setOnClickListener(v -> instagramActividad(MainActivity.this));
        whatsapp.setOnClickListener(v -> Toast.makeText(MainActivity.this, "Próximamente.", Toast.LENGTH_SHORT).show());
        builds.setOnClickListener(v -> buildActivity());
        misBuilds.setOnClickListener(v -> misBuildsActivity());
        artefactos.setOnClickListener(v -> artefactosActivity());
        hacerBKP.setOnClickListener(v -> importarBKP());
    }

    private void importarBKP() {
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
        File directorioApp = new File("/sdcard/Genshin Impact Recursos/Genshin Impact Datos/", DB_NAME);
        if (!directorioApp.exists()) {
            File rutaGenshin = new File("/sdcard/Genshin Impact Recursos/");
            rutaGenshin.mkdirs();
        }
        File rutaBDDatos = new File("/sdcard/Genshin Impact Recursos/Genshin Impact Datos/");
        if (!rutaBDDatos.exists()) {
            File rutaBDDatosCrear = new File("/sdcard/Genshin Impact Recursos/Genshin Impact Datos/");
            rutaBDDatosCrear.mkdirs();
        }
    }
}