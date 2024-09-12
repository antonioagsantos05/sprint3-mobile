package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.CadastroActivity


class LoginActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Encontre o TextView que leva à tela de cadastro
        val cadastroLink: TextView = findViewById(R.id.tv_cadastro)

        // Botão de login
        val loginButton: Button = findViewById(R.id.btn_login)

        // Configure o listener de clique para o TextView de cadastro
        cadastroLink.setOnClickListener {
            // Cria um Intent para iniciar a CadastroActivity
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }

        // Configure o listener de clique para o botão de login
        loginButton.setOnClickListener {
            // Aqui você pode adicionar a lógica de verificação do login (validação de credenciais)

            // Redireciona para a MainActivity após o login bem-sucedido
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
