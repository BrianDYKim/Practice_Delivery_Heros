package com.example.practicedeliveryheros.screen.home

import com.example.practicedeliveryheros.databinding.FragmentHomeBinding
import com.example.practicedeliveryheros.screen.base.BaseFragment
import com.example.practicedeliveryheros.screen.home.restaurant.RestaurantCategory
import com.example.practicedeliveryheros.screen.home.restaurant.RestaurantListFragment
import com.example.practicedeliveryheros.widget.adapter.viewholder.RestaurantListFragmentPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    override val viewModel by viewModel<HomeViewModel>()

    override fun getViewBinding(): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)

    private lateinit var viewPagerAdapter: RestaurantListFragmentPagerAdapter

    override fun observeData() {

    }

    override fun initViews() {
        super.initViews()
        initViewPager()
    }

    private fun initViewPager() = with(binding) {
        val restaurantCategories = RestaurantCategory.values()

        if (::viewPagerAdapter.isInitialized.not()) {
            val restaurantFragmentList = restaurantCategories.map {
                RestaurantListFragment.newInstance(it)
            }

            viewPagerAdapter = RestaurantListFragmentPagerAdapter(
                this@HomeFragment,
                restaurantFragmentList
            )

            viewPager.adapter = viewPagerAdapter
        }

        viewPager.offscreenPageLimit = restaurantCategories.size

        // TabLayout과 viewPager를 연결
        TabLayoutMediator(tablayout, viewPager) {tab, position ->
            tab.setText(restaurantCategories[position].categoryNameId)
        }.attach()
    }

    companion object {
        const val TAG = "HomeFragment"

        fun newInstance() = HomeFragment()
    }

}