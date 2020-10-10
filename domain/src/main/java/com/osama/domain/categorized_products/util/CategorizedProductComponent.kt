package com.osama.domain.categorized_products.util

import com.osama.domain.categorized_products.GetCategorizedProductsUseCaseImpl
import dagger.Component
import javax.inject.Scope

@CategorizedProductScope
@Component(modules = [CategorizedProductModule::class])
interface CategorizedProductComponent {
    fun inject(target: GetCategorizedProductsUseCaseImpl)
}
@Scope
@Retention(AnnotationRetention.RUNTIME)
internal annotation class CategorizedProductScope