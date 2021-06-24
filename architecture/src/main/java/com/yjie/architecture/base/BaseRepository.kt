package com.yjie.architecture.base

import androidx.lifecycle.MutableLiveData
import com.yjie.architecture.http.ApiException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * <pre>
 *     author : yjie.liang
 *     time   : 2021/06/23
 *     desc   :
 *     version: 1.0
 * </pre>
 *
 * @param coroutineScope 注入viewModel的coroutineScope用于协程管理
 * @param errorLiveData 业务出错或者爆发异常，由errorLiveData通知视图层去处理
 *
 */

typealias Error = suspend (e: ApiException) -> Unit

open class BaseRepository {

    private lateinit var coroutineScope: CoroutineScope
    private lateinit var errorLiveData: MutableLiveData<ApiException>

    constructor(coroutineScope: CoroutineScope, errorLiveData: MutableLiveData<ApiException>) {
        this.coroutineScope = coroutineScope
        this.errorLiveData = errorLiveData
    }

    constructor()

    protected suspend fun <T> withIO(block: suspend () -> T): T {
        return withContext(Dispatchers.IO) {
            block.invoke()
        }
    }
}