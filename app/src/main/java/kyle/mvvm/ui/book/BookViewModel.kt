package kyle.mvvm.ui.book

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kyle.mvvm.data.book.BookRepository
import kyle.mvvm.di.IoDispatcher
import kyle.mvvm.net.res.BookInfo
import kyle.mvvm.utils.Category
import kyle.mvvm.utils.Logger

data class BookUiState(
    val books: List<BookInfo>
)

fun BookUiState.isEmptyResult(): Boolean = books.isEmpty()

@HiltViewModel
class BookViewModel @Inject constructor(
    private val repository: BookRepository,
    @IoDispatcher val ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    companion object {
        private const val TAG = "BookViewModel"
    }

    private val log = Logger(TAG).apply {
        category = Category.UI
        useThreadInfo = true
    }

    private val _bookUiState = MutableLiveData(BookUiState(emptyList()))
    val bookUiState: LiveData<BookUiState> = _bookUiState

    fun search(query: String) {
        log.info("search() query: $query")

        viewModelScope.launch {
            repository.searchBooks(query)
                .flowOn(ioDispatcher)
                .catch { log.error("Search error", it) }
                .collect {
                    _bookUiState.postValue(BookUiState(it))
                }
        }
    }
}