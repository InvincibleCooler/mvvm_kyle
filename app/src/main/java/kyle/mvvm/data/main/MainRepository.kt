package kyle.mvvm.data.main

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kyle.mvvm.data.Repository
import kyle.mvvm.net.ServiceApi
import kyle.mvvm.net.res.BookInfo
import kyle.mvvm.utils.Category
import kyle.mvvm.utils.Logger

/**
 * Copyright (C) 2022 Kakao corp. All rights reserved.
 *
 */
class MainRepository @Inject constructor(
    private val serviceApi: ServiceApi
) : Repository {
    companion object {
        private const val TAG = "MainRepository"
    }

    private val log = Logger(TAG).apply {
        category = Category.Data
        useThreadInfo = true
    }

    val books: Flow<List<BookInfo>> = flow {
        val bookList = serviceApi.getSearch("Kotlin").books ?: emptyList()
        log.debug("books::flow() bookList: ${bookList.size}")

        emit(bookList)
    }
}