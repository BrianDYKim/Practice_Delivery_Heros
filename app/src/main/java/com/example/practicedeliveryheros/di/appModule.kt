package com.example.practicedeliveryheros.di

import com.example.practicedeliveryheros.data.network.MapApiService
import com.example.practicedeliveryheros.data.repository.map.DefaultMapRepository
import com.example.practicedeliveryheros.data.repository.map.MapRepository
import com.example.practicedeliveryheros.data.repository.restaurant.DefaultRestaurantRepository
import com.example.practicedeliveryheros.data.repository.restaurant.RestaurantRepository
import com.example.practicedeliveryheros.screen.home.HomeViewModel
import com.example.practicedeliveryheros.screen.home.restaurant.RestaurantCategory
import com.example.practicedeliveryheros.screen.home.restaurant.RestaurantListViewModel
import com.example.practicedeliveryheros.screen.my.MyViewModel
import com.example.practicedeliveryheros.util.provider.DefaultResourcesProvider
import com.example.practicedeliveryheros.util.provider.ResourcesProvider
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { HomeViewModel(get()) }
    viewModel { MyViewModel() }
    viewModel { (restaurantCategory: RestaurantCategory) -> RestaurantListViewModel(restaurantCategory, get()) }

    // Retrofit 관련 Dependency 주입
    single { provideGsonConverterFactory() }
    single { provideOkHttpClient() }
    single { provideMapRetrofit(get(), get()) }

    single { provideMapApiService(get()) }

    // Repository에 대한 의존성 주입
    single<MapRepository> { DefaultMapRepository(get(), get()) }
    single<RestaurantRepository> { DefaultRestaurantRepository(get(), get()) }

    // ResourcesProvider에 대한 의존성 주입
    single<ResourcesProvider> { DefaultResourcesProvider(androidContext()) }

    // Coroutine에 대한 Dispatchers 주입
    single { Dispatchers.IO }
    single { Dispatchers.Main }
}