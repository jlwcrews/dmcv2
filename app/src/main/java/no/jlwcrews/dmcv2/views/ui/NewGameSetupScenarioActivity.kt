package no.jlwcrews.dmcv2.views.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import no.jlwcrews.dmcv2.R

class NewGameSetupScenarioActivity : AppCompatActivity() {

    var selectedExpansions: HashMap<Int, Boolean> = hashMapOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_game_setup_scenario)
        //val selectedExpansions: List<Int> = intent.getSerializableExtra("expansionList") as MutableList<Int>
        val selectedExpansions: Map<Int, Boolean> = intent.getSerializableExtra("expansionList") as Map<Int, Boolean>
        selectedExpansions.map {
            println("id = ${it.key}, value = ${it.value}")
        }
    }
}
