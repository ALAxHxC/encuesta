package org.encuestabase.vistas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.encuestabase.R;

public class Respuesta_Activity extends AppCompatActivity {
    private int valor;
    private TextView respuesta;
    private ImageView imageViewRespuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respuesta_);
        valor = getIntent().getIntExtra(getString(R.string.respuesta), 0);
        respuesta = (TextView) findViewById(R.id.textViewRespuesta);
        imageViewRespuesta = (ImageView) findViewById(R.id.imageViewRespuesta);
        validadRespuesta();
        Log.println(Log.ASSERT, "LOG", valor + "");
    }

    private void validadRespuesta() {
        if (valor <= 9) {
            respuesta.setText(getString(R.string.solo_te_gusta));
            imageViewRespuesta.setImageResource(R.drawable.like);
            return;
        }

        if (valor <= 19) {
            respuesta.setText(getString(R.string.le_tienes_aprecio));
            imageViewRespuesta.setImageResource(R.drawable.apreciar);
            return;
        }
        respuesta.setText(getString(R.string.estas_enamorado));
        imageViewRespuesta.setImageResource(R.drawable.love);
    }
}
