package com.osama.domain.detailed_product

import com.osama.data.StoreRepositoryImpl
import com.osama.data_model.product.Product
import com.osama.domain.detailed_product.util.ResultProductToDetailedProductMapper
import com.osama.domain.entity.DetailedProduct
import com.osama.domain.entity.Transaction
import com.osama.repository.Result
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetDetailedProductUseCaseImplTest {

    private val storeRepositoryImplMock: StoreRepositoryImpl = mockk()
    private val resultProductToDetailedProductMapper: ResultProductToDetailedProductMapper = mockk()


    private val subject: GetDetailedProductUseCaseImpl =
        GetDetailedProductUseCaseImpl(storeRepositoryImplMock, resultProductToDetailedProductMapper)

    @Test
    fun `Test GetDetailedProductUseCase with a given result`() = runBlocking {
        val id = 12
        val resultMock: Result<Product?> = mockk()
        val transactionMockk: Transaction<DetailedProduct?> = mockk()

        coEvery { storeRepositoryImplMock.getProduct(id) } returns resultMock
        coEvery { resultProductToDetailedProductMapper.map(resultMock) } returns transactionMockk

        val detailedProduct = subject(id)

        assertEquals(transactionMockk, detailedProduct)

        coVerifySequence {
            storeRepositoryImplMock.getProduct(id)
            resultProductToDetailedProductMapper.map(resultMock)
            transactionMockk == detailedProduct
        }

        confirmVerified(
            storeRepositoryImplMock,
            resultProductToDetailedProductMapper,
            resultMock,
            transactionMockk
        )
    }



}