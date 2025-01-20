//Mikaela Dobie 300164161

#include <iostream>
#include <vector>
using namespace std;

int main() {
  int x; 
  cout << "Type a number: ";
  cin >> x; 
  int max = x;
  int min = x;
  
  for (int j = 1; j < 5; j++){
      cout << "Type a number: ";
      cin >> x; 
      if(max<x){
          max = x;
      }
      if (min>x){
          min = x;
      }

  }
  cout << "max: "<< max << " min: "<< min;
  return 0;
}
