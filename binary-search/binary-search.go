package main

import (
	"fmt"
	"math/rand"
)

func find(array []int, el int) (int, int) {
	var inicio = 0
	var fim = len(array) - 1
	var loops = 0
	for inicio <= fim {
		var meio = (inicio + fim) / 2
		var value = array[meio]
		if value == el {
			return meio, loops
		}
		if value > el {
			fim = meio - 1
		} else {
			inicio = meio + 1
		}
		loops++
	}
	return -1, loops
}

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
	const MAX_NUMBERS = 200_000
	var array [MAX_NUMBERS]int
	for i := 0; i < MAX_NUMBERS; i++ {
		array[i] = rand.Intn(MAX_NUMBERS / 100)
	}

	fmt.Println("Ordeando array...")
	var ordenado = quicksort(array[:])

	var el = 13

	fmt.Println("Pequisando o elemento ", el, " no array.")
	index, loops := find(ordenado[:], el)

	fmt.Println("Elemento ", el, " está no index: ", index, ". Total iteracoes: ", loops)
}
