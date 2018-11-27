package com.example.mm.volleyglidejsonrecyclerview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    RequestOptions options ;
    private static Context mContext;
    private static List<Anime> mData ;


    public Adapter(Context mContext, List lst) {


        this.mContext = mContext;
        this.mData = lst;
        options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.load)
                .error(R.drawable.load);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.anime_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Anime anime = mData.get(position);

        holder.tv_name.setText(anime.getName());
        holder.tv_rating.setText(anime.getRating());
        holder.tv_studio.setText(anime.getStudio());
        holder.tv_category.setText(anime.getCategorie());

        // load image from the internet using Glide
        Glide.with(mContext).load(anime.getImage_url()).apply(options).into(holder.img_thumbnail);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tv_name ;
        TextView tv_rating ;
        TextView tv_studio ;
        TextView tv_category;
        ImageView img_thumbnail;


        public MyViewHolder(View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.anime_name);
            tv_category = itemView.findViewById(R.id.categorie);
            tv_rating = itemView.findViewById(R.id.rating);
            tv_studio = itemView.findViewById(R.id.studio);
            img_thumbnail = itemView.findViewById(R.id.thumbnail);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Anime anime = mData.get(getAdapterPosition());
            Intent i = new Intent(mContext, AnimeActivity.class);

            // sending data process
            i.putExtra("anime_name",anime.getName());
            i.putExtra("anime_description",anime.getDescription());
            i.putExtra("anime_studio",anime.getStudio());
            i.putExtra("anime_category",anime.getCategorie());
            i.putExtra("anime_nb_episode",anime.getNb_episode());
            i.putExtra("anime_rating",anime.getRating());
            i.putExtra("anime_img",anime.getImage_url());

            mContext.startActivity(i);

        }
    }

}
