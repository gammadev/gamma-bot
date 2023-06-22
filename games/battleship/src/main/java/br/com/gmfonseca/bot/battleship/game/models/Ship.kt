package br.com.gmfonseca.bot.battleship.game.models

sealed class Ship(val size: Int, val symbol: Char) {
    object Carrier : Ship(size = 5, symbol = 'C')
    object Battleship : Ship(size = 4, symbol = 'B')
    object Destroyer : Ship(size = 3, symbol = 'D')
    object Submarine : Ship(size = 3, symbol = 'S')
    object PatrolBoat : Ship(size = 2, symbol = 'P')
}