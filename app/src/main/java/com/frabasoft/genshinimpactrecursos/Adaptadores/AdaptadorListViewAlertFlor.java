package com.frabasoft.genshinimpactrecursos.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.frabasoft.genshinimpactrecursos.Clases.ArtefactosAlert.FlorArtefacto;
import com.frabasoft.genshinimpactrecursos.R;

import java.util.ArrayList;

public class AdaptadorListViewAlertFlor extends BaseAdapter {
    private Context context;//el contexto para la clase
    ArrayList<FlorArtefacto> florArtefactoArrayList = new ArrayList<>();//el array de datos

    //clase con parámetros
    public AdaptadorListViewAlertFlor(Context context, ArrayList<FlorArtefacto> clientes){
        this.context = context;
        this.florArtefactoArrayList.addAll(clientes); // Crea una copia de los contactos
    }
    @Override
    public int getCount() {
        return this.florArtefactoArrayList.size();
    }
    @Override
    public Object getItem(int position) {
        return this.florArtefactoArrayList.get(position);
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

        FlorArtefacto florArtefacto = this.florArtefactoArrayList.get(position);
        if (florArtefacto.getSeleccionDatoSpiner() == 0){
            ivArtefactosAlert.setImageResource(R.drawable.flor_afortunado);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella);
        }else if (florArtefacto.getSeleccionDatoSpiner() == 1){
            ivArtefactosAlert.setImageResource(R.drawable.flor_aventurero);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella);
        }else if (florArtefacto.getSeleccionDatoSpiner() == 2){
            ivArtefactosAlert.setImageResource(R.drawable.flor_curativa);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella);
        }else if (florArtefacto.getSeleccionDatoSpiner() == 3){
            ivArtefactosAlert.setImageResource(R.drawable.flor_instructor);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella);
        }else if (florArtefacto.getSeleccionDatoSpiner() == 4){
            ivArtefactosAlert.setImageResource(R.drawable.flor_berseker);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella);
        }else if (florArtefacto.getSeleccionDatoSpiner() == 5){
            ivArtefactosAlert.setImageResource(R.drawable.flor_exiliado);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella);
        }else if (florArtefacto.getSeleccionDatoSpiner() == 6){
            ivArtefactosAlert.setImageResource(R.drawable.flor_viajero);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella);
        }else if (florArtefacto.getSeleccionDatoSpiner() == 7){
            ivArtefactosAlert.setImageResource(R.drawable.flor_marcial);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella);
        }else if (florArtefacto.getSeleccionDatoSpiner() == 8){
            ivArtefactosAlert.setImageResource(R.drawable.flor_guardian);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella);
        }else if (florArtefacto.getSeleccionDatoSpiner() == 9){
            ivArtefactosAlert.setImageResource(R.drawable.flor_milagro);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella);
        }else if (florArtefacto.getSeleccionDatoSpiner() == 10){
            ivArtefactosAlert.setImageResource(R.drawable.flor_guerrero);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella);
        }else if (florArtefacto.getSeleccionDatoSpiner() == 11){
            ivArtefactosAlert.setImageResource(R.drawable.flor_jugadora);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella);
        }else if (florArtefacto.getSeleccionDatoSpiner() == 12){
            ivArtefactosAlert.setImageResource(R.drawable.flor_erudita);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella);
        }else if (florArtefacto.getSeleccionDatoSpiner() == 13){
            ivArtefactosAlert.setImageResource(R.drawable.flor_gladiador);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (florArtefacto.getSeleccionDatoSpiner() == 14){
            ivArtefactosAlert.setImageResource(R.drawable.flor_doncella);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (florArtefacto.getSeleccionDatoSpiner() == 15){
            ivArtefactosAlert.setImageResource(R.drawable.flor_nobleza);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (florArtefacto.getSeleccionDatoSpiner() == 16){
            ivArtefactosAlert.setImageResource(R.drawable.flor_sangui);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (florArtefacto.getSeleccionDatoSpiner() == 17){
            ivArtefactosAlert.setImageResource(R.drawable.flor_errante);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (florArtefacto.getSeleccionDatoSpiner() == 18){
            ivArtefactosAlert.setImageResource(R.drawable.flor_esmeralda);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (florArtefacto.getSeleccionDatoSpiner() == 19){
            ivArtefactosAlert.setImageResource(R.drawable.flor_furiatrueno);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (florArtefacto.getSeleccionDatoSpiner() == 20){
            ivArtefactosAlert.setImageResource(R.drawable.flor_domtrueno);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (florArtefacto.getSeleccionDatoSpiner() == 21){
            ivArtefactosAlert.setImageResource(R.drawable.flor_bruja);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (florArtefacto.getSeleccionDatoSpiner() == 22){
            ivArtefactosAlert.setImageResource(R.drawable.flor_corredor);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (florArtefacto.getSeleccionDatoSpiner() == 23){
            ivArtefactosAlert.setImageResource(R.drawable.flor_petra);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (florArtefacto.getSeleccionDatoSpiner() == 24){
            ivArtefactosAlert.setImageResource(R.drawable.flor_meteoro);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (florArtefacto.getSeleccionDatoSpiner() == 25){
            ivArtefactosAlert.setImageResource(R.drawable.flor_nomada);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (florArtefacto.getSeleccionDatoSpiner() == 26){
            ivArtefactosAlert.setImageResource(R.drawable.flor_profundidades);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }
        return rowView; //retornamos los datos
    }
}