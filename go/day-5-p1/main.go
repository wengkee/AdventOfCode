package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"strconv"
)

func main() {

	f, e := os.Open("input.txt")
	if e != nil {
		log.Fatal("e")
	}
	defer f.Close()

	line := bufio.NewScanner(f)

	var biggestSeatID int

	for line.Scan() {
		in := line.Text()
		if in != "" {

			currRow := scanlowerUpper(in, 0, 127, 0, 7, "F", "B")
			currColumn := scanlowerUpper(in, 0, 7, 7, 10, "L", "R")

			seatID := currRow*8 + currColumn

			if biggestSeatID < seatID {
				biggestSeatID = seatID
			}

		}

	}

	fmt.Println("Answer is: ", biggestSeatID)

}

func scanlowerUpper(in string, min int, max int, forStart int, forEnd int, lower string, upper string) int {

	var curr int
	var c string

	for i := forStart; i < forEnd; i++ {

		c = string(in[i])

		if c == lower {
			max -= ((max - min + 1) / 2)

			if i == forEnd-1 {
				curr = min
			}

		} else if c == upper {
			min += ((max - min) / 2) + 1

			if i == forEnd-1 {
				curr = max
			}
		}

	}

	return curr
}

func isValidRange(str string, min int, max int, msg string) bool {
	num, err := strconv.Atoi(str)
	if err != nil || (num < min || num > max) {
		return false
	}

	return true
}

func stringInSlice(lst []string, a string) bool {
	for _, b := range lst {
		if b == a {
			return true
		}
	}
	return false
}
