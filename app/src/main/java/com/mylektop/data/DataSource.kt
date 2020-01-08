package com.mylektop.data

import com.mylektop.data.remote.Data

/**
 * Created by ahmedeltaher on 3/23/17.
 */

internal interface DataSource {
    fun requestNews(): Data?
}
