package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
)

func main() {
	i := traverseMap(1, 1)
	j := traverseMap(3, 1)
	k := traverseMap(5, 1)
	l := traverseMap(7, 1)
	m := traverseMap(1, 2)

	fmt.Println("Answer is: ", (i * j * k * l * m))
}

func traverseMap(xMove int, yMove int) int {

	fmt.Println("xMove: ", xMove, "yMove: ", yMove)

	f, e := os.Open("input.txt")
	if e != nil {
		log.Fatal("e")
	}
	defer f.Close()

	line := bufio.NewScanner(f)
	var x, y, tree int
	for line.Scan() {

		if y == 0 {
			fmt.Println(line.Text())
			y++
			continue
		}

		if y%yMove != 0 {
			fmt.Println(line.Text())
			y++
			continue
		}

		x += xMove
		// reset x if more than 30
		if x > 30 {
			x -= 31
		}

		txt := line.Text()
		destination := string(txt[x])

		if destination == "#" {
			tree++
			destination = "X"
		} else {
			destination = "O"
		}

		txt = string(txt[:x]) + destination + string(txt[x+1:])

		fmt.Println(txt, "       ", x)
		y++
	}

	fmt.Println("Num of tree is: ", tree)
	return tree
}
