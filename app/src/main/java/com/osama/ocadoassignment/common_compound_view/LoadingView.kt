package com.osama.ocadoassignment.common_compound_view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.osama.ocadoassignment.R
import kotlinx.android.synthetic.main.laoding_layout.view.*

class LoadingView constructor(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {
    init {
        inflate(getContext(), R.layout.laoding_layout, this)
    }

    fun setLoading(isLoading: Boolean) {
        val viewVisibility = if (isLoading) View.VISIBLE else View.GONE
        progressBar.visibility = viewVisibility
        visibility = viewVisibility
    }
}