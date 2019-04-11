package no.jlwcrews.dmcv2.views.adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.recyclerview_expansion.view.*
import no.jlwcrews.dmcv2.R
import no.jlwcrews.dmcv2.db.models.NewGameContainer
import no.jlwcrews.dmcv2.db.entities.Expansion

class ExpansionListAdapter internal constructor(context: Context) : RecyclerView.Adapter<ExpansionListAdapter.ExpansionViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var expansions = emptyList<Expansion>()
    val newGameContainer: NewGameContainer = NewGameContainer()

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpansionViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_expansion, parent, false)
        return ExpansionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExpansionViewHolder, position: Int) {
        val current = expansions[position]

        holder.itemView.checkBoxExpansion.setOnClickListener{
            newGameContainer.expansions.set(current.expansionId, holder.itemView.checkBoxExpansion.isChecked)
        }
        holder.expansionNameView.text = current.expansionName
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
    }
}
