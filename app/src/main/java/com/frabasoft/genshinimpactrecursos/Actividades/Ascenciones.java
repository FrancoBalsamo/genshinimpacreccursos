package com.frabasoft.genshinimpactrecursos.Actividades;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.frabasoft.genshinimpactrecursos.Adaptadores.AdaptadorPaginadorAscensiones;
import com.frabasoft.genshinimpactrecursos.Preferencias.PreferenciaSonidosEntrar;
import com.frabasoft.genshinimpactrecursos.R;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.master.permissionhelper.PermissionHelper;

import java.text.DecimalFormat;

public class Ascenciones extends AppCompatActivity {
    private final static String TAG = "ERRORASCEN";
    private int[] imagenesAscensiones = new int[]{
            R.drawable.ascension_a, R.drawable.ascension_b, R.drawable.ascension_c,
            R.drawable.ascension_d, R.drawable.ascension_e, R.drawable.ascension_f,
            R.drawable.ascension_g, R.drawable.ascension_h, R.drawable.ascencion_i
    };
    private ViewPager viewPager;
    private MediaPlayer salir;
    private PermissionHelper permissionHelper;

    //publicidad
    //para el ad de la descarga de la imagen
    private static final String AD_UNIT_ID = "ca-app-pub-4467142756516555/7442029029";
    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ascenciones);
        ejecutar();
        loadAd();

        viewPager = findViewById(R.id.viewPagerAscencion);
        salir = MediaPlayer.create(getApplicationContext(), R.raw.salir);

        if (viewPager != null) {
            viewPager.setAdapter(new AdaptadorPaginadorAscensiones(this, imagenesAscensiones));
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

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////PUBLICIDAD
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
                        Ascenciones.this.interstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");
                        interstitialAd.setFullScreenContentCallback(
                                new FullScreenContentCallback() {
                                    @Override
                                    public void onAdDismissedFullScreenContent() {
                                        // Called when fullscreen content is dismissed.
                                        // Make sure to set your reference to null so you don't
                                        // show it a second time.
                                        Ascenciones.this.interstitialAd = null;
                                        Log.d("TAG", "The ad was dismissed.");
                                    }
                                    @Override
                                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                                        // Called when fullscreen content failed to show.
                                        // Make sure to set your reference to null so you don't
                                        // show it a second time.
                                        Ascenciones.this.interstitialAd = null;
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
        if (interstitialAd != null) {
            interstitialAd.show(this);
        } else {
            Toast.makeText(this, "¡No se ha podido cargar la publicidad!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(traerValorChk(this) == 1){
            salir.start();
        }
        Ascenciones.this.finish();
        showInterstitial();
    }
}