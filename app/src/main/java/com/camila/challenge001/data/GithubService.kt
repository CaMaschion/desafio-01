package com.camila.challenge001.data

import com.camila.challenge001.model.GithubRepository
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {

    @GET("/search/repositories?q=language:kotlin&sort=stars")
    fun getRepositories(@Query("page")page: Int ) : Call<GithubRepository>
}
