package com.mylektop.ui.component.news

import androidx.lifecycle.MutableLiveData
import com.mylektop.data.remote.Error
import com.mylektop.data.remote.dto.NewsItem
import com.mylektop.data.remote.dto.NewsModel
import com.mylektop.ui.base.BaseViewModel
import com.mylektop.ui.base.listeners.BaseCallback
import com.mylektop.usecase.NewsUseCase
import javax.inject.Inject

/**
 * Created by AhmedEltaher on 5/12/2016
 */

class NewsListViewModel @Inject
constructor(newsDataUseCase: NewsUseCase) : BaseViewModel() {

    private var newsUseCase: NewsUseCase = newsDataUseCase
    var newsModel: MutableLiveData<NewsModel> = MutableLiveData()
    var newsSearchFound: MutableLiveData<NewsItem> = MutableLiveData()
    var noSearchFound: MutableLiveData<Boolean> = MutableLiveData()
    var noInterNetConnection: MutableLiveData<Boolean> = MutableLiveData()
    var showError: MutableLiveData<Error> = MutableLiveData()

    fun getNews() {
        newsUseCase.getNews(callback)
    }

    private val callback = object : BaseCallback {

        override fun onSuccess(data: NewsModel) {
            newsModel.postValue(data)
        }

        override fun onFail(error: Error?) {
            if (error?.code == -1) {
                noInterNetConnection.postValue(true)
            } else {
                showError.postValue(error)
            }

        }
    }

     fun onSearchClick(newsTitle: String) {
        val news = newsModel.value?.newsItems
        if (newsTitle.isNotEmpty() && !news.isNullOrEmpty()) {
            newsSearchFound.value = newsUseCase.searchByTitle(news, newsTitle)
            noSearchFound.value = (newsSearchFound.value == null)
        } else {
            noSearchFound.value = true
        }
    }
}
