package com.marcospb.peliculasapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marcospb.domain.utils.Result
import com.marcospb.peliculasapp.databinding.FragmentHomeBinding
import com.marcospb.peliculasapp.ui.home.adapter.MoviesAdapter
import com.marcospb.peliculasapp.ui.utils.hide
import com.marcospb.peliculasapp.ui.utils.show
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()


    private lateinit var adapterNowPlaying: MoviesAdapter
    private lateinit var adapterMostPopular: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        viewModel.getNowPlayingMovies()
        viewModel.getMostPopular()
        viewModel.getUIState().observe(viewLifecycleOwner, ::onUIState)
        viewModel.getUIStateMostPopular().observe(viewLifecycleOwner, ::onMostPopularState)

    }

    private fun setupViews() {
        adapterNowPlaying = MoviesAdapter { movie ->
            //onMovie click
            val dir = HomeFragmentDirections.actionNavigationHomeToDetail(movie.id)
            findNavController().navigate(dir)

        }

        adapterMostPopular = MoviesAdapter { movie ->
            //onMovie

            val dir = HomeFragmentDirections.actionNavigationHomeToDetail(movie.id)
            findNavController().navigate(dir)
        }

        binding.rvNowPlaying.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)

        binding.rvNowPlaying.adapter = adapterNowPlaying

        binding.rvMostPopular.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = adapterMostPopular
        }


    }


    fun onUIState(state: HomeViewModel.UiState) {

        when (state) {
            is HomeViewModel.UiState.HomeSuccess -> {
                binding.progressBar.hide()
                adapterNowPlaying.submitList(state.movieList)
            }
            is HomeViewModel.UiState.Error -> {
                binding.progressBar.hide()
                Toast.makeText(requireContext(), "${state.message}", Toast.LENGTH_SHORT).show()
            }
            is HomeViewModel.UiState.Loading -> {
                binding.progressBar.show()
            }
            is HomeViewModel.UiState.NotLoading -> {
                binding.progressBar.hide()
            }


        }

    }

    fun onMostPopularState(state: HomeViewModel.UiStateMostPopular) {
        when (state) {
            is HomeViewModel.UiStateMostPopular.HomeSuccessMostPopular -> {
                binding.progressBar.hide()
                adapterMostPopular.submitList(state.movieList)
            }

            is HomeViewModel.UiStateMostPopular.Loading -> {
                binding.progressBar.show()
            }

            is HomeViewModel.UiStateMostPopular.Error -> {
                Toast.makeText(requireContext(), "${state.message}", Toast.LENGTH_SHORT).show()
                binding.progressBar.hide()
            }
            is HomeViewModel.UiStateMostPopular.NotLoading -> {
                binding.progressBar.hide()

            }

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}