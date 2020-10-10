package com.osama.repository

sealed class Result<out T> {
    data class Loaded<out T>(val result: T):Result<T>()
    data class Error(val code: Int, val text: String) : Result<Nothing>()
}