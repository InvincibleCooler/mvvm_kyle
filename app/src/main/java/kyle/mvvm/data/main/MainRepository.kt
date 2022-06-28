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

    fun searchBooks(query: String): Flow<List<BookInfo>> = flow {
        log.info("searchBooks() query: $query")

        val bookList = serviceApi.getSearch(query).books ?: emptyList()
        emit(bookList.also { log.debug("searchBooks() result: ${it.size}") })
    }
}