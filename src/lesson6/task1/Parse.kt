@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1

import lesson2.task2.daysInMonth
import java.lang.IllegalArgumentException

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main() {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}


/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */
val month = listOf(
    "января", "февраля", "марта", "апреля",
    "мая", "июня", "июля", "августа",
    "сентября", "октября", "ноября", "декабря"
)

fun dateStrToDigit(str: String): String {
    val data = str.split(" ")
    if (data.size != 3) return ""
    val mon = month.indexOf(data[1])
    if (mon == -1) return ""
    if (data[0].toInt() > daysInMonth(mon + 1, data[2].toInt())) return ""
    return "%02d.%02d.%d".format(data[0].toInt(), mon + 1, data[2].toInt())
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30 февраля 2009) считается неверными
 * входными данными.
 */
fun dateDigitToStr(digital: String): String {
    val data = digital.split(".")
    if (data.size != 3) return ""
    if (data[1].toIntOrNull() == null || data[1].toInt() < 1) return ""
    if (data[1].toInt() !in 1..12) return ""
    if (data[0].toInt() > daysInMonth(data[1].toInt(), data[2].toInt())) return ""
    return "${data[0].toInt()} ${month[data[1].toInt() - 1]} ${data[2]}"
}

/**
 * Средняя
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -89 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку.
 *
 * PS: Дополнительные примеры работы функции можно посмотреть в соответствующих тестах.
 */
fun flattenPhoneNumber(phone: String): String =
    if (!Regex("""^(\+)?\d+(\(\d+\))?(\d+)?""").matches(Regex("""[\s-]""").replace(phone, ""))) ""
    else Regex("""[-()\s]""").replace(phone, "")

/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int {
    var max1 = -1
    if (Regex("""[^\d-%\s]""").find(jumps)?.value != null) return -1
    val max = Regex("""\d+""").findAll(jumps)
    max.forEach { max1 = maxOf(it.value.toInt(), max1) }
    return max1
}

/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки, а также в случае отсутствия удачных попыток,
 * вернуть -1.
 */
fun bestHighJump(jumps: String): Int {
    if (Regex("""[^\d-+%\s]""").find(jumps)?.value != null) return -1
    val list = jumps.split(" ")
    var max = -1
    if (list.size % 2 == 1) return -1
    for (i in 0 until list.size step 2) {
        if (list[i+1] == "+" && list[i].toInt() > max)
            max = list[i].toInt()
    }
    return max
}

/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int {
    val expList = expression.split(" ")
    require(expList[0] != "")
    var ans = 0
    var plusOrMinus = 1
    expList.mapIndexed { index, s ->
        if (index % 2 == 0) {
            require(s.all { it in '0'..'9' })
            ans += plusOrMinus * s.toInt()
        } else {
            plusOrMinus = when (s) {
                "+" -> 1
                "-" -> -1
                else -> throw IllegalArgumentException()
            }
        }
    }
    return ans
}

/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int {
    var countIndex = 0
    val list = str.toLowerCase().split(" ")
    for (i in 0 until list.size - 1) {
        if (list[i] == list[i + 1]) return countIndex
        countIndex += list[i].length + 1
    }
    return -1
}

/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть больше либо равны нуля.
 */
fun mostExpensive(description: String): String {
    val list = description.split("; ")
    var maxName = ""
    var maxValue = -1.0
    list.map {
        val map = it.split(" ")
        if (map.size != 2) return ""
        val num = map[1].toDoubleOrNull()
        if (num == null || num < 0.0) return ""
        if (num > maxValue) {
            maxName = map[0]
            maxValue = num
        }
    }
    return maxName
}

/**
 * Сложная
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int {
    if (roman.isEmpty()) return -1
    if (!roman.matches(Regex("""M*(CM)?(D|CD)?C{0,3}(XC)?(L|XL)?X{0,3}(IX)?(V|IV)?I{0,3}"""))) {
        return -1
    }
    val romans = listOf("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I")
    val number = listOf(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1)
    var index = 0
    var ans = 0
    while (index <= roman.length - 1) {
        val doubleR = if (index < roman.length - 1) {
            romans.indexOf(roman.slice(index..index + 1))
        } else -1
        if (doubleR == -1) {
            ans += number[romans.indexOf(roman[index].toString())]
            index++
        } else {
            ans += number[doubleR]
            index += 2
        }
    }
    return ans
}

/**
 * Очень сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */

fun find(commands: String, perPos: Int): Int {
    var countOfBrackets = 0
    for (i in perPos until commands.length) {
        when (commands[i]) {
            '[' -> countOfBrackets++
            ']' -> countOfBrackets--
        }
        if (countOfBrackets == 0) return i
    }
    return -1
}

fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> {
    val setOfTrue = setOf('>', '<', '+', '-', '[', ']', ' ')
    require(setOfTrue + commands.toSet() == setOfTrue)
    var k = 0
    for (i in commands) {
        when (i) {
            '[' -> k++
            ']' -> k--
        }
        require(k >= 0)
    }
    require(k == 0)
    val listAnswer = MutableList(cells) { 0 }
    var nowPos = cells / 2
    var perPos = 0
    var count = 0
    val order = mutableListOf<Int>()
    while ((count < limit) && (perPos < commands.length)) {
        check(nowPos in 0 until cells)
        when (commands[perPos]) {
            '>' -> nowPos++
            '<' -> nowPos--
            '+' -> listAnswer[nowPos]++
            '-' -> listAnswer[nowPos]--
            '[' -> if (listAnswer[nowPos] == 0) perPos = find(commands, perPos) else order.add(perPos)
            ']' -> if (listAnswer[nowPos] != 0) perPos = order.last()
            else order.remove(order.last())
        }
        count++
        perPos++
    }
    check(nowPos in 0 until cells)
    return listAnswer
}
