package com.github.bun133.scoreboardsample

import com.github.bun133.scoreboardfly.BaseScoreBoard
import com.github.bun133.scoreboardfly.value
import org.bukkit.scoreboard.Objective

class ScoreBoard(objective: Objective) : BaseScoreBoard(objective) {
    val int1 = value(111)
}