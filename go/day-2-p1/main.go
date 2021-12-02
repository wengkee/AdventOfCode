package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"regexp"
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
		min, _ := strconv.Atoi(q[0])
		max, _ := strconv.Atoi(q[1])
		// fmt.Println(min, max)

		pattern := regexp.MustCompile(strings.TrimRight(fields[1], ":"))
		// fmt.Println(pattern)

		password := fields[2]
		matches := pattern.FindAllStringIndex(password, -1)
		if len(matches) < min || len(matches) > max {
			fmt.Println(pattern, password, min, max, len(matches))
		} else {
			valid++
		}

	}
	fmt.Println("Answer is:", valid)

}
