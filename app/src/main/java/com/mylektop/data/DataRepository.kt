package com.mylektop.data

import com.mylektop.data.local.LocalRepository
import com.mylektop.data.remote.RemoteRepository
import com.mylektop.data.remote.Data
import javax.inject.Inject


/**
 * Created by AhmedEltaher on 5/12/2016
 */

class DataRepository @Inject
constructor(private val remoteRepository: RemoteRepository, private val localRepository: LocalRepository) : DataSource {

    override  fun requestNews(): Data? {
        return remoteRepository.requestNews()
    }
}
