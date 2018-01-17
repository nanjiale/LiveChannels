package com.lele.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.lele.dataadapter.TvListAdapter
import com.lele.entity.Channels
import com.lele.utils.GsonUtils
import com.lele.utils.InputStream2String
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var channels: Channels?=null //声明频道变量
    private var sendplayview:Intent?=null //要跳转的播放界面
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setData();
    }

    /**
     * 设值数据
     */
    private fun setData() {
        val inputstream = assets.open("channels.json")  //通过assets打开json拿到一个流
        val channeljson = InputStream2String.in2str(inputstream);
       channels= GsonUtils.parseJson(Channels::class.java,channeljson) as Channels //通过解析得到channels变量
       lv_tv_name.adapter=TvListAdapter(channels!!,applicationContext)//给listview设置适配器
        lv_tv_name.setOnItemClickListener { parent, view, position, id ->
            sendplayview= Intent(applicationContext,PlayView::class.java)
            sendplayview!!.putExtra("url",channels!!.channels!!.get(position).url)
            sendplayview!!.putExtra("name",channels!!.channels!!.get(position).name)
            startActivity(sendplayview)
        }
    }

}
