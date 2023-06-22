package quiz.example.androiddevelopertestcase.fragments

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import quiz.example.androiddevelopertestcase.viewModel.RetrofitViewModel

import quiz.example.androiddevelopertestcase.R
import quiz.example.androiddevelopertestcase.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding
    private lateinit var retrofitViewModel: RetrofitViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSplashBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        retrofitViewModel = ViewModelProvider(requireActivity())[RetrofitViewModel::class.java]

        CoroutineScope(Dispatchers.Main).launch {
            binding.progressBar.max = 2200
            val value = 2000
            ObjectAnimator.ofInt(binding.progressBar, "progress", value).setDuration(2000).start()
            delay(2000)

            retrofitViewModel.fetchMatches()

            navigateToMainFragment()
        }
    }

    private fun navigateToMainFragment() {
        findNavController().navigate(R.id.action_splashFragment_to_basketballListFragment)
    }
}
