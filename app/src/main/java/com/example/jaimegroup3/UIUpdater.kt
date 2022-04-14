package com.example.jaimegroup3

interface UIUpdaterInterface {

    fun resetUIWithConnection(status: Boolean)
    fun updateStatusViewWith(status: String)
    fun update(message: String)
    fun payloadMes(message: String)

}