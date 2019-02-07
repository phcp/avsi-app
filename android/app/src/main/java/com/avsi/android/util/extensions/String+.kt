package com.avsi.android.util.extensions

/**
 * @author Victor Oliveira
 */
fun String.isValidEmail(): Boolean {
    return this.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}