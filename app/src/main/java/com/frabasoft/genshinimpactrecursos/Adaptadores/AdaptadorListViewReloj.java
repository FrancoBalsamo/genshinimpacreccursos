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
        }
        return rowView; //retornamos los datos
    }
}
