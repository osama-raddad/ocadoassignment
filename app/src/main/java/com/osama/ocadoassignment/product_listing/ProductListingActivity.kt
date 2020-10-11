package com.osama.ocadoassignment.product_listing

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.osama.domain.categorized_products.GetCategorizedProductsUseCaseImpl
import com.osama.domain.detailed_product.GetDetailedProductUseCaseImpl
import com.osama.domain.entity.CategorizedProduct
import com.osama.ocadoassignment.ProductViewModel
import com.osama.ocadoassignment.R
import com.osama.ocadoassignment.State
import kotlinx.android.synthetic.main.activity_product_listing.*

internal class ProductListingActivity : AppCompatActivity() {

    private val viewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_listing)
        viewModel.categorizedProducts().observe(this, ::stateObserver)
    }

    private fun stateObserver(state: State<Map<String, List<CategorizedProduct>>>) =
        when (state) {
            is State.Data -> showResult(state.data)
            is State.Error -> showError(state.text)
            is State.Loading -> loading(state.isLoading)
        }

    private fun loading(loading: Boolean) {
        loadingView.setLoading(loading)
    }

    private fun showError(text: String) {
        errorView.setError(text)
    }

    private fun showResult(data: Map<String, List<CategorizedProduct>>) {
        Log.d(ProductListingActivity::class.simpleName, data.keys.size.toString())
        categoryListingView.setData(data)
        categoryListingView.visibility = View.VISIBLE
    }
}