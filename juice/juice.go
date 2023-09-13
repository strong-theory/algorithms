package main

import "fmt"

func main() {

	const JUICE_PER_FRUIT = 0.050

	var friends int
	var fruits int
	fmt.Scanf("%d %d\n", &friends, &fruits)

	for friends != 0 && fruits != 0 {
		total_juice := JUICE_PER_FRUIT * float64(fruits)

		mean := total_juice / float64(friends)

		fmt.Printf(">>%.2f\n", mean)
		fmt.Scanf("%d %d\n", &friends, &fruits)
	}
}
