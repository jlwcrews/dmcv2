package no.jlwcrews.dmcv2.db.models

import androidx.room.Embedded
import no.jlwcrews.dmc.db.entities.Action
import no.jlwcrews.dmc.db.entities.MonsterAction
import java.io.Serializable

class MonsterActionWithAction: Serializable{
    @Embedded
    lateinit var monsterAction: MonsterAction
    @Embedded
    lateinit var action: Action
}