package com.lele.utils

import android.content.Context
import android.net.ConnectivityManager

/**
 * 网络工具类
 * Created by 南加乐 on 2018/1/18.
 * 返回1表示移动数据 2表示wifi 3表示其他
 */
object NetWorkUtils{
    fun getnetwork(mcontext:Context):Int{
        val connectivityManager = mcontext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        if (networkInfo!=null&&networkInfo.isAvailable){
            return when(networkInfo.type){
                ConnectivityManager.TYPE_MOBILE->0
                ConnectivityManager.TYPE_WIFI->1
                else -> {
                    2
                }
            }

        }else{
            return 2
        }

    }
}