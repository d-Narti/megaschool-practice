package ru.sample.duckapp.data

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import ru.sample.duckapp.domain.Duck

interface DucksApi {
  @GET("random")
  suspend fun getRandomDuck(): Duck

  @GET("http/{code}")
  suspend fun getCodeDuck(
    @Path("code") code: String
  ): ResponseBody
}