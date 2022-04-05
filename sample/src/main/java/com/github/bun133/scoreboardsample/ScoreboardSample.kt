package com.github.bun133.scoreboardsample

import dev.kotx.flylib.command.Command
import dev.kotx.flylib.flyLib
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scoreboard.DisplaySlot
import org.bukkit.scoreboard.Objective
import kotlin.random.Random

class ScoreboardSample : JavaPlugin() {
    val command = SampleCommand(null)

    init {
        flyLib {
            command(command)
        }
    }

    override fun onEnable() {
        command.scoreBoard = ScoreBoard(getOrCreateObjective("Sample"))
    }
}

fun getOrCreateObjective(name: String): Objective {
    return Bukkit.getServer().scoreboardManager.mainScoreboard.getObjective(name)
        ?: Bukkit.getServer().scoreboardManager.mainScoreboard.registerNewObjective(name, "dummy")
}

class SampleCommand(var scoreBoard: ScoreBoard?) : Command("sample") {
    init {
        description("sample command")
        usage {
            selectionArgument("selection", "randomInt", "setDisplay")
            executes {
                when (typedArgs[0] as String) {
                    "randomInt" -> {
                        if (scoreBoard == null) {
                            fail("scoreboard is null")
                            return@executes
                        }
                        scoreBoard!!.int1.value = Random.nextInt(10000)
                        success("Random int: ${scoreBoard!!.int1.value}")
                    }
                    "setDisplay" -> {
                        if (scoreBoard == null) {
                            fail("scoreboard is null")
                            return@executes
                        }
                        scoreBoard!!.objective.displaySlot = DisplaySlot.SIDEBAR
                        success("Set display slot to SIDEBAR")
                    }
                }
            }
        }
    }
}