package com.prograiii.myapplication

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import com.prograiii.myapplication.basededatos.EjemploDao
import com.prograiii.myapplication.basededatos.EjemploDataBase
import com.prograiii.myapplication.basededatos.EjemploParaRoom
import com.prograiii.myapplication.databinding.ActivityEjemploDePersistenciaBinding
import com.prograiii.myapplication.dataclases.Estudiante
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class EjemploDePersistenciaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEjemploDePersistenciaBinding
    private lateinit var sharedPreferences: SharedPreferences
    val context: Context = this

    private lateinit var ejemploDao: EjemploDao

    companion object{
        val NOMBRE_FICHERO_SHARED_PREFERENCES = "Progra3II"
        val NOMBRE_DATO_EJEMPLO = "DatoEjemplo"
        val NOMBRE_ESTUADIANTE_GUARDADO = "EstudianteAlmacenado"
        val DATABASE_NAME: String = "USER_DATABASE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityEjemploDePersistenciaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = context.getSharedPreferences(
            NOMBRE_FICHERO_SHARED_PREFERENCES,MODE_PRIVATE
        )

        val ejemploDataBase = Room.databaseBuilder(
            context, EjemploDataBase::class.java, DATABASE_NAME
        ).build()

        ejemploDao = ejemploDataBase.ejemploDao()

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
            guardarDatosEnBaseDeDatos()
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
            }

            val datosEjemploRoom  = obtenerDatosEnBaseDeDatos()
            binding.textViewDemosttracion.text = datosEjemploRoom.toString()

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

    private fun guardarDatosEnBaseDeDatos() {
        GlobalScope.launch {
            val ejemplo = EjemploParaRoom(
                id = 0,
                unTextoColumna = "Texto Ejemplo",
                unNumeroColumna = 1,
                unBooleanColumna = true,
            )
            ejemploDao.insertAll(ejemplo)
        }
    }

    private fun obtenerDatosEnBaseDeDatos():List<EjemploParaRoom>  {
        var ejemplo: List<EjemploParaRoom> = listOf()
        runBlocking {
            withContext(Dispatchers.IO){
                ejemplo = ejemploDao.getAll()
            }
        }
        return ejemplo
    }

}