package com.sandeep.jetpack

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sandeep.jetpack.databinding.FragmentOrderItemBinding
import com.sandeep.jetpack.db.OrderData
import com.sandeep.jetpack.placeholder.PlaceholderContent.PlaceholderItem

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 */
class OrderItemRecyclerViewAdapter(
  private val values: List<OrderData>
) : RecyclerView.Adapter<OrderItemRecyclerViewAdapter.ViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

    return ViewHolder(
      FragmentOrderItemBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
      )
    )

  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val item = values[position]
    holder.idView.text = item.dishName
    holder.contentView.text = item.dishQty
  }

  override fun getItemCount(): Int = values.size

  inner class ViewHolder(binding: FragmentOrderItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    val idView: TextView = binding.itemNumber
    val contentView: TextView = binding.content

    override fun toString(): String {
      return super.toString() + " '" + contentView.text + "'"
    }
  }

}