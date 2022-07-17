package com.kushalsharma.fairywallaapp.ui.explore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.kushalsharma.fairywallaapp.R
import com.kushalsharma.fairywallaapp.modals.FairyWalla

class ExploreAdapter(options: FirestoreRecyclerOptions<FairyWalla>, val listener : iPostAdapter) :
    FirestoreRecyclerAdapter<FairyWalla, ExploreAdapter.ExploreViewHolder>(
        options
    ) {

    class ExploreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var FairyWallaName: TextView? = itemView.findViewById(R.id.exploreItem_profile_name)
        var FairyWallaImg: ImageView? = itemView.findViewById(R.id.exploreItem_profile_image)
        var FairyWallaEmail: TextView? = itemView.findViewById(R.id.exploreItem_profile_email)
        var FairyWallaPhno: TextView? = itemView.findViewById(R.id.exploreItem_profile_phNo)
        var FairyWallaAddress: TextView? = itemView.findViewById(R.id.et_exploreItem_address)
        var FairyWallaRating: TextView? = itemView.findViewById(R.id.exploreItem_rating_profile)
        var FairyWallaCard : CardView? = itemView.findViewById(R.id.exploreItem_cardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExploreViewHolder {

        val viewHolder = ExploreViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_explore, parent, false)
        )

        return viewHolder

    }

    override fun onBindViewHolder(holder: ExploreViewHolder, position: Int, model: FairyWalla) {

        holder.FairyWallaName!!.text = model.displayName
        holder.FairyWallaAddress!!.text = "No 15 uit street off ovie palace road effrun delta state"
        holder.FairyWallaEmail!!.text = "donma@gmail.com"
        holder.FairyWallaPhno!!.text = "+23695524"
        holder.FairyWallaRating!!.text = "4.6"
        Glide.with(holder.FairyWallaImg!!.context)
            .load(model.imageUrl)
            .placeholder(R.drawable.ic_loading_placeholder)
            .transform(CenterCrop(), RoundedCorners(40))
            .into(holder.FairyWallaImg!!)

        holder.FairyWallaCard!!.setOnClickListener{
            val fairywallaId : String = model.uid
            listener.onPostClicked(it,fairywallaId)
        }

        holder.FairyWallaImg!!.setOnClickListener{
            listener.iPictureClicked(it)
        }

    }
}

interface iPostAdapter{
    fun onPostClicked( v: View, FairyWallaId : String)
    fun iPictureClicked(v : View)
}