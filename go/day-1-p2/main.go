package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"strconv"
)

// var pl = fmt.Println
// var pf = fmt.Printf

func main() {

	f, e := os.Open("input.txt")
	if e != nil {
		log.Fatal("e")
	}
	defer f.Close()

	s := bufio.NewScanner(f)
	var tmp []int
	for s.Scan() {

		i, err := strconv.Atoi(s.Text())
		if err != nil {
			log.Fatal("Not a number!")
		}

		tmp = append(tmp, i)
	}

	findSumOfThree(tmp)
}

func printSlice(s []int) {
	fmt.Printf("len=%d cap=%d %v\n", len(s), cap(s), s)
}

func findSumOfThree(listOfNum []int) {

	fmt.Println(len(listOfNum))

	for i, v1 := range listOfNum {

		for j, v2 := range listOfNum {

			if j == i {
				continue
			}

			for k, v3 := range listOfNum {
				if k == j || k == i {
					continue
				}

				sum := v1 + v2 + v3
				if sum == 2020 {

					product := (v1 * v2 * v3)
					fmt.Printf("%d + %d + %d, %d + %d + %d = %d \n", i, j, k, v1, v2, v3, sum)
					fmt.Printf("Answer is: %d\n", product)
					return
				}
			}

		}

	}
	return
}
