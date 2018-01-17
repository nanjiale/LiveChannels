package com.lele.activity

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import io.vov.vitamio.MediaPlayer
import io.vov.vitamio.Vitamio
import kotlinx.android.synthetic.main.activity_play_view.*
import java.util.*

class PlayView : AppCompatActivity(), OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_view)
        setOnclck();
        setData()

    }
//设置点击事件
    private fun setOnclck() {
        back.setOnClickListener(this)
    palyname.setOnClickListener(this)
    is_play.setOnClickListener(this)
    }

    private fun setData() {
        tv_name.setText(intent.getStringExtra("name"))//将上个页面设置的值显示在顶栏
        Vitamio.isInitialized(applicationContext)//检查视频组件是否已经准备就绪
        tv_display.setVideoURI(Uri.parse(intent.getStringExtra("url")))//设置直播地址
        tv_display.setOnPreparedListener { //添加准备事件监听
            tv_display.start() //开始播放
            is_play.setImageResource(R.drawable.stop) //设置播放按钮为暂停
            loadvideo.visibility=View.GONE
            showorhidebar(false)
        }
        tv_display.setOnInfoListener { mp, what, extra ->
            when(what){
                MediaPlayer.MEDIA_INFO_BUFFERING_START->  loadvideo.visibility=View.VISIBLE//开始缓存时将等待圆圈显示出来
                MediaPlayer.MEDIA_INFO_BUFFERING_END->loadvideo.visibility=View.GONE //结束缓存时 将圆圈隐藏
            }

            true
        }

    }
    fun showorhidebar(isshow:Boolean) =//设置是否显示工具栏
            if (isshow){
                toolsbar.visibility=View.VISIBLE
                playdock.visibility=View.VISIBLE
                val timer=Timer()
                val timerTask = object :TimerTask(){
                    override fun run() {
                        runOnUiThread{toolsbar.visibility= View.GONE
                            playdock.visibility=View.GONE}
                    }
                }
                timer.schedule(timerTask,3000) //如果显示了  3秒没操作  就将其隐藏
            }else{
                toolsbar.visibility= View.GONE
                playdock.visibility=View.GONE
            }
    override fun onClick(v: View?) {
        when(v?.id){
           R.id.back->finish() //返回键点击事件  点击后结束当前页
            R.id.palyname->{//给界面添加点击事件  如果当前工具栏显示则隐藏  如果隐藏则显示
                if (toolsbar.visibility==View.VISIBLE||playdock.visibility==View.VISIBLE){
                    showorhidebar(false)
                }else{
                    showorhidebar(true)
                }

            }
            R.id.is_play->{//给播放按钮添加点击事件  如果显示则隐藏  如果隐藏则显示
                if (tv_display.isPlaying){
                    tv_display.pause()
                    is_play.setImageResource(R.drawable.play)
                }else{
                    tv_display.start()
                    is_play.setImageResource(R.drawable.stop)
                }
            }
        }
    }

}
