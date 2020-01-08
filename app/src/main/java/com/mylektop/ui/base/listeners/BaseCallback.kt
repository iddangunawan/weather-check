package com.mylektop.ui.base.listeners

import com.mylektop.data.remote.Error
import com.mylektop.data.remote.dto.NewsModel

/**
 * Created by ahmedeltaher on 3/22/17.
 */

interface BaseCallback {
    fun onSuccess(data: NewsModel)

    fun onFail(error : Error?)
}
