package com.frabasoft.genshinimpactrecursos.Actividades;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.frabasoft.genshinimpactrecursos.Clases.Armas.Armas;
import com.frabasoft.genshinimpactrecursos.Clases.Artefactos.Copa;
import com.frabasoft.genshinimpactrecursos.Clases.Artefactos.Corona;
import com.frabasoft.genshinimpactrecursos.Clases.Artefactos.Flor;
import com.frabasoft.genshinimpactrecursos.Clases.Artefactos.Pluma;
import com.frabasoft.genshinimpactrecursos.Clases.Artefactos.Reloj;
import com.frabasoft.genshinimpactrecursos.R;
import com.frabasoft.genshinimpactrecursos.SQLiteGenshin.Procesos.DatosProcesosSqlite;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.master.permissionhelper.PermissionHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class VistaPrevia extends AppCompatActivity {
    private ArrayList<Flor> florArrayList;
    private ArrayList<Pluma> plumaArrayList;
    private ArrayList<Reloj> relojArrayList;
    private ArrayList<Copa> copaArrayList;
    private ArrayList<Corona> coronaArrayList;
    private ArrayList<Armas> armasArrayList;

    private TextView tvf, tvFl, tvFlo, tvFlor, tvFore;
    private TextView P, Pl, Plu, Plum, Pluma;
    private TextView RR, Re, Rel, Relo, Reloj;
    private TextView C, Co, Cop, Copa, Copas;
    private TextView tvC, tvCo, tvCor, tvCoro, tvCoron;
    private ImageView ivArmaVP;

    private String nombrePersonaje = "";

    PermissionHelper permissionHelper;
    private final static String TAG = "ERRORTAG";

    private LinearLayout contenido;


    private String VistaPreviaDetalle = "VISTA PREVIA DE PERSONAJES: ";
    private DatosProcesosSqlite datosProcesosSqlite;

    //para el ad de la descarga de la imagen
    private static final String AD_UNIT_ID = "ca-app-pub-4467142756516555/7442029029";
    private InterstitialAd interstitialAd;

    //para los soniditos
    private MediaPlayer salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_previa);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        salir = MediaPlayer.create(getApplicationContext(), R.raw.salir);

        ejecutar();

        MobileAds.initialize(this, initializationStatus -> {});
        loadAd();

        contenido = (LinearLayout)findViewById(R.id.creado);

        nombrePersonaje = getIntent().getStringExtra("pj");

        tvf = (TextView)findViewById(R.id.tvF);
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

        ivArmaVP = (ImageView)findViewById(R.id.ivArmaVP);

        CargarDatosSQLite(nombrePersonaje);
        backgroundPJ(nombrePersonaje);
        contenido.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if(permissionHelper.hasPermission()){
                    GuardarLayout();
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
            if(tvf.getText().toString()=="" || tvf.getText().toString()==null || tvf.equals("")
                    || tvFl.getText().toString()=="" || tvFl.getText().toString()==null || tvFl.equals("")
                    || tvFlo.getText().toString()=="" || tvFlo.getText().toString()==null || tvFlo.equals("")
                    || tvFlor.getText().toString()=="" || tvFlor.getText().toString()==null || tvFlor.equals("")
                    || tvFore.getText().toString()=="" || tvFore.getText().toString()==null || tvFore.equals("")){
                limpiarTV();
            }else{
                tvf.setText(florArrayList.get(i).getPrincipal());
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
        //arma
        armasArrayList = datosProcesosSqlite.mostrarDatosDelPjArma(personaje);
        for(int i = 0; i < armasArrayList.size(); i++){
            if(armasArrayList.get(i).getArma() == 0) {
                Toast.makeText(this, "¡No has guardado ningún arma!", Toast.LENGTH_SHORT).show();
            }else{
                int valorImagen = armasArrayList.get(i).getArma();
                ivArmaVP.setImageResource(valorImagen);
            }
        }
    }

    private void limpiarTV() {
        //flor
        tvf.setText("");
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
            contenido.setBackgroundResource(R.drawable.albedovp);
        } else if(nombre == "Amber" || nombre.equals("Amber")){
            contenido.setBackgroundResource(R.drawable.ambervp);
        } else if(nombre == "Barbara" || nombre.equals("Barbara")){
            contenido.setBackgroundResource(R.drawable.barbaravp);
        } else if(nombre == "Beidou" || nombre.equals("Beidou")){
            contenido.setBackgroundResource(R.drawable.beidouvp);
        } else if(nombre == "Bennet" || nombre.equals("Bennet")){
            contenido.setBackgroundResource(R.drawable.bennetvp);
        } else if(nombre == "Chongyun" || nombre.equals("Chongyun")){
            contenido.setBackgroundResource(R.drawable.chongyunvp);
        } else if(nombre == "Diluc" || nombre.equals("Diluc")){
            contenido.setBackgroundResource(R.drawable.dilucvp);
        } else if(nombre == "Diona" || nombre.equals("Diona")){
            contenido.setBackgroundResource(R.drawable.dionavp);
        } else if(nombre == "Fischl" || nombre.equals("Fischl")){
            contenido.setBackgroundResource(R.drawable.fischlvp);
        } else if(nombre == "Ganyu" || nombre.equals("Ganyu")){
            contenido.setBackgroundResource(R.drawable.ganyuvp);
        } else if(nombre == "Hu Tao" || nombre.equals("Hu Tao")){
            contenido.setBackgroundResource(R.drawable.hutaovp);
        } else if(nombre == "Jean" || nombre.equals("Jean")){
            contenido.setBackgroundResource(R.drawable.jeanvp);
        } else if(nombre == "Kaeya" || nombre.equals("Kaeya")){
            contenido.setBackgroundResource(R.drawable.kaeyavp);
        } else if(nombre == "Keqing" || nombre.equals("Keqing")){
            contenido.setBackgroundResource(R.drawable.kekingvp);
        } else if(nombre == "Klee" || nombre.equals("Klee")){
            contenido.setBackgroundResource(R.drawable.kleevp);
        } else if(nombre == "Lisa" || nombre.equals("Lisa")){
            contenido.setBackgroundResource(R.drawable.lisavp);
        } else if(nombre == "Mona" || nombre.equals("Mona")){
            contenido.setBackgroundResource(R.drawable.monavp);
        } else if(nombre == "Ninguang" || nombre.equals("Ninguang")){
            contenido.setBackgroundResource(R.drawable.ningguangvp);
        } else if(nombre == "Noelle" || nombre.equals("Noelle")){
            contenido.setBackgroundResource(R.drawable.noellevp);
        } else if(nombre == "Qiqi" || nombre.equals("Qiqi")){
            contenido.setBackgroundResource(R.drawable.qiqivp);
        } else if(nombre == "Razor" || nombre.equals("Razor")){
            contenido.setBackgroundResource(R.drawable.razorvp);
        } else if(nombre == "Rosaria" || nombre.equals("Rosaria")){
            contenido.setBackgroundResource(R.drawable.rosariavp);
        } else if(nombre == "Sucrose" || nombre.equals("Sucrose")){
            contenido.setBackgroundResource(R.drawable.sucrosevp);
        } else if(nombre == "Tartaglia" || nombre.equals("Tartaglia")){
            contenido.setBackgroundResource(R.drawable.tartagliavp);
        } else if(nombre == "Venti" || nombre.equals("Venti")){
            contenido.setBackgroundResource(R.drawable.ventivp);
        } else if(nombre == "Xianling" || nombre.equals("Xianling")){
            contenido.setBackgroundResource(R.drawable.xianglingvp);
        } else if(nombre == "Xiao" || nombre.equals("Xiao")){
            contenido.setBackgroundResource(R.drawable.xiaovp);
        } else if(nombre == "Xingqiu" || nombre.equals("Xingqiu")){
            contenido.setBackgroundResource(R.drawable.xingqiuvp);
        } else if(nombre == "Xinyan" || nombre.equals("Xinyan")){
            contenido.setBackgroundResource(R.drawable.xinyanvp);
        } else if(nombre == "Zhongli" || nombre.equals("Zhongli")){
            contenido.setBackgroundResource(R.drawable.zhonglivp);
        }
    }

    private void GuardarLayout() {
        contenido.setDrawingCacheEnabled(true);
        contenido.buildDrawingCache();
        Bitmap bmap = contenido.getDrawingCache();
        try {
            guardarImagenMedoto(bmap);
            agregarLineasFicheroTXT(VistaPreviaDetalle, nombrePersonaje);
        } catch (Exception e) {
            Log.d("GuardarLayout", "GuardarLayout: " + e.getMessage());
            e.printStackTrace();
        } finally {
            contenido.destroyDrawingCache();
        }
    }

    private void guardarImagenMedoto(Bitmap bitmap){
        showInterstitial();
        File rutaMisBuilds = new File(Environment.getExternalStorageDirectory() + "/Genshin Impact Recursos/Genshin Impact Mis Builds/");
        if (!rutaMisBuilds.exists()) {
            File rutaMisBuildsCrear = new File(Environment.getExternalStorageDirectory() + "/Genshin Impact Recursos/Genshin Impact Mis Builds/");
            rutaMisBuildsCrear.mkdirs();
        }

        File archivo = new File(rutaMisBuilds, nombrePersonaje + ".jpg");
        if (archivo.exists()) {
            archivo.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(archivo);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            Toast.makeText(this, "¡Se ha guardado con éxito tu build de " + nombrePersonaje + "!", Toast.LENGTH_SHORT).show();
            Log.d("saveImagesTwo", "try: " + "\nRuta: " + rutaMisBuilds + "\nArchivo: " + archivo);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "¡Ha ocurrido un error al intentar guardar tu build de " + nombrePersonaje + "!", Toast.LENGTH_SHORT).show();
            Log.d("saveImagesTwo", "Catch: " + e.getMessage() + "\nRuta: " + rutaMisBuilds + "\nArchivo: " + archivo);
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
        salir.start();
    }

    private void agregarLineasFicheroTXT(String vistaPrevia, String pj) throws IOException {
        String detalleRutaYArchivoDescargado = "Se ha descargado el archivo: "
                + pj + ".jpg" + "\n"
                + "Ubicación del archivo: sdcard/Genshin Impact Recursos/Genshin Impact Mis Builds";
        datosProcesosSqlite = new DatosProcesosSqlite(VistaPrevia.this);
        datosProcesosSqlite.copiarArchivo(vistaPrevia + detalleRutaYArchivoDescargado);
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
                        VistaPrevia.this.interstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded" + interstitialAd);
                        interstitialAd.setFullScreenContentCallback(
                                new FullScreenContentCallback() {
                                    @Override
                                    public void onAdDismissedFullScreenContent() {
                                        // Called when fullscreen content is dismissed.
                                        // Make sure to set your reference to null so you don't
                                        // show it a second time.
                                        VistaPrevia.this.interstitialAd = null;
                                        Log.d("TAG", "The ad was dismissed.");
                                    }

                                    @Override
                                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                                        // Called when fullscreen content failed to show.
                                        // Make sure to set your reference to null so you don't
                                        // show it a second time.
                                        VistaPrevia.this.interstitialAd = null;
                                        Log.d("TAG", "The ad failed to show." + adError);
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
            Toast.makeText(this, "No se ha cargado la publicidad.", Toast.LENGTH_SHORT).show();
        }
    }
}