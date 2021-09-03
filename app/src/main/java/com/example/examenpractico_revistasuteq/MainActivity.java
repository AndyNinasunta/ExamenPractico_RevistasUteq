package com.example.examenpractico_revistasuteq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.examenpractico_revistasuteq.PlaceHolder.HolderRevista;
import com.mindorks.placeholderview.PlaceHolderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {


    private RequestQueue  requestQue;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        obtenerRevistas();

    }

    private void obtenerRevistas(){
        url="https://revistas.uteq.edu.ec/ws/journals.php";
        JsonArrayRequest requestJson=new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        PlaceHolderView phvRevista = (PlaceHolderView) findViewById(R.id.phv_revistas);

                        phvRevista.getBuilder()
                                .setHasFixedSize(false)
                                .setItemViewCacheSize(10)
                                .setLayoutManager(new LinearLayoutManager(
                                        getApplicationContext(),
                                        LinearLayoutManager.VERTICAL,
                                        false));

                        String nombre="",descripcion="",urlImg="",idRevista="";


                        for (int i = 0; i < response.length(); i++) {

                            try {

                                JSONObject objectJson = new JSONObject(response.getJSONObject(i).toString());

                                nombre=objectJson.get("name").toString();
                                descripcion=objectJson.get("description").toString();
                                urlImg=objectJson.get("portada").toString();
                                idRevista= objectJson.get("journal_id").toString();

                                phvRevista.addView(new HolderRevista(MainActivity.this,nombre,descripcion,urlImg,idRevista,volumen.class));


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