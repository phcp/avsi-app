package com.avsi.android.items

import com.avsi.android.BasePresenter
import com.avsi.android.BaseView

/**
 * @author Victor Oliveira
 */
interface ItemsContract {

    interface View : BaseView<Presenter>

    interface Presenter : BasePresenter
}
