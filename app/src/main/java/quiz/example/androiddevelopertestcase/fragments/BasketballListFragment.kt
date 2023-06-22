package quiz.example.androiddevelopertestcase.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import quiz.example.androiddevelopertestcase.R
import quiz.example.androiddevelopertestcase.adapter.BasketballListAdapter
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import quiz.example.androiddevelopertestcase.databinding.FragmentBasketballListBinding
import quiz.example.androiddevelopertestcase.vewModel.RetrofitViewModel

class BasketballListFragment : Fragment() {

    private lateinit var binding: FragmentBasketballListBinding
    private lateinit var adapter: BasketballListAdapter
    private lateinit var onBackPressedCallback: OnBackPressedCallback
    private lateinit var retrofitViewModel: RetrofitViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentBasketballListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!isOnline()) {
            findNavController().navigate(R.id.action_basketballListFragment_to_notInternetFragment)
        }

        adapter = BasketballListAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        retrofitViewModel = ViewModelProvider(requireActivity())[RetrofitViewModel::class.java]
        retrofitViewModel.matches.observe(viewLifecycleOwner) { matches ->
            adapter.setMatches(matches)
        }

        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_basketballListFragment_to_webViewFragment)
        }

        onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            onBackPressedCallback)

    }

    private fun isOnline(): Boolean {
        val connectivityManager =
            requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
        return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    }
}
