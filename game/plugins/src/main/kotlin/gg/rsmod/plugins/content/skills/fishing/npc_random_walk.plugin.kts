//package gg.rsmod.plugins.content.skills.fishing
//
//import gg.rsmod.game.model.attr.FACING_PAWN_ATTR
//import gg.rsmod.game.model.attr.NO_CLIP_ATTR
//
//val MOVEMENT_TIMER = TimerKey()
//val MOVEMENT_DELAY = 280..530
//
//for ()
//
//on_global_npc_spawn {
//    if (npc.walkRadius > 0) {
//        npc.timers[SEARCH_FOR_PATH_TIMER] = world.random(SEARCH_FOR_PATH_DELAY)
//    }
//}
//
//on_timer(SEARCH_FOR_PATH_TIMER) {
//    if (npc.isActive() && npc.lock.canMove()) {
//        val facing = npc.attr[FACING_PAWN_ATTR]?.get()
//
//        /*
//         * The npc is not facing a player, so it can walk.
//         */
//        if (facing == null) {
//            val rx = world.random(-npc.walkRadius..npc.walkRadius)
//            val rz = world.random(-npc.walkRadius..npc.walkRadius)
//
//            val start = npc.spawnTile
//            val dest = start.transform(rx, rz)
//
//            val noClip = npc.attr[NO_CLIP_ATTR] ?: false
//
//            /*
//             * Only walk to destination if the chunk has previously been created.
//             */
//            if (world.collision.chunks.get(dest, createIfNeeded = false) != null) {
//                npc.walkMask = npc.def.walkMask
//                npc.walkTo(dest, detectCollision = !noClip)
//            }
//        }
//    }
//
//    npc.timers[SEARCH_FOR_PATH_TIMER] = world.random(SEARCH_FOR_PATH_DELAY)
//}