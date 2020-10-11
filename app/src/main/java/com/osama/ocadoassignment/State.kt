package com.osama.ocadoassignment


sealed class State <out T> {
    data class Data<out T>(val data: T): State<T>()
    data class Error(val code: Int, val text: String) : State<Nothing>()
    data class Loading(var isLoading: Boolean) : State<Nothing>()

    }