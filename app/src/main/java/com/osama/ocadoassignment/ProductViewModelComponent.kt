package com.osama.ocadoassignment

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ProductViewModelModule::class])
internal interface ProductViewModelComponent {
    fun inject(viewModel: ProductViewModel)
}