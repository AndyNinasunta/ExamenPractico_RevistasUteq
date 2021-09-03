package com.example.examenpractico_revistasuteq.PlaceHolder;


import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.examenpractico_revistasuteq.MainActivity;
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
@Layout(R.layout.item)
public class HolderRevista {


    @View(R.id.imgCover)
    ImageView imgRevista;

    @View(R.id.txtName)
    TextView txtNombre;

    @View(R.id.txtDescr)
    TextView txtDescripcion;

    @Position
    int position;

    private Context context;
    private String nombre,descripcion,urlImg,idRevista;
    private Class clase;

    public HolderRevista(Context context,String nombre,
                         String descripcion,String urlImg,String idRevista,Class clase) {
        this.context=context;
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.urlImg=urlImg;
        this.idRevista=idRevista;
        this.clase=clase;
    }

    @Resolve
    public void onResolved() {

        txtNombre.setText(nombre);
        txtDescripcion.setText(descripcion);
        Glide.with(context).load(urlImg).into(imgRevista);
    }

    @Recycle
    public void onRecycled(){

    }

    @Click(R.id.cardView)
    public void onImageViewClick(){
        Intent intent=new Intent(this.context,clase);
        intent.putExtra("idRevista",idRevista);
        startActivity(context,intent,null   );

    }

    @LongClick(R.id.imgCover)
    public void onImageViewLongClick() {

    }

}
