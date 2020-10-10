package com.osama.domain.categorized_products.util

import com.osama.data.StoreRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
internal object CategorizedProductModule {

    @Provides
    @JvmStatic
    @Reusable
    fun categorizedProductFactory(): CategorizedProductFactory = CategorizedProductFactory()

    @Provides
    @JvmStatic
    @CategorizedProductScope
    fun storeRepositoryImpl(): StoreRepositoryImpl = StoreRepositoryImpl()


    @Provides
    @JvmStatic
    @CategorizedProductScope
    fun resultClustersToCategorizedProductsMapper
                (categorizedProductFactory: CategorizedProductFactory)
            : ResultClustersToCategorizedProductsMapper =
        ResultClustersToCategorizedProductsMapper(categorizedProductFactory)

}