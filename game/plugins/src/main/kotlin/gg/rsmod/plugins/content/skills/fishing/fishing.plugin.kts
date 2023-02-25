package gg.rsmod.plugins.content.skills.fishing

val MOVEMENT_TIMER = TimerKey()
val MOVEMENT_DELAY = 280..530

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
        // TODO: use npc.moveTo instead
        world.remove(npc)
        val newNpc = Npc(npc.id, group.newTile(npc.tile), world)
        world.spawn(newNpc)
//        npc.moveTo(group.newTile(npc.tile))
//        npc.timers[MOVEMENT_TIMER] = world.random(MOVEMENT_DELAY)
    }
}
