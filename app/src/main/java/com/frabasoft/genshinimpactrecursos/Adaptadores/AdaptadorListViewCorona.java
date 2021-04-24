package com.frabasoft.genshinimpactrecursos.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.frabasoft.genshinimpactrecursos.Clases.ArtefactosAlert.CoronaArtefacto;
import com.frabasoft.genshinimpactrecursos.Clases.ArtefactosAlert.PlumaArtefacto;
import com.frabasoft.genshinimpactrecursos.R;

import java.util.ArrayList;

public class AdaptadorListViewCorona extends BaseAdapter {
    private Context context;//el contexto para la clase
    ArrayList<CoronaArtefacto> coronaArtefactoArrayList = new ArrayList<>();//el array de datos

    //clase con parámetros
    public AdaptadorListViewCorona(Context context, ArrayList<CoronaArtefacto> coronaArtefactoArrayList){
        this.context = context;
        this.coronaArtefactoArrayList.addAll(coronaArtefactoArrayList); // Crea una copia de los contactos
    }
    @Override
    public int getCount() {
        return this.coronaArtefactoArrayList.size();
    }
    @Override
    public Object getItem(int position) {
        return this.coronaArtefactoArrayList.get(position);
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

        CoronaArtefacto coronaArtefacto = this.coronaArtefactoArrayList.get(position);
        if (coronaArtefacto.getSeleccionDatoSpiner() == 0){
            ivArtefactosAlert.setImageResource(R.drawable.corona_afortunado);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella);
        }else if (coronaArtefacto.getSeleccionDatoSpiner() == 1){
            ivArtefactosAlert.setImageResource(R.drawable.corona_aventurero);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella);
        }else if (coronaArtefacto.getSeleccionDatoSpiner() == 2){
            ivArtefactosAlert.setImageResource(R.drawable.corona_curativa);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella);
        }else if (coronaArtefacto.getSeleccionDatoSpiner() == 3){
            ivArtefactosAlert.setImageResource(R.drawable.corona_instructor);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella);
        }else if (coronaArtefacto.getSeleccionDatoSpiner() ==4){
            ivArtefactosAlert.setImageResource(R.drawable.corona_berseker);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella);
        }else if (coronaArtefacto.getSeleccionDatoSpiner() == 5){
            ivArtefactosAlert.setImageResource(R.drawable.corona_exiliado);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella);
        }else if (coronaArtefacto.getSeleccionDatoSpiner() == 6){
            ivArtefactosAlert.setImageResource(R.drawable.corona_viajero);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella);
        }else if (coronaArtefacto.getSeleccionDatoSpiner() == 7){
            ivArtefactosAlert.setImageResource(R.drawable.corona_marcial);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella);
        }else if (coronaArtefacto.getSeleccionDatoSpiner() == 8){
            ivArtefactosAlert.setImageResource(R.drawable.corona_guardian);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella);
        }else if (coronaArtefacto.getSeleccionDatoSpiner() == 9){
            ivArtefactosAlert.setImageResource(R.drawable.corona_milagro);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella);
        }else if (coronaArtefacto.getSeleccionDatoSpiner() == 10){
            ivArtefactosAlert.setImageResource(R.drawable.corona_guerrero);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella);
        }else if (coronaArtefacto.getSeleccionDatoSpiner() == 11){
            ivArtefactosAlert.setImageResource(R.drawable.corona_jugadora);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella);
        }else if (coronaArtefacto.getSeleccionDatoSpiner() == 12){
            ivArtefactosAlert.setImageResource(R.drawable.corona_erudita);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella);
        }else if (coronaArtefacto.getSeleccionDatoSpiner() == 13){
            ivArtefactosAlert.setImageResource(R.drawable.corona_gladiador);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (coronaArtefacto.getSeleccionDatoSpiner() == 14){
            ivArtefactosAlert.setImageResource(R.drawable.corona_doncella);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (coronaArtefacto.getSeleccionDatoSpiner() == 15){
            ivArtefactosAlert.setImageResource(R.drawable.corona_nobleza);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (coronaArtefacto.getSeleccionDatoSpiner() == 16){
            ivArtefactosAlert.setImageResource(R.drawable.corona_sangui);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (coronaArtefacto.getSeleccionDatoSpiner() == 17){
            ivArtefactosAlert.setImageResource(R.drawable.corona_errante);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (coronaArtefacto.getSeleccionDatoSpiner() == 18){
            ivArtefactosAlert.setImageResource(R.drawable.corona_esmeralda);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (coronaArtefacto.getSeleccionDatoSpiner() == 19){
            ivArtefactosAlert.setImageResource(R.drawable.corona_furiatrueno);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (coronaArtefacto.getSeleccionDatoSpiner() == 20){
            ivArtefactosAlert.setImageResource(R.drawable.corona_domtrueno);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (coronaArtefacto.getSeleccionDatoSpiner() == 21){
            ivArtefactosAlert.setImageResource(R.drawable.corona_bruja);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (coronaArtefacto.getSeleccionDatoSpiner() == 22){
            ivArtefactosAlert.setImageResource(R.drawable.corona_corredor);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (coronaArtefacto.getSeleccionDatoSpiner() == 23){
            ivArtefactosAlert.setImageResource(R.drawable.corona_petra);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (coronaArtefacto.getSeleccionDatoSpiner() == 24){
            ivArtefactosAlert.setImageResource(R.drawable.corona_meteoro);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (coronaArtefacto.getSeleccionDatoSpiner() == 25){
            ivArtefactosAlert.setImageResource(R.drawable.corona_nomada);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (coronaArtefacto.getSeleccionDatoSpiner() == 26){
            ivArtefactosAlert.setImageResource(R.drawable.corona_profundidades);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }
        return rowView; //retornamos los datos
    }
}