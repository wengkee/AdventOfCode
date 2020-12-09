package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"strings"
	"unicode"
)

type passport struct {
	byr string //(Birth Year)
	iyr string //(Issue Year)
	eyr string //(Expiration Year)
	hgt string //(Height)
	hcl string //(Hair Color)
	ecl string //(Eye Color)
	pid string //(Passport ID)
	cid string //(Country ID)
}

func main() {

	f, e := os.Open("input.txt")
	if e != nil {
		log.Fatal("e")
	}
	defer f.Close()

	line := bufio.NewScanner(f)

	// p := passport{}
	// var listOfPassport []passport

	m := make(map[string]string)

	var validPassport int

	for line.Scan() {

		if line.Text() != "" {
			c := func(c rune) bool {
				return !unicode.IsLetter(c) && !unicode.IsNumber(c)
			}

			// fmt.Println(line.Text())
			lst := strings.FieldsFunc(line.Text(), c)
			// fmt.Printf("Fields are: %q", lst)

			for len(lst) > 0 {
				var elem, value string
				elem, lst = lst[0], lst[1:] // popping
				// fmt.Println(elem)

				if stringInSlice(elem) {
					value, lst = lst[0], lst[1:] // popping
					m[elem] = value
				}

			}
		} else {
			// fmt.Println("###################################################################################")

			if isValidPassport(m) {
				validPassport++
			}

			// fmt.Println(m)
			m = make(map[string]string)
		}

	}

	// EOF
	if len(m) > 0 {
		if isValidPassport(m) {
			validPassport++
		}
		m = make(map[string]string)
	}

	fmt.Println("Answer: ", validPassport)

}

func isValidPassport(m map[string]string) bool {
	if len(m) == 8 {
		return true
	}

	if len(m) == 7 {
		if _, ok := m["cid"]; !ok {
			fmt.Println(m)
			return true
		}
	}
	return false
}

func stringInSlice(a string) bool {
	list := []string{
		"byr",
		"iyr",
		"eyr",
		"hgt",
		"hcl",
		"ecl",
		"pid",
		"cid",
	}
	for _, b := range list {
		if b == a {
			return true
		}
	}
	return false
}
