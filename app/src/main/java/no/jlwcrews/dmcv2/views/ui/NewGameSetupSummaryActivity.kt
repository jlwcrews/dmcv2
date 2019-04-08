package no.jlwcrews.dmcv2.views.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import no.jlwcrews.dmcv2.R
import no.jlwcrews.dmcv2.db.models.NewGameContainer
import no.jlwcrews.dmcv2.viewmodels.SummaryViewModel
import no.jlwcrews.dmcv2.views.adapters.CharacterSummaryAdapter
import no.jlwcrews.dmcv2.views.adapters.MonsterSummaryAdapter


class NewGameSetupSummaryActivity : AppCompatActivity() {

    lateinit var newGameContainer: NewGameContainer
    lateinit var summaryViewModel: SummaryViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_game_setup_summary)

        newGameContainer = intent.getSerializableExtra("newGame") as NewGameContainer

        summaryViewModel = ViewModelProviders.of(this).get(SummaryViewModel::class.java)

        val monsterRecyclerView = findViewById<RecyclerView>(R.id.monsterRecyclerView)
        val characterRecyclerView = findViewById<RecyclerView>(R.id.characterRecyclerView)

        val monsterAdapter = MonsterSummaryAdapter(this)
        val characterAdapter = CharacterSummaryAdapter(this)

        summaryViewModel.initViewModel(newGameContainer)

        characterRecyclerView.adapter = characterAdapter
        monsterRecyclerView.adapter = monsterAdapter

        characterRecyclerView.layoutManager = LinearLayoutManager(this)
        monsterRecyclerView.layoutManager = LinearLayoutManager(this)


        summaryViewModel.characters.observe(this, Observer { characters ->
            characters?.let { characterAdapter.setCharacters(it) }
        })

        summaryViewModel.monsters.observe(this, Observer { monsters ->
            // Update the cached copy of the words in the adapter.
            monsters?.let { monsterAdapter.setMonsters(it) }
        })
    }
}
