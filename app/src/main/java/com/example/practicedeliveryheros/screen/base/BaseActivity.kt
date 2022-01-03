package com.example.practicedeliveryheros.screen.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.Job

/**
 * Activity에서는 ViewBinding, ViewModel을 통해서 UI Component를 관리해야함
 * LiveData를 사용할 것이기 때문에 observe에 대한 구현이 필요하다
 */
abstract class BaseActivity<VM: BaseViewModel, VB: ViewBinding> : AppCompatActivity() {

    abstract val viewModel : VM

    private lateinit var binding: VB // 바인딩의 경우 onCreate() 시에 처리를한다.

    private lateinit var fetchJob: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = getViewBinding()
        setContentView(binding.root)
        super.onCreate(savedInstanceState)
        initState()
    }

    override fun onDestroy() {
        if(fetchJob.isActive) {
            fetchJob.cancel()
        }
        super.onDestroy()
    }

    abstract fun getViewBinding(): VB

    open fun initState() {
        initViews()
        fetchJob = viewModel.fetchData()
        observeData()
    }

    open fun initViews()  = Unit // View를 초기화 할 필요가 없는 경우도 있기 때문에 open으로 처리

    abstract fun observeData() // View마다 데이터 처리가 다르기 때문에 상속 activity에서 별도 구현이 필요함
}