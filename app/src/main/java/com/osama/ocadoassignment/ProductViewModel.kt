package com.osama.ocadoassignment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.osama.domain.categorized_products.GetCategorizedProductsUseCaseImpl
import com.osama.domain.detailed_product.GetDetailedProductUseCaseImpl
import com.osama.domain.entity.CategorizedProduct
import com.osama.domain.entity.DetailedProduct
import com.osama.domain.entity.Transaction
import javax.inject.Inject

internal class ProductViewModel : ViewModel() {
    @Inject
    internal lateinit var getCategorizedProductsUseCaseImpl: GetCategorizedProductsUseCaseImpl

    @Inject
    internal lateinit var getDetailedProductUseCaseImpl: GetDetailedProductUseCaseImpl

    init {
        DaggerProductViewModelComponent.create().inject(this)
    }

    private lateinit var detailedProductState: State<DetailedProduct>
    fun detailedProduct(id: Int) = liveData {
        if (::detailedProductState.isInitialized) {
            emit(detailedProductState)
        } else {
            emit(State.Loading(true))
            val transaction = getDetailedProductUseCaseImpl(id)
            detailedProductState = mapTransactionToState(transaction)
            emit(detailedProductState)
            emit(State.Loading(false))
        }
    }

    private lateinit var categorizedProductsState: State<Map<String, List<CategorizedProduct>>>
    fun categorizedProducts() = liveData {
        if (::categorizedProductsState.isInitialized) {
            emit(categorizedProductsState)
        } else {
            emit(State.Loading(true))
            val transaction = getCategorizedProductsUseCaseImpl()
            categorizedProductsState = mapTransactionToState(transaction)
            emit(categorizedProductsState)
            emit(State.Loading(false))
        }
    }


    private fun <T> mapTransactionToState(transaction: Transaction<T?>): State<T> {
        return when (transaction) {
            is Transaction.Ready -> if (transaction.result != null) {
                State.Data(transaction.result!!)
            } else {
                State.Error(0, "null response")
            }
            is Transaction.Error -> State.Error(transaction.code, transaction.text)
        }
    }
}