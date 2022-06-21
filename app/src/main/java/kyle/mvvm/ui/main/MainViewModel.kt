package kyle.mvvm.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kyle.mvvm.data.main.MainRepository
import kyle.mvvm.utils.Category
import kyle.mvvm.utils.Logger


class MainViewModel(
    private val repository: MainRepository
) : ViewModel() {
    companion object {
        private const val TAG = "MainViewModel"


    }

    private val log = Logger(TAG).apply {
        category = Category.UI
        useThreadInfo = true
    }

    init {
        log.info("init()")
    }

    override fun onCleared() {
        log.info("onCleared()")

        // 활동용이다 by 인리더
    }

    fun testCode() {
        log.debug("testCode()")

        repository.testCode()
    }

    class Factory(
        private val repository: MainRepository
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                return MainViewModel(repository) as T
            }

            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}