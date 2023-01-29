package com.koblov05.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    val state = CalculatorStateMachine()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()
    }

    private val listener_dig_op = View.OnClickListener { view ->

        state.addSymbolToTheFormulaEnd("${view.tag}")
        println(state.formula)

        val textView = findViewById(R.id.formula) as TextView
        textView.text = state.formula
    }

    private val listener_ac = View.OnClickListener { view ->

        state.formulaClear()
        println(state.formula)

        val textView = findViewById(R.id.formula) as TextView
        textView.text = state.formula
    }

    private val listener_del = View.OnClickListener { view ->

        state.formulaDropLastSymbol()
        println(state.formula)

        val textView = findViewById(R.id.formula) as TextView
        textView.text = state.formula
    }

    private val listener_equal = View.OnClickListener { view ->

        println(state.formula)

        val textView = findViewById(R.id.formula) as TextView
        textView.text = state.formula
    }

    private fun setListeners() {
        bindListenerDigOp(R.id.button_left_brace)
        bindListenerDigOp(R.id.button_right_brace)
        bindListenerDigOp(R.id.button_plus)
        bindListenerDigOp(R.id.button_7)
        bindListenerDigOp(R.id.button_8)
        bindListenerDigOp(R.id.button_9)
        bindListenerDigOp(R.id.button_minus)
        bindListenerDigOp(R.id.button_4)
        bindListenerDigOp(R.id.button_5)
        bindListenerDigOp(R.id.button_6)
        bindListenerDigOp(R.id.button_div)
        bindListenerDigOp(R.id.button_1)
        bindListenerDigOp(R.id.button_2)
        bindListenerDigOp(R.id.button_3)
        bindListenerDigOp(R.id.button_multiplication)
        bindListenerDigOp(R.id.button_0)
        bindListenerDigOp(R.id.button_dot)
        bindListenerEqual(R.id.button_equal)
        bindListenerAc(R.id.button_ac)
        bindListenerDel(R.id.button_demolition)
    }

    private fun bindListenerDigOp(id: Int) {
        val button: Button = findViewById(id) as Button
        button.setOnClickListener(listener_dig_op)
    }

    private fun bindListenerAc(id: Int) {
        val button: Button = findViewById(id) as Button
        button.setOnClickListener(listener_ac)
    }

    private fun bindListenerDel(id: Int) {
        val button: Button = findViewById(id) as Button
        button.setOnClickListener(listener_del)
    }

    private fun bindListenerEqual(id: Int) {
        val button: Button = findViewById(id) as Button
        button.setOnClickListener(listener_equal)
    }
}