package com.example.fofocaplay

class Cadastro {
    private var list: MutableList<Fofoca>

    init {
        this.list = mutableListOf()
    }

    fun add(fofoca: Fofoca) {
        this.list.add(fofoca)
    }

    fun size(): Int {
        return this.list.size
    }

    fun getFofoca(): Fofoca {
        return this.list.random()
    }
}