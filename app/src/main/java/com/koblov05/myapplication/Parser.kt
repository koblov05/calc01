package com.koblov05.myapplication

class Parser {

    fun splitInToParts (str: String): MutableList<String> {
        var result: MutableList<String> = arrayListOf()

        var number: String = ""

        for (i in 0..str.length - 1) {
            if (str[i] == '(' || str[i] == ')') {
                if (number.isNotEmpty()) {
                    result.add(number)
                }
                result.add(str[i].toString())
                number=""
            } else if (str[i] == '+' || str[i] == '-' || str[i] == '/' || str[i] == '*') {
                if (number.isNotEmpty()) {
                    result.add(number)
                }
                result.add(str[i].toString())
                number=""
            } else {
                number = number + str[i]
            }

            if (i==str.length - 1) {
                if (number.isNotEmpty()) {
                    result.add(number)
                }
            }
        }


        return result
    }

    fun findLastOperation(list: MutableList<String>): Int? {
        var result: Int?= null

        var noBracesList = dropBraces(list)

        var operationOrder: MutableList<Int> = arrayListOf()
        var bracesCounter = 0

        for (i in 0..noBracesList.size - 1) {
            if (noBracesList[i] == "(") {
                bracesCounter += 1
            } else if (noBracesList[i] == ")") {
                bracesCounter -= 1
            }

            if ((noBracesList[i] == "/" || noBracesList[i] =="*") && bracesCounter == 0) {
              operationOrder.add(i)
            }
        }

        for (i in 0..noBracesList.size - 1) {
            if (noBracesList[i] == "(") {
                bracesCounter += 1
            } else if (noBracesList[i] == ")") {
                bracesCounter -= 1
            }

            if ((noBracesList[i] == "+" || noBracesList[i] =="-")  && bracesCounter == 0) {
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

        result = dropBraces(result)

        return result
    }

    fun getRightPart(list: MutableList<String>, borderIndex: Int): MutableList<String> {
        var result: MutableList<String> = arrayListOf()

        for (i in borderIndex + 1..list.size - 1) {
            result.add(list[i])
        }

        result = dropBraces(result)

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

    fun dropBraces(list: MutableList<String>): MutableList<String> {
        var bracesCounter: MutableList<Int> = arrayListOf()

        for (i in 0..list.size - 1) {
            if (list[i] == "(") {
                bracesCounter.add(i)
            }

            if (i == list.size - 1 && list.last() == ")" && bracesCounter.first() == 0 && bracesCounter.size == 1) {
                list.removeAt(0)
                list.removeAt(list.size - 1)
                break
            }

            if (list[i] == ")") {
                bracesCounter.removeLast()
            }
        }

        return list
    }
}