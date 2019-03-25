package no.jlwcrews.dmcv2.views.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import kotlinx.android.synthetic.main.activity_main.*
import no.jlwcrews.dmcv2.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        newGameBtn.setOnClickListener {
            val newGameIntent: Intent = Intent(this, NewGameSetupActivity::class.java)
            startActivity(newGameIntent)
        }

        /*settingsBtn.setOnClickListener {
            val settingsIntent = Intent(this, SettingsActivity::class.java)
            startActivity(settingsIntent)
        }*/


    }
}
