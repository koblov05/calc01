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
//        val testStr="27.3/3+9-2*2"
        val parser = Parser()
        var list = parser.splitInToParts(formula)

        var root = parser.createOperationTree(list)
        println(root.getResult())
    }
}