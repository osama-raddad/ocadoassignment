package com.osama.repository

import com.osama.data_model.cluster.Clusters
import com.osama.data_model.product.Product

public interface StoreRepository {
    suspend fun getProducts(): Result<Clusters?>
    suspend fun getProduct(id:Int): Result<Product?>
}