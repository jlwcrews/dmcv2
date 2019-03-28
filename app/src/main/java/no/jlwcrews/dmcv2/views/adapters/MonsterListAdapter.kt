package no.jlwcrews.dmcv2.views.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import no.jlwcrews.dmc.db.entities.Monster
import no.jlwcrews.dmcv2.R

class MonsterListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<MonsterListAdapter.MonsterViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var monsters = emptyList<Monster>() // Cached copy of words

    inner class MonsterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val monsterIdView: TextView = itemView.findViewById(R.id.monsterIdView)
        val monsterNameView: TextView = itemView.findViewById(R.id.monsterNameView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonsterViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_expansion, parent, false)
        return MonsterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MonsterViewHolder, position: Int) {
        val current = monsters[position]
        holder.monsterIdView.text = current.monsterId.toString()
        holder.monsterNameView.text = current.monsterName
    }

    internal fun setMonsters(monsters: List<Monster>) {
        this.monsters = monsters
        notifyDataSetChanged()
    }

    override fun getItemCount() = monsters.size
}

