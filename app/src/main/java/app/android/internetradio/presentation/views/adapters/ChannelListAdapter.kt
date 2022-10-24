package app.android.internetradio.presentation.views.adapters

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import app.android.internetradio.R
import app.android.internetradio.data.view.ChannelViewData
import app.android.internetradio.loadImage
import app.android.internetradio.presentation.views.activities.ChannelDetailsActivity
import com.bumptech.glide.Glide

class ChannelListAdapter(var channelList: List<ChannelViewData>) :
    RecyclerView.Adapter<ChannelListAdapter.ChannelListViewHolder>() {

    fun updateList(updatedList: List<ChannelViewData>) {
        channelList = updatedList
        this.notifyDataSetChanged()
    }

    class ChannelListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView
        val description: TextView
        val dj: TextView
        val icon: AppCompatImageView

        init {
            title = view.findViewById(R.id.title)
            description = view.findViewById(R.id.description)
            dj = view.findViewById(R.id.dj)
            icon = view.findViewById(R.id.radioIcon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.channel_list_item, parent, false)
        return ChannelListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChannelListViewHolder, position: Int) {
        holder.title.text = channelList[position].title
        holder.description.text = channelList[position].description
        holder.dj.text = channelList[position].dj
        // Created and extension function for imageview to load m=image from url
        // Provides ease of use,
        // and could be handy if we ever want to switch the library providing image loading
        holder.icon.loadImage(channelList[position].iconUrl, R.drawable.radio_default_image)

        // A better way of handling click is to pass an interface to the adapter
        // and invoke the interface method on click, and handle the logic of click separately

        // I would also not directly pass data to the activity,
        // but pass the id to the data object the needs to be shown to the activity
        // and let the viewModel of the details activity fetch the data and load the details

        // Another way to do this is having the details view as a fragment,
        // and that way the fragment can share the ViewModel and load the details view

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, ChannelDetailsActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable("channelData", channelList[position])
            intent.putExtras(bundle)
            it.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return channelList.size
    }
}
