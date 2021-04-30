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
import android.media.MediaPlayer;
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

import com.frabasoft.genshinimpactrecursos.MainActivity;
import com.frabasoft.genshinimpactrecursos.Preferencias.PreferenciaSonidosEntrar;
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

    //para el ad de la descarga de la imagen
    private static final String AD_UNIT_ID = "ca-app-pub-4467142756516555/7442029029";
    private InterstitialAd interstitialAd;

    //para los soniditos
    private MediaPlayer salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rutasartefactos);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        salir = MediaPlayer.create(getApplicationContext(), R.raw.salir);

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

        spinnerRutas.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_color_text, rutasString));
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
        loadAd();
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
        MainActivity mainActivity = new MainActivity();
        if(traerValorChk(this) == 1){
            salir.start();
        }
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
        showInterstitial();
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

    private void agregarLineasFicheroTXT(String ruta, String archivo) throws IOException {
        String detalleRutaYArchivoDescargado = "Se ha descargado el archivo: "
                + archivo + ".jpg" + "\n"
                + "Ubicación del archivo: sdcard/Genshin Impact Recursos/Genshin Impact Ruta Artefactos";
        datosProcesosSqlite = new DatosProcesosSqlite(RutaArtefactos.this);
        datosProcesosSqlite.copiarArchivo(ruta + detalleRutaYArchivoDescargado);
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
                        RutaArtefactos.this.interstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");
                        interstitialAd.setFullScreenContentCallback(
                                new FullScreenContentCallback() {
                                    @Override
                                    public void onAdDismissedFullScreenContent() {
                                        // Called when fullscreen content is dismissed.
                                        // Make sure to set your reference to null so you don't
                                        // show it a second time.
                                        RutaArtefactos.this.interstitialAd = null;
                                        Log.d("TAG", "The ad was dismissed.");
                                    }

                                    @Override
                                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                                        // Called when fullscreen content failed to show.
                                        // Make sure to set your reference to null so you don't
                                        // show it a second time.
                                        RutaArtefactos.this.interstitialAd = null;
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
            Toast.makeText(this, "¡No se ha podido cargar la publicidad!", Toast.LENGTH_SHORT).show();
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