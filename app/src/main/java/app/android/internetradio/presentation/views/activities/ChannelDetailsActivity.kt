package app.android.internetradio.presentation.views.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.android.internetradio.R
import app.android.internetradio.data.view.ChannelViewData
import app.android.internetradio.databinding.ActivityChannelDetailsBinding
import com.bumptech.glide.Glide

class ChannelDetailsActivity: AppCompatActivity() {

    private lateinit var binding: ActivityChannelDetailsBinding
    private var data: ChannelViewData? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChannelDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        data = intent.getParcelableExtra("channelData")

        if(data != null) {
            binding.title.text = data?.title
            binding.dj.text = data?.dj
            binding.djEmail.text = data?.djMail
            binding.numberOfListeners.text = data?.listeners
            binding.genre.text = data?.genre

            Glide
                .with(this)
                .load(data?.largeImageUrl)
                .centerCrop()
                .placeholder(R.drawable.radio_default_image)
                .into(binding.icon)
        }

    }
}