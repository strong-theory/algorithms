package main

import (
	"fmt"
)

func factorial(num int) int {
	if num == 0 {
		return 0
	}
	if num == 1 {
		return 1
	}
	return factorial(num-1) * num
}

func main() {
	fmt.Println("Calulando fatorial")
	for i := 0; i < 25; i++ {
		fmt.Println("Fat(", i, ") = ", factorial(i))
	}

}
