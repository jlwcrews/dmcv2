package no.jlwcrews.dmcv2.views.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(no.jlwcrews.dmcv2.R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let{
            it.title = "Deep Madness Companion"
        }

        newGameButton.setOnClickListener(Navigation.createNavigateOnClickListener(no.jlwcrews.dmcv2.R.id.to_expansion_selection))
        historyButton.setOnClickListener(Navigation.createNavigateOnClickListener(no.jlwcrews.dmcv2.R.id.to_history))
        diceRollerButton.setOnClickListener(Navigation.createNavigateOnClickListener(no.jlwcrews.dmcv2.R.id.to_dice_roller))
    }
}
