package com.lele.utils

import java.io.InputStream

/**
 * Created by 南加乐 on 2018/1/17.
 * 将字流转化为字符串
 */
object InputStream2String{
    private var b:ByteArray?=null//声明每次读取的字节数
    private var len:Int=0  //将每次读取的长度存入此变量
    private var arr:StringBuffer?=null //将每次读取的内容存入此变量
    fun in2str(inputstream:InputStream):String{
        b=ByteArray(2048)//每次读取2048个字节
        len=inputstream.read(b)  //记录读取长度
        arr= StringBuffer() //实例化存储
        while (len>-1){//如果记录长度不等于-1就一直读
            arr!!.append(String(b!!,0,len)) //将每次读取内容追加到buffer
            len=inputstream.read(b) //再读
        }
        inputstream.close()//关流
        return arr.toString()
    }
}