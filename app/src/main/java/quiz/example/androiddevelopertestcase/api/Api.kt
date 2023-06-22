package quiz.example.androiddevelopertestcase.api

import quiz.example.androiddevelopertestcase.model.MatchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("?met=H2H")
    suspend fun getMatch(
        @Query("APIkey") apiKey: String,
        @Query("firstTeamId") firstTeamId: String,
        @Query("secondTeamId") secondTeamId: String,
    ): MatchResponse
}