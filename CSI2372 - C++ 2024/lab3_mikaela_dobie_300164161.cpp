//Mikaela Dobie 300164161

#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#include <string.h>
#include <cmath>
#include <array>
#include <map>
#include <cstdlib> 
#include <time.h>
using namespace std;

struct Node {
    int data;       
    Node* next;    
};

Node* createCircle(int n) {
    Node* head = new Node();
    head->data = 1;
    Node* temp = head;

    for (int i = 2; i <= n; ++i) {
        Node* nNode = new Node();
        nNode->data = i;
        temp->next = nNode;
        temp = nNode;
    }
    
    temp->next = head;
    return head;
}

string alpha(){
    const char* str1;
    string str01;
    const char* str2;
    string str02;

    cout << "please enter two strings to compare \n" << "string 1: \n";
    cin.ignore();
    getline(cin, str01);
    str1 = str01.c_str();
    cout << "\n" << "string 2: \n";
    cin.ignore();
    getline(cin, str02);
    str2 = str02.c_str();


    while (*str1 && *str2){
        if (*str1 < *str2) {
            return str01;
        } else if (*str1 > *str2) {
            return str02;
        }
        ++str1;
        ++str2;

    }
    return "neither, both strings are equal.";

}


int child(){
    int n;
    int m;

    cout << "How many children are there? ";
    cin >> n;
    cout <<"\n At what number does one get removed? ";
    cin >> m;
    

    Node* head = createCircle(n);
    Node* current = head;

    
    while (current->next != current) { 
        for (int count = 1; count < m; ++count) {
            current = current->next; 
        }
        
        Node* toDelete = current; 
        current->next = current->next->next;
        current = current->next; 
        delete toDelete; 
    }

    int lastChild = current->data; 
    delete current; 
    return lastChild; 


}

class Rectangle {
private:
    double length;
    double width;

public:
    // Constructor
    Rectangle(double l, double w) : length(l), width(w) {}

    // Method to calculate area
    double area() const {
        return length * width;
    }

    // Method to calculate perimeter
    double perimeter() const {
        return 2 * (length + width);
    }

    // Method to resize the rectangle
    void resize(double newLength, double newWidth) {
        length = newLength;
        width = newWidth;
    }

    // Method to compare areas of two rectangles
    bool hasLargerArea(const Rectangle& other) const {
        return this->area() > other.area();
    }

    // Method to print dimensions
    void display() const {
        cout << "Length: " << length << ", Width: " << width 
             << ", Area: " << area() << ", Perimeter: " << perimeter() << endl;
    }
};

int rectangles(){
    int l;
    int w;
    int l1;
    int w1;
    cout << "Please enter the length and width for two rectangles: \n";
    cout << "Rectangle 1 length: \n";
    cin >> l;
    cout <<"Rectangle 1 width: \n";
    cin >> w;
    Rectangle rect1(l,w);
    cout << "Rectangle 2 length: \n";
    cin >> l1;
    cout <<"Rectangle 2 width: \n";
    cin >> w1;
    Rectangle rect2(l1,w1);

    cout << "Initial Rectangles:" << endl;
    rect1.display();
    rect2.display();

    if (rect1.hasLargerArea(rect2)) {
        cout << "Rectangle 1 has a larger area than Rectangle 2." << endl;
    } else {
        cout << "Rectangle 2 has a larger area than Rectangle 1." << endl;
    }

    rect1.resize(l*3, w*3);
    rect2.resize(l1*2,w1*2);

    cout << "\nAfter resizing:" << endl;
    rect1.display();
    rect2.display();
    return 0;
}



enum Suit { HEARTS, DIAMONDS, CLUBS, SPADES };
enum Rank { TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE };

struct Card {
    Suit suit;
    Rank rank;
};

bool testPair() {
    array<Card, 5> hand;
    int suit;
    int rank;
    srand(time(0));

    for (int i = 0; i<5; i++){
        suit = rand() % 4;
        rank = rand() % 13; 
        hand[i] = Card {static_cast<Suit>(suit), static_cast<Rank>(rank)};
    }

    cout << "Ranks: TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE" << "\nSuits: HEARTS, DIAMONDS, CLUBS, SPADES";
    cout << "\n Your hand is: ";
    for (const auto& card : hand) {
        cout << "(" << card.rank << ", " << card.suit << ") ";
    }
    cout << endl;

    map<Rank, int> rankCount;

    for (const auto& card : hand) {
        rankCount[card.rank]++;
    }

    for (const auto& entry : rankCount) {
        if (entry.second == 2) {
            return true;
        }
    }

    return false;
}

int main(){
    int x;
    cout << "Choose an exercise: 1, 2, 3, or 4: " << endl;
    cin >> x;
    
    string a;
    int b;
    int c;
    bool d;
    if (x==1){
        a = alpha();
        cout << "The one that comes first in the alphabet is " << a;
    }else if(x==2){
        b = child();
        cout << "The original position of the last child remaining is: " << b;
    }else if (x==3){
        c = rectangles();
    }else if (x==4){
        d = testPair();
        if (d) {
            cout << "The hand has a pair!" << endl;
        } else {
            cout << "The hand does not have a pair." << endl;
        }

    }else{
        return 0;
    }
    return 0;
    
}