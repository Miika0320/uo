//Mikaela Dobie 300164161
#include <iostream>
#include <unordered_map>
#include <string>
using namespace std;

// DiaryEntry class to store each diary entries
class DiaryEntry {
public:
    int id;
    string content;

    DiaryEntry(int entryId, const string& eContent)
        : id(entryId), content(eContent) {}

    void print() const {
        cout << "ID: " << id << ", Entry content: " << content << endl;
    }
};

// Diary class to modify diary entries
class Diary {
private:
    unordered_map<int, DiaryEntry*> entries;

public:
    ~Diary() {
        // Destructor to clean up dynamically allocated memory
        for (auto& pair : entries) {
            delete pair.second;
        }
        entries.clear();
    }

    void addEntry(int id, const string& content) {
        if (entries.find(id) != entries.end()) {
            cout << "Entry with ID " << id << " already exists.\n";
            return;
        }
        entries[id] = new DiaryEntry(id, content);
    }

    void deleteEntry(int id) {
        auto it = entries.find(id);
        if (it != entries.end()) {
            delete it->second; // Free memory
            entries.erase(it); // Remove from the map
        } else {
            cout << "No entry with ID " << id << " found.\n";
        }
    }

    void printAllEntries() const {
        if (entries.empty()) {
            cout << "No entries available.\n";
            return;
        }
        for (const auto& pair : entries) {
            pair.second->print();
        }
    }
};

int main() {
    Diary diary;

    // Adding entries
    diary.addEntry(1, "This is the first entry");
    diary.addEntry(2, "This is the second entry");
    diary.addEntry(3, "This is the third entry");

    // Printing all entries
    cout << "All entries:\n";
    diary.printAllEntries();

    // Deleting an entry
    cout << "Deleting the entry with ID: 2\n";
    diary.deleteEntry(2);

    // Printing all entries after deletion
    cout << "All entries without Entry of ID 2:\n";
    diary.printAllEntries();

    return 0;
}
