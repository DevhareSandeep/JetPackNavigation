package com.jio.jetpacklib

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

/**
 * Created by sandeep2.devhare@ril.com on 16/06/22 in project Jio Prime Partner.
 */
@Parcelize
data class Order(val menu: String, val quantity: Int, val amount: BigDecimal) : Parcelable
