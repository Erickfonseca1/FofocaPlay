package com.example.fofocaplay

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity: AppCompatActivity() {
    private lateinit var btJogar: Button
    private lateinit var btCadastrar: Button
    private lateinit var tvQuantidade: TextView
    private var cadastro: Cadastro

    init {
        this.cadastro = Cadastro()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.btCadastrar = findViewById(R.id.btCadastrar)
        this.btJogar = findViewById(R.id.btJogar)
        this.tvQuantidade = findViewById(R.id.tvQuantidade)

        this.tvQuantidade.text = "${this.cadastro.size()} fofoca(s) salva(s)."

        var formResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val fofoca = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    it.data?.getSerializableExtra("FOFOCA", Fofoca::class.java)
                } else {
                    it.data?.getSerializableExtra("FOFOCA")
                } as Fofoca
                this.cadastro.add(fofoca)
                this.tvQuantidade.text = "${this.cadastro.size()} fofoca(s) salva(s)"
                Toast.makeText(this, "Cadastrada com sucesso!", Toast.LENGTH_SHORT).show()
            }
        }

        var jogoResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                Toast.makeText(this, "Ganhou!!!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Perdeu...", Toast.LENGTH_SHORT).show()
            }
        }

        this.btCadastrar.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            formResult.launch(intent)
        }

        this.btJogar.setOnClickListener {
            if (this.cadastro.size() > 0) {
                val intent = Intent(this, JogoActivity::class.java).apply {
                    putExtra("FOFOCA", this@MainActivity.cadastro.getFofoca())
                }
                jogoResult.launch(intent)
            } else {
                Toast.makeText(this, "Não há fofocas cadastradas", Toast.LENGTH_SHORT).show()
            }
        }


    }
}