package com.example.fofocaplay

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity

class CadastroActivity: AppCompatActivity() {
    private lateinit var etInputCadastro: EditText
    private lateinit var rbVerdadeCadastro: RadioButton
    private lateinit var rbMentiraCadastro: RadioButton
    private lateinit var btSalvar: Button
    private lateinit var btCancelar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        this.etInputCadastro = findViewById(R.id.etInputCadastro)
        this.rbVerdadeCadastro = findViewById(R.id.rbVerdadeCadastro)
        this.rbMentiraCadastro = findViewById(R.id.rbMentiraCadastro)
        this.btSalvar = findViewById(R.id.btSalvar)
        this.btCancelar = findViewById(R.id.btCancelar)

        this.btSalvar.setOnClickListener({ salvar() })
        this.btCancelar.setOnClickListener({ cancelar() })
    }

    fun salvar() {
        val descricao = this.etInputCadastro.text.toString()
        val status = this.rbVerdadeCadastro.isChecked
        val fofoca = Fofoca(descricao, status)
        val intent = Intent().apply {
            putExtra("FOFOCA", fofoca)
        }

        setResult(RESULT_OK, intent)
        finish()
    }

    fun cancelar() {
        finish()
    }
}