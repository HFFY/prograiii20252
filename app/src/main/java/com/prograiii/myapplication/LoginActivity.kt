package com.prograiii.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.prograiii.myapplication.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val currentUser = auth.currentUser
        if (currentUser != null){
            val intentUsuarioLogueado = Intent(this, EjemploRecyclerViewActivity::class.java)
            startActivity(intentUsuarioLogueado)
        }

        binding.buttonLogin.setOnClickListener {
            val correo = binding.editEmail.text.toString()
            val password = binding.editPassword.text.toString()
            loginUsuario(correo, password)
        }

        binding.buttonCrearCuenta.setOnClickListener {
            val correo = binding.editEmail.text.toString()
            val password = binding.editPassword.text.toString()
            crearUsuario(correo, password)
        }

    }

    fun loginUsuario(
        correo: String, password: String
    ){
        auth.signInWithEmailAndPassword(correo, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    // Nuestro Usuario se Logueo Correctamente
                    val intentLogueado = Intent(this, MainActivity::class.java)
                    startActivity(intentLogueado)
                } else {
                    // Nuestro usuario no se pudo Loguear
                    Toast.makeText(
                        baseContext,
                        "No pudo loguearse",
                        Toast.LENGTH_LONG,
                    ).show()
                }
        }
    }

    fun crearUsuario(
        correo: String,
        password: String
    ){
        auth.createUserWithEmailAndPassword(correo, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    // Mi usuario se creo Correctamente
                } else{
                    // No se pudo crear usuario
                    Toast.makeText(
                        baseContext,
                        "No pudo loguearse",
                        Toast.LENGTH_LONG,
                    ).show()
                }
            }

    }
}