package com.jonathan.zenly

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.counter_layout.view.*


class CounterAdapter : RecyclerView.Adapter<CounterAdapter.ViewHolder>() {

    private val counterList = mutableListOf<Int>()

    init {
        for (x in 0..COUNTER_MAX) {
            Log.d("JJJ", "x is $x")
            counterList.add(x + COUNTER_OFFSET)
        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private lateinit var coounterTextView : TextView

        fun bindView(counterValue: String) {
            Log.d("JJJ", "counterValue is $counterValue")
            coounterTextView = view.counterTextView
            coounterTextView.text =  counterValue
            Log.d("JJJ", "  view.counterText is ${view.counterTextView.text} , ${coounterTextView.text}")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.counter_layout, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.d("JJJ", "size is " + counterList.size)
        return counterList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(counterList[position + COUNTER_OFFSET].toString())
    }

    companion object {
        const val COUNTER_MAX = 250
        const val COUNTER_OFFSET = 1
    }
}