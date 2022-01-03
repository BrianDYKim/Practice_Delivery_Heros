package com.example.practicedeliveryheros.data.entity

import android.os.Parcelable
import com.example.practicedeliveryheros.screen.home.restaurant.RestaurantCategory
import kotlinx.parcelize.Parcelize


@Parcelize
class RestaurantEntity(
    override val id: Long,
    val restaurantInfoId: Long,
    val restaurantCategory: RestaurantCategory,
    val restaurantTitle: String,
    val restaurantImageUrl: String,
    val grade: Float,
    val reviewCount: Int,
    val deliveryTimeRange: Pair<Int, Int>,
    val deliveryTipRange: Pair<Int, Int>
): Entity, Parcelable {

}