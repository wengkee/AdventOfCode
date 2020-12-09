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

	m := make(map[string]string)
	var sum int

	l := bufio.NewScanner(f)
	for l.Scan() {
		line := l.Text()
		// fmt.Println(line)

		if line != "" {

			for i := 0; i < len(line); i++ {
				c := string(line[i])
				m[c] = "1"
			}

		} else {
			// check map size
			sum += len(m)
			// fmt.Println(len(m))
			m = make(map[string]string)
		}

	}

	if len(m) > 0 {
		sum += len(m)
		// fmt.Println(len(m))
		m = make(map[string]string)
	}

	fmt.Println("Answer is: ", sum)
}
