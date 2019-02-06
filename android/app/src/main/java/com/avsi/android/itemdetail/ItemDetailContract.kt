package com.avsi.android.itemdetail

import com.avsi.android.BasePresenter
import com.avsi.android.BaseView

/**
 * @author Victor Oliveira
 */
interface ItemDetailContract {

    interface View : BaseView<Presenter>

    interface Presenter : BasePresenter
}
