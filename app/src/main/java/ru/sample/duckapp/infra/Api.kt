package ru.sample.duckapp.infra

import androidx.core.text.isDigitsOnly
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.sample.duckapp.data.DucksApi

object Api {
  private const val BASE_URL = "https://random-d.uk/api/v2/"

  private val retrofit by lazy {
    Retrofit.Builder()
      .baseUrl(BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }

  val ducksApi by lazy {
    retrofit.create(DucksApi::class.java)
  }

  fun codeImageUrl(code: String): String {
    return "${BASE_URL}http/$code"
  }

  fun isHttpStatusCode(code: String): Boolean {
    return code.isDigitsOnly() && (100..599).contains(code.toInt())
  }
}