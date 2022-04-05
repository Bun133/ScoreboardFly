package com.github.bun133.scoreboardfly.values

import com.github.bun133.scoreboardfly.BaseScoreBoard

open class Value<T : Any>(v: T?) {
    var value: T? = v
        set(value) {
            field = value
            onChange()
        }

    @Transient
    internal var board: BaseScoreBoard? = null

    private fun onChange() {
        board?.update()
    }
}