package gg.rsmod.plugins.content.skills.fishing

import gg.rsmod.plugins.content.magic.TeleportType
import gg.rsmod.plugins.content.magic.teleport

val MOVEMENT_TIMER = TimerKey()
//val MOVEMENT_DELAY = 280..530
val MOVEMENT_DELAY = 15..15

FishingSpot.values().forEach { spot ->
    spot.objectIds.forEach { spotId ->
        spot.tools.forEach { tool ->
            on_npc_option(spotId, tool.option) {
                val fishingSpot = player.getInteractingNpc()
                player.interruptQueues()
                player.resetInteractions()
                player.queue {
                    Fishing.fish(this, fishingSpot, tool)
                }
            }
        }
    }
}

FishingSpot.values().forEach { spot ->
    spot.objectIds.forEach { spotId ->
        on_npc_spawn(spotId) {
            npc.timers[MOVEMENT_TIMER] = world.random(MOVEMENT_DELAY)
        }
    }
}

on_timer(MOVEMENT_TIMER) {
    FishingSpotGroup.forTile(npc.tile)?.let { group ->
        npc.moveTo(group.newTile(npc.tile))
//        npc.walkTo(group.newTile(npc.tile), detectCollision = false)
        npc.timers[MOVEMENT_TIMER] = world.random(MOVEMENT_DELAY)
    }
}
