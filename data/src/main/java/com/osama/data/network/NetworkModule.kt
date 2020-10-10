package com.osama.data.network

import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
internal object NetworkModule {
    @Provides
    @JvmStatic
    @Reusable
    fun baseUrl(): String = "https://my-json-server.typicode.com/ocadotechnology/mobile-challenge/"

    @Provides
    @JvmStatic
    fun httpClientBuilder() = OkHttpClient.Builder()

    @Provides
    @JvmStatic
    @Reusable
    fun httpClient(httpClientBuilder: OkHttpClient.Builder): OkHttpClient =
        httpClientBuilder.build()

    @Provides
    @JvmStatic
    @Reusable
    fun gsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @JvmStatic
    fun retrofitBuilder(baseUrl: String, gsonConverterFactory: GsonConverterFactory) =
        Retrofit.Builder().apply {
            baseUrl(baseUrl)
            addConverterFactory(gsonConverterFactory)
        }

    @Provides
    @JvmStatic
    @Reusable
    fun retrofit(retrofitBuilder: Retrofit.Builder, httpClient: OkHttpClient): Retrofit =
        retrofitBuilder
            .client(httpClient)
            .build()

    @Provides
    @JvmStatic
    @StoreRepositoryScope
    fun client(retrofit: Retrofit): OcadoStoreClient = retrofit.create(OcadoStoreClient::class.java)

    @Provides
    @JvmStatic
    @StoreRepositoryScope
    fun responseMapper() = ResponseResultMapper()
}