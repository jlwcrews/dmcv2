package no.jlwcrews.dmcv2.views.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_monster_selection.*
import no.jlwcrews.dmcv2.R
import no.jlwcrews.dmcv2.db.models.NewGameContainer
import no.jlwcrews.dmcv2.viewmodels.DmcViewModel
import no.jlwcrews.dmcv2.views.adapters.MonsterListAdapter


class MonsterSelectionFragment : Fragment() {

    private lateinit var dmcViewModel: DmcViewModel

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
        activity?.let{
            dmcViewModel = ViewModelProviders.of(it).get(DmcViewModel::class.java)
            it.title = "Monsters"
        }
        dmcViewModel.monsters.observe(this, Observer { monsters ->
            monsters?.let { adapter.setMonsters(it) }
        })

        monsterNextButton.setOnClickListener {
            if (adapter.count == adapter.maximumMonsters){
                val newGameBundle: Bundle = Bundle()
                newGameBundle.putSerializable("newGame", adapter.newGameContainer)
                it.findNavController().navigate(R.id.summaryFragment, newGameBundle)
            } else {
                Toast.makeText(this.context!!, "You must select six monsters.", Toast.LENGTH_LONG).show()
            }

        }
    }


}
