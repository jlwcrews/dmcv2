package no.jlwcrews.dmcv2.views.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_new_game_setup_scenario.*
import no.jlwcrews.dmcv2.R
import no.jlwcrews.dmcv2.viewmodels.ScenarioViewModel
import no.jlwcrews.dmcv2.views.adapters.ScenarioListAdapter
import java.io.Serializable

class NewGameSetupScenarioActivity : AppCompatActivity() {

    private lateinit var selectedExpansions: Map<Int, Boolean>
    private lateinit var scenarioViewModel: ScenarioViewModel
    lateinit var expansionList: List<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_game_setup_scenario)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerviewScenario)
        val adapter = ScenarioListAdapter(this)
        expansionList = handleExpansionList()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        scenarioViewModel = ViewModelProviders.of(this).get(ScenarioViewModel::class.java)
        scenarioViewModel.initScenarios(expansionList)
        scenarioViewModel.scenarios.observe(this, Observer { scenarios ->
            scenarios?.let { adapter.setScenarios(it) }
        })

        scenarioNextButton.setOnClickListener {
            val characterSelectIntent: Intent = Intent(this, NewGameSetupCharacterActivity::class.java)
            characterSelectIntent.putExtra("expansionList", expansionList as Serializable)
            startActivity(characterSelectIntent)
        }
    }

    private fun handleExpansionList(): List<Int>{
        selectedExpansions = intent.getSerializableExtra("expansionList") as Map<Int, Boolean>
        val expansionList: MutableList<Int> = mutableListOf()
        selectedExpansions.map {
            if (it.value){
                expansionList.add(it.key)
            }
        }
        return expansionList
    }
}
