package com.mylektop.ui.component.details

import androidx.lifecycle.MutableLiveData
import com.mylektop.data.remote.dto.NewsItem
import com.mylektop.ui.base.BaseViewModel
import javax.inject.Inject

/**
 * Created by AhmedEltaher on 11/12/16.
 */

class DetailsViewModel @Inject
constructor() : BaseViewModel(){
    var newsItem: MutableLiveData<NewsItem>? = MutableLiveData()
}
