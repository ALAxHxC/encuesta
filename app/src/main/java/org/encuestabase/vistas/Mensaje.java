package org.encuestabase.vistas;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by daniel on 21/07/2016.
 */
public class Mensaje {
    public Mensaje(Context context) {
        this.context = context;
    }

    private Context context;

    public void toastShow(String cuerpo) {
        Toast.makeText(context, cuerpo, Toast.LENGTH_SHORT).show();
    }
}
