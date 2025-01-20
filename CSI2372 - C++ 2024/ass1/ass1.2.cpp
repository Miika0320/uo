// ------------------------------------------------------------------------------
// Assignment 1.2
// Written by: Mikaela Dobie 300164161
// For CSI2372 Section B
// Time needed to complete this assignment: 10 min
// List the resources used to complete this assignment: Course notes, W3 Schools
// ----------------------------------------------------------------------------- 

#include <iostream>
#include <cmath>
using namespace std;

// takes in two integers from the user then prints the square of the two added as U and the square of the first minus the second as V
int main() {
  int x;
  int y; 
  cout << "Please enter a your first integer (or enter any other character to exit): ";
  cin >> x; 
  cin.ignore();
  cout << "Please enter a your second integer (or enter any other character to exit): ";
  cin >> y;
  
  
  int u = x+y;
  int v = x-y;
  u = u*u;
  v = v*v;

  
  cout << "U= "<< u << " V= "<< v;
  return 0;
}
