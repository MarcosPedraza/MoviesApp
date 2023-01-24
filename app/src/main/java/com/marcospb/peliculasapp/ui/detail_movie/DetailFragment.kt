package com.marcospb.peliculasapp.ui.detail_movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.marcospb.domain.utils.Result
import com.marcospb.peliculasapp.R

import com.marcospb.peliculasapp.databinding.FragmentDetailBinding
import com.marcospb.peliculasapp.models.MovieDetailItem
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: DetailViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val idMovie = DetailFragmentArgs.fromBundle(requireArguments()).idMovie
        viewModel.getDetailMovieById(idMovie)
        viewModel.getDetailState().observe(viewLifecycleOwner, ::OnViewState)

        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    private fun OnViewState(uiDetailState: DetailViewModel.UiDetailState) {

        when (uiDetailState) {
            is DetailViewModel.UiDetailState.OnSuccess -> {
                setInfo(uiDetailState.detailMovie)
            }

            is DetailViewModel.UiDetailState.OnError -> {
                Toast.makeText(requireContext(), "${uiDetailState.message}", Toast.LENGTH_SHORT).show()
            }

            is DetailViewModel.UiDetailState.Loading -> {

            }

        }

    }

    private fun setInfo(detailMovie: MovieDetailItem) {
        Glide.with(requireContext())
            .load(detailMovie.getPosterImage())
            .into(binding.imgPoster)

        binding.movieTitle.text = detailMovie.title
        binding.description.text = getString(R.string.description_s, detailMovie.description)
        binding.scoreMovie.text = getString(
            R.string.d_votes_d,
            detailMovie.score.toString(),
            detailMovie.voteAverage.toString()
        )


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}