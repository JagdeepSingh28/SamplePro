package com.example.jagdeepsingh.samplepro.imageCaching;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jagdeepsingh.samplepro.R;

import java.util.List;

/**
 * Created by jagdeep.singh on 05-10-2016.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    private List<Movie> moviesList;
    private ImageCache  imageCache;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;
        public ImageView image_thumb;

        public MyViewHolder(View view) {
            super(view);

            image_thumb = (ImageView) view.findViewById(R.id.image_thumb);
            title = (TextView) view.findViewById(R.id.title);
            genre = (TextView) view.findViewById(R.id.genre);
            year = (TextView) view.findViewById(R.id.year);
        }
    }


    public MovieAdapter(List<Movie> moviesList) {
        this.moviesList = moviesList;
        imageCache = ImageCache.getInstance();
        imageCache.initializeCache();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Movie movie = moviesList.get(position);

        Bitmap bitmap = imageCache.getImageFromCahce(movie.getImageUrl());

        if(bitmap == null){
//            DownloadImageTask imageTask = new DownloadImageTask(this,300,300);
//            imageTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,new String[]{movie.getImageUrl()});

            DownloadImageTask imgTask = new DownloadImageTask(holder.image_thumb, 300, 300);//Since you are using it from `Activity` call second Constructor.

            imgTask.execute(movie.getImageUrl());
        }else{
            holder.image_thumb.setImageBitmap(bitmap);
        }

        holder.title.setText(movie.getTitle());
        holder.genre.setText(movie.getGenre());
        holder.year.setText(movie.getYear());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}