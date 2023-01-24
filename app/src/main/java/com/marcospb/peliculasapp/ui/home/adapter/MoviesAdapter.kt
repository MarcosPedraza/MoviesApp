package com.marcospb.peliculasapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marcospb.peliculasapp.R
import com.marcospb.peliculasapp.databinding.ItemMovieListBinding
import com.marcospb.peliculasapp.models.MovieItem

class MoviesAdapter(private val onMovieClick: (movie: MovieItem) -> Unit) :
    ListAdapter<MovieItem, MoviesAdapter.ViewHolder>(MovieItemCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemMovieListBinding.bind(itemView)

        init {
            binding.root.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    onMovieClick(getItem(adapterPosition))
                }
            }
        }

        fun bind(movie: MovieItem) {

            Glide.with(itemView.context)
                .load(movie.getPosterUrl())
                .into(binding.shapeableImageView)
            binding.movieName.text = movie.title

        }

    }


    object MovieItemCallback : DiffUtil.ItemCallback<MovieItem>() {
        override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
            return oldItem == newItem
        }

    }


}