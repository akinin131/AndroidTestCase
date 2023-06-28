package quiz.example.androiddevelopertestcase.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import quiz.example.androiddevelopertestcase.retrofit.RetrofitSingleton
import quiz.example.androiddevelopertestcase.retrofit.Api
import quiz.example.androiddevelopertestcase.model.MatchItem

class RetrofitViewModel : ViewModel() {
    private val _matches = MutableLiveData<List<MatchItem>>()
    val matches: LiveData<List<MatchItem>> get() = _matches

    fun fetchMatches() {
        viewModelScope.launch {
            val retrofit = RetrofitSingleton.getInstance()
            val api = retrofit.create(Api::class.java)
            try {
                val match = api.getMatch(
                    "c127f79638e90ca57bf2eb2e364e0bda0a97ebb548b5fc874e17f791c7841111",
                    "2616",
                    "2617"
                )
                _matches.value =
                    match.result.H2H + match.result.firstTeamResults + match.result.secondTeamResults
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
