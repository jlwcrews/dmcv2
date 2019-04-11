package no.jlwcrews.dmcv2.views.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import kotlinx.android.synthetic.main.activity_main.*
import no.jlwcrews.dmcv2.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

/*        newGameBtn.setOnClickListener {
            val newGameIntent: Intent = Intent(this, NewGameSetupExpansionActivity::class.java)
            startActivity(newGameIntent)
        }*/

        newGameBtn.setOnClickListener {
            val newGameIntent: Intent = Intent(this, MainActivity2::class.java)
            startActivity(newGameIntent)
        }
    }
}
