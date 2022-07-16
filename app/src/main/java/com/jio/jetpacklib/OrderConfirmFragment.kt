package com.jio.jetpacklib

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation

class OrderConfirmFragment : Fragment(), View.OnClickListener {
  private var order: Order? = null
  var navControler: NavController? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    arguments?.let {
      order = it.getParcelable("order")
    }
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_order_confirm, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    navControler = Navigation.findNavController(view)
    view.findViewById<TextView>(R.id.orderdetails).text =
      "You ordered \n ${order?.menu} and Quantity is ${order?.quantity}"
    view.findViewById<Button>(R.id.showorders).setOnClickListener(this)

  }

  override fun onClick(v: View?) {
   if (v?.id == R.id.showorders) {
     val bundle = bundleOf("columnCount" to "1", "order" to order)
     navControler?.navigate(R.id.action_orderConfirmFragment_to_orderItemFragment, bundle)
   }
  }
}