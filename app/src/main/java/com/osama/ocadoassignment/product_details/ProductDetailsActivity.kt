package com.osama.ocadoassignment.product_details

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import com.osama.domain.categorized_products.GetCategorizedProductsUseCaseImpl
import com.osama.domain.detailed_product.GetDetailedProductUseCaseImpl
import com.osama.domain.entity.DetailedProduct
import com.osama.ocadoassignment.ProductViewModel
import com.osama.ocadoassignment.R
import com.osama.ocadoassignment.State
import kotlinx.android.synthetic.main.activity_product_details.*

internal class ProductDetailsActivity : AppCompatActivity() {
    private val viewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
        val id = intent.getIntExtra("id", -1)
        if (id != -1) {
            viewModel.detailedProduct(id).observe(this, ::stateObserver)
        } else {
            showError("no id")
        }
    }

    private fun stateObserver(state: State<DetailedProduct>) {
        when (state) {
            is State.Data -> showResult(state.data)
            is State.Error -> showError(state.text)
            is State.Loading -> loading(state.isLoading)
        }
    }


    private fun loading(loading: Boolean) {
        loadingView.setLoading(loading)
    }

    private fun showError(text: String) {
        errorView.setError(text)
    }

    private fun showResult(detailedProduct: DetailedProduct) {
        detailView.setData(detailedProduct)
    }
}