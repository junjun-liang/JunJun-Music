package com.yjie.architecture.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yjie.architecture.http.ApiException
import com.yjie.architecture.common.toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONException
import java.net.ConnectException
import java.net.HttpRetryException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.CancellationException

/**
 * 给 (e : ApiException) -> Unit 取个别名
 */
typealias VmError = (e: ApiException) -> Unit

open class BaseViewModel : ViewModel() {

    /**
     * 错误信息
     */
    val errorLiveData = MutableLiveData<ApiException>()

    /**
     * 无更多数据
     */
    val footLiveData = MutableLiveData<Any>()

    /**
     * 无数据
     */
    val emptyLiveData = MutableLiveData<Any>()

    /**
     * 处理错误信息
     */
    fun handleError(e: Throwable) {
        val error = getApiException(e)
        toast(error.errorMsg)
        errorLiveData.postValue(error)
    }

    /**
     * 捕获异常信息
     */
    fun getApiException(e: Throwable): ApiException {
        return when (e) {
            is UnknownHostException -> {
                ApiException("网络异常", -100)
            }
            is JSONException -> {
                ApiException("数据异常", -100)
            }
            is SocketTimeoutException -> {
                ApiException("链接超时", -100)
            }
            is ConnectException -> {
                ApiException("链接错误", -100)
            }
            is HttpRetryException -> {
                ApiException("重试错误", -100)
            }
            is ApiException -> e
            /**
             * 如果协程还在运行，个别机型退出当前界面时，viewModel会通过抛出CancellationException，
             * 强行结束协程，与java中InterruptException类似，所以不必理会,只需将toast隐藏即可
             */
            is CancellationException -> {
                ApiException("", -100)
            }
            else -> ApiException("未知错误", -100)
        }
    }

    protected fun <T> launch(block:()->T, error:VmError? = null){
        viewModelScope.launch {
            runCatching{
                block()
            }.onFailure {
                it.printStackTrace()
                getApiException(it).apply {
                    withContext(Dispatchers.Main){
                        error?.invoke(this@apply)
                        toast(errorMsg)
                    }
                }
            }
        }
    }

    protected fun <T> launch(block: suspend () -> T) {
        viewModelScope.launch {
            runCatching {
                block()
            }.onFailure {
                //if (BuildConfig.DeBUG)
                getApiException(it).apply {
                    withContext(Dispatchers.Main) {
                        toast(errorMsg)
                        errorLiveData.value = this@apply
                    }
                }
            }
        }
    }

    /**
     * 处理列表是否有更多数据
     */
    protected fun <T> handleList(listLiveData: LiveData<MutableList<T>>, pageSize: Int) {
        val listSize = listLiveData.value?.size ?: 0
        if (listSize % pageSize != 0) {
            footLiveData.value = 1
        }
    }
}