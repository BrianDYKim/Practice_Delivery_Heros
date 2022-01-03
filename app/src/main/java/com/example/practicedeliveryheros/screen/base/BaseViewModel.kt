package com.example.practicedeliveryheros.screen.base

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * BaseViewModel은 View와 Model 간의 중간다리 역할을 수행하는 계층이다.
 */
abstract class BaseViewModel : ViewModel() {

    protected var stateBundle : Bundle? = null // 컴포넌트에서 내부적인 데이터 처리를 위해서 선언

    open fun fetchData() : Job = viewModelScope.launch {  }

    open fun storeState(stateBundle: Bundle) {
        this.stateBundle = stateBundle
    }
}