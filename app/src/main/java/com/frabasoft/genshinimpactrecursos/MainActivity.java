package com.frabasoft.genshinimpactrecursos;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
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
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.frabasoft.genshinimpactrecursos.Actividades.Builds;
import com.frabasoft.genshinimpactrecursos.Actividades.MisBuilds;
import com.frabasoft.genshinimpactrecursos.Actividades.RutaArtefactos;
import com.frabasoft.genshinimpactrecursos.Actividades.VistaPrevia;
import com.frabasoft.genshinimpactrecursos.SQLiteGenshin.Procesos.DatosProcesosSqlite;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.master.permissionhelper.PermissionHelper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ImageView instagram, whatsapp;
    AdView publicidad;
    Button builds, artefactos, misBuilds;
    private DatosProcesosSqlite datosProcesosSqlite;
    private String primerStringTxt = "Archivo de instalación: " +
            "\n-Se ha creado por primera vez el archivo. " +
            "\n-Instalación de Genshin Impact Recursos exitosa." +
            "\n" +
            "\n";
    PermissionHelper permissionHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ejecutar();
        if(permissionHelper.hasPermission()){
            crearFicheroTxt(primerStringTxt);
        }

        instagram = (ImageView) findViewById(R.id.instagram);
        whatsapp = (ImageView) findViewById(R.id.whatsapp);
        builds = (Button) findViewById(R.id.builds);
        artefactos = (Button) findViewById(R.id.artefactos);
        misBuilds = (Button) findViewById(R.id.misBuilds);

        MobileAds.initialize(MainActivity.this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        publicidad = (AdView) findViewById(R.id.banerMainActivity);
        AdRequest adRequest = new AdRequest.Builder().build();
        publicidad.loadAd(adRequest);

        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                instagramActividad(MainActivity.this);
            }
        });
        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Próximamente.", Toast.LENGTH_SHORT).show();
            }
        });
        builds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buildActivity();
            }
        });
        misBuilds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                misBuildsActivity();
            }
        });
        artefactos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                artefactosActivity();
            }
        });
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
        datosProcesosSqlite = new DatosProcesosSqlite(MainActivity.this);
        datosProcesosSqlite.copiarArchivo(instalacion);
    }

    private void ejecutar(){
        permissionHelper = new PermissionHelper(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        permissionHelper.request(new PermissionHelper.PermissionCallback() {
            @Override
            public void onPermissionGranted() {
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
}