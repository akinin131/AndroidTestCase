package quiz.example.androiddevelopertestcase.model

data class MatchResult(
    val H2H: List<MatchItem>,
    val firstTeamResults: List<MatchItem>,
    val secondTeamResults: List<MatchItem>,
)