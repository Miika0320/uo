//Mikaela Dobie 300164161

#include <iostream>
#include <vector>
#include <string>
using namespace std;

class Student{

    int studentId;
    string name; 

    public:
        Student(int id, const string& name) : studentId(id), name(name) {}
        
        
        void print() const {
            cout << "Student ID: " << studentId << ", Name: " << name << endl;
        }
};

int createClass(int idMaker, string names[]){
    vector<Student> students;
    for (int i = 0; i < 30; i++){
        students.push_back(Student(idMaker, names[i]));
        idMaker++;
    }

    for (const Student& student : students){
        student.print();
    }
    return idMaker;
}

int main(){

    int idMaker = 1;
    string names[30] = {"Ellen Solomon", "Eve Miranda", "Clifton Booth", "Wayne Burch", "Alexandra Ball", "Corina Braun", "Winifred Martin", "Tracey Wyatt", "Amber Novak", "Sol Schwartz", "Ramon Stark", "Nita Garrison", "Sasha Stafford", "Marcelino Francis", "Adrienne Hoffman", "Ramiro Hickman", "Rita Mcmillan", "Dolores Mcgrath", "Jeri Terrell", "Kathryn Hoover", "Nick Best", "Vilma Clarke", "Benny Wilkerson", "Larry Brock", "Daron Michael", "Otto Palmer", "Mose Cuevas", "Frankie May", "Leanne Shaffer", "Holly Hughes"};
    idMaker = createClass(idMaker, names);
    return 0;
}