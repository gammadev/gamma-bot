package br.com.gmfonseca.bot.battleship.game

import br.com.gmfonseca.bot.battleship.game.models.PlayerState
import br.com.gmfonseca.bot.battleship.game.models.Ship
import br.com.gmfonseca.bot.battleship.game.models.entities.ShipEntity
import kotlin.random.Random

class BattleshipGame(private val boardSize: Int, ships: List<Ship>) {
    private val playerBoard = PlayerState(boardSize, ships)
    private val computerBoard = PlayerState(boardSize, ships)

    private val firstChar = Char(FIRST_LETTER_CODE)
    private val lastChar = Char(FIRST_LETTER_CODE + boardSize - 1)

    fun play() {
        initializeBoards()

        while (playerBoard.shipsRemaining > 0 && computerBoard.shipsRemaining > 0) {
            printBoards()
            playerTurn()
            if (playerBoard.shipsRemaining == 0) {
                break
            }
            computerTurn()
        }

        printBoards(secret = false)
        announceWinner()
    }

    private fun initializeBoards() {
        playerBoard.placeShips()
        computerBoard.placeShips()
    }

    private fun printBoards(secret: Boolean = true) {
        println("Player Board:")
        printBoard(playerBoard)
        println("Computer Board:")
        printBoard(computerBoard, hide = secret)
        println()
    }

    private fun printBoard(state: PlayerState, hide: Boolean = false) {
        print("    ")
        for (j in 0 until boardSize) {
            print("$j ")
        }
        println()
        for (i in 0 until boardSize) {
            print("${Char(FIRST_LETTER_CODE + i)} | ")
            for (j in 0 until boardSize) {
                val symbol = state.get(i, j).takeIf { !hide || it !is ShipEntity } ?: '-'
                print("$symbol ")
            }
            println()
        }
    }

    private fun playerTurn() {
        println("Player's Turn:")
        val row = getPlayerInput("Enter the row ($firstChar-$lastChar): ")
        val col = getPlayerInput("Enter the column (0-${boardSize - 1}): ")

        computerBoard.computePlay(row, col)
    }

    private fun computerTurn() {
        println("Computer's Turn:")
        val random = Random.Default
        var row: Int
        var col: Int
        do {
            row = random.nextInt(boardSize)
            col = random.nextInt(boardSize)
        } while (playerBoard.get(row, col) != null && playerBoard.get(row, col) !is ShipEntity)

        playerBoard.computePlay(row, col)
    }

    private fun getPlayerInput(prompt: String): Int {
        print(prompt)
        val input = readlnOrNull()?.replace(" ", "")?.let {
            it.firstOrNull()?.hashCode()?.let { tmp ->
                if (tmp in firstChar.hashCode()..lastChar.hashCode()) {
                    tmp - firstChar.hashCode()
                } else null
            } ?: it.toIntOrNull()
        }

        return input ?: getPlayerInput(prompt)
    }

    private fun announceWinner() {
        if (playerBoard.hasShips() && computerBoard.hasShips()) {
            println("It's a draw! Both sides destroyed all the ships.")
        } else if (playerBoard.hasShips()) {
            println("Game Over. Computer wins!")
        } else {
            println("Congratulations! You sank all the computer's ships. You win!")
        }
    }

    private companion object {
        val FIRST_LETTER_CODE = 'a'.hashCode()
    }
}
