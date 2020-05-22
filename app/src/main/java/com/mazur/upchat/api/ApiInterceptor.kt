package com.mazur.upchat.api

import com.mazur.upchat.helpers.Preferences
import com.orhanobut.hawk.Hawk
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ApiInterceptor : Interceptor {
    private val HEADER_NAME = "Authorization"
    private val HEADER_VALUE_PREFIX = "Bearer "

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val requestBuilder = original.newBuilder()

        if (Hawk.contains(Preferences.ACCESS_TOKEN)) {
            val token: String = Hawk.get(Preferences.ACCESS_TOKEN)
            requestBuilder.addHeader(HEADER_NAME, HEADER_VALUE_PREFIX + token)
        }

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}

