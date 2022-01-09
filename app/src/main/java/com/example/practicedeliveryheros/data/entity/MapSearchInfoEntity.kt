package com.example.practicedeliveryheros.data.entity

/**
 * @author Doyeop Kim
 * @since 2022/01/06
 * @throws
 * @description
 */
data class MapSearchInfoEntity(
    val fullAddress: String,
    val name: String,
    val locationLatLng: LocationLatLngEntity
) {
}