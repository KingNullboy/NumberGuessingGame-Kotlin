import EasyKotlin.*
import java.io.File

fun main() {
	println("KingNullboy's Kotlin Number Guessing Game")
	
	while (true) {
		val num = (1..100).random()
		var guess = -1
		val file = File("save.txt")
		if (file.length() == 0L) {
			file.writeText("0,0")
		}
		var (plays, wins) = file.readText().split(",").map { it.toInt() }
		
		println("You have played $plays time${if (plays == 1) { "" } else {"s"}} before, and won $wins of those times for a win rate of ${(wins.toDouble()/maxOf(1,plays))*100}%.")
		
		plays++
		
		while (num != guess) {
			try {
				guess = input("What is your guess of the secret number?\n> ").toInt()
			} catch (e: NumberFormatException) {
				println("Buddy, that's not a number. Defaulting to 50.")
				guess = 50
			}

			if (guess < 1 || guess > 100) {
				println("That's not a number between 1 and 100. Can't you read?")
			} else if (guess > num) {
				println("That number is greater than the secret number.")
			} else if (guess < num) {
				println("That number is less than the secret number.")
			} else if (guess == num) {
				println("You win!")
				wins++
				break
			}
		}

		file.writeText("$plays,$wins")

		val again = input("Play again? (Y/n)\n> ")
		if (again.lowercase().startsWith("y")) {
			continue
		} else if (again.lowercase().startsWith("n")) {
			break
		} else {
			println("That doesn't start with y or n. Can't you read?")
			println("Assuming no.")
			break
		}
	}
}