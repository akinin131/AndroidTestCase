package quiz.example.androiddevelopertestcase.model

// Объект данных, отображающий один матч.
data class MatchItem(
    val event_date: String,
    val event_time: String,
    val event_home_team: String,
    val event_away_team: String,
    val event_final_result: String,
)

