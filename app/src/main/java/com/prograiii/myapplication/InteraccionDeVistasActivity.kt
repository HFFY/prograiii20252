package com.prograiii.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class InteraccionDeVistasActivity : AppCompatActivity() {

    val context: Context = this

    companion object{
        val ID_PASO_DE_DATOS_STRING = "ID_ENVIO_DATO"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_interaccion_de_vistas)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val textViewEjemplo: TextView = findViewById(R.id.text_view_interaccion_ejemplo)
        val button: Button = findViewById(R.id.button_interaccion_ejemplo)
        val editTextEjemplo: EditText = findViewById(R.id.edit_ejempli_clases)
        val textViewEjemploClases: TextView = findViewById(R.id.text_view_ejemplo_de_clases)
        val buttonEjemplo: Button = findViewById(R.id.button_ejemplo_clases)
        textViewEjemplo.text = "Este es un ejemplo de texto desde Kotlin"

        button.setOnClickListener {
            val intentCambioAMain: Intent = Intent(context, EjemploConstraintActivity::class.java)
            intentCambioAMain.apply {
                putExtra(ID_PASO_DE_DATOS_STRING, "Ejemplo de Envio de datos")
                //putExtra("ID_SERIALIZABLE", Estudiante("","","","",1,1,1))
            }
            startActivity(intentCambioAMain)

        }

        buttonEjemplo.setOnClickListener {
            val textoEscrito: String = editTextEjemplo.text.toString()
            textViewEjemploClases.text = textoEscrito
            editTextEjemplo.setText("")
        }

    }

    override fun onStart() {
        super.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
    }


}