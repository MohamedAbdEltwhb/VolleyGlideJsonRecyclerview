package com.example.mm.volleyglidejsonrecyclerview;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class AnimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime);

        // hide the default actionbar
        getSupportActionBar().hide();

        // ini views
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar_id);
        collapsingToolbarLayout.setTitleEnabled(true);

        TextView tv_name = findViewById(R.id.aa_anime_name);
        TextView tv_studio = findViewById(R.id.aa_studio);
        TextView tv_categorie = findViewById(R.id.aa_categorie) ;
        TextView tv_description = findViewById(R.id.aa_description);
        TextView tv_rating  = findViewById(R.id.aa_rating) ;
        ImageView img = findViewById(R.id.aa_thumbnail);


        // Recieve data
        Intent intent = getIntent();
        String name  = intent.getStringExtra("anime_name");
        String description = intent.getStringExtra("anime_description");
        String studio = intent.getStringExtra("anime_studio") ;
        String category = intent.getStringExtra("anime_category");
        String rating = intent.getStringExtra("anime_rating") ;
        String image_url = intent.getStringExtra("anime_img") ;

        // setting values to each view
        tv_name.setText(name);
        tv_categorie.setText(category);
        tv_description.setText(description);
        tv_rating.setText(rating);
        tv_studio.setText(studio);

        collapsingToolbarLayout.setTitle(name);


        RequestOptions requestOptions = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.loading_shape)
                .error(R.drawable.loading_shape);


        // set image using Glide
        Glide.with(this)
                .load(image_url)
                .apply(requestOptions)
                .into(img);

    }
}
