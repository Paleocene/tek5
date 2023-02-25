package gg.rsmod.plugins.content.skills.fishing

import gg.rsmod.game.model.Tile

enum class FishingSpotGroup(val tiles: List<Tile>) {
    LUMBRIDGE_RIVER(listOf(Tile(3239, 3241), Tile(3239, 3242), Tile(3239, 3243), Tile(3239, 3244), Tile(3238, 3251), Tile(3238, 3252), Tile(3238, 3254), Tile(3238, 3255))),
    AL_KHARID(listOf(Tile(3268, 3147), Tile(3267, 3148), Tile(3276, 3140), Tile(3275, 3140), Tile(3278, 3138))),
    DRAYNOR(listOf(Tile(3086, 3227), Tile(3085, 3230), Tile(3086, 3228), Tile(3085, 3231))),
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
