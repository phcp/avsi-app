package com.avsi.android.login

import com.avsi.android.BasePresenter
import com.avsi.android.BaseView

/**
 * @author Victor Oliveira
 */
interface LoginContract {

    interface View : BaseView<Presenter>

    interface Presenter : BasePresenter
}
