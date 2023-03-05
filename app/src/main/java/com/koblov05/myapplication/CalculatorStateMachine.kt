package com.koblov05.myapplication

class CalculatorStateMachine {
    var formula = ""
    var history: MutableList<String> = arrayListOf()

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
        val parser = Parser()
        var list = parser.splitInToParts(formula)
        var result = ""
        var root = parser.createOperationTree(list)
        result = formula + "="
        result += root.getResult().toString()
        addToHistory(result)
        formulaClear()

        println(root.getResult())
    }

    fun addToHistory(item: String) {
        history.add(0, item)
        if (history.size == 5){
            history.removeLast()
        }
    }

    fun getHistory(): String {
        var result = ""

        for (i in history.size - 1 downTo 0 ) {
            result += history[i]
            result += "\n"
        }

        result += "\n"

        if (formula == "") {
            result += "Введите выражение"
        } else {
            result += formula
        }

        return result
    }
}