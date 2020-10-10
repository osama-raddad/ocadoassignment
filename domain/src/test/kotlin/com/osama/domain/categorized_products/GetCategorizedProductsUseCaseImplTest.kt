package com.osama.domain.categorized_products

import com.osama.data.StoreRepositoryImpl
import com.osama.data_model.cluster.Clusters
import com.osama.domain.categorized_products.util.ResultClustersToCategorizedProductsMapper
import com.osama.domain.entity.CategorizedProduct
import com.osama.domain.entity.Transaction
import com.osama.repository.Result
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetCategorizedProductsUseCaseImplTest {

    private val storeRepositoryImplMock: StoreRepositoryImpl = mockk()
    private val resultClustersToCategorizedProductsMapper: ResultClustersToCategorizedProductsMapper =
        mockk()


    private val subject: GetCategorizedProductsUseCaseImpl = GetCategorizedProductsUseCaseImpl(
        storeRepositoryImplMock,
        resultClustersToCategorizedProductsMapper
    )

    @Test
    fun `Test GetCategorizedProductsUseCase with a given result`() = runBlocking {
        val resultMock: Result<Clusters?> = mockk()
        val transactionMockk: Transaction<Map<String, List<CategorizedProduct>>?> = mockk()

        coEvery { storeRepositoryImplMock.getProducts() } returns resultMock
        coEvery { resultClustersToCategorizedProductsMapper.map(resultMock) } returns transactionMockk

        val detailedProduct = subject()

        assertEquals(transactionMockk, detailedProduct)

        coVerifySequence {
            storeRepositoryImplMock.getProducts()
            resultClustersToCategorizedProductsMapper.map(resultMock)
            transactionMockk == detailedProduct
        }

        confirmVerified(
            storeRepositoryImplMock,
            resultClustersToCategorizedProductsMapper,
            resultMock,
            transactionMockk
        )
    }
}