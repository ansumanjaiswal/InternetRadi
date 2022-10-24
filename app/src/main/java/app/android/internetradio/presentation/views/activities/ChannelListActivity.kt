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

        observeDataFetchProgress()

        observeChannelListData()

        handleFindButtonClick()

        handleResetButtonClick()

        observeErrorState()

        viewModel.getChannelListData()
    }

    private fun observeDataFetchProgress() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.progressBarVisibility.collect {
                    binding.progressBar.visibility = it
                }
            }
        }
    }

    private fun observeChannelListData() {
        lifecycleScope.launchWhenStarted {
            viewModel.channelListFlow.collect {
                adapter.updateList(it)
            }
        }
    }

    private fun handleFindButtonClick() {
        binding.find.setOnClickListener {
            lifecycleScope.launchWhenCreated {
                val filterList = viewModel.filterByDJ(binding.searchView.text.toString())
                adapter.updateList(filterList)
            }
        }
    }

    private fun handleResetButtonClick() {
        binding.reset.setOnClickListener {
            binding.searchView.text.clear()
            adapter.updateList(viewModel.getChannelListData())
        }
    }

    private fun observeErrorState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.errorMessage.collect {
                    binding.errorText.visibility = View.VISIBLE
                    binding.errorText.text = it
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.errorMessageVisibility.collect {
                    binding.errorText.visibility = it
                }
            }
        }
    }
}

