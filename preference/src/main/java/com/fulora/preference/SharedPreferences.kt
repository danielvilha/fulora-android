package com.fulora.preference

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

/**
 * Created by danielvilha on 28/04/21
 * https://github.com/danielvilha
 */
fun insertUser(context: Context, displayName: String?, email: String?, uid: String?) {
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("FuloraSharedPref", MODE_PRIVATE)

    // Creating an Editor object to edit(write to the file)
    val edit = sharedPreferences.edit()
    edit.putString("USER_NAME", displayName)
    edit.putString("USER_EMAIL", email)
    edit.putString("USER_UID", uid)
    edit.apply()
}

fun getUserName(context: Context): String {
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("FuloraSharedPref", MODE_PRIVATE)

    return sharedPreferences.getString("USER_NAME", "").toString()
}

fun getUserEmail(context: Context): String {
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("FuloraSharedPref", MODE_PRIVATE)

    return sharedPreferences.getString("USER_EMAIL", "").toString()
}

fun getUserUid(context: Context): String {
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("FuloraSharedPref", MODE_PRIVATE)

    return sharedPreferences.getString("USER_UID", "").toString()
}

fun cleanUser(context: Context) {
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("FuloraSharedPref", MODE_PRIVATE)

    // Creating an Editor object to edit(write to the file)
    val edit = sharedPreferences.edit()
    edit.putString("USER_NAME", "")
    edit.putString("USER_EMAIL", "")
    edit.putString("USER_UID", "")
    edit.apply()
}