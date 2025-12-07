package com.prograiii.myapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prograiii.myapplication.databinding.AdapterEjemploBinding
import com.prograiii.myapplication.dataclases.TituloDataClass

class EjemploAdater: RecyclerView.Adapter<EjemploAdater.EjemploCardViewHolder>() {

    private val dataCards = mutableListOf<TituloDataClass>()
    private var context: Context? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EjemploCardViewHolder {
        context = parent.context
        return EjemploCardViewHolder(
            AdapterEjemploBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: EjemploCardViewHolder, position: Int) {
        holder.binding(dataCards[position])
    }

    override fun getItemCount(): Int = dataCards.size


    // Donde hacer la logica
    inner class EjemploCardViewHolder(private val binding: AdapterEjemploBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binding(data: TituloDataClass) {


            binding.textViewAdapterTitulo.text = data.titulo
            binding.textViewAdapterSubTitulo.text = data.subTitulo
            binding.textViewAdapterTitulo.setOnClickListener {
                binding.textViewAdapterSubTitulo.text = " El Titulo fue clickeado"
            }
            manejoDeDatos()
        }

        fun manejoDeDatos(){}
    }

    fun addDataCards(list: List<TituloDataClass>) {
        dataCards.clear()
        dataCards.addAll(list)
    }
}