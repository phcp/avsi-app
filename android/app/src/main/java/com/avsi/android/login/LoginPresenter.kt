package com.avsi.android.login

/**
 * @author Victor Oliveira
 */
class LoginPresenter(private val view: LoginContract.View): LoginContract.Presenter {

    override fun singIn(email: String, password: String, onSuccess: () -> Unit, onError: (Exception) -> Unit) {
        onSuccess()
    }
}