package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val institutionAList = mutableListOf(
        ClassSchedule("Matemática", "08:00 - 09:30", "Instituição A"),
        ClassSchedule("História", "09:45 - 11:15", "Instituição A"),
        ClassSchedule("Química", "11:30 - 13:00", "Instituição A"),
        ClassSchedule("Educação Física", "14:00 - 15:30", "Instituição A")
    )

    private val institutionBList = mutableListOf(
        ClassSchedule("Biologia", "08:00 - 09:30", "Instituição B"),
        ClassSchedule("Geografia", "09:45 - 11:15", "Instituição B"),
        ClassSchedule("Física", "11:30 - 13:00", "Instituição B"),
        ClassSchedule("Artes", "14:00 - 15:30", "Instituição B")
    )

    private lateinit var listView: ListView
    private lateinit var spinner: Spinner
    private lateinit var adapter: ScheduleAdapter
    private var currentList = institutionAList

    private lateinit var editTextClassName: EditText
    private lateinit var editTextClassTime: EditText
    private lateinit var buttonAddClass: Button
    private lateinit var buttonBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listViewSchedule)
        spinner = findViewById(R.id.spinnerInstitution)
        editTextClassName = findViewById(R.id.editTextClassName)
        editTextClassTime = findViewById(R.id.editTextClassTime)
        buttonAddClass = findViewById(R.id.buttonAddClass)
        buttonBack = findViewById(R.id.buttonBack)

        adapter = ScheduleAdapter(this, currentList)
        listView.adapter = adapter

        val spinnerAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.institution_array,
            android.R.layout.simple_spinner_item
        )
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                currentList = when (position) {
                    0 -> institutionAList
                    1 -> institutionBList
                    else -> institutionAList
                }
                adapter = ScheduleAdapter(this@MainActivity, currentList)
                listView.adapter = adapter
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        buttonAddClass.setOnClickListener {
            val className = editTextClassName.text.toString()
            val classTime = editTextClassTime.text.toString()
            if (className.isNotEmpty() && classTime.isNotEmpty()) {
                val newClass = ClassSchedule(className, classTime, "Instituição A") // Altere para "Instituição B" se necessário
                currentList.add(newClass)
                adapter.notifyDataSetChanged()
                editTextClassName.text.clear()
                editTextClassTime.text.clear()
            }
        }

        buttonBack.setOnClickListener {
            finish()
        }
    }
}