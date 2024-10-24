package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PreparedClassesActivity : AppCompatActivity() {
    private val preparedClasses = mutableListOf<String>()
    private lateinit var listViewPreparedClasses: ListView
    private lateinit var editTextNewClass: EditText
    private lateinit var adapter: ArrayAdapter<String>

    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prepared_classes)

        listViewPreparedClasses = findViewById(R.id.listViewPreparedClasses)
        editTextNewClass = findViewById(R.id.editTextNewClass)
        val buttonBackToMenu: Button = findViewById(R.id.buttonBackToMenuPrepared)
        val buttonAddClass: Button = findViewById(R.id.buttonAddClass)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, preparedClasses)
        listViewPreparedClasses.adapter = adapter

        buttonBackToMenu.setOnClickListener {
            finish()
        }

        buttonAddClass.setOnClickListener {
            val newClass = editTextNewClass.text.toString()
            if (newClass.isNotEmpty()) {
                preparedClasses.add(newClass)
                adapter.notifyDataSetChanged()
                editTextNewClass.text.clear()
            }
        }

        // Configurar Retrofit
        setupRetrofit()
        fetchPreparedClasses()
    }

    private fun setupRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://your-api-url.com/") // Substitua pela URL base da sua API
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    private fun fetchPreparedClasses() {
        apiService.getPreparedClasses().enqueue(object : Callback<List<String>> {
            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                if (response.isSuccessful) {
                    response.body()?.let { classes ->
                        preparedClasses.clear()
                        preparedClasses.addAll(classes)
                        adapter.notifyDataSetChanged()
                    }
                } else {
                    Toast.makeText(this@PreparedClassesActivity, "Erro ao obter aulas preparadas", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                Toast.makeText(this@PreparedClassesActivity, "Falha na chamada: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}

private fun <T> Call<T>.enqueue(callback: Callback<List<String>>) {

}
