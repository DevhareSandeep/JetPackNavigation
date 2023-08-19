package com.jio.jetpacklib.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by sandeep2.devhare@ril.com on 16/06/22 in project Jio Prime Partner.
 */
@Entity(tableName = "order_table")
data class OrderData(
  @PrimaryKey(autoGenerate = true) val orderId: Int = 0,
  @ColumnInfo(name = "dish_name") val dishName: String?,
  @ColumnInfo(name = "dish_qty") val dishQty: String?,
  @ColumnInfo(name = "dish_price") val dishPrice: String?
)