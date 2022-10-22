package app.android.internetradio.presentation.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import app.android.internetradio.R
import app.android.internetradio.databinding.ActivityMainBinding
import app.android.internetradio.presentation.viewmodels.ChannelListViewModel
import app.android.internetradio.presentation.views.adapters.ChannelListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class ChannelListActivity : AppCompatActivity() {

    private val viewModel: ChannelListViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private val adapter = ChannelListAdapter(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.adapter = adapter

        lifecycleScope.launchWhenStarted {
            viewModel.channelListFlow.collect {
                adapter.updateList(it)
            }
        }
        binding.button.setOnClickListener {
            viewModel.fetchChannelList()
        }

    }
}