package no.jlwcrews.dmcv2.views.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import no.jlwcrews.dmc.db.entities.Scenario
import no.jlwcrews.dmcv2.R

class ScenarioListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<ScenarioListAdapter.ScenarioViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var scenarios = emptyList<Scenario>() // Cached copy of words

    inner class ScenarioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val scenarioItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScenarioViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return ScenarioViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ScenarioViewHolder, position: Int) {
        val current = scenarios[position]
        holder.scenarioItemView.text = current.scenarioName
    }

    internal fun setScenarios(scenarios: List<Scenario>) {
        this.scenarios = scenarios
        notifyDataSetChanged()
    }

    override fun getItemCount() = scenarios.size
}