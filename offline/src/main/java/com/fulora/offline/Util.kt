package com.fulora.offline

import android.content.Context
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Created by danielvilha on 28/04/21
 * https://github.com/danielvilha
 */
fun String.isValidEmail(): Boolean {
    return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isValidPassword(): Boolean {
    if (TextUtils.isEmpty(this)) {
        return false
    }

    val pattern: Pattern
    val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{4,8}$"
    pattern = Pattern.compile(passwordPattern)
    val matcher: Matcher = pattern.matcher(this)
    return matcher.matches()
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    if (imm.isActive)
        imm.hideSoftInputFromWindow(windowToken, 0)
}