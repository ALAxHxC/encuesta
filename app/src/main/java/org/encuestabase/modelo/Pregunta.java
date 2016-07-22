package org.encuestabase.modelo;

import android.view.View;

import java.util.List;

/**
 * Created by daniel on 21/07/2016.
 */
public class Pregunta {
    private String pregunta;
    private int npregunta;
    private List<String> opcciones;
    private int valor;
    private boolean respondio;
    private View vista;

    public Pregunta(String pregunta, int npregunta, List<String> opcciones) {
        this.pregunta = pregunta;
        this.npregunta = npregunta;
        this.opcciones = opcciones;
        this.valor = 0;
        respondio = false;
        vista = null;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public int getNpregunta() {
        return npregunta;
    }

    public void setNpregunta(int npregunta) {
        this.npregunta = npregunta;
    }

    public List<String> getOpcciones() {
        return opcciones;
    }

    public void setOpcciones(List<String> opcciones) {
        this.opcciones = opcciones;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public boolean isRespondio() {
        return respondio;
    }

    public void setRespondio(boolean respondio) {
        this.respondio = respondio;
    }

    public View getVista() {
        return vista;
    }

    public void setVista(View vista) {
        this.vista = vista;
    }
}
