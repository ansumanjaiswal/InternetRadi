package app.android.internetradio

import android.widget.ImageView
import com.bumptech.glide.Glide


fun ImageView.loadImage(url: String, defaulDrawanle: Int) {
    Glide
        .with(this.context)
        .load(url)
        .centerCrop()
        .placeholder(defaulDrawanle)
        .into(this)
}