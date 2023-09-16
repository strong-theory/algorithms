package main

import "fmt"

func main() {
	var teams int
	var games int
	fmt.Scanf("%d %d\n", &teams, &games)

	for teams != 0 && games != 0 {
		max_points := teams * games
		sum_points := 0

		var team string
		var points int
		for i := 0; i < teams; i++ {
			fmt.Scanf("%s %d\n", &team, &points)
			sum_points += points
		}

		draws := max_points % sum_points
		fmt.Println(">>>", draws)

		fmt.Scanf("%d %d\n", &teams, &games)
	}
}
