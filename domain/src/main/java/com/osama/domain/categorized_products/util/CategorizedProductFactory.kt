package com.osama.domain.categorized_products.util

import com.osama.data_model.cluster.Item
import com.osama.domain.entity.CategorizedProduct

internal class CategorizedProductFactory {
    operator fun invoke(item: Item): CategorizedProduct = item.run {
        CategorizedProduct(id, price, title, imageUrl)
    }
}
