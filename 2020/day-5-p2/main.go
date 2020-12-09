package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"sort"
	"strconv"
)

func main() {

	f, e := os.Open("input.txt")
	if e != nil {
		log.Fatal("e")
	}
	defer f.Close()

	line := bufio.NewScanner(f)

	var smallestSeatID, biggestSeatID int
	var listOfSeatID []int

	for line.Scan() {
		in := line.Text()
		if in != "" {

			currRow := scanlowerUpper(in, 0, 127, 0, 7, "F", "B")
			currColumn := scanlowerUpper(in, 0, 7, 7, 10, "L", "R")

			seatID := currRow*8 + currColumn

			if biggestSeatID < seatID {
				biggestSeatID = seatID
			}

			if smallestSeatID > seatID {
				smallestSeatID = seatID
			}

			listOfSeatID = append(listOfSeatID, seatID)
		}

	}

	sort.Ints(listOfSeatID)

	var expected, myseat int
	for i := 0; i < len(listOfSeatID); i++ {

		curr := listOfSeatID[i]

		if i != 0 && curr != expected {
			// fmt.Println("expected: ", expected, "current: ", curr)
			myseat = expected
		}

		expected = curr + 1
	}

	fmt.Println("Answer is: ", myseat)
	// fmt.Println("smallestSeatID is: ", smallestSeatID)
	// fmt.Println("biggestSeatID is: ", biggestSeatID)

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

		// fmt.Println(c, min, max, curr)

	}

	// fmt.Println(in, c, min, max, curr)
	// fmt.Println("##################################")

	return curr
}

func isValidRange(str string, min int, max int, msg string) bool {
	num, err := strconv.Atoi(str)
	if err != nil || (num < min || num > max) {
		// fmt.Println("Invalid "+msg+": ", str)
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
