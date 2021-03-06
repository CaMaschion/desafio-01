package com.camila.challenge001.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiService {

    private fun retrofit() = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> buildService(service:Class<T>) : T {
        return retrofit().create(service)
    }
}
