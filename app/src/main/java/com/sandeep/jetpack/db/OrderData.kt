package com.sandeep.jetpack.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Sandeep Devhare on 17/06/22.
 */
@Entity(tableName = "order_table")
data class OrderData(
  @PrimaryKey(autoGenerate = true) val orderId: Int = 0,
  @ColumnInfo(name = "dish_name") val dishName: String?,
  @ColumnInfo(name = "dish_qty") val dishQty: String?,
  @ColumnInfo(name = "dish_price") val dishPrice: String?
)