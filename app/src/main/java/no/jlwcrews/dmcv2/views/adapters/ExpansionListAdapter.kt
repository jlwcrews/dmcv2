package no.jlwcrews.dmcv2.views.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import no.jlwcrews.dmcv2.R
import no.jlwcrews.dmcv2.db.entities.Expansion

class ExpansionListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<ExpansionListAdapter.ExpansionViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var expansions = emptyList<Expansion>() // Cached copy of words

    inner class ExpansionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val expansionItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpansionViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return ExpansionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExpansionViewHolder, position: Int) {
        val current = expansions[position]
        holder.expansionItemView.text = current.expansionName
    }

    internal fun setExpansions(expansions: List<Expansion>) {
        this.expansions = expansions
        notifyDataSetChanged()
    }

    override fun getItemCount() = expansions.size
}