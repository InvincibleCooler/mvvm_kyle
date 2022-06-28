package kyle.mvvm.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.catch
import kyle.mvvm.data.main.MainRepository
import kyle.mvvm.net.res.BookInfo
import kyle.mvvm.utils.Category
import kyle.mvvm.utils.Logger


class MainViewModel(
    repository: MainRepository
) : ViewModel() {
    companion object {
        private const val TAG = "MainViewModel"
    }

    private val log = Logger(TAG).apply {
        category = Category.UI
        useThreadInfo = true
    }

    val books: LiveData<List<BookInfo>> = repository.books
        .catch { log.error("books error", it) }
        .asLiveData()

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