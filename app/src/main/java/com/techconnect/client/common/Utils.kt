package com.techconnect.client.common

import android.content.Context
import com.airbnb.lottie.BuildConfig

//get versionName from build.gradle
fun getVersionName(context: Context): String {
    return try {
        val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        packageInfo.versionName
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}