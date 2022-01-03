package com.example.practicedeliveryheros.util.mapper

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.practicedeliveryheros.databinding.ViewholderEmptyBinding
import com.example.practicedeliveryheros.databinding.ViewholderRestaurantBinding
import com.example.practicedeliveryheros.model.CellType
import com.example.practicedeliveryheros.model.Model
import com.example.practicedeliveryheros.screen.base.BaseViewModel
import com.example.practicedeliveryheros.util.provider.ResourcesProvider
import com.example.practicedeliveryheros.widget.adapter.viewholder.EmptyViewHolder
import com.example.practicedeliveryheros.widget.adapter.viewholder.ModelViewHolder
import com.example.practicedeliveryheros.widget.adapter.viewholder.restaurant.RestaurantViewHolder

/**
 * Model의 타입에 따라 다른 ModelViewHolder를 반환하는 메소드
 * @param parent: ViewGroup : UI가 그려지는 ViewGroup
 * @param type: CellType
 * @param viewModel: BaseViewModel : Model의 비지니스 로직을 처리해주는 viewModel
 * @param resourcesProvider: ResourcesProvider : View의 변화가 발생했을 때 resource에 접근할 수 있게 해주는 provider
 */
object ModelViewHolderMapper {

    @SuppressLint("UNCHECKED_CAST")
    fun <M : Model> map(
        parent: ViewGroup,
        type: CellType,
        viewModel: BaseViewModel,
        resourcesProvider: ResourcesProvider
    ): ModelViewHolder<M> {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)

        val viewHolder = when(type) {
            CellType.EMPTY_CELL -> EmptyViewHolder(
                ViewholderEmptyBinding.inflate(inflater, parent, false), viewModel, resourcesProvider
            )
            CellType.RESTAURANT_CELL -> RestaurantViewHolder(
                ViewholderRestaurantBinding.inflate(inflater, parent, false), viewModel, resourcesProvider
            )
        }

        return viewHolder as ModelViewHolder<M>
    }
}