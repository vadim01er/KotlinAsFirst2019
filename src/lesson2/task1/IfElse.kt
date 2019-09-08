@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import kotlin.math.max
import kotlin.math.sqrt
import kotlin.math.abs
import lesson1.task1.sqr

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    var god : String = ""
    if (age % 100 in 5..20) {
        god = "лет"
    }
    else if (age % 10 == 1) {
        god = "год"
    }
    else if (age % 10 in 2..4) {
        god = "года"
    }
    else if ((age % 10 in 5..9) || (age % 10 == 0)){
        god = "лет"
    }
    return "$age $god"
}

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double
): Double {
    var s05 = (t1 * v1 + t2 * v2 + t3 * v3) / 2
    var t0: Double = 0.0
    var s051: Double = 0.0
    var s052: Double = 0.0

    if (s05 / v1 == t1) t0 = t1
    else if (s05 / v1 < t1) t0 = s05 / v1
    else {
        s051 = s05 - v1 * t1
        if (s051 / v2 == t2) t0 = t1 + t2
        else if (s051 / v2 < t2) t0 = t1 + s051 / v2
        else {
            s052 = s051 - v2 * t2
            if (s052 / v3 == t3) t0 = t1 + t2 + t3
            else if (s052 / v3 < t3) t0 = t1 + t2 + s052 / v3
        }
    }
    return t0
}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(
    kingX: Int, kingY: Int,
    rookX1: Int, rookY1: Int,
    rookX2: Int, rookY2: Int
): Int {
    var out = 0
    if (((rookX2 == kingX) || (rookY2 == kingY)) && ((rookX1 == kingX) || (rookY1 == kingY))) out = 3
    else if ((kingX == rookX1) || (kingY == rookY1)) out = 1
    else if ((rookX2 == kingX) || (rookY2 == kingY)) out = 2
    else out = 0
    return out
}

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int
): Int {
    var out = 0
    if ((abs(kingX - bishopX) == abs(kingY - bishopY)) && ((rookX == kingX) || (rookY == kingY))) out = 3
    else if ((rookX == kingX) || (rookY == kingY)) out = 1
    else if ((abs(kingX - bishopX) == abs(kingY - bishopY))) out = 2
    return out
}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    var maxim: Double = 0.0
    if (a > b)
        if (a > c) maxim = a else maxim = c
    else if (b > c) maxim = b else maxim = c

    when (maxOf(a, b, c)) {
        a -> if (a >= b + c) return -1
        b -> if (b >= a + c) return -1
        else -> if (c >= b + a) return -1
    }

    when (maxOf(a, b, c)) {
        a -> if (sqr(a) > sqr(b) + sqr(c)) return 2
        else if (sqr(a) == sqr(b) + sqr(c)) return 1
        else return 0
        b -> if (sqr(b) > sqr(a) + sqr(c)) return 2
        else if (sqr(b) == sqr(a) + sqr(c)) return 1
        else return 0
        else -> if (sqr(c) > sqr(b) + sqr(a)) return 2
        else if (sqr(c) == sqr(b) + sqr(a)) return 1
        else return 0
    }

}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    var d1 = 0
    if ((b > c) && (b < d) && (a > c) && (a < d)) d1 = b - a
    else if ((c > a) && (c < b) && (d > a) && (d < b)) d1 = d - c
    else if ((a > c) && (a < d)) {
        d1 = d - a
    }
    else if ((b > c) && (b < d)) {
        d1 = b - c
    }
    else if ((a == c)) {
        if (b > d) d1 = d - a
        else d1 = b - a
    }
    else if (d == b) {
        if (a > c) d1 = d - a
        else d1 = d - c
    }
    else if ((b == c) || (a == d)) d1 = 0
    else if ((a == c) && (b == d)) d1 = b - a
    else d1 = -1
    return d1
}
