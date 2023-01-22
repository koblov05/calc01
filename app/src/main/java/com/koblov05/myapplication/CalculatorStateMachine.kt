package com.koblov05.myapplication

class CalculatorStateMachine {
    var formula = ""

    fun addSymbolToTheFormulaEnd(symbol: String) {
        formula += symbol
    }
}