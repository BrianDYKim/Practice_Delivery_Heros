package com.example.practicedeliveryheros.screen.home.restaurant

import android.util.Log
import android.widget.Toast
import androidx.core.os.bundleOf
import com.example.practicedeliveryheros.databinding.FragmentRestaurantListBinding
import com.example.practicedeliveryheros.model.restaurant.RestaurantModel
import com.example.practicedeliveryheros.screen.base.BaseFragment
import com.example.practicedeliveryheros.util.provider.ResourcesProvider
import com.example.practicedeliveryheros.widget.adapter.ModelRecyclerAdapter
import com.example.practicedeliveryheros.widget.listener.restaurant.RestaurantListListener
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class RestaurantListFragment :
    BaseFragment<RestaurantListViewModel, FragmentRestaurantListBinding>() {

    private val restaurantCategory by lazy { arguments?.getSerializable(RESTAURANT_CATEGORY_KEY) as RestaurantCategory }

    override val viewModel by viewModel<RestaurantListViewModel> { parametersOf(restaurantCategory) }

    override fun getViewBinding(): FragmentRestaurantListBinding =
        FragmentRestaurantListBinding.inflate(layoutInflater)

    // ResourcesProvider 주입 받기
    private val resourcesProvider by inject<ResourcesProvider>()

    // RecyclerAdapter 구성
    private val adapter by lazy {
        ModelRecyclerAdapter<RestaurantModel, RestaurantListViewModel>(
            listOf(),
            viewModel,
            resourcesProvider,
            adapterListener = object : RestaurantListListener {
                override fun onClickItem(model: RestaurantModel) {
                    Toast.makeText(requireContext(), "${model}", Toast.LENGTH_SHORT).show()
                }
            })
    }

    override fun initViews() = with(binding) {
        recyclerView.adapter = adapter // 어댑터 장착
    }

    // LiveData의 구독은 viewModel의 viewLifecycleOwner를 통해 시행하는 된다
    override fun observeData() = viewModel.restaurantListLiveData.observe(viewLifecycleOwner) {
        Log.e("restaurantList : ", it.toString()) // 로그 찍기
        adapter.submitList(it) // 어댑터에 리스트 장착
    }

    companion object {

        const val RESTAURANT_CATEGORY_KEY = "restaurantCategory"

        fun newInstance(restaurantCategory: RestaurantCategory): RestaurantListFragment {

            // 카테고리를 받아서 리턴을 해주면된다
            return RestaurantListFragment().apply {
                // RestaurantListFragment를 리턴하면 되기는 하지만, restaurantCategory를 키-밸류로 페어를 지정해서 번들로 날려줘야함
                arguments = bundleOf(
                    RESTAURANT_CATEGORY_KEY to restaurantCategory
                )
            }
        }
    }

}