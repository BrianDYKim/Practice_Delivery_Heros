package com.example.practicedeliveryheros.screen.home.restaurant

import androidx.annotation.StringRes
import com.example.practicedeliveryheros.R

/**
 * 식당의 카테고리를 저장하는 enum class
 * @param categoryNameId: Int -> 카테고리의 이름을 저장하는 변수 (전체, ...)
 * @param categoryTypeId: Int -> 실제 검색에서 사용할 카테고리 변수
 */
enum class RestaurantCategory(
    @StringRes val categoryNameId: Int,
    @StringRes val categoryTypeId: Int
) {

    ALL(R.string.all, R.string.all_type)
}