package org.encuestabase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import org.encuestabase.modelo.Pregunta;
import org.encuestabase.vistas.AdaptadorVista;
import org.encuestabase.vistas.Mensaje;
import org.encuestabase.vistas.Respuesta_Activity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button enviar;
    private ExpandableListView lista;
    private Mensaje mensaje;
    private List<Pregunta> listaPreguntas;
    String respuestas[];
    int respuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mensaje = new Mensaje(MainActivity.this);
        respuestas = MainActivity.this.getResources().getStringArray(R.array.preguntas_pre);
        cargarPreguntas();
        cargarVistas();

    }

    private void cargarVistas() {
        enviar = (Button) findViewById(R.id.buttonEnviar);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviar();
            }
        });
        lista = (ExpandableListView) findViewById(R.id.expandableListView);
        AdaptadorVista vista = new AdaptadorVista(MainActivity.this, listaPreguntas);
        lista.setAdapter(vista);
    }

    private void cargarPreguntas() {
        listaPreguntas = new ArrayList<>();
        String preguntas[] = MainActivity.this.getResources().getStringArray(R.array.preguntas);
        int pregunta = 0;
        respuesta = -1;
        for (String pre : preguntas) {
            listaPreguntas.add(new Pregunta(pre, pregunta++, opcciones()));
        }

    }

    private List<String> opcciones() {
        List<String> listarespuestas = new ArrayList<>();
        listarespuestas.add(respuestas[++respuesta]);
        listarespuestas.add(respuestas[++respuesta]);
        listarespuestas.add(respuestas[++respuesta]);
        return listarespuestas;
    }

    private boolean validarRespuestas() {
        for (Pregunta pregunta : listaPreguntas) {
            if (!pregunta.isRespondio()) {
                mensaje.toastShow(getString(R.string.debe_responder) + ":" + pregunta.getPregunta());
                return false;

            }
        }
        return true;
    }

    private void enviar() {

        if (validarRespuestas()) {
            Intent intent = new Intent(MainActivity.this, Respuesta_Activity.class);
            intent.putExtra(getString(R.string.respuesta), respuesta());
            startActivity(intent);
        }

    }

    private int respuesta() {
        int resp = 0;
        for (Pregunta pregunta : listaPreguntas) {
            resp += pregunta.getValor();
        }
        return resp;
    }


}
