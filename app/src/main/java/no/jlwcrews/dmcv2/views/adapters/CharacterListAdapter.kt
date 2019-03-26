package no.jlwcrews.dmcv2.views.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import no.jlwcrews.dmc.db.entities.PlayerCharacter
import no.jlwcrews.dmcv2.R

class CharacterListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<CharacterListAdapter.CharacterViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var characters = emptyList<PlayerCharacter>() // Cached copy of words

    inner class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val characterItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return CharacterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val current = characters[position]
        holder.characterItemView.text = current.characterName
    }

    internal fun setCharacters(characters: List<PlayerCharacter>) {
        this.characters = characters
        notifyDataSetChanged()
    }

    override fun getItemCount() = characters.size
}