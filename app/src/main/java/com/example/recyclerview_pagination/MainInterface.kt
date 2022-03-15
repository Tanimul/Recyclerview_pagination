package com.example.recyclerview_pagination

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MainInterface {
    @GET("v2/list")
    suspend fun getList(@Query("page") page: Int?, @Query("limit") limit: Int?): Response<List<ProfileModel>>
}