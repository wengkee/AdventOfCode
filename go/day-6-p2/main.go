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

	m := make(map[string]int)
	var sum, numOfPersons, numOfQuestions int

	l := bufio.NewScanner(f)
	for l.Scan() {
		line := l.Text()
		// fmt.Println(line)

		if line != "" {
			for i := 0; i < len(line); i++ {
				c := string(line[i])

				if v, ok := m[c]; ok {
					m[c] = v + 1
				} else {
					m[c] = 1
				}

			}
			numOfPersons++

		} else {

			for _, v := range m {
				if v == numOfPersons {
					numOfQuestions++
				}
			}

			sum += numOfQuestions
			m = make(map[string]int)
			numOfPersons = 0
			numOfQuestions = 0
		}

	}

	if len(m) > 0 {
		for _, v := range m {
			if v == numOfPersons {
				numOfQuestions++
			}
		}
		sum += numOfQuestions
		m = make(map[string]int)
	}

	fmt.Println("Answer is: ", sum)
}
