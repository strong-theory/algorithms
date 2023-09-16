package main

import "fmt"

func fat(n int) int {
	if n == 0 {
		return 0
	}
	if n == 1 {
		return 1
	}

	return fat(n-1) * n
}

func maiorFatorial(n int) int {
	for i := 10; i > 0; i-- {
		fat := fat(i)
		if fat <= n {
			return fat
		}
	}
	return -1
}

func calc(n int) int {
	sum := n
	i := 0
	for sum != 0 {
		fat := maiorFatorial(sum)
		sum = sum - fat
		i++
	}
	return i
}

func test(n int, expected int) {
	result := calc(n)
	if result == expected {
		fmt.Println("Test OK. O", n, " equivale a soma de ", result, " fatoriais")
	} else {
		fmt.Println("Erro. Expected: ", expected, " result: ", result)
	}
}

func main() {
	fmt.Println("Soma de fatorial")

	test(10, 3)
	test(25, 2)
}
