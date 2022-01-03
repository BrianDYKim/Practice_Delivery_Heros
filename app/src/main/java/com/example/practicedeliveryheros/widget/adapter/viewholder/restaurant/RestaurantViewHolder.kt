package com.example.practicedeliveryheros.widget.adapter.viewholder.restaurant

import com.example.practicedeliveryheros.R
import com.example.practicedeliveryheros.databinding.ViewholderRestaurantBinding
import com.example.practicedeliveryheros.extensions.clear
import com.example.practicedeliveryheros.extensions.load
import com.example.practicedeliveryheros.model.restaurant.RestaurantModel
import com.example.practicedeliveryheros.screen.base.BaseViewModel
import com.example.practicedeliveryheros.util.provider.ResourcesProvider
import com.example.practicedeliveryheros.widget.adapter.viewholder.ModelViewHolder
import com.example.practicedeliveryheros.widget.listener.AdapterListener
import com.example.practicedeliveryheros.widget.listener.restaurant.RestaurantListListener

class RestaurantViewHolder(
    private val binding: ViewholderRestaurantBinding,
    viewModel: BaseViewModel,
    resourcesProvider: ResourcesProvider
) : ModelViewHolder<RestaurantModel>(binding, viewModel, resourcesProvider) {

    override fun reset() = with(binding) {
        restaurantImage.clear()
    }

    override fun bindData(model: RestaurantModel) {
        super.bindData(model)
        with(binding) {
            restaurantImage.load(model.restaurantImageUrl, 24f)
            restaurantTitleText.text = model.restaurantTitle
            gradeText.text = resourcesProvider.getString(R.string.grade_format, model.grade)
            reviewCountText.text = resourcesProvider.getString(R.string.review_count, model.reviewCount)
            val (minTime, maxTime) = model.deliveryTimeRange
            deliveryTimeText.text = resourcesProvider.getString(R.string.delivery_time, minTime, maxTime)

            val (minTip, maxTip) = model.deliveryTipRange
            deliveryTipText.text = resourcesProvider.getString(R.string.delivery_tip, minTip, maxTip)
        }
    }

    override fun bindViews(model: RestaurantModel, adapterListener: AdapterListener) = with(binding) {
        if(adapterListener is RestaurantListListener) {
            root.setOnClickListener {
                adapterListener.onClickItem(model)
            }
        }
    }
}