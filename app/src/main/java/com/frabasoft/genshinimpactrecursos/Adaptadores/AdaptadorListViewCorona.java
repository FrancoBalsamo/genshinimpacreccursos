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
        }
        return rowView; //retornamos los datos
    }
}