package com.jio.jetpacklib

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.textfield.TextInputEditText

class VegFragment : Fragment(), View.OnClickListener {
  var navControler: NavController? = null
  private var input: TextInputEditText? = null

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_veg, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    navControler = Navigation.findNavController(view)
    view.findViewById<Button>(R.id.ok_btn).setOnClickListener(this)
    input = view.findViewById(R.id.inputItemVeg)

  }

  override fun onClick(v: View?) {
    when (v?.id) {
      R.id.ok_btn -> {
        if (!TextUtils.isEmpty(
            input?.text.toString()
          )
        ) {
          val bundle = bundleOf("recipient" to input?.text.toString())
          navControler?.navigate(R.id.action_vegFragment_to_orderDetailsFragment, bundle)
        } else {
          Toast.makeText(activity, "Please enter menu", Toast.LENGTH_SHORT).show()
        }
      }

    }
  }
}