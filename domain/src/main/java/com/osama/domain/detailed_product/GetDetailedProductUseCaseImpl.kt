package com.osama.domain.detailed_product

import com.google.common.annotations.VisibleForTesting
import com.osama.data.StoreRepositoryImpl
import com.osama.domain.detailed_product.util.DaggerDetailedProductComponent
import com.osama.domain.detailed_product.util.ResultProductToDetailedProductMapper
import com.osama.domain.entity.DetailedProduct
import com.osama.domain.entity.Transaction
import javax.inject.Inject

class GetDetailedProductUseCaseImpl : GetDetailedProductUseCase {
    @Inject
    internal lateinit var storeRepositoryImpl: StoreRepositoryImpl

    @Inject
    internal lateinit var resultProductToDetailedProductMapper: ResultProductToDetailedProductMapper

    constructor() {
        DaggerDetailedProductComponent.create().inject(this)
    }

    @VisibleForTesting
    internal constructor(
        storeRepositoryImpl: StoreRepositoryImpl,
        resultProductToDetailedProductMapper: ResultProductToDetailedProductMapper
    ) {
        this.storeRepositoryImpl = storeRepositoryImpl
        this.resultProductToDetailedProductMapper = resultProductToDetailedProductMapper
    }

    override suspend operator fun invoke(id: Int): Transaction<DetailedProduct?> =
        resultProductToDetailedProductMapper.map(storeRepositoryImpl.getProduct(id))
}