package kyle.mvvm.utils

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