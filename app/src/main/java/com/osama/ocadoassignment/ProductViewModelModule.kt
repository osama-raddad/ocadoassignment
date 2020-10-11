package com.osama.ocadoassignment

import com.osama.domain.categorized_products.GetCategorizedProductsUseCaseImpl
import com.osama.domain.detailed_product.GetDetailedProductUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
internal object ProductViewModelModule {

    @Provides
    @JvmStatic
    @Reusable
    fun getCategorizedProductsUseCaseImpl(): GetCategorizedProductsUseCaseImpl =
        GetCategorizedProductsUseCaseImpl()


    @Provides
    @JvmStatic
    @Reusable
    fun getDetailedProductUseCaseImpl(): GetDetailedProductUseCaseImpl =
        GetDetailedProductUseCaseImpl()
}