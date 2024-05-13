package com.example.postman.models

import com.google.firebase.firestore.Exclude

data class User(
    var disabled : Boolean,
    var username : String? = "",
    var email : String? = "",
    var password : String? = ""
) {

    fun toMap(): Map<String, Any?>{
        return mapOf(
            "disabled" to disabled,
            "username" to username,
            "email" to email,
            "password" to password )
    }
}