package com.osama.domain.detailed_product.util

import com.osama.domain.detailed_product.GetDetailedProductUseCaseImpl
import dagger.Component
import javax.inject.Scope

@DetailedProductScope
@Component(modules = [DetailedProductModule::class])
interface DetailedProductComponent {
    fun inject(target: GetDetailedProductUseCaseImpl)
}
@Scope
@Retention(AnnotationRetention.RUNTIME)
internal annotation class DetailedProductScope