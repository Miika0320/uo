% Mikaela Dobie 300164161 Part 4 %

/* Facts */

%Pattern Match to access element in list%
first([E,_,_|_], E).
second([_,E,_|_], E).
third([_,_,E|_], E).
fourth([_,_,_,E|_], E).


/* Rules */

% Reads file in and creates list of points%
read_xyz_file(File, Points) :-
 open(File, read, Stream),
read_xyz_points(Stream,Points),
 close(Stream).
 
read_xyz_points(Stream, []) :-
 at_end_of_stream(Stream).
 
read_xyz_points(Stream, [Point|Points]) :-
 \+ at_end_of_stream(Stream),
read_line_to_string(Stream,L), split_string(L, "\t", "\s\t\n",
XYZ), convert_to_float(XYZ,Point),
 read_xyz_points(Stream, Points).
 
%converts each coordinate into a float%
convert_to_float([],[]).

convert_to_float([H|T],[HH|TT]) :-
 atom_number(H, HH),
 convert_to_float(T,TT). 

%Takes a list of three points and checks if they are in the points list%
random3points(Points, Point3) :- member(Points(first(first(Point3)))),
member(Points(first(first(Point3)))),
member(Points(second(first(Point3)))),
member(Points(third(first(Point3)))),
member(Points(first(second(Point3)))),
member(Points(second(second(Point3)))),
member(Points(third(second(Point3)))),
member(Points(first(third(Point3)))),
member(Points(second(third(Point3)))),
member(Points(third(third(Point3)))).


%check if H is a memeber of list%
member([H|T], H).
member([_|T], H) :- member(T, H).
member([], _) :- !, fail.

%checks if the three points are in the same plane%
plane(Point3 , Plane) :-
first(first(Point3))*first(Plane)+first(second(Point3))*second(Plane)+first(third(Point3))*third(Plane) == fourth(Plane),
second(first(Point3))*first(Plane)+second(second(Point3))*second(Plane)+second(third(Point3))*third(Plane) == fourth(Plane),
third(first(Point3))*first(Plane)+third(second(Point3))*second(Plane)+third(third(Point3))*third(Plane) == fourth(Plane).

%checks how many points are within eps distance of the plane%
support(Plane, Points, Eps, N) :-
inPlane(Points.X,Plane),
support(Plane,Points.Y, eps, N-1).

support(Plane, Points, Eps, N) :-
inPlane(Points.X,Plane)==false,
support(Plane,Points.Y, eps, N).

support(Plane, Points, Eps, N) :-
Points==[],
N==0.

inPlane(point,Plane):-
(first(Point3))*first(Plane)+second(Point3))*second(Plane)+third(Point3))*third(Plane) == fourth(Plane).

%checks if N = log(1-confidence)/log(1-percentage^3)%
ransac-number-of-iterations(Confidence, Percentage, N) :-
N == ceiling(log(1-Confidence)/(log(1-Percentage**3).

/* TEST */

?-ransac-number-of-iterations(.99, .45, N).

?-plane([[0.12,0.22,0.33][0.21,0.32,0.33][0.14,0.22,0.35],[0,2,4,6]).

?-plane([[1,2,-2][3,-2,1][5,1,-4],[11,16,14,-15]).