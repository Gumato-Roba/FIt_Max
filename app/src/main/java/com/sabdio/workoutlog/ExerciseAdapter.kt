package com.sabdio.workoutlog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.sabdio.workoutlog.models.Exercises

class ExerciseAdapter(context: Context,var exercise: List<Exercises> ):
    ArrayAdapter<Exercises>(context,0,exercise) {
    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getExerciseCustomView(position, convertView, parent)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getExerciseCustomView(position, convertView, parent)
    }

    fun getExerciseCustomView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = LayoutInflater.from(context).inflate(R.layout.custom_spinner_item, parent, false)
        val tvSpinnerText = view.findViewById<TextView>(R.id.tvSpinnerText)
        tvSpinnerText.text = exercise.get(position).exerciseId
        return view
    }
}