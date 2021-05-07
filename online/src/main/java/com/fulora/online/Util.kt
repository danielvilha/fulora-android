package com.fulora.online

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Created by danielvilha on 29/04/21
 * https://github.com/danielvilha
 */
fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    if (imm.isActive)
        imm.hideSoftInputFromWindow(windowToken, 0)
}