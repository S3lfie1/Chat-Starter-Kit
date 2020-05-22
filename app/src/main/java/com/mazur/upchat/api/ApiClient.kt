package com.mazur.upchat.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {
    companion object {
        private var address: String = ""

        private fun createClient(address: String): ApiService {
            val okHttpClient = OkHttpClient.Builder()
                    .readTimeout(10, TimeUnit.SECONDS)
                    .connectTimeout(10, TimeUnit.SECONDS)
//                    .authenticator(TokenAuthenticator())
                    .addInterceptor(ApiInterceptor())
                    .build()

            val gson = GsonBuilder()
                    .setDateFormat("yyyy-MM-dd")
                    .setLenient()
                    .create()

            val retrofit = Retrofit.Builder()
                    .baseUrl(address)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build()

            return retrofit.create(ApiService::class.java)
        }

//        fun postData(address: String): ApiService {
//            if (instance == null || address != this.address) {
//                instance = createClient(address)
//                this.address = address
//            }
//
//            return instance!!
//        }
//
//        fun getData(address: String) {
//            if (instance == null) {
//                instance = createClient(address)
//            }
//
//            return instance!!
//        }
    }
}