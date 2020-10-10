package com.osama.data

import com.google.common.annotations.VisibleForTesting
import com.osama.data.network.DaggerNetworkComponent
import com.osama.data.network.OcadoStoreClient
import com.osama.data.network.ResponseResultMapper
import com.osama.dto.cluster.Clusters
import com.osama.dto.product.Product
import com.osama.repository.Result
import com.osama.repository.StoreRepository
import javax.inject.Inject

class StoreRepositoryImpl : StoreRepository {

    @Inject
    @VisibleForTesting
    internal lateinit var client: OcadoStoreClient

    @Inject
    @VisibleForTesting
    internal lateinit var responseResultMapper: ResponseResultMapper


    constructor() {
        DaggerNetworkComponent.create().inject(this)
    }

    @VisibleForTesting
    internal constructor(client: OcadoStoreClient, responseResultMapper: ResponseResultMapper) {
        this.client = client
        this.responseResultMapper = responseResultMapper
    }

    override suspend fun getProducts(): Result<Clusters?>
            = responseResultMapper.mapResponse(client.getProducts())

    override suspend fun getProduct(id: Int):Result<Product?>
            = responseResultMapper.mapResponse(client.getProductByID(id))
}
