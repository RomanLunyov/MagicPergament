package com.lunyov.magicpergament

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var imageView: ImageView
    private val handler = Handler()
    private var mediaPlayer: MediaPlayer? = null

    private val predictions = listOf(
        "Скоро вы получите долгожданное известие",
        "Ваша жизнь наполнится радостью и светом",
        "Вы встретите интересного человека",
        "Неожиданное событие изменит ваш день",
        "Скоро вы получите новый опыт",
        "Загадайте желание — оно сбудется",
        "Будьте готовы к приятным сюрпризам",
        "Удача уже на вашем пороге",
        "Завтра будет день, полный возможностей"
    )

    private fun formatTextIntoTwoLines(text: String): String {
        val words = text.split(" ")
        val midPoint = words.size / 2

        // Формируем первую и вторую строку
        val firstLine = words.subList(0, midPoint).joinToString(" ")
        val secondLine = words.subList(midPoint, words.size).joinToString(" ")

        return "$firstLine\n$secondLine"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        imageView = findViewById(R.id.imageView)

        // Устанавливаем магический шрифт
        val customFont = ResourcesCompat.getFont(this, R.font.greatvibes)
        textView.typeface = customFont

        // Анимация текста при запуске
        val initialText = "Подумай о чём хочешь спросить \n и нажми, чтобы узнать ответ"
        displayTextWithTypingEffect(initialText)

        // Устанавливаем обработчик клика на картинку
        imageView.setOnClickListener {
            textView.text = "" // Очищаем текст
            playMagicSound()
            val prediction = getRandomPrediction()
            displayPredictionWithTypingEffect(prediction)
            addShareButton(prediction)
        }
    }

    private fun getRandomPrediction(): String {
        return predictions.random()
    }

    private fun playMagicSound() {
        mediaPlayer = MediaPlayer.create(this, R.raw.magic_sound)
        mediaPlayer?.start()
    }

    private fun displayTextWithTypingEffect(text: String) {
        val charArray = text.toCharArray()
        var index = 0
        val builder = StringBuilder()

        val typingRunnable = object : Runnable {
            override fun run() {
                if (index < charArray.size) {
                    builder.append(charArray[index])
                    textView.text = builder.toString()
                    index++
                    handler.postDelayed(this, 100) // Задержка между символами
                }
            }
        }
        handler.post(typingRunnable)
    }

    private fun displayPredictionWithTypingEffect(prediction: String) {
        val charArray = prediction.toCharArray()
        var index = 0
        val builder = StringBuilder()

        val typingRunnable = object : Runnable {
            override fun run() {
                if (index < charArray.size) {
                    builder.append(charArray[index])
                    textView.text = builder.toString()

                    // Переход на новую строку после каждых 4 слов
                    if (builder.split(" ").size % 4 == 0 && charArray[index] == ' ') {
                        builder.append("\n")
                    }

                    index++
                    handler.postDelayed(this, 100) // Задержка между символами
                }
            }
        }
        handler.post(typingRunnable)
    }

    private fun addShareButton(prediction: String) {
        // Добавление кнопки поделиться
        imageView.setOnLongClickListener {
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Моё предсказание: \"$prediction\"")
                type = "text/plain"
            }
            if (shareIntent.resolveActivity(packageManager) != null) {
                startActivity(Intent.createChooser(shareIntent, "Поделиться предсказанием через"))
            } else {
                Toast.makeText(this, "Не удалось найти приложение для отправки", Toast.LENGTH_SHORT).show()
            }
            true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
    }
}
