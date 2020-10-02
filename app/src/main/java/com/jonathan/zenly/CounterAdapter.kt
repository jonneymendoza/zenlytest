package com.jonathan.zenly

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.counter_layout.view.*


class CounterAdapter(val context: Context) : RecyclerView.Adapter<CounterAdapter.ViewHolder>() {

    private val counterList = mutableListOf<Int>()

    init {
        for (x in 0..COUNTER_MAX) {
            Log.d("JJJ", "init x is $x")
            counterList.add(x + COUNTER_OFFSET)
        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private lateinit var coounterTextView: TextView

        fun bindView(counterValue: String) {
            coounterTextView = view.counterTextView
            coounterTextView.text = counterValue
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.counter_layout, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return counterList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(counterList[position].toString())
    }

    companion object {
        const val COUNTER_MAX = 250
        const val COUNTER_OFFSET = 1
    }
}