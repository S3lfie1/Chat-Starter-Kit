package com.mazur.upchat.api

import com.mazur.upchat.api.model.Credentials
import com.mazur.upchat.helpers.Preferences
import com.orhanobut.hawk.Hawk
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import java.io.IOException

class TokenAuthenticator : Authenticator {
    @Throws(IOException::class)
    override fun authenticate(route: Route?, response: Response): Request? {

//        if (response.code == 401) {
//            Hawk.remove(Preferences.ACCESS_TOKEN)
//
//            val email: String = Hawk.get(Preferences.USER_EMAIL)
//            val password: String = Hawk.get(Preferences.USER_PASSWORD)
//
//            val refresh = ApiClient.getClient().loginUser(Credentials(email, password)).blockingGet()
//
//            if (refresh.body() != null && refresh.code() == 200) {
//                val token = refresh.body()?.access_token
//                token?.let {
//                    Hawk.put(Preferences.ACCESS_TOKEN, it)
//                    return response.request.newBuilder()
//                            .header("Authorization", "Bearer $it")
//                            .build()
//                }
//            }
//        }

        return null
    }
}
