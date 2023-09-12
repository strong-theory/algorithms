package main

import (
	"fmt"
	"reflect"
)

type LinkedList[N Node[any]] struct {
	noInicial *N
	noFinal   *N
}

type Node[V any] struct {
	valor    V
	proxNode *Node[any]
}

func add(list *LinkedList[Node[any]], node *Node[any]) {
	if reflect.ValueOf(list.noInicial).IsZero() {
		list.noInicial = node
		list.noFinal = node
	} else {
		var currentNode *Node[any]

		currentNode = list.noInicial
		for !reflect.ValueOf(currentNode.proxNode).IsZero() {
			currentNode = currentNode.proxNode
		}
		currentNode.proxNode = node
		list.noFinal = node
	}
}

func printNode(node Node[any]) {
	fmt.Println("Node: {")
	fmt.Println("\tvalor:", node.valor)
	fmt.Println("\tproxNode:", node.proxNode)
	fmt.Println("}")
}

func printList(list LinkedList[Node[any]]) {
	var node *Node[any]

	node = list.noInicial
	for node != nil {
		printNode(*node)
		node = node.proxNode
	}
}

func contains(list *LinkedList[Node[any]], node Node[any]) bool {
	var currentNode *Node[any]

	currentNode = list.noInicial
	for currentNode != nil {
		if currentNode.valor == node.valor {
			return true
		}
		currentNode = currentNode.proxNode
	}

	return false
}

func main() {

	var list LinkedList[Node[any]]

	// adding nodes to list
	for i := 0; i < 10; i++ {
		var node Node[any]
		node.valor = i + 100
		add(&list, &node)
	}

	fmt.Println("Linked-list:")
	printList(list)

	var node Node[any]
	node.valor = 1010

	contains := contains(&list, node)
	fmt.Println("List contains", node, "? ", contains)
}
