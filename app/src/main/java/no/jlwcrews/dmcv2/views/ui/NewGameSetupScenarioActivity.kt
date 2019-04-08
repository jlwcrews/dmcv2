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
import no.jlwcrews.dmcv2.db.models.NewGameContainer
import no.jlwcrews.dmcv2.viewmodels.ScenarioViewModel
import no.jlwcrews.dmcv2.views.adapters.ScenarioListAdapter

class NewGameSetupScenarioActivity : AppCompatActivity() {

    private lateinit var scenarioViewModel: ScenarioViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_game_setup_scenario)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerviewScenario)
        val adapter = ScenarioListAdapter(this)
        recyclerView.adapter = adapter
        adapter.newGameContainer = intent.getSerializableExtra("newGame") as NewGameContainer
        println(adapter.newGameContainer.getAsList(adapter.newGameContainer.expansions))
        recyclerView.layoutManager = LinearLayoutManager(this)
        scenarioViewModel = ViewModelProviders.of(this).get(ScenarioViewModel::class.java)
        scenarioViewModel.initScenarios(adapter.newGameContainer.getAsList(adapter.newGameContainer.expansions))
        scenarioViewModel.scenarios.observe(this, Observer { scenarios ->
            scenarios?.let { adapter.setScenarios(it) }
        })

        scenarioNextButton.setOnClickListener {
            val characterSelectIntent = Intent(this, NewGameSetupCharacterActivity::class.java)
            characterSelectIntent.putExtra("newGame", adapter.newGameContainer)
            startActivity(characterSelectIntent)
        }
    }
}
