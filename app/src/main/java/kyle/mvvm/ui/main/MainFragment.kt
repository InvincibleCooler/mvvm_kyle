package kyle.mvvm.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kyle.mvvm.databinding.FragmentMainBinding
import kyle.mvvm.utils.Category
import kyle.mvvm.utils.Logger
import kyle.mvvm.utils.viewBinding

@AndroidEntryPoint
class MainFragment : Fragment() {
    companion object {
        private const val TAG = "MainFragment"
    }

    private val log = Logger(TAG).apply {
        category = Category.UI
        useThreadInfo = true
    }

    private val viewModel: MainViewModel by viewModels()

    private var binding by viewBinding<FragmentMainBinding>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        log.info("onCreateView() savedInstanceState: $savedInstanceState")

        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        log.info("onViewCreated() savedInstanceState: $savedInstanceState")

        viewModel.books.observe(viewLifecycleOwner) { list ->
            log.debug("onViewCreated::books.observe() list size: ${list.size}")
        }
    }
}