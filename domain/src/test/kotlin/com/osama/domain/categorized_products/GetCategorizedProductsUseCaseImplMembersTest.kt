package com.osama.domain.categorized_products

import org.junit.Assert.assertTrue
import org.junit.Test

class GetCategorizedProductsUseCaseImplMembersTest{
    private val subject = GetCategorizedProductsUseCaseImpl()

    @Test
    fun `Test default constructor`() {
        assertTrue(
            "fail to construct GetDetailedProductUseCaseImpl()",
            subject != null
        )
        assertTrue(
            "fail to load dependency storeRepositoryImpl",
            subject.storeRepositoryImpl != null
        )
        assertTrue(
            "fail to load dependency resultClustersToCategorizedProductsMapper",
            subject.resultClustersToCategorizedProductsMapper != null
        )
    }
}