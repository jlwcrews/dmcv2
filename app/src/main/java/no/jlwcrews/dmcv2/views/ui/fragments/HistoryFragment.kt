package no.jlwcrews.dmcv2.views.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import no.jlwcrews.dmcv2.R
import no.jlwcrews.dmcv2.viewmodels.DmcViewModel
import no.jlwcrews.dmcv2.views.adapters.HistoryListAdapter

class HistoryFragment : Fragment() {

    lateinit var dmcViewModel: DmcViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val recyclerView = getView()?.findViewById<RecyclerView>(R.id.recyclerviewHistory)
        val adapter = HistoryListAdapter(this.context!!)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(this.context!!)
        activity?.let {
            dmcViewModel = ViewModelProviders.of(it).get(DmcViewModel::class.java)
        }
        dmcViewModel.history.observe(this, Observer { history ->
            history?.let { adapter.setHistory(it) }
        })
    }
}
