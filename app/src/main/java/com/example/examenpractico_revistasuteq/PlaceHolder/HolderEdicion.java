package com.example.examenpractico_revistasuteq.PlaceHolder;


import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.examenpractico_revistasuteq.R;
import com.mindorks.placeholderview.annotations.Animate;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.LongClick;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Position;
import com.mindorks.placeholderview.annotations.Recycle;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

@NonReusable
@Animate(Animate.CARD_TOP_IN_DESC)
@Layout(R.layout.itemedicion)
public class HolderEdicion {

    @View(R.id.txtName)
    TextView txtNombre;

    @View(R.id.txtSeccion)
    TextView txtSeccion;

    @Position
    int position;

    private Context context;

    private String nombre, seccion;

    public HolderEdicion(Context context, String nombre, String seccion) {
        this.context = context;
        this.nombre = nombre;
        this.seccion = seccion;
    }

    @Resolve
    public void onResolved() {
        txtNombre.setText(nombre);
        txtSeccion.setText(seccion);
    }

    @Recycle
    public void onRecycled(){

    }

    @Click(R.id.cardView)
    public void onImageViewClick(){


    }

    @LongClick(R.id.imgCover)
    public void onImageViewLongClick() {

    }

}
