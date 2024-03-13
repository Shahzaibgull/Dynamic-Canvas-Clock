package com.example.canvasexamples

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.Shader
import android.graphics.SweepGradient
import android.util.AttributeSet
import android.view.View
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class SecondClock(context: Context?, attr: AttributeSet? = null) : View(context, attr) {

    private val drawableContainer = Rect()
    private val boundaryOfText = Rect()
    private val text = "Hello World"

    private var rectangle: RectF? = null
    private var margin: Float =50f


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (w > 0 && h > 0) {
            drawableContainer[0, 0, w] = h
            val fontSize =  calculateFontSize(boundaryOfText, drawableContainer, text)
            digitalClockPaint.textSize=fontSize
            TextPaint.textSize=fontSize
        }
        invalidate()
    }

    companion object {
        private fun calculateFontSize(textBounds: Rect, textContainer: Rect, text: String): Float {
            val textPaint = Paint()
            var stage = 1
            var textSize = 0f
            while (stage < 3) {
                if (stage == 1) textSize += 10f else if (stage == 2) textSize -= 1f
                textPaint.textSize = textSize
                textPaint.getTextBounds(text, 0, text.length, textBounds)
                textBounds.offsetTo(textContainer.left, textContainer.top)
                val fits = textContainer.contains(textBounds)
                if (stage == 1 && !fits) stage++ else if (stage == 2 && fits) stage++
            }
            return textSize
        }
    }

    val digitalClockPaint = Paint().apply {
        color = Color.WHITE
        textSize = 210f
        textAlign = Paint.Align.CENTER
        isFakeBoldText = true
    }

    val TextPaint = Paint().apply {
        color = Color.WHITE
        textSize = 30f
        textAlign = Paint.Align.CENTER
        isFakeBoldText = true
    }

    val circlePaint = Paint().apply {
        color = Color.WHITE
    }

    val circlePaintBackground = Paint().apply {
        isAntiAlias = true
        color = Color.GRAY
        style = Paint.Style.STROKE
        strokeWidth = 80f
    }

    val circlePaintForeground = Paint().apply {
        isAntiAlias = true
        color = Color.CYAN
        style = Paint.Style.STROKE
        strokeWidth = 80f
    }

    val BatteryTextPaint = Paint().apply {
        color = Color.CYAN
        style= Paint.Style.STROKE
        strokeWidth=10f
    }

    val BatteryTextPaint1 = Paint().apply {
        color = Color.CYAN
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        canvas.drawColor(Color.BLACK)

        if (rectangle == null) {
            //rectangle = RectF(margin , margin+200 , width.toFloat() - margin, height.toFloat() - 120f)
            rectangle = RectF(margin , margin+200 , width.toFloat() - margin, height.toFloat() - 120f)
        }
        canvas.drawArc(rectangle!!, -90f, 0.82f * 360, false, circlePaintForeground)
        canvas.drawArc(rectangle!!, -90f + 0.82f * 360, (1 - 0.82f) * 360, false, circlePaintBackground)

        val radius=width.coerceAtLeast(width/2)/2.2f  //2.5
        //canvas.drawCircle(width / 2f, height / 2f, radius, circlePaintBackground)

        canvas.clipRect(0, 0, width, height)
        val halfTextHeight = boundaryOfText.height() / 2f

        val currentTimeHour = SimpleDateFormat("HH", Locale.getDefault()).format(Date())
        canvas.drawText(currentTimeHour, drawableContainer.centerX()/1.5f, drawableContainer.centerY()/6f + halfTextHeight, digitalClockPaint)

        val currentTimeMinutes = SimpleDateFormat("mm", Locale.getDefault()).format(Date())
        canvas.drawText(currentTimeMinutes, drawableContainer.centerX()/0.75f, drawableContainer.centerY()/6f + halfTextHeight, digitalClockPaint)

        canvas.drawCircle(width / 2f, height / 9f, radius/18, circlePaint)
        canvas.drawCircle(width / 2f, height / 13f, radius/18, circlePaint)

        val currentMonth = SimpleDateFormat("MMM", Locale.getDefault()).format(Date())
        canvas.drawText(currentMonth,drawableContainer.centerX().toFloat(), drawableContainer.centerY()/1.55f + halfTextHeight,TextPaint)

        val currentDay = SimpleDateFormat("EEE", Locale.getDefault()).format(Date())
        canvas.drawText(currentDay,drawableContainer.centerX().toFloat(), drawableContainer.centerY()/1.05f + halfTextHeight,TextPaint)

        val currentDate = SimpleDateFormat("dd", Locale.getDefault()).format(Date())
        canvas.drawText(currentDate,drawableContainer.centerX().toFloat(), drawableContainer.centerY()/0.79f + halfTextHeight,TextPaint)

        val rectF = RectF(width/1.67f, height/1.28f, width/2.5f, height/1.35f)
        canvas.drawRoundRect(rectF, 8f, 8f, BatteryTextPaint)

        val rectF1 = RectF(width/1.87f, height/1.28f, width/2.5f, height/1.35f)
        canvas.drawRoundRect(rectF1, 8f, 8f, BatteryTextPaint1)

        //canvas.drawText(text1,drawableContainer.centerX().toFloat(), drawableContainer.centerY()/0.54f + halfTextHeight,BatteryTextPaint11)
    }
}