package com.github.bun133.scoreboardfly

import com.github.bun133.scoreboardfly.reflection.getAllFieldAndValueWithoutAnnotation
import com.github.bun133.scoreboardfly.values.Value
import org.bukkit.scoreboard.Objective
import kotlin.reflect.full.createInstance

open class BaseScoreBoard(val objective: Objective) {
    fun update() {
        val entries = this.getAllFieldAndValueWithoutAnnotation(Transient::class.createInstance())
        entries
            .filter { it.second is Value<*> }
            .map {
                val value = it.second as Value<*>
                it.first to value.value
            }
            .filter { it.second is Int }
            .forEach {
                objective.getScore(it.first).score = it.second as Int
            }
    }
}

fun <T : Any> BaseScoreBoard.value(t: T): Value<T> {
    return Value(t).also { it.board = this }
}