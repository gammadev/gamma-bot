package br.com.gmfonseca.bot.battleship.game.models

import br.com.gmfonseca.bot.battleship.game.models.entities.Entity
import br.com.gmfonseca.bot.battleship.game.models.entities.ShipEntity
import kotlin.random.Random

class PlayerState(private val boardSize: Int, ships: List<Ship>) {

    private val ships = ships.map { ShipEntity(it) }

    private val _board: Array<Array<Entity?>> = Array(boardSize) { arrayOfNulls(boardSize) }
    var shipsRemaining: Int = ships.size; private set

    fun computePlay(row: Int, col: Int) {
        val entity = _board[row][col]
        if (entity is ShipEntity) {
            println("Hit!")
            _board[row][col] = Entity.Hit
            if (entity.computeHit()) {
                shipsRemaining--
                println("The computer destroyed one of your ships!")
            }
        } else if (entity !is Entity) {
            println("Miss!")
            _board[row][col] = Entity.Miss
        }

        println()
    }

    fun placeShips() {
        val random = Random.Default
        for (ship in ships) {
            var row: Int
            var col: Int
            var isVertical: Boolean
            do {
                row = random.nextInt(boardSize)
                col = random.nextInt(boardSize)
                isVertical = random.nextBoolean()
            } while (!isPlacementValid(row, col, ship.size, isVertical))

            for (i in 0 until ship.size) {
                if (isVertical) {
                    _board[row + i][col] = ship
                } else {
                    _board[row][col + i] = ship
                }
            }
        }
    }

    private fun isPlacementValid(
        startRow: Int,
        startCol: Int,
        length: Int,
        isVertical: Boolean
    ): Boolean {
        if (isVertical) {
            if (startRow + length > boardSize) {
                return false
            }
            for (i in 0 until length) {
                if (_board[startRow + i][startCol] != null) {
                    return false
                }
            }
        } else {
            if (startCol + length > boardSize) {
                return false
            }
            for (i in 0 until length) {
                if (_board[startRow][startCol + i] != null) {
                    return false
                }
            }
        }
        return true
    }

    fun hasShips() = shipsRemaining == 0

    fun get(row: Int, col: Int) = _board[row][col]
}
