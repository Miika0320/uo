#include <iostream>
#include <string>
#include <type_traits>
#include <vector>
#include <memory>
using namespace std;

// Base class Employee
class Employee {
protected:
    string name;
public:
    Employee(string n) : name(n) {}
    virtual ~Employee() {}  // Virtual destructor for polymorphism
};

// Derived class Manager
class Manager : public Employee {
    int projectsManaged;
public:
    Manager(string n, int projects) : Employee(n), projectsManaged(projects) {}

    int getProjectsManaged() const { return projectsManaged; }
};

// Derived class Developer
class Developer : public Employee {
    int linesOfCode;
public:
    Developer(string n, int lines) : Employee(n), linesOfCode(lines) {}

    int getLinesOfCode() const { return linesOfCode; }
};

// Bonus Calculator class for method overloading
class BonusCalculator {
public:
    // Overloaded method for Manager
    static double calculateBonus(const Manager& manager) {
        return manager.getProjectsManaged() * 500;
    }

    // Overloaded method for Developer
    static double calculateBonus(const Developer& developer) {
        return developer.getLinesOfCode() * 0.05;
    }
};

// Base template for IterativeSquare (Compile-time recursion)
template <typename T, int N>
class IterativeSquare {
public:
    static T apply(T value) {
        return IterativeSquare<T, N - 1>::apply(value * value);
    }
};

// Specialization for the base case (N = 0)
template <typename T>
class IterativeSquare<T, 0> {
public:
    static T apply(T value) {
        return value;
    }
};

// Runtime version of iterative squaring
int iterativeSquareRuntime(int value, int n) {
    for (int i = 0; i < n; ++i) {
        value *= value;
    }
    return value;
}

// Primary template for UnitConverter (to be specialized)
template <typename From, typename To>
struct UnitConverter;

// Specialization: Centimeters to Meters
template <>
struct UnitConverter<int, double> {
    static double convert(int value) {
        return value / 100.0;  // Convert cm to m (integer input, double output)
    }
};

template <>
struct UnitConverter<double, double> {
    static double convert(double value) {
        return value / 100.0;  // Convert cm to m (double input)
    }
};

// Specialization: Meters to Centimeters
template <>
struct UnitConverter<double, int> {
    static int convert(double value) {
        return static_cast<int>(value * 100);  // Convert m to cm (double input, int output)
    }
};

template <>
struct UnitConverter<int, int> {
    static int convert(int value) {
        return value * 100;  // Convert m to cm (integer input and output)
    }
};

// Template for printing the result with precision control (for floating-point types)
template <typename T>
void printResult(T value) {
    if constexpr (std::is_floating_point<T>::value) {
        std::cout.precision(2);
        std::cout << std::fixed;
    }
    std::cout << "Result: " << value << std::endl;
}

// Base class Vehicle
class Vehicle {
protected:
    string name;
    int year;
public:
    Vehicle(string n, int y) : name(n), year(y) {}
    virtual ~Vehicle() {}

    virtual void showDetails() const {
        cout << "Vehicle: " << name << ", Year: " << year << endl;
    }
};

// Derived class Car
class Car : public Vehicle {
    int passengers;
public:
    Car(string n, int y, int p) : Vehicle(n, y), passengers(p) {}

    void showDetails() const override {
        cout << "Car: " << name << ", Year: " << year 
             << ", Passengers: " << passengers << endl;
    }
};

// Derived class Truck
class Truck : public Vehicle {
    double loadCapacity;
public:
    Truck(string n, int y, double lc) : Vehicle(n, y), loadCapacity(lc) {}

    double getLoadCapacity() const { return loadCapacity; }

    void showDetails() const override {
        cout << "Truck: " << name << ", Year: " << year 
             << ", Load Capacity: " << loadCapacity << " tons" << endl;
    }
};

// Fleet template to manage a collection of vehicles
template <typename T>
class Fleet {
    vector<shared_ptr<T>> vehicles;  // Aggregation: collection of vehicles
public:
    // Add a vehicle to the fleet
    void addVehicle(shared_ptr<T> vehicle) {
        vehicles.push_back(vehicle);
    }

    // Remove a vehicle from the fleet by index
    void removeVehicle(size_t index) {
        if (index < vehicles.size()) {
            vehicles.erase(vehicles.begin() + index);
            cout << "Vehicle removed from fleet.\n";
        } else {
            cout << "Invalid index!\n";
        }
    }

    // Show details of all vehicles in the fleet
    void showDetails() const {
        cout << "Fleet Details:\n";
        for (const auto& vehicle : vehicles) {
            vehicle->showDetails();
        }
    }
};

// Template specialization for Fleet<Truck> to calculate total load capacity
template <>
class Fleet<Truck> {
    vector<shared_ptr<Truck>> trucks;  // Aggregation: collection of trucks
public:
    void addVehicle(shared_ptr<Truck> truck) {
        trucks.push_back(truck);
    }

    void removeVehicle(size_t index) {
        if (index < trucks.size()) {
            trucks.erase(trucks.begin() + index);
            cout << "Truck removed from fleet.\n";
        } else {
            cout << "Invalid index!\n";
        }
    }

    void showDetails() const {
        cout << "Truck Fleet Details:\n";
        for (const auto& truck : trucks) {
            truck->showDetails();
        }
    }

    // Additional function to calculate the total load capacity of all trucks
    double totalLoadCapacity() const {
        double totalCapacity = 0;
        for (const auto& truck : trucks) {
            totalCapacity += truck->getLoadCapacity();
        }
        return totalCapacity;
    }
};

int main() {


    int choice;
    cout << "Choose an exercise: 1, 2, 3, or 4: ";
    cin >> choice;

    if (choice == 1) {
        
        string mName, eName;
        int projects, line;

        cout << "Enter name of the manager: ";
        cin >> mName;
        cout << "Enter number of projects managed: ";
        cin >> projects;

        Manager manager(mName, projects);
        
        cout << "\nEnter name of the developer: ";
        cin >> eName;
        cout << "Enter number of lines of code written: ";
        cin >> line;

        Developer developer(eName, line);

        // Calculate bonuses using overloaded methods
        double managerBonus = BonusCalculator::calculateBonus(manager);
        double developerBonus = BonusCalculator::calculateBonus(developer);

        // Display the results
        cout << "\nManager " << manager.getProjectsManaged() << " projects, Bonus: $" << managerBonus << endl;
        cout << "Developer " << developer.getLinesOfCode() << " lines of code, Bonus: $" << developerBonus << endl;
    } else if (choice == 2) {
        int number, iterations;

        cout << "Enter an int to operate on: ";
        cin >> number;
        cout << "Enter how many times to apply the operation: ";
        cin >> iterations;

        try {
            // Use runtime version for user input
            int result = iterativeSquareRuntime(number, iterations);
            cout << "Result: " << result << endl;

            int result2 = IterativeSquare<int, 3>::apply(iterations);
            cout << "Result example hardcoded with the value = 3: " << result << endl;
        } catch (const exception& e) {
            cerr << "An error occurred: " << e.what() << endl;
        }

    } else if (choice == 3) {
        int select;
    cout << "Choose conversion type:\n";
    cout << "1. Centimeters to Meters\n";
    cout << "2. Meters to Centimeters\n";
    cout << "Enter your choice (1 or 2): ";
    cin >> select;

    if (select == 1) {
        cout << "\nEnter the value in centimeters: ";
        double cm;
        cin >> cm;

        // Use UnitConverter to convert cm to meters
        double meters = UnitConverter<double, double>::convert(cm);
        printResult(meters);

    } else if (select == 2) {
        cout << "\nEnter the value in meters: ";
        double meters;
        cin >> meters;

        // Use UnitConverter to convert meters to cm
        int cm = UnitConverter<double, int>::convert(meters);
        printResult(cm);

    } else {
        cout << "Invalid choice! Please select 1 or 2." << endl;
    }

        
    } else if (choice == 4) {
        Fleet<Car> carFleet;
        Fleet<Truck> truckFleet;
        int selection;

        while (true) {
            cout << "\nMenu:\n";
            cout << "1. Add Car\n";
            cout << "2. Add Truck\n";
            cout << "3. Show Car Fleet Details\n";
            cout << "4. Show Truck Fleet Details\n";
            cout << "5. Show Total Truck Load Capacity\n";
            cout << "6. Exit\n";
            cout << "Enter your choice: ";
            cin >> selection;

            if (selection == 1) {
                string name;
                int year, passengers;
                cout << "Enter car name: ";
                cin >> ws;  // To consume any trailing whitespace
                getline(cin, name);
                cout << "Enter manufacturing year: ";
                cin >> year;
                cout << "Enter number of passengers: ";
                cin >> passengers;

                carFleet.addVehicle(make_shared<Car>(name, year, passengers));
                cout << "Car added to fleet.\n";

            } else if (selection == 2) {
                string name;
                int year;
                double loadCapacity;
                cout << "Enter truck name: ";
                cin >> ws;  // To consume any trailing whitespace
                getline(cin, name);
                cout << "Enter manufacturing year: ";
                cin >> year;
                cout << "Enter load capacity (in tons): ";
                cin >> loadCapacity;

                truckFleet.addVehicle(make_shared<Truck>(name, year, loadCapacity));
                cout << "Truck added to fleet.\n";

            } else if (selection == 3) {
                cout << "\nCar Fleet Details:\n";
                carFleet.showDetails();

            } else if (selection == 4) {
                cout << "\nTruck Fleet Details:\n";
                truckFleet.showDetails();

            } else if (selection == 5) {
                double totalCapacity = truckFleet.totalLoadCapacity();
                cout << "Total Truck Load Capacity: " << totalCapacity << " tons\n";

            } else if (selection == 6) {
                cout << "Exiting...\n";
                break;

            } else {
                cout << "Invalid choice! Please try again.\n";
            }
        }
    }

    return 0;
}
