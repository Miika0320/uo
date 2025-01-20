// Mikaela Dobie 300164161
#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
using namespace std;

class Animal {
public:
    string name;
    string type;

    Animal(const string& aName, const string& aType)
        : name(aName), type(aType) {}

    void print() const {
        cout << "Name: " << name << ", Race (type): " << type << endl;
    }
};

class AnimalShelter {
private:
    vector<Animal*> animals;

public:
    ~AnimalShelter() {
        // Destructor to clean up dynamically allocated memory
        for (Animal* animal : animals) {
            delete animal;
        }
        animals.clear();
    }

    void addAnimal(const string& name, const string& type) {
        animals.push_back(new Animal(name, type));
    }

    void removeAnimalByName(const string& name) {
        auto it = remove_if(animals.begin(), animals.end(), 
            [&name](Animal* animal) {
                bool match = animal->name == name;
                if (match) {
                    delete animal; 
                }
                return match;
            });
        animals.erase(it, animals.end());
    }

    void displayAllAnimals() const {
        if (animals.empty()) {
            cout << "There are currently no animals in the shelter.\n";
            return;
        }
        for (const Animal* animal : animals) {
            animal->print();
        }
    }
};

int main() {
    AnimalShelter shelter;

    // Adding animals
    shelter.addAnimal("Wallace", "Dog");
    shelter.addAnimal("Mila", "Cat");
    shelter.addAnimal("Maya", "Dog");

    // Displaying all animals
    cout << "Current animals in the shelter:\n";
    shelter.displayAllAnimals();

    // Removing an animal by name
    cout << "Removing animal 'Maya'...\n";
    shelter.removeAnimalByName("Maya");

    // Displaying all animals after removing Maya
    cout << "Current animals in the shelter after removing one:\n";
    shelter.displayAllAnimals();

    return 0;
}
