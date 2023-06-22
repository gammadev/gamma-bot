package br.com.gmfonseca.bot.battleship.game.models.entities

import br.com.gmfonseca.bot.battleship.game.models.Ship

class ShipEntity(ship: Ship) : Entity(ship.symbol) {

    val size: Int = ship.size
    private var remainingSize: Int = ship.size

    init {
        require(ship.size > 0) { "A ship should be greater than zero." }
    }

    /**
     * returns True if destroyed
     */
    fun computeHit(): Boolean = synchronized(this) {
        if (remainingSize > 0) {
            remainingSize--
        }

        return remainingSize <= 0
    }
}