package org.encuestabase.vistas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import org.encuestabase.R;
import org.encuestabase.modelo.Pregunta;

import java.util.List;

/**
 * Created by daniel on 21/07/2016.
 */
public class AdaptadorVista extends BaseExpandableListAdapter {
    private Context context;
    private List<Pregunta> listaPreguntas;

    public AdaptadorVista(Context context, List<Pregunta> listaPreguntas) {
        this.context = context;
        this.listaPreguntas = listaPreguntas;
    }

    @Override
    public int getGroupCount() {
        return listaPreguntas.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listaPreguntas.get(groupPosition).getOpcciones().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listaPreguntas.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listaPreguntas.get(groupPosition).getOpcciones().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.titulopregunta, null);

        Pregunta pregunta = listaPreguntas.get(groupPosition);
        TextView tituloPregunta = (TextView) convertView.findViewById(R.id.textViewTituloPregunta);
        tituloPregunta.setText(pregunta.getPregunta());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.pregunta, null);
        validarVista(convertView, listaPreguntas.get(groupPosition), childPosition);
        String opccion = (String) getChild(groupPosition, childPosition);
        TextView opccionText = (TextView) convertView.findViewById(R.id.textViewPregunta);
        opccionText.setText(opccion);
        controlarVista(listaPreguntas.get(groupPosition), convertView, childPosition);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private void controlarVista(final Pregunta pregunta, View view, final int item) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pregunta.setValor(valorRespuesta(item));
                pregunta.setRespondio(true);
                limpiarVista(pregunta, v);
            }
        });
    }

    private int valorRespuesta(int position) {
        switch (position) {
            case 0:
                return 5;
            case 1:
                return 3;
            case 2:
                return 1;
            default:
                return 0;
        }

    }

    private void limpiarVista(Pregunta pregunta, View vista) {
        if (pregunta.getVista() != null) {
            pregunta.getVista().setBackgroundResource(R.color.colorPrimary);
        }
        vista.setBackgroundResource(R.color.colorAccent);
        pregunta.setVista(vista);

    }

    private void validarVista(View vista, Pregunta pregunta, int position) {
        if (valorRespuesta(position) == pregunta.getValor()) {
            vista.setBackgroundResource(R.color.colorAccent);
            pregunta.setVista(vista);
        }
    }
}
