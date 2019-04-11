package no.jlwcrews.dmcv2.views.ui.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_summary.*
import no.jlwcrews.dmcv2.R
import no.jlwcrews.dmcv2.db.models.NewGameContainer
import no.jlwcrews.dmcv2.viewmodels.SummaryViewModel
import no.jlwcrews.dmcv2.views.adapters.CharacterSummaryAdapter
import no.jlwcrews.dmcv2.views.adapters.MonsterSummaryAdapter

class SummaryFragment : Fragment() {

    lateinit var summaryViewModel: SummaryViewModel
    lateinit var newGameContainer: NewGameContainer

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_summary, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newGameContainer = arguments?.getSerializable("newGame") as NewGameContainer

        summaryViewModel = ViewModelProviders.of(this).get(SummaryViewModel::class.java)

        val monsterRecyclerView = getView()?.findViewById<RecyclerView>(R.id.monsterRecyclerView)
        val characterRecyclerView = getView()?.findViewById<RecyclerView>(R.id.characterRecyclerView)

        val monsterAdapter = MonsterSummaryAdapter(this.context!!)
        val characterAdapter = CharacterSummaryAdapter(this.context!!)

        characterRecyclerView?.adapter = characterAdapter
        monsterRecyclerView?.adapter = monsterAdapter

        characterRecyclerView?.layoutManager = LinearLayoutManager(this.context!!)
        monsterRecyclerView?.layoutManager = LinearLayoutManager(this.context!!)

        summaryViewModel.initViewModel(newGameContainer)

        summaryViewModel.scenario.observe(this, Observer { scenario ->
            scenario?.let {scenarioTextView.text = (it.scenarioName)}
        })

        summaryViewModel.characters.observe(this, Observer { characters ->
            characters?.let { characterAdapter.setCharacters(it) }
        })

        summaryViewModel.monsters.observe(this, Observer { monsters ->
            monsters?.let { monsterAdapter.setMonsters(it) }
        })
    }

}
