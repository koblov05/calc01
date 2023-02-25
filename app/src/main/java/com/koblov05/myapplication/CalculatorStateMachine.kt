package com.koblov05.myapplication

class CalculatorStateMachine {
    var formula = ""

    fun addSymbolToTheFormulaEnd(symbol: String) {
        formula += symbol
    }

    fun formulaClear(){
        formula = ""
    }

    fun formulaDropLastSymbol(){
        if (formula.isNotEmpty()) {
            formula = formula.substring(0, formula.length - 1)
        }
    }

    fun testEq() {
        val testStr="a+b*c-d/c+a*c*z"
        val parser=Parser()
        var res = parser.splitInToParts(testStr)
        var lastOperation = parser.findLastOperation(res)
        println(res)
        println(lastOperation)
    }
}