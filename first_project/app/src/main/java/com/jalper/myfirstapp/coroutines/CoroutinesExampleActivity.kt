package com.jalper.myfirstapp.coroutines

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.jalper.myfirstapp.R
import com.jalper.myfirstapp.databinding.ActivityCoroutinesExampleBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull
import java.net.URL

class CoroutinesExampleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoroutinesExampleBinding

    private val taskOneState = MutableLiveData<CoroutineResult<String>>()
    private val taskTwoState = MutableLiveData<CoroutineResult<Bitmap>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoroutinesExampleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnActivityCoroutinesExampleTaskOne.setOnClickListener {
            launchTaskOne()
        }

        binding.btnActivityCoroutinesExampleTaskTwo.setOnClickListener {
            launchTaskTwo()
        }

        // Paso 1
        taskOneState.observe(this) { result ->
            handleTaskOneState(result)
        }

        taskTwoState.observe(this) { result ->
            handleTaskTwoState(result)
        }
    }

    // Paso 2
    private fun handleTaskOneState(state: CoroutineResult<String>) {
        when (state) {
            is CoroutineResult.Loading -> {
                binding.pbActivityCoroutinesExampleTasks.visibility = View.VISIBLE
            }

            is CoroutineResult.Success -> {
                binding.pbActivityCoroutinesExampleTasks.visibility = View.GONE
                binding.tvActivityCoroutinesExampleResult.text =
                    getString(R.string.coroutine_result, state.result)
            }

            else -> {
                binding.pbActivityCoroutinesExampleTasks.visibility = View.GONE
            }
        }
    }

    private fun handleTaskTwoState(state: CoroutineResult<Bitmap>) {
        when (state) {
            is CoroutineResult.Loading -> {
                binding.pbActivityCoroutinesExampleTasks.visibility = View.VISIBLE
            }

            is CoroutineResult.Success -> {
                binding.pbActivityCoroutinesExampleTasks.visibility = View.GONE
                binding.ivActivityCoroutinesExampleImage.setImageBitmap(state.result)
            }

            is CoroutineResult.Error -> {
                binding.pbActivityCoroutinesExampleTasks.visibility = View.GONE

                // Mostramos el error en un toast
                Toast.makeText(this, state.error, Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Paso 3
    private fun launchTaskOne() {
        taskOneState.value = CoroutineResult.Loading()

        lifecycleScope.launch(Dispatchers.IO) {

            withTimeoutOrNull(2000) {
                val result = doTaskOne()

                withContext(Dispatchers.Main) {
                    taskOneState.value = CoroutineResult.Success(result)
                }
            }
        }
    }

    // Paso 4
    private suspend fun doTaskOne(): String {
        delay(3000)
        return "My first coroutine!"
    }

    private fun launchTaskTwo() {
        taskTwoState.value = CoroutineResult.Loading()

        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val result =
                    doTaskTwo("https://images.wikidexcdn.net/mwuploads/wikidex/thumb/7/77/latest/20150621181250/Pikachu.png/800px-Pikachu.png")

                withContext(Dispatchers.Main) {
                    taskTwoState.value = CoroutineResult.Success(result)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    taskTwoState.value = CoroutineResult.Error("Error al cargar imagen")
                }
            }
        }
    }

    private fun doTaskTwo(url: String): Bitmap {
        val responseStream = URL(url).openStream()
        return BitmapFactory.decodeStream(responseStream)
    }
}