// src/main/java/com/example/myapplication/ScheduleAdapter.kt
package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.myapplication.ClassSchedule
import com.example.myapplication.R

class ScheduleAdapter(private val context: Context, private val scheduleList: List<ClassSchedule>) :
    ArrayAdapter<ClassSchedule>(context, 0, scheduleList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_schedule, parent, false)
        val classSchedule = getItem(position)

        val subjectTextView = view.findViewById<TextView>(R.id.textViewSubject)
        val timeTextView = view.findViewById<TextView>(R.id.textViewTime)
        val institutionTextView = view.findViewById<TextView>(R.id.textViewInstitution)

        subjectTextView.text = classSchedule?.subject
        timeTextView.text = classSchedule?.time
        institutionTextView.text = classSchedule?.institution

        return view
    }
}