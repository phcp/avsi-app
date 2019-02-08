package com.avsi.android.login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View

import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.avsi.android.R
import com.avsi.android.util.extensions.isValidEmail
import com.avsi.android.items.ItemsActivity

import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginContract.View {
    private val presenter: LoginContract.Presenter by lazy { LoginPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        addListeners()
    }

    private fun addListeners() {
        emailSignInButton.setOnClickListener { attemptLogin() }
        passwordEditText.setOnEditorActionListener(TextView.OnEditorActionListener { _, id, _ ->
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                attemptLogin()
                return@OnEditorActionListener true
            }
            false
        })
    }

    private fun attemptLogin() {
        resetErrors()

        val emailStr = emailEditText.text.toString()
        val passwordStr = passwordEditText.text.toString()

        var isValidForSignIn = false
        var focusView: View? = null

        if (!emailStr.isValidEmail()) {
            emailEditText.error = getString(R.string.error_invalid_email)
            focusView = emailEditText
            isValidForSignIn = true
        }

        if (isValidForSignIn) {
            focusView?.requestFocus()
        } else {
            showProgress()
            presenter.singIn(emailStr, passwordStr, {
                startActivity(Intent(applicationContext, ItemsActivity::class.java))
                finish()
            }) { exception ->
                exception.message?.also { message ->
                    hideProgress()
                    Snackbar.make(loginForm, message, Snackbar.LENGTH_LONG)
                }
            }
        }
    }

    private fun hideProgress() {
        loginProgress.visibility = View.GONE
        loginForm.visibility = View.VISIBLE
    }

    private fun resetErrors() {
        emailEditText.error = null
        passwordEditText.error = null
    }

    private fun showProgress() {
        loginProgress.visibility = View.VISIBLE
        loginForm.visibility = View.GONE
    }



}
