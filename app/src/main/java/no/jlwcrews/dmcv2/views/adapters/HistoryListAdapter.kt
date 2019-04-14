package no.jlwcrews.dmcv2.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import no.jlwcrews.dmc.db.entities.History
import no.jlwcrews.dmcv2.R

class HistoryListAdapter internal constructor(context: Context) : RecyclerView.Adapter<HistoryListAdapter.HistoryViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var history = emptyList<History>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_history, parent, false)
        return HistoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val current = history[position]
        holder.historyIdView.text = current.historyId.toString()
        holder.historyDateView.text = current.historyDate
        holder.historyLocationView.text = current.historyLocation
        holder.historyScenarioId.text = current.historyScenarioId.toString()
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    internal fun setHistory(history: List<History>) {
        this.history = history
        notifyDataSetChanged()
    }

    override fun getItemCount() = history.size

    inner class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val historyIdView: TextView = itemView.findViewById(R.id.historyIdView)
        val historyDateView: TextView = itemView.findViewById(R.id.historyDateView)
        val historyLocationView: TextView = itemView.findViewById(R.id.historyLocationView)
        val historyScenarioId: TextView = itemView.findViewById(R.id.historyScenarioId)
    }
}
