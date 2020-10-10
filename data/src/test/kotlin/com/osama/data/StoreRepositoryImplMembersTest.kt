package com.osama.data

import org.junit.Assert
import org.junit.Test

class StoreRepositoryImplMembersTest {
    private val subject = StoreRepositoryImpl()

    @Test
    fun `Test default constructor`() {
        Assert.assertTrue(
            "fail to construct StoreRepositoryImpl()",
            subject != null
        )
        Assert.assertTrue("fail to load dependency client", subject.client != null)
        Assert.assertTrue(
            "fail to load dependency responseResultMapper",
            subject.responseResultMapper != null
        )
    }
}