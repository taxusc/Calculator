package com.example.co.jp.calculator

import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.String.valueOf

class CalcUtil(private var _tv : TextView, private var _tvF : TextView) {

    // 桁数取得用のサイズテーブル
    private val sizeTable : LongArray = longArrayOf(
            9, 99, 999, 9999, 99999,
            999999, 9999999, 99999999,
            999999999, 9999999999,
            99999999999, 999999999999)

    // 内部の計算に使用する値を保持する.
    private var _numberBefore : Int = 0;
    private var _numberAfter : Int = 0;

    // 四則演算子を保持する.
    private var _operator : String = "none"

    /**
     * 外部での計算処理(四則演算).
     *
     * @param btn "="Button
     */
    fun calcOut(btn : Button) : Unit{

        var result : Int = 0;
        when(this._operator) {
            "+" -> result = this._numberBefore + this._numberAfter
            "-" -> result = this._numberBefore - this._numberAfter
            "*" -> result = this._numberBefore * this._numberAfter
            "/" -> result = this._numberBefore / this._numberAfter
        }

        // 表示の更新
        this.updateTextView(result)

        // 内部パラメータの初期化
        this._numberBefore = this.rmvSepalator(_tv.text.toString()).toInt()
        this._numberAfter = 0
        this._operator = "none"
        updateTxtFormula()
    }

    /**
     * 内部での計算処理.
     *
     * @param btn 数値Button
     */
    fun calcIn(btn : Button) : Unit{

        // 各Viewから数値を取得する.
        var existVal : Int = this.rmvSepalator(_tv.text.toString())
        var newVal : Int = Integer.parseInt(btn.text.toString())

        if (this._operator.equals("none")) {
            // 演算フラグ無し.
            this._numberBefore = ((existVal * 10) + newVal)
            this.updateTextView(this._numberBefore)
        } else{
            // 演算フラグON
            this._numberAfter = ((existVal * 10) + newVal)
            this.updateTextView(this._numberAfter)
        }
        updateTxtFormula()
    }

    /**
     * 数値TreeViewをクリア
     */
    fun clearTextView() : Unit {
        this._tv.text = "0"
        this._numberBefore = 0
        this._numberAfter = 0
        this._operator = "none"
        updateTxtFormula()
    }

    /**
     * 四則演算フラグをセット.
     *
     * @param btn 四則演算Button
     */
    fun setOperator(btn : Button) : Unit {
        var operator = btn.text.toString()
        this._tv.text = "0"
        this._operator = operator
        this.updateTxtFormula()
    }

    /**
     * TextViewに表示される数値を更新.
     *
     * @param value 新たに表示する数値(3桁区切り).
     */
    private fun updateTextView(value : Int) : Unit {
        //_tv.text = String.format("%,3d", valueOf(value))
        _tv.text = valueOf(value)
    }

    /**
     * textViewに表示された数値(金額)を取得
     *
     * @param value textViewの表示文字列
     *
     * @return カンマを除いたtextViewの数値
     */
    private fun rmvSepalator(value : String) : Int{
        var txt = value.replace(",", "");
        return Integer.parseInt(txt)
    }

    /**
     * 計算式表示textViewの更新
     */
    private fun updateTxtFormula() : Unit{
        var operator : String = ""
        when(this._operator){
            "+" -> operator = " + "
            "-" -> operator = " - "
            "*" -> operator = " × "
            "/" -> operator = " ÷ "
        }
        var numBf : String = valueOf(this._numberBefore)
        var numAf = ""
        if (this._numberAfter != 0) {numAf = valueOf(this._numberAfter)}
        _tvF.text = numBf + operator + numAf
    }

    /**
     * 桁数の取得処理.
     *
     * @param value 桁数を取得したい数値
     * @return 桁数
     */
    private fun getDigit(value : Int) : Int {
        var digit : Int = 1

        for (item in this.sizeTable) {
            if (value <= item) { break }
            digit++
        }
        return digit
    }
}