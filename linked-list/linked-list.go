package main

import (
	"fmt"
	"reflect"
)

type LinkedList[N Node[any]] struct {
	firstNode *N
	lastNode  *N
}

type Node[V any] struct {
	value        V
	previousNode *Node[any]
	nextNode     *Node[any]
}

// O(1) to add element
func add(list *LinkedList[Node[any]], node *Node[any]) {
	if reflect.ValueOf(list.firstNode).IsZero() {
		list.firstNode = node
		list.lastNode = node
	} else {
		node.previousNode = list.lastNode
		list.lastNode.nextNode = node
		list.lastNode = node
	}
}

// O(1) to remove any node
func remove(node *Node[any]) {
	if node.previousNode != nil {
		*node.previousNode.nextNode = *node.nextNode
	}
}

func printNode(node Node[any]) {
	fmt.Println("Node: {")
	fmt.Println("\tvalor:", node.value)
	fmt.Println("\tproxNode:", node.nextNode)
	fmt.Println("}")
}

func printList(list LinkedList[Node[any]]) {
	var node *Node[any]

	node = list.firstNode
	for node != nil {
		printNode(*node)
		node = node.nextNode
	}
}

func containsNode(list *LinkedList[Node[any]], node Node[any]) bool {
	var currentNode *Node[any]

	currentNode = list.firstNode
	for currentNode != nil {
		if currentNode.value == node.value {
			return true
		}
		currentNode = currentNode.nextNode
	}

	return false
}

// O(n) to get the nth element
func get(list LinkedList[Node[any]], index int) Node[any] {

	currentIndex := 1
	var currentNode *Node[any]

	currentNode = list.firstNode
	for !reflect.ValueOf(currentNode.nextNode).IsZero() && currentIndex < index {
		currentNode = currentNode.nextNode
		currentIndex++
	}
	return *currentNode
}

func main() {

	var list LinkedList[Node[any]]

	// adding nodes to list
	for i := 0; i < 10; i++ {
		var node Node[any]
		node.value = i + 100
		add(&list, &node)
	}

	fmt.Println("Linked-list:")
	printList(list)

	var node Node[any]
	node.value = 1010

	contains := containsNode(&list, node)
	fmt.Println("List contains", node, "? ", contains)

	node5th := get(list, 5)
	fmt.Println("\nGet 5th element: ", node5th)
	contains5th := containsNode(&list, node5th)
	fmt.Println("List contains", node5th, "? ", contains5th)

	fmt.Println("Removing", node5th)
	remove(&node5th)

	contains5th = containsNode(&list, node5th)
	fmt.Println("List contains", node5th, "? ", contains5th)
}
