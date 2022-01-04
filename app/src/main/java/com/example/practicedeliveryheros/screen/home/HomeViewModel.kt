package com.example.practicedeliveryheros.screen.home

import androidx.lifecycle.MutableLiveData
import com.example.practicedeliveryheros.screen.base.BaseViewModel

class HomeViewModel : BaseViewModel() {

    // HomeState를 LiveData로 관리
    val homeStateLiveData = MutableLiveData<HomeState>(HomeState.Uninitialized)



}