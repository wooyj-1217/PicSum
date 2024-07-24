package com.wooyj.picsum.data.remote

data class NetworkSetting(
    val connectionTimeOut: Long,
    val readTimeOut: Long,
    val writeTimeOut: Long,
)
