package main

import (
	"fmt"
	"math/rand"
)

func soma(array []int, size int) int {
	if size == 0 {
		return 0
	} else if size == 1 {
		return array[0]
	} else {
		return array[0] + soma(array[1:size], size-1)
	}
}

func main() {

	const ARRAY_SIZE = 5

	var array [ARRAY_SIZE]int

	for i := 0; i < ARRAY_SIZE; i++ {
		array[i] = rand.Intn(20)
	}

	var total = soma(array[:], 5)

	fmt.Println("Array: ", array)
	fmt.Println("Soma: ", total)
}
