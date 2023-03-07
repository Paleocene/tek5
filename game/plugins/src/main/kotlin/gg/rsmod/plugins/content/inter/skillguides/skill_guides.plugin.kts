package gg.rsmod.plugins.content.inter.skillguides

import gg.rsmod.game.model.attr.SKILL_MENU

val SKILL_ID_VARBIT = 965
val LEVELED_SKILL_VARBIT = 4729

val LEVELUP_INTERFACE_ID = 741

on_interface_close(LEVELUP_INTERFACE_ID) {
    player.message("Closed interface: $LEVELUP_INTERFACE_ID, resetting milestone varbits", type = ChatMessageType.CONSOLE)
    player.setVarbit(Skills.TOTAL_MILESTONE_VARBIT, 0)
    player.setVarbit(Skills.TOTAL_MILESTONE_VALUE, 0)
    player.setVarbit(Skills.COMBAT_MILESTONE_VARBIT, 0)
    player.setVarbit(Skills.COMBAT_MILESTONE_VALUE, 0)
}

SkillGuide.values.forEach { guide ->
    on_button(interfaceId = 320, component = guide.child) {
        if (!player.lock.canInterfaceInteract()) {
            return@on_button
        }

        val skill = guide.id
        val bit = guide.bit

        if(player.getVarbit(Skills.FLASHING_ICON_VARBITS[skill]) > 0) {

            // set the varbit for the skill advance guide we're viewing
            player.setVarbit(LEVELED_SKILL_VARBIT, bit)

            // set the varc for our last viewed level
            player.setVarc(Skills.LEVELLED_AMOUNT_VARC[bit - 1], player.getSkills().getLastLevel(skill))

            // disable the flashing icon
            player.setVarbit(Skills.FLASHING_ICON_VARBITS[skill], 0)

            // set our last viewed level to our current level
            player.getSkills().setLastLevel(skill, player.getSkills().getCurrentLevel(skill))

            player.getSkills().setLastTotalLevel(player.getSkills().calculateTotalLevel)
            player.getSkills().setLastCombatLevel(player.combatLevel)

            // open the skill advance guide
            player.openInterface(interfaceId = 741, dest = InterfaceDestination.MAIN_SCREEN_FULL)
        } else {
            player.setVarp(SKILL_ID_VARBIT, bit)
            player.attr[SKILL_MENU] = guide.bit
            player.openInterface(interfaceId = 499, dest = InterfaceDestination.MAIN_SCREEN_FULL)
        }
    }
}

for(buttonId in 10..25) {
    on_button(interfaceId = 499, component = buttonId) {
        player.setVarp(SKILL_ID_VARBIT, (buttonId - 10) * 1024 + player.attr[SKILL_MENU]!!)
    }
}

