package no.jlwcrews.dmcv2.views.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import no.jlwcrews.dmcv2.R
import no.jlwcrews.dmcv2.db.NewGameContainer

class NewGameSetupSummaryActivity : AppCompatActivity() {

    lateinit var newGameContainer: NewGameContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_game_setup_summary)

        newGameContainer = intent.getSerializableExtra("newGame") as NewGameContainer


    }
}
