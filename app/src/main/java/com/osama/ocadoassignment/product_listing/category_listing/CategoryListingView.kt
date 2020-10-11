package com.osama.ocadoassignment.product_listing.category_listing

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.osama.domain.entity.CategorizedProduct
import com.osama.ocadoassignment.R
import kotlinx.android.synthetic.main.category_listing_layout.view.*

internal class CategoryListingView constructor(context: Context, attrs: AttributeSet) :
    FrameLayout(context, attrs) {
    init {
        inflate(getContext(), R.layout.category_listing_layout, this)
    }

    fun setData(data: Map<String, List<CategorizedProduct>>) {
        val adapter = CategoryAdapter(data)
        categoryRecyclerView.adapter = adapter
        categoryRecyclerView.layoutManager = LinearLayoutManager(context)

        visibility = View.VISIBLE
        categoryRecyclerView.visibility = View.VISIBLE
    }
}