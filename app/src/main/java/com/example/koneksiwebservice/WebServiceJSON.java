package com.example.koneksiwebservice;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.koneksiwebservice.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WebServiceJSON extends AppCompatActivity {

    private TextView textHasilJSON;
    private RequestQueue mQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_json);

        mQueue = Volley.newRequestQueue(this);
        textHasilJSON = findViewById(R.id.textJSON);
        Button btnJson = findViewById(R.id.btnJSON);

        btnJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uraiJSON();

            }
        });
    }
    private void uraiJSON(){
        String url = "http://papaside.com/data.php";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject mahasantri = jsonArray.getJSONObject(i);

                        String kota = mahasantri.getString("Kota");
                        String siang = mahasantri.getString("siang");
                        String malam = mahasantri.getString("malam");
                        String diniHari = mahasantri.getString("dini_hari");
                        String suhu = mahasantri.getString("suhu");
                        String kelembapan = mahasantri.getString("Kelembapan");

                        textHasilJSON.append(kota + "," + siang + "," + malam + "," + diniHari + "," + suhu + "," + kelembapan + "\n\n");
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });
        mQueue.add(request);

    }
}
