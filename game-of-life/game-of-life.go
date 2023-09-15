package main

import (
	"fmt"
	"math/rand"
	"time"
)

const WIDTH = 25
const HEIGHT = 25

func printBoard(board [WIDTH][HEIGHT]int) {
	for i := 0; i < len(board); i++ {
		for j := 0; j < len(board[i]); j++ {
			if board[i][j] == 1 {
				fmt.Print("▤")
			} else {
				fmt.Print(" ")
			}
		}
		fmt.Println()
	}
}

func nextRound(board [WIDTH][HEIGHT]int) [WIDTH][HEIGHT]int {
	var next [WIDTH][HEIGHT]int

	for i := 0; i < WIDTH; i++ {
		for j := 0; j < HEIGHT; j++ {
			totalNeighbours := countNeighbours(board, i, j)
			if totalNeighbours <= 1 || totalNeighbours >= 4 {
				next[i][j] = 0
			} else if totalNeighbours == 2 {
				next[i][j] = board[i][j]
			} else {
				next[i][j] = 1
			}
		}
	}
	return next
}

func countNeighbours(board [WIDTH][HEIGHT]int, i int, j int) int {

	total := 0
	for x := -1; x <= 1; x++ {
		for y := -1; y <= 1; y++ {
			if x == 0 && y == 0 {
				continue
			}

			nextX := i + x
			nextY := j + y
			if nextX < 0 || nextY < 0 || nextX >= WIDTH || nextY >= HEIGHT {
				continue
			}
			pos := board[nextX][nextY]
			if pos == 1 {
				total++
			}
		}
	}
	return total
}

func initRandomBoard() [WIDTH][HEIGHT]int {
	var board [WIDTH][HEIGHT]int
	for i := 0; i < WIDTH; i++ {
		for j := 0; j < HEIGHT; j++ {
			board[i][j] = rand.Intn(2)
		}
	}
	return board
}

func initWithOscilator() [WIDTH][HEIGHT]int {
	board := [WIDTH][HEIGHT]int{
		{0, 0, 0, 0, 0},
		{0, 0, 1, 0, 0},
		{0, 0, 1, 0, 0},
		{0, 0, 1, 0, 0},
		{0, 0, 0, 0, 0},
	}
	return board
}

func clear() {
	fmt.Print("\033[H\033[2J")
}

func main() {
	clear()
	fmt.Println("**** Game of Life ****")

	board := initRandomBoard()

	printBoard(board)

	nextRodada := '\n'
	round := 0
	for nextRodada == '\n' || nextRodada == '\r' {
		//fmt.Print("Deseja continuar ?\n")
		//fmt.Scanf("%c\n", &nextRodada)

		board = nextRound(board)

		clear()
		fmt.Println("Round ", round)
		printBoard(board)
		round++
		time.Sleep(100 * time.Millisecond)
	}

}
