package com.example.practicedeliveryheros.data.repository

import com.example.practicedeliveryheros.data.entity.RestaurantEntity
import com.example.practicedeliveryheros.screen.home.restaurant.RestaurantCategory

interface RestaurantRepository {

    // 코루틴 블록 내부에서만 repository가 동작하기 때문에 suspend로 정의
    suspend fun getList(
        restaurantCategory: RestaurantCategory
    ): List<RestaurantEntity>
}