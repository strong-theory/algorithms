package main

import (
	"fmt"
)

type Item struct {
	name      string
	weight    float32
	price     float32
	processed bool
}

func mostExpensiveItem(items *[]Item, maxWeight float32) *Item {

	price := float32(0.0)
	var returned *Item

	for i := 0; i < len(*items); i++ {
		var currentItem *Item
		currentItem = &(*items)[i]
		if currentItem.weight <= maxWeight && !currentItem.processed && price < currentItem.price {
			price = currentItem.price
			returned = currentItem
		}
	}
	if returned != nil {
		returned.processed = true
	}
	return returned
}

func calculte(items *[]Item, maxWeight float32) {
	mostExpensive := mostExpensiveItem(items, maxWeight)

	totalValue := float32(0)
	totalWeight := maxWeight
	for mostExpensive != nil {
		totalValue += mostExpensive.price
		totalWeight -= mostExpensive.weight
		fmt.Println("Added item: ", mostExpensive, " current weight: ", totalWeight)
		mostExpensive = mostExpensiveItem(items, totalWeight)
	}
}

func main() {
	fmt.Println("Backpack Problem")

	const MAX_WEIGHT = 6.0

	items := []Item{
		{
			name:   "Laptop",
			weight: 3.0,
			price:  2000.00,
		},
		{
			name:   "pants",
			weight: 1.0,
			price:  100.00,
		},
		{
			name:   "Book",
			weight: 1.0,
			price:  60.00,
		},
		{
			name:   "Blanket",
			weight: 5.0,
			price:  60.00,
		},
		{
			name:   "Radio",
			weight: 3.0,
			price:  50.00,
		},
		{
			name:   "lamp",
			weight: 2.0,
			price:  10.00,
		},
		{
			name:   "water",
			weight: 5.0,
			price:  5.00,
		},
	}

	calculte(&items, MAX_WEIGHT)

}
