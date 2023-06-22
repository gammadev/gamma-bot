package br.com.gmfonseca.bot.battleship.game.models.entities

sealed class Entity(val symbol: Char) {
    object Miss : Entity('o')
    object Hit : Entity('x')

    override fun toString(): String {
        return symbol.toString()
    }
}