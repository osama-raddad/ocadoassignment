package com.osama.ocadoassignment.common_compound_view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.osama.ocadoassignment.R
import kotlinx.android.synthetic.main.error_layout.view.*

internal class ErrorView constructor(context: Context, attrs: AttributeSet) :
    FrameLayout(context, attrs) {
    init {
        inflate(getContext(), R.layout.error_layout, this)
    }

    fun setError(error: String) {
        errorTextView.text = error
        errorTextView.visibility = View.VISIBLE
        visibility = View.VISIBLE
    }
}