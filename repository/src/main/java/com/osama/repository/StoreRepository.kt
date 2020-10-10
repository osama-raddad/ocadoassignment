package com.osama.repository

import com.osama.dto.cluster.Clusters
import com.osama.dto.product.Product

public interface StoreRepository {
    suspend fun getProducts(): Result<Clusters?>
    suspend fun getProduct(id:Int): Result<Product?>
}