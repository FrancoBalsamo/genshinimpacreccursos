package com.frabasoft.genshinimpactrecursos.Actividades;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;

public class RutaArtefactos extends AppCompatActivity {
    AdView publicidad;
    Spinner spinnerRutas;
    TextView descargaArtefactos;
    String[] rutasString = {"Seleccione", "Liyue - Ruta A", "Liyue - Ruta B",
            "Liyue - Ruta C", "Liyue - Ruta D", "Liyue - Ruta E"};
    private TouchImageView imgRutas;
    private DecimalFormat df;
    PermissionHelper permissionHelper;
    private final static String TAG = "ERRORTAG";
    private DatosProcesosSqlite datosProcesosSqlite;
    private String buildsDetalle = "RUTA DE ARTEFACTOS: ";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rutasartefactos);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ejecutar();

        MobileAds.initialize(RutaArtefactos.this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) { }
        });
        publicidad = (AdView)findViewById(R.id.banerRuta);
        AdRequest adRequest = new AdRequest.Builder().build();
        publicidad.loadAd(adRequest);

        spinnerRutas = (Spinner)findViewById(R.id.spinnerRutas);
        descargaArtefactos = (TextView)findViewById(R.id.descargaArtefactos);
        descargaArtefactos.setText("**Puedes descargar las imágenes presionando por unos segundos sobre la misma.");

        df = new DecimalFormat("#.##");
        imgRutas = (TouchImageView) findViewById(R.id.imgRutas);

        spinnerRutas.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, rutasString));
        spinnerRutas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    imgRutas.setImageResource(R.drawable.wallpaper);
                }else if(position == 1){
                    imgRutas.setImageResource(R.drawable.ruta_cero);
                    imgRutas.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            if(permissionHelper.hasPermission()){
                                //convertir imagen a bitmap
                                imgRutas.buildDrawingCache();
                                Bitmap bmap = imgRutas.getDrawingCache();
                                guardarImagenMedoto(bmap, spinnerRutas.getSelectedItem().toString());
                                try {
                                    agregarLineasFicheroTXT(buildsDetalle, spinnerRutas.getSelectedItem().toString());
                                } catch (IOException ioException) {
                                    ioException.printStackTrace();
                                }
                            }else{
                                ejecutar();
                            }
                            return true;
                        }
                    });
                }else if(position == 2){
                    imgRutas.setImageResource(R.drawable.ruta_ceroa);
                    imgRutas.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            if(permissionHelper.hasPermission()){
                                //convertir imagen a bitmap
                                imgRutas.buildDrawingCache();
                                Bitmap bmap = imgRutas.getDrawingCache();
                                guardarImagenMedoto(bmap, spinnerRutas.getSelectedItem().toString());
                                try {
                                    agregarLineasFicheroTXT(buildsDetalle, spinnerRutas.getSelectedItem().toString());
                                } catch (IOException ioException) {
                                    ioException.printStackTrace();
                                }
                            }else{
                                ejecutar();
                            }
                            return true;
                        }
                    });
                }else if(position == 3){
                    imgRutas.setImageResource(R.drawable.ruta_cerob);
                    imgRutas.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            if(permissionHelper.hasPermission()){
                                //convertir imagen a bitmap
                                imgRutas.buildDrawingCache();
                                Bitmap bmap = imgRutas.getDrawingCache();
                                guardarImagenMedoto(bmap, spinnerRutas.getSelectedItem().toString());
                                try {
                                    agregarLineasFicheroTXT(buildsDetalle, spinnerRutas.getSelectedItem().toString());
                                } catch (IOException ioException) {
                                    ioException.printStackTrace();
                                }
                            }else{
                                ejecutar();
                            }
                            return true;
                        }
                    });
                }else if(position == 4){
                    imgRutas.setImageResource(R.drawable.ruta_ceroc);
                    imgRutas.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            if(permissionHelper.hasPermission()){
                                //convertir imagen a bitmap
                                imgRutas.buildDrawingCache();
                                Bitmap bmap = imgRutas.getDrawingCache();
                                guardarImagenMedoto(bmap, spinnerRutas.getSelectedItem().toString());
                                try {
                                    agregarLineasFicheroTXT(buildsDetalle, spinnerRutas.getSelectedItem().toString());
                                } catch (IOException ioException) {
                                    ioException.printStackTrace();
                                }
                            }else{
                                ejecutar();
                            }
                            return true;
                        }
                    });
                }else if(position == 5){
                    imgRutas.setImageResource(R.drawable.ruta_cerod);
                    imgRutas.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            if(permissionHelper.hasPermission()){
                                //convertir imagen a bitmap
                                imgRutas.buildDrawingCache();
                                Bitmap bmap = imgRutas.getDrawingCache();
                                guardarImagenMedoto(bmap, spinnerRutas.getSelectedItem().toString());
                                try {
                                    agregarLineasFicheroTXT(buildsDetalle, spinnerRutas.getSelectedItem().toString());
                                } catch (IOException ioException) {
                                    ioException.printStackTrace();
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
        MobileAds.initialize(RutaArtefactos.this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) { }
        });
        publicidad = (AdView)findViewById(R.id.banerRuta);
        AdRequest adRequest = new AdRequest.Builder().build();
        publicidad.loadAd(adRequest);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        RutaArtefactos.this.finish();
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

    private void guardarImagenMedoto(Bitmap bitmap, String rutaDetalle){
        File rutaArtefactos = new File(Environment.getExternalStorageDirectory() + "/Genshin Impact Recursos/Genshin Impact Ruta Artefactos");
        if (!rutaArtefactos.exists()) {
            File rutaArtefactosCrear = new File(Environment.getExternalStorageDirectory() + "/Genshin Impact Recursos/Genshin Impact Ruta Artefactos");
            rutaArtefactosCrear.mkdirs();
        }

        File archivo = new File(rutaArtefactos, rutaDetalle + ".jpg");
        if (archivo.exists()) {
            archivo.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(archivo);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            Toast.makeText(this, "¡Se ha guardado con éxito tu ruta!", Toast.LENGTH_SHORT).show();
            Log.d("saveImagesTwo", "try: " + "\nRuta: " + rutaArtefactos + "\nArchivo: " + archivo);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "¡Ha ocurrido un error al intentar guardar tu ruta!", Toast.LENGTH_SHORT).show();
            Log.d("saveImagesTwo", "Catch: " + e.getMessage() + "\nRuta: " + rutaArtefactos + "\nArchivo: " + archivo);
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void agregarLineasFicheroTXT(String ruta, String archivo) throws IOException {
        String detalleRutaYArchivoDescargado = "Se ha descargado el archivo: "
                + archivo + ".jpg" + "\n"
                + "Ubicación del archivo: sdcard/Genshin Impact Recursos/Genshin Impact Ruta Artefactos";
        datosProcesosSqlite = new DatosProcesosSqlite(RutaArtefactos.this);
        datosProcesosSqlite.copiarArchivo(ruta + detalleRutaYArchivoDescargado);
    }
}