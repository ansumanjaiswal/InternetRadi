package app.android.internetradio.presentation.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import app.android.internetradio.R
import app.android.internetradio.databinding.ActivityMainBinding
import app.android.internetradio.presentation.viewmodels.ChannelListViewModel
import app.android.internetradio.presentation.views.adapters.ChannelListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.progressBarVisibility.collect {
                    binding.progressBar.visibility = it
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.channelListFlow.collect {
                adapter.updateList(it)
            }
        }

        viewModel.getChannelListData()

        binding.find.setOnClickListener {
            lifecycleScope.launchWhenCreated {
                val filterList = viewModel.filterByDJ(binding.searchView.text.toString())
                adapter.updateList(filterList)
            }
        }

        binding.reset.setOnClickListener {
            binding.searchView.text.clear()
            adapter.updateList(viewModel.getChannelListData())
        }
    }
}

/*
fun <T> AppCompatActivity.collectLifecycleFlow(flow: Flow<T>, collect: suspend(T) -> Unit) {
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collect(collect)
        }
    }
}*/
