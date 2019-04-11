package no.jlwcrews.dmcv2.views.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import no.jlwcrews.dmcv2.R
import no.jlwcrews.dmcv2.viewmodels.DmcViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dmcViewModel = ViewModelProviders.of(this).get(DmcViewModel::class.java)
    }
}
