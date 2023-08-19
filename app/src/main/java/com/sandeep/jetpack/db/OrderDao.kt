package com.sandeep.jetpack.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Created by Sandeep Devhare on 17/06/22.
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
