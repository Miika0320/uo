% Mikaela Dobie 300164161 Assignment 2 Prolog %

/* Facts */

pet(fido, dog, 3).
pet(spot, dog, 5).
pet(mittens, cat, 2).
pet(tweety, bird, 1).
male(fido).
male(spot).
female(mittens).

/* Rules */


pet(X,Y,Z):- pet(X,Y,Z).

species(X,Y):- findall(X, pet(X,A,B),C), length(C,N) == Y.

length([],0).
length([X|L],N) :-
length(L,NN),
N is NN+1.

age_range(X,Y,Z):- bagof(C, (pet(A,B,C), X<C<Y), D), length(D,N) == Z.