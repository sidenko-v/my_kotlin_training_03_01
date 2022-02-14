import kotlin.math.min

//Используя when, напишите функцию agoToText (скорее всего, вам понадобится не одна функция), которая переводит количество секунд, которые человек был назад в сети в соответствующий текст (String).
//Нас будет интересовать вся фраза, например, был(а) только что или был(а) в сети 3 часа назад.
//Какие правила:
//Если количество секунд от 0 до 60, работает вариант с только что.
//Если количество секунд от 61 до 60 * 60 (один час), работает вариант с x минут назад.
// 1 минута 2ы 3ы 4ы 5т 6т 7т 8т 9т 10т 11т 12т 13т14т15т16т17т18т19т20т21та 22ы
//Если количество секунд от 60 * 60 + 1 до 24 * 60 * 60 (сутки) и , работает вариант с x часов назад.
// 1 час 2 3 4 часа 5 6 7 8 9 10 часов 11 12 13 14 15 16 17 18 19 20 час 21 час 22 23 24 часа
//Если количество секунд от суток до двух, то - сегодня.
//Если количество секунд от двух суток до трёх, то - вчера.
//Если количество секунд больше трёх суток, то - давно.
//Обратите внимание! Вам понадобятся вспомогательные функции, которые умеют делать:
//
//1 минуту назад.
//2 минуты назад.
//5 минут назад.
//11 минут назад.
//21 минуту назад.
//25 минут назад.
//То же самое с часами, потому что с часами будет час, часа, часов.
//
//Итог: у вас должен быть репозиторий на GitHub, в котором будет ваш Gradle-проект.


fun main() {

    val seconds: Int = 1234
    val text = agoToText(seconds)
    println(text)
}


fun timeToText(seconds: Int): String {

    val textTime: String = when (seconds) {
        in 0..60 -> "только что"
        in 61..60 * 60 -> "${countingMinutes(seconds)} ${minToText(seconds)} назад"
        in 60 * 60 + 1..24 * 60 * 60 -> "${countingHours(seconds)} ${hourToText(seconds)} назад"
        in 24 * 60 * 60 + 1..24 * 60 * 60 * 2 -> "сегодня"
        in 24 * 60 * 60 * 2 + 1..24 * 60 * 60 * 3 -> "вчера"
        else -> "давно"
    }
    return textTime
}

fun countingMinutes(seconds: Int): Int {
    val minutes: Int = seconds / 60
    return minutes
}

fun countingHours(seconds: Int): Int {
    val hours: Int = seconds / (60 * 60)
    return hours
}

fun minToText(seconds: Int): String {

    val minutes = countingMinutes(seconds)

    val text: String = when {
        (minutes % 10 == 1 && minutes % 100 != 11) -> "минута"
        ((minutes % 10 == 2 || minutes % 10 == 3 || minutes % 10 == 4) && minutes % 100 != 12 && minutes % 100 != 13 && minutes % 100 != 14) -> "минуты"
        else -> "минут"
    }
    return text
}

fun hourToText(seconds: Int): String {

    val hours = countingHours(seconds)

    val text: String = when {
        (hours % 10 == 1 && hours % 100 != 11) -> "час"
        ((hours % 10 == 2 || hours % 10 == 3 || hours % 10 == 4) && hours % 100 != 12 && hours % 100 != 13 && hours % 100 != 14) -> "часа"
        else -> "часов"
    }

    return text
}

fun agoToText(seconds: Int): String {

    return "был(а) в сети " + timeToText(seconds)

}
