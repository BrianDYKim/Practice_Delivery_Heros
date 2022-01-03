package com.example.practicedeliveryheros.screen.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.Job

/**
 * BaseFragment는 Fragment들을 추상화하는 클래스이다.
 */
abstract class BaseFragment<VM: BaseViewModel, VB: ViewBinding> : Fragment() {

    abstract val viewModel: VM

    protected lateinit var binding: VB

    private lateinit var fetchJob: Job

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getViewBinding()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initState()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        if(fetchJob.isActive) {
            fetchJob.cancel()
        }
        super.onDestroy()
    }

    abstract fun getViewBinding(): VB

    open fun initState() {
        arguments?.let {
            viewModel.storeState(it) // viewModel에 해당 Fragment의 Bundle을 초기화시켜서 viewModel에서 내부적인 데이터 처리를 효윯적으로 만든다
        }
        initViews()
        fetchJob = viewModel.fetchData() // ViewModelScope의 Job 상태를 받아온다
        observeData()
    }

    open fun initViews() = Unit

    abstract fun observeData()

}