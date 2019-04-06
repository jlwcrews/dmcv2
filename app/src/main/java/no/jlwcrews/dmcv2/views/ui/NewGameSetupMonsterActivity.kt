package no.jlwcrews.dmcv2.views.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import no.jlwcrews.dmcv2.R
import no.jlwcrews.dmcv2.db.NewGameContainer
import no.jlwcrews.dmcv2.viewmodels.MonsterViewModel
import no.jlwcrews.dmcv2.views.adapters.MonsterListAdapter

class NewGameSetupMonsterActivity : AppCompatActivity() {

    private lateinit var monsterViewModel: MonsterViewModel
    lateinit var expansionList: List<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_game_setup_monster)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerviewMonster)
        val adapter = MonsterListAdapter(this)
        recyclerView.adapter = adapter
        adapter.newGameContainer = intent.getSerializableExtra("newGame") as NewGameContainer
        recyclerView.layoutManager = LinearLayoutManager(this)
        monsterViewModel = ViewModelProviders.of(this).get(MonsterViewModel::class.java)
        monsterViewModel.initMonsters(adapter.newGameContainer.getAsList(adapter.newGameContainer.expansions))
        monsterViewModel.monsters.observe(this, Observer { monsters ->
            monsters?.let { adapter.setMonsters(it) }
        })
    }
}
