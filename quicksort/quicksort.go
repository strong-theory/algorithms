package main

import (
	"fmt"
	"math/rand"
)

func quicksort(array []int) []int {

	size := len(array)
	if size < 2 {
		return array
	}

	meio := size / 2
	pivo := array[meio]

	var totalMenores = 0
	var menores = make([]int, size)

	for i := 0; i < size; i++ {
		if i == meio {
			continue
		}
		valor := array[i]
		if valor <= pivo {
			menores[totalMenores] = valor
			totalMenores = totalMenores + 1
		}
	}

	menores = menores[0:totalMenores]

	var totalMaiores = 0
	maiores := make([]int, size)
	for i := 0; i < size; i++ {
		valor := array[i]
		if valor > pivo {
			maiores[totalMaiores] = valor
			totalMaiores++
		}
	}

	maiores = maiores[0:totalMaiores]
	menores = quicksort(menores)
	maiores = quicksort(maiores)

	result := make([]int, size)

	indexResult := 0
	for i := 0; i < len(menores); i++ {
		result[indexResult] = menores[i]
		indexResult++
	}

	result[indexResult] = pivo
	indexResult++

	for i := 0; i < len(maiores); i++ {
		result[indexResult] = maiores[i]
		indexResult++
	}

	return result
}

func main() {
	const ARRAY_SIZE = 20
	const SEED = 10

	var array [ARRAY_SIZE]int
	for i := 0; i < ARRAY_SIZE; i++ {
		array[i] = rand.Intn(SEED)
	}
	fmt.Println("array original:\t", array[:])
	fmt.Println("ordenado:\t", quicksort(array[:]))
}
