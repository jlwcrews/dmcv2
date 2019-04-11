package no.jlwcrews.dmcv2.views.ui.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_monster_selection.*
import no.jlwcrews.dmcv2.R
import no.jlwcrews.dmcv2.db.models.NewGameContainer
import no.jlwcrews.dmcv2.viewmodels.MonsterViewModel
import no.jlwcrews.dmcv2.views.adapters.MonsterListAdapter


class MonsterSelectionFragment : Fragment() {

    private lateinit var monsterViewModel: MonsterViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_monster_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = getView()?.findViewById<RecyclerView>(R.id.recyclerviewMonster)
        val adapter = MonsterListAdapter(this.context!!)
        adapter.newGameContainer = arguments?.getSerializable("newGame") as NewGameContainer
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(this.context!!)
        monsterViewModel = ViewModelProviders.of(this).get(MonsterViewModel::class.java)
        monsterViewModel.initMonsters(adapter.newGameContainer.getAsList(adapter.newGameContainer.expansions))
        monsterViewModel.monsters.observe(this, Observer { monsters ->
            monsters?.let { adapter.setMonsters(it) }
        })

        monsterNextButton.setOnClickListener {
            val newGameBundle: Bundle = Bundle()
            newGameBundle.putSerializable("newGame", adapter.newGameContainer)
            it.findNavController().navigate(R.id.summaryFragment, newGameBundle)
        }
    }


}
