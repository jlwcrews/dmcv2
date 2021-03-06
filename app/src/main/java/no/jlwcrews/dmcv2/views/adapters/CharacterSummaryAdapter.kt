package no.jlwcrews.dmcv2.views.adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.recyclerview_character.view.*
import no.jlwcrews.dmc.db.entities.PlayerCharacter
import no.jlwcrews.dmcv2.R
import no.jlwcrews.dmcv2.db.models.NewGameContainer

class CharacterSummaryAdapter internal constructor(
    private val context: Context
) : RecyclerView.Adapter<CharacterSummaryAdapter.CharacterViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var characters = emptyList<PlayerCharacter>()
    var count: Int = 0
    val minimumCharacters: Int = 4
    private val maximumCharacters: Int = 6
    lateinit var newGameContainer: NewGameContainer

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_character_summary, parent, false)
        return CharacterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val current = characters[position]
        holder.characterNameView.text = current.characterName
    }

    internal fun setCharacters(characters: List<PlayerCharacter>) {
        this.characters = characters
        notifyDataSetChanged()
    }

    override fun getItemCount() = characters.size

    inner class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val characterNameView: TextView = itemView.findViewById(R.id.characterNameView)
    }
}