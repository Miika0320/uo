// ------------------------------------------------------------------------------
// Assignment 4
// Written by: Mikaela Dobie 300164161
// For CSI2372 Section B
// Time needed to complete this assignment: 6 hours
// List the resources used to complete this assignment: Course notes, W3 Schools, stack overflow
// ----------------------------------------------------------------------------- 


#include <iostream>
#include <stdexcept>
#include <cmath>
using namespace std;

class BigInteger {
private:
    int *digits; // Dynamically allocated array for digits
    int base;    // Base of the number
    int size;    // Number of digits

    void validate_base(int b) {
        if (b < 2 || b > 36) {
            throw invalid_argument("Base must be between 2 and 36.");
        }
    }

    char digit_to_char(int d) const {
        if (d >= 0 && d <= 9) return '0' + d;
        if (d >= 10 && d < 36) return 'A' + (d - 10);
        throw invalid_argument("Invalid digit.");
    }

    int char_to_digit(char c) const {
        if (c >= '0' && c <= '9') return c - '0';
        if (c >= 'A' && c <= 'Z') return c - 'A' + 10;
        throw invalid_argument("Invalid character.");
    }

public:
    // Default constructor
    BigInteger() : digits(new int[1]{0}), base(10), size(1) {}

    // Constructor with number and base
    BigInteger(int number, int b) {
        validate_base(b);
        base = b;

        // Count digits in the given base
        int temp = number;
        size = (number == 0) ? 1 : 0;
        while (temp > 0) {
            size++;
            temp /= base;
        }

        digits = new int[size];
        for (int i = size - 1; i >= 0; --i) {
            digits[i] = number % base;
            number /= base;
        }
    }

    // Copy constructor
    BigInteger(const BigInteger &other)
        : digits(new int[other.size]), base(other.base), size(other.size) {
        for (int i = 0; i < size; ++i) {
            digits[i] = other.digits[i];
        }
    }

    // Destructor
    ~BigInteger() {
        delete[] digits;
    }

    // Method: Number of digits
    int get_num_digits() const {
        return size;
    }

    // Method: get base
    int getBase() {
        return base;
    }

    // Method: Add a digit
    void add_digit(int digit) {
        if (digit < 0 || digit >= base) {
            throw invalid_argument("Digit out of range for the given base.");
        }

        // Create a new array with extra space
        int *new_digits = new int[size + 1];
        for (int i = 0; i < size; ++i) {
            new_digits[i] = digits[i];
        }
        new_digits[size] = digit;

        delete[] digits;
        digits = new_digits;
        size++;
    }

    // Method: convert base
    BigInteger convert_base(int new_base) const {
        if (new_base < 2 || new_base > 36) {
            throw invalid_argument("Invalid base: Must be between 2 and 36.");
        }

        // Convert the number to base 10
        BigInteger result;
        result.base = new_base;

        int decimal_value = 0;
        for (int i = 0; i < size; ++i) {
            decimal_value = decimal_value * base + digits[i];
        }

        // Convert base 10 number to the new base
        int temp = decimal_value;
        result.size = 0;
        while (temp > 0) {
            ++result.size;
            temp /= new_base;
        }

        result.digits = new int[result.size];
        temp = decimal_value;
        for (int i = result.size - 1; i >= 0; --i) {
            result.digits[i] = temp % new_base;
            temp /= new_base;
        }

        if (result.size == 0) { // Handle the case for zero
            result.size = 1;
            result.digits = new int[1]{0};
        }

        return result;
    }


    // Overloaded operator: Access digits
    int operator[](int index) const {
        if (index < 0 || index >= size) {
            throw out_of_range("Index out of range.");
        }
        return digits[index];
    }

    // Overloaded operator: Print the number
    friend ostream &operator<<(ostream &out, const BigInteger &num) {
        for (int i = 0; i < num.size; ++i) {
            out << num.digit_to_char(num.digits[i]);
        }
        out << "(" << num.base << ")";
        return out;
    }

    // Overloaded operator: Get number as input
    friend istream &operator>>(istream &in, BigInteger &num) {
        string str;
        int b;

        in >> str >> b;
        num.validate_base(b);

        num.base = b;
        num.size = str.length();
        delete[] num.digits;
        num.digits = new int[num.size];

        for (int i = 0; i < num.size; ++i) {
            num.digits[i] = num.char_to_digit(str[i]);
            if (num.digits[i] >= num.base) {
                throw invalid_argument("Invalid digit for the given base.");
            }
        }
        return in;
    }

    // Overloaded operator: Assignment
    BigInteger &operator=(const BigInteger &other) {
        if (this == &other) return *this;

        delete[] digits;
        size = other.size;
        base = other.base;
        digits = new int[size];
        for (int i = 0; i < size; ++i) {
            digits[i] = other.digits[i];
        }
        return *this;
    }

    // Overloaded operator: remove least significant digit
    void remove_digit() {
        if (size == 1) {
            digits[0] = 0; // Reset to 0 if it's the last digit
            return;
        }

        // Create a new array with one less size
        int *new_digits = new int[size - 1];
        for (int i = 0; i < size - 1; ++i) {
            new_digits[i] = digits[i];
        }

        delete[] digits;
        digits = new_digits;
        size--;
    }

    // Overloaded operator: insert number into a position like a string
    void insert_digit(int digit, int position) {
        if (digit < 0 || digit >= base || position < 0 || position > size) {
            throw invalid_argument("Invalid digit or position.");
        }

        // Create a new array with one more size
        int *new_digits = new int[size + 1];
        for (int i = 0, j = 0; i < size + 1; ++i) {
            if (i == position) {
                new_digits[i] = digit;
            } else {
                new_digits[i] = digits[j++];
            }
        }

        delete[] digits;
        digits = new_digits;
        size++;
    }

    // Overloaded operator: addition
    BigInteger operator+(const BigInteger &other) const {
        if (base != other.base) {
            throw invalid_argument("Base mismatch for addition.");
        }

        int max_size = max(size, other.size);
        int carry = 0;
        BigInteger result;
        result.base = base;
        result.size = max_size + 1; // May require extra space for carry
        result.digits = new int[result.size];

        for (int i = 0; i < result.size; ++i) {
            int digit1 = (i < size) ? digits[size - 1 - i] : 0;
            int digit2 = (i < other.size) ? other.digits[other.size - 1 - i] : 0;
            int sum = digit1 + digit2 + carry;

            result.digits[result.size - 1 - i] = sum % base;
            carry = sum / base;
        }

        // Remove leading zero if no carry
        if (result.digits[0] == 0) {
            int *trimmed_digits = new int[result.size - 1];
            for (int i = 1; i < result.size; ++i) {
                trimmed_digits[i - 1] = result.digits[i];
            }
            delete[] result.digits;
            result.digits = trimmed_digits;
            result.size--;
        }

        return result;
    }

    // Overloaded operator: boolean equals
    bool operator==(const BigInteger &other) const {
        if (base != other.base || size != other.size) return false;
        for (int i = 0; i < size; ++i) {
            if (digits[i] != other.digits[i]) return false;
        }
        return true;
    }

    // Overloaded operator: less than
    bool operator<(const BigInteger &other) const {
        if (base != other.base) {
            throw invalid_argument("Base mismatch for comparison.");
        }
        if (size != other.size) return size < other.size;

        for (int i = 0; i < size; ++i) {
            if (digits[i] != other.digits[i]) {
                return digits[i] < other.digits[i];
            }
        }
        return false;
    }

    // Overloaded operator: boolean not equal
    bool operator!=(const BigInteger &other) const {
        return !(*this == other);
    }

    // Overloaded operator: boolean greater than
    bool operator>(const BigInteger &other) const {
        return !(*this < other) && !(*this == other);
    }

    // Overloaded operator: boolean less than or equal to
    bool operator<=(const BigInteger &other) const {
        return (*this < other) || (*this == other);
    }

    // Overloaded operator: boolean greater or equal to
    bool operator>=(const BigInteger &other) const {
        return (*this > other) || (*this == other);
    }

    // Overloaded operator: increment by 1
    BigInteger &operator++() {
        *this = *this + BigInteger(1, base);
        return *this;
    }

    // Overloaded operator: decrement by 1
    BigInteger &operator--() {
        *this = *this - BigInteger(1, base);
        return *this;
    }


    // Overloaded operator: subtraction
    BigInteger operator-(const BigInteger &other) const {
        if (base != other.base) {
            throw invalid_argument("Base mismatch for subtraction.");
        }

        // Check if the first number is smaller than the second
        if (*this < other) {
            throw invalid_argument("Subtraction would result in a negative number.");
        }

        BigInteger result;
        result.base = base;
        result.size = size;
        result.digits = new int[result.size];

        int borrow = 0;
        for (int i = 0; i < size; ++i) {
            int digit1 = digits[size - 1 - i];
            int digit2 = (i < other.size) ? other.digits[other.size - 1 - i] : 0;
            int diff = digit1 - digit2 - borrow;

            if (diff < 0) {
                diff += base;
                borrow = 1;
            } else {
                borrow = 0;
            }

            result.digits[result.size - 1 - i] = diff;
        }

        // Remove leading zeros
        while (result.size > 1 && result.digits[0] == 0) {
            for (int i = 1; i < result.size; ++i) {
                result.digits[i - 1] = result.digits[i];
            }
            result.size--;
        }

        return result;
    }

    // Overloaded operator: multiplication
    BigInteger operator*(const BigInteger &other) const {
        if (base != other.base) {
            throw invalid_argument("Base mismatch for multiplication.");
        }

        BigInteger result;
        result.base = base;
        result.size = size + other.size;
        result.digits = new int[result.size](); // Initialize with zeros

        for (int i = size - 1; i >= 0; --i) {
            int carry = 0;
            for (int j = other.size - 1; j >= 0; --j) {
                int product = digits[i] * other.digits[j] + result.digits[i + j + 1] + carry;
                result.digits[i + j + 1] = product % base;
                carry = product / base;
            }
            result.digits[i] += carry;
        }

        // Remove leading zeros
        while (result.size > 1 && result.digits[0] == 0) {
            for (int i = 1; i < result.size; ++i) {
                result.digits[i - 1] = result.digits[i];
            }
            result.size--;
        }

        return result;
    }

    // Overloaded operator: division
    BigInteger operator/(const BigInteger &other) const {
        if (base != other.base) {
            throw invalid_argument("Base mismatch for division.");
        }
        if (other == BigInteger(0, base)) {
            throw invalid_argument("Division by zero is undefined.");
        }

        BigInteger dividend = *this;
        BigInteger divisor = other;
        BigInteger quotient(0, base);

        while (dividend >= divisor) {
            dividend = dividend - divisor;
            quotient = quotient + BigInteger(1, base);
        }

        return quotient;
    }

    // Overloaded operator: modulus
    BigInteger operator%(const BigInteger &other) const {
        if (base != other.base) {
            throw invalid_argument("Base mismatch for modulo.");
        }
        if (other == BigInteger(0, base)) {
            throw invalid_argument("Modulo by zero is undefined.");
        }

        BigInteger dividend = *this;
        BigInteger divisor = other;

        while (dividend >= divisor) {
            dividend = dividend - divisor;
        }

        return dividend;
    }


};


void display_menu() {
    cout << "\nSelect an operation:\n";
    cout << "1. Addition (+)\n";
    cout << "2. Subtraction (-)\n";
    cout << "3. Multiplication (*)\n";
    cout << "4. Division (/)\n";
    cout << "5. Modulo (%)\n";
    cout << "6. Increment \n";
    cout << "7. Decrement \n";
    cout << "8. Compare (==, !=, >, <, >=, <=)\n";
    cout << "9. Convert base \n";
    cout << "10. Get number of digits \n";
    cout << "11. Insert a digit \n";
    cout << "12. Delete least significant \n";
    cout << "13. Exit\n";
    cout << "Enter your choice: ";

}

int main() {
    try {
        BigInteger num1, num2;

        cout << "Enter the first number and its base (e.g., '123 10'): ";
        cin >> num1;


        int choice = 0;
        while (choice != 9) {
            display_menu();
            cin >> choice;

            switch (choice) {
                case 1: { // Addition
                    cout << "Enter the second number and its base (e.g., '45 10'): ";
                    cin >> num2;
                    BigInteger sum = num1 + num2;
                    cout << "Result: " << sum << "\n";
                    break;
                }
                case 2: { // Subtraction
                    cout << "Enter the second number and its base (e.g., '45 10'): ";
                    cin >> num2;
                    try {
                        BigInteger diff = num1 - num2;
                        cout << "Result: " << diff << "\n";
                    } catch (const exception &e) {
                        cerr << "Error: " << e.what() << "\n";
                    }
                    break;
                }
                case 3: { // Multiplication
                    cout << "Enter the second number and its base (e.g., '45 10'): ";
                    cin >> num2;
                    BigInteger product = num1 * num2;
                    cout << "Result: " << product << "\n";
                    break;
                }
                case 4: { // Division
                    cout << "Enter the second number and its base (e.g., '45 10'): ";
                    cin >> num2;
                    try {
                        BigInteger quotient = num1 / num2;
                        cout << "Result: " << quotient << "\n";
                    } catch (const exception &e) {
                        cerr << "Error: " << e.what() << "\n";
                    }
                    break;
                }
                case 5: { // Modulo
                    cout << "Enter the second number and its base (e.g., '45 10'): ";
                    cin >> num2;
                    try {
                        BigInteger remainder = num1 % num2;
                        cout << "Result: " << remainder << "\n";
                    } catch (const exception &e) {
                        cerr << "Error: " << e.what() << "\n";
                    }
                    break;
                }
                case 6: { // Increment
                    ++num1;
                    cout << "Num1 after increment: " << num1 << "\n";
                    break;
                }
                case 7: { // Decrement
                    --num1;
                    cout << "Num1 after decrement: " << num1 << "\n";
                    break;
                }
                case 8: { // Comparisons
                    cout << "Enter the second number and its base (e.g., '45 10'): ";
                    cin >> num2;
                    cout << "num1 == num2: " << (num1 == num2) << "\n";
                    cout << "num1 != num2: " << (num1 != num2) << "\n";
                    cout << "num1 > num2: " << (num1 > num2) << "\n";
                    cout << "num1 < num2: " << (num1 < num2) << "\n";
                    cout << "num1 >= num2: " << (num1 >= num2) << "\n";
                    cout << "num1 <= num2: " << (num1 <= num2) << "\n";
                    break;
                }
                case 9: { // Base conversion
                    int new_base;
                    cout << "Enter the new base (2 to 36): ";
                    cin >> new_base;
                    try {
                        BigInteger converted = num1.convert_base(new_base);
                        cout << "Num1 in base " << new_base << ": " << converted << "\n";
                    } catch (const exception &e) {
                        cerr << "Error: " << e.what() << "\n";
                    }
                    break;
                }
                case 10: { // Get size
                    cout << num1.get_num_digits() << endl;
                    break;
                }
                case 11: { // Insert a digit
                    int digit, position;
                    cout << "Enter the digit to insert: ";
                    cin >> digit;
                    cout << "Enter the position (0-based index): ";
                    cin >> position;
                    cout << "Enter the digit you would like to add: \n";
                    try {
                        num1.insert_digit(digit, position);
                        cout << "Updated Number: " << num1 << "\n";
                    } catch (const exception &e) {
                        cerr << "Error: " << e.what() << "\n";
                    }
                    break;
                    
                }
                case 12: { // Delete a digit

                    try {
                        num1.remove_digit();
                        cout << "Updated Number: " << num1 << "\n";
                    } catch (const exception &e) {
                        cerr << "Error: " << e.what() << "\n";
                    }
                    break;
                }
                case 13: { // Exit
                    cout << "Exiting program.\n";
                    return 0;
                }
                default:
                    cout << "Invalid choice. Goodbye.\n";
                    return 0;
            }
        }
    } catch (const exception &e) {
        cerr << "Error: " << e.what() << "\n";
    }

    return 0;
}

