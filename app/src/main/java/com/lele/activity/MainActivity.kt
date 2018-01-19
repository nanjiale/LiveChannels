package com.lele.activity

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Toast
import com.lele.dataadapter.TvListAdapter
import com.lele.entity.Channels
import com.lele.utils.GsonUtils
import com.lele.utils.InputStream2String
import com.lele.utils.NetWorkUtils
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var create:AlertDialog?=null
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
        builddialog()
        val inputstream = assets.open("channels.json")  //通过assets打开json拿到一个流
        val channeljson = InputStream2String.in2str(inputstream);
       channels= GsonUtils.parseJson(Channels::class.java,channeljson) as Channels //通过解析得到channels变量
       lv_tv_name.adapter=TvListAdapter(channels!!,applicationContext)//给listview设置适配器
        lv_tv_name.setOnItemClickListener { parent, view, position, id ->
            sendplayview= Intent(applicationContext,PlayView::class.java)
            sendplayview!!.putExtra("url",channels!!.channels!!.get(position).url)
            sendplayview!!.putExtra("name",channels!!.channels!!.get(position).name)
            if (NetWorkUtils.getnetwork(applicationContext)==0){
               create!!.show()
            }else if (NetWorkUtils.getnetwork(applicationContext)==1){
                startActivity(sendplayview)
            }else{
                Toast.makeText(this,"当前网络异常",Toast.LENGTH_SHORT).show()
            }

        }
    }

    /**
     * 构建diaglog
     * 当用户使用流量时 提醒用户是否继续观看  否退出 是继续观看
     */
    private fun builddialog() {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        builder.setTitle("温馨提示")
        builder.setMessage("当前使用的是移动流量，是否继续观看?")

        builder.setPositiveButton("是",object :DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                startActivity(sendplayview)
                create!!.dismiss()
            }
        })
        builder.setNegativeButton("否",object :DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                create!!.dismiss()
            }
        })
        create= builder.create()
    }

}
