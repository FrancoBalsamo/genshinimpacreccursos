package com.frabasoft.genshinimpactrecursos.Actividades;

import androidx.annotation.NonNull;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.frabasoft.genshinimpactrecursos.Clases.Artefactos.Copa;
import com.frabasoft.genshinimpactrecursos.Clases.Artefactos.Corona;
import com.frabasoft.genshinimpactrecursos.Clases.Artefactos.Flor;
import com.frabasoft.genshinimpactrecursos.Clases.Artefactos.Pluma;
import com.frabasoft.genshinimpactrecursos.Clases.Artefactos.Reloj;
import com.frabasoft.genshinimpactrecursos.MainActivity;
import com.frabasoft.genshinimpactrecursos.R;
import com.frabasoft.genshinimpactrecursos.SQLiteGenshin.Procesos.DatosProcesosSqlite;
import com.master.permissionhelper.PermissionHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Objects;

public class VistaPrevia extends AppCompatActivity {
    private ArrayList<Flor> florArrayList;
    private ArrayList<Pluma> plumaArrayList;
    private ArrayList<Reloj> relojArrayList;
    private ArrayList<Copa> copaArrayList;
    private ArrayList<Corona> coronaArrayList;

    private TextView tvF, tvFl, tvFlo, tvFlor, tvFore;
    private TextView P, Pl, Plu, Plum, Pluma;
    private TextView RR, Re, Rel, Relo, Reloj;
    private TextView C, Co, Cop, Copa, Copas;
    private TextView tvC, tvCo, tvCor, tvCoro, tvCoron;

    private ImageView bkgPJ;

    private String nombrePersonaje = "";

    PermissionHelper permissionHelper;
    private final static String TAG = "ERRORTAG";

    private LinearLayout contenido;


    private String VistaPreviaDetalle = "VISTA PREVIA DE PERSONAJES: ";
    private DatosProcesosSqlite datosProcesosSqlite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_previa);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        ejecutar();

        contenido = (LinearLayout)findViewById(R.id.creado);

        nombrePersonaje = getIntent().getStringExtra("pj");

        tvF = (TextView)findViewById(R.id.tvF);
        tvFl = (TextView)findViewById(R.id.tvFl);
        tvFlo = (TextView)findViewById(R.id.tvFlo);
        tvFlor = (TextView)findViewById(R.id.tvFlor);
        tvFore = (TextView)findViewById(R.id.tvFlore);

        P = (TextView)findViewById(R.id.P);
        Pl = (TextView)findViewById(R.id.Pl);
        Plu = (TextView)findViewById(R.id.Plu);
        Plum = (TextView)findViewById(R.id.Plum);
        Pluma = (TextView)findViewById(R.id.Pluma);

        RR = (TextView)findViewById(R.id.R);
        Re = (TextView)findViewById(R.id.Re);
        Rel = (TextView)findViewById(R.id.Rel);
        Relo = (TextView)findViewById(R.id.Relo);
        Reloj = (TextView)findViewById(R.id.Reloj);

        C = (TextView)findViewById(R.id.C);
        Co = (TextView)findViewById(R.id.Co);
        Cop = (TextView)findViewById(R.id.Cop);
        Copa = (TextView)findViewById(R.id.Copa);
        Copas = (TextView)findViewById(R.id.Copas);

        tvC = (TextView)findViewById(R.id.tvC);
        tvCo = (TextView)findViewById(R.id.tvCo);
        tvCor = (TextView)findViewById(R.id.tvCor);
        tvCoro = (TextView)findViewById(R.id.tvCoro);
        tvCoron = (TextView)findViewById(R.id.tvCoron);

        bkgPJ = (ImageView)findViewById(R.id.bkgPJ);

        CargarDatosSQLite(nombrePersonaje);
        backgroundPJ(nombrePersonaje);
        contenido.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if(permissionHelper.hasPermission()){
                    GuardarLayout(VistaPrevia.this);
                }else{
                    ejecutar();
                }
            }
        });
    }

    private void CargarDatosSQLite(String personaje){
        DatosProcesosSqlite datosProcesosSqlite = new DatosProcesosSqlite(getApplicationContext());
        //flor
        florArrayList = datosProcesosSqlite.mostrarDatosDelPjFlor(personaje);
        for(int i = 0; i < florArrayList.size(); i++){
            if(tvF.getText().toString()=="" || tvF.getText().toString()==null || tvF.equals("")
                    || tvFl.getText().toString()=="" || tvFl.getText().toString()==null || tvFl.equals("")
                    || tvFlo.getText().toString()=="" || tvFlo.getText().toString()==null || tvFlo.equals("")
                    || tvFlor.getText().toString()=="" || tvFlor.getText().toString()==null || tvFlor.equals("")
                    || tvFore.getText().toString()=="" || tvFore.getText().toString()==null || tvFore.equals("")){
                limpiarTV();
            }else{
                tvF.setText(florArrayList.get(i).getPrincipal());
                tvFl.setText(florArrayList.get(i).getSecundarioA());
                tvFlo.setText(florArrayList.get(i).getSecundarioB());
                tvFlor.setText(florArrayList.get(i).getSecundarioC());
                tvFore.setText(florArrayList.get(i).getSecundarioD());
            }
        }
        //pluma
        plumaArrayList = datosProcesosSqlite.mostrarDatosDelPjPluma(personaje);
        for(int i = 0; i < plumaArrayList.size(); i++){
            if(P.getText().toString()=="" || P.getText().toString()==null || P.equals("")
                    || Pl.getText().toString()=="" || Pl.getText().toString()==null || Pl.equals("")
                    || Plu.getText().toString()=="" || Plu.getText().toString()==null || Plu.equals("")
                    || Plum.getText().toString()=="" || Plum.getText().toString()==null || Plum.equals("")
                    || Pluma.getText().toString()=="" || Pluma.getText().toString()==null || Pluma.equals("")){
                limpiarTV();
            }else{
                P.setText(plumaArrayList.get(i).getPrincipal());
                Pl.setText(plumaArrayList.get(i).getSecundarioA());
                Plu.setText(plumaArrayList.get(i).getSecundarioB());
                Plum.setText(plumaArrayList.get(i).getSecundarioC());
                Pluma.setText(plumaArrayList.get(i).getSecundarioD());
            }
        }
        //reloj
        relojArrayList = datosProcesosSqlite.mostrarDatosDelPjReloj(personaje);
        for(int i = 0; i < relojArrayList.size(); i++){
            if(RR.getText().toString()=="" || RR.getText().toString()==null || RR.equals("")
                    || Re.getText().toString()=="" || Re.getText().toString()==null || Re.equals("")
                    || Rel.getText().toString()=="" || Rel.getText().toString()==null || Rel.equals("")
                    || Relo.getText().toString()=="" || Relo.getText().toString()==null || Relo.equals("")
                    || Reloj.getText().toString()=="" || Reloj.getText().toString()==null || Reloj.equals("")){
                limpiarTV();
            }else{
                RR.setText(relojArrayList.get(i).getPrincipal());
                Re.setText(relojArrayList.get(i).getSecundarioA());
                Rel.setText(relojArrayList.get(i).getSecundarioB());
                Relo.setText(relojArrayList.get(i).getSecundarioC());
                Reloj.setText(relojArrayList.get(i).getSecundarioD());
            }
        }
        //copa
        copaArrayList = datosProcesosSqlite.mostrarDatosDelPjCopa(personaje);
        for(int i = 0; i < copaArrayList.size(); i++){
            if(C.getText().toString()=="" || C.getText().toString()==null || C.equals("")
                    || Co.getText().toString()=="" || Co.getText().toString()==null || Co.equals("")
                    || Cop.getText().toString()=="" || Cop.getText().toString()==null || Cop.equals("")
                    || Copa.getText().toString()=="" || Copa.getText().toString()==null || Copa.equals("")
                    || Copas.getText().toString()=="" || Copas.getText().toString()==null || Copas.equals("")){
                limpiarTV();
            }else{
                C.setText(copaArrayList.get(i).getPrincipal());
                Co.setText(copaArrayList.get(i).getSecundarioA());
                Cop.setText(copaArrayList.get(i).getSecundarioB());
                Copa.setText(copaArrayList.get(i).getSecundarioC());
                Copas.setText(copaArrayList.get(i).getSecundarioD());
            }
        }
        //corona
        coronaArrayList = datosProcesosSqlite.mostrarDatosDelPjCorona(personaje);
        for(int i = 0; i < coronaArrayList.size(); i++){
            if(tvC.getText().toString()=="" || tvC.getText().toString()==null || tvC.equals("")
                    || tvCo.getText().toString()=="" || tvCo.getText().toString()==null || tvCo.equals("")
                    || tvCor.getText().toString()=="" || tvCor.getText().toString()==null || tvCor.equals("")
                    || tvCoro.getText().toString()=="" || tvCoro.getText().toString()==null || tvCoro.equals("")
                    || tvCoron.getText().toString()=="" || tvCoron.getText().toString()==null || tvCoron.equals("")){
                limpiarTV();
            }else{
                tvC.setText(coronaArrayList.get(i).getPrincipal());
                tvCo.setText(coronaArrayList.get(i).getSecundarioA());
                tvCor.setText(coronaArrayList.get(i).getSecundarioB());
                tvCoro.setText(coronaArrayList.get(i).getSecundarioC());
                tvCoron.setText(coronaArrayList.get(i).getSecundarioD());
            }
        }
    }

    private void limpiarTV() {
        //flor
        tvF.setText("");
        tvFl.setText("");
        tvFlo.setText("");
        tvFlor.setText("");
        tvFore.setText("");
        //pluma
        P.setText("");
        Pl.setText("");
        Plu.setText("");
        Plum.setText("");
        Pluma.setText("");
        //reloj
        RR.setText("");
        Re.setText("");
        Rel.setText("");
        Relo.setText("");
        Reloj.setText("");
        //copa
        C.setText("");
        Co.setText("");
        Cop.setText("");
        Copa.setText("");
        Copas.setText("");
        //corona
        tvC.setText("");
        tvCo.setText("");
        tvCor.setText("");
        tvCoro.setText("");
        tvCoron.setText("");
    }

    private void backgroundPJ(String nombre){
        if(nombre == "Albedo" || nombre.equals("Albedo")){
            bkgPJ.setImageResource(R.drawable.albedobuilds);
        } else if(nombre == "Amber" || nombre.equals("Amber")){
            bkgPJ.setImageResource(R.drawable.amberbuilds);
        } else if(nombre == "Barbara" || nombre.equals("Barbara")){
            bkgPJ.setImageResource(R.drawable.barbarabuilds);
        } else if(nombre == "Beidou" || nombre.equals("Beidou")){
            bkgPJ.setImageResource(R.drawable.beidoubuilds);
        } else if(nombre == "Bennet" || nombre.equals("Bennet")){
            bkgPJ.setImageResource(R.drawable.bennetbuilds);
        } else if(nombre == "Chongyun" || nombre.equals("Chongyun")){
            bkgPJ.setImageResource(R.drawable.chongyunbuilds);
        } else if(nombre == "Diluc" || nombre.equals("Diluc")){
            bkgPJ.setImageResource(R.drawable.dilucbuilds);
        } else if(nombre == "Diona" || nombre.equals("Diona")){
            bkgPJ.setImageResource(R.drawable.dionabuilds);
        } else if(nombre == "Fischl" || nombre.equals("Fischl")){
            bkgPJ.setImageResource(R.drawable.fischlbuilds);
        } else if(nombre == "Ganyu" || nombre.equals("Ganyu")){
            bkgPJ.setImageResource(R.drawable.ganyubuilds);
        } else if(nombre == "Hu Tao" || nombre.equals("Hu Tao")){
            bkgPJ.setImageResource(R.drawable.hutaobuilds);
        } else if(nombre == "Jean" || nombre.equals("Jean")){
            bkgPJ.setImageResource(R.drawable.jeanbuilds);
        } else if(nombre == "Kaeya" || nombre.equals("Kaeya")){
            bkgPJ.setImageResource(R.drawable.kaeyabuilds);
        } else if(nombre == "Keqing" || nombre.equals("Keqing")){
            bkgPJ.setImageResource(R.drawable.keqingbuilds);
        } else if(nombre == "Klee" || nombre.equals("Klee")){
            bkgPJ.setImageResource(R.drawable.kleebuilds);
        } else if(nombre == "Lisa" || nombre.equals("Lisa")){
            bkgPJ.setImageResource(R.drawable.lisabuilds);
        } else if(nombre == "Mona" || nombre.equals("Mona")){
            bkgPJ.setImageResource(R.drawable.monabuilds);
        } else if(nombre == "Ninguang" || nombre.equals("Ninguang")){
            bkgPJ.setImageResource(R.drawable.ningguangbuilds);
        } else if(nombre == "Noelle" || nombre.equals("Noelle")){
            bkgPJ.setImageResource(R.drawable.noellebuilds);
        } else if(nombre == "Qiqi" || nombre.equals("Qiqi")){
            bkgPJ.setImageResource(R.drawable.qiqibuilds);
        } else if(nombre == "Razor" || nombre.equals("Razor")){
            bkgPJ.setImageResource(R.drawable.razorbuilds);
        } else if(nombre == "Rosaria" || nombre.equals("Rosaria")){
            bkgPJ.setImageResource(R.drawable.rosariabuilds);
        } else if(nombre == "Sucrose" || nombre.equals("Sucrose")){
            bkgPJ.setImageResource(R.drawable.sacarosabuilds);
        } else if(nombre == "Tartaglia" || nombre.equals("Tartaglia")){
            bkgPJ.setImageResource(R.drawable.tartagliabuilds);
        } else if(nombre == "Venti" || nombre.equals("Venti")){
            bkgPJ.setImageResource(R.drawable.ventibuilds);
        } else if(nombre == "Xianling" || nombre.equals("Xianling")){
            bkgPJ.setImageResource(R.drawable.xianlingbuilds);
        } else if(nombre == "Xiao" || nombre.equals("Xiao")){
            bkgPJ.setImageResource(R.drawable.xiaobuilds);
        } else if(nombre == "Xingqiu" || nombre.equals("Xingqiu")){
            bkgPJ.setImageResource(R.drawable.xingqiubuilds);
        } else if(nombre == "Xinyan" || nombre.equals("Xinyan")){
            bkgPJ.setImageResource(R.drawable.xinyanbuilds);
        } else if(nombre == "Zhongli" || nombre.equals("Zhongli")){
            bkgPJ.setImageResource(R.drawable.zhonglibuilds);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void GuardarLayout(Context context) {
        contenido.setDrawingCacheEnabled(true);
        contenido.buildDrawingCache();
        Bitmap bmap = contenido.getDrawingCache();
        try {
            guardarImagenMedoto(bmap);
            //saveBitmapAsNewFile(bmap);
            agregarLineasFicheroTXT(VistaPreviaDetalle, nombrePersonaje);
        } catch (Exception e) {
            Log.d("GuardarLayout", "GuardarLayout: " + e.getMessage());
            e.printStackTrace();
        } finally {
            contenido.destroyDrawingCache();
        }
    }

    private void guardarImagenMedoto(Bitmap bitmap){
        File ruta = new File(Environment.DIRECTORY_PICTURES + File.separator + "Genshin Impact Mis Builds");
        if (!ruta.exists()) {
            File wallpaperDirectory = new File("/sdcard/Pictures/Genshin Impact Mis Builds");
            wallpaperDirectory.mkdirs();
        }

        File archivo = new File("/sdcard/Pictures/Genshin Impact Mis Builds", nombrePersonaje + ".jpg");
        if (archivo.exists()) {
            archivo.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(archivo);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            Toast.makeText(this, "¡Se ha guardado con éxito tu build de " + nombrePersonaje + "!", Toast.LENGTH_SHORT).show();
            Log.d("saveImagesTwo", "try: " + "\nRuta: " + ruta + "\nArchivo: " + archivo);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "¡Ha ocurrido un error al intentar guardar tu build de " + nombrePersonaje + "!", Toast.LENGTH_SHORT).show();
            Log.d("saveImagesTwo", "Catch: " + e.getMessage() + "\nRuta: " + ruta + "\nArchivo: " + archivo);
        }
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        VistaPrevia.this.finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void agregarLineasFicheroTXT(String vistaPrevia, String pj) throws IOException {
        String detalleRutaYArchivoDescargado = "Se ha descargado el archivo: "
                + pj + ".jpg" + "\n"
                + "Ubicación del archivo: "
                + Environment.DIRECTORY_PICTURES
                + File.separator
                + "Genshin Impact Mis Builds";
        datosProcesosSqlite = new DatosProcesosSqlite(VistaPrevia.this);
        datosProcesosSqlite.copiarArchivo(vistaPrevia + detalleRutaYArchivoDescargado);
    }

    private void saveBitmapAsNewFile(Bitmap target){

        // Define directory names
        String fileName = nombrePersonaje + "_new_file.jpg"; /* or Replace with yout filename*/
        String root = "/sdcard/Pictures/Genshin Impact Mis Builds";
        OutputStream outputStream = null;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){

            ContentResolver resolver = getApplicationContext().getContentResolver();
            ContentValues contentValues = new ContentValues(); // or your content values
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, fileName); // avoid this column if DisplayName is already set
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg");
            contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + root);
            Uri imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);


            try {
                resolver.notifyChange(Objects.requireNonNull(imageUri), null);
                outputStream = resolver.openOutputStream(Objects.requireNonNull(imageUri));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Log.d("PRUEBATAG", "=> Error creating directory on path " + imageUri);
            }
        }
        else {
            if (!isExternalStorageReadable() || !isExternalStorageWritable()) {
                Log.d("PRUEBATAG", "=> Cannot read or write on External directory ");
                return;
            }

            String file_path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + root;
            File dir = new File(file_path);
            if (!dir.exists() && !dir.mkdirs()) {
                Log.d("Error in" , "=> Error creating directory on path " + file_path);
                return;
            }

            File file = new File(dir, fileName);

            if(file.exists())
                return;

            try {
                outputStream = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        try{
            if(outputStream != null) {
                target.compress(Bitmap.CompressFormat.JPEG, 85, outputStream);
                outputStream.flush();
                outputStream.close();
            }
        } catch (IOException e) {
            Log.d("Error in", "=> Error creating image on path");
            e.printStackTrace();
        }

    }

    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }
}