package com.example.fofocaplay

class ListaFofoca {
    private var fofocas: MutableList<Fofoca> = mutableListOf()
    private lateinit var fofocaAtual: Fofoca

    fun getFofocaAtual(): Fofoca {
        return this.fofocaAtual
    }

    fun alterarFofocaAtual() {
        this.fofocaAtual = this.fofocas.random()
    }

    fun addFofoca(fofoca: Fofoca) {
        this.fofocas.add(fofoca)
    }

    fun chute(status: Boolean): GameStatus {
        return if (status == this.fofocaAtual.getStatus())
            GameStatus.CORRETO
        else
            GameStatus.INCORRETO
    }

    enum class GameStatus {
        CORRETO,
        INCORRETO
    }
}