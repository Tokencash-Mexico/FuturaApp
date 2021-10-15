package com.futura.app.application

import android.app.Application
import android.os.StrictMode
import androidx.viewbinding.BuildConfig
import com.futura.app.di.Modules.applicationModule
import com.futura.app.di.Modules.commonModule
import com.futura.app.di.Modules.dataModule
import com.futura.app.di.Modules.domainModule
import com.futura.app.di.Modules.uiModule
import com.futura.app.di.Modules.viewModelModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@FlowPreview
@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class App: Application() {

    override fun onCreate() {

        //Strict mode for debugging only
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build()
            )
            StrictMode.setVmPolicy(
                StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build()
            )
        }

        super.onCreate()
        //Koin dependency injection
        startKoin {
            androidContext(this@App)
            modules(
                applicationModule,
                uiModule,
                domainModule,
                commonModule,
                dataModule,
                viewModelModule
            )
        }
    }
}