package com.osama.ocadoassignment.product_listing.category_listing.product_list

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.osama.domain.entity.CategorizedProduct
import com.osama.ocadoassignment.R
import kotlinx.android.synthetic.main.product_listing_layout.view.*

internal class ProductListView constructor(context: Context, attrs: AttributeSet) :
    FrameLayout(context, attrs) {
    init {
        inflate(getContext(), R.layout.product_listing_layout, this)
    }

    fun setData(data: List<CategorizedProduct>) {
        val adapter = ProductAdapter(data)
        productRecyclerView.adapter = adapter
        productRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        visibility = View.VISIBLE
        productRecyclerView.visibility = View.VISIBLE
    }
}