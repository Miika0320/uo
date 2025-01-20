//Mikaela Dobie 300164161

#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#include <string.h>
#include <cmath>
using namespace std;


int ex1() {
  vector<int> numbers;
  int x; 
  for (int j = 1; j < 6; j++){
      cout << "Type a number: ";
      cin >> x; 
      numbers.push_back(x);
  }

  for (int i = 0; i < numbers.size(); i++) {
    cout << numbers[numbers.size()-(i+1)] << "\n";
  }
  return 0;
}



int ex2() {
  string j;
  string y;
  cout << "is this a pallindrome? \n";
  cin.ignore();
  getline(cin, j);

  y = j;

  reverse(j.begin(), j.end());
  if(j.compare(y) == 0){
      cout << "it is!";
      return 0;
  }
  else{
      cout << "it is not: " << j;
  }
  return 0;
}

int ex3() {
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

int ex4() {
  float x; 
  cout << "Type a number: ";
  cin >> x; 
  float sqroot = sqrt(x);
  float square = x*x;
  
  cout << "Square root: "<< sqroot << " Square: "<< square;
  return 0;
}

int main(){
    int x;
    cout << "Choose an exercise: 1, 2, 3, or 4: ";
    cin >> x;
    
    int a;
    int b;
    int c;
    int d;
    if (x==1){
        a = ex1();
    }else if(x==2){
        b = ex2();
    }else if (x==3){
        c = ex3();
    }else if (x==4){
        d = ex4();
    }else{
        return 0;
    }
    return 0;
    
    return 0;
}