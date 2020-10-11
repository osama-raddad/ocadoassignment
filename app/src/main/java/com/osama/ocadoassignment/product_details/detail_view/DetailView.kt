package com.osama.ocadoassignment.product_details.detail_view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ScrollView
import com.bumptech.glide.Glide
import com.osama.domain.entity.DetailedProduct
import com.osama.ocadoassignment.R
import kotlinx.android.synthetic.main.detail_layout.view.*

internal class DetailView constructor(context: Context, attrs: AttributeSet) :
    ScrollView(context, attrs) {
    init {
        inflate(getContext(), R.layout.detail_layout, this)
    }

    fun setData(data: DetailedProduct) {
        Glide.with(context)
            .load(data.imageUrl)
            .into(productImageView)

        titleTextView.text = data.title
        descriptionTextView.text = data.description
        allergyInformationTextView.text = data.allergyInformation
        visibility = View.VISIBLE
    }
}