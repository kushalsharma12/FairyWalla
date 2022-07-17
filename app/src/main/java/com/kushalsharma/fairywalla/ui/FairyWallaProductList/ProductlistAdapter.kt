package com.kushalsharma.fairywallaapp.ui.FairyWallaProductList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.kushalsharma.fairywallaapp.R
import com.kushalsharma.fairywallaapp.modals.Fproducts

class ProductlistAdapter(options: FirestoreRecyclerOptions<Fproducts>) :
    FirestoreRecyclerAdapter<Fproducts, ProductlistAdapter.ProductViewHolder>(options) {

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var pName: TextView? = itemView.findViewById(R.id.item_name_product_list)
        var pImg: ImageView? = itemView.findViewById(R.id.image_item_product)
        var pDesciption: TextView? = itemView.findViewById(R.id.description_item_product_list)
        var pPrice: TextView? = itemView.findViewById(R.id.price_item_product_list)
        var pQuantity: TextView? = itemView.findViewById(R.id.quantity_item_product_list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val viewHolder = ProductViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_product_list, parent, false)
        )

        return viewHolder
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int, model: Fproducts) {

        holder.pName!!.text = model.title.toString()
        holder.pPrice!!.text = "₹ ${model.price}"
        holder.pDesciption!!.text = model.description
        holder.pQuantity!!.text = "Qty: ${model.quantity}"
        Glide.with(holder.pImg!!.context)
            .load(model.productImageUrl)
            .transform(CenterCrop(), RoundedCorners(40))
            .placeholder(R.drawable.ic_loading_placeholder)
            .into(holder.pImg!!)

    }
}