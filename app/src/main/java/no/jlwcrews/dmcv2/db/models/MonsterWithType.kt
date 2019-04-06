package no.jlwcrews.dmcv2.db.models

import android.arch.persistence.room.Embedded
import no.jlwcrews.dmc.db.entities.Monster
import no.jlwcrews.dmc.db.entities.MonsterType
import java.io.Serializable

class MonsterWithType : Serializable {
    @Embedded
    lateinit var monster: Monster
    @Embedded
    lateinit var monsterType: MonsterType
}