//Mikaela Dobie 300164161
#include <iostream>
#include <vector>
using namespace std;

class Course {
private:
    int code;   // Course number (code)
    int hours;  // Number of course hours

public:
    // Constructor
    Course(int courseCode, int courseHours) : code(courseCode), hours(courseHours) {}

    // Accessor method to get the course number (code)
    int getNum() const {
        return code;
    }

    // Accessor method to get the number of course hours
    int getHours() const {
        return hours;
    }
};

class Student {
private:
    int regID;                   // Student registration ID
    int maxCourses;              // Maximum number of courses a student can take
    int numCourses;              // Number of courses taken by the student
    Course** courses;            // Pointer to an array of pointers to Course objects
    int* grades;                 // Pointer to an array of grades (integers)

public:
    // Constructor to initialize the student with their ID and the maximum number of courses
    Student(int studentID, int maxCoursesAllowed) : regID(studentID), maxCourses(maxCoursesAllowed), numCourses(0) {
        // Allocate memory for the course pointers and grades arrays
        courses = new Course*[maxCourses];
        grades = new int[maxCourses];
    }

    // Destructor to release dynamically allocated memory
    ~Student() {
        delete[] courses;
        delete[] grades;
    }

    // Method to add a course and the corresponding grade
    void addCourse(Course* course, int grade) {
        if (numCourses < maxCourses) {
            courses[numCourses] = course;  // Store the course pointer
            grades[numCourses] = grade;    // Store the grade
            numCourses++;                  // Increment the number of courses
        } else {
            cout << "Cannot add more courses. Maximum limit reached." << endl;
        }
    }

    // Method to calculate the student's average grade
    double average() const {
        if (numCourses == 0) return 0.0;  // Return 0 if no courses were taken

        int totalGrades = 0;
        for (int i = 0; i < numCourses; i++) {
            totalGrades += grades[i];
        }
        return static_cast<double>(totalGrades) / numCourses;
    }

    // Method to calculate the total number of hours for the courses taken
    int totalHours() const {
        int totalHours = 0;
        for (int i = 0; i < numCourses; i++) {
            totalHours += courses[i]->getHours();
        }
        return totalHours;
    }

    // Method to display student details and their courses with grades
    void displayInfo() const {
        cout << "Student ID: " << regID << endl;
        cout << "Number of courses taken: " << numCourses << endl;

        for (int i = 0; i < numCourses; i++) {
            cout << "Course Code: " << courses[i]->getNum() << ", Grade: " << grades[i] << endl;
        }

        cout << "Average Grade: " << average() << endl;
        cout << "Total Course Hours: " << totalHours() << endl;
    }
};

class SetInt {
public:
    // Default constructor creates an empty set
    SetInt() : elem(nullptr), size(0) {}

    // Constructor that receives an array of integers and its size
    SetInt(int arr[], int arrSize) : elem(nullptr), size(0) {
        for (int i = 0; i < arrSize; i++) {
            add(arr[i]);  // Add each element, ensuring no duplicates
        }
    }

    // Destructor to release allocated memory
    ~SetInt() {
        delete[] elem;
    }

    // Copy constructor
    SetInt(const SetInt& other) : size(other.size) {
        if (other.elem == nullptr) {
            elem = nullptr;
        } else {
            elem = new int[size];
            for (int i = 0; i < size; i++) {
                elem[i] = other.elem[i];
            }
        }
    }

    // Method to add an integer to the set (only if it doesn't already belong)
    void add(int n) {
        if (!contains(n)) {
            // Create a new array with one additional element
            int* newElem = new int[size + 1];
            for (int i = 0; i < size; i++) {
                newElem[i] = elem[i];
            }
            newElem[size] = n;  // Add the new element
            delete[] elem;      // Release the old array
            elem = newElem;
            size++;             // Update size
        }
    }

    // Method to remove an integer from the set
    void remove(int n) {
        if (contains(n)) {
            // Create a new array with one less element
            int* newElem = new int[size - 1];
            int newIndex = 0;
            for (int i = 0; i < size; i++) {
                if (elem[i] != n) {
                    newElem[newIndex++] = elem[i];
                }
            }
            delete[] elem;  // Release the old array
            elem = newElem;
            size--;         // Update size
        }
    }

    // Method to check if an integer belongs to the set
    bool contains(int n) const {
        for (int i = 0; i < size; i++) {
            if (elem[i] == n) {
                return true;
            }
        }
        return false;
    }

    // Method to get the number of elements in the set
    int nbElem() const {
        return size;
    }

    // Method to return a dynamically allocated array containing the elements of the set
    int* tabElem() const {
        if (size == 0) {
            return nullptr;  // Return NULL if the set is empty
        }
        int* arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = elem[i];
        }
        return arr;  // Caller is responsible for freeing this memory
    }

private:
    int* elem;  // Array of integers representing the set
    int size;   // Number of elements in the set

    // Auxiliary method to check if an integer belongs to the set at a specific position
    bool containsAux(int n, int& pos) const {
        for (int i = 0; i < size; i++) {
            if (elem[i] == n) {
                pos = i;
                return true;
            }
        }
        return false;
    }
};

int trapWater(vector<int>& height) {
    int n = height.size();
    if (n == 0) return 0;  // No water can be trapped if there are no bars

    int left = 0, right = n - 1;
    int left_max = 0, right_max = 0;
    int totalWater = 0;

    while (left < right) {
        if (height[left] < height[right]) {
            if (height[left] >= left_max) {
                left_max = height[left];  // Update left_max
            } else {
                totalWater += left_max - height[left];  // Water trapped at left
            }
            left++;  // Move left pointer to the right
        } else {
            if (height[right] >= right_max) {
                right_max = height[right];  // Update right_max
            } else {
                totalWater += right_max - height[right];  // Water trapped at right
            }
            right--;  // Move right pointer to the left
        }
    }

    return totalWater;
}
int uniquePaths(int m, int n) {
    // Create a 2D DP array initialized to 0
    vector<vector<int>> dp(m, vector<int>(n, 0));
    
    // Base case: There's only one way to reach any cell in the first row or first column
    for (int i = 0; i < m; i++) {
        dp[i][0] = 1;  // Only one way to move down in the first column
    }
    for (int j = 0; j < n; j++) {
        dp[0][j] = 1;  // Only one way to move right in the first row
    }

    // Fill the DP table using the recurrence relation
    for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
            dp[i][j] = dp[i-1][j] + dp[i][j-1];  // Sum of top and left cells
        }
    }

    // The bottom-right corner contains the number of unique paths
    return dp[m-1][n-1];
}


int main() {

    int x;
    cout << "Choose an exercise: 1, 2, 3, or 4: " << endl;
    cin >> x;
    
    
    if (x==1){
        // Create a few Course objects
        Course course1(101, 3);
        Course course2(102, 4);

        cout << "Course Code: " << course1.getNum() << endl;
        cout << "Course Hours: " << course1.getHours() << endl;

        // Create a Student object
        Student student1(1001, 5);

        // Add courses and grades for the student
        student1.addCourse(&course1, 85);
        student1.addCourse(&course2, 90);

        // Display student information
        student1.displayInfo();
        
    }else if(x==2){
        int arr[] = {1, 2, 3, 4};
        SetInt set1(arr, 4);  // Create a set with initial elements

        cout << "Number of elements: " << set1.nbElem() << endl;

        // Add an element
        set1.add(5);
        cout << "Number of elements after adding 5: " << set1.nbElem() << endl;

        // Remove an element
        set1.remove(2);
        cout << "Number of elements after removing 2: " << set1.nbElem() << endl;

        // Check if an element exists
        if (set1.contains(3)) {
            cout << "Set contains 3" << endl;
        } else {
            cout << "Set does not contain 3" << endl;
        }

        // Get the elements of the set
        int* elements = set1.tabElem();
        if (elements != nullptr) {
            cout << "Set elements: ";
            for (int i = 0; i < set1.nbElem(); i++) {
                cout << elements[i] << " ";
            }
            cout << endl;
            delete[] elements;  // Free the dynamically allocated array
        }
    }else if (x==3){
        vector<int> elevationMap = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        cout << "Elevation Map: {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}\n";
        cout << "Total water trapped: " << trapWater(elevationMap) << " units" << endl;
    }else if (x==4){
        int m = 3, n = 8;  // Example: a 3x8 grid
        cout << "Grid size = " << m << " x " << n << "\n";
        cout << "Number of unique paths: " << uniquePaths(m, n) << endl;

    }else{
        return 0;
    }
    return 0;
}
