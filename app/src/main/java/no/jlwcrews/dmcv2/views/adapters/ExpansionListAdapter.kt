package no.jlwcrews.dmcv2.views.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.SelectionTracker
import no.jlwcrews.dmcv2.R
import no.jlwcrews.dmcv2.db.entities.Expansion

class ExpansionListAdapter internal constructor(context: Context) : RecyclerView.Adapter<ExpansionListAdapter.ExpansionViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var expansions = emptyList<Expansion>()
    var tracker: SelectionTracker<Long>? = null

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpansionViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_expansion, parent, false)
        return ExpansionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExpansionViewHolder, position: Int) {
        val current = expansions[position]
        holder.expansionNameView.text = current.expansionName

        tracker?.let { holder.bind(current, it.isSelected(position.toLong())) }

        val parent = holder.expansionNameView.parent as LinearLayout


    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    internal fun setExpansions(expansions: List<Expansion>) {
        this.expansions = expansions
        notifyDataSetChanged()
    }

    override fun getItemCount() = expansions.size

    inner class ExpansionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val expansionNameView: TextView = itemView.findViewById(R.id.expansionNameView)

        fun bind(value: Expansion, isActivated: Boolean = false) {
            expansionNameView.text = value.expansionName
            itemView.isActivated = isActivated
        }

        fun getExpansionDetails(): ItemDetailsLookup.ItemDetails<Long> =
            object: ItemDetailsLookup.ItemDetails<Long>() {
                override fun getPosition(): Int = adapterPosition

                override fun getSelectionKey(): Long? = itemId
            }
    }

}