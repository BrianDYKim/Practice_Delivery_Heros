package com.example.practicedeliveryheros.widget.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.practicedeliveryheros.model.Model
import com.example.practicedeliveryheros.screen.base.BaseViewModel
import com.example.practicedeliveryheros.util.provider.ResourcesProvider
import com.example.practicedeliveryheros.widget.listener.AdapterListener

/**
 * ViewHolder는 뷰를 담아두는 역할을 수행한다.
 * 뷰를 담는 과정에서 Model, ViewModel이 필요할것이다.
 * ViewHolder는 테이터 변화를 감지해야하기 때문에 adapterListener도 전달을 받아야할 것이다.
 */
abstract class ModelViewHolder<M : Model>(
    binding: ViewBinding,
    protected val viewModel: BaseViewModel,
    protected val resourcesProvider: ResourcesProvider
) : RecyclerView.ViewHolder(binding.root) {

    abstract fun reset()

    // 데이터의 변화가 일어날 때 뷰의 변화를 처리하는 메소드
    abstract fun bindViews(model: M, adapterListener: AdapterListener)

    open fun bindData() {
        reset()
    }

}