package no.jlwcrews.dmcv2.views.ui.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_main.*
import no.jlwcrews.dmcv2.R


class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_main, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newGameButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.to_expansion_selection))

        historyButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.to_history))
    }


}
