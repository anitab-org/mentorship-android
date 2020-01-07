package org.systers.mentorship.models

import androidx.annotation.DrawableRes

data class HomeStatsItem(
    val text: String,
    val count: Int, @DrawableRes val icon: Int,
    val actionFun: () -> Unit
)
