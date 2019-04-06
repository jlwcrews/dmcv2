package no.jlwcrews.dmcv2.views.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.recyclerview_monster.view.*
import no.jlwcrews.dmc.db.entities.Monster
import no.jlwcrews.dmcv2.R
import no.jlwcrews.dmcv2.db.NewGameContainer

class MonsterListAdapter internal constructor(
    private val context: Context
) : RecyclerView.Adapter<MonsterListAdapter.MonsterViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var monsters = emptyList<Monster>()
    private var count: Int = 0
    private val minimumMonsters: Int = 4
    private val maximumMonsters: Int = 6
    lateinit var newGameContainer: NewGameContainer

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonsterViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_monster, parent, false)
        return MonsterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MonsterViewHolder, position: Int) {
        val current = monsters[position]
        holder.monsterNameView.text = current.monsterName

        holder.itemView.checkBoxMonster.setOnClickListener{
            if (holder.itemView.checkBoxMonster.isChecked){
                if (count >= maximumMonsters){
                    holder.itemView.checkBoxMonster.isChecked = false
                    Toast.makeText(context, "Maximum of 6 monsters.", Toast.LENGTH_LONG).show()
                } else{
                    newGameContainer.monsters.set(current.monsterId, true)
                    count++
                }
            } else {
                newGameContainer.monsters.set(current.monsterId, false)
                count--
            }
            println("Count is $count")
            newGameContainer.monsters.map { println("${it.key} ${it.value}") }
        }

    }

    internal fun setMonsters(monsters: List<Monster>) {
        this.monsters = monsters
        notifyDataSetChanged()
    }

    override fun getItemCount() = monsters.size

    inner class MonsterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val monsterNameView: TextView = itemView.findViewById(R.id.monsterNameView)
    }
}

