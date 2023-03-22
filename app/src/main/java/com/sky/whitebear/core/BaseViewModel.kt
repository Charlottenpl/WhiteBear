package com.sky.whitebear.core


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sky.whitebear.date.bean.BaseResult
import kotlinx.coroutines.launch


/**
 * viewModel通用处理
 */

typealias ViewStateMutableLiveData<T> = MutableLiveData<ViewState<T>>
typealias ViewStateLiveData<T> = LiveData<ViewState<T>>

open class BaseViewModel : ViewModel() {

    /**
     * livedata通用处理
     */
    protected fun <T : BaseResult> launch(
        liveData: ViewStateMutableLiveData<T>? = null,
        handleResult: ((T) -> Unit)? = null,
        judgeEmpty: ((T) -> Boolean)? = null,
        call: suspend () -> T
    ) {
        viewModelScope.launch {
            runCatching {
                liveData?.let {
                    it.value = ViewState.Loading
                }
                call()
            }.onSuccess { result ->
                if (result.code == 200) {
                    if (judgeEmpty?.invoke(result) == true) {
                        liveData?.let {
                            it.value = ViewState.Empty
                        }
                    } else {
                        handleResult?.invoke(result)
                        liveData?.let {
                            it.value = ViewState.Success(result)
                        }
                    }
                } else {
                    liveData?.let {
                        it.value = ViewState.Fail(result.code.toString(), result.msg ?: "请求出错")
                    }
                }
            }.onFailure { e ->
                liveData?.let {
                    it.value = ViewState.Error(e)
                }
            }
        }
    }
}