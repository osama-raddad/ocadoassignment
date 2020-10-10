package com.osama.data

import com.osama.data.network.OcadoStoreClient
import com.osama.data.network.ResponseResultMapper
import com.osama.data_model.product.Product
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

internal class StoreRepositoryImplGetProductTest {

    private val id = 12

    private val client: OcadoStoreClient = mockk("client")
    private val mapper: ResponseResultMapper = mockk("mapper")
    private val responseMock: Response<Product> = mockk("response")


    private val subject: StoreRepositoryImpl = StoreRepositoryImpl(client, mapper)


    @Test
    fun `Test getProduct with success status`() = runBlocking {
        val productMock: Product = mockk("clusters")
        val result = Result.Loaded(productMock)

        coEvery { client.getProductByID(id) } returns responseMock
        coEvery { mapper.mapResponse(responseMock) } returns result

        Assert.assertTrue(
            "the requested result dose not match the mocked value == Result.Loaded#Product",
            subject.getProduct(id) == result
        )

        confirmVerified(productMock)
    }

    @Test
    fun `Test getProducts with success status and null mapped value`() = runBlocking {
        val productMock: Product = mockk("clusters")
        val result = Result.Loaded(null)

        coEvery { client.getProductByID(id) } returns responseMock
        coEvery { mapper.mapResponse(responseMock) } returns result

        Assert.assertTrue(
            "the requested result dose not match the mocked value == Result.Loaded#null",
            subject.getProduct(id) == result
        )

        confirmVerified(productMock)
    }

    @Test
    fun `Test getProducts with error status`() = runBlocking {
        val productMock: Product = mockk("clusters")
        val result = Result.Error(403, "Forbidden request code")

        coEvery { client.getProductByID(id) } returns responseMock
        coEvery { mapper.mapResponse(responseMock) } returns result

        Assert.assertTrue(
            "the requested result dose not match the mocked value " +
                    "== Result.Error#{403,\"Forbidden request code\"}",
            subject.getProduct(id) == result
        )

        confirmVerified(productMock)
    }


    @After
    fun after() {
        coVerifySequence {
            client.getProductByID(id)
            mapper.mapResponse(responseMock)
        }

        confirmVerified(client, mapper)
    }
}