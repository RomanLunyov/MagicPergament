package com.lunyov.magicpergament

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class SplashActivity : AppCompatActivity() {

    private lateinit var invitationText: TextView
    private lateinit var splashContainer: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Инициализация элементов
        invitationText = findViewById(R.id.invitationText)
        splashContainer = findViewById(R.id.splashContainer)

        // Анимация текста
        displayTextWithTypingEffect("Коснись свитка на столе, \n чтобы получить ответы")

        // Установка обработчика клика для перехода на MainActivity
        splashContainer.setOnClickListener {
            goToMainActivity()
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
}
