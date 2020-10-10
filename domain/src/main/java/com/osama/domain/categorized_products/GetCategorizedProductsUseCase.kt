package com.osama.domain.categorized_products

import com.osama.domain.entity.CategorizedProduct
import com.osama.domain.entity.Transaction

internal interface GetCategorizedProductsUseCase {
    suspend operator fun invoke(): Transaction<Map<String, List<CategorizedProduct>>?>
}