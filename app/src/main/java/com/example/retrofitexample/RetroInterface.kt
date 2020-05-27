package com.example.retrofitexample


import retrofit2.Call
import retrofit2.http.*

data class OKResult(val result : String)

interface RetroInterface {
    @GET("/get")
    fun getUser(): Call<OKResult>

    @Headers(
        "Accept:application/json",
        "Content-Type:application/json;charset=UTF-8"
    )
    @POST("/post")
    fun postUser(@Body result: OKResult): Call<OKResult>
}
