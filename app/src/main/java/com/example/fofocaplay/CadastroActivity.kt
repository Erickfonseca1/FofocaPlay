package com.example.fofocaplay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CadastroActivity : AppCompatActivity() {
    private lateinit var btCancelar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        this.btCancelar = findViewById(R.id.btCancelar)
        this.btCancelar.setOnClickListener { backToMain() }
    }

    fun backToMain() {
        finish()
    }
}