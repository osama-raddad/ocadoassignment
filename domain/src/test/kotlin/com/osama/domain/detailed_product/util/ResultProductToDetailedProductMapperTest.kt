package com.osama.domain.detailed_product.util

import com.osama.data_model.product.Product
import com.osama.domain.entity.DetailedProduct
import com.osama.domain.entity.Transaction
import com.osama.repository.Result
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verifySequence
import org.junit.Assert.assertEquals
import org.junit.Test

class ResultProductToDetailedProductMapperTest {
    private val factoryMock: DetailedProductFactory = mockk()

    private val subject = ResultProductToDetailedProductMapper(factoryMock)

    @Test
    fun `Test mapping ResultProduct? to TransactionDetailedProduct? Loaded no nulls`() {
        val resultMock: Result.Loaded<Product?> = mockk()
        val productMock: Product = mockk()
        val detailedProduct: DetailedProduct = mockk()

        every { resultMock.result } returns productMock
        every { factoryMock.invoke(productMock) } returns detailedProduct

        val detailedProductTransaction: Transaction<DetailedProduct?> = subject.map(resultMock)

        assertEquals(Transaction.Ready(detailedProduct), detailedProductTransaction)

        verifySequence {
            resultMock.result
            factoryMock.invoke(productMock)
            detailedProduct ==detailedProduct
        }

        confirmVerified(factoryMock, resultMock, productMock, detailedProduct)
    }

    @Test
    fun `Test mapping ResultProduct? to TransactionDetailedProduct? Loaded with nulls`() {
        val resultMock: Result.Loaded<Product?> = mockk()
        val detailedProduct: DetailedProduct = mockk()

        every { resultMock.result } returns null

        val detailedProductTransaction: Transaction<DetailedProduct?> = subject.map(resultMock)

        assertEquals(Transaction.Ready(null), detailedProductTransaction)

        verifySequence {
            resultMock.result
        }

        confirmVerified(factoryMock, resultMock, detailedProduct)
    }

    @Test
    fun `Test mapping ResultProduct? to TransactionDetailedProduct? Error`() {
        val resultMock: Result.Error = mockk()
        val transactionError=Transaction.Error(404,"404")

        every { resultMock.code } returns transactionError.code
        every { resultMock.text } returns transactionError.text

        val detailedProductTransaction: Transaction<DetailedProduct?> = subject.map(resultMock)

        assertEquals(transactionError, detailedProductTransaction)

        verifySequence {
            resultMock.code
            resultMock.text
        }

        confirmVerified( resultMock)
    }
}