package com.iti.gofood.presentation.recipe.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.project_iti.data.Remote.Retrofit.MyRetrofit
import com.example.project_iti.data.Remote.source.RemoteDataSourceImpl
import com.example.project_iti.repo.Repo
import com.iti.gofood.R
import com.iti.gofood.databinding.FragmentDetailsBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

class DetailsFragment:Fragment() {
    private val repository by lazy { Repo(RemoteDataSourceImpl(MyRetrofit.service)) }
    private val viewModel : DetailsViewModel by viewModels(factoryProducer = { DetailsViewModelFactory(repository) })
    private var _binding: FragmentDetailsBinding?= null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details , container , false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailsBinding.bind(view)
        lifecycle.addObserver(binding.videoView)
        val mealId = arguments?.let { DetailsFragmentArgs.fromBundle(it).id }
        viewModel.mealDetails.observe(viewLifecycleOwner) {
            val meal = it.meals[0]
            binding.textViewMealTitle.text = meal.strMeal
            binding.textViewMealCategory.text = "Category: ${meal.strCategory}"
            binding.textViewMealArea.text = "Area: ${meal.strArea}"
            binding.inst.text = meal.strInstructions
            Glide.with(this)
                .load(meal.strMealThumb)
                .into(binding.imageViewMeal)
            val youtubeUrl = meal.strYoutube
            if (youtubeUrl != null) {
                val videoId = extractYoutubeVideoId(youtubeUrl)
                if (videoId != null) {
                    binding.videoView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            youTubePlayer.loadVideo(videoId, 0f)
                        }
                    })
                }
            }
        }
        viewModel.fetchMealById(mealId.toString())
    }

    private fun extractYoutubeVideoId(youtubeUrl: String): String? {
        val regex = "https?://(?:www\\.|m\\.)?youtube\\.com/watch\\?v=([\\w-]{11})".toRegex()
        val match = regex.find(youtubeUrl)
        return match?.groups?.get(1)?.value
    }
}