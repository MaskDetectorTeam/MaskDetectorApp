package com.example.maskdetector

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.util.Collections.min
import kotlin.math.min


class ImageAdapter(val urls: List<String>): RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameTextView = itemView.findViewById<TextView>(R.id.nameTextView)
        var photoImageView = itemView.findViewById<ImageView>(R.id.ImageView3)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.image_item,parent,false))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val url = urls[position]
        Glide.with(holder.itemView).load(url).into(holder.photoImageView)
        holder.nameTextView.text = getName(url.toString())
    }

    override fun getItemCount(): Int {
        return urls.size
    }

    fun getName(url: String): String {
        var name: String = ""
        var start: Boolean = false
        for(i in url.indices) {
            if(start) {
                if (url[i].isLetter())
                    name += url[i]
                else if (url[i] == '_' && url[i - 1] == '_')
                    name += ",\n"
                else if (url[i] == '_' && url[i - 1] != '_' && url[i + 1] != '_')
                    name += ' '
                else if (url[i] == '.')
                    start = false
            } else {
                if (url[i] == 'F' && url[i - 1] == '2' && url[i - 2] == '%')
                    start = true
            }
        }
        return name
    }
}