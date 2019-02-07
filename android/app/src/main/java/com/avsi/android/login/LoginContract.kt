package com.avsi.android.login

/**
 * @author Victor Oliveira
 */
interface LoginContract {

    interface View

    interface Presenter {
        fun singIn(email: String, password: String, onSuccess: () -> Unit, onError: (Exception) -> Unit)
    }
}
