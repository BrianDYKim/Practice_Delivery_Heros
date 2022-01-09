package com.example.practicedeliveryheros.data.repository.map

import com.example.practicedeliveryheros.data.entity.LocationLatLngEntity
import com.example.practicedeliveryheros.data.response.address.AddressInfo

/**
 * @author Doyeop Kim
 * @since 2022/01/06
 * @throws
 * @description
 */
interface MapRepository {

    suspend fun getReverseGeoInformation(locationLatLngEntity: LocationLatLngEntity): AddressInfo?

}