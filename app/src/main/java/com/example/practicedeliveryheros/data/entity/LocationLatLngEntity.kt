package com.example.practicedeliveryheros.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * @author Doyeop Kim
 * @since 2022/01/06
 * @throws
 * @description
 */
@Parcelize
class LocationLatLngEntity(
    val latitude: Double,
    val longitude: Double,
    override val id: Long = -1
) : Entity, Parcelable {

}