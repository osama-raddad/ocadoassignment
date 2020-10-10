package com.osama.domain.detailed_product

import com.osama.domain.entity.DetailedProduct
import com.osama.domain.entity.Transaction

internal interface GetDetailedProductUseCase {
    suspend fun invoke(id: Int): Transaction<DetailedProduct?>
}