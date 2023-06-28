package quiz.example.androiddevelopertestcase.model

// Объект данных, содержащий списки матчей для сложения в списке матчей
data class MatchResult(
    val H2H: List<MatchItem>,
    val firstTeamResults: List<MatchItem>,
    val secondTeamResults: List<MatchItem>,
)