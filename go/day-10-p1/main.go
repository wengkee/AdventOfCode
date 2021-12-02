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

	m := make(map[int]int)
	l := bufio.NewScanner(f)
	var listOfJolts []int
	for l.Scan() {
		line := l.Text()
		fmt.Println(line)

		if line != "" {
			num, err := strconv.Atoi(line)

			if err != nil {
				fmt.Println("Invalid num: ", line)
				continue
			}

			listOfJolts = append(listOfJolts, num)
		}
	}

	sort.Ints(listOfJolts)
	listOfJolts = append(listOfJolts, listOfJolts[len(listOfJolts)-1]+3) // +3 for built-in adapter

	var curr int // 0 jolt for charging outlet
	for i := 0; i < len(listOfJolts); i++ {
		elem := listOfJolts[i]
		gap := elem - curr

		if v, ok := m[gap]; ok {
			m[gap] = v + 1
		} else {
			m[gap] = 1
		}

		curr = elem

	}

	fmt.Println(listOfJolts, m)

	fmt.Println(m[1] * m[3])

}
