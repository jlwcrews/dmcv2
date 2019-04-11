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
import kotlinx.android.synthetic.main.fragment_scenario_selection.*
import no.jlwcrews.dmcv2.R
import no.jlwcrews.dmcv2.db.models.NewGameContainer
import no.jlwcrews.dmcv2.viewmodels.DmcViewModel
import no.jlwcrews.dmcv2.views.adapters.ScenarioListAdapter


class ScenarioSelectionFragment : Fragment() {

    lateinit var dmcViewModel: DmcViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_scenario_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = getView()?.findViewById<RecyclerView>(R.id.recyclerviewScenario)
        val adapter = ScenarioListAdapter(this.context!!)
        recyclerView?.adapter = adapter
        adapter.newGameContainer = arguments?.getSerializable("newGame") as NewGameContainer
        recyclerView?.layoutManager = LinearLayoutManager(this.context!!)
        activity?.let{
            dmcViewModel = ViewModelProviders.of(it).get(DmcViewModel::class.java)
        }
        dmcViewModel.initViewModel(adapter.newGameContainer)
        dmcViewModel.scenarios.observe(this, Observer { scenarios ->
            scenarios?.let { adapter.setScenarios(it) }
        })

        scenarioNextButton.setOnClickListener {
            val newGameBundle: Bundle = Bundle()
            newGameBundle.putSerializable("newGame", adapter.newGameContainer)
            it.findNavController().navigate(R.id.characterSelectionFragment, newGameBundle)
        }
    }
}
