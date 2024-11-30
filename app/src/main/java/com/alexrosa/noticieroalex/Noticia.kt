package com.alexrosa.noticieroalex

data class Noticia(
    val id: Int,
    val titulo: String,
    val resumen: String,
    val fecha: String,
    val imagen: String,
    val enlace: String
)
