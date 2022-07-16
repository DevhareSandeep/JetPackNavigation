package com.jio.jetpacklib.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Created by sandeep2.devhare@ril.com on 16/06/22 in project Jio Prime Partner.
 */
@Database(
  entities = [OrderData::class],
  version = 1,
  exportSchema = false
) //exportSchema is used for migration purpose if it true it export the schema.
//In this array [OrderData::class] we can add multiple tables called entities
abstract class AppDatabase : RoomDatabase() {
  abstract fun getOrderDao(): OrderDao //This is abstract methode since  class is abstract.

  companion object {
    // Singleton prevents multiple instances of database opening at the
    // same time.
    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(
      context: Context,
      scope: CoroutineScope
    ): AppDatabase {
      // if the INSTANCE is not null, then return it,
      // if it is, then create the database
      return INSTANCE ?: synchronized(this) {
        val instance = Room.databaseBuilder(
          context.applicationContext,
          AppDatabase::class.java,
          "order_database"
        ).fallbackToDestructiveMigration()
          .addCallback(OrderDataBaseCallBack(scope))
          .build()
        INSTANCE = instance
        // return instance
        instance
      }
    }
  }

  private class OrderDataBaseCallBack(
    private val scope: CoroutineScope
  ) : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
      super.onCreate(db)
      INSTANCE?.let { database ->
        scope.launch {
          //populateDatabase(database.getOrderDao())
        }
      }
    }

    suspend fun populateDatabase(orderDao: OrderDao) {
      // Delete all content here.
      orderDao.deleteAllOrders()
      // Add sample orders.
      val order = OrderData(1, "Paneer", "4", "240")
      orderDao.insertOrder(order)
    }
  }
}