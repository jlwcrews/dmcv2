package no.jlwcrews.dmcv2.views.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import no.jlwcrews.dmcv2.R
import no.jlwcrews.dmcv2.viewmodels.CharacterViewModel
import no.jlwcrews.dmcv2.viewmodels.ScenarioViewModel
import no.jlwcrews.dmcv2.views.adapters.CharacterListAdapter
import no.jlwcrews.dmcv2.views.adapters.ScenarioListAdapter

class NewGameSetupCharacterActivity : AppCompatActivity() {

    private lateinit var selectedExpansions: Map<Int, Boolean>
    private lateinit var characterViewModel: CharacterViewModel
    lateinit var expansionList: List<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_game_setup_character)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerviewCharacter)
        val adapter = CharacterListAdapter(this)
        expansionList = handleExpansionList()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        characterViewModel = ViewModelProviders.of(this).get(CharacterViewModel::class.java)
        characterViewModel.initCharacters(expansionList)
        characterViewModel.characters.observe(this, Observer { characters ->
            characters?.let { adapter.setCharacters(it) }
        })
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
