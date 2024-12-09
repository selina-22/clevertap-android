package com.example.clevertapdemo

import android.app.Application
import com.clevertap.android.sdk.ActivityLifecycleCallback
import com.clevertap.android.sdk.CleverTapAPI

class MyApplication : Application() {
    override fun onCreate() {
        ActivityLifecycleCallback.register(this)
        super.onCreate()

        // Enable CleverTap debugging
        CleverTapAPI.setDebugLevel(CleverTapAPI.LogLevel.DEBUG)
    }
}