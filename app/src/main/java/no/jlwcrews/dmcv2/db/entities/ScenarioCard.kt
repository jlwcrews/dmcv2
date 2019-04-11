package no.jlwcrews.dmc.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dmc_scenario_cards")
data class ScenarioCard(
    @PrimaryKey
    @ColumnInfo(name = "scenario_card_id")
    val scenarioCardId: Int,

    @ColumnInfo(name = "scenario_card_name")
    val scenarioCardName: String,

    @ColumnInfo(name="scenario_card_scenario_id")
    val scenarioCardScenarioId: Int,

    @ColumnInfo(name="scenario_card_reference_id")
    val scenarioCardReferenceId: Int,

    @ColumnInfo(name="scenario_card_type")
    val scenarioCardType: String

    )
