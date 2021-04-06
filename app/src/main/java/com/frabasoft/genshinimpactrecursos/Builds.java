package com.frabasoft.genshinimpactrecursos;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.frabasoft.genshinimpactrecursos.TouchImage.TouchImageView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.master.permissionhelper.PermissionHelper;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;

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
            "Noelle", "Qiqi", "Razor",
            "Sucrose","Tartaglia", "Traveler Anemo",
            "Venti", "Xianling v1", "Xianling v2",
            "Xiao", "Xingqiu v1", "Xingqiu v2",
            "Xinyan","Zhongli v1", "Zhongli v2",
            "Zhongli v3"};
    private TouchImageView imgPJ;
    private DecimalFormat df;
    PermissionHelper permissionHelper;
    private final static String TAG = "ERRORTAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_builds);

        ejecutar();

        personajes = (Spinner)findViewById(R.id.personajes);
        descargaBuild = (TextView)findViewById(R.id.descargaBuilds);
        descargaBuild.setText("**Puedes descargar las imágenes presionando por unos segundos sobre la misma.");

        df = new DecimalFormat("#.##");
        imgPJ = (TouchImageView) findViewById(R.id.imgPJ);

        personajes.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, personajesString));
        personajes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                if(position == 0){
                    imgPJ.setImageResource(R.drawable.wallpaper);
                }else if(position == 1){
                    toastGeo(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.albedo);
                    imgPJ.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            if(permissionHelper.hasPermission()){
                                //convertir imagen a bitmap
                                imgPJ.buildDrawingCache();
                                Bitmap bmap = imgPJ.getDrawingCache();
                                try {
                                    saveBitmap(getApplicationContext(), bmap, Bitmap.CompressFormat.JPEG, "image/*", personajes.getSelectedItem().toString());
                                } catch (IOException e) {
                                    Toast.makeText(Builds.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                ejecutar();
                            }
                            return true;
                        }
                    });
                }else if(position == 2){
                    toastPyro(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.amber);

                    imgPJ.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            if(permissionHelper.hasPermission()){
                                //convertir imagen a bitmap
                                imgPJ.buildDrawingCache();
                                Bitmap bmap = imgPJ.getDrawingCache();
                                try {
                                    saveBitmap(getApplicationContext(), bmap, Bitmap.CompressFormat.JPEG, "image/*", personajes.getSelectedItem().toString());
                                } catch (IOException e) {
                                    Toast.makeText(Builds.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                ejecutar();
                            }
                            return true;
                        }
                    });
                }else if(position == 3){
                    toastHydro(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.barbara);
                    imgPJ.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            if(permissionHelper.hasPermission()){
                                //convertir imagen a bitmap
                                imgPJ.buildDrawingCache();
                                Bitmap bmap = imgPJ.getDrawingCache();
                                try {
                                    saveBitmap(getApplicationContext(), bmap, Bitmap.CompressFormat.JPEG, "image/*", personajes.getSelectedItem().toString());
                                } catch (IOException e) {
                                    Toast.makeText(Builds.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                ejecutar();
                            }
                            return true;
                        }
                    });
                }else if(position == 4){
                    toastElectro(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.beidou);
                    imgPJ.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            if(permissionHelper.hasPermission()){
                                //convertir imagen a bitmap
                                imgPJ.buildDrawingCache();
                                Bitmap bmap = imgPJ.getDrawingCache();
                                try {
                                    saveBitmap(getApplicationContext(), bmap, Bitmap.CompressFormat.JPEG, "image/*", personajes.getSelectedItem().toString());
                                } catch (IOException e) {
                                    Toast.makeText(Builds.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                ejecutar();
                            }
                            return true;
                        }
                    });
                }else if(position == 5){
                    toastPyro(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.bennet_v1);
                    imgPJ.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            if(permissionHelper.hasPermission()){
                                //convertir imagen a bitmap
                                imgPJ.buildDrawingCache();
                                Bitmap bmap = imgPJ.getDrawingCache();
                                
                                try {
                                    saveBitmap(getApplicationContext(), bmap, Bitmap.CompressFormat.JPEG, "image/*", personajes.getSelectedItem().toString());
                                } catch (IOException e) {
                                    Toast.makeText(Builds.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                ejecutar();
                            }
                            return true;
                        }
                    });
                }else if(position == 6){
                    toastPyro(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.bennet_v2);
                    imgPJ.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            if(permissionHelper.hasPermission()){
                                //convertir imagen a bitmap
                                imgPJ.buildDrawingCache();
                                Bitmap bmap = imgPJ.getDrawingCache();
                                
                                try {
                                    saveBitmap(getApplicationContext(), bmap, Bitmap.CompressFormat.JPEG, "image/*", personajes.getSelectedItem().toString());
                                } catch (IOException e) {
                                    Toast.makeText(Builds.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                ejecutar();
                            }
                            return true;
                        }
                    });
                }else if(position == 7){
                    toastCryo(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.chongyun);
                    imgPJ.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            if(permissionHelper.hasPermission()){
                                //convertir imagen a bitmap
                                imgPJ.buildDrawingCache();
                                Bitmap bmap = imgPJ.getDrawingCache();
                                
                                try {
                                    saveBitmap(getApplicationContext(), bmap, Bitmap.CompressFormat.JPEG, "image/*", personajes.getSelectedItem().toString());
                                } catch (IOException e) {
                                    Toast.makeText(Builds.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                ejecutar();
                            }
                            return true;
                        }
                    });
                }else if(position == 8){
                    toastPyro(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.diluc);
                    imgPJ.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            if(permissionHelper.hasPermission()){
                                //convertir imagen a bitmap
                                imgPJ.buildDrawingCache();
                                Bitmap bmap = imgPJ.getDrawingCache();
                                
                                try {
                                    saveBitmap(getApplicationContext(), bmap, Bitmap.CompressFormat.JPEG, "image/*", personajes.getSelectedItem().toString());
                                } catch (IOException e) {
                                    Toast.makeText(Builds.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                ejecutar();
                            }
                            return true;
                        }
                    });
                }else if(position == 9){
                    toastCryo(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.diona);
                    imgPJ.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            if(permissionHelper.hasPermission()){
                                //convertir imagen a bitmap
                                imgPJ.buildDrawingCache();
                                Bitmap bmap = imgPJ.getDrawingCache();
                                
                                try {
                                    saveBitmap(getApplicationContext(), bmap, Bitmap.CompressFormat.JPEG, "image/*", personajes.getSelectedItem().toString());
                                } catch (IOException e) {
                                    Toast.makeText(Builds.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                ejecutar();
                            }
                            return true;
                        }
                    });
                }else if(position == 10){
                    toastElectro(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.fischl);
                    imgPJ.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            if(permissionHelper.hasPermission()){
                                //convertir imagen a bitmap
                                imgPJ.buildDrawingCache();
                                Bitmap bmap = imgPJ.getDrawingCache();
                                
                                try {
                                    saveBitmap(getApplicationContext(), bmap, Bitmap.CompressFormat.JPEG, "image/*", personajes.getSelectedItem().toString());
                                } catch (IOException e) {
                                    Toast.makeText(Builds.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                ejecutar();
                            }
                            return true;
                        }
                    });
                }else if(position == 11){
                    toastCryo(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.ganyu);
                    imgPJ.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            if(permissionHelper.hasPermission()){
                                //convertir imagen a bitmap
                                imgPJ.buildDrawingCache();
                                Bitmap bmap = imgPJ.getDrawingCache();
                                
                                try {
                                    saveBitmap(getApplicationContext(), bmap, Bitmap.CompressFormat.JPEG, "image/*", personajes.getSelectedItem().toString());
                                } catch (IOException e) {
                                    Toast.makeText(Builds.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                ejecutar();
                            }
                            return true;
                        }
                    });
                }else if(position == 12){
                    toastPyro(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.hutao);
                    imgPJ.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            if(permissionHelper.hasPermission()){
                                //convertir imagen a bitmap
                                imgPJ.buildDrawingCache();
                                Bitmap bmap = imgPJ.getDrawingCache();
                                
                                try {
                                    saveBitmap(getApplicationContext(), bmap, Bitmap.CompressFormat.JPEG, "image/*", personajes.getSelectedItem().toString());
                                } catch (IOException e) {
                                    Toast.makeText(Builds.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                ejecutar();
                            }
                            return true;
                        }
                    });
                }else if(position == 13){
                    toastAnemo(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.jean);
                    imgPJ.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            if(permissionHelper.hasPermission()){
                                //convertir imagen a bitmap
                                imgPJ.buildDrawingCache();
                                Bitmap bmap = imgPJ.getDrawingCache();
                                
                                try {
                                    saveBitmap(getApplicationContext(), bmap, Bitmap.CompressFormat.JPEG, "image/*", personajes.getSelectedItem().toString());
                                } catch (IOException e) {
                                    Toast.makeText(Builds.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                ejecutar();
                            }
                            return true;
                        }
                    });
                }else if(position == 14){
                    toastCryo(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.kaeya);
                    imgPJ.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            if(permissionHelper.hasPermission()){
                                //convertir imagen a bitmap
                                imgPJ.buildDrawingCache();
                                Bitmap bmap = imgPJ.getDrawingCache();
                                
                                try {
                                    saveBitmap(getApplicationContext(), bmap, Bitmap.CompressFormat.JPEG, "image/*", personajes.getSelectedItem().toString());
                                } catch (IOException e) {
                                    Toast.makeText(Builds.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                ejecutar();
                            }
                            return true;
                        }
                    });
                }else if(position == 15){
                    imgPJ.setImageResource(R.drawable.keqing);
                    imgPJ.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            if(permissionHelper.hasPermission()){
                                //convertir imagen a bitmap
                                imgPJ.buildDrawingCache();
                                Bitmap bmap = imgPJ.getDrawingCache();
                                
                                try {
                                    saveBitmap(getApplicationContext(), bmap, Bitmap.CompressFormat.JPEG, "image/*", personajes.getSelectedItem().toString());
                                } catch (IOException e) {
                                    Toast.makeText(Builds.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                ejecutar();
                            }
                            return true;
                        }
                    });
                }else if(position == 16){
                    imgPJ.setImageResource(R.drawable.kekingv2);
                    imgPJ.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            if(permissionHelper.hasPermission()){
                                //convertir imagen a bitmap
                                imgPJ.buildDrawingCache();
                                Bitmap bmap = imgPJ.getDrawingCache();

                                try {
                                    saveBitmap(getApplicationContext(), bmap, Bitmap.CompressFormat.JPEG, "image/*", personajes.getSelectedItem().toString());
                                } catch (IOException e) {
                                    Toast.makeText(Builds.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                ejecutar();
                            }
                            return true;
                        }
                    });
                }else if(position == 17){
                    toastPyro(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.klee);
                    imgPJ.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            if(permissionHelper.hasPermission()){
                                //convertir imagen a bitmap
                                imgPJ.buildDrawingCache();
                                Bitmap bmap = imgPJ.getDrawingCache();
                                
                                try {
                                    saveBitmap(getApplicationContext(), bmap, Bitmap.CompressFormat.JPEG, "image/*", personajes.getSelectedItem().toString());
                                } catch (IOException e) {
                                    Toast.makeText(Builds.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                ejecutar();
                            }
                            return true;
                        }
                    });
                }else if(position == 18){
                    toastElectro(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.lisa);
                    imgPJ.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            if(permissionHelper.hasPermission()){
                                //convertir imagen a bitmap
                                imgPJ.buildDrawingCache();
                                Bitmap bmap = imgPJ.getDrawingCache();
                                
                                try {
                                    saveBitmap(getApplicationContext(), bmap, Bitmap.CompressFormat.JPEG, "image/*", personajes.getSelectedItem().toString());
                                } catch (IOException e) {
                                    Toast.makeText(Builds.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                ejecutar();
                            }
                            return true;
                        }
                    });
                }else if(position == 19){
                    toastHydro(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.mona_v1);
                    imgPJ.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            if(permissionHelper.hasPermission()){
                                //convertir imagen a bitmap
                                imgPJ.buildDrawingCache();
                                Bitmap bmap = imgPJ.getDrawingCache();
                                
                                try {
                                    saveBitmap(getApplicationContext(), bmap, Bitmap.CompressFormat.JPEG, "image/*", personajes.getSelectedItem().toString());
                                } catch (IOException e) {
                                    Toast.makeText(Builds.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                ejecutar();
                            }
                            return true;
                        }
                    });
                }else if(position == 20){
                    toastHydro(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.mona_v2);
                    imgPJ.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            if(permissionHelper.hasPermission()){
                                //convertir imagen a bitmap
                                imgPJ.buildDrawingCache();
                                Bitmap bmap = imgPJ.getDrawingCache();
                                
                                try {
                                    saveBitmap(getApplicationContext(), bmap, Bitmap.CompressFormat.JPEG, "image/*", personajes.getSelectedItem().toString());
                                } catch (IOException e) {
                                    Toast.makeText(Builds.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                ejecutar();
                            }
                            return true;
                        }
                    });
                }else if(position == 21){
                    toastGeo(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.ningguang);
                    imgPJ.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            if(permissionHelper.hasPermission()){
                                //convertir imagen a bitmap
                                imgPJ.buildDrawingCache();
                                Bitmap bmap = imgPJ.getDrawingCache();
                                
                                try {
                                    saveBitmap(getApplicationContext(), bmap, Bitmap.CompressFormat.JPEG, "image/*", personajes.getSelectedItem().toString());
                                } catch (IOException e) {
                                    Toast.makeText(Builds.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                ejecutar();
                            }
                            return true;
                        }
                    });
                }else if(position == 22){
                    toastGeo(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.noelle);
                    imgPJ.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            if(permissionHelper.hasPermission()){
                                //convertir imagen a bitmap
                                imgPJ.buildDrawingCache();
                                Bitmap bmap = imgPJ.getDrawingCache();
                                
                                try {
                                    saveBitmap(getApplicationContext(), bmap, Bitmap.CompressFormat.JPEG, "image/*", personajes.getSelectedItem().toString());
                                } catch (IOException e) {
                                    Toast.makeText(Builds.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                ejecutar();
                            }
                            return true;
                        }
                    });
                }else if(position == 23){
                    toastCryo(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.qiqi);
                    imgPJ.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            if(permissionHelper.hasPermission()){
                                //convertir imagen a bitmap
                                imgPJ.buildDrawingCache();
                                Bitmap bmap = imgPJ.getDrawingCache();
                                
                                try {
                                    saveBitmap(getApplicationContext(), bmap, Bitmap.CompressFormat.JPEG, "image/*", personajes.getSelectedItem().toString());
                                } catch (IOException e) {
                                    Toast.makeText(Builds.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                ejecutar();
                            }
                            return true;
                        }
                    });
                }else if(position == 24){
                    toastElectro(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.razor);
                    imgPJ.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            if(permissionHelper.hasPermission()){
                                //convertir imagen a bitmap
                                imgPJ.buildDrawingCache();
                                Bitmap bmap = imgPJ.getDrawingCache();
                                
                                try {
                                    saveBitmap(getApplicationContext(), bmap, Bitmap.CompressFormat.JPEG, "image/*", personajes.getSelectedItem().toString());
                                } catch (IOException e) {
                                    Toast.makeText(Builds.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                ejecutar();
                            }
                            return true;
                        }
                    });
                }else if(position == 25){
                    toastAnemo(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.sucrose);
                    imgPJ.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            if(permissionHelper.hasPermission()){
                                //convertir imagen a bitmap
                                imgPJ.buildDrawingCache();
                                Bitmap bmap = imgPJ.getDrawingCache();
                                
                                try {
                                    saveBitmap(getApplicationContext(), bmap, Bitmap.CompressFormat.JPEG, "image/*", personajes.getSelectedItem().toString());
                                } catch (IOException e) {
                                    Toast.makeText(Builds.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                ejecutar();
                            }
                            return true;
                        }
                    });
                }else if(position == 26){
                    toastHydro(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.tartaglia);
                    imgPJ.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            if(permissionHelper.hasPermission()){
                                //convertir imagen a bitmap
                                imgPJ.buildDrawingCache();
                                Bitmap bmap = imgPJ.getDrawingCache();
                                
                                try {
                                    saveBitmap(getApplicationContext(), bmap, Bitmap.CompressFormat.JPEG, "image/*", personajes.getSelectedItem().toString());
                                } catch (IOException e) {
                                    Toast.makeText(Builds.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                ejecutar();
                            }
                            return true;
                        }
                    });
                }else if(position == 27){
                    toastAnemo(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.traveler_anemo);
                    imgPJ.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            if(permissionHelper.hasPermission()){
                                //convertir imagen a bitmap
                                imgPJ.buildDrawingCache();
                                Bitmap bmap = imgPJ.getDrawingCache();
                                
                                try {
                                    saveBitmap(getApplicationContext(), bmap, Bitmap.CompressFormat.JPEG, "image/*", personajes.getSelectedItem().toString());
                                } catch (IOException e) {
                                    Toast.makeText(Builds.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                ejecutar();
                            }
                            return true;
                        }
                    });
                }else if(position == 28){
                    toastAnemo(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.venti);
                    imgPJ.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            if(permissionHelper.hasPermission()){
                                //convertir imagen a bitmap
                                imgPJ.buildDrawingCache();
                                Bitmap bmap = imgPJ.getDrawingCache();
                                
                                try {
                                    saveBitmap(getApplicationContext(), bmap, Bitmap.CompressFormat.JPEG, "image/*", personajes.getSelectedItem().toString());
                                } catch (IOException e) {
                                    Toast.makeText(Builds.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                ejecutar();
                            }
                            return true;
                        }
                    });
                }else if(position == 29){
                    toastPyro(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.xiangling_v1);
                    imgPJ.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            if(permissionHelper.hasPermission()){
                                //convertir imagen a bitmap
                                imgPJ.buildDrawingCache();
                                Bitmap bmap = imgPJ.getDrawingCache();
                                
                                try {
                                    saveBitmap(getApplicationContext(), bmap, Bitmap.CompressFormat.JPEG, "image/*", personajes.getSelectedItem().toString());
                                } catch (IOException e) {
                                    Toast.makeText(Builds.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                ejecutar();
                            }
                            return true;
                        }
                    });
                }else if(position == 30){
                    toastPyro(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.xiangling_v2);
                    imgPJ.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            if(permissionHelper.hasPermission()){
                                //convertir imagen a bitmap
                                imgPJ.buildDrawingCache();
                                Bitmap bmap = imgPJ.getDrawingCache();
                                
                                try {
                                    saveBitmap(getApplicationContext(), bmap, Bitmap.CompressFormat.JPEG, "image/*", personajes.getSelectedItem().toString());
                                } catch (IOException e) {
                                    Toast.makeText(Builds.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                ejecutar();
                            }
                            return true;
                        }
                    });
                }else if(position == 31){
                    toastAnemo(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.xiao);
                    imgPJ.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            if(permissionHelper.hasPermission()){
                                //convertir imagen a bitmap
                                imgPJ.buildDrawingCache();
                                Bitmap bmap = imgPJ.getDrawingCache();
                                
                                try {
                                    saveBitmap(getApplicationContext(), bmap, Bitmap.CompressFormat.JPEG, "image/*", personajes.getSelectedItem().toString());
                                } catch (IOException e) {
                                    Toast.makeText(Builds.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                ejecutar();
                            }
                            return true;
                        }
                    });
                }else if(position == 32){
                    toastHydro(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.xingqiu_v1);
                    imgPJ.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            if(permissionHelper.hasPermission()){
                                //convertir imagen a bitmap
                                imgPJ.buildDrawingCache();
                                Bitmap bmap = imgPJ.getDrawingCache();
                                
                                try {
                                    saveBitmap(getApplicationContext(), bmap, Bitmap.CompressFormat.JPEG, "image/*", personajes.getSelectedItem().toString());
                                } catch (IOException e) {
                                    Toast.makeText(Builds.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                ejecutar();
                            }
                            return true;
                        }
                    });
                }else if(position == 33){
                    toastHydro(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.xingqiu_v2);
                    imgPJ.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            if(permissionHelper.hasPermission()){
                                //convertir imagen a bitmap
                                imgPJ.buildDrawingCache();
                                Bitmap bmap = imgPJ.getDrawingCache();
                                
                                try {
                                    saveBitmap(getApplicationContext(), bmap, Bitmap.CompressFormat.JPEG, "image/*", personajes.getSelectedItem().toString());
                                } catch (IOException e) {
                                    Toast.makeText(Builds.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                ejecutar();
                            }
                            return true;
                        }
                    });
                }else if(position == 34){
                    toastPyro(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.xinyan);
                    imgPJ.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            if(permissionHelper.hasPermission()){
                                //convertir imagen a bitmap
                                imgPJ.buildDrawingCache();
                                Bitmap bmap = imgPJ.getDrawingCache();
                                
                                try {
                                    saveBitmap(getApplicationContext(), bmap, Bitmap.CompressFormat.JPEG, "image/*", personajes.getSelectedItem().toString());
                                } catch (IOException e) {
                                    Toast.makeText(Builds.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                ejecutar();
                            }
                            return true;
                        }
                    });
                }else if(position == 35){
                    toastGeo(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.zhongli_v1);
                    imgPJ.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            if(permissionHelper.hasPermission()){
                                //convertir imagen a bitmap
                                imgPJ.buildDrawingCache();
                                Bitmap bmap = imgPJ.getDrawingCache();
                                
                                try {
                                    saveBitmap(getApplicationContext(), bmap, Bitmap.CompressFormat.JPEG, "image/*", personajes.getSelectedItem().toString());
                                } catch (IOException e) {
                                    Toast.makeText(Builds.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                ejecutar();
                            }
                            return true;
                        }
                    });
                }else if(position == 36){
                    toastGeo(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.zhongli_v2);
                    imgPJ.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            if(permissionHelper.hasPermission()){
                                //convertir imagen a bitmap
                                imgPJ.buildDrawingCache();
                                Bitmap bmap = imgPJ.getDrawingCache();
                                
                                try {
                                    saveBitmap(getApplicationContext(), bmap, Bitmap.CompressFormat.JPEG, "image/*", personajes.getSelectedItem().toString());
                                } catch (IOException e) {
                                    Toast.makeText(Builds.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                ejecutar();
                            }
                            return true;
                        }
                    });
                }else if(position == 37){
                    toastGeo(Builds.this, personajes.getSelectedItem().toString());
                    imgPJ.setImageResource(R.drawable.zhongli_v3);
                    imgPJ.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            if(permissionHelper.hasPermission()){
                                //convertir imagen a bitmap
                                imgPJ.buildDrawingCache();
                                Bitmap bmap = imgPJ.getDrawingCache();
                                
                                try {
                                    saveBitmap(getApplicationContext(), bmap, Bitmap.CompressFormat.JPEG, "image/*", personajes.getSelectedItem().toString());
                                } catch (IOException e) {
                                    Toast.makeText(Builds.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                ejecutar();
                            }
                            return true;
                        }
                    });
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
        banerPublicitario();
    }

    private void banerPublicitario(){
        MobileAds.initialize(Builds.this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) { }
        });
        publicidad = (AdView)findViewById(R.id.banerBuild);
        AdRequest adRequest = new AdRequest.Builder().build();
        publicidad.loadAd(adRequest);
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

    @Nullable
    private Uri saveBitmap(@NonNull final Context context, @NonNull final Bitmap bitmap,
                           @NonNull final Bitmap.CompressFormat format, @NonNull final String mimeType,
                           @NonNull final String displayName) throws IOException{
        final ContentValues  contentValues = new ContentValues();
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, displayName + ".jpg");
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, mimeType);
        contentValues.put(MediaStore.DownloadColumns.RELATIVE_PATH, "DCIM/Genshin Impact BUILDS");

        final ContentResolver resolver = context.getContentResolver();

        OutputStream stream = null;
        Uri uri = null;

        try{
            final Uri contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            uri = resolver.insert(contentUri, contentValues);

            if (uri == null){
                Toast.makeText(context, "uri == null", Toast.LENGTH_SHORT).show();
                throw new IOException("Fallo al crear.");
            }

            stream = resolver.openOutputStream(uri);

            if (stream == null){
                Toast.makeText(context, "stream == null", Toast.LENGTH_SHORT).show();
                throw new IOException("No se pudo obtener la salida.");
            }
            if (bitmap.compress(format, 95, stream) == false){
                Toast.makeText(context, "bitmap.compress(format, 95, stream) == false", Toast.LENGTH_SHORT).show();
                throw new IOException("Fallo al guardar el Bitmap.");
            }
        }catch (IOException e)        {
            if (uri != null)
            {
                resolver.delete(uri, null, null);
                Toast.makeText(context, "IOException", Toast.LENGTH_SHORT).show();
            }
            throw e;
        }
        finally{
            if (stream != null){
                stream.close();
                Toast.makeText(context, "¡La imagen se ha guardado con éxito!", Toast.LENGTH_SHORT).show();
            }
        }
        return uri;
    }
}