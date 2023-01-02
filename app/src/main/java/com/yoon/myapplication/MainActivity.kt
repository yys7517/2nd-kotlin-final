package com.yoon.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.yoon.myapplication.databinding.ActivityMainBinding
import java.math.BigInteger
import java.text.DecimalFormat
import java.util.*
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var currRes = BigInteger.ZERO
    private var hasOperator = false

    private val opStack = Stack<Char>()
    private val sumList = mutableListOf<BigInteger>()

    private var mValue = BigInteger.ZERO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvCalc.text = "0"
        initBtnTouchListener()

    }
    @SuppressLint("ClickableViewAccessibility")
    private fun initBtnTouchListener() {
        with(binding) {
            btn0.setOnTouchListener { _, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        btn0.isPressed = true
                        btn0.textSize = 16f
                        if (binding.tvCalc.text.toString() != "0") {
                            checkNumLengthAndAppend("0")
                        }
                    }
                    MotionEvent.ACTION_UP -> {
                        btn0.isPressed = false
                        btn0.textSize = 24f
                    }
                }
                true
            }

            btn00.setOnTouchListener { _, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        btn00.isPressed = true
                        btn00.textSize = 16f
                        if (binding.tvCalc.text.toString() != "0") {
                            checkNumLengthAndAppend("00")
                        }
                    }
                    MotionEvent.ACTION_UP -> {
                        btn00.isPressed = false
                        btn00.textSize = 24f
                    }
                }
                true
            }

            btn1.setOnTouchListener { _, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        btn1.isPressed = true
                        btn1.textSize = 16f
                        checkNumLengthAndAppend("1")
                    }
                    MotionEvent.ACTION_UP -> {
                        btn1.isPressed = false
                        btn1.textSize = 24f
                    }
                }
                true
            }

            btn2.setOnTouchListener { _, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        btn2.isPressed = true
                        btn2.textSize = 16f
                        checkNumLengthAndAppend("2")
                    }
                    MotionEvent.ACTION_UP -> {
                        btn2.isPressed = false
                        btn2.textSize = 24f
                    }
                }
                true
            }

            btn3.setOnTouchListener { _, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        btn3.isPressed = true
                        btn3.textSize = 16f
                        checkNumLengthAndAppend("3")

                    }
                    MotionEvent.ACTION_UP -> {
                        btn3.isPressed = false
                        btn3.textSize = 24f
                    }
                }
                true
            }

            btn4.setOnTouchListener { _, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        btn4.isPressed = true
                        btn4.textSize = 16f
                        checkNumLengthAndAppend("4")

                    }
                    MotionEvent.ACTION_UP -> {
                        btn4.isPressed = false
                        btn4.textSize = 24f
                    }
                }
                true
            }

            btn5.setOnTouchListener { _, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        btn5.isPressed = true
                        btn5.textSize = 16f
                        checkNumLengthAndAppend("5")

                    }
                    MotionEvent.ACTION_UP -> {
                        btn5.isPressed = false
                        btn5.textSize = 24f
                    }
                }
                true
            }

            btn6.setOnTouchListener { _, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        btn6.isPressed = true
                        btn6.textSize = 16f
                        checkNumLengthAndAppend("6")

                    }
                    MotionEvent.ACTION_UP -> {
                        btn6.isPressed = false
                        btn6.textSize = 24f
                    }
                }
                true
            }

            btn7.setOnTouchListener { _, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        btn7.isPressed = true
                        btn7.textSize = 16f
                        checkNumLengthAndAppend("7")

                    }
                    MotionEvent.ACTION_UP -> {
                        btn7.isPressed = false
                        btn7.textSize = 24f
                    }
                }
                true
            }

            btn8.setOnTouchListener { _, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        btn8.isPressed = true
                        btn8.textSize = 16f
                        checkNumLengthAndAppend("8")

                    }
                    MotionEvent.ACTION_UP -> {
                        btn8.isPressed = false
                        btn8.textSize = 24f
                    }
                }
                true
            }

            btn9.setOnTouchListener { _, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        btn9.isPressed = true
                        btn9.textSize = 16f
                        checkNumLengthAndAppend("9")

                    }
                    MotionEvent.ACTION_UP -> {
                        btn9.isPressed = false
                        btn9.textSize = 24f
                    }
                }
                true
            }

            btnReverse.setOnTouchListener { _, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        btnReverse.isPressed = true
                        btnReverse.textSize = 16f
                        tvCalc.text = (tvCalc.text.toString().toDouble() * -1).toString()
                    }
                    MotionEvent.ACTION_UP -> {
                        btnReverse.isPressed = false
                        btnReverse.textSize = 24f
                    }
                }
                true
            }

            btnAllClear.setOnTouchListener { _, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        btnAllClear.isPressed = true
                        btnAllClear.textSize = 16f

                        binding.tvCalc.text = "0"
                        sumList.clear()
                        opStack.clear()
                        currRes = BigInteger.ZERO
                        setOpVisible(false)
                        tvGt.visibility = View.INVISIBLE
                        tvK.visibility = View.INVISIBLE
                        hasOperator = false
                    }
                    MotionEvent.ACTION_UP -> {
                        btnAllClear.isPressed = false
                        btnAllClear.textSize = 24f
                    }
                }
                true
            }

            btnClear.setOnTouchListener { _, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        btnClear.isPressed = true
                        btnClear.textSize = 16f
                        binding.tvCalc.text = "0"

                        hasOperator = false
                    }
                    MotionEvent.ACTION_UP -> {
                        btnClear.isPressed = false
                        btnClear.textSize = 24f
                    }
                }
                true
            }

            btnHms.setOnTouchListener { _, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        btnHms.isPressed = true
                        btnHms.textSize = 12f
                        shortToast("기능 구현 예정입니다")
                    }
                    MotionEvent.ACTION_UP -> {
                        btnHms.isPressed = false
                        btnHms.textSize = 16f
                    }
                }
                true
            }

            btnMc.setOnTouchListener { _, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        btnMc.isPressed = true
                        btnMc.textSize = 12f

                        mValue = BigInteger.ZERO
                        tvM.visibility = View.INVISIBLE
                    }
                    MotionEvent.ACTION_UP -> {
                        btnMc.isPressed = false
                        btnMc.textSize = 16f
                    }
                }
                true
            }

            btnMr.setOnTouchListener { _, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        btnMr.isPressed = true
                        btnMr.textSize = 12f

                        binding.tvCalc.text = mValue.toString()
                        hasOperator = true

                    }
                    MotionEvent.ACTION_UP -> {
                        btnMr.isPressed = false
                        btnMr.textSize = 16f
                    }
                }
                true
            }

            btnMemoryMinus.setOnTouchListener { _, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        btnMemoryMinus.isPressed = true
                        btnMemoryMinus.textSize = 12f
                        tvM.visibility = View.VISIBLE

                        mValue = mValue.minus(BigInteger(binding.tvCalc.text.toString()))
                        hasOperator = true

                    }
                    MotionEvent.ACTION_UP -> {
                        btnMemoryMinus.isPressed = false
                        btnMemoryMinus.textSize = 16f
                    }
                }
                true
            }

            btnMemoryPlus.setOnTouchListener { _, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        btnMemoryPlus.isPressed = true
                        btnMemoryPlus.textSize = 12f
                        tvM.visibility = View.VISIBLE

                        mValue = mValue.add(BigInteger(binding.tvCalc.text.toString()))
                        hasOperator = true
                    }
                    MotionEvent.ACTION_UP -> {
                        btnMemoryPlus.isPressed = false
                        btnMemoryPlus.textSize = 16f
                    }
                }
                true
            }

            btnDiv.setOnTouchListener { _, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        btnDiv.isPressed = true
                        btnDiv.textSize = 16f

                        binding.tvDiv.visibility = View.VISIBLE
                        binding.tvPlus.visibility = View.INVISIBLE
                        binding.tvMinus.visibility = View.INVISIBLE
                        binding.tvMul.visibility = View.INVISIBLE

                        hasOperator = true
                        if (!opStack.isEmpty()) {
                            calcWithOp(opStack.pop())
                            binding.tvCalc.text = currRes.toString()
                        }
                        opStack.push('/')
                        currRes = BigInteger(tvCalc.text.toString())
                        Log.e("currRes", currRes.toString())
                    }
                    MotionEvent.ACTION_UP -> {
                        btnDiv.isPressed = false
                        btnDiv.textSize = 24f
                    }
                }
                true
            }

            btnMul.setOnTouchListener { _, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        btnMul.isPressed = true
                        btnMul.textSize = 16f
                        binding.tvDiv.visibility = View.INVISIBLE
                        binding.tvPlus.visibility = View.INVISIBLE
                        binding.tvMinus.visibility = View.INVISIBLE
                        binding.tvMul.visibility = View.VISIBLE

                        hasOperator = true

                        if (!opStack.isEmpty()) {
                            calcWithOp(opStack.pop())
                            binding.tvCalc.text = currRes.toString()
                        }
                        currRes = BigInteger(tvCalc.text.toString())

                        opStack.push('*')
                    }
                    MotionEvent.ACTION_UP -> {
                        btnMul.isPressed = false
                        btnMul.textSize = 24f
                    }
                }
                true
            }

            btnMinus.setOnTouchListener { _, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        btnMinus.isPressed = true
                        btnMinus.textSize = 16f
                        binding.tvDiv.visibility = View.INVISIBLE
                        binding.tvPlus.visibility = View.INVISIBLE
                        binding.tvMinus.visibility = View.VISIBLE
                        binding.tvMul.visibility = View.INVISIBLE
                        hasOperator = true


                        if (!opStack.isEmpty()) {
                            calcWithOp(opStack.pop())
                            binding.tvCalc.text = currRes.toString()
                        }
                        opStack.push('-')
                        currRes = BigInteger(tvCalc.text.toString())

                    }
                    MotionEvent.ACTION_UP -> {
                        btnMinus.isPressed = false
                        btnMinus.textSize = 24f
                    }
                }
                true
            }

            btnPlus.setOnTouchListener { _, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        btnPlus.isPressed = true
                        btnPlus.textSize = 16f
                        binding.tvDiv.visibility = View.INVISIBLE
                        binding.tvMinus.visibility = View.INVISIBLE
                        binding.tvMul.visibility = View.INVISIBLE

                        binding.tvPlus.visibility = View.VISIBLE

                        if (!opStack.isEmpty()) {
                            calcWithOp(opStack.pop())
                            binding.tvCalc.text = currRes.toString()
                        }
                        opStack.push('+')
                        currRes = BigInteger(tvCalc.text.toString())
                        hasOperator = true
                    }
                    MotionEvent.ACTION_UP -> {
                        btnPlus.isPressed = false
                        btnPlus.textSize = 24f
                    }
                }
                true
            }

            btnResult.setOnTouchListener { _, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        btnResult.isPressed = true
                        btnResult.textSize = 16f

                        if (!opStack.isEmpty()) {
                            when (opStack.pop()) {
                                '+' -> {
                                    currRes = currRes.add(BigInteger(tvCalc.text.toString()))
                                    hasOperator = true
                                    setOpVisible(false)
                                    tvCalc.text = currRes.toString()
                                    sumList.add(currRes)
                                }
                                '-' -> {
                                    currRes = currRes.minus(BigInteger(tvCalc.text.toString()))
                                    hasOperator = true
                                    setOpVisible(false)
                                    tvCalc.text = currRes.toString()
                                    sumList.add(currRes)
                                }
                                '*' -> {
                                    currRes = currRes.multiply(BigInteger(tvCalc.text.toString()))
                                    hasOperator = true
                                    setOpVisible(false)
                                    tvCalc.text = currRes.toString()
                                    sumList.add(currRes)
                                }
                                '/' -> {

                                    val value = BigInteger(binding.tvCalc.text.toString())

                                    if (value == BigInteger.ZERO) {
                                        shortToast("0으로 나눌 수 없습니다.")
                                        binding.tvCalc.text = "Error. 0으로 나눌 수 없습니다."
                                        hasOperator = true
                                        setOpVisible(false)

                                    } else {
                                        currRes = currRes.div(BigInteger(tvCalc.text.toString()))
                                        hasOperator = true
                                        setOpVisible(false)
                                        tvCalc.text = currRes.toString()
                                        sumList.add(currRes)
                                    }
                                }
                                else -> {}
                            }

                        }

                        if (sumList.isNotEmpty()) {
                            tvGt.visibility = View.VISIBLE
                        }
                    }
                    MotionEvent.ACTION_UP -> {
                        btnResult.isPressed = false
                        btnResult.textSize = 24f
                    }
                }
                true
            }

            btnPercent.setOnTouchListener { _, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        btnPercent.isPressed = true
                        btnPercent.textSize = 16f
                        shortToast("기능 구현 예정입니다")
                    }
                    MotionEvent.ACTION_UP -> {
                        btnPercent.isPressed = false
                        btnPercent.textSize = 24f
                    }
                }
                true
            }

            btnRoot.setOnTouchListener { _, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        btnRoot.isPressed = true
                        btnRoot.textSize = 16f
                        if (tvCalc.text.toString() != "0") {
                            tvCalc.text = sqrt(tvCalc.text.toString().toDouble()).toString()
                            hasOperator = true

                        }
                    }
                    MotionEvent.ACTION_UP -> {
                        btnRoot.isPressed = false
                        btnRoot.textSize = 24f
                    }
                }
                true
            }

            btnDelete.setOnTouchListener { _, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        btnDelete.isPressed = true
                        btnDelete.textSize = 16f
                        val text = binding.tvCalc.text
                        if (text.length == 1) {
                            binding.tvCalc.text = "0"
                        } else {
                            binding.tvCalc.text = text.substring(0, text.length - 1)
                        }
                    }
                    MotionEvent.ACTION_UP -> {
                        btnDelete.isPressed = false
                        btnDelete.textSize = 24f
                    }
                }
                true
            }

            btnGt.setOnTouchListener { _, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        btnGt.isPressed = true
                        btnGt.textSize = 12f
                        hasOperator = true


                        var sum = BigInteger.ZERO
                        for (value in sumList) {
                            sum = sum.add(value)
                        }
                        tvCalc.text = sum.toString()
                    }
                    MotionEvent.ACTION_UP -> {
                        btnGt.isPressed = false
                        btnGt.textSize = 16f
                    }
                }
                true
            }
        }
    }


    private fun setOpVisible(visible: Boolean) {
        binding.tvDiv.isVisible = visible
        binding.tvPlus.isVisible = visible
        binding.tvMinus.isVisible = visible
        binding.tvMul.isVisible = visible
    }

    private fun checkNumLengthAndAppend(input: String) {
        with(binding) {
            if (tvCalc.text.toString().trim() == "0") {
                tvCalc.text = input
                hasOperator = false
            } else if (tvCalc.text.length < 14) {
                if (hasOperator) {
                    tvCalc.text = ""
                    tvCalc.append(input)
                    hasOperator = false
                } else {
                    tvCalc.append(input)
                }
            } else if (tvCalc.text.length == 14) {
                binding.tvCalc.text = "14자리까지 입력할 수 있습니다."
                hasOperator = true
            }
        }

    }

    private fun calcWithOp(op: Char) {
        when (op) {
            '+' -> {
                val value = BigInteger(binding.tvCalc.text.toString().trim())
                Log.e("value", value.toString())
                currRes = currRes.add(value)
                hasOperator = true
                setOpVisible(false)
                binding.tvCalc.text = currRes.toString()
                sumList.add(currRes)
            }
            '-' -> {
                val value = BigInteger(binding.tvCalc.text.toString().trim())
                currRes = currRes.minus(value)
                hasOperator = true
                setOpVisible(false)
                binding.tvCalc.text = currRes.toString()
                sumList.add(currRes)
            }
            '*' -> {
                val value = BigInteger(binding.tvCalc.text.toString().trim())
                currRes = currRes.multiply(value)
                hasOperator = true
                setOpVisible(false)
                binding.tvCalc.text = currRes.toString()
                sumList.add(currRes)
            }
            '/' -> {
                val value = BigInteger(binding.tvCalc.text.toString())

                if (value == BigInteger.ZERO) {
                    shortToast("0으로 나눌 수 없습니다.")
                    binding.tvCalc.text = "Error. 0으로 나눌 수 없습니다."
                    hasOperator = true
                    setOpVisible(false)

                } else {
                    currRes = currRes.div(BigInteger(binding.tvCalc.text.toString()))
                    hasOperator = true
                    setOpVisible(false)
                    binding.tvCalc.text = currRes.toString()
                    sumList.add(currRes)
                }
            }
            else -> {}
        }
    }

    private fun Context.shortToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}