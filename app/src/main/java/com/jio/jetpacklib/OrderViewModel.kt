package com.jio.jetpacklib

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.jio.jetpacklib.data.OrderRepository
import com.jio.jetpacklib.db.OrderData
import kotlinx.coroutines.launch

/**
 * Created by sandeep2.devhare@ril.com on 17/06/22 in project Jio Prime Partner.
 */

class OrderViewModel(private val repository: OrderRepository) : ViewModel() {

  // Using LiveData and caching what allWords returns has several benefits:
  // - We can put an observer on the data (instead of polling for changes) and only update the
  //   the UI when the data actually changes.
  // - Repository is completely separated from the UI through the ViewModel.
  val allOrders: LiveData<List<OrderData>> = repository.allOrders.asLiveData()

  /**
   * Launching a new coroutine to insert the data in a non-blocking way
   */
  fun insert(order: Order) = viewModelScope.launch {
    repository.insert(
      OrderData(
        dishName = order.menu,
        dishQty = order.quantity.toString(),
        dishPrice = order.amount.toString()
      )
    )
  }
}

class OrderViewModelFactory(private val repository: OrderRepository) : ViewModelProvider.Factory {
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(OrderViewModel::class.java)) {
      @Suppress("UNCHECKED_CAST")
      return OrderViewModel(repository) as T
    }
    throw IllegalArgumentException("Unknown ViewModel class")
  }
}