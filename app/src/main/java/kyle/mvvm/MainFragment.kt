package kyle.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kyle.mvvm.databinding.FragmentMainBinding
import kyle.mvvm.utils.Category
import kyle.mvvm.utils.Logger
import kyle.mvvm.utils.viewBinding


class MainFragment : Fragment() {
    companion object {
        private const val TAG = "MainFragment"
    }

    private val log = Logger(TAG).apply {
        useThreadInfo = true
        category = Category.UI
    }

    private var binding by viewBinding<FragmentMainBinding>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        log.info("onCreateView() savedInstanceState: $savedInstanceState")

        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }
}