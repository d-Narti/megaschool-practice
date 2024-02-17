package ru.sample.duckapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch
import ru.sample.duckapp.databinding.ActivityMainBinding
import ru.sample.duckapp.infra.Api

class InvalidHttpCodeException : Exception()

class MainActivity : AppCompatActivity() {

  private val binding by lazy {
    ActivityMainBinding.inflate(layoutInflater)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)

    binding.button.setOnClickListener {
      lifecycleScope.launch {
        binding.button.isEnabled = false
        val url = try {
          when (val code = binding.editText.text?.toString()) {
            "", null -> Api.ducksApi.getRandomDuck().url
            else -> {
              if (Api.isHttpStatusCode(code)) {
                Api.codeImageUrl(code)
              } else {
                throw InvalidHttpCodeException()
              }
            }
          }
        } catch (exc: InvalidHttpCodeException) {
          binding.textLayout.error = "Invalid HTTP code"
          null
        } catch (exc: Exception) {
          Log.e("rtgjrtjkgnrt", "grtbgrtjhbgtr")
          binding.textLayout.error = "Error. Please, check your internet connection"
          null
        }
        url?.let {
          Glide.with(binding.image).load(it).into(binding.image)
          binding.textLayout.error = null
        }
        binding.button.isEnabled = true
      }
    }
  }
}