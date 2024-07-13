package com.example.moviepractice.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.moviepractice.databinding.RowLayoutBinding
import com.example.moviepractice.model.Movie
import com.example.moviepractice.model.MovieResponse
import com.example.moviepractice.utils.Constants


class MovieAdapter(private var movieList: List<Movie> = emptyList()): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(var binding: RowLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movie) {
            binding.apply {
                imageView.load(Constants.POSTER_BASE_URL + item.posterPath)
                textView.text = item.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflator = LayoutInflater.from(parent.context)
        return MovieViewHolder(RowLayoutBinding.inflate(layoutInflator, parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = movieList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun setMovie(movies: List<Movie>) {
        movieList = movies
        notifyDataSetChanged()
    }
}