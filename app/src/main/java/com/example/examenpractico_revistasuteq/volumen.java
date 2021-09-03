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
import com.example.examenpractico_revistasuteq.PlaceHolder.HolderVolumenes;
import com.mindorks.placeholderview.PlaceHolderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class volumen extends AppCompatActivity {

    private RequestQueue requestQue;
    private String idRevistas= "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volumen);

        Bundle extras=getIntent().getExtras();
        idRevistas= extras.getString("idRevista");
        obtenerVolumenes();
    }

    public void regresar(View view){
        finish();
    }


    private void obtenerVolumenes(){
        String url="https://revistas.uteq.edu.ec/ws/issues.php?j_id="+idRevistas;
        JsonArrayRequest requestJson=new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        PlaceHolderView phvVolumenes = (PlaceHolderView) findViewById(R.id.phv_ediciones);

                        phvVolumenes.getBuilder()
                                .setHasFixedSize(false)
                                .setItemViewCacheSize(10)
                                .setLayoutManager(new LinearLayoutManager(
                                        getApplicationContext(),
                                        LinearLayoutManager.VERTICAL,
                                        false));


                        Volumenes volumenes;
                        for(int i=0; i<response.length();i++){
                            try {

                                JSONObject objectJson = new JSONObject(response.getJSONObject(i).toString());

                                volumenes=new Volumenes(objectJson.getString("issue_id"),objectJson.getString("title"),
                                        objectJson.getString("cover"),objectJson.getString("volume"),
                                        objectJson.getString("year"),objectJson.getString("number"));


                                phvVolumenes.addView(new HolderVolumenes(volumen.this,volumenes,ediciones.class));

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