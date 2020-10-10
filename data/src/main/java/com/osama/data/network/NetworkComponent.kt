package com.osama.data.network

import com.osama.data.StoreRepositoryImpl
import dagger.Component
import javax.inject.Scope

@StoreRepositoryScope
@Component(modules = [NetworkModule::class])
interface  NetworkComponent {
    fun inject(target: StoreRepositoryImpl)
}
@Scope
@Retention(AnnotationRetention.RUNTIME)
internal annotation class StoreRepositoryScope
