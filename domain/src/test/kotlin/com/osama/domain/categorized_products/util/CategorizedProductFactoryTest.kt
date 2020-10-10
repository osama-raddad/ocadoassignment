package com.osama.domain.categorized_products.util

import com.osama.data_model.cluster.Item
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verifySequence
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

internal class CategorizedProductFactoryTest {
    private val subject = CategorizedProductFactory()

    @Test
    operator fun invoke() {
        val id = 12
        val price = 0.90
        val title = "Ocado Sunflower Seeds"
        val imageUrl = "https://mobile.ocado.com/240x240.jpg"

        val itemMock: Item = mockk()

        every { itemMock.id } returns id
        every { itemMock.price } returns price
        every { itemMock.title } returns title
        every { itemMock.imageUrl } returns imageUrl

        val categorizedProduct = subject(itemMock)

        assertTrue(id == categorizedProduct.id)
        assertTrue(price == categorizedProduct.price)
        assertEquals(title, categorizedProduct.title)
        assertEquals(imageUrl, categorizedProduct.imageUrl)

        verifySequence {
            itemMock.id
            itemMock.price
            itemMock.title
            itemMock.imageUrl
        }

        confirmVerified(itemMock)
    }
}