package com.example.examenpractico_revistasuteq.PlaceHolder;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.examenpractico_revistasuteq.Modelos.Volumenes;
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

import java.util.List;

@NonReusable
@Animate(Animate.CARD_TOP_IN_DESC)
@Layout(R.layout.item)
public class HolderVolumenes {

    @View(R.id.imgCover)
    ImageView imgRevista;

    @View(R.id.txtName)
    TextView txtNombre;

    @View(R.id.txtDescr)
    TextView txtDescripcion;

    @Position
    int position;

    private Context context;
    private Volumenes volumenes;
    private Class clase;

    public HolderVolumenes(Context context,Volumenes volumenes,Class clase) {
        this.context = context;
        this.volumenes=volumenes;
        this.clase=clase;
    }

    @Resolve
    public void onResolved() {

        //holder.txtTitle.setText(revistaLista.get(position).getTitle());
        txtNombre.setText(volumenes.getTitle());
        txtDescripcion.setText("Vol. "+volumenes.getVolume()+" Núm."+volumenes.getNumber()+" ("+volumenes.getYear()+")");
        Glide.with(context).load(volumenes.getUrlImgCover()).into(imgRevista);

    }

    @Recycle
    public void onRecycled(){

    }

    @Click(R.id.cardView)
    public void onImageViewClick(){

        Intent intent=new Intent(this.context,clase);
        intent.putExtra("idVolumen",volumenes.getId());
        startActivity(context,intent,null   );

    }

    @LongClick(R.id.imgCover)
    public void onImageViewLongClick() {

    }
}
