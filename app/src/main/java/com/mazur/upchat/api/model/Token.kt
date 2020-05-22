package com.mazur.upchat.api.model

data class Token(val user_id: String, val access_token: String, val user_roles: Array<String>)