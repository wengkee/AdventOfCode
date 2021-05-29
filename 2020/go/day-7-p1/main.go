package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"strings"
	"unicode"
)

func main() {
	f, e := os.Open("input.txt")
	if e != nil {
		log.Fatal("e")
	}
	defer f.Close()

	// m := make(map[string]string)

	l := bufio.NewScanner(f)
	for l.Scan() {
		line := l.Text()

		if line != "" {

			c := func(c rune) bool {
				return !unicode.IsLetter(c) && !unicode.IsNumber(c)
			}

			words := strings.FieldsFunc(line, c)

			// child: []string parents
			var parent, child string
			var parentFound bool = false
			var findParentMap map[string][]string = []string{}

			for i, v := range words {

				if strings.HasPrefix(v, "bag") {

					if !parentFound {

						parent = words[i-2] + " " + words[i-1]
						fmt.Println("parent: ", parent)
						parentFound = true

					} else {

						child = words[i-2] + " " + words[i-1]

						if !strings.HasPrefix(child, "no other") {

							fmt.Println("child: ", child)
							parents := []string{}

							if lst, ok := findParentMap[child]; ok {
								parents = lst
							}

							parents = append(parents, parent)
							findParentMap[child] = parents
						}

					}
				}

				fmt.Println(findParentMap)

			}

			// fmt.Println(m)
		}
	}

}
