//Mikaela Dobie 300164161

#include <iostream>
#include <vector>
using namespace std;

int main() {
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
