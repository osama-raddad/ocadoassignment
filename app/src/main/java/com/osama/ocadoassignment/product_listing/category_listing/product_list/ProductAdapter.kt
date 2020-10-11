package com.osama.ocadoassignment.product_listing.category_listing.product_list

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.osama.domain.entity.CategorizedProduct
import com.osama.ocadoassignment.R
import com.osama.ocadoassignment.product_details.ProductDetailsActivity


internal class ProductAdapter(private val data: List<CategorizedProduct>) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {


    override fun getItemCount(): Int = data.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.item_product, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataItem = data[position]

        holder.titleTextView.text = dataItem.title
        holder.priceTextView.text = dataItem.price.toString()

        Glide.with(holder.productImageView.context)
            .load(dataItem.imageUrl)
            .into(holder.productImageView)

        holder.root.setOnClickListener {
            val myIntent = Intent(it.context, ProductDetailsActivity::class.java)
            myIntent.putExtra("id", dataItem.id)
            it.context.startActivity(myIntent)
        }
    }

    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val root = listItemView
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val productImageView: ImageView = itemView.findViewById(R.id.productImageView)
        val priceTextView: TextView = itemView.findViewById(R.id.priceTextView)
    }

}
