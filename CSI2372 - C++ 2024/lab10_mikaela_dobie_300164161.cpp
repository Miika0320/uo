#include <iostream>
#include <queue>
#include <string>
#include <unordered_map>
#include <vector>
#include <iterator>
#include <unordered_set>
#include <algorithm>
#include <map>
#include <set>
using namespace std;


// Backend system for managing group messages
class MessagingSystem {
private:
    unordered_multimap<string, string> groupMessages;

public:
    // Post a message to a group
    void postMessage(const string &groupID, const string &message) {
        groupMessages.emplace(groupID, message);
        cout << "Message posted to group " << groupID << ".\n";
    }

    // Retrieve all messages for a group
    void retrieveMessages(const string &groupID) const {
        auto range = groupMessages.equal_range(groupID);
        if (range.first == range.second) {
            cout << "No messages found for group " << groupID << ".\n";
            return;
        }

        cout << "Messages for group " << groupID << ":\n";
        for (auto it = range.first; it != range.second; ++it) {
            cout << "- " << it->second << "\n";
        }
    }

    // Clear all messages for a group
    void clearMessages(const string &groupID) {
        auto count = groupMessages.erase(groupID);
        if (count > 0) {
            cout << "Cleared " << count << " messages for group " << groupID << ".\n";
        } else {
            cout << "No messages found for group " << groupID << " to clear.\n";
        }
    }
};



// Define the Patient struct
struct Patient {
    string name;
    int condition_severity; // Higher values indicate more severe conditions
    int order_of_arrival;   // Lower values indicate earlier arrival

    // Overload comparison operator for priority_queue
    bool operator<(const Patient &other) const {
        // Patients with higher severity are treated first
        // If severity is the same, earlier arrivals are treated first
        if (condition_severity == other.condition_severity) {
            return order_of_arrival > other.order_of_arrival;
        }
        return condition_severity < other.condition_severity;
    }
};

// Structure to store individual feedback entries
struct Feedback {
    string message;        // The feedback message
    vector<string> tags; // Tags associated with the feedback
};

// Tool for analyzing feedback
class FeedbackAnalyzer {
private:
    // Multiset to categorize feedback by tags
    unordered_multiset<string> tagSet;

    // Map to store feedback entries
    vector<Feedback> feedbackEntries;

public:
    // Add feedback with associated tags
    void addFeedback(const string &message, const vector<string> &tags) {
        Feedback feedback{message, tags};
        feedbackEntries.push_back(feedback);
        for (const auto &tag : tags) {
            tagSet.insert(tag);
        }
        cout << "Feedback added successfully.\n";
    }

    // Retrieve all feedback associated with a particular tag
    void retrieveFeedbackByTag(const string &tag) const {
        cout << "Feedback entries associated with tag \"" << tag << "\":\n";
        for (const auto &entry : feedbackEntries) {
            if (find(entry.tags.begin(), entry.tags.end(), tag) != entry.tags.end()) {
                cout << "- " << entry.message << "\n";
            }
        }
    }

    // Provide statistics on most and least common tags
    void tagStatistics() const {
        if (tagSet.empty()) {
            cout << "No tags found.\n";
            return;
        }

        // Count the frequency of each tag
        unordered_map<string, int> tagFrequency;
        for (const auto &tag : tagSet) {
            tagFrequency[tag]++;
        }

        // Find the most and least common tags
        auto maxTag = max_element(tagFrequency.begin(), tagFrequency.end(),
                                       [](const auto &a, const auto &b) { return a.second < b.second; });
        auto minTag = min_element(tagFrequency.begin(), tagFrequency.end(),
                                       [](const auto &a, const auto &b) { return a.second < b.second; });

        cout << "Most common tag: " << maxTag->first << " (" << maxTag->second << " occurrences)\n";
        cout << "Least common tag: " << minTag->first << " (" << minTag->second << " occurrences)\n";
    }
};

// Student class
class Student {
public:
    int studentID;
    map<string, int> scores;

    // Constructor
    Student(int id) : studentID(id) {}

    // Overload operator < for set to ensure unique students by studentID
    bool operator<(const Student &other) const {
        return studentID < other.studentID;
    }

    // Add or update a subject score
    void updateScore(const string &subject, int score) {
        scores[subject] = score;
    }

    // Display student record
    void displayRecord() const {
        cout << "Student ID: " << studentID << "\n";
        for (const auto &entry : scores) {
            cout << " - " << entry.first << ": " << entry.second << "\n";
        }
    }
};

// Student Record Management System
class StudentManagementSystem {
private:
    set<Student> students;

public:
    // Add a new student
    void addStudent(int studentID) {
        auto result = students.emplace(studentID);
        if (result.second) {
            cout << "Student with ID " << studentID << " added successfully.\n";
        } else {
            cout << "Student with ID " << studentID << " already exists.\n";
        }
    }

    // Retrieve a student's record
    void retrieveStudentRecord(int studentID) const {
        auto it = students.find(Student(studentID));
        if (it != students.end()) {
            it->displayRecord();
        } else {
            cout << "Student with ID " << studentID << " not found.\n";
        }
    }

    // Update a student's score for a specific subject
    void updateStudentScore(int studentID, const string &subject, int score) {
        auto it = students.find(Student(studentID));
        if (it != students.end()) {
            // Create a temporary student to modify scores
            Student temp = *it;
            students.erase(it); // Remove the old record
            temp.updateScore(subject, score);
            students.insert(temp); // Re-insert updated record
            cout << "Score updated for Student ID " << studentID << " in subject " << subject << ".\n";
        } else {
            cout << "Student with ID " << studentID << " not found.\n";
        }
    }
};

int main() {
    int choiceA;
    cout << "Choose an exercise: 1, 2, 3, or 4: ";
    cin >> choiceA;
    int a,b,c;

    if (choiceA == 1) {
        priority_queue<Patient> triage_queue;
        int order_counter = 0; // Keeps track of arrival order
        int choice;

        do {
            cout << "\nHospital Triage System\n";
            cout << "1. Add Patient\n";
            cout << "2. View Next Patient\n";
            cout << "3. Treat Next Patient\n";
            cout << "4. Exit\n";
            cout << "Enter your choice: ";
            cin >> choice;

            switch (choice) {
            case 1: {
                // Add a patient
                string name;
                int severity;
                cout << "Enter patient name: ";
                cin.ignore();
                getline(cin, name);
                cout << "Enter condition severity (higher is more severe): ";
                cin >> severity;

                triage_queue.push({name, severity, order_counter++});
                cout << "Patient " << name << " added to the triage queue.\n";
                break;
            }
            case 2: {
                // View the next patient
                if (triage_queue.empty()) {
                    cout << "No patients in the queue.\n";
                } else {
                    Patient next = triage_queue.top();
                    cout << "Next patient to treat: " << next.name
                            << " (Severity: " << next.condition_severity
                            << ", Arrival Order: " << next.order_of_arrival << ")\n";
                }
                break;
            }
            case 3: {
                // Treat the next patient
                if (triage_queue.empty()) {
                    cout << "No patients in the queue.\n";
                } else {
                    Patient next = triage_queue.top();
                    triage_queue.pop();
                    cout << "Treating patient: " << next.name << "\n";
                }
                break;
            }
            case 4:
                cout << "Exiting the system. Goodbye!\n";
                break;
            default:
                cout << "Invalid choice. Please try again.\n";
            }
        } while (choice != 4); 
    }else if(choiceA==2){

        // Main function for interacting with the system

        MessagingSystem system;
        int choice;
        string groupID, message;

        do {
            cout << "\nMessaging System\n";
            cout << "1. Post a message to a group\n";
            cout << "2. Retrieve all messages from a group\n";
            cout << "3. Clear all messages from a group\n";
            cout << "4. Exit\n";
            cout << "Enter your choice: ";
            cin >> choice;

            switch (choice) {
            case 1: {
                cout << "Enter group ID: ";
                cin >> groupID;
                cin.ignore(); // To ignore the newline after group ID input
                cout << "Enter message: ";
                getline(cin, message);
                system.postMessage(groupID, message);
                break;
            }
            case 2: {
                cout << "Enter group ID: ";
                cin >> groupID;
                system.retrieveMessages(groupID);
                break;
            }
            case 3: {
                cout << "Enter group ID: ";
                cin >> groupID;
                system.clearMessages(groupID);
                break;
            }
            case 4:
                cout << "Exiting the system. Goodbye!\n";
                break;
            default:
                cout << "Invalid choice. Please try again.\n";
            }
        } while (choice != 4);


    }else if(choiceA==3){

        FeedbackAnalyzer analyzer;
        int choice;

        do {
            cout << "\nCustomer Feedback Analyzer\n";
            cout << "1. Add Feedback\n";
            cout << "2. Retrieve Feedback by Tag\n";
            cout << "3. Display Tag Statistics\n";
            cout << "4. Exit\n";
            cout << "Enter your choice: ";
            cin >> choice;

            switch (choice) {
            case 1: {
                cin.ignore();
                string message, tag;
                vector<string> tags;

                cout << "Enter feedback message: ";
                getline(cin, message);

                cout << "Enter tags (comma-separated): ";
                getline(cin, tag);
                size_t pos = 0;
                while ((pos = tag.find(',')) != string::npos) {
                    tags.push_back(tag.substr(0, pos));
                    tag.erase(0, pos + 1);
                }
                tags.push_back(tag);

                analyzer.addFeedback(message, tags);
                break;
            }
            case 2: {
                cin.ignore();
                string tag;
                cout << "Enter tag to retrieve feedback: ";
                getline(cin, tag);
                analyzer.retrieveFeedbackByTag(tag);
                break;
            }
            case 3:
                analyzer.tagStatistics();
                break;
            case 4:
                cout << "Exiting the system. Goodbye!\n";
                break;
            default:
                cout << "Invalid choice. Please try again.\n";
            }
        } while (choice != 4);

    }else if(choiceA==4){

        StudentManagementSystem sms;
        int choice, studentID, score;
        string subject;

        do {
            cout << "\nStudent Record Management System\n";
            cout << "1. Add Student\n";
            cout << "2. Retrieve Student Record\n";
            cout << "3. Update Student Score\n";
            cout << "4. Exit\n";
            cout << "Enter your choice: ";
            cin >> choice;

            switch (choice) {
            case 1:
                cout << "Enter Student ID: ";
                cin >> studentID;
                sms.addStudent(studentID);
                break;
            case 2:
                cout << "Enter Student ID: ";
                cin >> studentID;
                sms.retrieveStudentRecord(studentID);
                break;
            case 3:
                cout << "Enter Student ID: ";
                cin >> studentID;
                cin.ignore();
                cout << "Enter Subject Name: ";
                getline(cin, subject);
                cout << "Enter New Score: ";
                cin >> score;
                sms.updateStudentScore(studentID, subject, score);
                break;
            case 4:
                cout << "Exiting the system. Goodbye!\n";
                break;
            default:
                cout << "Invalid choice. Please try again.\n";
            }
        } while (choice != 4);

    }else{
        return 0;
    }

    return 0;
}
