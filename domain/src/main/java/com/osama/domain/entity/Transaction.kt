package com.osama.domain.entity

sealed class Transaction<out T> {
    data class Ready<out T>(val result: T):Transaction<T>()
    data class Error(val code: Int, val text: String) : Transaction<Nothing>(){

    }
}