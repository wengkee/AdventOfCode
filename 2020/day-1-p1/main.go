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

	s := bufio.NewScanner(f)
	var tmp []int
	for s.Scan() {

		i, err := strconv.Atoi(s.Text())
		if err != nil {
			log.Fatal("Not a number!")
		}

		tmp = append(tmp, i)
	}

	for i, v := range tmp {

		// fmt.Printf("idx: %d = %d\n", i, v)

		for x := i + 1; x < len(tmp); x++ {

			sum := v + tmp[x]

			if sum == 2020 {
				fmt.Printf("%d and %d", tmp[i], tmp[x])
				fmt.Printf("\n%d", (tmp[i] * tmp[x]))
			}

		}

	}

	// printSlice(tmp)
}

func printSlice(s []int) {
	fmt.Printf("len=%d cap=%d %v\n", len(s), cap(s), s)
}
