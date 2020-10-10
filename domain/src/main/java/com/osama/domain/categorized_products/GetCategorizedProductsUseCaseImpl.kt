package com.osama.domain.categorized_products

import com.google.common.annotations.VisibleForTesting
import com.osama.data.StoreRepositoryImpl
import com.osama.domain.categorized_products.util.DaggerCategorizedProductComponent
import com.osama.domain.categorized_products.util.ResultClustersToCategorizedProductsMapper
import com.osama.domain.entity.CategorizedProduct
import com.osama.domain.entity.Transaction
import javax.inject.Inject

class GetCategorizedProductsUseCaseImpl : GetCategorizedProductsUseCase {
    @Inject
    internal lateinit var storeRepositoryImpl: StoreRepositoryImpl

    @Inject
    internal lateinit var resultClustersToCategorizedProductsMapper
            : ResultClustersToCategorizedProductsMapper

    constructor() {
        DaggerCategorizedProductComponent.create().inject(this)
    }

    @VisibleForTesting
    internal constructor(
        storeRepositoryImpl: StoreRepositoryImpl,
        resultClustersToCategorizedProductsMapper: ResultClustersToCategorizedProductsMapper) {
        this.storeRepositoryImpl = storeRepositoryImpl
        this.resultClustersToCategorizedProductsMapper = resultClustersToCategorizedProductsMapper
    }

    override suspend operator fun invoke(): Transaction<Map<String, List<CategorizedProduct>>?> =
        resultClustersToCategorizedProductsMapper.map(storeRepositoryImpl.getProducts())
}