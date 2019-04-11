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
import kotlinx.android.synthetic.main.fragment_expansion_selection.*
import no.jlwcrews.dmcv2.R
import no.jlwcrews.dmcv2.viewmodels.DmcViewModel
import no.jlwcrews.dmcv2.views.adapters.ExpansionListAdapter


class ExpansionSelectionFragment : Fragment() {

    lateinit var dmcViewModel: DmcViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_expansion_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = getView()?.findViewById<RecyclerView>(R.id.recyclerviewExpansion)
        val adapter = ExpansionListAdapter(this.context!!)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(this.context!!)
        activity?.let{
            dmcViewModel = ViewModelProviders.of(this).get(DmcViewModel::class.java)
        }
        dmcViewModel.expansions.observe(this, Observer { expansions ->
            expansions?.let { adapter.setExpansions(it) }
        })

        expansionNextButton.setOnClickListener {
            val newGameBundle = Bundle()
            newGameBundle.putSerializable("newGame", adapter.newGameContainer)
            it.findNavController().navigate(R.id.scenarioSelectionFragment, newGameBundle)
        }
    }

}
