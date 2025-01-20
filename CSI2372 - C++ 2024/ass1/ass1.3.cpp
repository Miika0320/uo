// ------------------------------------------------------------------------------
// Assignment 1.3
// Written by: Mikaela Dobie 300164161
// For CSI2372 Section B
// Time needed to complete this assignment: 15 min
// List the resources used to complete this assignment: Course notes, W3 Schools
// ----------------------------------------------------------------------------- 


#include <iostream>
using namespace std;


// takes in any number of positive integers and prints the maximum
int main() {
  int x; 
  int max = 0;
  while(x>=0){
      cout << "Type a positive integer (negative to stop, anything else to exit): ";
      cin >> x; 
      if(cin.fail()){
          break;
      }
      if (max<x){
          max = x;
      }
  }

  cout << "The max of these numbers is:" << max;
  
  return 0;
}
