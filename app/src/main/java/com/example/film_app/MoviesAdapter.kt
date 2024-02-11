package com.example.film_app

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MoviesAdapter(var movies: List<MovieCard>, var context: Context) : RecyclerView.Adapter<MoviesAdapter.MyViewHolder>(){

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val image: ImageView = view.findViewById(R.id.movie_list_image)
        val title: TextView = view.findViewById(R.id.movie_list_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movies_in_list, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.count()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = movies[position].title
        var imageId = context.resources.getIdentifier(
            movies[position].image,
            "drawable",
            context.packageName
        )
        holder.image.setImageResource(imageId)
    }
}