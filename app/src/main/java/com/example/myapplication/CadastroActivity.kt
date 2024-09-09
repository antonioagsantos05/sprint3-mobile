package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.LoginActivity

class CadastroActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        // Encontre o TextView que leva à tela de login
        val loginLink: TextView = findViewById(R.id.tv_login)

        // Configure o listener de clique para o TextView
        loginLink.setOnClickListener {
            // Cria um Intent para iniciar a LoginActivity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}