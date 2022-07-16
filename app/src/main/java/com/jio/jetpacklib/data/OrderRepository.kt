package com.jio.jetpacklib.data

import android.util.Log
import androidx.annotation.WorkerThread
import com.jio.jetpacklib.db.OrderDao
import com.jio.jetpacklib.db.OrderData
import kotlinx.coroutines.flow.Flow

/**
 * Created by sandeep2.devhare@ril.com on 16/06/22 in project Jio Prime Partner.
 */

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class OrderRepository(private val orderDao: OrderDao) {
  // Room executes all queries on a separate thread.
  // Observed Flow will notify the observer when the data has changed.
  val allOrders: Flow<List<OrderData>> = orderDao.getAllOrders()

  // By default Room runs suspend queries off the main thread, therefore, we don't need to
  // implement anything else to ensure we're not doing long running database work
  // off the main thread.
  @Suppress("RedundantSuspendModifier")
  @WorkerThread
  suspend fun insert(orders: OrderData) {
    Log.d("***", "insert: OrderRepository ${orders.dishName}")
    orderDao.insertOrder(
      orders
    )
  }
}