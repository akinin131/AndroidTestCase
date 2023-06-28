package quiz.example.androiddevelopertestcase.model

// Объект данных, содержащий ответ от сервера, включающий результаты матчей
data class MatchResponse(
    val success: Int,
    val result: MatchResult,
)