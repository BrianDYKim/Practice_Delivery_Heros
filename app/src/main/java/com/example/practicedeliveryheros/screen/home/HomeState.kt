package com.example.practicedeliveryheros.screen.home

import androidx.annotation.StringRes
import com.example.practicedeliveryheros.data.entity.MapSearchInfoEntity

// 위치 정보를 받아올 때의 상태를 정의한 클래스
sealed class HomeState {

    object Uninitialized: HomeState()

    object Loading: HomeState()

    data class Success(
        val mapSearchInfo: MapSearchInfoEntity
    ): HomeState()

    data class Error(
        @StringRes val messageId: Int
    ): HomeState()
}
