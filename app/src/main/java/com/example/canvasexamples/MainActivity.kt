package com.example.canvasexamples


import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.graphics.drawable.shapes.RectShape
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.canvasexamples.databinding.ActivityMainBinding
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Creating a Bitmap and Canvas
        val bitmap: Bitmap = Bitmap.createBitmap(700, 1000, Bitmap.Config.ARGB_8888)
        val canvas: Canvas = Canvas(bitmap)

        //Draw Digital Clock on Canvas
        val digitalClockPaintAM= Paint().apply {
            color = Color.LTGRAY
            textSize = 30f
            textAlign = Paint.Align.CENTER
            isFakeBoldText = true
        }
        val currentTimeAM = SimpleDateFormat("aa", Locale.getDefault()).format(Date())
        canvas.drawText(currentTimeAM, canvas.width / 2f, canvas.height / 4f, digitalClockPaintAM)

        // Paint for hour text
        val digitalClockPaint = Paint().apply {
            color = Color.MAGENTA
            textSize = 210f
            textAlign = Paint.Align.CENTER
            isFakeBoldText = true
        }
        val currentTimeHour = SimpleDateFormat("HH", Locale.getDefault()).format(Date())
        canvas.drawText(currentTimeHour, canvas.width / 2f, canvas.height / 2f-70, digitalClockPaint)

        val LinePaint1 = Paint().apply {
            color = Color.LTGRAY
            strokeWidth=5f
            isFakeBoldText = true
        }
        canvas.drawLine(0f,450f,1080f,450f,LinePaint1)

        val TextPaint = Paint().apply {
            color = Color.WHITE
            textSize = 30f
            textAlign = Paint.Align.CENTER
            isFakeBoldText = true
        }
        canvas.drawText("24 JULY MON",canvas.width / 2f,canvas.height * 0.5f,TextPaint)

        canvas.drawLine(0f,530f,1080f,530f,LinePaint1)

        val currentTimeMinutes = SimpleDateFormat("mm", Locale.getDefault()).format(Date())
        canvas.drawText(currentTimeMinutes, canvas.width / 2f, canvas.height / 2f+205, digitalClockPaint)

        val circlePaint = Paint().apply {
            color = Color.LTGRAY
        }
        canvas.drawCircle(canvas.width / 2f-300, canvas.height / 1.5f-173, 7f, circlePaint)
        canvas.drawCircle(canvas.width / 2f-270, canvas.height / 1.5f-173, 7f, circlePaint)
        canvas.drawCircle(canvas.width / 2f-240, canvas.height / 1.5f-173, 7f, circlePaint)

        canvas.drawCircle(canvas.width / 2f+240, canvas.height / 1.5f-173, 7f, circlePaint)
        canvas.drawCircle(canvas.width / 2f+270, canvas.height / 1.5f-173, 7f, circlePaint)
        canvas.drawCircle(canvas.width / 2f+300, canvas.height / 1.5f-173, 7f, circlePaint)

/*
        // Battery triangle1
        val trianglePaint = Paint().apply {
            color = Color.GREEN
            style = Paint.Style.FILL
        }
        val x1 = canvas.width / 2f - 10f-200
        val y1 = canvas.height / 2f + 10f-350
        val x2 = canvas.width / 2f + 10f-200
        val y2 = canvas.height / 2f + 10f-350
        val x3 = canvas.width / 2f-200
        val y3 = canvas.height / 2f - 10f-350
        canvas.save()
        canvas.rotate(295f, canvas.width/2f, canvas.height/2f)
        val path = Path().apply {
            moveTo(x1, y1)
            lineTo(x2, y2)
            lineTo(x3, y3)
            close()
        }
        canvas.drawPath(path, trianglePaint)*/


        val BatteryTextPaint = Paint().apply {
            color = Color.WHITE
            textSize = 40f
            textAlign = Paint.Align.CENTER
            isFakeBoldText = true
        }
        canvas.drawText("89",canvas.width / 2f-280,canvas.height * 0.4f,BatteryTextPaint)


        // Set the bitmap as the background of the ImageView
        binding.imageView.background = BitmapDrawable(resources, bitmap)
    }
}

