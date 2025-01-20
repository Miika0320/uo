// ------------------------------------------------------------------------------
// Assignment 1.5
// Written by: Mikaela Dobie 300164161
// For CSI2372 Section B
// Time needed to complete this assignment: 15 min
// List the resources used to complete this assignment: Course notes, W3 Schools
// ----------------------------------------------------------------------------- 

#include <iostream>
#include <ctype.h> 
using namespace std;

//converts a char pointer to a string per character
long conversion(char *s) {
    long number = 0;
    int sign = 1;  
   

    while (*s == ' ') {
        s++;
    }
    
    
    if (*s == '-') {
        sign = -1;
        s++;
    } else if (*s == '+') {
        s++;
    }
    
    
    while (*s) {
        if (isdigit(*s)) {
            number = number * 10 + (*s - '0');
        } else if (*s == ' '){
            
        } else {
            break;
        }
        s++;
    }
    
    return sign * number;
}

//takes in string, converts to long by using the conversion() function created above
int main() {
    char str[80];  
    
    cout << "Enter a string to convert into a number: ";
    cin.getline(str, 80); 
    
    long num = conversion(str);  
    
    cout << "The converted number is: " << num;
    
    return 0;
}
