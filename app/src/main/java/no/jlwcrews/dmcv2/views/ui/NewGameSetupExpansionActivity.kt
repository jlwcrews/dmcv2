package no.jlwcrews.dmcv2.views.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_new_game_setup_expansion.*
import no.jlwcrews.dmcv2.R
import no.jlwcrews.dmcv2.viewmodels.ExpansionViewModel
import no.jlwcrews.dmcv2.views.adapters.ExpansionListAdapter

class NewGameSetupExpansionActivity : AppCompatActivity() {

    private lateinit var expansionViewModel: ExpansionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_game_setup_expansion)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerviewExpansion)
        val adapter = ExpansionListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        expansionViewModel = ViewModelProviders.of(this).get(ExpansionViewModel::class.java)

        expansionViewModel.allExpansions.observe(this, Observer { expansions ->
            // Update the cached copy of the words in the adapter.
            expansions?.let { adapter.setExpansions(it) }
        })

        expansionNextButton.setOnClickListener {
            val scenarioSelectIntent: Intent = Intent(this, NewGameSetupScenarioActivity::class.java)
            scenarioSelectIntent.putExtra("newGame", adapter.newGameContainer)
            startActivity(scenarioSelectIntent)
        }

    }
}
