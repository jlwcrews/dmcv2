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
import kotlinx.android.synthetic.main.fragment_character_selection.*
import no.jlwcrews.dmcv2.R
import no.jlwcrews.dmcv2.db.models.NewGameContainer
import no.jlwcrews.dmcv2.viewmodels.CharacterViewModel
import no.jlwcrews.dmcv2.views.adapters.CharacterListAdapter


class CharacterSelectionFragment : Fragment() {

    lateinit var characterViewModel: CharacterViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_character_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = getView()?.findViewById<RecyclerView>(R.id.recyclerviewCharacter)
        val adapter = CharacterListAdapter(this.context!!)
        adapter.newGameContainer = arguments?.getSerializable("newGame") as NewGameContainer
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(this.context!!)
        characterViewModel = ViewModelProviders.of(this).get(CharacterViewModel::class.java)
        characterViewModel.initCharacters(adapter.newGameContainer.getAsList(adapter.newGameContainer.expansions))
        characterViewModel.characters.observe(this, Observer { characters ->
            characters?.let { adapter.setCharacters(it) }
        })

        characterNextButton.setOnClickListener {
            val newGameBundle: Bundle = Bundle()
            newGameBundle.putSerializable("newGame", adapter.newGameContainer)
            it.findNavController().navigate(R.id.monsterSelectionFragment, newGameBundle)
        }
    }


}
