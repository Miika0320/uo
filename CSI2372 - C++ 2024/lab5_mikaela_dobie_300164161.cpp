// Mikaela Dobie 300164161
#include <iostream>
#include <cmath>      
#include <iomanip>    
#include <stdexcept> 
#include <string>
#include <vector>
#include <algorithm> 
#include <memory>
#include <typeinfo>
using namespace std; 

class MathLibrary {
public:
    static double squareRoot(double number) {
        if (number < 0) {
            throw invalid_argument("Error: You cannot square root a negative number.");
        }
        return sqrt(number);
    }

    static double logarithm(double number) {
        if (number <= 0) {
            throw invalid_argument("Error: You cannot take the log of a negative number or 0.");
        }
        return log(number);
    }
};

class User {
private:
    string username;
    int userID;
    string accessLevel;
    static int userCount;

public:
    User() : username(""), userID(0), accessLevel("regular") {
        ++userCount;
    }
    
    User(const string& name, int id, const string& level) 
        : username(name), userID(id), accessLevel(level) {
        ++userCount;
    }

    ~User() {
        --userCount;
    }

    static int getUserCount() {
        return userCount;
    }

    friend bool compareAccessLevel(const User& user1, const User& user2);
};

int User::userCount = 0;

bool compareAccessLevel(const User& user1, const User& user2) {
    if (user1.accessLevel == user2.accessLevel) {
        cout << user1.username << " and " << user2.username 
             << " have the same access level.\n";
        return true;
    } else {
        cout << user1.username << " and " << user2.username 
             << " have different access levels.\n";
        return false;
    }
}

User getUserInput() {
    string name, access;
    int id;

    cout << "Enter username: ";
    cin >> name;
    cout << "Enter user ID: ";
    cin >> id;
    cout << "Enter access level (admin/regular): ";
    cin >> access;

    return User(name, id, access);
}

class SportsTeam {
private:
    string teamName;
    int wins, losses, draws;
    static int teamCount;

public:
    SportsTeam(const string& name = "", int w = 0, int l = 0, int d = 0)
        : teamName(name), wins(w), losses(l), draws(d) {
        ++teamCount;
    }

    static int getTeamCount() {
        return teamCount;
    }

    double getWinPercentage() const {
        int totalGames = wins + losses + draws;
        return totalGames == 0 ? 0.0 : (static_cast<double>(wins) / totalGames) * 100;
    }

    void displayTeamInfo() const {
        cout << "Team: " << teamName 
             << " | Wins: " << wins 
             << " | Losses: " << losses 
             << " | Draws: " << draws 
             << " | Win%: " << getWinPercentage() << "%\n";
    }

    friend bool operator<(const SportsTeam& t1, const SportsTeam& t2);
    friend bool operator>(const SportsTeam& t1, const SportsTeam& t2);
};

int SportsTeam::teamCount = 0;

bool operator<(const SportsTeam& t1, const SportsTeam& t2) {
    return t1.getWinPercentage() < t2.getWinPercentage();
}

bool operator>(const SportsTeam& t1, const SportsTeam& t2) {
    return t1.getWinPercentage() > t2.getWinPercentage();
}

SportsTeam getTeamInput(int index) {
    string name;
    int w, l, d;

    cout << "Enter details for Team " << index + 1 << ":\n";
    cout << "Team Name: ";
    cin >> name;
    cout << "Wins: ";
    cin >> w;
    cout << "Losses: ";
    cin >> l;
    cout << "Draws: ";
    cin >> d;

    return SportsTeam(name, w, l, d);
}

class Shape {
public:
    virtual void draw() const = 0;
    virtual double area() const = 0;
    virtual ~Shape() = default;
};

class Circle : public Shape {
private:
    double radius;

public:
    Circle(double r) : radius(r) {}
    void draw() const override {
        cout << "Drawing a Circle with radius: " << radius << "\n";
    }
    double area() const override {
        return 3.14159 * radius * radius;
    }
    void setRadius(double r) {
        radius = r;
    }
};

class Rectangle : public Shape {
private:
    double width, height;

public:
    Rectangle(double w, double h) : width(w), height(h) {}
    void draw() const override {
        cout << "Drawing a Rectangle with width: " << width 
             << " and height: " << height << "\n";
    }
    double area() const override {
        return width * height;
    }
};

void modifyRadius(shared_ptr<Shape> shape, double newRadius) {
    shared_ptr<Circle> circle = dynamic_pointer_cast<Circle>(shape);
    if (circle) {
        circle->setRadius(newRadius);
        cout << "Circle radius changed to: " << newRadius << "\n";
    } else {
        cout << "The shape is not a Circle. Radius change not applied.\n";
    }
}

int main() {
    int choice;
    cout << "Choose an exercise: 1, 2, 3, or 4: ";
    cin >> choice;
    int a,b;

    if (choice == 1) {
        double num;
        cout << "Enter a number: ";
        cin >> num;
        try {
            cout << fixed << setprecision(2);
            cout << "Square root: " << MathLibrary::squareRoot(num) << "\n";
            cout << "Logarithm: " << MathLibrary::logarithm(num) << "\n";
        } catch (const invalid_argument& e) {
            cerr << e.what() << "\n";
        }
    } else if (choice == 2) {
        int userCount;
        cout << "How many users? ";
        cin >> userCount;
        vector<User> users(userCount);
        for (int i = 0; i < userCount; ++i) {
            users[i] = getUserInput();
        }
        cout << "Please choose which two you would like to compare (1-" << User::getUserCount() << "): " << "\nFirst user: ";
        cin >> a;
        cout << "Second user: ";
        cin >> b;
        if(a >= 1 && a < userCount+1 && b >= 1 && b < userCount+1)
            compareAccessLevel(users[a-1], users[b-1]);
        else{
            cout << "Invalid choice, goodbye \n";
        }
        cout << "Total users: " << User::getUserCount() << "\n";
        
    } else if (choice == 3) {
        int teamCount;
        cout << "How many teams? ";
        cin >> teamCount;
        vector<SportsTeam> league(teamCount);
        for (int i = 0; i < teamCount; ++i) {
            league[i] = getTeamInput(i);
        }
        sort(league.begin(), league.end(), greater<SportsTeam>());
        for (const auto& team : league) {
            team.displayTeamInfo();
        }
    } else if (choice == 4) {
        shared_ptr<Shape> shape;
        int shapeChoice;
        cout << "1. Circle\n2. Rectangle\nEnter choice: ";
        cin >> shapeChoice;
        if (shapeChoice == 1) {
            double r;
            cout << "Enter radius: ";
            cin >> r;
            shape = make_shared<Circle>(r);
        } else {
            double w, h;
            cout << "Enter width and height: ";
            cin >> w >> h;
            shape = make_shared<Rectangle>(w, h);
        }
        shape->draw();
        cout << "Area: " << shape->area() << "\n";
        if (choice == 1){
            cout << "Enter a new radius for the circle: ";
            cin >> a;
            modifyRadius(shape, a);
            cout << "Updated area of the circle: " << shape->area() << "\n";
        }
    }

    return 0;
}
