package ru.smallcircle

fun main() {
    val coffeeBot = CoffeeQueue()
    listOf(
        "STATUS",
        "ORDER Сергей1 CAPPUCCINO",
        "PAUSE 2",
        "ORDER Евгения2 ESPRESSO",
        "PAUSE 4",
        "ORDER Мария3 CAPPUCCINO VIP_BRONSE",
        "PAUSE 5",
        "ORDER Станислав4 ESPRESSO",
        "PAUSE 10",
        "ORDER Иван5 LATTE",
        "ORDER Анна6 LATTE VIP_SILVER",
        "ORDER Руслан7 LATTE VIP_BRONSE",
        "ORDER Маргарита8 LATTE VIP_GOLD",
        "ORDER Данил9 LATTE",
        "STATUS",
        "ORDER Игорь10 LATTE",
        "STATUS",
    ).forEach {
        it.split(" ").let{ cmd ->
            when(cmd[0]){
                "STATUS" -> println(coffeeBot.toString())
                "PAUSE" -> Thread.sleep(cmd[1].toLong() * 1_000L)
                "ORDER" -> {
                    try{
                        val vip = if (cmd.size > 3) when(cmd[3].split("_")[1]){
                            "SILVER" -> 2
                            "GOLD" -> 3
                            else -> 1
                        } else 0
                        coffeeBot.addOrder(CoffeeType.valueOf(cmd[2]),cmd[1], vip)
                        println("${cmd[1]}, Ваш заказ в очереди, ожидайте...")
                    } catch (e: Exception) {
                        println(e.message)
                    }
                }
            }
        }
    }
}