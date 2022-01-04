package com.example.practicedeliveryheros.screen.home

// 위치 정보를 받아올 때의 상태를 정의한 클래스
sealed class HomeState {

    object Uninitialized: HomeState()

    object Loading: HomeState()

    object Success: HomeState()
}
