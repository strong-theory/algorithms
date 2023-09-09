package main

import "fmt"

func fib(max int) int {
	if max == 0 {
		return 0
	} else if max == 1 {
		return 1
	} else if max == 2 {
		return 1
	}
	return fib(max-1) + fib(max-2)
}

func main() {
	for i := 0; i < 10; i++ {
		fmt.Println((i * 5), " termo da sequencia é: ", fib(i*5))
	}
}
