package com.koblov05.myapplication

class Parser {

//    fun getResult(inputLine: String): Operation {
//        try {
//            if (inputLine.contains('+')
//                || inputLine.contains('-')
//                || inputLine.contains('*')
//                || inputLine.contains('/')
//            ) {
//
//            }
//        } catch (e: ParserException) {
//            println(e.message)
//        }
//    }

    fun isConst(str: String): Float? {
        var result: Float? = null

        try {
            result = str.toFloat()
        } catch (e: NumberFormatException) {
            println(e.message)
        }

        return result
    }

    fun splitInToParts (str: String): MutableList<String> {
        var result: MutableList<String> = arrayListOf()

        var number: String = ""

        for (i in 0..str.length - 1) {
            if (str[i] == '+' || str[i] == '-' || str[i] == '/' || str[i] == '*') {
                result.add(number)
                result.add(str[i].toString())
                number=""
            } else {
                number = number + str[i]
            }

            if (i==str.length - 1) {
                result.add(number)
            }
        }

        return result
    }

    fun findLastOperation(list: MutableList<String>): Int? {
        var result: Int?= null

        var operationOrder: MutableList<Int> = arrayListOf()

        for (i in 0..list.size - 1) {
            if (list[i] == "/" || list[i] =="*") {
              operationOrder.add(i)
            }
        }

        for (i in 0..list.size - 1) {
            if (list[i] == "+" || list[i] =="-") {
                operationOrder.add(i)
            }
        }

        if (operationOrder.size > 0) {
            result = operationOrder.last()
        }

        return result
    }
}