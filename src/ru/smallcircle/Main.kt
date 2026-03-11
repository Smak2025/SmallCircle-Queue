package ru.smallcircle

fun main() {
    val coffeeBot = CoffeeQueue()
    listOf(
        "STATUS",
        "ORDER Сергей CAPPUCCINO",
        "PAUSE 2",
        "ORDER Евгения ESPRESSO",
        "PAUSE 9",
        "ORDER Мария CAPPUCCINO",
        "PAUSE 10",
        "ORDER Станислав ESPRESSO",
        "PAUSE 20",
        "ORDER Иван LATTE",
        "ORDER Анна LATTE",
        "ORDER Руслан LATTE",
        "ORDER Маргарита LATTE",
        "ORDER Данил LATTE",
        "STATUS",
        "ORDER Игорь LATTE",
        "STATUS",
    ).forEach {
        it.split(" ").let{ cmd ->
            when(cmd[0]){
                "STATUS" -> println(coffeeBot.toString())
                "PAUSE" -> Thread.sleep(cmd[1].toLong() * 1_000L)
                "ORDER" -> {
                    try{
                        coffeeBot.addOrder(CoffeeType.valueOf(cmd[2]),cmd[1])
                        println("${cmd[1]}, Ваш заказ в очереди, ожидайте...")
                    } catch (e: Exception) {
                        println(e.message)
                    }
                }
            }
        }
    }
}