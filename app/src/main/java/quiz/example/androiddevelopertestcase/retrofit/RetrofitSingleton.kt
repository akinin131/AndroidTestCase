package quiz.example.androiddevelopertestcase.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitSingleton {
    private var retrofit: Retrofit? = null

    fun getInstance(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl("https://apiv2.allsportsapi.com/basketball/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
}



