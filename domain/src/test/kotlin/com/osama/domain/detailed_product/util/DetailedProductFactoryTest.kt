package com.osama.domain.detailed_product.util

import com.osama.data_model.product.Product
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verifySequence
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

internal class DetailedProductFactoryTest {
    private val subject = DetailedProductFactory()

    @Test
    fun invoke() {
        val price = 1.35
        val title = "Ocado Baby Leeks"
        val imageUrl = "https://mobile.ocado.com/360x360.jpg"
        val description = "onion flavour."
        val allergyInformation = "No allergens"

        val productMock: Product = mockk()

        every { productMock.price } returns price
        every { productMock.title } returns title
        every { productMock.imageUrl } returns imageUrl
        every { productMock.description } returns description
        every { productMock.allergyInformation } returns allergyInformation

        val detailedProduct = subject(productMock)

        assertTrue(price == detailedProduct.price)
        assertEquals(title, detailedProduct.title)
        assertEquals(imageUrl, detailedProduct.imageUrl)
        assertEquals(description, detailedProduct.description)
        assertEquals(allergyInformation, detailedProduct.allergyInformation)

        verifySequence {
            productMock.price
            productMock.title
            productMock.imageUrl
            productMock.description
            productMock.allergyInformation
        }

        confirmVerified(productMock)
    }
}