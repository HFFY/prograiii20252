package com.prograiii.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.prograiii.myapplication.databinding.ActivityEjemploViewBindingBinding

class EjemploViewBindingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEjemploViewBindingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityEjemploViewBindingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //val textView1 : TextView = findViewById(R.id.text_view_ejemplo_binding_1)

        binding.textViewEjemploBinding1.text = "hola"

        binding.textViewEjemploBinding3.text = "Mundo"

        binding.buttonEjemploBinding.setOnClickListener {

        }
    }

    fun ejemploDeManejoDeVistas(){
        binding.textViewEjemploBinding5.text = " Progra 3 "
    }
}