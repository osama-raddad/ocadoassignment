package com.osama.domain.detailed_product.util

import com.osama.data_model.product.Product
import com.osama.domain.entity.DetailedProduct
import com.osama.domain.entity.Transaction
import com.osama.repository.Result

internal class ResultProductToDetailedProductMapper(private val detailedProductFactory: DetailedProductFactory) {
    fun map(productResult: Result<Product?>): Transaction<DetailedProduct?> {
        return when (productResult) {
            is Result.Loaded -> buildTransactionFromNullableResult(productResult.result)
            is Result.Error -> {
                Transaction.Error(productResult.code, productResult.text)
            }
        }
    }

    private fun buildTransactionFromNullableResult(result: Product?) = when (result == null) {
        true -> Transaction.Ready(null)
        false -> Transaction.Ready(detailedProductFactory(result))
    }


}
