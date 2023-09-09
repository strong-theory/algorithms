package main

import "fmt"

func find(array []int, el int) (int) {
	var inicio = 0
	var fim = len(array) - 1
	for (inicio <=fim) {
		var meio = (inicio + fim) /2
		var value = array[meio]
		if (value == el) {
			return meio;
		}
		if (value > el) {
			fim = meio - 1
		} else {
			inicio = meio + 1
		}
	}
	return -1
}

func main() {
	var array = [8]int{1, 3, 5, 7, 9, 11, 13, 15}

	var el = 13

	var index = find(array[:], el)

	fmt.Println("El ", el, " no index ", index)
}