package com.osama.domain.detailed_product

import org.junit.Assert.assertTrue
import org.junit.Test

class GetDetailedProductUseCaseImplMembersTest {
    private val subject = GetDetailedProductUseCaseImpl()

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
            "fail to load dependency resultProductToDetailedProductMapper",
            subject.resultProductToDetailedProductMapper != null
        )
    }
}