package com.frabasoft.genshinimpactrecursos.Actividades;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.frabasoft.genshinimpactrecursos.MainActivity;
import com.frabasoft.genshinimpactrecursos.R;
import com.frabasoft.genshinimpactrecursos.SQLiteGenshin.Procesos.DatosProcesosSqlite;
import com.frabasoft.genshinimpactrecursos.TouchImage.TouchImageView;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.master.permissionhelper.PermissionHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.Calendar;

public class Builds extends FragmentActivity {
    AdView publicidad;
    Spinner personajes;
    TextView descargaBuild;
    String[] personajesString = {"Seleccione", "Albedo", "Amber",
            "Barbara", "Beidou", "Bennet v1",
            "Bennet v2", "Chongyun", "Diluc",
            "Diona", "Fischl", "Ganyu",
            "Hu Tao", "Jean", "Kaeya",
            "Keqing", "Keqing v2", "Klee", "Lisa",
            "Mona v1", "Mona v2", "Ninguang",
            "Noelle", "Qiqi", "Razor", "Rosaria",
            "Sucrose","Tartaglia", "Traveler Anemo",
            "Venti", "Xianling v1", "Xianling v2",
            "Xiao", "Xingqiu v1", "Xingqiu v2",
            "Xinyan","Zhongli v1", "Zhongli v2",
            "Zhongli v3"};
    private TouchImageView imgPJ;
    private DecimalFormat df;
    PermissionHelper permissionHelper;
    private final static String TAG = "ERRORTAG";
    private String buildsDetalle = "BUILDS DE PERSONAJES: ";
    private DatosProcesosSqlite datosProcesosSqlite;

    //para el ad de la descarga de la imagen
    private static final String AD_UNIT_ID = "ca-app-pub-4467142756516555/7442029029";
    private InterstitialAd interstitialAd;

    //para los soniditos
    private MediaPlayer salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_builds);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        salir = MediaPlayer.create(getApplicationContext(), R.raw.salir);

        ejecutar();

        MobileAds.initialize(Builds.this, initializationStatus -> { });
        publicidad = findViewById(R.id.banerBuild);
        AdRequest adRequest = new AdRequest.Builder().build();
        publicidad.loadAd(adRequest);

        personajes = findViewById(R.id.personajes);
        descargaBuild = findViewById(R.id.descargaBuilds);
        descargaBuild.setText("**Puedes descargar las imágenes presionando por unos segundos sobre la misma.");

        df = new DecimalFormat("#.##");
        imgPJ = findViewById(R.id.imgPJ);

        personajes.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_color_text, personajesString));
        personajes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                if(position == 0){
                    imgPJ.setImageResource(R.drawable.wallpaper);
                }else if(position == 1){
                    toastGeo(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.albedo);
                    imgPJ.setOnLongClickListener(v -> {
                        if(permissionHelper.hasPermission()){
                            //convertir imagen a bitmap
                            imgPJ.buildDrawingCache();
                            Bitmap bmap = imgPJ.getDrawingCache();
                            guardarImagenMedoto(bmap, personajes.getSelectedItem().toString());
                            try {
                                agregarLineasFicheroTXT(buildsDetalle, personajes.getSelectedItem().toString());
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }else{
                            ejecutar();
                        }
                        return true;
                    });
                }else if(position == 2){
                    toastPyro(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.amber);

                    imgPJ.setOnLongClickListener(v -> {
                        if(permissionHelper.hasPermission()){
                            //convertir imagen a bitmap
                            imgPJ.buildDrawingCache();
                            Bitmap bmap = imgPJ.getDrawingCache();
                            guardarImagenMedoto(bmap, personajes.getSelectedItem().toString());
                            try {
                                agregarLineasFicheroTXT(buildsDetalle, personajes.getSelectedItem().toString());
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }else{
                            ejecutar();
                        }
                        return true;
                    });
                }else if(position == 3){
                    toastHydro(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.barbara);
                    imgPJ.setOnLongClickListener(v -> {
                        if(permissionHelper.hasPermission()){
                            //convertir imagen a bitmap
                            imgPJ.buildDrawingCache();
                            Bitmap bmap = imgPJ.getDrawingCache();
                            guardarImagenMedoto(bmap, personajes.getSelectedItem().toString());

                            try {
                                agregarLineasFicheroTXT(buildsDetalle, personajes.getSelectedItem().toString());
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }else{
                            ejecutar();
                        }
                        return true;
                    });
                }else if(position == 4){
                    toastElectro(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.beidou);
                    imgPJ.setOnLongClickListener(v -> {
                        if(permissionHelper.hasPermission()){
                            //convertir imagen a bitmap
                            imgPJ.buildDrawingCache();
                            Bitmap bmap = imgPJ.getDrawingCache();
                            guardarImagenMedoto(bmap, personajes.getSelectedItem().toString());

                            try {
                                agregarLineasFicheroTXT(buildsDetalle, personajes.getSelectedItem().toString());
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }else{
                            ejecutar();
                        }
                        return true;
                    });
                }else if(position == 5){
                    toastPyro(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.bennet_v1);
                    imgPJ.setOnLongClickListener(v -> {
                        if(permissionHelper.hasPermission()){
                            //convertir imagen a bitmap
                            imgPJ.buildDrawingCache();
                            Bitmap bmap = imgPJ.getDrawingCache();
                            guardarImagenMedoto(bmap, personajes.getSelectedItem().toString());

                            try {
                                agregarLineasFicheroTXT(buildsDetalle, personajes.getSelectedItem().toString());
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }else{
                            ejecutar();
                        }
                        return true;
                    });
                }else if(position == 6){
                    toastPyro(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.bennet_v2);
                    imgPJ.setOnLongClickListener(v -> {
                        if(permissionHelper.hasPermission()){
                            //convertir imagen a bitmap
                            imgPJ.buildDrawingCache();
                            Bitmap bmap = imgPJ.getDrawingCache();
                            guardarImagenMedoto(bmap, personajes.getSelectedItem().toString());
                            try {
                                agregarLineasFicheroTXT(buildsDetalle, personajes.getSelectedItem().toString());
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }else{
                            ejecutar();
                        }
                        return true;
                    });
                }else if(position == 7){
                    toastCryo(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.chongyun);
                    imgPJ.setOnLongClickListener(v -> {
                        if(permissionHelper.hasPermission()){
                            //convertir imagen a bitmap
                            imgPJ.buildDrawingCache();
                            Bitmap bmap = imgPJ.getDrawingCache();
                            guardarImagenMedoto(bmap, personajes.getSelectedItem().toString());

                            try {
                                agregarLineasFicheroTXT(buildsDetalle, personajes.getSelectedItem().toString());
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }else{
                            ejecutar();
                        }
                        return true;
                    });
                }else if(position == 8){
                    toastPyro(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.diluc);
                    imgPJ.setOnLongClickListener(v -> {
                        if(permissionHelper.hasPermission()){
                            //convertir imagen a bitmap
                            imgPJ.buildDrawingCache();
                            Bitmap bmap = imgPJ.getDrawingCache();
                            guardarImagenMedoto(bmap, personajes.getSelectedItem().toString());

                            try {
                                agregarLineasFicheroTXT(buildsDetalle, personajes.getSelectedItem().toString());
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }else{
                            ejecutar();
                        }
                        return true;
                    });
                }else if(position == 9){
                    toastCryo(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.diona);
                    imgPJ.setOnLongClickListener(v -> {
                        if(permissionHelper.hasPermission()){
                            //convertir imagen a bitmap
                            imgPJ.buildDrawingCache();
                            Bitmap bmap = imgPJ.getDrawingCache();
                            guardarImagenMedoto(bmap, personajes.getSelectedItem().toString());

                            try {
                                agregarLineasFicheroTXT(buildsDetalle, personajes.getSelectedItem().toString());
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }else{
                            ejecutar();
                        }
                        return true;
                    });
                }else if(position == 10){
                    toastElectro(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.fischl);
                    imgPJ.setOnLongClickListener(v -> {
                        if(permissionHelper.hasPermission()){
                            //convertir imagen a bitmap
                            imgPJ.buildDrawingCache();
                            Bitmap bmap = imgPJ.getDrawingCache();
                            guardarImagenMedoto(bmap, personajes.getSelectedItem().toString());

                            try {
                                agregarLineasFicheroTXT(buildsDetalle, personajes.getSelectedItem().toString());
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }else{
                            ejecutar();
                        }
                        return true;
                    });
                }else if(position == 11){
                    toastCryo(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.ganyu);
                    imgPJ.setOnLongClickListener(v -> {
                        if(permissionHelper.hasPermission()){
                            //convertir imagen a bitmap
                            imgPJ.buildDrawingCache();
                            Bitmap bmap = imgPJ.getDrawingCache();
                            guardarImagenMedoto(bmap, personajes.getSelectedItem().toString());

                            try {
                                agregarLineasFicheroTXT(buildsDetalle, personajes.getSelectedItem().toString());
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }else{
                            ejecutar();
                        }
                        return true;
                    });
                }else if(position == 12){
                    toastPyro(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.hutao);
                    imgPJ.setOnLongClickListener(v -> {
                        if(permissionHelper.hasPermission()){
                            //convertir imagen a bitmap
                            imgPJ.buildDrawingCache();
                            Bitmap bmap = imgPJ.getDrawingCache();
                            guardarImagenMedoto(bmap, personajes.getSelectedItem().toString());
                            try {
                                agregarLineasFicheroTXT(buildsDetalle, personajes.getSelectedItem().toString());
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }else{
                            ejecutar();
                        }
                        return true;
                    });
                }else if(position == 13){
                    toastAnemo(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.jean);
                    imgPJ.setOnLongClickListener(v -> {
                        if(permissionHelper.hasPermission()){
                            //convertir imagen a bitmap
                            imgPJ.buildDrawingCache();
                            Bitmap bmap = imgPJ.getDrawingCache();
                            guardarImagenMedoto(bmap, personajes.getSelectedItem().toString());

                            try {
                                agregarLineasFicheroTXT(buildsDetalle, personajes.getSelectedItem().toString());
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }else{
                            ejecutar();
                        }
                        return true;
                    });
                }else if(position == 14){
                    toastCryo(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.kaeya);
                    imgPJ.setOnLongClickListener(v -> {
                        if(permissionHelper.hasPermission()){
                            //convertir imagen a bitmap
                            imgPJ.buildDrawingCache();
                            Bitmap bmap = imgPJ.getDrawingCache();
                            guardarImagenMedoto(bmap, personajes.getSelectedItem().toString());

                            try {
                                agregarLineasFicheroTXT(buildsDetalle, personajes.getSelectedItem().toString());
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }else{
                            ejecutar();
                        }
                        return true;
                    });
                }else if(position == 15){
                    imgPJ.setImageResource(R.drawable.keqing);
                    toastElectro(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setOnLongClickListener(v -> {
                        if(permissionHelper.hasPermission()){
                            //convertir imagen a bitmap
                            imgPJ.buildDrawingCache();
                            Bitmap bmap = imgPJ.getDrawingCache();
                            guardarImagenMedoto(bmap, personajes.getSelectedItem().toString());

                            try {
                                agregarLineasFicheroTXT(buildsDetalle, personajes.getSelectedItem().toString());
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }else{
                            ejecutar();
                        }
                        return true;
                    });
                }else if(position == 16){
                    imgPJ.setImageResource(R.drawable.kekingv2);
                    toastElectro(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setOnLongClickListener(v -> {
                        if(permissionHelper.hasPermission()){
                            //convertir imagen a bitmap
                            imgPJ.buildDrawingCache();
                            Bitmap bmap = imgPJ.getDrawingCache();
                            guardarImagenMedoto(bmap, personajes.getSelectedItem().toString());
                            try {
                                agregarLineasFicheroTXT(buildsDetalle, personajes.getSelectedItem().toString());
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }else{
                            ejecutar();
                        }
                        return true;
                    });
                }else if(position == 17){
                    toastPyro(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.klee);
                    imgPJ.setOnLongClickListener(v -> {
                        if(permissionHelper.hasPermission()){
                            //convertir imagen a bitmap
                            imgPJ.buildDrawingCache();
                            Bitmap bmap = imgPJ.getDrawingCache();
                            guardarImagenMedoto(bmap, personajes.getSelectedItem().toString());
                            try {
                                agregarLineasFicheroTXT(buildsDetalle, personajes.getSelectedItem().toString());
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }else{
                            ejecutar();
                        }
                        return true;
                    });
                }else if(position == 18){
                    toastElectro(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.lisa);
                    imgPJ.setOnLongClickListener(v -> {
                        if(permissionHelper.hasPermission()){
                            //convertir imagen a bitmap
                            imgPJ.buildDrawingCache();
                            Bitmap bmap = imgPJ.getDrawingCache();
                            guardarImagenMedoto(bmap, personajes.getSelectedItem().toString());
                            try {
                                agregarLineasFicheroTXT(buildsDetalle, personajes.getSelectedItem().toString());
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }else{
                            ejecutar();
                        }
                        return true;
                    });
                }else if(position == 19){
                    toastHydro(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.mona_v1);
                    imgPJ.setOnLongClickListener(v -> {
                        if(permissionHelper.hasPermission()){
                            //convertir imagen a bitmap
                            imgPJ.buildDrawingCache();
                            Bitmap bmap = imgPJ.getDrawingCache();
                            guardarImagenMedoto(bmap, personajes.getSelectedItem().toString());
                            try {
                                agregarLineasFicheroTXT(buildsDetalle, personajes.getSelectedItem().toString());
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }else{
                            ejecutar();
                        }
                        return true;
                    });
                }else if(position == 20){
                    toastHydro(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.mona_v2);
                    imgPJ.setOnLongClickListener(v -> {
                        if(permissionHelper.hasPermission()){
                            //convertir imagen a bitmap
                            imgPJ.buildDrawingCache();
                            Bitmap bmap = imgPJ.getDrawingCache();
                            guardarImagenMedoto(bmap, personajes.getSelectedItem().toString());
                            try {
                                agregarLineasFicheroTXT(buildsDetalle, personajes.getSelectedItem().toString());
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }else{
                            ejecutar();
                        }
                        return true;
                    });
                }else if(position == 21){
                    toastGeo(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.ningguang);
                    imgPJ.setOnLongClickListener(v -> {
                        if(permissionHelper.hasPermission()){
                            //convertir imagen a bitmap
                            imgPJ.buildDrawingCache();
                            Bitmap bmap = imgPJ.getDrawingCache();
                            guardarImagenMedoto(bmap, personajes.getSelectedItem().toString());
                            try {
                                agregarLineasFicheroTXT(buildsDetalle, personajes.getSelectedItem().toString());
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }else{
                            ejecutar();
                        }
                        return true;
                    });
                }else if(position == 22){
                    toastGeo(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.noelle);
                    imgPJ.setOnLongClickListener(v -> {
                        if(permissionHelper.hasPermission()){
                            //convertir imagen a bitmap
                            imgPJ.buildDrawingCache();
                            Bitmap bmap = imgPJ.getDrawingCache();
                            guardarImagenMedoto(bmap, personajes.getSelectedItem().toString());
                            try {
                                agregarLineasFicheroTXT(buildsDetalle, personajes.getSelectedItem().toString());
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }else{
                            ejecutar();
                        }
                        return true;
                    });
                }else if(position == 23){
                    toastCryo(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.qiqi);
                    imgPJ.setOnLongClickListener(v -> {
                        if(permissionHelper.hasPermission()){
                            //convertir imagen a bitmap
                            imgPJ.buildDrawingCache();
                            Bitmap bmap = imgPJ.getDrawingCache();
                            guardarImagenMedoto(bmap, personajes.getSelectedItem().toString());
                            try {
                                agregarLineasFicheroTXT(buildsDetalle, personajes.getSelectedItem().toString());
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }else{
                            ejecutar();
                        }
                        return true;
                    });
                }else if(position == 24){
                    toastElectro(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.razor);
                    imgPJ.setOnLongClickListener(v -> {
                        if(permissionHelper.hasPermission()){
                            //convertir imagen a bitmap
                            imgPJ.buildDrawingCache();
                            Bitmap bmap = imgPJ.getDrawingCache();
                            guardarImagenMedoto(bmap, personajes.getSelectedItem().toString());
                            try {
                                agregarLineasFicheroTXT(buildsDetalle, personajes.getSelectedItem().toString());
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }else{
                            ejecutar();
                        }
                        return true;
                    });
                }else if(position == 25){
                    toastCryo(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.rosaria);
                    imgPJ.setOnLongClickListener(v -> {
                        if(permissionHelper.hasPermission()){
                            //convertir imagen a bitmap
                            imgPJ.buildDrawingCache();
                            Bitmap bmap = imgPJ.getDrawingCache();
                            guardarImagenMedoto(bmap, personajes.getSelectedItem().toString());
                            try {
                                agregarLineasFicheroTXT(buildsDetalle, personajes.getSelectedItem().toString());
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }else{
                            ejecutar();
                        }
                        return true;
                    });
                }else if(position == 26){
                    toastAnemo(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.sucrose);
                    imgPJ.setOnLongClickListener(v -> {
                        if(permissionHelper.hasPermission()){
                            //convertir imagen a bitmap
                            imgPJ.buildDrawingCache();
                            Bitmap bmap = imgPJ.getDrawingCache();
                            guardarImagenMedoto(bmap, personajes.getSelectedItem().toString());
                            try {
                                agregarLineasFicheroTXT(buildsDetalle, personajes.getSelectedItem().toString());
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }else{
                            ejecutar();
                        }
                        return true;
                    });
                }else if(position == 27){
                    toastHydro(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.tartaglia);
                    imgPJ.setOnLongClickListener(v -> {
                        if(permissionHelper.hasPermission()){
                            //convertir imagen a bitmap
                            imgPJ.buildDrawingCache();
                            Bitmap bmap = imgPJ.getDrawingCache();
                            guardarImagenMedoto(bmap, personajes.getSelectedItem().toString());
                            try {
                                agregarLineasFicheroTXT(buildsDetalle, personajes.getSelectedItem().toString());
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }else{
                            ejecutar();
                        }
                        return true;
                    });
                }else if(position == 28){
                    toastAnemo(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.traveler_anemo);
                    imgPJ.setOnLongClickListener(v -> {
                        if(permissionHelper.hasPermission()){
                            //convertir imagen a bitmap
                            imgPJ.buildDrawingCache();
                            Bitmap bmap = imgPJ.getDrawingCache();
                            guardarImagenMedoto(bmap, personajes.getSelectedItem().toString());
                            try {
                                agregarLineasFicheroTXT(buildsDetalle, personajes.getSelectedItem().toString());
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }else{
                            ejecutar();
                        }
                        return true;
                    });
                }else if(position == 29){
                    toastAnemo(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.venti);
                    imgPJ.setOnLongClickListener(v -> {
                        if(permissionHelper.hasPermission()){
                            //convertir imagen a bitmap
                            imgPJ.buildDrawingCache();
                            Bitmap bmap = imgPJ.getDrawingCache();
                            guardarImagenMedoto(bmap, personajes.getSelectedItem().toString());
                            try {
                                agregarLineasFicheroTXT(buildsDetalle, personajes.getSelectedItem().toString());
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }else{
                            ejecutar();
                        }
                        return true;
                    });
                }else if(position == 30){
                    toastPyro(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.xiangling_v1);
                    imgPJ.setOnLongClickListener(v -> {
                        if(permissionHelper.hasPermission()){
                            //convertir imagen a bitmap
                            imgPJ.buildDrawingCache();
                            Bitmap bmap = imgPJ.getDrawingCache();
                            guardarImagenMedoto(bmap, personajes.getSelectedItem().toString());
                            try {
                                agregarLineasFicheroTXT(buildsDetalle, personajes.getSelectedItem().toString());
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }else{
                            ejecutar();
                        }
                        return true;
                    });
                }else if(position == 31){
                    toastPyro(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.xiangling_v2);
                    imgPJ.setOnLongClickListener(v -> {
                        if(permissionHelper.hasPermission()){
                            //convertir imagen a bitmap
                            imgPJ.buildDrawingCache();
                            Bitmap bmap = imgPJ.getDrawingCache();
                            guardarImagenMedoto(bmap, personajes.getSelectedItem().toString());
                            try {
                                agregarLineasFicheroTXT(buildsDetalle, personajes.getSelectedItem().toString());
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }else{
                            ejecutar();
                        }
                        return true;
                    });
                }else if(position == 32){
                    toastAnemo(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.xiao);
                    imgPJ.setOnLongClickListener(v -> {
                        if(permissionHelper.hasPermission()){
                            //convertir imagen a bitmap
                            imgPJ.buildDrawingCache();
                            Bitmap bmap = imgPJ.getDrawingCache();
                            guardarImagenMedoto(bmap, personajes.getSelectedItem().toString());
                            try {
                                agregarLineasFicheroTXT(buildsDetalle, personajes.getSelectedItem().toString());
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }else{
                            ejecutar();
                        }
                        return true;
                    });
                }else if(position == 33){
                    toastHydro(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.xingqiu_v1);
                    imgPJ.setOnLongClickListener(v -> {
                        if(permissionHelper.hasPermission()){
                            //convertir imagen a bitmap
                            imgPJ.buildDrawingCache();
                            Bitmap bmap = imgPJ.getDrawingCache();
                            guardarImagenMedoto(bmap, personajes.getSelectedItem().toString());
                            try {
                                agregarLineasFicheroTXT(buildsDetalle, personajes.getSelectedItem().toString());
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }else{
                            ejecutar();
                        }
                        return true;
                    });
                }else if(position == 34){
                    toastHydro(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.xingqiu_v2);
                    imgPJ.setOnLongClickListener(v -> {
                        if(permissionHelper.hasPermission()){
                            //convertir imagen a bitmap
                            imgPJ.buildDrawingCache();
                            Bitmap bmap = imgPJ.getDrawingCache();
                            guardarImagenMedoto(bmap, personajes.getSelectedItem().toString());
                            try {
                                agregarLineasFicheroTXT(buildsDetalle, personajes.getSelectedItem().toString());
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }else{
                            ejecutar();
                        }
                        return true;
                    });
                }else if(position == 35){
                    toastPyro(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.xinyan);
                    imgPJ.setOnLongClickListener(v -> {
                        if(permissionHelper.hasPermission()){
                            //convertir imagen a bitmap
                            imgPJ.buildDrawingCache();
                            Bitmap bmap = imgPJ.getDrawingCache();
                            guardarImagenMedoto(bmap, personajes.getSelectedItem().toString());
                            try {
                                agregarLineasFicheroTXT(buildsDetalle, personajes.getSelectedItem().toString());
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }else{
                            ejecutar();
                        }
                        return true;
                    });
                }else if(position == 36){
                    toastGeo(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.zhongli_v1);
                    imgPJ.setOnLongClickListener(v -> {
                        if(permissionHelper.hasPermission()){
                            //convertir imagen a bitmap
                            imgPJ.buildDrawingCache();
                            Bitmap bmap = imgPJ.getDrawingCache();
                            guardarImagenMedoto(bmap, personajes.getSelectedItem().toString());
                            try {
                                agregarLineasFicheroTXT(buildsDetalle, personajes.getSelectedItem().toString());
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }else{
                            ejecutar();
                        }
                        return true;
                    });
                }else if(position == 37){
                    toastGeo(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.zhongli_v2);
                    imgPJ.setOnLongClickListener(v -> {
                        if(permissionHelper.hasPermission()){
                            //convertir imagen a bitmap
                            imgPJ.buildDrawingCache();
                            Bitmap bmap = imgPJ.getDrawingCache();
                            guardarImagenMedoto(bmap, personajes.getSelectedItem().toString());
                            try {
                                agregarLineasFicheroTXT(buildsDetalle, personajes.getSelectedItem().toString());
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }else{
                            ejecutar();
                        }
                        return true;
                    });
                }else if(position == 38){
                    toastGeo(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.zhongli_v3);
                    imgPJ.setOnLongClickListener(v -> {
                        if(permissionHelper.hasPermission()){
                            //convertir imagen a bitmap
                            imgPJ.buildDrawingCache();
                            Bitmap bmap = imgPJ.getDrawingCache();
                            guardarImagenMedoto(bmap, personajes.getSelectedItem().toString());
                            try {
                                agregarLineasFicheroTXT(buildsDetalle, personajes.getSelectedItem().toString());
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }else{
                            ejecutar();
                        }
                        return true;
                    });
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
        loadAd();
    }

    private void toastAnemo(Context actividad, String mensaje){
        Toast toast = Toast.makeText(actividad, mensaje, Toast.LENGTH_SHORT);
        View view = toast.getView();
        view.getBackground().setColorFilter(getResources().getColor(R.color.anemoColor), PorterDuff.Mode.SRC_IN);
        TextView toastTextView = view.findViewById(R.id.toastTextView);
        toast.show();
    }
    private void toastCryo(Context actividad, String mensaje){
        Toast toast = Toast.makeText(actividad, mensaje, Toast.LENGTH_SHORT);
        View view = toast.getView();
        view.getBackground().setColorFilter(getResources().getColor(R.color.cryoColor), PorterDuff.Mode.SRC_IN);
        TextView toastTextView = view.findViewById(R.id.toastTextView);
        toast.show();
    }
    private void toastElectro(Context actividad, String mensaje){
        Toast toast = Toast.makeText(actividad, mensaje, Toast.LENGTH_SHORT);
        View view = toast.getView();
        view.getBackground().setColorFilter(getResources().getColor(R.color.electroColor), PorterDuff.Mode.SRC_IN);
        TextView toastTextView = view.findViewById(R.id.toastTextView);
        toast.show();
    }
    private void toastGeo(Context actividad, String mensaje){
        Toast toast = Toast.makeText(actividad, mensaje, Toast.LENGTH_SHORT);
        View view = toast.getView();
        view.getBackground().setColorFilter(getResources().getColor(R.color.geoColor), PorterDuff.Mode.SRC_IN);
        TextView toastTextView = view.findViewById(R.id.toastTextView);
        toast.show();
    }
    private void toastHydro(Context actividad, String mensaje){
        Toast toast = Toast.makeText(actividad, mensaje, Toast.LENGTH_SHORT);
        View view = toast.getView();
        view.getBackground().setColorFilter(getResources().getColor(R.color.hydroColor), PorterDuff.Mode.SRC_IN);
        TextView toastTextView = view.findViewById(R.id.toastTextView);
        toast.show();
    }
    private void toastPyro(Context actividad, String mensaje){
        Toast toast = Toast.makeText(actividad, mensaje, Toast.LENGTH_SHORT);
        View view = toast.getView();
        view.getBackground().setColorFilter(getResources().getColor(R.color.pyroColor), PorterDuff.Mode.SRC_IN);
        TextView toastTextView = view.findViewById(R.id.toastTextView);
        toast.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        salir.start();
        Builds.this.finish();
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

    /** Called when leaving the activity */
    @Override
    public void onPause() {
        if (publicidad != null) {
            publicidad.pause();
        }
        super.onPause();
    }

    /** Called when returning to the activity */
    @Override
    public void onResume() {
        super.onResume();
        if (publicidad != null) {
            publicidad.resume();
        }
    }

    /** Called before the activity is destroyed */
    @Override
    public void onDestroy() {
        if (publicidad != null) {
            publicidad.destroy();
        }
        super.onDestroy();
    }

    private void guardarImagenMedoto(Bitmap bitmap, String nombrePersonaje){
        showInterstitial();
        File rutaBuilds = new File(Environment.getExternalStorageDirectory() + "/Genshin Impact Recursos/Genshin Impact Builds/");
        if (!rutaBuilds.exists()) {
            File rutaBuildsCrear = new File(Environment.getExternalStorageDirectory() + "/Genshin Impact Recursos/Genshin Impact Builds/");
            rutaBuildsCrear.mkdirs();
        }

        File archivo = new File(rutaBuilds, nombrePersonaje + ".jpg");
        if (archivo.exists()) {
            archivo.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(archivo);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            Toast.makeText(this, "¡Se ha guardado con éxito la build de " + nombrePersonaje + "!", Toast.LENGTH_SHORT).show();
            Log.d("guardarImagenMedoto", "try: " + "\nRuta: " + rutaBuilds + "\nArchivo: " + archivo);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "¡Ha ocurrido un error al intentar guardar la build de " + nombrePersonaje + "!", Toast.LENGTH_SHORT).show();
            Log.d("guardarImagenMedoto", "Catch: " + e.getMessage() + "\nRuta: " + rutaBuilds + "\nArchivo: " + archivo);
        }
    }

    private void agregarLineasFicheroTXT(String builds, String pj) throws IOException {
        String detalleRutaYArchivoDescargado = "Se ha descargado el archivo: "
                + pj + ".jpg" + "\n"
                + "Ubicación del archivo: sdcard/Genshin Impact Recursos/Genshin Impact Builds";
        datosProcesosSqlite = new DatosProcesosSqlite(Builds.this);
        datosProcesosSqlite.copiarArchivo(builds + detalleRutaYArchivoDescargado);
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
                        Builds.this.interstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");
                        interstitialAd.setFullScreenContentCallback(
                                new FullScreenContentCallback() {
                                    @Override
                                    public void onAdDismissedFullScreenContent() {
                                        // Called when fullscreen content is dismissed.
                                        // Make sure to set your reference to null so you don't
                                        // show it a second time.
                                        Builds.this.interstitialAd = null;
                                        Log.d("TAG", "The ad was dismissed.");
                                    }

                                    @Override
                                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                                        // Called when fullscreen content failed to show.
                                        // Make sure to set your reference to null so you don't
                                        // show it a second time.
                                        Builds.this.interstitialAd = null;
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
}