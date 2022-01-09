package com.example.practicedeliveryheros.screen.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.practicedeliveryheros.R
import com.example.practicedeliveryheros.data.entity.LocationLatLngEntity
import com.example.practicedeliveryheros.data.repository.map.MapRepository
import com.example.practicedeliveryheros.screen.base.BaseViewModel
import kotlinx.coroutines.launch

class HomeViewModel(
    private val mapRepository: MapRepository
) : BaseViewModel() {

    // HomeState를 LiveData로 관리
    val homeStateLiveData = MutableLiveData<HomeState>(HomeState.Uninitialized)

    fun loadReverseGeoInformation(locationLatLngEntity: LocationLatLngEntity) =
        viewModelScope.launch {
//            homeStateLiveData.value = HomeState.Loading

            val addressInfo = mapRepository.getReverseGeoInformation(locationLatLngEntity)

            addressInfo?.let { info ->
                homeStateLiveData.value = HomeState.Success(
                    mapSearchInfo = info.toSearchInfoEntity(locationLatLngEntity)
                )
            } ?: kotlin.run {
                homeStateLiveData.value = HomeState.Error(
                    R.string.cannot_load_address_info
                )
            }
        }
}