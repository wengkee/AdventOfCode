package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
)

func main() {
	f, e := os.Open("input.txt")
	if e != nil {
		log.Fatal("e")
	}
	defer f.Close()

	line := bufio.NewScanner(f)
	var x, y, tree int
	for line.Scan() {

		y++

		if y == 1 {
			continue
		}

		x += 3
		// reset y if more than 30
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
	}

	fmt.Println("Answer is: ", tree)
}
