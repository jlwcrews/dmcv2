package no.jlwcrews.dmcv2.views.adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.recyclerview_monster.view.*
import no.jlwcrews.dmcv2.R
import no.jlwcrews.dmcv2.db.models.NewGameContainer
import no.jlwcrews.dmcv2.db.models.MonsterWithType

class MonsterSummaryAdapter internal constructor(
    private val context: Context
) : RecyclerView.Adapter<MonsterSummaryAdapter.MonsterViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var monsters = emptyList<MonsterWithType>()
    var count: Int = 0
    val maximumMonsters: Int = 6
    lateinit var newGameContainer: NewGameContainer

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonsterViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_monster_summary, parent, false)
        return MonsterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MonsterViewHolder, position: Int) {
        val current = monsters[position]
        holder.monsterNameView.text = current.monster.monsterName
    }

    internal fun setMonsters(monsters: List<MonsterWithType>) {
        this.monsters = monsters
        notifyDataSetChanged()
    }

    override fun getItemCount() = monsters.size

    inner class MonsterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val monsterNameView: TextView = itemView.findViewById(R.id.monsterNameView)
    }
}

