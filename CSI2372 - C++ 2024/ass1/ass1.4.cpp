// ------------------------------------------------------------------------------
// Assignment 1.4
// Written by: Mikaela Dobie 300164161
// For CSI2372 Section B
// Time needed to complete this assignment: 5 min
// List the resources used to complete this assignment: Course notes, W3 Schools
// ----------------------------------------------------------------------------- 

#include <iostream>
using namespace std;


//takes in any number of positive real numbers and prints the average of the set
int main() {
  int count;
  float x; 
  float total = 0;
  while(x>=0){
      cout << "Type a positive number (negative to stop, anything else to exit): ";
      cin >> x; 
      if(cin.fail() || x<=0){
          break;
      }
      total+=x;
      count+=1;
      }

  cout << "The average of these numbers is: " << total/count;
  
  return 0;
}