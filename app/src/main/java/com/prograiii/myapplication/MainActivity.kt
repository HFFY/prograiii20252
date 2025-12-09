package com.prograiii.myapplication

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.prograiii.myapplication.dataclases.Estudiante
import com.prograiii.myapplication.dataclases.actualizarSemestre
import com.prograiii.myapplication.dataclases.esUnSemestreAvanzado
import kotlinx.serialization.json.Json

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var variableNuleable: Int? = null
        val nombreEstudiante: String = "Hugo"
        val apellidoPaterno: String? = null
        var variableInt = 0

        variableInt = apellidoPaterno?.length ?: 0

        val estudiante1 = Estudiante(
            "Hugo",
            "Fabian",
            "Flores",
            "Yañez",
            1234,
            1234,
            10
        )

        val apellidoCompleto: String =
            "El apellidp completo del estudiante es: ${estudiante1.apellidoPaterno} ${estudiante1.apellidoMaterno}"

        println(apellidoCompleto)

        ejemplo("este es un texto de ejemplo")

        var miPrimeraLista: List<String> = listOf("Hola", "Mundo", "Progra 3")

        miPrimeraLista.get(0)

        miPrimeraLista = listOf("")

        val miListaMutable: MutableList<String> = mutableListOf("Hola", "Curso","Progra")

        miListaMutable.add("como estan")
        miListaMutable.add("Buen Lunes")

        //miListaMutable = mutableListOf("Progra 3")

        for (i in 1..10 step 3){
            println(i)
        }

        for (i in 10 downTo 0 step 2){
            println(i)
        }

        for (i in "Hola Mundo"){
            println(i)
        }

        val listaGrupo: List<Estudiante> = listEstudiantesFuncion()

        listaGrupo.forEach { integrante ->
           println(integrante.primerNombre)
        }

        val buttonEjemplo: Button = findViewById(R.id.button_ejemplo)

        buttonEjemplo.setOnClickListener {
            println("He sido Clickeado")
        }

        val jsonString: String = Json.encodeToString(estudiante1)

    }

    fun ejemplo(textoEjemplo: String){
        val ejemploConcatenado = "El String que recibi es $textoEjemplo"
        println(ejemploConcatenado)
    }

    fun listEstudiantesFuncion(): MutableList<Estudiante>{

        val estudiant1 = Estudiante(
            "Hugo",
            "Fabian",
            "Flores",
            "Yañez",
            1234,
            1234,
            1234
        )
        val estudiant2 = Estudiante(
            "Paco",
            "Fabian",
            "Flores",
            "Yañez",
            1234,
            1234,
            1234
        )
        val estudiant3 = Estudiante(
            "Luis",
            "Fabian",
            "Flores",
            "Yañez",
            1234,
            1234,
            1234
        )
        val listaEstudiantes: MutableList<Estudiante> = mutableListOf(estudiant1,estudiant2,estudiant3)
//        println("Nuestro Grupo esta conformado por: ${listaEstudiantes.get(0).primerNombre}, ${listaEstudiantes.get(1).primerNombre}, ${listaEstudiantes.get(3).primerNombre} ")
        return listaEstudiantes
    }

    fun mapaEstudiantesEjercicio(): MutableMap<Int, Estudiante>{
        val estudiant1 = Estudiante(
            "Hugo",
            "Fabian",
            "Flores",
            "Yañez",
            1234,
            1234,
            1234
        )

        val estudiant2 = Estudiante(
            "Paco",
            "Fabian",
            "Flores",
            "Yañez",
            12346,
            1234,
            1234
        )
        val estudiant3 = Estudiante(
            "Luis",
            "Fabian",
            "Flores",
            "Yañez",
            12345,
            1234,
            1234
        )

        "hola Mundo".largoEsPar()
        val ejmploStr : String = "hola Mundo progra 3"
        ejmploStr.largoEsPar()

        val ejemploInt = 10
        ejemploInt.esImpar()

        estudiant3.esUnSemestreAvanzado()

        estudiant3.actualizarSemestre(8)
        estudiant2.actualizarSemestre(2)

        return mutableMapOf(
            estudiant1.codigo to estudiant1,
            estudiant2.codigo to estudiant2,
            estudiant3.codigo to estudiant3
        )
    }

    fun String.largoEsPar(): Boolean{
        return this.length % 2 == 0
    }

    fun Int.esImpar(): Boolean{
        return this % 2 != 0
    }

}