//Mikaela Dobie 300164161

#include <iostream>
#include <vector>
#include <algorithm>
#include <cstring> 
#include <cmath>
#include <numeric>
#include <functional>
#include <map>
#include <memory>
using namespace std;

// Custom string class
class MyString {
    char* str; // Pointer to hold the string
    size_t length; // Length of the string

public:
    // Constructors
    MyString() : str(nullptr), length(0) {}

    MyString(const char* s) {
        length = strlen(s);
        str = new char[length + 1];
        strcpy(str, s);
    }

    MyString(const MyString& other) {
        length = other.length;
        str = new char[length + 1];
        strcpy(str, other.str);
    }

    // Destructor
    ~MyString() {
        delete[] str;
    }

    // Operator + for concatenation
    MyString operator+(const MyString& other) const {
        size_t newLength = length + other.length;
        char* newStr = new char[newLength + 1];
        strcpy(newStr, str);
        strcat(newStr, other.str);
        return MyString(newStr);
    }

    // Operator == for equality comparison
    bool operator==(const MyString& other) const {
        return strcmp(str, other.str) == 0;
    }

    // Operator << for output
    friend ostream& operator<<(ostream& os, const MyString& obj) {
        os << obj.str;
        return os;
    }

    // Getter for length
    size_t getLength() const {
        return length;
    }

    // Getter for string value
    const char* getCString() const {
        return str;
    }
};

// Functor for comparing strings by length
struct CompareByLength {
    bool operator()(const MyString& a, const MyString& b) const {
        return a.getLength() < b.getLength();
    }
};

// Functor to calculate the average
struct AverageCalculator {
    double operator()(const vector<double>& data) const {
        if (data.empty()) return 0.0;
        return accumulate(data.begin(), data.end(), 0.0) / data.size();
    }
};

// Functor to calculate the standard deviation
struct StdDevCalculator {
    double operator()(const vector<double>& data) const {
        if (data.size() < 2) return 0.0;
        double mean = AverageCalculator()(data);
        double variance = accumulate(data.begin(), data.end(), 0.0,
            [mean](double sum, double value) {
                return sum + (value - mean) * (value - mean);
            }) / (data.size() - 1);
        return sqrt(variance);
    }
};

// Class for data analysis
class DataAnalysis {
    vector<double> stockPrices;  // Stores stock prices

public:
    // Add a stock price to the dataset
    void addPrice(double price) {
        stockPrices.push_back(price);
    }

    // Filter stock prices based on a lambda
    vector<double> filterPrices(const function<bool(double)>& criteria) const {
        vector<double> filtered;
        copy_if(stockPrices.begin(), stockPrices.end(), back_inserter(filtered), criteria);
        return filtered;
    }

    // Calculate average
    double calculateAverage() const {
        return AverageCalculator()(stockPrices);
    }

    // Calculate standard deviation
    double calculateStdDev() const {
        return StdDevCalculator()(stockPrices);
    }

    // Display all stock prices
    void displayPrices() const {
        cout << "Stock Prices: ";
        for (double price : stockPrices) {
            cout << price << " ";
        }
        cout << endl;
    }
};

// Templated Container class
template <typename T>
class Container {
    vector<T> elements; // Internal storage

public:
    // Add an element to the container
    void add(const T& element) {
        elements.push_back(element);
    }

    // Nested Iterator class
    class Iterator {
        typename vector<T>::iterator current;

    public:
        // Constructor
        Iterator(typename vector<T>::iterator it) : current(it) {}

        // Dereference operator
        T& operator*() {
            return *current;
        }

        // Increment operator
        Iterator& operator++() {
            ++current;
            return *this;
        }

        // Decrement operator
        Iterator& operator--() {
            --current;
            return *this;
        }

        // Inequality operator
        bool operator!=(const Iterator& other) const {
            return current != other.current;
        }
    };

    // Begin iterator
    Iterator begin() {
        return Iterator(elements.begin());
    }

    // End iterator
    Iterator end() {
        return Iterator(elements.end());
    }

    // Apply a function or lambda to each element
    void forEach(const function<void(T&)>& func) {
        for (T& element : elements) {
            func(element);
        }
    }

    // Display all elements
    void display() const {
        cout << "Container elements: ";
        for (const T& element : elements) {
            cout << element << " ";
        }
        cout << endl;
    }
};

// Base Sensor class
class Sensor {
protected:
    string id;

public:
    Sensor(string sensorId) : id(sensorId) {}
    virtual ~Sensor() {}

    string getId() const { return id; }

    // Pure virtual function to handle events
    virtual void handleEvent() const = 0;
};

// Derived TemperatureSensor class
class TemperatureSensor : public Sensor {
public:
    TemperatureSensor(string sensorId) : Sensor(sensorId) {}

    void handleEvent() const override {
        cout << "Handling temperature event for sensor ID: " << id << endl;
    }
};

// Derived PressureSensor class
class PressureSensor : public Sensor {
public:
    PressureSensor(string sensorId) : Sensor(sensorId) {}

    void handleEvent() const override {
        cout << "Handling pressure event for sensor ID: " << id << endl;
    }
};

// Real-time Monitoring System
class MonitoringSystem {
    map<string, function<void()>> sensorHandlers; // Map of sensor IDs to event handlers

public:
    // Register a sensor's handleEvent method
    void registerSensor(shared_ptr<Sensor> sensor) {
        sensorHandlers[sensor->getId()] = [sensor]() { sensor->handleEvent(); };
    }

    // Handle a sensor event dynamically
    void handleSensorEvent(const string& sensorId) {
        if (sensorHandlers.find(sensorId) != sensorHandlers.end()) {
            sensorHandlers[sensorId](); // Invoke the corresponding handleEvent method
        } else {
            cout << "No handler found for sensor ID: " << sensorId << endl;
        }
    }
};

int main() {
    int choiceA;
    cout << "Choose an exercise: 1, 2, 3, or 4: ";
    cin >> choiceA;
    int a,b,c;

    if (choiceA == 1) {
        
        vector<MyString> myStrings;
    int choice;

    while (true) {
        cout << "\nMenu:\n";
        cout << "1. Add a string\n";
        cout << "2. Concatenate two strings\n";
        cout << "3. Compare two strings\n";
        cout << "4. Show all strings\n";
        cout << "5. Sort strings by length\n";
        cout << "6. Exit\n";
        cout << "Enter your choice: ";
        cin >> choice;

        if (choice == 1) {
            string input;
            cout << "Enter a string: ";
            cin >> ws;  // To clear any trailing whitespace
            getline(cin, input);
            myStrings.push_back(MyString(input.c_str()));
            cout << "String added.\n";

        } else if (choice == 2) {
            int idx1, idx2;
            cout << "Enter the indices of two strings to concatenate (0-based): ";
            cin >> idx1 >> idx2;

            if (idx1 >= 0 && idx1 < myStrings.size() && idx2 >= 0 && idx2 < myStrings.size()) {
                MyString result = myStrings[idx1] + myStrings[idx2];
                cout << "Result of concatenation: " << result << endl;
            } else {
                cout << "Invalid indices!\n";
            }

        } else if (choice == 3) {
            int idx1, idx2;
            cout << "Enter the indices of two strings to compare (0-based): ";
            cin >> idx1 >> idx2;

            if (idx1 >= 0 && idx1 < myStrings.size() && idx2 >= 0 && idx2 < myStrings.size()) {
                if (myStrings[idx1] == myStrings[idx2]) {
                    cout << "The strings are equal.\n";
                } else {
                    cout << "The strings are not equal.\n";
                }
            } else {
                cout << "Invalid indices!\n";
            }

        } else if (choice == 4) {
            cout << "\nList of strings:\n";
            for (size_t i = 0; i < myStrings.size(); ++i) {
                cout << i << ": " << myStrings[i] << " (Length: " << myStrings[i].getLength() << ")\n";
            }

        } else if (choice == 5) {
            sort(myStrings.begin(), myStrings.end(), CompareByLength());
            cout << "Strings sorted by length.\n";

        } else if (choice == 6) {
            cout << "Exiting...\n";
            break;

        } else {
            cout << "Invalid choice! Please try again.\n";
        }
    }

    } else if (choiceA == 2) {

        DataAnalysis analysis;
    int choice;

    while (true) {
        cout << "\nMenu:\n";
        cout << "1. Add stock price\n";
        cout << "2. Display all prices\n";
        cout << "3. Filter prices above a threshold\n";
        cout << "4. Calculate average\n";
        cout << "5. Calculate standard deviation\n";
        cout << "6. Exit\n";
        cout << "Enter your choice: ";
        cin >> choice;

        if (choice == 1) {
            double price;
            cout << "Enter stock price: ";
            cin >> price;
            analysis.addPrice(price);
            cout << "Price added.\n";

        } else if (choice == 2) {
            analysis.displayPrices();

        } else if (choice == 3) {
            double threshold;
            cout << "Enter threshold: ";
            cin >> threshold;
            vector<double> filtered = analysis.filterPrices([threshold](double price) {
                return price > threshold;
            });
            cout << "Filtered prices above " << threshold << ": ";
            for (double price : filtered) {
                cout << price << " ";
            }
            cout << endl;

        } else if (choice == 4) {
            double avg = analysis.calculateAverage();
            cout << "Average stock price: " << avg << endl;

        } else if (choice == 5) {
            double stdDev = analysis.calculateStdDev();
            cout << "Standard deviation of stock prices: " << stdDev << endl;

        } else if (choice == 6) {
            cout << "Exiting...\n";
            break;

        } else {
            cout << "Invalid choice. Try again.\n";
        }
    }
        
    } else if (choiceA == 3) {

        Container<int> container;
        int numElements;

        // Get user input for elements
        cout << "Enter the number of elements to add to the container: ";
        cin >> numElements;

        cout << "Enter " << numElements << " integers:" << endl;
        for (int i = 0; i < numElements; ++i) {
            int value;
            cin >> value;
            container.add(value);
        }

        // Display elements
        cout << "\nOriginal elements:" << endl;
        container.display();

        // Iterate using custom iterator
        cout << "\nIterating using custom iterator:" << endl;
        for (auto it = container.begin(); it != container.end(); ++it) {
            cout << *it << " ";
        }
        cout << endl;

        // Modify elements using forEach
        cout << "\nModifying elements (multiply by 2):" << endl;
        container.forEach([](int& x) {
            x *= 2;
        });

        // Display modified elements
        cout << "\nModified elements:" << endl;
        container.display();
        
    } else if (choiceA == 4) {

        MonitoringSystem system;

        // Create sensors
        shared_ptr<Sensor> tempSensor = make_shared<TemperatureSensor>("T001");
        shared_ptr<Sensor> pressureSensor = make_shared<PressureSensor>("P001");

        // Register sensors
        system.registerSensor(tempSensor);
        system.registerSensor(pressureSensor);

        // Simulate events
        cout << "Simulating sensor events:" << endl;

        system.handleSensorEvent("T001"); // Invoke TemperatureSensor's handleEvent
        system.handleSensorEvent("P001"); // Invoke PressureSensor's handleEvent
        system.handleSensorEvent("X001"); // Nonexistent sensor

    }
    return 0;
}
