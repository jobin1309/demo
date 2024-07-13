package com.example.moviepractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.moviepractice.adapter.MovieAdapter
import com.example.moviepractice.databinding.ActivityMainBinding
import com.example.moviepractice.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MovieViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var mAdapter: MovieAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel.movieResponse()
        mAdapter = MovieAdapter()

        viewModel.movieDb.observe(this) { response ->
            if (response != null) {
                mAdapter.setMovie(response)
            }
            Log.d("response", response.toString())
        }

        binding.recyclerView.adapter = mAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }
}