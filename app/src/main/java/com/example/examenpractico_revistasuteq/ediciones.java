package com.example.examenpractico_revistasuteq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.examenpractico_revistasuteq.Modelos.Volumenes;
import com.example.examenpractico_revistasuteq.PlaceHolder.HolderEdicion;
import com.example.examenpractico_revistasuteq.PlaceHolder.HolderRevista;
import com.example.examenpractico_revistasuteq.PlaceHolder.HolderVolumenes;
import com.mindorks.placeholderview.PlaceHolderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ediciones extends AppCompatActivity {

    private RequestQueue requestQue;
    private String idVolumen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ediciones);

        Bundle extras=getIntent().getExtras();
        idVolumen= extras.getString("idVolumen");

        obtenerEdiciones();

    }
    public void regresar(View view){
        finish();
    }

    private void obtenerEdiciones(){
        String url="https://revistas.uteq.edu.ec/ws/pubs.php?i_id="+idVolumen;
        JsonArrayRequest requestJson=new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        PlaceHolderView phvEdiciones = (PlaceHolderView) findViewById(R.id.phv_ediciones);

                        phvEdiciones.getBuilder()
                                .setHasFixedSize(false)
                                .setItemViewCacheSize(1)
                                .setLayoutManager(new LinearLayoutManager(
                                        getApplicationContext(),
                                        LinearLayoutManager.VERTICAL,
                                        false));

                        String nombre,seccion;

                        for(int i=0; i<response.length();i++){
                            try {

                                JSONObject objectJson = new JSONObject(response.getJSONObject(i).toString());

                                seccion=objectJson.get("section").toString();
                                nombre=objectJson.get("title").toString();

                                phvEdiciones.addView(new HolderEdicion(ediciones.this,nombre,seccion));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"Error al conectarse:"+error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }

        );
        requestQue = Volley.newRequestQueue(this);
        requestQue.add(requestJson);
    };

}