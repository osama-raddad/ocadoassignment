package com.osama.data

import com.osama.data.network.OcadoStoreClient
import com.osama.data.network.ResponseResultMapper
import com.osama.data_model.cluster.Clusters
import com.osama.repository.Result
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Test
import retrofit2.Response

internal class StoreRepositoryImplGetProductsTest {

    private val client: OcadoStoreClient = mockk("client")
    private val mapper: ResponseResultMapper = mockk("mapper")
    private val responseMock: Response<Clusters> = mockk("response")


    private val subject: StoreRepositoryImpl = StoreRepositoryImpl(client, mapper)


    @Test
    fun `Test getProducts with success status`() = runBlocking {
        val clustersMock: Clusters = mockk("clusters")
        val result = Result.Loaded(clustersMock)

        coEvery { client.getProducts() } returns responseMock
        coEvery { mapper.mapResponse(responseMock) } returns result

        Assert.assertTrue(
            "the requested result dose not match the mocked value",
            subject.getProducts() == result
        )

        confirmVerified(responseMock, clustersMock)
    }

    @Test
    fun `Test getProducts with success status and null mapped value`() = runBlocking {
        val result = Result.Loaded(null)

        coEvery { client.getProducts() } returns responseMock
        coEvery { mapper.mapResponse(responseMock) } returns result

        Assert.assertTrue(
            "the requested result dose not match the mocked value",
            subject.getProducts() == result
        )

        confirmVerified(responseMock)
    }

    @Test
    fun `Test getProducts with error status`() = runBlocking {
        val result = Result.Error(403, "Forbidden request code")

        coEvery { client.getProducts() } returns responseMock
        coEvery { mapper.mapResponse(responseMock) } returns result

        Assert.assertTrue(
            "the requested result dose not match the mocked value",
            subject.getProducts() == result
        )

        confirmVerified(responseMock)
    }


    @After
    fun after() {
        coVerifySequence {
            client.getProducts()
            mapper.mapResponse(responseMock)
        }

        confirmVerified(client, mapper)
    }
}