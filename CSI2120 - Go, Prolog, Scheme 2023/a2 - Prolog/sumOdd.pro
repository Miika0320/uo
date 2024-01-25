% Mikaela Dobie 300164161 Assignment 2 Prolog %

/* Rules */

sum_odd_numbers([],S,S).
sum_odd_numbers(L,S):- sum_odd_numbers(L,0,S).

sum_odd_numbers([X|Y],Z,S):- isOdd(X), 
	ZZ is Z+X, sum_odd_numbers(L,ZZ,S).

isOdd(X):= X%2==0.

