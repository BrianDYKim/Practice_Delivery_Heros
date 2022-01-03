package com.example.practicedeliveryheros.widget.adapter.viewholder

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * RestaurantList를 관리하는 Fragment의 ViewPager에 달아줄 어댑터
 * @param fragment: Fragment -> 부모 프래그먼트
 * @param fragmentList: List<Fragment> -> 프래그먼트를 담아주는 리스트
 */
class RestaurantListFragmentPagerAdapter(
    fragment: Fragment,
    val fragmentList: List<Fragment>
): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position]
}