package com.camila.challenge001.model

data class Item(
    val name: String,
    val description: String,
    val forks_count: Int,
    val stargazers_count: Int,
    var owner: Owner
)