//Mikaela Dobie 300164161

#include <iostream>
#include <fstream>
#include <string>
#include <ctime>
#include <string>
#include <vector>
#include <sstream>

using namespace std;

class Date {
private:
    int day, month, year;

    // Helper function to check if a year is a leap year
    bool isLeapYear(int y) const {
        return (y % 4 == 0 && y % 100 != 0) || (y % 400 == 0);
    }

    // Helper function to get the number of days in a month
    int daysInMonth(int m, int y) const {
        if (m == 2) return isLeapYear(y) ? 29 : 28;
        else if (m == 4 || m == 6 || m == 9 || m == 11) return 30;
        else return 31;
    }

    // Helper function to adjust date forward by a certain number of days
    void addDays(int days) {
        day += days;
        while (day > daysInMonth(month, year)) {
            day -= daysInMonth(month, year);
            month++;
            if (month > 12) {
                month = 1;
                year++;
            }
        }
    }

    // Helper function to adjust date backward by a certain number of days
    void subtractDays(int days) {
        day -= days;
        while (day <= 0) {
            month--;
            if (month < 1) {
                month = 12;
                year--;
            }
            day += daysInMonth(month, year);
        }
    }

public:
    Date(int d, int m, int y) : day(d), month(m), year(y) {}

    friend ostream& operator<<(ostream& os, const Date& date) {
        os << date.day << "/" << date.month << "/" << date.year;
        return os;
    }
    // Overload operator+ to add days
    Date operator+(int days) const {
        Date result = *this;
        result.addDays(days);
        return result;
    }

    // Overload operator- to subtract days
    Date operator-(int days) const {
        Date result = *this;
        result.subtractDays(days);
        return result;
    }

    // Overload comparison operators
    bool operator==(const Date &other) const {
        return day == other.day && month == other.month && year == other.year;
    }

    bool operator<(const Date &other) const {
        if (year < other.year) return true;
        if (year == other.year && month < other.month) return true;
        return year == other.year && month == other.month && day < other.day;
    }

    bool operator>(const Date &other) const {
        return other < *this;
    }

    // Display the date (for testing purposes)
    void display() const {
        cout << day << "/" << month << "/" << year << endl;
    }
};

class Logger {
private:
    ofstream logFile;

    // Helper function to get the current timestamp
    string getCurrentTimestamp() const {
        time_t now = time(nullptr);
        char buffer[20];
        strftime(buffer, sizeof(buffer), "%Y-%m-%d %H:%M:%S", localtime(&now));
        return string(buffer);
    }

public:
    // Constructor to open the log file
    Logger(const string& filename) {
        openLogFile(filename);
    }

    // Destructor to close the log file if it's open
    ~Logger() {
        if (logFile.is_open()) {
            logFile.close();
        }
    }

    // Open the log file
    void openLogFile(const string& filename) {
        logFile.open(filename, ios::app); // Append mode
        if (!logFile) {
            cerr << "Error opening log file: " << filename << endl;
        }
    }

    // Close the log file
    void closeLogFile() {
        if (logFile.is_open()) {
            logFile.close();
        }
    }

    // Overload << operator for logging messages
    Logger& operator<<(const string& message) {
        if (logFile.is_open()) {
            logFile << getCurrentTimestamp() << " - " << message << endl;
        } else {
            cerr << "Log file is not open." << endl;
        }
        return *this;
    }

    // Helper methods to log info, warning, and error messages
    void logInfo(const string& message) {
        *this << "[INFO] " + message;
    }

    void logWarning(const string& message) {
        *this << "[WARNING] " + message;
    }

    void logError(const string& message) {
        *this << "[ERROR] " + message;
    }
};

class Employee {
public:
    int id;
    string name;
    string department;

    // Constructor to initialize an employee
    Employee(int empId, const string& empName, const string& empDept)
        : id(empId), name(empName), department(empDept) {}

    // Convert employee data to a string for file output
    string toString() const {
        ostringstream oss;
        oss << id << "," << name << "," << department;
        return oss.str();
    }

    // Load employee data from a comma-separated string
    static Employee fromString(const string& line) {
        istringstream iss(line);
        string idStr, name, department;

        getline(iss, idStr, ',');
        getline(iss, name, ',');
        getline(iss, department, ',');

        return Employee(stoi(idStr), name, department);
    }
};

class EmployeeDatabase {
private:
    vector<Employee> employees;
    string filename;

public:
    EmployeeDatabase(const string& file) : filename(file) {
        loadFromFile();
    }

    // Load employee records from a file
    void loadFromFile() {
        ifstream inFile(filename);
        string line;
        employees.clear();

        while (getline(inFile, line)) {
            employees.push_back(Employee::fromString(line));
        }
        inFile.close();
    }

    // Save employee records to a file
    void saveToFile() const {
        ofstream outFile(filename);
        for (const auto& emp : employees) {
            outFile << emp.toString() << endl;
        }
        outFile.close();
    }

    // Search for an employee by ID
    Employee* searchById(int id) {
        for (auto& emp : employees) {
            if (emp.id == id) {
                return &emp;
            }
        }
        return nullptr;
    }

    // Update an employee's details
    bool updateEmployee(int id, const string& newName, const string& newDept) {
        Employee* emp = searchById(id);
        if (emp) {
            emp->name = newName;
            emp->department = newDept;
            return true;
        }
        return false;
    }

    // Display all employees (for demonstration purposes)
    void displayAll() const {
        for (const auto& emp : employees) {
            cout << "ID: " << emp.id << ", Name: " << emp.name
                      << ", Department: " << emp.department << endl;
        }
    }
};


class Complex {
private:
    double real;
    double imag;

public:
    // Constructor to initialize a complex number
    Complex(double r = 0, double i = 0) : real(r), imag(i) {}

    // Overload + operator for complex addition
    Complex operator+(const Complex& other) const {
        return Complex(real + other.real, imag + other.imag);
    }

    // Overload - operator for complex subtraction
    Complex operator-(const Complex& other) const {
        return Complex(real - other.real, imag - other.imag);
    }

    // Overload * operator for complex multiplication
    Complex operator*(const Complex& other) const {
        return Complex(real * other.real - imag * other.imag,
                       real * other.imag + imag * other.real);
    }

    // Overload / operator for complex division
    Complex operator/(const Complex& other) const {
        double denominator = other.real * other.real + other.imag * other.imag;
        return Complex((real * other.real + imag * other.imag) / denominator,
                       (imag * other.real - real * other.imag) / denominator);
    }

    // Overload << operator for output
    friend ostream& operator<<(ostream& os, const Complex& c) {
        os << c.real;
        if (c.imag >= 0) os << " + " << c.imag << "i";
        else os << " - " << -c.imag << "i";
        return os;
    }

    // Overload >> operator for input
    friend istream& operator>>(istream& is, Complex& c) {
        cout << "Enter real part: ";
        is >> c.real;
        cout << "Enter imaginary part: ";
        is >> c.imag;
        return is;
    }
};

int main() {
    int choice;
    cout << "Choose an exercise: 1, 2, 3, or 4: ";
    cin >> choice;
    int a,b,c;

    if (choice == 1) {
        
        cout << "Please enter a date (integers):" << endl;
        cout << "Day: ";
        cin >> a;
        cout << "\nMonth: ";
        cin >> b;
        cout << "\nYear: ";
        cin >> c;
        cout << endl;

        Date dat0(a, b, c);

        cout << "Please enter a date (integers):" << endl;
        cout << "Day: ";
        cin >> a;
        cout << "\nMonth: ";
        cin >> b;
        cout << "\nYear: ";
        cin >> c;
        cout << endl;

        Date dat1(a, b, c);

        cout << "Please enter a date (integers):" << endl;
        cout << "Day: ";
        cin >> a;
        cout << "\nMonth: ";
        cin >> b;
        cout << "\nYear: ";
        cin >> c;
        cout << endl;

        Date dat2(a, b, c);
        cout << "Date 0: " << dat0 << endl;
        cout << "Date 1: " << dat1 << endl;
        cout << "Date 2: " << dat2 << endl;


        if (dat1 > dat0){
            cout << "Date 1 is later than Date 0"<<endl;
        } else if (dat1 < dat0){
            cout << "Date 1 is earlier than Date 0"<<endl;
        }else{
            cout << "Date 0 and Date 1 are the same." << endl;
        }

        if (dat2 > dat0){
            cout << "Date 2 is later than Date 0"<<endl;
        } else if (dat2 < dat0){
            cout << "Date 2 is earlier than Date 0"<<endl;
        }else{
            cout << "Date 0 and Date 2 afe the same." << endl;
        }
        

    } else if (choice == 2) {
        // Create a Logger instance
        Logger logger("log.txt");

        // Log different types of messages
        logger.logInfo("This is an informational message.");
        logger.logWarning("This is a warning message.");
        logger.logError("This is an error message.");

        // Manually log a custom message using the << operator overload
        logger << "[DEBUG] This is a debug message logged directly.";

        // Close the log file (optional, destructor will also close it)
        logger.closeLogFile();
    } else if (choice == 3) {
        EmployeeDatabase db("employees.txt");

        // Display all employees
        cout << "All Employees:" << endl;
        db.displayAll();

        // Search for an employee by ID
        cout << "enter an employee id to searchand update: ";
        cin >> a;
        cout << endl;
        Employee* emp = db.searchById(a);
        if (emp) {
            cout << "\nEmployee found - ID: " << emp->id
                    << ", Name: " << emp->name
                    << ", Department: " << emp->department << endl;
        } else {
            cout << "\nEmployee with ID " << a << " not found." << endl;
            return 0;
        }

        // Update an employee record
        cout << "enter new department: ";
        string dep;
        cin >> dep;
        cout << endl;
        if (db.updateEmployee(a, emp->name, dep)) {
            cout << "\nEmployee ID " << a << " updated successfully." << endl;
        } else {
            cout << "\nEmployee ID " << a << " not found for updating." << endl;
        }

        // Save changes back to the file
        db.saveToFile();

        // Display all employees after update
        cout << "\nAll Employees after update:" << endl;
        db.displayAll();
    } else if (choice == 4) {
       Complex c1, c2, result;
        char operation;

        // Get user input for the first complex number
        cout << "Enter the first complex number:\n";
        cin >> c1;

        // Get user input for the second complex number
        cout << "Enter the second complex number:\n";
        cin >> c2;

        // Get user input for the operation
        cout << "Enter an operation (+, -, *, /): ";
        cin >> operation;

        // Perform the chosen operation
        switch (operation) {
            case '+':
                result = c1 + c2;
                cout << "Result: " << result << endl;
                break;
            case '-':
                result = c1 - c2;
                cout << "Result: " << result << endl;
                break;
            case '*':
                result = c1 * c2;
                cout << "Result: " << result << endl;
                break;
            case '/':
                result = c1 / c2;
                cout << "Result: " << result << endl;
                break;
            default:
                cout << "Invalid operation!" << endl;
                break;
        } 
    }

    return 0;
}