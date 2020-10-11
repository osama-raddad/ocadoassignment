package com.osama.ocadoassignment.product_listing.category_listing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.osama.domain.entity.CategorizedProduct
import com.osama.ocadoassignment.R
import com.osama.ocadoassignment.product_listing.category_listing.product_list.ProductListView

internal class CategoryAdapter(private val data: Map<String, List<CategorizedProduct>>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.item_category, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataItem = data.keys.toList()[position]
        holder.categoryTitleTextView.text = dataItem
        holder.categoryTitleTextView.visibility = View.VISIBLE
        holder.productListView.setData(data.getValue(dataItem))

    }

    override fun getItemCount(): Int = data.keys.size


    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val categoryTitleTextView: TextView = itemView.findViewById(R.id.categoryTitleTextView)
        val productListView: ProductListView = itemView.findViewById(R.id.productListView)
    }
}