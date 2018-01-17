package com.lele.utils

import com.google.gson.Gson
import java.io.InputStream

/**
 * Created by 南加乐 on 2018/1/17.
 * 解析json工具类
 */

object GsonUtils {
    fun parseJson(c: Class<*>, jsonString: String): Any {
        val gson = Gson()
        return gson.fromJson(jsonString, c)
    }
}
