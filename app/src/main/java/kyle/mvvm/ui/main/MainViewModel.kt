package kyle.mvvm.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.catch
import kyle.mvvm.data.main.MainRepository
import kyle.mvvm.net.res.BookInfo
import kyle.mvvm.utils.Category
import kyle.mvvm.utils.Logger

@HiltViewModel
class MainViewModel @Inject constructor(
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
}