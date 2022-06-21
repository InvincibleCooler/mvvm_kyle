package kyle.mvvm.data.main

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kyle.mvvm.data.Repository
import kyle.mvvm.net.RequestManager
import kyle.mvvm.utils.Category
import kyle.mvvm.utils.Logger

/**
 * Copyright (C) 2022 Kakao corp. All rights reserved.
 *
 */
class MainRepository : Repository {
    companion object {
        private const val TAG = "MainRepository"
    }

    private val log = Logger(TAG).apply {
        category = Category.Data
        useThreadInfo = true
    }

    fun testCode() {
        log.info("testCode()")

        CoroutineScope(Dispatchers.IO).launch {
            val res = RequestManager.getServiceApi().getSearch("kotlin", "0")
            log.debug("testCode() res: $res")
        }
    }
}