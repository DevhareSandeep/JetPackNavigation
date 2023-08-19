package com.sandeep.jetpack

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.textfield.TextInputEditText
import java.math.BigDecimal

private const val RECIPIENT = "recipient"

class OrderDetailsFragment : Fragment(), View.OnClickListener {

  private var quantity: TextInputEditText? = null
  private var navControler: NavController? = null
  private var recipient: String? = null
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    arguments?.let {
      recipient = it.getString(RECIPIENT)
    }
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_order_details, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    navControler = Navigation.findNavController(view)
    view.findViewById<Button>(R.id.ok_btn).setOnClickListener(this)
    view.findViewById<Button>(R.id.cancel_btn).setOnClickListener(this)
    quantity = view.findViewById(R.id.inputItemQuantity)
    view.findViewById<TextView>(R.id.title).text =
      "You have ordered $recipient,\n Now add quantity 🥳"
  }

  override fun onClick(v: View?) {
    when (v?.id) {
      R.id.ok_btn -> {
        if (!TextUtils.isEmpty(quantity?.text.toString())) {
          val order =
            Order(recipient.toString(), quantity?.text.toString().toInt(), BigDecimal(280))
          val bundle = bundleOf("order" to order)
          navControler?.navigate(R.id.action_orderDetailsFragment_to_orderConfirmFragment, bundle)
        } else {
          Toast.makeText(activity, "Please enter quantity", Toast.LENGTH_SHORT).show()
        }

      }
      R.id.cancel_btn -> {
        activity?.onBackPressed()
      }

    }

  }
}