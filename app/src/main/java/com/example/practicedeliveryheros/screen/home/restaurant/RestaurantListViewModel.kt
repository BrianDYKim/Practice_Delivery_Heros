package com.example.practicedeliveryheros.screen.home.restaurant

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.practicedeliveryheros.data.entity.RestaurantEntity
import com.example.practicedeliveryheros.data.repository.RestaurantRepository
import com.example.practicedeliveryheros.screen.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * RestaurantList 데이터와 RestairantFragment 사이를 중재하는 ViewModel
 * @param restaurantCategory: RestaurantCategory -> 식당의 종류를 담는 enum class
 * @param restaurantRepository: RestaurantRepository
 */
class RestaurantListViewModel(
    private val restaurantCategory: RestaurantCategory,
    private val restaurantRepository: RestaurantRepository
): BaseViewModel() {

    val restaurantListLiveData = MutableLiveData<List<RestaurantEntity>>()

    override fun fetchData(): Job = viewModelScope.launch {
        restaurantListLiveData.value = restaurantRepository.getList(restaurantCategory)
    }
}