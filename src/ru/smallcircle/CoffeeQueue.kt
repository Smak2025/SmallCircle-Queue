package ru.smallcircle

import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.PriorityQueue
import java.util.Queue
import kotlin.concurrent.thread

class CoffeeQueue {
    private val queue: Queue<Order> = PriorityQueue(Comparator{
        o1, o2 -> if (o1.priority != o2.priority) o2.priority - o1.priority else (o1.time - o2.time).toInt()
    })
    private val maxCapacity = 5

    private val isActive: Boolean
        get() = queue.isNotEmpty()

    fun addOrder(drink: CoffeeType, name: String, vip: Int = 0) {
        if (queue.size >= maxCapacity) {
            throw Exception("$name, простите, слишком много заказов. Попробуйте позже.")
        }
        queue.offer(Order(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC), name, drink, vip))
        if (queue.size == 1) start()
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

    private fun start(){
        thread {
            while (isActive) {
                serveOrder()?.also {
                    println("${it.clientName}, Ваш заказ готов. Заберите его по коду: ${it.code}")
                }
            }
        }
    }

    fun serveOrder(): Order? {
        val order: Order? = queue.peek()
        return order?.let{
            order.priority = Int.MAX_VALUE
            Thread.sleep(order.drink.prepareTime * 1_000)
            order.createOrderCode()
            queue.poll()
        }
    }
}