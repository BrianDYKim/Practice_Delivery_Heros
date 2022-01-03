package com.example.practicedeliveryheros.screen.home.restaurant

import android.util.Log
import androidx.core.os.bundleOf
import com.example.practicedeliveryheros.databinding.FragmentRestaurantListBinding
import com.example.practicedeliveryheros.screen.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class RestaurantListFragment: BaseFragment<RestaurantListViewModel, FragmentRestaurantListBinding>() {

    private val restaurantCategory by lazy {
        arguments?.getSerializable(RESTAURANT_CATEGORY_KEY) as RestaurantCategory
    }

    override val viewModel by viewModel<RestaurantListViewModel> { parametersOf(restaurantCategory) }

    override fun getViewBinding(): FragmentRestaurantListBinding = FragmentRestaurantListBinding.inflate(layoutInflater)

    // 데이터의 변화를 관찰하는 메소드
    override fun observeData() = viewModel.restaurantListLiveData.observe(viewLifecycleOwner) {
        Log.e("restaurantList: ", it.toString())
    }

    companion object {
        const val RESTAURANT_CATEGORY_KEY = "restaurantCategory"

        fun newInstance(restaurantCategory: RestaurantCategory): RestaurantListFragment {

            return RestaurantListFragment().apply {
                arguments = bundleOf(
                    RESTAURANT_CATEGORY_KEY to restaurantCategory
                )
            }
        }
    }
}