package com.example.practicedeliveryheros

import android.app.Application
import android.content.Context
import com.example.practicedeliveryheros.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PracticeDeliveryHerosApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = this

        startKoin {
            androidContext(this@PracticeDeliveryHerosApplication)
            modules(appModule)
        }
    }

    override fun onTerminate() {
        appContext = null
        super.onTerminate()
    }

    // AppContext를 Singleton 패턴으로 가져와야하기 때문에 static으로 선언을 해줘야한다.
    companion object {
        var appContext: Context? = null
            private set
    }
}