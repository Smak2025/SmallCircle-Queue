package ru.smallcircle

import java.util.ArrayDeque
import java.util.Queue
import kotlin.random.Random

class CoffeeQueue {
    private val queue: Queue<Order> = ArrayDeque()
    private val maxCapacity = 5

    fun addOrder(drink: CoffeeType, name: String) {
        if (queue.size >= maxCapacity) {
            throw Exception("$name, простите, слишком много заказов. Попробуйте позже.")
        }
        queue.offer(Order(name, drink))
    }

    override fun toString(): String = buildString {
        if (queue.isEmpty()) {
            append("Очередь пуста")
        } else {
            append("=======================\n")
            queue.forEach {
                append(it)
                append("\n")
            }
            append("=======================\n")
        }
    }

    fun serveOrder(): Order? {
        val order: Order? = queue.poll()
        return order?.also{
            Thread.sleep(order.drink.prepareTime * 1_000)
            order.createOrderCode()
        }
    }
}