package com.example.practicedeliveryheros.widget.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.practicedeliveryheros.model.CellType
import com.example.practicedeliveryheros.model.Model
import com.example.practicedeliveryheros.screen.base.BaseViewModel
import com.example.practicedeliveryheros.util.mapper.ModelViewHolderMapper
import com.example.practicedeliveryheros.util.provider.ResourcesProvider
import com.example.practicedeliveryheros.widget.adapter.viewholder.ModelViewHolder
import com.example.practicedeliveryheros.widget.listener.AdapterListener

/**
 * RecyclerView의 Adapter로 활용이 되는 클래스이다.
 * @param modelList: List<Model> : RecyclerView에서 띄워질 데이터 리스트
 * @param viewModel: VM : 제네릭으로 전달받은 VM 타입의 ViewModel 객체
 * @param resourcesProvider: ResourcesProvider
 * @param adapterListener: AdapterListener : 데이터의 변화가 일어날 때 콜백을 받아서 처리를 진행하는 리스너 객체
 */
class ModelRecyclerAdapter<M: Model, VM: BaseViewModel>(
    private var modelList: List<Model>,
    private val viewModel: VM,
    private val resourcesProvider: ResourcesProvider,
    private val adapterListener: AdapterListener
) : ListAdapter<Model, ModelViewHolder<Model>>(Model.DIFF_CALLBACK) {

    override fun getItemCount(): Int = modelList.size

    override fun getItemViewType(position: Int): Int {
        return modelList[position].type.ordinal
    }

    // 아이템의 타입에 따라서 ViewHolder를 매핑해주는 메소드가 필요하다
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder<Model> {
        return ModelViewHolderMapper.map(parent, CellType.values()[viewType], viewModel, resourcesProvider)
    }

    @SuppressLint("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: ModelViewHolder<Model>, position: Int) {
        with(holder) {
            bindData(modelList[position] as M)
            bindViews(modelList[position] as M, adapterListener)
        }
    }

    override fun submitList(list: List<Model>?) {
        list?.let {
            modelList = it
        }
        super.submitList(list)
    }
}