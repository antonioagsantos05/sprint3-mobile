package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.model.CadastroRequest
import com.example.myapplication.model.CadastroResponse
import com.example.myapplication.network.ClienteService
import com.example.myapplication.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CadastroActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        Log.d("CadastroActivity", "CadastroActivity created")

        val loginLink: TextView = findViewById(R.id.tv_login)
        val cadastroButton: Button = findViewById(R.id.btn_cadastrar)
        val nomeEditText: EditText = findViewById(R.id.inputNome)
        val emailEditText: EditText = findViewById(R.id.inputEmail)
        val senhaEditText: EditText = findViewById(R.id.inputSenha)

        loginLink.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        cadastroButton.setOnClickListener {
            val nome = nomeEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val senha = senhaEditText.text.toString().trim()

            Log.d("CadastroActivity", "Cadastro button clicked")

            if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                Log.d("CadastroActivity", "Campos não preenchidos")
                Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Criação de um novo cliente usando Retrofit
            val cadastroRequest = CadastroRequest(nome, email, senha)
            Log.d("CadastroActivity", "CadastroRequest created: $cadastroRequest")

            val clienteService = RetrofitClient.createService(ClienteService::class.java)
            Log.d("CadastroActivity", "Retrofit client and ClienteService instance created")

            clienteService.cadastro(listOf(cadastroRequest)).enqueue(object : Callback<List<CadastroResponse>> {
                override fun onResponse(call: Call<List<CadastroResponse>>, response: Response<List<CadastroResponse>>) {
                    Log.d("CadastroActivity", "onResponse called")
                    Log.d("CadastroActivity", "Response code: ${response.code()}")
                    Log.d("CadastroActivity", "Response body: ${response.body()}")
                    Log.d("CadastroActivity", "Error body: ${response.errorBody()?.string()}")

                    if (response.isSuccessful && response.body() != null) {
                        Log.d("CadastroActivity", "Cadastro realizado com sucesso: ${response.body()}")
                        Toast.makeText(this@CadastroActivity, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@CadastroActivity, LoginActivity::class.java))
                        finish()
                    } else {
                        val errorMessage = response.errorBody()?.string() ?: "Erro desconhecido"
                        Log.e("CadastroActivity", "Falha no cadastro - Erro: $errorMessage")
                        Toast.makeText(this@CadastroActivity, "Falha no cadastro: $errorMessage", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<List<CadastroResponse>>, t: Throwable) {
                    Log.e("CadastroActivity", "Erro de rede: ${t.message}")
                    Toast.makeText(this@CadastroActivity, "Erro de rede: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}
