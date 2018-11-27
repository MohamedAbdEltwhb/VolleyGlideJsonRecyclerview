package com.example.mm.volleyglidejsonrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String URL_JSON = "https://gist.githubusercontent.com/aws1994/f583d54e5af8e56173492d3f60dd5ebf/raw/c7796ba51d5a0d37fc756cf0fd14e54434c547bc/anime.json";
    private RequestQueue mRequestQueue;
    private List<Anime> lstAnime;
    private RecyclerView myrv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstAnime = new ArrayList<>();

        myrv = findViewById(R.id.rv);
        myrv.setLayoutManager(new LinearLayoutManager(this));
        myrv.setHasFixedSize(true);
        myrv.setNestedScrollingEnabled(false);
        myrv.setItemAnimator(new DefaultItemAnimator());

        mRequestQueue = Volley.newRequestQueue(this);
        jsonCall();
    }

    private void jsonCall() {
        final JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL_JSON
                , null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                try {

                    for (int i = 0; i < response.length(); i++) {

                        JSONObject jsonObject = response.getJSONObject(i);

                        String name = jsonObject.getString("name");
                        String Rating = jsonObject.getString("Rating");
                        String description = jsonObject.getString("description");
                        String img = jsonObject.getString("img");
                        String studio = jsonObject.getString("studio");
                        String categorie = jsonObject.getString("categorie");

                        lstAnime.add(new Anime(name, description, Rating, img, categorie, studio));

                    }

                    Adapter adapter = new Adapter(MainActivity.this, lstAnime);
                    myrv.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }

//    public void jsoncall() {
//
//        JsonArrayRequest ArrayRequest = new JsonArrayRequest(URL_JSON, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//
//                JSONObject jsonObject = null;
//
//                for (int i = 0 ; i<response.length();i++) {
//
//                    try {
//
//                        jsonObject = response.getJSONObject(i);
//                        Anime anime = new Anime();
//
//                        anime.setName(jsonObject.getString("name"));
//                        anime.setRating(jsonObject.getString("Rating"));
//                        anime.setDescription(jsonObject.getString("description"));
//                        anime.setImage_url(jsonObject.getString("img"));
//                        anime.setStudio(jsonObject.getString("studio"));
//                        anime.setCategorie(jsonObject.getString("categorie"));
//                        //Toast.makeText(MainActivity.this,anime.toString(),Toast.LENGTH_SHORT).show();
//                        lstAnime.add(anime);
//                    }
//                    catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//
//                Toast.makeText(MainActivity.this,"Size of Liste "+String.valueOf(lstAnime.size()),Toast.LENGTH_SHORT).show();
//                Toast.makeText(MainActivity.this,lstAnime.get(1).toString(), Toast.LENGTH_SHORT).show();
//
//                setRvadapter(lstAnime);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//
//
//        requestQueue = Volley.newRequestQueue(MainActivity.this);
//        requestQueue.add(ArrayRequest);
//    }

    public void setRvadapter(List<Anime> lst) {

        Adapter myAdapter = new Adapter(this, lst);
        myrv.setLayoutManager(new LinearLayoutManager(this));
        myrv.setAdapter(myAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

}
