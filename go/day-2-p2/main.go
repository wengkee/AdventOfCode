package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"strconv"
	"strings"
)

func main() {

	f, e := os.Open("input.txt")
	if e != nil {
		log.Fatal("e")
	}
	defer f.Close()

	line := bufio.NewScanner(f)

	var valid int

	for line.Scan() {

		txt := line.Text()
		fields := strings.Fields(txt)

		q := strings.Split(fields[0], "-")
		first, _ := strconv.Atoi(q[0])
		second, _ := strconv.Atoi(q[1])
		// fmt.Println(min, max)

		// pattern := regexp.MustCompile(strings.TrimRight(fields[1], ":"))
		pattern := strings.TrimRight(fields[1], ":")
		// fmt.Println(pattern)

		password := fields[2]

		// fmt.Println(firstC, secondC)

		// matches := pattern.FindAllStringIndex(password, -1)
		// if len(matches) < min || len(matches) > max {
		// 	fmt.Println(pattern, password, min, max, len(matches))
		// } else {
		// 	valid++
		// }

		if checkBothChar(password, first-1, second-1, pattern) {
			valid++
		}

	}
	fmt.Println("Answer is:", valid)

}

func checkBothChar(password string, first int, second int, pattern string) bool {

	var isValid bool = false

	if len(password) >= first {
		c := string(password[first])

		if c == pattern {
			fmt.Println("First:", password, first, c, pattern)
			isValid = !isValid
		}

	}

	if len(password) >= second {
		c := string(password[second])

		if c == pattern {
			fmt.Println("Second:", password, second, c, pattern)
			isValid = !isValid
		}

	}

	return isValid
}
