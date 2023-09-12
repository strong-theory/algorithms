package main

import (
	"fmt"
)

type Fifo[N Node] struct {
	first *N
	last  *N
}

type Node struct {
	valor int
	next  *Node
}

// add new element
func push(fifo *Fifo[Node], node *Node) {
	if fifo.first == nil {
		fifo.first = node
		fifo.last = node
	} else {
		fifo.last.next = node
		fifo.last = node
	}
}

// remove and return the first value
func pop(fifo *Fifo[Node]) Node {
	var node *Node
	node = fifo.first

	fifo.first = node.next

	return *node
}

// dont remove the value
func firstValue(fifo *Fifo[Node]) Node {
	return *fifo.first
}

func lastValue(fifo *Fifo[Node]) Node {
	return *fifo.last
}

func main() {
	var fifo Fifo[Node]

	// adding nodes to FIFO
	for i := 0; i < 10; i++ {
		var node Node
		node.valor = i + 100
		push(&fifo, &node)
	}

	fmt.Println("Fifo", fifo)
	fmt.Println("Pop value: ", pop(&fifo))
	fmt.Println("Pop value: ", pop(&fifo))
	fmt.Println("Pop value: ", pop(&fifo))
	fmt.Println("Pop value: ", pop(&fifo))

	fmt.Println("first.value: ", firstValue(&fifo))
	fmt.Println("last.value: ", lastValue(&fifo))
}
