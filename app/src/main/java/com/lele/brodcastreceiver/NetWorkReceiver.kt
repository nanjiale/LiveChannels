package com.lele.brodcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.lele.activity.MainActivity
import com.lele.utils.NetWorkUtils

class NetWorkReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val i = NetWorkUtils.getnetwork(context)
        if (i==0){
            Toast.makeText(context,"当前正在使用移动数据",Toast.LENGTH_SHORT).show()
        }else if (i==1){
            Toast.makeText(context,"当前正在使用WIFI",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context,"当前网络异常",Toast.LENGTH_SHORT).show()
            val intents= Intent(context,MainActivity::class.java)
            intents.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            context.startActivity(intents)
        }
    }
}
