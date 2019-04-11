package no.jlwcrews.dmcv2.db

import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import no.jlwcrews.dmc.db.dao.*
import no.jlwcrews.dmc.db.entities.*
import no.jlwcrews.dmcv2.db.entities.Expansion
import no.jlwcrews.dmcv2.db.dao.ExpansionDao
import no.jlwcrews.dmcv2.db.dao.MonsterActionWithActionDao
import no.jlwcrews.dmcv2.db.dao.MonsterWithTypeDao

@Database(entities = arrayOf(
    Expansion::class,
    Action::class,
    History::class,
    Monster::class,
    MonsterAction::class,
    MonsterType::class,
    PlayerCharacter::class,
    Scenario::class,
    ScenarioCard::class,
    ScenarioTile::class,
    Tile::class
    ), version = 7)
public abstract class DmcDatabase : RoomDatabase() {

    abstract fun actionDao(): ActionDao
    abstract fun expansionDao(): ExpansionDao
    abstract fun historyDao(): HistoryDao
    abstract fun monsterDao(): MonsterDao
    abstract fun monsterActionDao(): MonsterActionDao
    abstract fun monsterTypeDao(): MonsterTypeDao
    abstract fun characterDao(): CharacterDao
    abstract fun scenarioDao(): ScenarioDao
    abstract fun scenarioCardDao(): ScenarioCardDao
    abstract fun scenarioTileDao(): ScenarioTileDao
    abstract fun tileDao(): TileDao
    abstract fun monsterWithTypeDao(): MonsterWithTypeDao
    abstract fun monsterActionsWithActionDao(): MonsterActionWithActionDao

    companion object {
        @Volatile
        private var INSTANCE: DmcDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): DmcDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DmcDatabase::class.java,
                    "dmc_database"
                ).fallbackToDestructiveMigration()
                    .addCallback(DmcDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
        private class DmcDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateExpansions(database.expansionDao())
                        populateActions(database.actionDao())
                        populateMonsterTypes(database.monsterTypeDao())
                        populateScenarios(database.scenarioDao())
                        populateCharacters(database.characterDao())
                        populateMonsters(database.monsterDao())
                        populateTiles(database.tileDao())
                        populateMonsterActions(database.monsterActionDao())
                        populateScenarioTiles(database.scenarioTileDao())
                    }
                }
            }
        }

        private fun populateExpansions(expansionDao: ExpansionDao) {
            expansionDao.deleteAll()
            expansionDao.insert(Expansion(1,"Core Set"))
            expansionDao.insert(Expansion(2,"Uncounted Horrors"))
            expansionDao.insert(Expansion(3,"Endless Nightmares"))
            expansionDao.insert(Expansion(4,"Rise of Dagon"))
            expansionDao.insert(Expansion(5,"The Oracle's Betrayal"))
            expansionDao.insert(Expansion(6,"The Faces of the Sphere"))
        }

        private fun populateActions(actionDao: ActionDao) {
            actionDao.deleteAll()
            actionDao.insert(Action(1,"Move"))
            actionDao.insert(Action(2,"Attack"))
            actionDao.insert(Action(3,"Special"))
        }

        private fun populateMonsterTypes(monsterTypeDao: MonsterTypeDao){
            monsterTypeDao.deleteAll()
            monsterTypeDao.insert(MonsterType(1,"Normal"))
            monsterTypeDao.insert(MonsterType(2,"Epic"))
            monsterTypeDao.insert(MonsterType(3,"Chapter"))
        }

        private fun populateScenarios(scenarioDao: ScenarioDao){
            scenarioDao.deleteAll()
            scenarioDao.insert(
                Scenario(
                    1,"Crawling Asphyxia","",1)
            )
            scenarioDao.insert(
                Scenario(
                    2,"Last Shuttle to Hell","",1)
            )
            scenarioDao.insert(
                Scenario(
                    3,"Bathophobia","",1)
            )
            scenarioDao.insert(
                Scenario(
                    4,"Through the Looking Glass","",1)
            )
            scenarioDao.insert(
                Scenario(
                    5,"Madness Within","",1)
            )
            scenarioDao.insert(
                Scenario(
                    6,"Lost in the Mist","",1)
            )
            scenarioDao.insert(
                Scenario(
                    7,"The Horror Beneath","",1)
            )
            scenarioDao.insert(
                Scenario(
                    8,"The Substance of Terror","",1)
            )
            scenarioDao.insert(
                Scenario(
                    9,"Complete Bullshit","",2)
            )

        }

        private fun populateCharacters(characterDao: CharacterDao){
            val characterRoman: PlayerCharacter = PlayerCharacter(
                1,
                "Roman Asimov",
                9,
                5,
                "Biochemist",
                "Altered States",
                "Attack: Each monster''s resistance is reduce by 1 temporarily.",
                "Splicing Reality",
                "During your activation, you may exhaust 1 sanity to mark a non-epic monster.  " +
                        "The monster's resistance is reduced by 1 for each marker it has.  Take back the marker(s) " +
                        "after the monster is removed. (You may use this ability multiple times.)",
                1,
                ""
            )
            val characterSamuel: PlayerCharacter = PlayerCharacter(
                2,
                "Samuel Smith",
                10,
                3,
                "Captain",
                "Confronting the Darkness",
                "Once per activation, you may move a non-epic monster on the board 1 space toward " +
                        "you.",
                "No One Left Behind",
                "Move: You may exhaust 1 sanity to ignore monster's trap and/or choose an investigator " +
                        "in your space to move with you.",
                1,
                "SMG"
            )
            val characterFelicia: PlayerCharacter = PlayerCharacter(
                3,
                "Felicia Armitage",
                8,
                3,
                "Doctor",
                "One Step Ahead",
                "Once per activation, you may perform a free Move.",
                "You'll Be Fine",
                "Action: Heal 1 wound from an investigator in your space. Alternatively, exhaust 1 " +
                        "sanity to roll 1 die, and heal [X]/2 (round up) wounds from that investigator instead.",
                1,
                "Scalpel"
            )
            val characterRandi: PlayerCharacter = PlayerCharacter(
                4,
                "Randi Carter",
                7,
                5,
                "Researcher",
                "Curious Mind",
                "Once per activation, you may perform a free Investigate or Search. (Still no more " +
                        "than 1 Search per round.)",
                "Esoteric Knowledge",
                "Once per activation, you may take up to 1 sanity token from every other investigator. " +
                        "(Cannot exceed your maximum sanity.)",
                1,
                "Flashlight"
            )
            val characterJared: PlayerCharacter = PlayerCharacter(
                5,
                "Jared Drake",
                9,
                4,
                "Soldier",
                "Controlled Bursts",
                "Attack: Roll 1 additional die when you attack with a ranged weapon.",
                "Suppressing Fire",
                "You may exhaust 1 sanity to Attack a monster once with a ranged weapon immediately " +
                        "after it has Attacked you. Alternatively, exhaust 2 sanity to Attack it before it Attacks you. " +
                        "(Cannot do both.)",
                1,
                "Pistol"
            )
            val characterArthur: PlayerCharacter = PlayerCharacter(
                6,
                "Arthur Weyland",
                8,
                4,
                "Engineer",
                "Something Useful",
                "Search: You may reveal 3 search cards, choose 1 to keep and discard the others.",
                "Fighting Dirty",
                "Attack: Before rolling a die, you may exhaust 1 sanity to gain: +1 ranged, +1 die, " +
                        "-1 to hit, or +1 melee damage. Cannot combine this ability with ability cards. (Limited once " +
                        "per Attack.)",
                1,
                "Pistol"
            )
            val characterBogus: PlayerCharacter = PlayerCharacter(
                9,
                "Ted Smith",
                10,
                3,
                "Chef",
                "Confronting the Darkness",
                "Once per activation, you may move a non-epic monster on the board 1 space toward " +
                        "you.",
                "No One Left Behind",
                "Move: You may exhaust 1 sanity to ignore monster's trap and/or choose an investigator " +
                        "in your space to move with you.",
                2,
                "SMG"
            )
            characterDao.deleteAll()
            characterDao.insert(characterArthur)
            characterDao.insert(characterFelicia)
            characterDao.insert(characterJared)
            characterDao.insert(characterRandi)
            characterDao.insert(characterRoman)
            characterDao.insert(characterSamuel)
            characterDao.insert(characterBogus)
        }

        private fun populateMonsters(monsterDao: MonsterDao){
            val monsterAgony: Monster = Monster(
                1,
                "Agony",
                1,
                3,
                2,
                0,
                1,
                1,
                "",
                1,
                1)
            val monsterBathophobia1: Monster = Monster(
                2,
                "Bathophobia",
                3,
                3,
                3,
                0,
                6,
                3,
                "",
                2,
                1
            )
            val monsterBathophobia2: Monster = Monster(
                3,
                "Bathophobia",
                3,
                3,
                0,
                0,
                0,
                0,
                "",
                3,
                1
            )
            val monsterBlind: Monster = Monster(
                4,
                "Blind",
                1,
                0,
                2,
                0,
                2,
                2,
                "",
                1,
                1
            )
            val monsterDelirium: Monster = Monster(
                5,
                "Delirium",
                1,
                2,
                2,
                1,
                3,
                1,
                "",
                1,
                1
            )
            val monsterHusk: Monster = Monster(
                6,
                "Husk",
                1,
                1,
                1,
                0,
                4,
                0,
                "",
                1,
                1
            )
            val monsterHysteria: Monster = Monster(
                7,
                "Hysteria",
                1,
                2,
                0,
                0,
                2,
                1,
                "",
                1,
                1
            )
            val monsterMadnessWithin1: Monster = Monster(
                8,
                "Madness Within",
                3,
                0,
                0,
                0,
                6,
                3,
                "",
                2,
                1
            )
            val monsterMadnessWithin2: Monster = Monster(
                9,
                "Madness Within",
                3,
                0,
                0,
                0,
                6,
                3,
                "",
                3,
                1
            )
            val monsterMindEater: Monster = Monster(
                10,
                "Mind Eater",
                0,
                1,
                1,
                0,
                1,
                0,
                "",
                1,
                1
            )
            val monsterPutrid: Monster = Monster(
                11,
                "Putrid",
                2,
                2,
                2,
                1,
                6,
                1,
                "",
                1,
                1
            )
            val monsterRavenous: Monster = Monster(
                12,
                "Ravenous",
                1,
                1,
                1,
                0,
                2,
                0,
                "",
                1,
                1
            )
            val monsterSubstance1: Monster = Monster(
                13,
                "Substance",
                3,
                3,
                3,
                0,
                4,
                3,
                "",
                2,
                1
            )
            val monsterSubstance2: Monster = Monster(
                14,
                "Substance",
                3,
                0,
                3,
                0,
                4,
                3,
                "",
                3,
                1
            )
            val monsterTwisted: Monster = Monster(
                15,
                "Twisted",
                2,
                2,
                2,
                0,
                5,
                2,
                "",
                1,
                1
            )
            monsterDao.deleteAll()
            monsterDao.insert(monsterAgony)
            monsterDao.insert(monsterBathophobia1)
            monsterDao.insert(monsterBathophobia2)
            monsterDao.insert(monsterBlind)
            monsterDao.insert(monsterDelirium)
            monsterDao.insert(monsterHusk)
            monsterDao.insert(monsterHysteria)
            monsterDao.insert(monsterMadnessWithin1)
            monsterDao.insert(monsterMadnessWithin2)
            monsterDao.insert(monsterMindEater)
            monsterDao.insert(monsterPutrid)
            monsterDao.insert(monsterRavenous)
            monsterDao.insert(monsterSubstance1)
            monsterDao.insert(monsterSubstance2)
            monsterDao.insert(monsterTwisted)
        }

        private fun populateTiles(tileDao: TileDao){
            tileDao.deleteAll()
            for (i in 1..30){
                tileDao.insert(Tile(i))
            }
        }

        private fun populateMonsterActions(monsterActionDao: MonsterActionDao){
            monsterActionDao.deleteAll()
            monsterActionDao.insert(MonsterAction(1,1,1))
            monsterActionDao.insert(MonsterAction(1,2,2))
            monsterActionDao.insert(MonsterAction(2,3,1))
            monsterActionDao.insert(MonsterAction(2,1,2))
            monsterActionDao.insert(MonsterAction(2,2,3))
            monsterActionDao.insert(MonsterAction(3,3,1))
            monsterActionDao.insert(MonsterAction(3,1,2))
            monsterActionDao.insert(MonsterAction(3,2,3))
            monsterActionDao.insert(MonsterAction(4,1,1))
            monsterActionDao.insert(MonsterAction(4,2,2))
            monsterActionDao.insert(MonsterAction(4,3,3))
            monsterActionDao.insert(MonsterAction(5,1,1))
            monsterActionDao.insert(MonsterAction(5,3,2))
            monsterActionDao.insert(MonsterAction(5,2,3))
            monsterActionDao.insert(MonsterAction(6,1,1))
            monsterActionDao.insert(MonsterAction(6,2,2))
            monsterActionDao.insert(MonsterAction(6,3,3))
            monsterActionDao.insert(MonsterAction(7,3,1))
            monsterActionDao.insert(MonsterAction(7,1,2))
            monsterActionDao.insert(MonsterAction(7,1,3))
            monsterActionDao.insert(MonsterAction(7,2,4))
            monsterActionDao.insert(MonsterAction(8,1,1))
            monsterActionDao.insert(MonsterAction(8,3,2))
            monsterActionDao.insert(MonsterAction(9,1,1))
            monsterActionDao.insert(MonsterAction(9,3,2))
            monsterActionDao.insert(MonsterAction(10,1,1))
            monsterActionDao.insert(MonsterAction(10,1,2))
            monsterActionDao.insert(MonsterAction(10,2,3))
            monsterActionDao.insert(MonsterAction(11,1,1))
            monsterActionDao.insert(MonsterAction(11,2,2))
            monsterActionDao.insert(MonsterAction(12,1,1))
            monsterActionDao.insert(MonsterAction(12,1,2))
            monsterActionDao.insert(MonsterAction(12,2,3))
            monsterActionDao.insert(MonsterAction(13,3,1))
            monsterActionDao.insert(MonsterAction(13,1,2))
            monsterActionDao.insert(MonsterAction(13,2,3))
            monsterActionDao.insert(MonsterAction(14,3,1))
            monsterActionDao.insert(MonsterAction(14,1,2))
            monsterActionDao.insert(MonsterAction(14,2,3))
            monsterActionDao.insert(MonsterAction(15,1,1))
            monsterActionDao.insert(MonsterAction(15,2,2))
        }

        private fun populateScenarioTiles(scenarioTileDao: ScenarioTileDao){
            scenarioTileDao.deleteAll()
            scenarioTileDao.insert(ScenarioTile(1, 4, "Room"))
            scenarioTileDao.insert(ScenarioTile(1, 10, "Room"))
            scenarioTileDao.insert(ScenarioTile(1, 17, "Room"))
            scenarioTileDao.insert(ScenarioTile(1, 21, "Room"))
            scenarioTileDao.insert(ScenarioTile(1, 22, "Room"))
            scenarioTileDao.insert(ScenarioTile(1, 28, "Room"))
            scenarioTileDao.insert(ScenarioTile(1, 1, "Devoured"))
            scenarioTileDao.insert(ScenarioTile(1, 2, "Devoured"))
            scenarioTileDao.insert(ScenarioTile(1, 8, "Devoured"))
            scenarioTileDao.insert(ScenarioTile(1, 12, "Devoured"))
            scenarioTileDao.insert(ScenarioTile(1, 15, "Devoured"))
            scenarioTileDao.insert(ScenarioTile(1, 20, "Devoured"))
            scenarioTileDao.insert(ScenarioTile(2, 3, "Room"))
            scenarioTileDao.insert(ScenarioTile(2, 5, "Room"))
            scenarioTileDao.insert(ScenarioTile(2, 10, "Room"))
            scenarioTileDao.insert(ScenarioTile(2, 12, "Room"))
            scenarioTileDao.insert(ScenarioTile(2, 16, "Room"))
            scenarioTileDao.insert(ScenarioTile(2, 23, "Room"))
            scenarioTileDao.insert(ScenarioTile(2, 29, "Room"))
            scenarioTileDao.insert(ScenarioTile(2, 2, "Devoured"))
            scenarioTileDao.insert(ScenarioTile(2, 4, "Devoured"))
            scenarioTileDao.insert(ScenarioTile(2, 6, "Devoured"))
            scenarioTileDao.insert(ScenarioTile(2, 7, "Devoured"))
            scenarioTileDao.insert(ScenarioTile(2, 8, "Devoured"))
            scenarioTileDao.insert(ScenarioTile(2, 21, "Devoured"))
            scenarioTileDao.insert(ScenarioTile(3, 18, "Room"))
            scenarioTileDao.insert(ScenarioTile(3, 19, "Room"))
            scenarioTileDao.insert(ScenarioTile(3, 20, "Room"))
            scenarioTileDao.insert(ScenarioTile(3, 22, "Room"))
            scenarioTileDao.insert(ScenarioTile(3, 23, "Room"))
            scenarioTileDao.insert(ScenarioTile(3, 25, "Room"))
            scenarioTileDao.insert(ScenarioTile(3, 26, "Room"))
            scenarioTileDao.insert(ScenarioTile(3, 28, "Room"))
            scenarioTileDao.insert(ScenarioTile(3, 1, "Devoured"))
            scenarioTileDao.insert(ScenarioTile(3, 2, "Devoured"))
            scenarioTileDao.insert(ScenarioTile(3, 9, "Devoured"))
            scenarioTileDao.insert(ScenarioTile(3, 12, "Devoured"))
            scenarioTileDao.insert(ScenarioTile(3, 13, "Devoured"))
            scenarioTileDao.insert(ScenarioTile(3, 17, "Devoured"))
            scenarioTileDao.insert(ScenarioTile(3, 29, "Devoured"))
            scenarioTileDao.insert(ScenarioTile(4, 6, "Room"))
            scenarioTileDao.insert(ScenarioTile(4, 7, "Room"))
            scenarioTileDao.insert(ScenarioTile(4, 8, "Room"))
            scenarioTileDao.insert(ScenarioTile(4, 9, "Room"))
            scenarioTileDao.insert(ScenarioTile(4, 11, "Room"))
            scenarioTileDao.insert(ScenarioTile(4, 24, "Room"))
            scenarioTileDao.insert(ScenarioTile(4, 2, "Devoured"))
            scenarioTileDao.insert(ScenarioTile(4, 12, "Devoured"))
            scenarioTileDao.insert(ScenarioTile(4, 13, "Devoured"))
            scenarioTileDao.insert(ScenarioTile(4, 17, "Devoured"))
            scenarioTileDao.insert(ScenarioTile(4, 21, "Devoured"))
            scenarioTileDao.insert(ScenarioTile(4, 23, "Devoured"))
            scenarioTileDao.insert(ScenarioTile(4, 25, "Devoured"))
            scenarioTileDao.insert(ScenarioTile(5, 1, "Room"))
            scenarioTileDao.insert(ScenarioTile(5, 3, "Room"))
            scenarioTileDao.insert(ScenarioTile(5, 17, "Room"))
            scenarioTileDao.insert(ScenarioTile(5, 18, "Room"))
            scenarioTileDao.insert(ScenarioTile(5, 24, "Room"))
            scenarioTileDao.insert(ScenarioTile(5, 25, "Room"))
            scenarioTileDao.insert(ScenarioTile(5, 27, "Room"))
            scenarioTileDao.insert(ScenarioTile(5, 2, "Devoured"))
            scenarioTileDao.insert(ScenarioTile(5, 26, "Devoured"))
            scenarioTileDao.insert(ScenarioTile(5, 28, "Devoured"))
            scenarioTileDao.insert(ScenarioTile(5, 29, "Devoured"))
            scenarioTileDao.insert(ScenarioTile(6, 1, "Room"))
            scenarioTileDao.insert(ScenarioTile(6, 2, "Room"))
            scenarioTileDao.insert(ScenarioTile(6, 3, "Room"))
            scenarioTileDao.insert(ScenarioTile(6, 4, "Room"))
            scenarioTileDao.insert(ScenarioTile(6, 5, "Room"))
            scenarioTileDao.insert(ScenarioTile(6, 10, "Room"))
            scenarioTileDao.insert(ScenarioTile(6, 11, "Room"))
            scenarioTileDao.insert(ScenarioTile(6, 12, "Room"))
            scenarioTileDao.insert(ScenarioTile(6, 14, "Room"))
            scenarioTileDao.insert(ScenarioTile(6, 15, "Room"))
            scenarioTileDao.insert(ScenarioTile(6, 16, "Room"))
            scenarioTileDao.insert(ScenarioTile(6, 17, "Room"))
            scenarioTileDao.insert(ScenarioTile(6, 18, "Room"))
            scenarioTileDao.insert(ScenarioTile(6, 19, "Room"))
            scenarioTileDao.insert(ScenarioTile(6, 20, "Room"))
            scenarioTileDao.insert(ScenarioTile(6, 21, "Room"))
            scenarioTileDao.insert(ScenarioTile(6, 22, "Room"))
            scenarioTileDao.insert(ScenarioTile(6, 23, "Room"))
            scenarioTileDao.insert(ScenarioTile(6, 24, "Room"))
            scenarioTileDao.insert(ScenarioTile(6, 25, "Room"))
            scenarioTileDao.insert(ScenarioTile(6, 26, "Room"))
            scenarioTileDao.insert(ScenarioTile(6, 27, "Room"))
            scenarioTileDao.insert(ScenarioTile(6, 28, "Room"))
            scenarioTileDao.insert(ScenarioTile(6, 29, "Room"))
            scenarioTileDao.insert(ScenarioTile(6, 6, "Devoured"))
            scenarioTileDao.insert(ScenarioTile(6, 7, "Devoured"))
            scenarioTileDao.insert(ScenarioTile(6, 8, "Devoured"))
            scenarioTileDao.insert(ScenarioTile(6, 9, "Devoured"))
            scenarioTileDao.insert(ScenarioTile(6, 13, "Devoured"))
            scenarioTileDao.insert(ScenarioTile(6, 30, "Devoured"))
            scenarioTileDao.insert(ScenarioTile(7, 6, "Room"))
            scenarioTileDao.insert(ScenarioTile(7, 8, "Room"))
            scenarioTileDao.insert(ScenarioTile(7, 12, "Room"))
            scenarioTileDao.insert(ScenarioTile(7, 14, "Room"))
            scenarioTileDao.insert(ScenarioTile(7, 15, "Room"))
            scenarioTileDao.insert(ScenarioTile(7, 24, "Room"))
            scenarioTileDao.insert(ScenarioTile(7, 1, "Devoured"))
            scenarioTileDao.insert(ScenarioTile(7, 2, "Devoured"))
            scenarioTileDao.insert(ScenarioTile(7, 4, "Devoured"))
            scenarioTileDao.insert(ScenarioTile(7, 7, "Devoured"))
            scenarioTileDao.insert(ScenarioTile(7, 16, "Devoured"))
            scenarioTileDao.insert(ScenarioTile(7, 18, "Devoured"))
            scenarioTileDao.insert(ScenarioTile(8, 1, "Room"))
            scenarioTileDao.insert(ScenarioTile(8, 19, "Room"))
            scenarioTileDao.insert(ScenarioTile(8, 20, "Room"))
            scenarioTileDao.insert(ScenarioTile(8, 23, "Room"))
            scenarioTileDao.insert(ScenarioTile(8, 26, "Room"))
            scenarioTileDao.insert(ScenarioTile(8, 28, "Room"))
            scenarioTileDao.insert(ScenarioTile(8, 2, "Devoured"))
            scenarioTileDao.insert(ScenarioTile(8, 9, "Devoured"))
            scenarioTileDao.insert(ScenarioTile(8, 10, "Devoured"))
            scenarioTileDao.insert(ScenarioTile(8, 12, "Devoured"))
            scenarioTileDao.insert(ScenarioTile(8, 17, "Devoured"))
            scenarioTileDao.insert(ScenarioTile(8, 24, "Devoured"))
        }
    }
}