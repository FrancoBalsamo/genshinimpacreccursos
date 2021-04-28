package com.frabasoft.genshinimpactrecursos.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.frabasoft.genshinimpactrecursos.Clases.ArtefactosAlert.PlumaArtefacto;
import com.frabasoft.genshinimpactrecursos.Clases.ArtefactosAlert.RelojArtefacto;
import com.frabasoft.genshinimpactrecursos.R;

import java.util.ArrayList;

public class AdaptadorListViewReloj extends BaseAdapter {
    private Context context;//el contexto para la clase
    ArrayList<RelojArtefacto> relojArtefactoArrayList = new ArrayList<>();//el array de datos

    //clase con parámetros
    public AdaptadorListViewReloj(Context context, ArrayList<RelojArtefacto> relojArtefactoArrayList){
        this.context = context;
        this.relojArtefactoArrayList.addAll(relojArtefactoArrayList); // Crea una copia de los contactos
    }
    @Override
    public int getCount() {
        return this.relojArtefactoArrayList.size();
    }
    @Override
    public Object getItem(int position) {
        return this.relojArtefactoArrayList.get(position);
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

        RelojArtefacto relojArtefacto = this.relojArtefactoArrayList.get(position);
        if (relojArtefacto.getSeleccionDatoSpiner() == 0){
            ivArtefactosAlert.setImageResource(R.drawable.reloj_afortunado);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella);
        }else if (relojArtefacto.getSeleccionDatoSpiner() == 1){
            ivArtefactosAlert.setImageResource(R.drawable.reloj_aventurero);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella);
        }else if (relojArtefacto.getSeleccionDatoSpiner() == 2){
            ivArtefactosAlert.setImageResource(R.drawable.reloj_curativa);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella);
        }else if (relojArtefacto.getSeleccionDatoSpiner() == 3){
            ivArtefactosAlert.setImageResource(R.drawable.reloj_instructor);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella);
        }else if (relojArtefacto.getSeleccionDatoSpiner() == 4){
            ivArtefactosAlert.setImageResource(R.drawable.reloj_berseker);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella);
        }else if (relojArtefacto.getSeleccionDatoSpiner() == 5){
            ivArtefactosAlert.setImageResource(R.drawable.reloj_exiliado);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella);
        }else if (relojArtefacto.getSeleccionDatoSpiner() == 6){
            ivArtefactosAlert.setImageResource(R.drawable.reloj_viajero);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella);
        }else if (relojArtefacto.getSeleccionDatoSpiner() == 7){
            ivArtefactosAlert.setImageResource(R.drawable.reloj_marcial);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella);
        }else if (relojArtefacto.getSeleccionDatoSpiner() == 8){
            ivArtefactosAlert.setImageResource(R.drawable.reloj_guardian);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella);
        }else if (relojArtefacto.getSeleccionDatoSpiner() == 9){
            ivArtefactosAlert.setImageResource(R.drawable.reloj_milagro);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella);
        }else if (relojArtefacto.getSeleccionDatoSpiner() == 10){
            ivArtefactosAlert.setImageResource(R.drawable.reloj_guerrero);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella);
        }else if (relojArtefacto.getSeleccionDatoSpiner() == 11){
            ivArtefactosAlert.setImageResource(R.drawable.reloja_jugadora);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella);
        }else if (relojArtefacto.getSeleccionDatoSpiner() == 12){
            ivArtefactosAlert.setImageResource(R.drawable.reloj_erudita);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella);
        }else if (relojArtefacto.getSeleccionDatoSpiner() == 13){
            ivArtefactosAlert.setImageResource(R.drawable.reloj_gladiador);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (relojArtefacto.getSeleccionDatoSpiner() == 14){
            ivArtefactosAlert.setImageResource(R.drawable.reloj_doncella);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (relojArtefacto.getSeleccionDatoSpiner() == 15){
            ivArtefactosAlert.setImageResource(R.drawable.reloj_nobleza);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (relojArtefacto.getSeleccionDatoSpiner() == 16){
            ivArtefactosAlert.setImageResource(R.drawable.reloj_sangui);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (relojArtefacto.getSeleccionDatoSpiner() == 17){
            ivArtefactosAlert.setImageResource(R.drawable.reloj_errante);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (relojArtefacto.getSeleccionDatoSpiner() == 18){
            ivArtefactosAlert.setImageResource(R.drawable.reloj_esmeralda);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (relojArtefacto.getSeleccionDatoSpiner() == 19){
            ivArtefactosAlert.setImageResource(R.drawable.reloj_furiatrueno);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (relojArtefacto.getSeleccionDatoSpiner() == 20){
            ivArtefactosAlert.setImageResource(R.drawable.reloj_domtrueno);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (relojArtefacto.getSeleccionDatoSpiner() == 21){
            ivArtefactosAlert.setImageResource(R.drawable.reloj_bruja);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (relojArtefacto.getSeleccionDatoSpiner() == 22){
            ivArtefactosAlert.setImageResource(R.drawable.reloj_corredor);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (relojArtefacto.getSeleccionDatoSpiner() == 23){
            ivArtefactosAlert.setImageResource(R.drawable.reloj_petra);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (relojArtefacto.getSeleccionDatoSpiner() == 24){
            ivArtefactosAlert.setImageResource(R.drawable.reloj_meteoro);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (relojArtefacto.getSeleccionDatoSpiner() == 25){
            ivArtefactosAlert.setImageResource(R.drawable.reloj_nomada);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }else if (relojArtefacto.getSeleccionDatoSpiner() == 26){
            ivArtefactosAlert.setImageResource(R.drawable.reloj_profundidades);
            tvArtefactosAlert.setText(" " + estrella + estrella + estrella + estrella + estrella);
        }
        return rowView; //retornamos los datos
    }
}
