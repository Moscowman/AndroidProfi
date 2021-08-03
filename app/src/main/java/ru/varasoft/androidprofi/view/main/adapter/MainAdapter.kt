package ru.varasoft.androidprofi.view.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidprofi.databinding.ActivityMainRecyclerviewItemBinding
import ru.varasoft.androidprofi.model.data.DataModel

class MainAdapter(private var onListItemClickListener: OnListItemClickListener, private var data: List<DataModel>) :
    RecyclerView.Adapter<MainAdapter.RecyclerItemViewHolder>() {

    private lateinit var binding: ActivityMainRecyclerviewItemBinding

    fun setData(data: List<DataModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ActivityMainRecyclerviewItemBinding.inflate(layoutInflater)
        return RecyclerItemViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data.get(position))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(data: DataModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                binding.headerTextviewRecyclerItem.text = data.text
                binding.descriptionTextviewRecyclerItem.text = data.meanings?.get(0)?.translation?.translation

                itemView.setOnClickListener { openInNewWindow(data) }
            }
        }
    }

    private fun openInNewWindow(listItemData: DataModel) {
        onListItemClickListener.onItemClick(listItemData)
    }

    interface OnListItemClickListener {
        fun onItemClick(data: DataModel)
    }
}
