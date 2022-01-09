package com.example.practicedeliveryheros.data.repository.map

import android.util.Log
import com.example.practicedeliveryheros.data.entity.LocationLatLngEntity
import com.example.practicedeliveryheros.data.network.MapApiService
import com.example.practicedeliveryheros.data.response.address.AddressInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * @author Doyeop Kim
 * @since 2022/01/06
 * @throws
 * @description
 */
class DefaultMapRepository(
    private val mapApiService: MapApiService,
    private val ioDispatcher: CoroutineDispatcher
) : MapRepository {
    override suspend fun getReverseGeoInformation(
        locationLatLngEntity: LocationLatLngEntity
    ): AddressInfo? = withContext(ioDispatcher) {
        val response = mapApiService.getReverseGeoCode(
            lat = locationLatLngEntity.latitude,
            lon = locationLatLngEntity.longitude
        )

        if(response.isSuccessful) {
            response.body()?.addressInfo
        } else {
            null
        }
    }
}