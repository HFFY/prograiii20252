package com.prograiii.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.prograiii.myapplication.adapters.EjemploAdater
import com.prograiii.myapplication.databinding.ActivityEjemploRecyclerViewBinding
import com.prograiii.myapplication.dataclases.Estudiante
import com.prograiii.myapplication.dataclases.TituloDataClass
import kotlinx.serialization.json.Json

class EjemploRecyclerViewActivity : AppCompatActivity() {

    lateinit var binding: ActivityEjemploRecyclerViewBinding

    val adapterEjemplo1: EjemploAdater by lazy { EjemploAdater() }

    val adapterEjemplo2: EjemploAdater by lazy { EjemploAdater() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityEjemploRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val listaEjemploStr = mutableListOf<TituloDataClass>(
            TituloDataClass("Titulo 1", "Sub-Titulo"),
            TituloDataClass("Titulo 2", "Sub-Titulo"),
            TituloDataClass("Titulo 3", "Sub-Titulo"),
            TituloDataClass("Titulo", "Sub-Titulo"),
            TituloDataClass("Titulo", "Sub-Titulo"),
            TituloDataClass("Titulo", "Sub-Titulo"),
            TituloDataClass("Titulo", "Sub-Titulo"),
            TituloDataClass("Titulo", "Sub-Titulo 1"),
            TituloDataClass("Titulo", "Sub-Titulo 2"),
            TituloDataClass("Titulo", "Sub-Titulo 3"),
        )

        val listaDatosDos = mutableListOf<TituloDataClass>(
            TituloDataClass("Ejemplo Recycler 2", "Adaptado de datos 2"),
            TituloDataClass("Ejemplo Recycler 2", "Adaptado de datos 2"),
            TituloDataClass("Ejemplo Recycler 2", "Adaptado de datos 2"),
            TituloDataClass("Ejemplo Recycler 2", "Adaptado de datos 2"),
            TituloDataClass("Ejemplo Recycler 2", "Adaptado de datos 2"),
            TituloDataClass("Ejemplo Recycler 2", "Adaptado de datos 2"),
            TituloDataClass("Ejemplo Recycler 2", "Adaptado de datos 2"),
            TituloDataClass("Ejemplo Recycler 2", "Adaptado de datos 2"),
            TituloDataClass("Ejemplo Recycler 2", "Adaptado de datos 2"),
        )

        adapterEjemplo1.addDataCards(listaEjemploStr)
        adapterEjemplo2.addDataCards(listaDatosDos)

        binding.recyclerEjemploClases.layoutManager =
            GridLayoutManager(this, 2)
        binding.recyclerEjemploClases.adapter = adapterEjemplo1

        binding.recyclerEjemploClasesDos.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.recyclerEjemploClasesDos.adapter = adapterEjemplo2

        val estudiante1 = Estudiante(
            "Hugo",
            "Fabian",
            "Flores",
            "Yañez",
            1234,
            1234,
            10
        )

        val strEstudiante = Json.encodeToString(estudiante1)
        binding.textViewRojo.text = strEstudiante
    }
}