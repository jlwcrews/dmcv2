package no.jlwcrews.dmcv2.views.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import no.jlwcrews.dmcv2.R

class NewGameSetupScenarioActivity : AppCompatActivity() {

    var selectedExpansions: HashMap<Int, Boolean> = hashMapOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_game_setup_scenario)
    }
}