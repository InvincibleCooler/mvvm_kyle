package kyle.mvvm.ui.book

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kyle.mvvm.databinding.FragmentBookSearchBinding
import kyle.mvvm.extension.hideInputMethod
import kyle.mvvm.extension.textString
import kyle.mvvm.extension.toastShort
import kyle.mvvm.utils.Category
import kyle.mvvm.utils.Logger
import kyle.mvvm.utils.viewBinding

@AndroidEntryPoint
class BookSearchFragment : Fragment() {
    companion object {
        private const val TAG = "BookSearchFragment"
    }

    private val log = Logger(TAG).apply {
        category = Category.UI
        useThreadInfo = true
    }

    private val viewModel: BookViewModel by viewModels()

    private var binding by viewBinding<FragmentBookSearchBinding>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        log.info("onCreateView() savedInstanceState: $savedInstanceState")

        binding = FragmentBookSearchBinding.inflate(inflater, container, false).apply {
            // EditText
            edittextBookQuery.setOnEditorActionListener { textView, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_SEARCH,
                    EditorInfo.IME_ACTION_DONE -> {
                        val searchQuery = textView.textString()
                        if (searchQuery.isEmpty()) {
                            toastShort("검색어를 입력해주세요.")
                            return@setOnEditorActionListener false
                        }

                        hideInputMethod(textView)
                        viewModel.search(searchQuery)
                    }
                }

                false
            }

            // RecyclerView
            recyclerviewBook.adapter = BookAdapter()
        }

        viewModel.bookUiState.observe(viewLifecycleOwner) { uiState ->
            updateRecyclerView(uiState)
        }

        return binding.root
    }

    private fun updateRecyclerView(uiState: BookUiState) {
        log.info("updateRecyclerView() uiState: $uiState")

        binding.recyclerviewBook.run {
            visibility = when {
                uiState.isEmptyResult() -> View.GONE
                else -> View.VISIBLE
            }

            val bookAdapter = adapter as? BookAdapter ?: return
            bookAdapter.submitList(uiState.books)
        }
    }
}