package com.mazur.upchat.api

import com.mazur.upchat.api.model.Credentials
import com.mazur.upchat.api.model.Token
import com.mazur.upchat.model.User
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/users.json")
    fun createUser(@Body user: User) : Single<Response<User>>

//    @POST("auth/token")
//    fun loginUser(@Body credentials: Credentials) : Single<Response<Token>>
}