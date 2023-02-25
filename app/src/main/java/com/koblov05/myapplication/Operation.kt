package com.koblov05.myapplication

class Operation(
    leftValue: Operation? = null,
    rightValue: Operation? = null,
    constantValue: Float? = null,
    action: OperationType
) {
    var leftValue: Operation? = leftValue
    var rightValue: Operation? = rightValue
    var action: OperationType = action
    var constantValue: Float? = constantValue

    fun getResult(): Float {
        var result: Float

        println("!!! getResult. Type is ${action}. Left is ${leftValue}. Right is ${rightValue}. Const is ${constantValue}")

        when (action) {
            OperationType.PLUS ->
                result = (leftValue?.getResult() ?: 0F) + (rightValue?.getResult() ?: 0F)

            OperationType.MINUS ->
                result = (leftValue?.getResult() ?: 0F) - (rightValue?.getResult() ?: 0F)

            OperationType.DIVISION ->
                result = (leftValue?.getResult() ?: 0F) / (rightValue?.getResult() ?: 1F)

            OperationType.MULTIPLICATION ->
                result = (leftValue?.getResult() ?: 0F) * (rightValue?.getResult() ?: 0F)

            OperationType.CONSTANT ->
                result = constantValue ?: 0F
        }
        return result
    }
}