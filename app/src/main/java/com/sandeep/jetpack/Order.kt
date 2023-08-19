package com.sandeep.jetpack

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

/**
 * Created by Sandeep Devhare on 17/06/22.
 */
@Parcelize
data class Order(val menu: String, val quantity: Int, val amount: BigDecimal) : Parcelable
