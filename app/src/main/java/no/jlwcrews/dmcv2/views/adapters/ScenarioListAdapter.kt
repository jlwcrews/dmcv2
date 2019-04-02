package no.jlwcrews.dmcv2.views.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import no.jlwcrews.dmc.db.entities.Scenario
import no.jlwcrews.dmcv2.R

class ScenarioListAdapter internal constructor(context: Context) : RecyclerView.Adapter<ScenarioListAdapter.ScenarioViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var scenarios = emptyList<Scenario>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScenarioViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_scenario, parent, false)
        return ScenarioViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ScenarioViewHolder, position: Int) {
        val current = scenarios[position]

        /*holder.itemView.checkBoxScenario.setOnClickListener{
            selectedScenarios.set(current.scenarioId, holder.itemView.checkBoxScenario.isChecked)
        }*/
        holder.scenarioIdView.text = current.scenarioId.toString()
        holder.scenarioNameView.text = current.scenarioName
        holder.scenarioExpansionIdView.text = current.scenarioExpansionId.toString()
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    internal fun setScenarios(scenarios: List<Scenario>) {
        this.scenarios = scenarios
        notifyDataSetChanged()
    }

    override fun getItemCount() = scenarios.size

    inner class ScenarioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val scenarioIdView: TextView = itemView.findViewById(R.id.scenarioIdView)
        val scenarioNameView: TextView = itemView.findViewById(R.id.scenarioNameView)
        val scenarioExpansionIdView: TextView = itemView.findViewById(R.id.scenarioExpansionIdView)
    }
}
