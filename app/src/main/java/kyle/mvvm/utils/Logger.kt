package kyle.mvvm.utils

import android.util.Log

/**
 * Copyright (C) 2022 Kakao corp. All rights reserved.
 *
 */
enum class Category {
    UI,
    Domain,
    Data,
    Module,
    None
}

class Logger(
    private val tag: String
) {
    var useThreadInfo: Boolean = false

    var category: Category = Category.None

    fun verbose(message: String, error: Throwable? = null) = v(
        tag = mergedTag(tag, category),
        message = mergedMessage(message, useThreadInfo),
        error = error
    )

    fun debug(message: String, error: Throwable? = null) = d(
        tag = mergedTag(tag, category),
        message = mergedMessage(message, useThreadInfo),
        error = error
    )

    fun info(message: String, error: Throwable? = null) = i(
        tag = mergedTag(tag, category),
        message = mergedMessage(message, useThreadInfo),
        error = error
    )

    fun warn(message: String, error: Throwable? = null) = w(
        tag = mergedTag(tag, category),
        message = mergedMessage(message, useThreadInfo),
        error = error
    )

    fun error(message: String, error: Throwable? = null) = e(
        tag = mergedTag(tag, category),
        message = mergedMessage(message, useThreadInfo),
        error = error
    )

    private fun mergedTag(tag: String, category: Category): String {
        return when (category) {
            Category.None -> tag
            else -> "$tag[${category.name}]"
        }
    }

    private fun mergedMessage(message: String, withThreadInfo: Boolean): String {
        return if (withThreadInfo) "${getThreadInfo()} $message"
        else message
    }

    private fun getThreadInfo(): String = Thread.currentThread().run { "[${name.take(4)}-$id]" }

    companion object {

        fun v(tag: String, message: String, error: Throwable? = null) {
            printLog(Log.VERBOSE, tag, message, error)
        }

        fun d(tag: String, message: String, error: Throwable? = null) {
            printLog(Log.DEBUG, tag, message, error)
        }

        fun i(tag: String, message: String, error: Throwable? = null) {
            printLog(Log.INFO, tag, message, error)
        }

        fun w(tag: String, message: String, error: Throwable? = null) {
            printLog(Log.WARN, tag, message, error)
        }

        fun e(tag: String, message: String, error: Throwable? = null) {
            printLog(Log.ERROR, tag, message, error)
        }

        private fun printLog(priority: Int, tag: String, message: String, error: Throwable?) {
            when (priority) {
                Log.VERBOSE -> Log.v(tag, message, error)
                Log.DEBUG -> Log.d(tag, message, error)
                Log.INFO -> Log.i(tag, message, error)
                Log.WARN -> Log.w(tag, message, error)
                Log.ERROR -> Log.e(tag, message, error)
            }
        }
    }
}