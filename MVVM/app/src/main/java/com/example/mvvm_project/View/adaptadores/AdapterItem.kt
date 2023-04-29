package com.example.mvvm_project.View.adaptadores

import android.content.ClipData.Item
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_project.Model.ItemPoke
import com.example.mvvm_project.R
import com.example.mvvm_project.databinding.ListItemBinding
import com.squareup.picasso.Picasso

class AdapterItem : ListAdapter<ItemPoke, AdapterItem.ViewHolder>(DiffCallBack) {

    lateinit var onClickItem: (ItemPoke) -> Unit

    inner class ViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        private val biding = ListItemBinding.bind(view)

        fun bind(ItemPoke: ItemPoke){
            biding.tvId.text = ItemPoke.idFormat
            biding.tvName.text = ItemPoke.name
            Picasso.get().load(ItemPoke.img).placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image)
                .into(biding.ivPokemon)

            view.setOnClickListener{
                onClickItem(ItemPoke)
            }
        }
    }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

   override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        return holder.bind(item)
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<ItemPoke>() {

        override fun areItemsTheSame(oldItem: ItemPoke, newItem: ItemPoke): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ItemPoke, newItem: ItemPoke): Boolean {
            return oldItem == newItem
        }

    }
}