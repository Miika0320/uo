//Mikaela Dobie 300164161

#include <iostream>
#include <string>
#include <algorithm>
#include <string.h>
using namespace std;

int main() {
  string x;
  string y;
  cout << "is this a pallindrome? \n";
  getline(cin, x);
  y = x;
  reverse(x.begin(), x.end());
  if(x.compare(y) == 0){
      cout << "it is!";
      return 0;
  }
  else{
      cout << "it is not: " << x;
  }
  return 0;
}
