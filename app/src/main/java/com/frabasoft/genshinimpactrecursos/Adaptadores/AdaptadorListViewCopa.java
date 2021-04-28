package com.frabasoft.genshinimpactrecursos.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.frabasoft.genshinimpactrecursos.Clases.ArtefactosAlert.CopaArtefacto;
import com.frabasoft.genshinimpactrecursos.R;

import java.util.ArrayList;

public class AdaptadorListViewCopa extends BaseAdapter {
    private Context context;//el contexto para la clase
    ArrayList<CopaArtefacto> copaArtefactoArrayList = new ArrayList<>();//el array de datos

    //clase con parámetros
    public AdaptadorListViewCopa(Context context, ArrayList<CopaArtefacto> copaArtefactoArrayList){
        this.context = context;
        this.copaArtefactoArrayList.addAll(copaArtefactoArrayList); // Crea una copia de los contactos
    }
    @Override
    public int getCount() {
        return this.copaArtefactoArrayList.size();
    }
    @Override
    public Object getItem(int position) {
        return this.copaArtefactoArrayList.get(position);
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

        CopaArtefacto CopaArtefacto = this.copaArtefactoArrayList.get(position);
        if (CopaArtefacto.getSeleccionDatoSpiner() == 0){
            ivArtefactosAlert.setImageResource(R.drawable.copa_afortunado);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella);
        }else if (CopaArtefacto.getSeleccionDatoSpiner() == 1){
            ivArtefactosAlert.setImageResource(R.drawable.copa_aventurero);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella);
        }else if (CopaArtefacto.getSeleccionDatoSpiner() == 2){
            ivArtefactosAlert.setImageResource(R.drawable.copa_curativa);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella);
        }else if (CopaArtefacto.getSeleccionDatoSpiner() == 3){
            ivArtefactosAlert.setImageResource(R.drawable.copa_instructor);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella);
        }else if (CopaArtefacto.getSeleccionDatoSpiner() == 4){
            ivArtefactosAlert.setImageResource(R.drawable.copa_berseker);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella);
        }else if (CopaArtefacto.getSeleccionDatoSpiner() == 5){
            ivArtefactosAlert.setImageResource(R.drawable.copa_exiliado);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella);
        }else if (CopaArtefacto.getSeleccionDatoSpiner() == 6){
            ivArtefactosAlert.setImageResource(R.drawable.copa_viajero);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella);
        }else if (CopaArtefacto.getSeleccionDatoSpiner() == 7){
            ivArtefactosAlert.setImageResource(R.drawable.copa_marcial);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella);
        }else if (CopaArtefacto.getSeleccionDatoSpiner() == 8){
            ivArtefactosAlert.setImageResource(R.drawable.copa_guardian);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella);
        }else if (CopaArtefacto.getSeleccionDatoSpiner() == 9){
            ivArtefactosAlert.setImageResource(R.drawable.copa_milagro);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella);
        }else if (CopaArtefacto.getSeleccionDatoSpiner() == 10){
            ivArtefactosAlert.setImageResource(R.drawable.copa_guerrero);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella);
        }else if (CopaArtefacto.getSeleccionDatoSpiner() == 11){
            ivArtefactosAlert.setImageResource(R.drawable.copa_jugadora);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella);
        }else if (CopaArtefacto.getSeleccionDatoSpiner() == 12){
            ivArtefactosAlert.setImageResource(R.drawable.copa_erudita);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella);
        }else if (CopaArtefacto.getSeleccionDatoSpiner() == 13){
            ivArtefactosAlert.setImageResource(R.drawable.copa_gladiador);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (CopaArtefacto.getSeleccionDatoSpiner() == 14){
            ivArtefactosAlert.setImageResource(R.drawable.copa_doncella);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (CopaArtefacto.getSeleccionDatoSpiner() == 15){
            ivArtefactosAlert.setImageResource(R.drawable.copa_nobleza);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (CopaArtefacto.getSeleccionDatoSpiner() == 16){
            ivArtefactosAlert.setImageResource(R.drawable.copa_sangui);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (CopaArtefacto.getSeleccionDatoSpiner() == 17){
            ivArtefactosAlert.setImageResource(R.drawable.copa_errante);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (CopaArtefacto.getSeleccionDatoSpiner() == 18){
            ivArtefactosAlert.setImageResource(R.drawable.copa_esmeralda);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (CopaArtefacto.getSeleccionDatoSpiner() == 19){
            ivArtefactosAlert.setImageResource(R.drawable.copa_furiatrueno);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (CopaArtefacto.getSeleccionDatoSpiner() == 20){
            ivArtefactosAlert.setImageResource(R.drawable.copa_domtrueno);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (CopaArtefacto.getSeleccionDatoSpiner() == 21){
            ivArtefactosAlert.setImageResource(R.drawable.copa_bruja);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (CopaArtefacto.getSeleccionDatoSpiner() == 22){
            ivArtefactosAlert.setImageResource(R.drawable.copa_corredor);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (CopaArtefacto.getSeleccionDatoSpiner() == 23){
            ivArtefactosAlert.setImageResource(R.drawable.copa_petra);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (CopaArtefacto.getSeleccionDatoSpiner() == 24){
            ivArtefactosAlert.setImageResource(R.drawable.copa_meteoro);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (CopaArtefacto.getSeleccionDatoSpiner() == 25){
            ivArtefactosAlert.setImageResource(R.drawable.copa_nomada);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (CopaArtefacto.getSeleccionDatoSpiner() == 26){
            ivArtefactosAlert.setImageResource(R.drawable.copa_profundidades);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }
        return rowView; //retornamos los datos
    }
}