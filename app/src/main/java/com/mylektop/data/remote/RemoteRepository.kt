package com.mylektop.data.remote

import com.mylektop.App
import com.mylektop.data.remote.Error.Companion.NETWORK_ERROR
import com.mylektop.data.remote.service.NewsService
import com.mylektop.utils.Constants
import com.mylektop.utils.Constants.INSTANCE.ERROR_UNDEFINED
import com.mylektop.utils.Network.Utils.isConnected
import retrofit2.Call
import java.io.IOException
import javax.inject.Inject


/**
 * Created by AhmedEltaher on 5/12/2016
 */

class RemoteRepository @Inject
constructor(private val serviceGenerator: ServiceGenerator) : RemoteSource {

    override fun requestNews(): Data? {
        return if (!isConnected(App.context)) {
            Data(Error(code = -1, description = NETWORK_ERROR))
        } else {
            val newsService = serviceGenerator.createService(NewsService::class.java, Constants.BASE_URL)
            processCall(newsService.fetchNews(), false)
        }
    }

    private fun processCall(call: Call<*>, isVoid: Boolean): Data {
        if (!isConnected(App.context)) {
            return Data(Error())
        }
        try {
            val response = call.execute()
                    ?: return Data(Error(NETWORK_ERROR, ERROR_UNDEFINED))
            val responseCode = response.code()
            /**
             * isVoid is for APIs which reply only with code without any body, such as some Apis
             * reply with 200 or 401....
             */
            return if (response.isSuccessful) {
                val apiResponse: Any? = if (isVoid) null else response.body()
                Data(responseCode, apiResponse)
            } else {
                val serviceError = Error(response.message(), responseCode)
                Data(serviceError)
            }
        } catch (e: IOException) {
            return Data(Error(NETWORK_ERROR, ERROR_UNDEFINED))
        }

    }
}
