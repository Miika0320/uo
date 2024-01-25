// Mikaela Dobie 300164161
package main

import (
	"bufio"
	"fmt"
	"log"
	"math"
	"math/rand"
	"os"
	"strconv"
	"strings"
)

func check(e error) {
	if e != nil {
		panic(e)
	}
}

type Point3D struct {
	X float64
	Y float64
	Z float64
}

type Plane3D struct {
	A float64
	B float64
	C float64
	D float64
}

type Plane3DwSupport struct {
	plane       Plane3D
	SupportSize int
}

// reads an XYZ file and returns a slice of Point3D
func ReadXYZ(filename string) []Point3D {
	file, err := os.Open(filename)
	var list []Point3D

	check(err)

	defer file.Close()

	scanner := bufio.NewScanner(file)

	for scanner.Scan() {
		s := strings.Fields(scanner.Text())
		a, err := strconv.ParseFloat(s[0], 64)
		b, err1 := strconv.ParseFloat(s[1], 64)
		c, err2 := strconv.ParseFloat(s[2], 64)
		point := Point3D{a, b, c}
		list = append(list, point)

		check(err)
		check(err1)
		check(err2)

	}

	if err := scanner.Err(); err != nil {
		log.Fatal(err)
	}
	return list
}

// saves a slice of Point3D into an XYZ file
func SaveXYZ(filename string, points []Point3D) {
	f, err := os.Create(filename)

	if err != nil {
		log.Fatal(err)
	}

	defer f.Close()
	for _, value := range points {
		fmt.Fprintln(f, value) // print values to f, one per line
	}
}

// computes the distance between points p1 and p2
func (p1 *Point3D) GetDistance(p2 *Point3D) float64 {
	d := float64(math.Sqrt(math.Pow((p1.Y-p2.Y), 2) + math.Pow((p1.X-p2.X), 2) + math.Pow((p1.Z-p2.Z), 2)))
	return d
}

// computes the plane defined by a set of 3 points
func GetPlane(points []Point3D) Plane3D {

	var i, j, k float64
	line1 := Point3D{points[0].X - points[1].X, points[0].Y - points[1].Y, points[0].Z - points[1].Z}
	line2 := Point3D{points[2].X - points[1].X, points[2].Y - points[1].Y, points[2].Z - points[1].Z}

	i = (line1.Y * line2.Z) - (line1.Z * line2.Y)
	j = -(line1.X * line2.Z) - (line1.Z * line2.X)
	k = (line1.X * line2.Y) - (line1.Y * line2.X)

	plane := Plane3D{i, j, k, i*points[0].X + j*points[1].Y + k*points[2].Z}
	return plane
}

// computes the number of required RANSAC iterations
func GetNumberOfIterations(confidence float64, percentageOfPointsOnPlane float64) int {
	var num float64 = math.Log(1-confidence) / math.Log(1-math.Pow(percentageOfPointsOnPlane, 3))
	inter := int(num)
	return inter
}

// computes the support of a plane in a set of points
func GetSupport(plane Plane3D, points []Point3D, eps float64) Plane3DwSupport {
	var support []Point3D = GetSupportingPoints(plane, points, eps)
	refund := Plane3DwSupport{plane, len(support)}
	return refund
}

// extracts the points that supports the given plane
// and returns them as a slice of points
func GetSupportingPoints(plane Plane3D, points []Point3D, eps float64) []Point3D {
	var support []Point3D
	var dist float64
	for i := 0; i < len(points); i++ {
		dist = plane.A*points[i].X + plane.B*points[i].Y + plane.C*points[i].Z + plane.D
		if dist == 0 {
			support = append(support, points[i])
		}

	}

	return support

}

// creates a new slice of points in which all points
// belonging to the plane have been removed
func RemovePlane(plane Plane3D, points []Point3D, eps float64) []Point3D {
	var p []Point3D
	var dist float64
	for i := 0; i < len(points); i++ {
		dist = plane.A*points[i].X + plane.B*points[i].Y + plane.C*points[i].Z + plane.D
		if dist != 0 {
			p = append(p, points[i])
		}

	}

	return p

}
func Rand(points []Point3D) Point3D {
	len := len(points)
	a := int(rand.Intn(len))

	return points[a]
}

func main() {
	var filename string = os.Args[0]
	bestSupport := Plane3DwSupport{Plane3D{0, 0, 0, 0}, 0}
	var points []Point3D = ReadXYZ(filename)
	eps, err := strconv.ParseFloat(os.Args[3], 64)

	confidence, err1 := strconv.ParseFloat(os.Args[1], 64)
	percent, err2 := strconv.ParseFloat(os.Args[2], 64)
	check(err)
	check(err1)
	check(err2)

	var iterations int = GetNumberOfIterations(confidence, percent)
	bestPoints := []Point3D{}

	for i := 0; i < iterations; i++ {
		ppo := []Point3D{Rand(points), Rand(points), Rand(points)}
		var plane Plane3D = GetPlane(ppo)
		var tempSupport Plane3DwSupport = GetSupport(plane, points, eps)

		if tempSupport.SupportSize > bestSupport.SupportSize {
			bestSupport = tempSupport
			bestPoints = GetSupportingPoints(plane, points, eps)
		}

	}
	filename = filename[0 : len(filename)-4]
	SaveXYZ(filename+"_p.txt", bestPoints)
	var without []Point3D = RemovePlane(bestSupport.plane, points, eps)
	SaveXYZ(filename+"_p0.txt", without)

}
