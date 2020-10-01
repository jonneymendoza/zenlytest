package com.jonathan.zenly

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.animation.AccelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setGradientAnimation()
    }

    private fun setGradientAnimation() {
        var startColor = 0
        var endColor = 0

        val startColorAnim = ValueAnimator.ofObject(
            ArgbEvaluator(),
            ContextCompat.getColor(this, R.color.red),
            ContextCompat.getColor(this, R.color.green)
        )
        val endColorAnim = ValueAnimator.ofObject(
            ArgbEvaluator(),
            ContextCompat.getColor(this, R.color.blue),
            ContextCompat.getColor(this, R.color.yellow)
        )

        startColorAnim.apply {
            interpolator = AccelerateInterpolator()
            duration = 2000
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.RESTART

            addUpdateListener {
                startColor = animatedValue as Int
                gd.background = GradientDrawable(
                    GradientDrawable.Orientation.TOP_BOTTOM,
                    intArrayOf(startColor, endColor)
                )
            }
            start()
        }

        endColorAnim.apply {
            interpolator = AccelerateInterpolator()
            duration = 2000
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.RESTART
            addUpdateListener {
                endColor = animatedValue as Int
                gd.background = GradientDrawable(
                    GradientDrawable.Orientation.TOP_BOTTOM,
                    intArrayOf(startColor, endColor)
                )
            }
            start()
        }
    }

}