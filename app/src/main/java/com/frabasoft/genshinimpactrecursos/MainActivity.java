package com.frabasoft.genshinimpactrecursos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.frabasoft.genshinimpactrecursos.Actividades.Builds;
import com.frabasoft.genshinimpactrecursos.Actividades.MisBuilds;
import com.frabasoft.genshinimpactrecursos.Actividades.RutaArtefactos;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {
    ImageView instagram, whatsapp;
    AdView publicidad;
    Button builds, artefactos, misBuilds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        instagram = (ImageView)findViewById(R.id.instagram);
        whatsapp = (ImageView)findViewById(R.id.whatsapp);
        builds = (Button)findViewById(R.id.builds);
        artefactos = (Button)findViewById(R.id.artefactos);
        misBuilds = (Button)findViewById(R.id.misBuilds);

        banerPublicitario();

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

    private void instagramActividad(Context contexto){
        Uri uri = Uri.parse("https://www.instagram.com/_u/genshinbaoer/");
        Intent instagram = new Intent(Intent.ACTION_VIEW, uri);
        try {
            startActivity(instagram);
        }catch (ActivityNotFoundException activityNotFoundException){
            Toast.makeText(contexto, "No dispones de la aplicación, se abrirá en el navegador.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.instagram.com/genshinbaoer/")));
        }
    }

    private void banerPublicitario(){
        MobileAds.initialize(MainActivity.this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) { }
        });
        publicidad = (AdView)findViewById(R.id.banerMainActivity);
        AdRequest adRequest = new AdRequest.Builder().build();
        publicidad.loadAd(adRequest);
    }

    private void buildActivity(){
        Intent build = new Intent(MainActivity.this, Builds.class);
        startActivity(build);
    }

    private void misBuildsActivity(){
        Intent miBuild = new Intent(MainActivity.this, MisBuilds.class);
        startActivity(miBuild);
    }
    private void artefactosActivity(){
        Intent artefactos = new Intent(MainActivity.this, RutaArtefactos.class);
        startActivity(artefactos);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MainActivity.this.finish();
    }
}