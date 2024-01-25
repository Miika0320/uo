% Mikaela Dobie 300164161 Assignment 2 Prolog %

/* Facts */

parent(john, mary).
parent(john, tom).
parent(mary, ann).
parent(mary, fred).
parent(tom, liz).


male(john).
male(tom).
male(fred).
female(mary).
female(ann).
female(liz).

/* Rules */

father(X,Y):- male(X),
    parent(X,Y).
 
mother(X,Y):- female(X),
    parent(X,Y).
 
parent(X,Y):- parent(X,Y).

grandfather(X,Y):- male(X),
    parent(X,Z),
    parent(Z,Y).
 
grandmother(X,Y):- female(X),
    parent(X,Z),
    parent(Z,Y).
	
grandparent(X,Y):- parent(X,Z),
    parent(Z,Y).
	
sibling(X,Y):- %(X,Y or Y,X)%
    father(F, Y), father(F,X),X \= Y.
 
sibling(X,Y):- %(X,Y or Y,X)% 
    mother(M, Y), mother(M,X),X \= Y.

ancestor(X,Y):- parent(X,Y).
ancestor(X,Y):- parent(X,Z),
    ancestor(Z,Y).