package main

import (
	"fmt"
	"math"
)

func pages(n int, l int, c int, text string) int {
	size := len(text)

	total_lines := float64(size) / float64(c)

	pages := total_lines / float64(l)

	return int(math.Ceil(pages))
}

func test(n int, l int, c int, text string, expected int) {
	pages := pages(n, l, c, text)
	if pages == expected {
		fmt.Println("test2 OK")
	} else {
		fmt.Println("test Erro. Expected: ", expected, " current: ", pages)
	}
}

func main() {
	fmt.Println("Concurso")
	test(14, 4, 20, "Se mana Piedade tem casado com Quincas Borba apenas me daria uma esperanca colateral", 2)
	test(16, 3, 30, "No dia seguinte entrou a dizer de mim nomes feios e acabou alcunhando me Dom Casmurro", 1)
	test(5, 2, 2, "a de i de o", 3)
	test(5, 2, 2, "a e i o u", 3)
}
