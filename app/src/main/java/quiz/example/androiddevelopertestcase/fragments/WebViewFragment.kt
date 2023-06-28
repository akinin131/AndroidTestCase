package quiz.example.androiddevelopertestcase.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

import android.webkit.WebViewClient
import quiz.example.androiddevelopertestcase.R
import quiz.example.androiddevelopertestcase.databinding.FragmentWebViewBinding

class WebViewFragment : Fragment() {

    private lateinit var binding: FragmentWebViewBinding
    private lateinit var onBackPressedCallback: OnBackPressedCallback

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentWebViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!isOnline()) {
            findNavController().navigate(R.id.action_webViewFragment_to_notInternetFragment)
        } else {
            binding.webView.webViewClient = WebViewClient()
            binding.webView.settings.javaScriptEnabled = true

            binding.webView.settings.javaScriptCanOpenWindowsAutomatically = true

            val url =
                "https://www.britannica.com/sports/basketball"
            binding.webView.loadUrl(url)

            onBackPressedCallback = object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (binding.webView.canGoBack()) {
                        binding.webView.goBack()
                    } else {
                        findNavController().navigateUp()
                    }
                }
            }
            requireActivity().onBackPressedDispatcher.addCallback(
                viewLifecycleOwner,
                onBackPressedCallback
            )
        }

        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_webViewFragment_to_basketballListFragment)
        }
    }

    private fun isOnline(): Boolean {
        val connectivityManager =
            requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
        return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (::onBackPressedCallback.isInitialized) {
            onBackPressedCallback.remove()
            super.onDestroyView()
        }
    }
}
