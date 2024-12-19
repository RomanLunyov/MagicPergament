package com.lunyov.magicpergament

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import android.media.MediaPlayer

class SplashActivity : AppCompatActivity() {

    private lateinit var invitationText: TextView
    private lateinit var splashContainer: ConstraintLayout
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Инициализация элементов
        invitationText = findViewById(R.id.invitationText)
        splashContainer = findViewById(R.id.splashContainer)

        // Анимация текста
        displayTextWithTypingEffect("Коснись свитка на столе, \n чтобы узнать будущее")

        // Запуск фоновой музыки
        playBackgroundMusic()

        // Установка обработчика клика для перехода на MainActivity
        splashContainer.setOnClickListener {
            vibrateOnTransition()
            stopBackgroundMusic()
            goToMainActivity()
        }
    }

    private fun playBackgroundMusic() {
        mediaPlayer = MediaPlayer.create(this, R.raw.magic_background_music)
        mediaPlayer?.isLooping = true // Зациклить музыку
        mediaPlayer?.start()
    }

    private fun stopBackgroundMusic() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    private fun vibrateOnTransition() {
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val vibrationEffect = VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE)
            vibrator.vibrate(vibrationEffect)
        } else {
            vibrator.vibrate(200) // Для старых версий API
        }
    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // Закрыть текущую активность
    }

    private fun displayTextWithTypingEffect(text: String) {
        val charArray = text.toCharArray()
        var index = 0
        val builder = StringBuilder()

        val handler = Handler()
        val typingRunnable = object : Runnable {
            override fun run() {
                if (index < charArray.size) {
                    builder.append(charArray[index])
                    invitationText.text = builder.toString()
                    index++
                    handler.postDelayed(this, 100) // Задержка между символами
                }
            }
        }
        handler.post(typingRunnable)
    }

    override fun onDestroy() {
        super.onDestroy()
        stopBackgroundMusic()
    }
}
