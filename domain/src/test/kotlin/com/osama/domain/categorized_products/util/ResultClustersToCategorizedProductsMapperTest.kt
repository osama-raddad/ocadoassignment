package com.osama.domain.categorized_products.util

import com.osama.data_model.cluster.Cluster
import com.osama.data_model.cluster.Clusters
import com.osama.data_model.cluster.Item
import com.osama.domain.entity.CategorizedProduct
import com.osama.domain.entity.DetailedProduct
import com.osama.domain.entity.Transaction
import io.mockk.mockk
import org.junit.Test
import com.osama.repository.Result
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.verifySequence
import org.junit.Assert


internal class ResultClustersToCategorizedProductsMapperTest {
    private val categorizedProductFactory: CategorizedProductFactory = mockk()

    private val subject = ResultClustersToCategorizedProductsMapper(categorizedProductFactory)

    @Test
    fun `Test mapping ResultClusters? to TransactionMapStringToListCategorizedProduct? Loaded no nulls`() {
        val tag = "food"

        val resultMock: Result.Loaded<Clusters?> = mockk()
        val clustersMock: Clusters = mockk()
        val clusterMock: Cluster = mockk()
        val clusterListMock = listOf(clusterMock)
        val itemMock: Item = mockk()
        val itemListMock = listOf(itemMock)
        val categorizedProductMock: CategorizedProduct = mockk()
        val categorizedProductTransaction: Transaction<Map<String, List<CategorizedProduct>>?> =
            Transaction.Ready(mapOf(tag to listOf(categorizedProductMock)))

        every { resultMock.result } returns clustersMock
        every { clustersMock.clusters } returns clusterListMock
        every { clusterMock.tag } returns tag
        every { clusterMock.items } returns itemListMock
        every { categorizedProductFactory.invoke(itemMock) } returns categorizedProductMock

        Assert.assertEquals(categorizedProductTransaction, subject.map(resultMock))

        verifySequence {
            resultMock.result
            clustersMock.clusters
            clusterMock.tag
            clusterMock.items
            categorizedProductFactory.invoke(itemMock)
            categorizedProductMock == categorizedProductMock
        }

        confirmVerified(
            resultMock,
            clustersMock,
            clusterMock,
            itemMock,
            categorizedProductMock
        )
    }

    @Test
    fun `Test mapping ResultClusters? to TransactionMapStringToListCategorizedProduct? Loaded with nulls`() {
        val resultMock: Result.Loaded<Clusters?> = mockk()

        val categorizedProductMock: CategorizedProduct = mockk()
        val categorizedProductTransaction: Transaction<Map<String, List<CategorizedProduct>>?> =
            Transaction.Ready(null)

        every { resultMock.result } returns null

        Assert.assertEquals(categorizedProductTransaction, subject.map(resultMock))

        verifySequence {
            resultMock.result
        }

        confirmVerified(
            resultMock,
            categorizedProductMock
        )
    }

    @Test
    fun `Test mapping ResultClusters? to TransactionMapStringToListCategorizedProduct? Error`() {
        val resultMock: Result.Error = mockk()
        val transactionError=Transaction.Error(404,"404")

        every { resultMock.code } returns transactionError.code
        every { resultMock.text } returns transactionError.text

        val detailedProductTransaction: Transaction<Map<String, List<CategorizedProduct>>?>
                = subject.map(resultMock)

        Assert.assertEquals(transactionError, detailedProductTransaction)

        verifySequence {
            resultMock.code
            resultMock.text
        }

        confirmVerified( resultMock)
    }
}