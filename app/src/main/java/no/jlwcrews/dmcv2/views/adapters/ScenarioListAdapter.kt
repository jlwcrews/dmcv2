package no.jlwcrews.dmcv2.views.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.recyclerview_scenario.view.*
import no.jlwcrews.dmc.db.entities.Scenario
import no.jlwcrews.dmcv2.R
import no.jlwcrews.dmcv2.db.models.NewGameContainer

class ScenarioListAdapter internal constructor(context: Context) : RecyclerView.Adapter<ScenarioListAdapter.ScenarioViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var scenarios = emptyList<Scenario>()
    lateinit var newGameContainer: NewGameContainer
    var currentRadioButtonPosition: Int = -1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScenarioViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_scenario, parent, false)
        return ScenarioViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ScenarioViewHolder, position: Int) {
        val current = scenarios[position]
        holder.bindItems(current, position, currentRadioButtonPosition)

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
        val scenarioNameView: TextView = itemView.findViewById(R.id.scenarioNameView)
        val scenarioExpansionIdView: TextView = itemView.findViewById(R.id.scenarioExpansionIdView)

        fun bindItems(scenario: Scenario, position: Int, selectedPosition: Int){
            itemView.scenarioRadioButton.tag = scenario.scenarioId

            when {
                //selectedPosition == -1 && position == 0 -> itemView.scenarioRadioButton.isChecked = true
                selectedPosition == position -> itemView.scenarioRadioButton.isChecked = true
                else -> itemView.scenarioRadioButton.isChecked = false
            }

            itemView.scenarioRadioButton.setOnClickListener {
                currentRadioButtonPosition = adapterPosition
                newGameContainer.scenario = scenario.scenarioId
                notifyDataSetChanged()
            }
        }
    }
}
