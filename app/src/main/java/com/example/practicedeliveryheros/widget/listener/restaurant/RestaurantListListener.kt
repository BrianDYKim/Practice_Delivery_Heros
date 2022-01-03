package com.example.practicedeliveryheros.widget.listener.restaurant

import com.example.practicedeliveryheros.model.restaurant.RestaurantModel
import com.example.practicedeliveryheros.widget.listener.AdapterListener

interface RestaurantListListener : AdapterListener{

    // RestaurantModel을 클릭 시 이벤트 처리를 해주는 메소드
    fun onClickItem(model: RestaurantModel)
}