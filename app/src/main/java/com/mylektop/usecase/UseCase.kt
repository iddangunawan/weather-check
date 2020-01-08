package com.mylektop.usecase

import com.mylektop.data.remote.dto.NewsItem
import com.mylektop.ui.base.listeners.BaseCallback

/**
 * Created by ahmedeltaher on 3/22/17.
 */

interface UseCase {
    fun getNews(callback: BaseCallback)

    fun searchByTitle(news: List<NewsItem>, keyWord: String): NewsItem?
}
