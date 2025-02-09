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
        "Завтра будет день, полный возможностей",
        "Если Вы проявите инициативу, успех не заставит себя ждать",
        "Ваши надежды и планы сбудутся сверх всяких ожиданий",
        "Готовьтесь к романтическим приключениям",
        "В этом месяце ночная жизнь для вас",
        "Вам пора отдохнуть",
        "Вам предлагается мечта всей жизни. Скажите да!",
        "Вас ждет приятный сюрприз",
        "Время – ваш союзник, лучше отложить принятие важного решения хотя бы на день",
        "Время и терпение,  вас ждут много сюрпризов!",
        "Ни одно важное событие не пройдет мимо вас",
        "Ближайшие праздники привнесут в вашу жизнь радость!",
        "Предстоящие праздники отметьте в кругу друзей",
        "Вас ожидают легкие романтические увлечения",
        "Через неделю вас с наскоку поцелует ваша судьба",
        "Для каждого найдется кусочек рая на планете",
        "Это виртуальный поцелуй. Прижать к сердцу. Повторить 7 раз",
        "Ближайшие родственники замыслили что-то доброе",
        "Любимая песня на радио – предвестник удачи",
        "К лету ты  подрастешь на 2 см",
        "К лету ты  похудеешь на несколько килограмм",
        "Смейся всегда беспечно, чтобы счастье длилось вечно",
        "Если в течение 15 секунд не крикнешь УРА!, то все пропало!",
        "На неделе жди письмо. Уже отправили его",
        "Это магическая записка. Она увеличивает силу Вашей удачи в 2 раза",
        "Через час ты снова проголодаешься. Сладким не наешься",
        "Скоро Вы проснетесь от поцелуя и аромата кофе",
        "Обратите внимание на брюнетку рядом",
        "Внимательно следи за своим здоровьем",
        "Интуиция не подведет тебя в этом году",
        "Твоя будущая любовь ждет своего часа",
        "Повезет в денежных вопросах",
        "Будешь много плакать, но только от радости",
        "Тебя ожидает интересная поездка",
        "В любом начинании будет преследовать удача",
        "Тебе будут помогать люди, которые сейчас рядом",
        "Ожидаются кардинальные изменения",
        "Готовься к романтическому свиданию скоро",
        "Стремительный карьерный рост ожидает в ближайший год",
        "Успешным будет любое начатое дело",
        "Жди настоящую поддержку от семьи",
        "Нужно отдохнуть! Пора выбрать новое хобби",
        "Посвяти больше времени себе",
        "Встретишь новую любовь в ближайший год",
        "В скором будущем — большое приобретение",
        "Сбудется все, что задумано",
        "В скором времени будет много развлечений",
        "Найдешь новое интересное занятие",
        "Настало время проявить лидерские качества",
        "Выиграешь в лотерею в этом году",
        "Ждет много интересных приключений",
        "Не бойся поступать так, как считаешь нужным",
        "Высокая вероятность серьезной находки",
        "Благоприятное время для самореализации",
        "Ждет тебя, мой друг, услада 一 повышение оклада",
        "Жениха богатого, красивого найдешь,\n" +
                "Под венец в грядущий год с ним ты попадешь",
        "Ты уедешь жить в деревню,\n" +
                "Купишь там коров и землю",
        "Осторожной стоит быть, \n" +
                "Лишнего чтоб не купить",
        "Ты точно разбогатеешь, \n" +
                "но только на 2 дня \n" +
                "в день аванса и зарплаты",
        "Если станет сложно идти к своей мечте, ляг и лежи в ее направлении",
        "В этом году будет отвращение от сладкого, будешь пить только полусухое",
        "Будешь чаще улыбаться:\n" +
                "Все мечты начнут сбываться",
         "Весь год будешь в шоколаде. Главное не теряй бдительности, чтобы не получить лишний вес",
         "Тебя ждет сильный удар в кабинете директора, будь осторожней, ведь он поставит новую тумбочку",
         "Не учись на своих или чужих ошибках, лучше научись играть на гитаре",
         "Кому-то в этом году повезет и, возможно, этим «кем-то» станешь ты",
         "Бумажник похудеет очень,\n" +
                 "На море скоро ты захочешь",
        "Будь осторожней, в ближайшее время ожидается мощный взрыв 一 лопнут все твои завистники",
        "Перед тобой откроются все двери… супермаркетов",
        "Смотри внимательно по сторонам, переходя дорогу 一 большая вероятность встретить свою судьбу",
        "На тебя совершат нападение Успех, Любовь и Богатство. Отбиться от них ты не сможешь",
        "Если будешь просыпаться рано, ни разу не опоздаешь на работу",
        "В скором времени тебя ждет много интересных и увлекательных… домашних дел",
        "Тебя ожидает повышение на работе 一 офис перенесут на этаж выше",
        "Ты наконец-то избавишься от плохой привычки, а взамен приобретешь две новые",
        "Жди коварного и неожиданного предательства… от весов",
        "Не будешь точно ты болеть,\n" +
                "И в делах все будет удаваться,\n" +
                "Если не забудешь шарф надеть\n" +
                "И почаще будешь улыбаться",
        "Друзей удивить ты сильно сумеешь 一 \n" +
                "Ведь к алкоголю совсем охладеешь",
        "Осторожно бегай ночью в гололед, чтобы был прекрасным весь грядущий год",
        "Тебя ожидает встреча с золотой рыбкой. Вкусной, запеченной с овощами",
        "Год будет интересным и веселым… у тараканов, живущих в твоей голове",
        "Если будешь много улыбаться, разбогатеешь",
        "Скоро жди существенную прибавку в весе 一 кошелек станет намного тяжелее",
        "Жди пополнения в доме. Ведь после выходных холодильник значительно опустеет — придется восполнить запасы",
        "Будет много путешествий 一 на работу, на дачу и в супермаркеты",
        "В этом году тебя ждет много открытий… открытий бутылок с шампанским",
        "Ты мечтала о повышении, и оно обязательно будет. Повысится твой вес",
        "Вторая половинка скоро может найти клад. Так что лучше прячь свою заначку",
        "По ночам тебя будет мучить совесть, поэтому спи днем",
        "Ближайший год будет как зебра, но ты будь хитрее 一 дойди до белой полосы и иди по ней",
        "Встретишь большую любовь в этом году, килограммов 90-120",
        "Тебя ждет чудесный отпуск «все включено» 一 у родственников",
        "В этом году сумеешь заглянуть за горизонт. Колесо обозрения уже ждет тебя",
        "Если спрячешь ты заначку,\n" +
                "Найдешь попозже денег пачку",
        "Встретив женщину с пустым ведром, брось в него деньги. И человеку приятно, и себя от бед убережешь",
        "Ждет тебя крупная потеря… утратишь дар речи от счастья",
        "Ждут тебя 2 новости. Плохая 一 ты сильно прибавишь в весе. Хорошая 一 эта прибавка будет в кошельке",
        "Ты очень сблизишься с коллегами — чаще будете оставаться сверхурочно",
        "В этом году не придется вам мучиться,\n" +
                "Ведь все задуманное точно получится",
        "Твои мечты обретут невероятную силу и наконец-то объявят дивану бой",
        "Не стоит быть жадным, делись с окружающими… своими проблемами и переживаниями",
        "Подружись с соседями и бабушками на лавочке, ведь они 一 главный источник знаний",
        "В этому году сможешь достигнуть потрясающих высот 一 заберешься на гору",
        "Пора прикупить парашют — в этом году ты будешь на седьмом небе от счастья",
        "На твоем жизненном пути будет много ухабов, но каждый из них будет подкидывать тебя вверх",
        "Сядешь скоро ты на мель,\n" +
                "Но рядом будет пятизвездочный отель",
        "Жить будешь ты совсем не грустно,\n" +
                "Хрустеть в бумажнике «капустой»,\n" +
                "С ключами от авто в кармане\n" +
                "И в одежде от Армани",
        "Будешь пить и не болеть,\n" +
                "Будешь есть и не толстеть,\n" +
                "Деньги потекут рекой, \n" +
                "Будешь завтракать икрой",
        "У тебя знакомых 一 море,\n" +
                "Жди их всех ты в гости вскоре",
        "Уже очень скоро не останется места для переживаний. Его займет симпатичный парень/девушка",
        "В этом году ты вырастешь на 10 сантиметров 一 купишь себе новые туфли",
        "Жизнь изменится наверняка 一 \n" +
                "Станет она весела и легка",
        "Жить будешь ты весь год вольготно 一 \n" +
                "Набьешь кошелёк купюрами плотно",
        "Будь смелее в своем выборе. Мало знать себе цену — надо еще пользоваться спросом",
        "Скоро вы отправитесь в поездку",
        "Будет новое приятное знакомство",
        "Сегодня не путай настроение с самочувствием",
        "Берегите людей, после встречи с которыми, что-то светлое и радостное поселяется в вашей душе",
        "Сегодня следуй за настроением, никуда не сворачивай!",
        "Сегодня чаще смотри по сторонам, рядом кое-что интересное!",
        "Осторожно, впереди волна впечатлений!",
        "Не отказывай себе в отдыхе, он обещает быть незабываемым!",
        "Удача сопутствует твоим планам на следующей неделе!",
        "Просто скажи «Да». Скоро узнаешь, кому и когда!",
        "Обрати внимание на своё настроение – его тянет вверх!",
        "Вас ждет немало приятных и запоминающихся моментов",
        "Ваши романтические мечты сбудутся!",
        "Ждите необычного признания в любви",
        "Романтика переместит вас в новом направлении",
        "Сейчас очень удачное время для воплощения любовных желаний",
        "Улыбайся! Кто-то может влюбиться в твою улыбку!",
        "Ты можешь бесконечно смотреть на три вещи... а в итоге купить семь!",
        "Если опоздали к рассвету любуйтесь закатом!",
        "Все твои желания обязательно сбудутся",
        "Хочется приключений? Скоро они точно будут",
        "Иди вперед к своей удаче! И будет сердце пусть горячим!",
        "Кушай сладости - это к радости!",
        "У тебя много личных талантов, которые нравятся другим",
        "Изменится круг вашего общения",
        "Ваша цель достижима",
        "Вы много добьетесь, если возьмете все в свои руки",
        "Из безвыходной ситуации всегда найдётся выход",
        "Не оставляйте усилий и получите желаемое",
        "Ориентируйся на маленькие победы - они повлекут за собой большие",
        "Тебе будет сопутствовать успех",
        "В следующем году вы наконец-то откроете все банки с вареньем... но забудете, зачем они вам",
        "Ваши кофейные привычки начнут диктовать погоду: чем больше кофе, тем теплее",
        "Скоро Вы обнаружите, что ваш кот умеет работать с Excel, но не может понять, как закрывать окна",
        "Следующий год принесет вам много счастья, а старый — пару потерянных носков",
        "Завтра вы откроете секрет счастья: это шоколад и пауза на 5 минут",
        "В этом месяце вы победите в конкурсе... на лучшее сидение на диване",
        "Ваша сила воли ослабнет, когда увидите пиццу",
        "Сегодня вы станете мастером — хотя бы в оправданиях",
        "Через неделю вы научитесь делать что-то важное... и забудете об этом",
        "Удача будет с вами, если вы не потеряете ключи от квартиры",
        "Скоро вы станете гуру по уборке... хотя бы в те моменты, когда нужно найти ключи",
        "У Вас появится суперспособность — находить время для кофе в самый неподходящий момент",
        "Вы откроете новый талант — есть пельмени в три приема",
        "На этой неделе вы получите неожиданный подарок — это будут носки, потерянные несколько месяцев назад",
        "Через неделю вы будете чемпионом в поедании пиццы и забывании о диете",
        "В ближайшее время вы совершите великий подвиг — выйдете из дома без опоздания",
        "Вам предстоит важное открытие — оказывается, пульт от телевизора всегда возле подушки",
        "В этом месяце вы найдёте гармонию... в своем холодильнике",
        "Завтра вы встретите своего нового соперника — это будет кот, который решит, что ваша кровать теперь его",
        "Ваши будни будут полны магии — у вас исчезнут все носки, но появится двойная порция чая",
        "Вы откроете новый способ релаксации: это будет лежать на диване и думать, что скоро начнете работать",

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        imageView = findViewById(R.id.imageView)

        // Устанавливаем магический шрифт
        val customFont = ResourcesCompat.getFont(this, R.font.greatvibes)
        textView.typeface = customFont

        // Анимация текста при запуске
        val initialText = "Подумай о чём нибудь \n и нажми, чтобы узнать будущее"
        displayTextWithTypingEffect(initialText)

        // Воспроизведение фоновой музыки
        playBackgroundMusic()

        // Устанавливаем обработчик клика на картинку
        imageView.setOnClickListener {
            textView.text = "" // Очищаем текст
            playMagicSound()
            val prediction = getRandomPrediction()
            displayPredictionWithTypingEffect(prediction)
            addShareButton(prediction)
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

    private fun getRandomPrediction(): String {
        return predictions.random()
    }

    private fun playMagicSound() {
        val soundPlayer = MediaPlayer.create(this, R.raw.magic_sound)
        soundPlayer.setOnCompletionListener { it.release() }
        soundPlayer.start()
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

                    // Переход на новую строку после каждых 3 слов
                    if (builder.split(" ").size % 3 == 0 && charArray[index] == ' ') {
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
        stopBackgroundMusic()
    }
}
