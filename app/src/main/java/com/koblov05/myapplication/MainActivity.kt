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

    private val listener = View.OnClickListener { view ->
        //Log.d("MainActivity::listener", "$view")

        state.addSymbolToTheFormulaEnd("${view.tag}")
        println(state.formula)

        val textView = findViewById(R.id.formula) as TextView
        textView.text = state.formula

//        when (view.id) {
//        }
    }

    private fun setListeners() {
        bindListener(R.id.button_ac)
        bindListener(R.id.button_left_brace)
        bindListener(R.id.button_right_brace)
        bindListener(R.id.button_plus)
        bindListener(R.id.button_7)
        bindListener(R.id.button_8)
        bindListener(R.id.button_9)
        bindListener(R.id.button_minus)
        bindListener(R.id.button_4)
        bindListener(R.id.button_5)
        bindListener(R.id.button_6)
        bindListener(R.id.button_div)
        bindListener(R.id.button_1)
        bindListener(R.id.button_2)
        bindListener(R.id.button_3)
        bindListener(R.id.button_multiplication)
        bindListener(R.id.button_0)
        bindListener(R.id.button_dot)
        bindListener(R.id.button_equal)

    }

    private fun bindListener(id: Int) {
        val button: Button = findViewById(id) as Button
        button.setOnClickListener(listener)
    }
}