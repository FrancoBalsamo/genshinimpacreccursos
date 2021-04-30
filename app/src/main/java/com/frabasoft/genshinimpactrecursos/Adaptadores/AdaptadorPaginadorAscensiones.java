package com.frabasoft.genshinimpactrecursos.Adaptadores;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.viewpager.widget.PagerAdapter;
import com.frabasoft.genshinimpactrecursos.R;
import com.frabasoft.genshinimpactrecursos.TouchImage.TouchImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;

public class AdaptadorPaginadorAscensiones extends PagerAdapter {
    Context mContext;
    LayoutInflater mLayoutInflater;
    private int[] mResourceIds;

    private DecimalFormat df;

    public AdaptadorPaginadorAscensiones(Context context, int[] resourceIds) {
        mContext = context;
        mResourceIds = resourceIds;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mResourceIds.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.paginador, container, false);

        df = new DecimalFormat("#.##");
        TouchImageView touchImageView = itemView.findViewById(R.id.ivViewPager);
        touchImageView.setImageResource(mResourceIds[position]);
        touchImageView.setOnLongClickListener(v -> {
            touchImageView.buildDrawingCache();
            Bitmap bmap = touchImageView.getDrawingCache();
            guardarImagenMedoto(bmap, "Ascencion-0" + position);
            return true;
        });
        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

    private void guardarImagenMedoto(Bitmap bitmap, String nombreImagen){
        File rutaBuilds = new File(Environment.getExternalStorageDirectory() + "/Genshin Impact Recursos/Genshin Impact Ascenciones/");
        if (!rutaBuilds.exists()) {
            File rutaBuildsCrear = new File(Environment.getExternalStorageDirectory() + "/Genshin Impact Recursos/Genshin Impact Ascenciones/");
            rutaBuildsCrear.mkdirs();
        }

        File archivo = new File(rutaBuilds, nombreImagen + ".jpg");
        if (archivo.exists()) {
            archivo.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(archivo);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            Toast.makeText(mContext, "¡Se ha guardado con éxito!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(mContext, "¡Ha ocurrido un error al intentar guardar la imagen!", Toast.LENGTH_SHORT).show();
        }
    }
}