package no.jlwcrews.dmcv2.views.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.recyclerview_character.view.*
import no.jlwcrews.dmc.db.entities.PlayerCharacter
import no.jlwcrews.dmcv2.R

class CharacterListAdapter internal constructor(
    private val context: Context
) : RecyclerView.Adapter<CharacterListAdapter.CharacterViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var characters = emptyList<PlayerCharacter>()
    private var count: Int = 0
    private val minimumCharacters: Int = 4
    private val maximumCharacters: Int = 6
    private var characterList: MutableMap<Int, Boolean> = mutableMapOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_character, parent, false)
        return CharacterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val current = characters[position]
        holder.characterNameView.text = current.characterName
        holder.characterRoleView.text = current.characterRole

        holder.itemView.checkBoxCharacter.setOnClickListener{
            if (holder.itemView.checkBoxCharacter.isChecked){
                if (count >= maximumCharacters){
                    holder.itemView.checkBoxCharacter.isChecked = false
                    Toast.makeText(context, "Maximum of 6 characters.", Toast.LENGTH_SHORT).show()
                } else{
                    characterList.set(current.characterId, true)
                    count++
                }
            } else {
                characterList.set(current.characterId, false)
                count--
            }
            println("Count is $count")
            characterList.map { println("${it.key} ${it.value}") }
        }

    }

    internal fun setCharacters(characters: List<PlayerCharacter>) {
        this.characters = characters
        notifyDataSetChanged()
    }

    override fun getItemCount() = characters.size

    inner class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val characterNameView: TextView = itemView.findViewById(R.id.characterNameView)
        val characterRoleView: TextView = itemView.findViewById(R.id.characterRoleView)
    }
}