package com.sandeep.jetpack

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sandeep.jetpack.db.OrderData
import java.math.BigDecimal

/**
 * A fragment representing a list of Items.
 */
class OrderItemFragment : Fragment() {
  private var data: List<OrderData> = arrayListOf()
  private var order: Order? = null
  private var columnCount = "1"
  private var navControler: NavController? = null

  private val orderViewModel: OrderViewModel by viewModels {
    OrderViewModelFactory((requireActivity().application as HotelApplication).repository)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    arguments?.let {
      columnCount = it.getString("columnCount").toString()
      order = it.getParcelable("order")
      Log.d("***", "onCreate: ${order?.menu}")
      val order =
        Order(order?.menu.toString(), order?.quantity ?: 0, BigDecimal(order?.amount.toString()))
      orderViewModel.insert(order)
    }
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    Log.d("***", "onCreateView: ")
    return inflater.inflate(R.layout.fragment_order_item_list, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    Log.d("***", "onActivityCreated: ")
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    navControler = Navigation.findNavController(view)
    val viewList = view.findViewById<RecyclerView>(R.id.list)
    // Update the cached copy of the words in the adapter.
    with(viewList) {
      layoutManager = when {
        columnCount <= "1" -> LinearLayoutManager(context)
        else               -> GridLayoutManager(context, columnCount.toInt())
      }
      orderViewModel.allOrders.observe(viewLifecycleOwner) { order ->
        order?.let {
          data = it
        }
        Log.d("***", "onViewCreated: ${data.size}")
        adapter = OrderItemRecyclerViewAdapter(data)
      }

    }

  }


}