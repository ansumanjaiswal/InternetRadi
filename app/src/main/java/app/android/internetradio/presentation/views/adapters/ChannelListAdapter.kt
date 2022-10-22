package app.android.internetradio.presentation.views.adapters

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import app.android.internetradio.R
import app.android.internetradio.data.view.ChannelViewData
import app.android.internetradio.presentation.views.activities.ChannelDetailsActivity
import com.bumptech.glide.Glide

class ChannelListAdapter(var channelList: List<ChannelViewData>) :
    RecyclerView.Adapter<ChannelListAdapter.ChannelListViewHolder>() {

    fun updateList(updatedList: List<ChannelViewData>) {
        channelList = updatedList
        notifyDataSetChanged()
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
        val view = LayoutInflater.from(parent.context).inflate(R.layout.channel_list_item, parent, false)
        return ChannelListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChannelListViewHolder, position: Int) {
        holder.title.text = channelList[position].title
        holder.description.text = channelList[position].description
        holder.dj.text = channelList[position].dj
        Glide
            .with(holder.itemView.context)
            .load(channelList[position].iconUrl)
            .centerCrop()
            .placeholder(R.drawable.radio_default_image)
            .into(holder.icon)
        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, ChannelDetailsActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable("channelData", channelList[position])
            intent.putExtras(bundle)
            it.context.startActivity(intent)
            //Toast.makeText(it.context, "CLICKED ${channelList[position].title}", Toast.LENGTH_LONG).show()
        }

    }

    override fun getItemCount(): Int {
        return channelList.size
    }
}