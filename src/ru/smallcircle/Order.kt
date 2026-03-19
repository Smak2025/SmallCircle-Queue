package ru.smallcircle

import kotlin.random.Random

data class Order(
    val clientName: String,
    val drink: CoffeeType,
    var priority: Int = 0,
    var code: String? = null,
){
    fun createOrderCode(){
        code = buildString {
            repeat(6) {
                append(('A'..'Z').random())
            }
        }
    }
}