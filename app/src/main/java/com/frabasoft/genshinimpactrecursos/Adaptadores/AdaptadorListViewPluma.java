package com.frabasoft.genshinimpactrecursos.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.frabasoft.genshinimpactrecursos.Clases.ArtefactosAlert.PlumaArtefacto;
import com.frabasoft.genshinimpactrecursos.R;

import java.util.ArrayList;

public class AdaptadorListViewPluma extends BaseAdapter {
    private Context context;//el contexto para la clase
    ArrayList<PlumaArtefacto> plumaArtefactoArrayList = new ArrayList<>();//el array de datos

    //clase con parámetros
    public AdaptadorListViewPluma(Context context, ArrayList<PlumaArtefacto> plumaArtefactoArrayList){
        this.context = context;
        this.plumaArtefactoArrayList.addAll(plumaArtefactoArrayList); // Crea una copia de los contactos
    }
    @Override
    public int getCount() {
        return this.plumaArtefactoArrayList.size();
    }
    @Override
    public Object getItem(int position) {
        return this.plumaArtefactoArrayList.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    //desde acá aplicamos como queremos que se vea nuestra lista
    //en nuestro main activity
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //creamos la vista
        View rowView = convertView;
        if (convertView == null) {
            // Nueva vista en la lista
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.alert_artefactos_imageview, parent, false);//rellenamos el layout
        }

        String estrella = rowView.getResources().getString(R.string.estrella);
        TextView tvArtefactosAlert = rowView.findViewById(R.id.tvArtefactosAlert); //creamos el textview
        ImageView ivArtefactosAlert = rowView.findViewById(R.id.ivArtefactosAlert);

        PlumaArtefacto plumaArtefacto = this.plumaArtefactoArrayList.get(position);
        if (plumaArtefacto.getSeleccionDatoSpiner() == 0){
            ivArtefactosAlert.setImageResource(R.drawable.pluma_afortunado);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella);
        }else if (plumaArtefacto.getSeleccionDatoSpiner() == 1){
            ivArtefactosAlert.setImageResource(R.drawable.pluma_aventurero);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella);
        }else if (plumaArtefacto.getSeleccionDatoSpiner() == 2){
            ivArtefactosAlert.setImageResource(R.drawable.pluma_curativa);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella);
        }else if (plumaArtefacto.getSeleccionDatoSpiner() == 3){
            ivArtefactosAlert.setImageResource(R.drawable.pluma_instructor);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella);
        }else if (plumaArtefacto.getSeleccionDatoSpiner() == 4){
            ivArtefactosAlert.setImageResource(R.drawable.pluma_berseker);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella);
        }else if (plumaArtefacto.getSeleccionDatoSpiner() == 5){
            ivArtefactosAlert.setImageResource(R.drawable.pluma_exiliado);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella);
        }else if (plumaArtefacto.getSeleccionDatoSpiner() == 6){
            ivArtefactosAlert.setImageResource(R.drawable.pluma_viajero);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella);
        }else if (plumaArtefacto.getSeleccionDatoSpiner() == 7){
            ivArtefactosAlert.setImageResource(R.drawable.pluma_marcial);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella);
        }else if (plumaArtefacto.getSeleccionDatoSpiner() == 8){
            ivArtefactosAlert.setImageResource(R.drawable.pluma_guardian);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella);
        }else if (plumaArtefacto.getSeleccionDatoSpiner() == 9){
            ivArtefactosAlert.setImageResource(R.drawable.pluma_milagro);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella);
        }else if (plumaArtefacto.getSeleccionDatoSpiner() == 10){
            ivArtefactosAlert.setImageResource(R.drawable.pluma_guerrero);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella);
        }else if (plumaArtefacto.getSeleccionDatoSpiner() == 1){
            ivArtefactosAlert.setImageResource(R.drawable.pluma_jugadora);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella);
        }else if (plumaArtefacto.getSeleccionDatoSpiner() == 12){
            ivArtefactosAlert.setImageResource(R.drawable.pluma_erudita);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella);
        }else if (plumaArtefacto.getSeleccionDatoSpiner() == 13){
            ivArtefactosAlert.setImageResource(R.drawable.pluma_gladiador);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (plumaArtefacto.getSeleccionDatoSpiner() == 14){
            ivArtefactosAlert.setImageResource(R.drawable.pluma_doncella);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (plumaArtefacto.getSeleccionDatoSpiner() == 15){
            ivArtefactosAlert.setImageResource(R.drawable.pluma_nobleza);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (plumaArtefacto.getSeleccionDatoSpiner() == 16){
            ivArtefactosAlert.setImageResource(R.drawable.pluma_sangui);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (plumaArtefacto.getSeleccionDatoSpiner() == 17){
            ivArtefactosAlert.setImageResource(R.drawable.pluma_errante);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (plumaArtefacto.getSeleccionDatoSpiner() == 18){
            ivArtefactosAlert.setImageResource(R.drawable.pluma_esmeralda);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (plumaArtefacto.getSeleccionDatoSpiner() == 19){
            ivArtefactosAlert.setImageResource(R.drawable.pluma_furiatrueno);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (plumaArtefacto.getSeleccionDatoSpiner() == 20){
            ivArtefactosAlert.setImageResource(R.drawable.pluma_domtrueno);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (plumaArtefacto.getSeleccionDatoSpiner() == 21){
            ivArtefactosAlert.setImageResource(R.drawable.pluma_bruja);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (plumaArtefacto.getSeleccionDatoSpiner() == 22){
            ivArtefactosAlert.setImageResource(R.drawable.pluma_corredor);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (plumaArtefacto.getSeleccionDatoSpiner() == 23){
            ivArtefactosAlert.setImageResource(R.drawable.pluma_petra);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (plumaArtefacto.getSeleccionDatoSpiner() == 24){
            ivArtefactosAlert.setImageResource(R.drawable.pluma_meteoro);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (plumaArtefacto.getSeleccionDatoSpiner() == 25){
            ivArtefactosAlert.setImageResource(R.drawable.pluma_nomada);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (plumaArtefacto.getSeleccionDatoSpiner() == 26){
            ivArtefactosAlert.setImageResource(R.drawable.pluma_profundidades);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }
        return rowView; //retornamos los datos
    }
}