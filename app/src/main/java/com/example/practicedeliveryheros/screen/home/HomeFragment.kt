package com.example.practicedeliveryheros.screen.home

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.example.practicedeliveryheros.R
import com.example.practicedeliveryheros.data.entity.LocationLatLngEntity
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

    private lateinit var locationManager: LocationManager

    private lateinit var myLocationListener: MyLocationListener

    private val locationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val responsePermissions = permissions.entries.filter {
                (it.key == Manifest.permission.ACCESS_FINE_LOCATION) ||
                        (it.key == Manifest.permission.ACCESS_COARSE_LOCATION)
            }

            // 위치 권한이 모두 설정이 되어있음
            if (responsePermissions.filter { it.value == true }.size == locationPermissions.size) {
                // 위치를 불러오면 된다
                setMyLocationListener()
            } else {
                with(binding.locationTitleText) {
                    setText(R.string.please_setup_your_location_permission)

                    setOnClickListener {
                        getMyLocation()
                    }

                    Toast.makeText(
                        requireContext(),
                        getString(R.string.cannot_assigned_permission),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    // LiveData를 제어
    override fun observeData() = viewModel.homeStateLiveData.observe(viewLifecycleOwner) {
        when (it) {
            is HomeState.Uninitialized -> {
                getMyLocation()
            }

            is HomeState.Loading -> {
                with(binding) {
                    locationLoading.isVisible = true
                    locationTitleText.text = getString(R.string.loading)
                }
            }

            is HomeState.Success -> {
                with(binding) {
                    locationLoading.isGone = true
                    locationTitleText.text = it.mapSearchInfo.fullAddress
                    initViewPager(it.mapSearchInfo.locationLatLng)
                }
            }

            is HomeState.Error -> {
                Toast.makeText(requireContext(), it.messageId, Toast.LENGTH_SHORT).show()
            }
        }
    }

    // 나의 위치 정보를 받아오는 메소드
    private fun getMyLocation() {
        // locationManager가 초기화가 되어있는가?
        if (::locationManager.isInitialized.not()) {
            locationManager =
                requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager // locationManager 초기화
        }
        // GPS에 대한 기능이 존재하는지 검사를 한다
        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        if (isGpsEnabled) {
            locationPermissionLauncher.launch(locationPermissions)
        }
    }

    @SuppressLint("MissingPermission")
    private fun setMyLocationListener() {
        val minTime = 1500L
        val minDistance = 100f

        // myLocationListener가 초기화가 되어있지 않다면 초기화를 진행
        if (::myLocationListener.isInitialized.not()) {
            myLocationListener = MyLocationListener()
        }

        // 위치 변경을 요청한다
        with(locationManager) {
            requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                minTime,
                minDistance,
                myLocationListener
            )
            requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                minTime,
                minDistance,
                myLocationListener
            )
        }
    }

    private fun initViewPager(locationLatLngEntity: LocationLatLngEntity) = with(binding) {
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
        TabLayoutMediator(tablayout, viewPager) { tab, position ->
            tab.setText(restaurantCategories[position].categoryNameId)
        }.attach()
    }

    companion object {

        // 받아야하는 권한의 배열
        val locationPermissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

        const val TAG = "HomeFragment"

        fun newInstance() = HomeFragment()
    }

    private fun removeLocationListener() {
        if (::locationManager.isInitialized && ::myLocationListener.isInitialized) {
            locationManager.removeUpdates(myLocationListener)
        }
    }

    inner class MyLocationListener : LocationListener {

        override fun onLocationChanged(location: Location) {

            with(viewModel) {
                homeStateLiveData.value = HomeState.Loading

                loadReverseGeoInformation(
                    LocationLatLngEntity(
                        latitude = location.latitude,
                        longitude = location.longitude
                    )
                )
            }

            removeLocationListener()
        }
    }

}