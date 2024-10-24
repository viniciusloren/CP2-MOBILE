package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val buttonClassesSchedule: Button = findViewById(R.id.buttonClassesSchedule)
        val buttonPreparedClasses: Button = findViewById(R.id.buttonPreparedClasses)

        buttonClassesSchedule.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        buttonPreparedClasses.setOnClickListener {
            val intent = Intent(this, PreparedClassesActivity::class.java)
            startActivity(intent)
        }
    }
}

