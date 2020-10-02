package com.jonathan.zenly

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.animation.AccelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var counterAdapter: CounterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setGradientAnimation()

        setupCounterView()
    }

    /**
     * Task 3:
     *
     *
     */
    private fun setupCounterView() {
        counterAdapter = CounterAdapter(this)
        counterList.apply {
            adapter = counterAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            addOnItemTouchListener(object : RecyclerView.OnItemTouchListener{
                override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
                }

                override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                    return true
                }

                override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
                }

            })
        }

        counterButton.setOnClickListener {
            if (isValidNumber(enterValue.text.toString())) {
                counterList.smoothScrollToPosition(
                    enterValue.text.toString().toInt() - CounterAdapter.COUNTER_OFFSET
                )
            }
        }
    }

    private fun isValidNumber(counterText: String): Boolean {
        try {
            val counter: Int = counterText.toInt()

            if (counter < CounterAdapter.COUNTER_MAX) {
                return true
            }
        } catch (exception: NumberFormatException) {
            return false
        }
        return false
    }

    /**
     * Task 2:
     *
     *  Consider a GradientDrawable (let’s call it gd) displaying a red to blue vertical
    gradient. Write some code to animate gd so that it smoothly animates from its current
    state to a green to yellow vertical gradient. In order to animate the gradient you can
    either use a 2-seconds fixed timing or synchronise it to a ViewPager page scrolling
    event (using positionOffset from onPageScrolled).
     */
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