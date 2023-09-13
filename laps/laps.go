package main

import (
	"fmt"
	"math"
	"os"
	"strconv"
)

// Ex.: Circuit with 35m
// spped for X 35/5 = 7 m/s
// speed for Y 35/7 = 5 m/s
// speed diff = 2m/s
// To take 1 lap over the second place, the first place should run an entire lap (35m) more than the second place.
// With 2 m/s faster than the second place, the first place will take 17.5s to complete 35m of difference.
// The first place take 5s each lap, so 17.5 will take more than 3 laps.
// So the answer is 4 laps to the first place complete a lap over the second place.
func calc(x int, y int) int {

	size_lap := float64(1.0)

	speedX := size_lap / float64(x)
	speedY := size_lap / float64(y)

	diffSpeed := (speedX - speedY)
	secondsToCompleteLap := float64(size_lap / diffSpeed)

	laps := secondsToCompleteLap / float64(x)

	return int(math.Ceil(laps))
}

func getArg(arg string) int {
	x, e := strconv.Atoi(arg)
	_ = e
	return x
}

func main() {

	args := os.Args[1:]

	x := getArg(args[0])
	y := getArg(args[1])

	fmt.Println(calc(x, y))
}
