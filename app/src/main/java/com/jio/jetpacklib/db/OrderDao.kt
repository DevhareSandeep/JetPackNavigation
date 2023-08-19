package com.jio.jetpacklib.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Created by sandeep2.devhare@ril.com on 16/06/22 in project Jio Prime Partner.
 */
@Dao
interface OrderDao {
  @Query("SELECT * FROM order_table ORDER BY dish_name ASC")
  fun getAllOrders(): Flow<List<OrderData>>

  //By default room supports the suspend
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  suspend fun insertOrder(orders: OrderData)

  @Query("DELETE FROM order_table")
  suspend fun deleteAllOrders()
}
