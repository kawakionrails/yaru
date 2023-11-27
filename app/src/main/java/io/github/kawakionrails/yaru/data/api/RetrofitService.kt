package io.github.kawakionrails.yaru.data.api

import io.github.kawakionrails.yaru.data.models.RandomUserResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object RetrofitService {

    private const val BASE_URL: String = "https://randomuser.me/"
    private val retrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    val randomUserService: RandomUserService = retrofit.create(RandomUserService::class.java)

}

interface RandomUserService {

    @GET("api")
    suspend fun getRandomUser(
        @Query("gender") gender: String
    ): Response<RandomUserResponse>

}