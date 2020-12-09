package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"regexp"
	"strconv"
	"strings"
	"unicode"
)

var eyesColorList []string = []string{
	"amb", "blu", "brn", "gry", "grn", "hzl", "oth",
}

func main() {

	f, e := os.Open("input.txt")
	if e != nil {
		log.Fatal("e")
	}
	defer f.Close()

	line := bufio.NewScanner(f)

	m := make(map[string]string)

	var validPassport int

	passportList := []string{
		"byr",
		"iyr",
		"eyr",
		"hgt",
		"hcl",
		"ecl",
		"pid",
		"cid",
	}

	for line.Scan() {

		if line.Text() != "" {
			c := func(c rune) bool {
				return !unicode.IsLetter(c) && !unicode.IsNumber(c) && c != '#'
			}

			lst := strings.FieldsFunc(line.Text(), c)

			for len(lst) > 0 {
				var elem, value string
				elem, lst = lst[0], lst[1:] // popping

				if stringInSlice(passportList, elem) {
					value, lst = lst[0], lst[1:] // popping
					m[elem] = value
				}

			}
		} else {

			if isValidPassport(m) {
				validPassport++
			}

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

	// less than min
	if len(m) < 7 {
		return false
	}

	// meet bare min but optional field present
	if len(m) == 7 {
		if _, ok := m["cid"]; ok {
			return false
		}
	}

	// byr validation
	byr := m["byr"]
	if !isValidRange(byr, 1920, 2002, "birthyear") {
		return false
	}

	// iyr validation
	iyr := m["iyr"]
	if !isValidRange(iyr, 2010, 2020, "issueyear") {
		return false
	}

	// eyr validation
	eyr := m["eyr"]
	if !isValidRange(eyr, 2020, 2030, "expirationyear") {
		return false
	}

	// height validation
	if hgt, ok := m["hgt"]; ok {
		r := regexp.MustCompile(`(?P<num>\d{1,3})(?P<unit>[a-z]{2})`)

		hgtParts := r.FindStringSubmatch(hgt)
		if len(hgtParts) < 2 {
			return false
		}

		h := hgtParts[1]
		unit := hgtParts[2]

		if unit != "cm" && unit != "in" {
			fmt.Println("#####################################", unit)
			return false
		}

		if unit == "cm" {
			if !isValidRange(h, 150, 193, "height") {
				return false
			}
		} else if unit == "in" {
			if !isValidRange(h, 59, 76, "height") {
				return false
			}
		}

	} else {
		return false
	}

	// validate hair color
	if hcl, ok := m["hcl"]; ok {
		match, err := regexp.MatchString(`#[a-f0-9]{6}`, hcl)
		if err != nil || !match {
			// fmt.Println("######################### hcl wrong: ", hcl)
			return false
		}

	} else {
		return false
	}

	// Validate eye color
	if ecl, ok := m["ecl"]; ok {
		if !stringInSlice(eyesColorList, ecl) {
			// fmt.Println("#################### invalid ecl: ", ecl)
			return false
		}
	} else {
		return false
	}

	// Validate Passport ID
	if pid, ok := m["pid"]; ok {
		match, err := regexp.MatchString(`^[0-9]{9}$`, pid)
		if err != nil || !match {
			// fmt.Println("######################### pid wrong: ", pid)
			return false
		}
		// fmt.Println(pid)
	} else {
		return false
	}

	// fmt.Println(len(m), m)
	return true
}

func isValidRange(str string, min int, max int, msg string) bool {
	num, err := strconv.Atoi(str)
	if err != nil || (num < min || num > max) {
		// fmt.Println("Invalid "+msg+": ", str)
		return false
	}

	return true
}

func stringInSlice(lst []string, a string) bool {
	for _, b := range lst {
		if b == a {
			return true
		}
	}
	return false
}
