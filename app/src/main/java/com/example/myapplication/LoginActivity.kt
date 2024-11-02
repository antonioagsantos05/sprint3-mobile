package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.model.LoginRequest
import com.example.myapplication.model.LoginResponse
import com.example.myapplication.network.RetrofitClient
import com.example.myapplication.network.ClienteService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var senhaEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var cadastroLink: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Log.d("LoginActivity", "Activity started")

        emailEditText = findViewById(R.id.inputEmail)
        senhaEditText = findViewById(R.id.inputSenha)
        loginButton = findViewById(R.id.btn_login)
        cadastroLink = findViewById(R.id.tv_cadastro)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val senha = senhaEditText.text.toString().trim()

            if (email.isEmpty() || senha.isEmpty()) {
                Log.d("LoginActivity", "Campos de email ou senha vazios")
                Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show()
            } else {
                Log.d("LoginActivity", "Iniciando processo de login")
                performLogin(email, senha)
            }
        }

        cadastroLink.setOnClickListener {
            Log.d("LoginActivity", "Clicado no link de cadastro")
            startActivity(Intent(this, CadastroActivity::class.java))
        }
    }

    private fun performLogin(email: String, senha: String) {
        Log.d("LoginActivity", "Enviando requisição de login com email: $email")

        val clienteService = RetrofitClient.createService(ClienteService::class.java)
        clienteService.login(email, senha).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    Log.d("LoginActivity", "Login realizado com sucesso")
                    Toast.makeText(this@LoginActivity, "Login realizado com sucesso", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    val errorMessage = response.errorBody()?.string() ?: "Erro desconhecido"
                    Log.e("LoginActivity", "Falha no login: $errorMessage")
                    Toast.makeText(this@LoginActivity, "Falha no login: $errorMessage", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("LoginActivity", "Erro de rede: ${t.message}")
                Toast.makeText(this@LoginActivity, "Erro de rede: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
