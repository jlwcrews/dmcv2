package no.jlwcrews.dmcv2.views.ui

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_new_game_setup_character.*
import no.jlwcrews.dmcv2.R
import no.jlwcrews.dmcv2.db.models.NewGameContainer
import no.jlwcrews.dmcv2.viewmodels.CharacterViewModel
import no.jlwcrews.dmcv2.views.adapters.CharacterListAdapter

class NewGameSetupCharacterActivity : AppCompatActivity() {

    private lateinit var characterViewModel: CharacterViewModel
    lateinit var expansionList: List<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_game_setup_character)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerviewCharacter)
        val adapter = CharacterListAdapter(this)
        adapter.newGameContainer = intent.getSerializableExtra("newGame") as NewGameContainer
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        characterViewModel = ViewModelProviders.of(this).get(CharacterViewModel::class.java)
        characterViewModel.initCharacters(adapter.newGameContainer.getAsList(adapter.newGameContainer.expansions))
        characterViewModel.characters.observe(this, Observer { characters ->
            characters?.let { adapter.setCharacters(it) }
        })

        characterNextButton.setOnClickListener {
            if(adapter.count >= adapter.minimumCharacters){
                val monsterSelectIntent: Intent = Intent(this, NewGameSetupMonsterActivity::class.java)
                monsterSelectIntent.putExtra("newGame", adapter.newGameContainer)
                startActivity(monsterSelectIntent)
            } else {
                Toast.makeText(this, "You must select at least four characters.", Toast.LENGTH_LONG).show()
            }
        }
    }
}
