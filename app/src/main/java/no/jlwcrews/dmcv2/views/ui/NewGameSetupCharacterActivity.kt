package no.jlwcrews.dmcv2.views.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_new_game_setup_character.*
import no.jlwcrews.dmcv2.R
import no.jlwcrews.dmcv2.viewmodels.CharacterViewModel
import no.jlwcrews.dmcv2.views.adapters.CharacterListAdapter
import java.io.Serializable

class NewGameSetupCharacterActivity : AppCompatActivity() {

    private lateinit var characterViewModel: CharacterViewModel
    lateinit var expansionList: List<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_game_setup_character)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerviewCharacter)
        val adapter = CharacterListAdapter(this)
        expansionList = intent.getSerializableExtra("expansionList") as List<Int>
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        characterViewModel = ViewModelProviders.of(this).get(CharacterViewModel::class.java)
        characterViewModel.initCharacters(expansionList)
        characterViewModel.characters.observe(this, Observer { characters ->
            characters?.let { adapter.setCharacters(it) }
        })

        characterNextButton.setOnClickListener {
            if(adapter.count >= adapter.minimumCharacters){
                val monsterSelectIntent: Intent = Intent(this, NewGameSetupMonsterActivity::class.java)
                monsterSelectIntent.putExtra("expansionList", expansionList as Serializable)
                startActivity(monsterSelectIntent)
            } else {
                Toast.makeText(this, "You must select at least four characters.", Toast.LENGTH_LONG).show()
            }
        }
    }
}
