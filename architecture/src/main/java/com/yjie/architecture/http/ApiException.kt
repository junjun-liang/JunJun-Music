package com.yjie.architecture.http

/**
 * 用来封装业务错误信息
 */
class ApiException(val errorMsg:String, val errorCode:Int) : Throwable()