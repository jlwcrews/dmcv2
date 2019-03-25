package no.jlwcrews.dmc.db.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "dmc_characters")
data class PlayerCharacter(
    @PrimaryKey
    @ColumnInfo(name = "character_id")
    val characterId: Int,

    @ColumnInfo(name = "character_name")
    val characterName: String,

    @ColumnInfo(name = "character_health")
    val characterHealth: Int,

    @ColumnInfo(name = "character_sanity")
    val characterSanity: Int,

    @ColumnInfo(name = "character_role")
    val characterRole: String,

    @ColumnInfo(name = "character_passive_ability_name")
    val characterPassiveAbilityName: String,

    @ColumnInfo(name = "character_passive_ability")
    val characterPassiveAbility: String,

    @ColumnInfo(name = "character_active_ability_name")
    val characterActiveAbilityName: String,

    @ColumnInfo(name = "character_active_ability")
    val characterActiveAbility: String,

    @ColumnInfo(name = "character_expansion_id")
    val characterExpansionId: Int,

    @ColumnInfo(name = "chracter_starting_item")
    val characterStartingItem: String
)
