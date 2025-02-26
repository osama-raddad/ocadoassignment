package com.osama.domain.detailed_product.util

import com.osama.dto.product.Product
import com.osama.domain.entity.DetailedProduct

internal class DetailedProductFactory {
    operator fun invoke(product: Product) = product.run {
        DetailedProduct(
            price,
            title,
            imageUrl,
            description,
            allergyInformation
        )
    }
}