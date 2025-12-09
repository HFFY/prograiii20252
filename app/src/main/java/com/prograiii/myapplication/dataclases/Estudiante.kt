package com.prograiii.myapplication.dataclases

import kotlinx.serialization.Serializable

@Serializable
data class Estudiante(
    val primerNombre: String,
    val segundoNombre: String?,
    val apellidoPaterno: String,
    val apellidoMaterno: String,
    val codigo: Int,
    var edad: Int,
    var semestre: Int
)

fun Estudiante.esUnSemestreAvanzado(): Boolean{
    return this.semestre >= 6
}

fun Estudiante.actualizarSemestre(nuevoSemestre: Int){
    this.semestre = nuevoSemestre
}
