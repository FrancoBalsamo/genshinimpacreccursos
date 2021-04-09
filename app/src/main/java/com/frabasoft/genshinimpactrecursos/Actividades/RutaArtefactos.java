package com.frabasoft.genshinimpactrecursos.Actividades;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
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

import com.frabasoft.genshinimpactrecursos.R;
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
                                try {
                                    saveBitmap(getApplicationContext(), bmap, Bitmap.CompressFormat.JPEG, "image/*", spinnerRutas.getSelectedItem().toString());
                                } catch (IOException e) {
                                    Toast.makeText(RutaArtefactos.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
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
                                try {
                                    saveBitmap(getApplicationContext(), bmap, Bitmap.CompressFormat.JPEG, "image/*", spinnerRutas.getSelectedItem().toString());
                                } catch (IOException e) {
                                    Toast.makeText(RutaArtefactos.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
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
                                try {
                                    saveBitmap(getApplicationContext(), bmap, Bitmap.CompressFormat.JPEG, "image/*", spinnerRutas.getSelectedItem().toString());
                                } catch (IOException e) {
                                    Toast.makeText(RutaArtefactos.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
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
                                try {
                                    saveBitmap(getApplicationContext(), bmap, Bitmap.CompressFormat.JPEG, "image/*", spinnerRutas.getSelectedItem().toString());
                                } catch (IOException e) {
                                    Toast.makeText(RutaArtefactos.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
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
                                try {
                                    saveBitmap(getApplicationContext(), bmap, Bitmap.CompressFormat.JPEG, "image/*", spinnerRutas.getSelectedItem().toString());
                                } catch (IOException e) {
                                    Toast.makeText(RutaArtefactos.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
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

    @Nullable
    private Uri saveBitmap(@NonNull final Context context, @NonNull final Bitmap bitmap,
                           @NonNull final Bitmap.CompressFormat format, @NonNull final String mimeType,
                           @NonNull final String displayName) throws IOException {
        final ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, displayName + ".jpg");
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, mimeType);
        contentValues.put(MediaStore.DownloadColumns.RELATIVE_PATH, "DCIM/Genshin Impact Artefactos");

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
}