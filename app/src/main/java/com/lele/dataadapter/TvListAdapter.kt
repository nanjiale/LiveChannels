package com.lele.dataadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.lele.activity.R
import com.lele.entity.Channels

/**
 * Created by 南加乐 on 2018/1/17.
 * 频道列表适配器
 */
class TvListAdapter(var listTv: Channels, var mcontext:Context):BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var inflate:View
                if (convertView!=null){
                    inflate=convertView
                }else {
                  inflate  = LayoutInflater.from(mcontext).inflate(R.layout.tv_item, parent, false)
                }
        inflate.findViewById<TextView>(R.id.tv_name).setText(listTv.channels!!.get(position).name)
        return inflate
                }

    override fun getItem(position: Int): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemId(position: Int): Long {
      return position.toLong()
    }

    override fun getCount(): Int {
       return listTv.channels!!.size
    }
}