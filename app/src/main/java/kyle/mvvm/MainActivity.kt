package kyle.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kyle.mvvm.utils.Logger

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    private val log = Logger(TAG).apply {
        useThreadInfo = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        log.info("onCreate() savedInstanceState: $savedInstanceState")

        setContentView(R.layout.activity_main)
    }

    override fun onDestroy() {
        super.onDestroy()
        log.info("onDestroy()")
    }
}