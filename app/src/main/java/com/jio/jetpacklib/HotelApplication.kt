package com.jio.jetpacklib

import android.app.Application
import com.jio.jetpacklib.data.OrderRepository
import com.jio.jetpacklib.db.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

/**
 * Created by sandeep2.devhare@ril.com on 17/06/22 in project Jio Prime Partner.
 */
class HotelApplication : Application() {
  // No need to cancel this scope as it'll be torn down with the process
  private val applicationScope = CoroutineScope(SupervisorJob())

  // Using by lazy so the database and the repository are only created when they're needed
  // rather than when the application starts
  val database by lazy { AppDatabase.getDatabase(this, applicationScope) }
  val repository by lazy { OrderRepository(database.getOrderDao()) }

}