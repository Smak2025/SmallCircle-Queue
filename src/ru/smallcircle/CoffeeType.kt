package ru.smallcircle

enum class CoffeeType(
    val prepareTime: Long,
) {
    CAPPUCCINO(10),
    LATTE(15),
    AMERICANO(5),
    ESPRESSO(1)
}