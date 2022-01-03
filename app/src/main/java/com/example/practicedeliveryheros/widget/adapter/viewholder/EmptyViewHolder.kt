package com.example.practicedeliveryheros.widget.adapter.viewholder

import androidx.viewbinding.ViewBinding
import com.example.practicedeliveryheros.model.Model
import com.example.practicedeliveryheros.screen.base.BaseViewModel
import com.example.practicedeliveryheros.util.provider.ResourcesProvider
import com.example.practicedeliveryheros.widget.listener.AdapterListener

class EmptyViewHolder(
    private val binding: ViewBinding,
    viewModel: BaseViewModel,
    resourcesProvider: ResourcesProvider
) : ModelViewHolder<Model>(binding, viewModel, resourcesProvider) {

    override fun reset() = Unit

    override fun bindViews(model: Model, adapterListener: AdapterListener) = Unit
}