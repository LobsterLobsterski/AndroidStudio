package com.example.myapplication

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class MyListAdapter(private val context: Activity, private val name: Array<String>, private val desc: Array<String>)
    : ArrayAdapter<String>(context, R.layout.custom_list, name) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater

        val rowView = inflater.inflate(R.layout.custom_list, null, true)

        val nameText = rowView.findViewById(R.id.textViewName) as TextView
        val descText = rowView.findViewById(R.id.textViewEmail) as TextView

        nameText.text = "Nazwa:\t ${name[position]}"
        descText.text = "Opis:\t ${desc[position]}"
        return rowView
    }
}
