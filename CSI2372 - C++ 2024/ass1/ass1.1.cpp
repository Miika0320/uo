// ------------------------------------------------------------------------------
// Assignment 1.1
// Written by: Mikaela Dobie 300164161
// For CSI2372 Section B
// Time needed to complete this assignment: 10 mins
// List the resources used to complete this assignment: Course notes, W3 Schools
// ----------------------------------------------------------------------------- 

#include <iostream>
using namespace std;

//reads a number from user
int read_number()
    {
        int n;
        /* Read a number */
        cout << "Enter a number : \n";
        cin >> n;
        return n;   
    }

//takes in a number using read_number function and prints the accumulated sum from 1 to the inputed number
int main()
    {
        int i, n;
        long total = 0;
        n = read_number();
        for (i = 1; i <= n; i++)
        {
            total += i; /* accumulation in the variable « total » */
            cout << "i = " << i << " total = " << total << "\n";
        }
    cout << "\n";
    cout << "*** Final Total = " << total << " ***";
} /* end of main() */
