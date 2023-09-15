package com.jalper.myfirstapp.chronometer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.jalper.myfirstapp.R
import com.jalper.myfirstapp.databinding.ActivityChronometerBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ChronometerActivity : AppCompatActivity() {

    private val binding: ActivityChronometerBinding by lazy {
        ActivityChronometerBinding.inflate(layoutInflater)
    }

    private var startTime: Long = 0
    private var pausedTime: Long = 0
    private var isRunning: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.fabActivityChronometerStart.setOnClickListener {
            start()
        }

        binding.fabActivityChronometerStop.setOnClickListener {
            stop()
        }

        binding.fabActivityChronometerReset.setOnClickListener {
            reset()
        }
    }

    private fun start() {
        if (!isRunning) {
            isRunning = true

            startTime = System.currentTimeMillis() - pausedTime

            lifecycleScope.launch(Dispatchers.Main) {
                while (isRunning) {
                    val elapsedTime = System.currentTimeMillis() - startTime

                    binding.tvActivityChronometerResult.text = elapsedTime.toTimeString()
                    delay(1000)
                }
            }
        }
    }

    private fun stop() {
        isRunning = false
        pausedTime = System.currentTimeMillis() - startTime
    }

    private fun reset() {
        isRunning = false
        startTime = 0L
        binding.tvActivityChronometerResult.text = getString(R.string.default_chronometer_value)
    }

    private fun Long.toTimeString(): String {
        val totalSeconds = this / 1000

        val seconds = totalSeconds % 60 // Resto de division por 60, 1 minuto tiene 60 segundos -> 80 segundos son 1 minuto y 20 segundos
        val minutes = (totalSeconds / 60) % 60
        val hours = totalSeconds / 60 / 60

        val hoursString = if (hours < 10) "0$hours" else hours.toString()
        val minutesString = if (minutes < 10) "0$minutes" else minutes.toString()
        val secondsString = if (seconds < 10) "0$seconds" else seconds.toString()

        return "$hoursString:$minutesString:$secondsString"
    }
}