package no.jlwcrews.dmcv2.views.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import no.jlwcrews.dmcv2.R
import no.jlwcrews.dmcv2.viewmodels.ScenarioViewModel
import no.jlwcrews.dmcv2.views.adapters.ScenarioListAdapter

class NewGameSetupScenarioActivity : AppCompatActivity() {

    var selectedExpansions: HashMap<Int, Boolean> = hashMapOf()
    private lateinit var scenarioViewModel: ScenarioViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_game_setup_scenario)
        val selectedExpansions: Map<Int, Boolean> = intent.getSerializableExtra("expansionList") as Map<Int, Boolean>
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerviewScenario)
        val adapter = ScenarioListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        scenarioViewModel = ViewModelProviders.of(this).get(ScenarioViewModel::class.java)

        scenarioViewModel.allScenarios.observe(this, Observer { scenarios ->
            // Update the cached copy of the words in the adapter.
            scenarios?.let { adapter.setScenarios(it) }
        })
    }
}
