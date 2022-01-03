package com.example.practicedeliveryheros.di

import com.example.practicedeliveryheros.data.repository.DefaultRestaurantRepository
import com.example.practicedeliveryheros.data.repository.RestaurantRepository
import com.example.practicedeliveryheros.screen.home.HomeViewModel
import com.example.practicedeliveryheros.screen.home.restaurant.RestaurantCategory
import com.example.practicedeliveryheros.screen.home.restaurant.RestaurantListViewModel
import com.example.practicedeliveryheros.screen.my.MyViewModel
import com.example.practicedeliveryheros.util.provider.DefaultResourcesProvider
import com.example.practicedeliveryheros.util.provider.ResourcesProvider
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import kotlin.math.sin

val appModule = module {

    viewModel { HomeViewModel() }
    viewModel { MyViewModel() }
    viewModel { (restaurantCategory: RestaurantCategory) -> RestaurantListViewModel(restaurantCategory, get()) }

    // Retrofit 관련 Dependency 주입
    single { provideGsonConverterFactory() }
    single { provideOkHttpClient() }
    single { provideRetrofit(get(), get()) }

    // Repository에 대한 의존성 주입
    single<RestaurantRepository> { DefaultRestaurantRepository(get(), get()) }

    // ResourcesProvider에 대한 의존성 주입
    single<ResourcesProvider> { DefaultResourcesProvider(androidContext()) }

    // Coroutine에 대한 Dispatchers 주입
    single { Dispatchers.IO }
    single { Dispatchers.Main }
}