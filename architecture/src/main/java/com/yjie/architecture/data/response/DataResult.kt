package com.yjie.architecture.data.response

class DataResult<T>(val mEntity: T, val mResponseStatus: ResponseStatus) {

    class ResponseStatus(var responseCode: String, var success: Boolean) {
        val source: Enum<ResultSource> = ResultSource.NETWORK

        init {
            success = true
        }
    }

    enum class ResultSource {
        NETWORK, DATABASE, LOCAL_FILE
    }

    interface Result<T> {
        fun onResult(dataResult: DataResult<T>)
    }
}