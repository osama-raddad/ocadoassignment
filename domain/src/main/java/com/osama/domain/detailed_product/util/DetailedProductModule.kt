package com.osama.domain.detailed_product.util

import com.osama.data.StoreRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
internal object DetailedProductModule {
    @Provides
    @JvmStatic
    @Reusable
    fun detailedProductFactory(): DetailedProductFactory = DetailedProductFactory()

    @Provides
    @JvmStatic
    @DetailedProductScope
    fun storeRepositoryImpl(): StoreRepositoryImpl = StoreRepositoryImpl()

    @Provides
    @JvmStatic
    @DetailedProductScope
    fun resultProductToDetailedProductMapper(detailedProductFactory: DetailedProductFactory): ResultProductToDetailedProductMapper =
        ResultProductToDetailedProductMapper(detailedProductFactory)
}