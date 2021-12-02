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

	l := bufio.NewScanner(f)

	var listOfJolts []int
	for l.Scan() {
		line := l.Text()

		if line != "" {
			num, err := strconv.Atoi(line)

			if err != nil {
				fmt.Println("Invalid num: ", line)
				continue
			}

			listOfJolts = append(listOfJolts, num)

		}
	}

	listOfJolts = append(listOfJolts, listOfJolts[len(listOfJolts)-1]+3) // +3 for built-in adapter

	findQuickerPermutations(listOfJolts)

}

func findQuickerPermutations(listOfJolts []int) {

	sort.Ints(listOfJolts)

	m := map[int]int{0: 1}

	for _, num := range listOfJolts {
		m[num] = m[num-1] + m[num-2] + m[num-3]
	}

	fmt.Println(m[listOfJolts[len(listOfJolts)-1]])
}

func findPermutations(biggest int, permutation []int, mapOfJolts map[int]int) {

	var last int // 0 indicates charging outlet

	if len(permutation) > 0 {
		sort.Ints(permutation)
		last = permutation[len(permutation)-1]
	}

	if last == biggest {
		// permutations = append(permutations, permutation)
		fmt.Println(permutation)
		// permutations++
	} else {
		for i := 1; i <= 3; i++ {
			next := last + i
			if _, ok := mapOfJolts[next]; ok {

				new := make([]int, len(permutation))
				copy(new, permutation)
				new = append(new, next)
				// fmt.Println("new in ", i, ": ", new)

				findPermutations(biggest, new, mapOfJolts)
			}
		}
	}

}
