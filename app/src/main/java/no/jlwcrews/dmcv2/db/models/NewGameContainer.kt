package no.jlwcrews.dmcv2.db.models

import java.io.Serializable

class NewGameContainer: Serializable {

    val expansions: MutableMap<Int, Boolean> = mutableMapOf()
    var scenario: Int = 0
    val characters: MutableMap<Int, Boolean> = mutableMapOf()
    val monsters: MutableMap<Int, Boolean> = mutableMapOf()

    fun getAsList(mutableMap: MutableMap<Int, Boolean>):List<Int>{
        var list: MutableList<Int> = mutableListOf()
        mutableMap.map {
            if (it.value){
                list.add(it.key)
            }
        }
        return list
    }
}