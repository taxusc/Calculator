package com.example.co.jp.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 数値ボタンの配列
        val btnNumArr : Array<Button> = arrayOf(
                btnOne, btnTwo, btnThree, btnFour, btnFive,
                btnSix, btnSeven, btnEight, btnNine
        )

        // 演算記号ボタンの配列
        val btnOprArr : Array<Button> = arrayOf(
                btnPlus, btnMinus, btnMultiplication, btnDivision
        )

        var calculator = CalcUtil(txtNum, txtFormula)

        // 数値ボタンのイベントセット
        for (btn in btnNumArr) {
            btn.setOnClickListener{
                calculator.calcIn(btn)
            }
        }
        // 演算記号ボタンのイベントセット
        for (btn in btnOprArr) {
            btn.setOnClickListener{
                calculator.setOperator(btn)
            }
        }
        // "="ボタンのイベントセット
        btnEqual.setOnClickListener{
            calculator.calcOut(btnEqual)
        }
        // クリアボタンのイベントセット
        btnClear.setOnClickListener{
            calculator.clearTextView()
        }
    }
}
