package kyle.mvvm.extension

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Copyright (C) 2022 Kakao corp. All rights reserved.
 *
 */
// Fragment
inline fun <reified VM : ViewModel> Fragment.viewModels(factory: ViewModelProvider.Factory): VM {
    return ViewModelProvider(this, factory)[VM::class.java]
}

fun Fragment.toastShort(message: String) {
    toast(message, Toast.LENGTH_SHORT)
}

fun Fragment.toastLong(message: String) {
    toast(message, Toast.LENGTH_LONG)
}

private fun Fragment.toast(message: String, duration: Int) {
    Toast.makeText(context, message, duration).show()
}

fun Fragment.hideInputMethod(targetView: View) {
    val imm = context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    imm.hideSoftInputFromWindow(targetView.windowToken, 0)
    targetView.clearFocus()
}