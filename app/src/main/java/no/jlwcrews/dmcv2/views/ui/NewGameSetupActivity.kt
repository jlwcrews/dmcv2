package no.jlwcrews.dmcv2.views.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StableIdKeyProvider
import androidx.recyclerview.selection.StorageStrategy
import no.jlwcrews.dmcv2.R
import no.jlwcrews.dmcv2.viewmodels.ExpansionViewModel
import no.jlwcrews.dmcv2.views.adapters.ExpansionListAdapter
import no.jlwcrews.dmcv2.views.adapters.ExpansionLookup

class NewGameSetupActivity : AppCompatActivity() {

    private lateinit var expansionViewModel: ExpansionViewModel
    private var tracker: SelectionTracker<Long>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_game_setup)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = ExpansionListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        expansionViewModel = ViewModelProviders.of(this).get(ExpansionViewModel::class.java)


        expansionViewModel.allExpansions.observe(this, Observer { expansions ->
            // Update the cached copy of the words in the adapter.
            expansions?.let { adapter.setExpansions(it) }
        })

        tracker = SelectionTracker.Builder<Long>(
            "selection",
            recyclerView,
            StableIdKeyProvider(recyclerView),
            ExpansionLookup(recyclerView),
            StorageStrategy.createLongStorage()
        ).withSelectionPredicate(
            SelectionPredicates.createSelectAnything()
        ).build()

        if(savedInstanceState != null)
            tracker?.onRestoreInstanceState(savedInstanceState)

        adapter.tracker = tracker
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        if(outState != null)
            tracker?.onSaveInstanceState(outState)
    }
}
