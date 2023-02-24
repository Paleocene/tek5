package gg.rsmod.plugins.content.skills.fishing

import gg.rsmod.game.model.Tile

enum class FishingSpotGroup(val tiles: List<Tile>) {
    LUMBRIDGE_RIVER(listOf(Tile(3239, 3241), Tile(3239, 3242), Tile(3239, 3243), Tile(3239, 3244), Tile(3238, 3251), Tile(3238, 3252), Tile(3238, 3254), Tile(3238, 3255)))
    ;

    fun newTile(previous: Tile): Tile {
        return (tiles - previous).random()
    }

    companion object {
        fun forTile(tile: Tile): FishingSpotGroup? {
            return values().firstOrNull { tile in it.tiles }
        }
    }
}
