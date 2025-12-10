package com.prograiii.myapplication

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.prograiii.myapplication.databinding.ActivityEjemploDePersistenciaBinding
import com.prograiii.myapplication.dataclases.Estudiante
import kotlinx.serialization.json.Json

class EjemploDePersistenciaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEjemploDePersistenciaBinding
    private lateinit var sharedPreferences: SharedPreferences
    val context: Context = this

    companion object{
        val NOMBRE_FICHERO_SHARED_PREFERENCES = "Progra3II"
        val NOMBRE_DATO_EJEMPLO = "DatoEjemplo"
        val NOMBRE_ESTUADIANTE_GUARDADO = "EstudianteAlmacenado"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityEjemploDePersistenciaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = context.getSharedPreferences(
            NOMBRE_FICHERO_SHARED_PREFERENCES,MODE_PRIVATE
        )

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val ejemploEstudiante: Estudiante = Estudiante(
            "Hugo",
            "Fabian",
            "Flores",
            "Yañez",
            1234,
            1234,
            10
        )

        val estudianteString = Json.encodeToString(ejemploEstudiante)


        binding.buttonGuardarDatosShared.setOnClickListener {
//            guardarDatosSharedPreferences(
//                NOMBRE_DATO_EJEMPLO,
//                "Hola Mundo, Progra 3"
//            )

            guardarDatosSharedPreferences(
                NOMBRE_ESTUADIANTE_GUARDADO,
                estudianteString
            )
        }

        binding.buttonMostrarDatosShared.setOnClickListener {
//            obtenerDatosSharedPreferences(NOMBRE_DATO_EJEMPLO)

            val estudainteGuardado = obtenerDatosSharedPreferences(
                NOMBRE_ESTUADIANTE_GUARDADO
            )

            if (estudainteGuardado != null){
                val estudianteDecodificado: Estudiante = Json.decodeFromString<Estudiante>(
                    estudainteGuardado
                )
                binding.textViewDemosttracion.text = estudianteDecodificado.primerNombre
            }

        }
    }

    fun obtenerDatosSharedPreferences(nombreDelDato: String) : String?{
        val stringgGuardado: String? = sharedPreferences.getString(
            nombreDelDato,
            "No Existe el Dato almacenado"
        )
        binding.textMostrarSharedAlmacenado.text = stringgGuardado
        return stringgGuardado
    }

    fun guardarDatosSharedPreferences(nombreDelDato: String, datoAGuardar: String){
        val editor = sharedPreferences.edit()
        editor.putString(nombreDelDato, datoAGuardar)
        editor.apply()
    }

}