package com.koblov05.myapplication

class Parser {

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

        if (result[0] == ""){
            result.removeAt(0)
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

    fun getLeftPart(list: MutableList<String>, borderIndex: Int): MutableList<String> {
        var result: MutableList<String> = arrayListOf()

        for (i in 0..borderIndex - 1) {
            result.add(list[i])
        }

        return result
    }

    fun getRightPart(list: MutableList<String>, borderIndex: Int): MutableList<String> {
        var result: MutableList<String> = arrayListOf()

        for (i in borderIndex + 1..list.size - 1) {
            result.add(list[i])
        }

        return result
    }

    fun getOperationType(operation: String): OperationType {
        var result : OperationType = OperationType.CONSTANT

        if (operation == "+") {
            result = OperationType.PLUS
        }

        if (operation == "-") {
            result = OperationType.MINUS
        }

        if (operation == "*") {
            result = OperationType.MULTIPLICATION
        }

        if (operation == "/") {
            result = OperationType.DIVISION
        }

        return result
    }

    fun createOperationTree(list: MutableList<String>): Operation {
        if (list.size == 1){
            val value = list.first().toFloat()
            return Operation(leftValue = null, rightValue = null, constantValue = value, action = OperationType.CONSTANT)
        }

        if (list.size == 2 && list[0]=="-"){
            val str = list[0] + list[1]
            val value = str.toFloat()
            return Operation(leftValue = null, rightValue = null, constantValue = value, action = OperationType.CONSTANT)
        }



        val parser = Parser()
        val lastOperation = parser.findLastOperation(list)
        val operationType = parser.getOperationType(list[lastOperation!!])

        val leftList = parser.getLeftPart(list, lastOperation)
        val leftTreeNode = createOperationTree(leftList)

        val rightList = parser.getRightPart(list, lastOperation)
        val rightTreeNode = createOperationTree(rightList)

        return Operation(leftValue = leftTreeNode, rightValue = rightTreeNode, constantValue = null, action = operationType)
    }
}