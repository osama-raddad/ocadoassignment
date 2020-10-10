package com.osama.data.network

import com.osama.dto.cluster.Clusters
import com.osama.dto.product.Product
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface OcadoStoreClient {
    @GET("products")
    suspend fun getProducts(): Response<Clusters>

    @GET("product/{product_id}")
    suspend fun getProductByID(@Path("product_id") id:Int): Response<Product>
}