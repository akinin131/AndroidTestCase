package quiz.example.androiddevelopertestcase.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import quiz.example.androiddevelopertestcase.api.Api
import quiz.example.androiddevelopertestcase.model.MatchItem
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitViewModel : ViewModel() {

    private val _matches = MutableLiveData<List<MatchItem>>()
    val matches: LiveData<List<MatchItem>> get() = _matches

    fun fetchMatches() {
        viewModelScope.launch {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://apiv2.allsportsapi.com/basketball/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

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

