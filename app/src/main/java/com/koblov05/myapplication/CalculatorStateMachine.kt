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
}